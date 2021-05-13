package com.net.nxx.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-21
 */
@Configuration
@MapperScan("com.net.nxx.common.exception.mbg.mapper")
public class MyBatisConfig {
}
