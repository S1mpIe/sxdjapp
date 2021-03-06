package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.*;
import com.finals.sxdj.model.sqlmodel.Order;
import com.finals.sxdj.model.sqlmodel.ShoppingAddress;
import com.finals.sxdj.model.sqlmodel.TeamCart;
import com.finals.sxdj.model.sqlmodel.Teammate;
import com.finals.sxdj.repository.GoodsMapper;
import com.finals.sxdj.repository.OrderMapper;
import com.finals.sxdj.repository.TeamMapper;
import com.finals.sxdj.repository.UserMapper;
import com.finals.sxdj.services.TeamService;
import com.finals.sxdj.utils.DESEncryptTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public JSONObject applyNewTeam(String name, String captainId,String nickName) {
        Team[] teams = teamMapper.queryMateTeam(captainId);
        JSONObject jsonObject = new JSONObject();
        if(teams.length >= 5){
            jsonObject.put("status","failed");
            jsonObject.put("errmsg","可申请（加入）的团队达到上限");
        }else {
            long id = System.currentTimeMillis()/1000;
            teamMapper.insertNewTeam(id,name);
            teamMapper.insertNewTeammates(id,captainId,"队长",nickName);
            userMapper.insertNewAccount("team-" + id,10000);
            jsonObject.put("status","success");
        }
        return jsonObject;
    }

    @Override
    public JSONObject applyNewTeammate(String teamCode, String mateId,String nickName) {
        String decrypt = DESEncryptTools.decrypt(teamCode);
        Team[] teams = teamMapper.queryMateTeam(mateId);
        JSONObject jsonObject = new JSONObject();
        try {
            long teamId = Long.parseLong(decrypt);
            for (Team team: teams) {
                if (team.getId() == teamId){
                    jsonObject.put("status","failed");
                    jsonObject.put("errmsg","您不能加入已经加入的团队");
                    return jsonObject;
                }
            }
            teamMapper.insertNewTeammates(teamId,mateId,"成员",nickName);
            jsonObject.put("status","success");
        } catch (NumberFormatException e) {
            jsonObject.put("status","failed");
        }
        return jsonObject;
    }

    @Override
    public JSONObject deleteTeamCart(String openId,long cartId) {
        JSONObject jsonObject = new JSONObject();
        int i = teamMapper.deleteCart(cartId);
        if (i != 1){
            jsonObject.put("status","failed");
        }else {
            jsonObject.put("status","success");
        }
        return jsonObject;
    }

    @Transactional
    @Override
    public JSONObject putNewOrder(String openId, TeamOrders orders) {
        JSONObject jsonObject = new JSONObject();
        long teamId = orders.getTeamId();
        double total = 0;
        long orderId = System.currentTimeMillis()/100;
        if(verify(openId,teamId)){
            for (TeammateOrder mate:
                 orders.getMateOrders()) {
                //获取当前队员Id
                Teammate teammate = teamMapper.queryMateById(mate.getId());
                //获取当前队员所有团队购物车订单
                TeamCart[] teamCarts = teamMapper.queryMateCart(teamId,teammate.getOpenId());
                //开始遍历选中的订单
                for (Long i:mate.getGoods()){
                    for (TeamCart cart:teamCarts) {
                        if (i == cart.getId()){
                            GoodsData goodsData = goodsMapper.queryGoodsById(cart.getGoodsId());
                            System.out.println(goodsData.toString());
                            if (goodsData.getNumber() < cart.getGoodsNumber()){
                                jsonObject.put("status","failed");
                                jsonObject.put("errmsg",goodsData.getName() + "库存不足");
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                return jsonObject;
                            }else {
                                goodsMapper.increaseSaleNumber(goodsData.getId(),cart.getGoodsNumber());
                                double totalPrice = goodsData.getPrice() * cart.getGoodsNumber();
                                userMapper.updateConsumerBalance("farmer-" + goodsData.getOriginId(),Double.parseDouble(String.format("%.2f", (userMapper.queryCount("farmer-" + goodsData.getOriginId()).getBalance() + totalPrice*0.92 ))));
                                total += goodsData.getPrice() * cart.getGoodsNumber();
                                orderMapper.insertNewOrderData(orderId,goodsData.getId(),cart.getGoodsNumber(),goodsData.getPrice(),(double) Math.round(total * 100) /100);
                                teamMapper.bookCart(cart.getId());
                            }
                        }
                    }
                }
                Account account = userMapper.queryCount("team-" + orders.getTeamId());
                if(account.getBalance() < total){
                    jsonObject.put("status","failed");
                    jsonObject.put("msg","balance not enough");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }else {
                    orderMapper.insertNewOrder(orderId,"team-" + orders.getTeamId(),new Date(System.currentTimeMillis()),total,orders.getAddressId());
                    userMapper.updateConsumerBalance("team-" + orders.getTeamId(),Double.valueOf(String.format("%.2f", account.getBalance()-(double) Math.round(total * 100) / 100 )));
                    userMapper.insertNewAccountDetail("team-" + orders.getTeamId(),new Date(System.currentTimeMillis()),"消费",orderId,-1 * total);
                    ShoppingAddress addressId = orderMapper.queryShoppingAddress(orders.getAddressId());
                    userMapper.updateConsumerBalance("extract-" + orders.getAddressId(),Double.valueOf(String.format("%.2f", userMapper.queryCount("extract-" + addressId.getPointId()).getBalance() + total * 0.08 )));
                    jsonObject.put("status","success");
                }
            }
        }else{
            jsonObject.put("status","failed");
            jsonObject.put("errmsg","权限不够");
        }
        return jsonObject;
    }

    @Override
    public JSONObject receiveOrders(String openId, long orderId,long teamId) {
        JSONObject jsonObject = new JSONObject();
        if (verify(openId,teamId)){
            orderMapper.updateOrder(orderId,"status","已完成");
            jsonObject.put("status","success");
        }else {
            jsonObject.put("status","failed");
        }
        return jsonObject;
    }

    @Override
    public JSONObject putNewGoods(String openId, long teamId, long goodsId, int number) {
        Teammate teammate = teamMapper.queryMate(openId, teamId);
        TeamCart cart = teamMapper.queryMateSingleCart(openId, teamId, goodsId);
        if (cart == null) {
            teamMapper.insertCart(openId, teamId, new Date(System.currentTimeMillis()), teammate.getId(), goodsId, number);
        }else {
            teamMapper.updateCart(openId,teamId,goodsId,number + cart.getGoodsNumber());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        return jsonObject;
    }

    @Override
    public JSONObject getTeamRecords(long teamId) {
        Order[] orders = orderMapper.queryOrdersByConsumer("team-" + teamId,"%");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orders",orders);
        return jsonObject;
    }

    @Override
    public JSONObject getTeam(String mateId) {
        Team[] teams = teamMapper.queryMateTeam(mateId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("team",teams);
        return jsonObject;
    }

    @Override
    public JSONObject getTeamDisCount(String teamId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("discount",0.98);
        return jsonObject;
    }

    @Override
    public JSONObject getPerson(String openId,long teamId) {
        Teammate teammate = teamMapper.queryMate(openId, teamId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("person",teammate);
        return jsonObject;
    }

    @Override
    public JSONObject getPersonCart(String openId,long teamId) {
        JSONObject jsonObject = new JSONObject();
        System.out.println(openId);
        jsonObject.put("carts",teamMapper.queryMateCart(teamId, openId));
        return jsonObject;
    }

    @Override
    public JSONObject refuseOrder(String openId, long cartId) {
        return null;
    }

    @Override
    public JSONObject getTeamCode(String openId, long teamId) {
        JSONObject jsonObject = new JSONObject();
        if (verify(openId, teamId)){
            String encrypt = DESEncryptTools.encrypt(String.valueOf(teamId));
            jsonObject.put("code",encrypt);
        }else {
            jsonObject.put("status","权限不够");
        }
        return jsonObject;
    }

    @Override
    public JSONObject getTeamAllCart(String openId,long teamId) {
        JSONObject jsonObject = new JSONObject();
        if(verify(openId, teamId)){
            jsonObject.put("carts",teamMapper.queryCart(teamId));
        }
        return jsonObject;
    }

    @Override
    public JSONObject deleteTeammate(String openId, long teamId, long mateId) {
        JSONObject jsonObject = new JSONObject();
        if(verify(openId, teamId)){
            teamMapper.deleteTeammate(mateId);
            jsonObject.put("status","success");
        }else{
            jsonObject.put("status","failed");
        }
        return jsonObject;
    }

    @Override
    public JSONObject getTeammember(String openId, long teamId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("members",teamMapper.queryTeamMembers(teamId));
        return jsonObject;
    }

    @Override
    public JSONObject updateTeamAccount(long teamId, double number) {
        String teamOpenId = "team-" + teamId;
        Account account = userMapper.queryCount(teamOpenId);
        userMapper.updateConsumerBalance(teamOpenId,account.getBalance() + number);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        return jsonObject;
    }

    @Override
    public JSONObject getTeamAccount(long teamId) {
        JSONObject jsonObject = new JSONObject();
        String teamOpenId = "team-" + teamId;
        jsonObject.put("balance",userMapper.queryCount(teamOpenId).getBalance());
        jsonObject.put("details",userMapper.queryAccountDetail(teamOpenId));
        return jsonObject;
    }

    @Override
    public JSONObject quitTeam(long teamId, String openId) {
        Teammate teammate = teamMapper.queryMate(openId, teamId);
        teamMapper.quitTeam(teamId,teammate.getId());
        return null;
    }

    @Override
    public JSONObject changeNickName(long teamId, long mateId, String nickName) {
        teamMapper.updateTeammate(teamId,mateId,"nickname",nickName);
        return null;
    }

    @Override
    public JSONObject transferLeader(long leaderId, long mateId, long teamId) {
        teamMapper.updateTeammate(teamId,leaderId,"status","成员");
        teamMapper.updateTeammate(teamId,mateId,"status","队长");
        return null;
    }

    @Override
    public JSONObject changeGoods(long teamId, String openId, long id, int number) {
        if(number == 0){
            teamMapper.deleteCart(id);
        }else {
            TeamCart cart = teamMapper.queryCartById(id);
            teamMapper.updateCart(openId,teamId,cart.getGoodsId(),number);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        return jsonObject;
    }

    private boolean verify(String openId,long teamId){
        Teammate teammate = teamMapper.queryMate(openId, teamId);
        return (teammate != null && teammate.getStatus().equals("队长"));
    }
}
