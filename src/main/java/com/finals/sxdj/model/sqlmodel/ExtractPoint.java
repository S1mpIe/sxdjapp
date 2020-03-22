package com.finals.sxdj.model.sqlmodel;

import lombok.Getter;
import lombok.Setter;

/**
 * @author S1mpIe
 */
@Setter
@Getter
public class ExtractPoint {
    private int id;
    private String owner;
    private String name;
    private String idNumber;
    private String occupation;
    private double latitude;
    private double longitude;
    private String phoneNumber;
    private String address;
    private int area;
    private String advantage;
}
