package com.github.zuihou;

import com.github.zuihou.security.annotation.EnableLoginArgResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 认证服务 启动类
 *
 * @author zuihou
 * @createTime 2020年03月23日16:24:37
 */
@SpringBootApplication
@EnableDiscoveryClient    //开启服务注册发现功能
@EnableFeignClients(value = {
        "com.github.zuihou",
})
@Slf4j         //省去了每次都写
                // private  final Logger logger = LoggerFactory.getLogger(当前类名.class);的麻烦
                // 在类上加上此注解可在方法中直接使用log关键词，前提需要Lombok插件
@EnableLoginArgResolver
public class OauthApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(OauthApplication.class, args);
        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "Swagger文档: \t\thttp://{}:{}/doc.html\n\t" +
                        "数据库监控: \t\thttp://{}:{}/druid\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                "127.0.0.1",
                env.getProperty("server.port"));
    }
}
