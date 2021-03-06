package com.hex.bigdata.udsp.client.impl;

import com.hex.bigdata.udsp.client.ConsumerClient;
import com.hex.bigdata.udsp.common.constant.ConsumerEntity;
import com.hex.bigdata.udsp.common.constant.ConsumerType;
import com.hex.bigdata.udsp.common.api.model.BaseRequest;
import com.hex.bigdata.udsp.model.request.SqlRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.response.AsyncResponse;
import com.hex.bigdata.udsp.model.response.StatusResponse;
import com.hex.bigdata.udsp.model.response.SyncResponse;
import com.hex.bigdata.udsp.model.response.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.SyncPackResponse;
import com.hex.bigdata.udsp.util.SdkHttpUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * SQL客户端（联机查询客户端）
 */
public class SqlClient extends ConsumerClient {

    private SqlClient() {
    }

    private SqlClient(String requestUrl) {
        super(requestUrl);
    }

    /**
     * 联机查询-同步start
     *
     * @param request
     * @return
     */
    public SyncPackResponse syncStart(SqlRequest request) {
        //检查基础参数，参数错误则抛出异常
        this.checkBasicParams(request, ConsumerType.SYNC, ConsumerEntity.START);
        //业务参数检查
        this.checkStartBusinessParams(request);
        SyncResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(), SyncResponse.class);
        return this.transSyncPackResponse(response);
    }

    /**
     * 联机查询-异步start
     *
     * @param request
     * @return
     */
    public AsyncPackResponse asyncStart(SqlRequest request) {
        //检查基础参数，参数错误则抛出异常
        this.checkBasicParams(request, ConsumerType.ASYNC, ConsumerEntity.START);
        //业务参数检查
        this.checkStartBusinessParams(request);
        AsyncResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(), AsyncResponse.class);
        return this.transAsyncPackResponse(response);
    }

    /**
     * 联机查询应用客户端-异步status
     *
     * @param request
     * @return
     */
    public StatusPackResponse asyncStatus(StatusRequest request) {
        //检查基础参数，参数错误则抛出异常
        this.checkBasicParams(request, ConsumerType.ASYNC, ConsumerEntity.STATUS);
        this.checkStatusBusinessParams(request.getConsumeId());
        StatusResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(), StatusResponse.class);
        return this.transStatusResponse(response);
    }


    @Override
    protected void checkStartBusinessParams(BaseRequest baseRequest) {
        SqlRequest olqRequest = (SqlRequest) baseRequest;
        if (StringUtils.isBlank(olqRequest.getSql())) {
            throw new IllegalArgumentException("查询业务参数sql不能为空!");
        }
    }
}
