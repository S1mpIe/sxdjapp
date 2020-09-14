package com.finals.sxdj.model.sqlmodel;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teammate {
    private long teamId;
    private long id;
    private String openId;
    private String nickName;
    private String status;
}
