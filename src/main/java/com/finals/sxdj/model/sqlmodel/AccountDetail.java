package com.finals.sxdj.model.sqlmodel;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDetail {
    private String consumerId;
    private String change;
    private String cause;
    private long source;
    private Date time;
}
