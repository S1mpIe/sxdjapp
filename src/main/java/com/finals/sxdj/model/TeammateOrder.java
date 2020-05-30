package com.finals.sxdj.model;

import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeammateOrder {
    private Long id;
    private List<Long> goods;
}
