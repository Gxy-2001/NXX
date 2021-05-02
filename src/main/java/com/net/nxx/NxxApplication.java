package com.net.nxx;


import org.mybatis.spring.annotation.MapperScan;
// import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.net.nxx.dao")
@SpringBootApplication
public class NxxApplication {

    public static void main(String[] args) {
        SpringApplication.run(NxxApplication.class, args);
//        SpringApplication app = new SpringApplication(NxxApplication.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run(args);
    }

}
