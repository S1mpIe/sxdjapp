package com.finals.sxdj.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author S1mpIe
 *
 */
@Component
public class HttpUtil {
    @Value("${url.token}")
    private String tokenUrl;
    @Value("${appId}")
    private String appId;
    @Value("${secret}")
    private String secret;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送post请求
     * @param headers
     * @param attributes
     * @param url
     * @return
     */
    public static JSONObject sendPostRequest(HashMap<String,String> headers, HashMap<String,String> attributes,String url){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String uri = url;
        if (attributes != null && !attributes.isEmpty()) {
            StringBuilder params = new StringBuilder();
            try{
                Set<Map.Entry<String, String>> entries = attributes.entrySet();
                Iterator<Map.Entry<String, String>> iterator = entries.iterator();
                while (iterator.hasNext()){
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    params.append(URLEncoder.encode(key,"utf-8"));
                    params.append("=");
                    params.append(URLEncoder.encode(value,"utf-8"));
                    if(iterator.hasNext()) {
                        params.append("&");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            uri = url + "?" + params.toString();
        }
        HttpPost httpPost = new HttpPost(uri);
        if (headers != null && !headers.isEmpty()) {
            Set<Map.Entry<String, String>> entries = headers.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = null;
                String value = null;
                try {
                    key = URLEncoder.encode(next.getKey(),"utf-8");
                    value = URLEncoder.encode(next.getValue(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (key != null && value != null){
                    httpPost.setHeader(key,value);
                }
            }
        }
        CloseableHttpResponse response = null;
        String responseStr = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if(responseEntity != null){
                responseStr = EntityUtils.toString(responseEntity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        return jsonObject;
    }

    /**
     * 发送get请求
     * @param headers
     * @param attributes
     * @param url
     * @return
     */
    public static JSONObject sendGetRequest(HashMap<String,String> headers,HashMap<String,String> attributes,String url){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String uri = url;
        if (attributes != null  && !attributes.isEmpty()) {
            StringBuffer params = new StringBuffer();
            try{
                Set<Map.Entry<String, String>> entries = attributes.entrySet();
                Iterator<Map.Entry<String, String>> iterator = entries.iterator();
                while (iterator.hasNext()){
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    params.append(URLEncoder.encode(key,"utf-8"));
                    params.append("=");
                    params.append(URLEncoder.encode(value,"utf-8"));
                    if(iterator.hasNext()) {
                        params.append("&");
                    }
                }
                uri = url + "?" + params.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        HttpGet httpGet = new HttpGet(uri);
        if (headers != null && !headers.isEmpty()) {
            Set<Map.Entry<String, String>> entries = headers.entrySet();
            for (Map.Entry<String, String> next : entries) {
                String key = null;
                String value = null;
                try {
                    key = URLEncoder.encode(next.getKey(), "utf-8");
                    value = URLEncoder.encode(next.getValue(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (key != null && value != null) {
                    httpGet.setHeader(key, value);
                }
            }
        }
        CloseableHttpResponse response = null;
        JSONObject responseJson = new JSONObject();
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000)
                    .setRedirectsEnabled(true).build();
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            responseJson = JSONObject.parseObject(EntityUtils.toString(responseEntity));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseJson;
    }

    /**
     * 获取最新的access_token
     * @return
     */
    public String getLastInterfaceToken(){
        Map accessToken = redisTemplate.opsForHash().entries("interfaceToken");
        String accessTokenStr = null;
        if(accessToken == null
                || accessToken.get("tokenValue") == null
                || accessToken.get("limitTime") == null
                || System.currentTimeMillis() - ((Date)accessToken.get("limitTime")).getTime()>= 7200000){
            HashMap<String,String> attributes = new HashMap<>();
            attributes.put("grant_type","client_credential");
            attributes.put("appid",appId);
            attributes.put("secret",secret);
            JSONObject jsonObject = sendGetRequest(null, attributes, tokenUrl);
            accessTokenStr = (String) jsonObject.get("access_token");
            Date date = new Date(System.currentTimeMillis());
            redisTemplate.opsForHash().put("interfaceToken","tokenValue",accessTokenStr);
            redisTemplate.opsForHash().put("interfaceToken","limitTime",date);
        }else {
            accessTokenStr = (String) redisTemplate.opsForHash().get("interfaceToken","tokenValue");
        }
        return accessTokenStr;
    }
}
