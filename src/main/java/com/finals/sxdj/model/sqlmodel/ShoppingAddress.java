package com.finals.sxdj.model.sqlmodel;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingAddress {
    private long id;
    private String name;
    private String phoneNumber;
    private long pointId;
    private ExtractPoint point;
}
