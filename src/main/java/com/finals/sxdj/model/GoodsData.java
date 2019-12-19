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
    private String id;
    private String imageUrl;
    private String number;
    private String numberUnit;
    private String origin;
    private String category;
    private String price;
    private String priceUnit;
}
