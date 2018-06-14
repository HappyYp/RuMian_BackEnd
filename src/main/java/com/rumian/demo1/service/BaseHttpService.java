package com.rumian.demo1.service;

import com.rumian.demo1.base.ResponseCode;
import com.rumian.demo1.base.ResponseResult;
import com.alibaba.fastjson.JSONObject;
import com.rumian.demo1.exception.OperationException;
import com.rumian.demo1.model.User;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public abstract class BaseHttpService {
    /**
     * 获得service对应域名地址
     * @return
     */
    public abstract String getDomain();

    public ResponseResult get(String url, Map<String, Object> param) throws OperationException{
        String newUrl = url;
        if (param != null){
            StringBuffer sb = new StringBuffer();
            for (Map.Entry entry : param.entrySet()){
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
            if (sb.length() > 0){
                sb.deleteCharAt(sb.lastIndexOf("&"));
                newUrl = newUrl + "?" + sb;
            }
        }
        newUrl = getDomain() + newUrl;
        Request request = new Request.Builder()
                .url(newUrl)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        Response response = null;
        ResponseResult responseResult = new ResponseResult();
        try {
            response = call.execute();
            responseResult = JSONObject.parseObject(response.body().string(), ResponseResult.class);
        } catch (IOException e) {
            throw new OperationException(ResponseCode.SYSTEM_ERROR, e.getMessage());
        }
        return responseResult;
    }

    public ResponseResult post(String url, Map<String, Object> param) throws OperationException{
        FormBody.Builder builder = new FormBody.Builder();
        if (param != null){
            for (Map.Entry entry : param.entrySet()){
                if (entry.getKey() != null && entry.getValue() != null){
                    builder.add(String.valueOf(entry.getKey()), String.valueOf(entry.getValue().toString()));
                }
            }
        }
        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .url(this.getDomain() + url)
                .post(body)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//               System.out.println("error");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println(response.body().string());
//            }
//        });
        Response response = null;
        ResponseResult responseResult = new ResponseResult();
        try {
            response = call.execute();
            System.out.println(response.body().string());
            responseResult = JSONObject.parseObject(response.body().string(),ResponseResult.class);
            System.out.println(responseResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseResult;
    }
}
