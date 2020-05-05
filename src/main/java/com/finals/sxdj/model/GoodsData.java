package com.finals.sxdj.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author S1mpIe
 */
@Setter
@Getter
@ToString
public class GoodsData {
    private int id;
    private String name;
    private String imageUrl;
    private int number;
    private String numberUnit;
    private int soldNumber;
    private String origin;
    private int originId;
    private String category;
    private double price;
    private String priceUnit;
    private int selectNumber;
}
