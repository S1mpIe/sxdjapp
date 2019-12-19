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
public class Goods {
    private String id;
    private String name;
    private String originId;
    private String imageAddress;
    private String category;
    private boolean ifDelete;

}
