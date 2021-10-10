package com.wyt.gateway.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterSingleton {

    private enum EnumFilterSingleton {
        /**
         * 懒汉枚举单例模式
         */
        INSTANCE;
        private FilterSingleton instance;

        EnumFilterSingleton() {
            instance = new FilterSingleton();

        }

        public FilterSingleton getSingleton() {
            return instance;
        }
    }

    static FilterSingleton getInstance() {
        return EnumFilterSingleton.INSTANCE.getSingleton();
    }

    /**
     * Request过滤操作链
     */
    private List<HttpRequestFilter> requestFilterList = new ArrayList<>();
    /**
     * Response过滤操作链
     */
    private List<HttpResponseFilter> responseFilters = new ArrayList<>();

    void registerRequestFrontFilter(HttpRequestFilter requestFrontFilter) {
        this.requestFilterList.add(requestFrontFilter);
    }

    void registerResponseBackendFilter(HttpResponseFilter responseBackendFilter) {
        this.responseFilters.add(responseBackendFilter);
    }

    List<HttpRequestFilter> getRequestFilterList() {
        return requestFilterList;
    }

    List<HttpResponseFilter> getResponseFilters() {
        return responseFilters;
    }

}
