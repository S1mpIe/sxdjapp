package com.finals.sxdj.model.sqlmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {
    private int orderId;
    private int goodsId;
    private int goodsNumber;
    private double goodsPrice;
    private double goodsTotalPrice;
}
