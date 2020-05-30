package com.finals.sxdj.model.sqlmodel;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    private long id;
    private long goodsId;
    private int starts;
    private String content;
    private String sender;
    private Date putTime;
}
