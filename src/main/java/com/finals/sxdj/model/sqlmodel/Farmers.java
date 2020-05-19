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
public class Farmers {
    private String id;
    private String openId;
    private String name;
    private String phone;
    private String introduce;
    private String imageAddress;
    private String cate;
    private String status;
}
