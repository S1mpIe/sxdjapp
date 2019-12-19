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
public class User {
    private String openId;
    private String name;
    private String address;
    private String image_address;
    private String phoneNumber;
    private String sex;

}
