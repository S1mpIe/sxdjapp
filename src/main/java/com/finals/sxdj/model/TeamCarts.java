package com.finals.sxdj.model;

import com.finals.sxdj.model.sqlmodel.TeamCart;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamCarts {
    private int mateId;
    private long teamsId;
    private String nickName;
    private List<TeamCart> carts;
}
