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
    private long sourceId;
    private String openId;
    private long teamId;
    private int goodsId;
    private int goodsNumber;
    private Date putTime;
    private String status;
    private double price;
    private String imageUrl;
    private int stock;
}
