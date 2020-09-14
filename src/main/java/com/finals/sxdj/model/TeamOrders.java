package com.finals.sxdj.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamOrders {
    private long teamId;
    private long addressId;
    private List<TeammateOrder> mateOrders;
}
