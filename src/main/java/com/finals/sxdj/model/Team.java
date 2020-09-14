package com.finals.sxdj.model;

import com.finals.sxdj.model.sqlmodel.Teammate;
import com.finals.sxdj.model.sqlmodel.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private long id;
    private String name;
    private List<Teammate> mates;
}
