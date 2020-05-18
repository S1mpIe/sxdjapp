package com.finals.sxdj.model.sqlmodel;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingAddress {
    private int id;
    private String name;
    private String phoneNumber;
    private int pointId;
    private ExtractPoint point;
}
