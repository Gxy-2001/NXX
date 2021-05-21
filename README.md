# Nxx - 二手交易平台

### 项目介绍

#### 技术选型

| 后端技术            | 版本号 | 说明                    |
| ------------------- | ------ | ----------------------- |
| SpringBoot          | 2.4.5  | 容器+MVC框架            |
| MyBatis             | 2.1.4  | ORM框架                 |
| MySQL               | 8.0.20 | 关系型数据库            |
| Elasticsearch       | 7.9.3  | 搜索引擎                |
| LogStash-input-jdbc | 7.9.3  | 数据库和ES间增量同步    |
| Druid               | 1.1.17 | 数据库连接池            |
| Lombok              |        | 简化对象封装工具        |
| Swagger-UI          | 2.9.2  | 后端调试，用Knife4j皮肤 |



### 运行方法

java 1.8

springboot 2.4.5

mysql8以上，sql文件在`./sql`目录下

配置文件：需要修改数据库密码

Elasticsearch7.9.3，开启并建立索引，名为`item`

logstash-7.9.3，安装logstash-input-jdbc（做es和mysql的增量同步）

logstash配置文件在`./ES`下，再看那里的readme

maven：import

前端：单独运行

调试界面：[Swagger](http://localhost:8090/swagger-ui.html ) 或者  [有皮肤的Swagger](http://localhost:8090/doc.html )



