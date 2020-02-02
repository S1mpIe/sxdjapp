package com.finals.sxdj.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Getter
@ToString
public class RequestMap {
    private Map<String,String> map;
}
