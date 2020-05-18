package com.finals.sxdj.model.sqlmodel;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teammate {
    private int teamId;
    private int id;
    private String openId;
    private String nickName;
    private String status;
}
