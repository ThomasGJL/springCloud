package com.sl.learn.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuliangzhao
 * @Title: ConfigClientApplication
 * @ProjectName spring-cloud-learn
 * @Description: TODO
 * @date 2019/12/8 16:58
 */
@SpringBootApplication
@RestController
@RefreshScope
@EnableEurekaClient
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerBusApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConfigServerBusApplication.class,args);
    }
    
}
