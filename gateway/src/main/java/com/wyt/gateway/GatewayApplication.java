package com.wyt.gateway;

import com.wyt.gateway.inbound.HttpInboundServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class GatewayApplication implements ApplicationRunner {
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";


    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String proxyPort = System.getProperty("proxyPort", "8888");
        String proxyServers = System.getProperty("proxyServers", "http://localhost:8801,http://localhost:8802,http://localhost:8803");
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServers.split(",")));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
