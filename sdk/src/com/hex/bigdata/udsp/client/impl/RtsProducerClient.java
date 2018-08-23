package com.hex.bigdata.udsp.client.impl;

import com.hex.bigdata.udsp.client.ConsumerClient;
import com.hex.bigdata.udsp.constant.ConsumerEntity;
import com.hex.bigdata.udsp.constant.ConsumerType;
import com.hex.bigdata.udsp.model.request.RtsProducerRequest;
import com.hex.bigdata.udsp.model.request.UdspRequest;
import com.hex.bigdata.udsp.model.response.origin.SyncResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import com.hex.bigdata.udsp.util.SdkHttpUtil;

import java.util.List;
import java.util.Map;

/**
 * 实时流-生产者客户端
 */
public class RtsProducerClient extends ConsumerClient {

    private RtsProducerClient() {
    }

    private RtsProducerClient(String requestUrl) {
        super(requestUrl);
    }

    /**
     * 实时流生产者-同步start
     *
     * @param request
     * @return
     */
    public SyncPackResponse syncStart(RtsProducerRequest request) {
        this.checkBasicParams(request, ConsumerType.SYNC, ConsumerEntity.START);
        this.checkStartBusinessParams(request);
        SyncResponse response = SdkHttpUtil.requestUdsp(request, this.getRequestUrl(), SyncResponse.class);
        return this.transSyncPackResponse(response);
    }

    @Override
    protected void checkStartBusinessParams(UdspRequest udspRequest) {
        RtsProducerRequest request = (RtsProducerRequest) udspRequest;
        List<Map<String, String>> dataStream = request.getDataStream();
        if (dataStream == null || dataStream.size() == 0) {
            throw new IllegalArgumentException("参数dataStream不能为空!");
        }
    }
}
