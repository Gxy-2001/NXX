# Nxx - 二手交易平台

### 项目介绍

#### 使用技术

| 主要后端技术            | 说明                    |
| ------------------- |  ----------------------- |
| SpringBoot          |  容器+MVC框架            |
| MyBatis             |  ORM框架                 |
| MySQL               |  关系型数据库            |
| Elasticsearch       |  搜索引擎                |
| LogStash-input-jdbc |  数据库和ES间增量同步    |
| Swagger-UI          |  后端调试，用Knife4j皮肤 |

#### 版本




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



