package com.finals.sxdj.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Resources {
    private int id;
    private String cate;
    private long owner;
    private long pathId;
    private String url;
}
