package com.wyt.gateway.router;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinHttpEndpointRouter implements HttpEndpointRouter {

    private static AtomicInteger pos = new AtomicInteger(0);

    @Override
    public String route(List<String> endpoints) {
        if (pos.get() >= endpoints.size()) {
            pos = new AtomicInteger(0);
        }
        return endpoints.get(pos.getAndIncrement());
    }


}
