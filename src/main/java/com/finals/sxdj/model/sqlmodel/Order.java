package com.finals.sxdj.model.sqlmodel;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;
    private String consumerId;
    private Date resultTime;
    private Date putTime;
    private Date overTime;
    private String status;
    private double pay;
}
