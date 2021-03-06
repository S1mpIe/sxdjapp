package com.finals.sxdj.model.sqlmodel;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private String consumerId;
    private Date resultTime;
    private Date putTime;
    private Date overTime;
    private String status;
    private List<OrderData> goods;
    private double pay;
    private String receiver;
    private long extractPoint;
    private String extractAddress;
}
