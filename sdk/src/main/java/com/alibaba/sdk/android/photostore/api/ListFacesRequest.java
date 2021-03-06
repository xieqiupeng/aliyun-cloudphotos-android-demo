/**
 * Copyright (C) 2017 Alibaba Group Holding Limited
 */
package com.alibaba.sdk.android.photostore.api;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;

public class ListFacesRequest extends NewPagedRequest {
    private boolean hasName;

    public ListFacesRequest(int sizePerPage, String cursor, String direction, String state, boolean hasName) {
        this.size = sizePerPage;
        this.cursor = cursor;
        this.state = state;
        this.direction = direction;
        this.hasName = hasName;
    }

    @Override
    public RpcAcsRequest build() {
        com.aliyuncs.cloudphoto.model.v20170711.ListFacesRequest request = new com.aliyuncs.cloudphoto.model.v20170711.ListFacesRequest();
        request.setProtocol(ProtocolType.HTTPS); //指定访问协议
        request.setAcceptFormat(FormatType.JSON); //指定api返回格式
        request.setMethod(MethodType.POST); //指定请求方法
        request.setRegionId(REGION_CN);//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。
        request.setDirection(direction);
        request.setSize(size);
        request.setCursor(cursor);
        request.setState(state);
        request.setHasFaceName(String.valueOf(hasName));
        if (!stsToken.isEmpty())
            request.setSecurityToken(stsToken);
        else if (!libraryId.isEmpty())
            request.setLibraryId(libraryId);
        request.setStoreName(storeName);
        request.setActionName("ListFaces");
        return request;
    }

    @Override
    public ListFacesResponse parse(String result) {
        return JSON.parseObject(result, ListFacesResponse.class);
    }
}
