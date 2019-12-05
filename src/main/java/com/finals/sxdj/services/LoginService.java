package com.finals.sxdj.services;

import com.alibaba.fastjson.JSONObject;

/**
 * @author S1mpIe
 */
public interface LoginService {
    JSONObject login(String code);
}
