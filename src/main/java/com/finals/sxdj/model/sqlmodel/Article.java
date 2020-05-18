package com.finals.sxdj.model.sqlmodel;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {
    private int id;
    private String title;
    private String mdUrl;
    private String imageUrl;
    private String author;
    private Date birthTime;
}
