package com.finals.sxdj.repository;

import com.finals.sxdj.model.Navigation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NavigationMapper {
    List<Navigation> queryAllNavigation();
}
