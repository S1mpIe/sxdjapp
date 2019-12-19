package com.finals.sxdj.model.sqlmodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author S1mpIe
 */
@Setter
@Getter
@ToString
public class GoodsNumber {
    private String goodsId;
    private String number;
    private String unit;
    private String sale_number;
}
