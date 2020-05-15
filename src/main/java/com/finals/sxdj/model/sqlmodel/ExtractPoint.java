package com.finals.sxdj.model.sqlmodel;

import lombok.*;

/**
 * @author S1mpIe
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExtractPoint {
    private int id;
    private String owner;
    private String name;
    private String idNumber;
    private double latitude;
    private double longitude;
    private String phoneNumber;
    private String address;
    private int area;
}
