package com.finals.sxdj.utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author S1mpIe
 */
public class JwtUtil {
    private static final String KEY_STR = "2019_sxdj_final";

    public static String getToken(String openId){//生成token
        try{
            Algorithm algorithm =  Algorithm.HMAC256(KEY_STR);
            Map<String,Object> header = new HashMap<>(2);
            header.put("typ","JWT");
            header.put("alg","HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("status",openId)
                    .sign(algorithm);
        }catch (Exception e){
            return null;
        }
    }
    public static boolean verify(String token){//解密token
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY_STR);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return  true;
        }catch (Exception e){
            return false;
        }
    }
    public static JSONObject getPayLoad(String token){//提取PayLoad
        Algorithm algorithm = Algorithm.HMAC256(KEY_STR);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verify = verifier.verify(token);
        String payload = verify.getPayload();
        Base64.Decoder decoder = Base64.getDecoder();
        String payloadStr = new String(decoder.decode(payload), StandardCharsets.UTF_8);
        return JSONObject.parseObject(payloadStr);
    }
}
