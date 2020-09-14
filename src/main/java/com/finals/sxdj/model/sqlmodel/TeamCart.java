package com.finals.sxdj.model.sqlmodel;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeamCart {
    private long id;
    private String goodsName;
    private long sourceId;
    private String openId;
    private long teamId;
    private long goodsId;
    private int goodsNumber;
    private Date putTime;
    private String status;
    private double price;
    private String imageUrl;
    private int stock;
}
