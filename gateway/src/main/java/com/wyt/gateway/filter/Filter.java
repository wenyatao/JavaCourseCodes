package com.wyt.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j
public class Filter implements ApplicationRunner {

    private static final FilterSingleton FILTER_SINGLETON = FilterSingleton.getInstance();


    static private void addRequestFilter(HttpRequestFilter httpRequestFilter) {
        FILTER_SINGLETON.registerRequestFrontFilter(httpRequestFilter);
    }

    static private void addResponseFilter(HttpResponseFilter httpResponseFilter) {
        FILTER_SINGLETON.registerResponseBackendFilter(httpResponseFilter);
    }

    /**
     * 添加Request的过滤操作类,在启动函数中进行调用
     */
    static void initRequestFilter() {
        addRequestFilter(new HeaderHttpRequestFilter());
    }

    /**
     * 添加Response的过滤操作类，在启动函数中进行调用
     */
    static void initResponseFilter() {
        addResponseFilter(new HeaderHttpResponseFilter());
    }

    /**
     * 遍历Request过滤操作链，对Request进行处理，在Server inbound接收到Request后进行调用
     * @param request request
     */
    static public void requestProcess(FullHttpRequest request, ChannelHandlerContext ctx) {
        for (HttpRequestFilter filter: FILTER_SINGLETON.getRequestFilterList()) {
            filter.filter(request,ctx);
        }
    }

    /**
     * 调用Response过滤操作链，对Response进行处理，在Server outbound发送Response前进行调用
     * @param response response
     */
    static public void responseProcess(FullHttpResponse response) {
        for (HttpResponseFilter filter: FILTER_SINGLETON.getResponseFilters()) {
            filter.filter(response);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Filter.initRequestFilter();
        Filter.initResponseFilter();
    }
}
