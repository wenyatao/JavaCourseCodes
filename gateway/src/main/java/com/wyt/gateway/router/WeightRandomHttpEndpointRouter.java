package com.wyt.gateway.router;

import java.util.*;

public class WeightRandomHttpEndpointRouter implements HttpEndpointRouter {
    public static Map<String, Integer> weightMap = new HashMap<>();

    static {
        weightMap.put("http://localhost:8801", 1);
        weightMap.put("http://localhost:8802", 2);
        weightMap.put("http://localhost:8803", 3);
    }

    @Override
    public String route(List<String> endpoints) {
        List<String> keyList = new LinkedList<>();
        endpoints.stream().forEach(s -> {
            int weight = weightMap.get(s);
            for (int i = 0; i < weight; i++) {
                keyList.add(s);
            }
        });
        System.out.println(keyList.toString());
        Random random = new Random(System.currentTimeMillis());
        int pos = random.nextInt(keyList.size());
        return keyList.get(pos);
    }
}
