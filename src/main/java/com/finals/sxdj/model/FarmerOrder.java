package com.finals.sxdj.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class FarmerOrder {
    private long orderId;
    private Date putTime;
    private long goodsId;
    private int goodsNumber;
    private double goodsPrice;
    private String numberUnit;
    private String imageUrl;
}
