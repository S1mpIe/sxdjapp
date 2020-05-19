package com.finals.sxdj.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.finals.sxdj.model.GoodsData;
import com.finals.sxdj.model.Resources;
import com.finals.sxdj.model.sqlmodel.Farmers;
import com.finals.sxdj.repository.FarmerMapper;
import com.finals.sxdj.repository.GoodsMapper;
import com.finals.sxdj.services.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class FarmerServiceImpl implements FarmerService {
    @Autowired
    private FarmerMapper farmerMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public JSONObject applyFarmer(String openId, Farmers farmers) {
        int i = farmerMapper.insertVerifiedFarmer(openId, farmers);
        JSONObject jsonObject = new JSONObject();
        if(i == 1){
            jsonObject.put("status","success");
        }else {
            jsonObject.put("status","failed");
        }
        return jsonObject;
    }


    @Override
    public JSONObject applyGoods(GoodsData goods, int farmerId) {
        int number = farmerMapper.insertNewGoods(goods, farmerId);
        JSONObject jsonObject = new JSONObject();
        if(number == 1){
            jsonObject.put("status","success");
        }else {
            jsonObject.put("status","failed");
        }
        return jsonObject;
    }

    @Override
    public JSONObject queryAllSold(int farmerId) {
        GoodsData[] goodsData = farmerMapper.queryAllSold(farmerId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goods",goodsData);
        return jsonObject;
    }

    @Override
    public JSONObject queryFarmer(String openId) {
        Farmers farmers = farmerMapper.queryFarmer(openId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","failed");
        if(farmers != null){
            if (farmers.getStatus().equals("待审核")){
                jsonObject.put("msg","未审核");
            }else {
                jsonObject.put("status", "success");
                jsonObject.put("farmer",farmers);
            }
        }else {
            jsonObject.put("msg","尚未注册");
        }
        return jsonObject;
    }

    @Override
    public JSONObject queryFarmerById(int farmerId) {
        Farmers farmers = farmerMapper.queryFarmerById(farmerId);
        farmers.setOpenId(null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("farmer",farmers);
        return jsonObject;
    }

    @Override
    public JSONObject updateGoods(int farmerId, GoodsData goodsData) {
        farmerMapper.updateGoods(goodsData);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status","success");
        return jsonObject;
    }

    @Override
    public JSONObject deleteGoods(int goodsId, String cate) {
        JSONObject jsonObject = new JSONObject();
        if(cate.equals("delete")){
            goodsMapper.deleteGoods(goodsId);
        }else if(cate.equals("off")){
            goodsMapper.offGoods(goodsId);
        }
        jsonObject.put("status","success");
        return jsonObject;
    }

    @Override
    public JSONObject uploadImage(MultipartFile files) {
        JSONObject jsonObject = new JSONObject();
        long id = System.currentTimeMillis();
        String path = "../../../../../static/image" + id + ".jpg";
        File saveFile = new File(path);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        // 文件保存路径
        System.out.println(saveFile.getParentFile().toString());
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
            out.write(files.getBytes());
            out.flush();
            out.close();
            jsonObject.put("status","success");
            jsonObject.put("url","https://www.s1mpie.top:453/sxdj/image/"+id+".jpg");
            jsonObject.put("id",id);
            farmerMapper.insertPathData(id,"https://www.s1mpie.top:453/sxdj/image/"+id+".jpg");
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("status","failed");
        }
        return jsonObject;
    }

    @Override
    public JSONObject deleteImage(long pathId) {
        farmerMapper.deleteImage(pathId);
        return null;
    }

    @Override
    public JSONObject applyResources(String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        for(long id:((List<Long>) jsonObject.get("ids"))){
            farmerMapper.insertResource(jsonObject.getLong("owner"),jsonObject.getString("cate"),id);
        }
        return null;
    }

    @Override
    public JSONObject queryResources(String cate, long id) {
        Resources[] resource = farmerMapper.getResource(cate, id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resources",resource);
        return jsonObject;
    }

    @Override
    public JSONObject getFarmerOrders(int farmerId) {
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("orders",farmerMapper.queryFarmerOrder(farmerId));
        return jsonObject;
    }
}
