-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: nxx
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Current Database: `nxx`
--

CREATE DATABASE /*!32312 IF NOT EXISTS */ `nxx` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `nxx`;

--
-- Table structure for table `nxx_address`
--

DROP TABLE IF EXISTS `nxx_address`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nxx_address`
(
    `id`              bigint      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `consignee_name`  varchar(32) NOT NULL COMMENT '收货人姓名',
    `consignee_phone` varchar(16) NOT NULL COMMENT '收货人手机号',
    `province_name`   varchar(32) NOT NULL COMMENT '省',
    `city_name`       varchar(32) NOT NULL COMMENT '市',
    `region_name`     varchar(32) NOT NULL COMMENT '区',
    `detail_address`  varchar(64) NOT NULL COMMENT '详细地址',
    `default_flag`    tinyint     NOT NULL COMMENT '是否默认地址',
    `user_id`         bigint      NOT NULL COMMENT '用户主键id',
    PRIMARY KEY (`id`),
    KEY `user_id_index` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 25
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nxx_address`
--

LOCK TABLES `nxx_address` WRITE;
/*!40000 ALTER TABLE `nxx_address`
    DISABLE KEYS */;
INSERT INTO `nxx_address`
VALUES (20, 'Gxy', '123456789', '江苏省', '南京市', '栖霞区', '仙林大街163号', 1, 1),
       (24, 'Gxy', '12345678890', '北京市', '市辖区', '东城区', '江苏', 1, 2);
/*!40000 ALTER TABLE `nxx_address`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nxx_admin`
--

DROP TABLE IF EXISTS `nxx_admin`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nxx_admin`
(
    `id`             bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `account_number` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员账号',
    `admin_password` varchar(16)                                                  NOT NULL COMMENT '密码',
    `admin_name`     varchar(8)                                                   NOT NULL COMMENT '管理员名字',
    PRIMARY KEY (`id`),
    UNIQUE KEY `account_number` (`account_number`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nxx_admin`
--

LOCK TABLES `nxx_admin` WRITE;
/*!40000 ALTER TABLE `nxx_admin`
    DISABLE KEYS */;
INSERT INTO `nxx_admin`
VALUES (1, '111111', '123456', '管理员');
/*!40000 ALTER TABLE `nxx_admin`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nxx_carousel`
--

DROP TABLE IF EXISTS `nxx_carousel`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nxx_carousel`
(
    `carousel_id`   int                                                     NOT NULL AUTO_INCREMENT COMMENT '首页轮播图主键id',
    `carousel_url`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '轮播图',
    `redirect_url`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '''##''' COMMENT '点击后的跳转地址(默认不跳转)',
    `carousel_rank` int                                                              DEFAULT '0' COMMENT '排序值(字段越大越靠前)',
    `is_deleted`    tinyint                                                          DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
    `create_time`   datetime                                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_user`   int                                                              DEFAULT '0' COMMENT '创建者id',
    `update_time`   datetime                                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `update_user`   int                                                              DEFAULT '0' COMMENT '修改者id',
    PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nxx_carousel`
--

LOCK TABLES `nxx_carousel` WRITE;
/*!40000 ALTER TABLE `nxx_carousel`
    DISABLE KEYS */;
INSERT INTO `nxx_carousel`
VALUES (0,
        'https://img-host-service.oss-cn-shanghai.aliyuncs.com/nxx/1.png?versionId=CAEQIRiBgICRseeAzBciIGZiNmMwNjNmYWQ3NTQ5ZmM5MDBiZmMxYTU3YzMxY2Rj',
        'https://img-host-service.oss-cn-shanghai.aliyuncs.com/nxx/1.png?versionId=CAEQIRiBgICRseeAzBciIGZiNmMwNjNmYWQ3NTQ5ZmM5MDBiZmMxYTU3YzMxY2Rj',
        0, 0, '2021-05-19 00:00:00', 0, '2021-05-19 00:00:00', 0),
       (1,
        'https://img-host-service.oss-cn-shanghai.aliyuncs.com/nxx/2.png?versionId=CAEQIRiBgMCIseeAzBciIDMyNDdjZDllNmU2ZDRkMDc5NjliZDQ2NmZlNjViYmUx',
        'https://juejin.im/book/5da2f9d4f265da5b81794d48/section/5da2f9d6f265da5b794f2189',
        0, 0, '2021-05-19 00:00:00', 0, '2021-05-19 00:00:00', 0),
       (2,
        'https://img-host-service.oss-cn-shanghai.aliyuncs.com/nxx/3.png?versionId=CAEQIRiBgMCRseeAzBciIGM4YjNjNzhmMThhZDQwMjU5NzUwY2Y4YWI0ODU4NjEz',
        'https://juejin.im/book/5da2f9d4f265da5b81794d48/section/5da2f9d6f265da5b794f2189',
        0, 0, '2021-05-19 00:00:00', 0, '2021-05-19 00:00:00', 0),
       (3,
        'https://img-host-service.oss-cn-shanghai.aliyuncs.com/nxx/4.png?versionId=CAEQIRiBgICJseeAzBciIDc5YTM5YThlZGViODRiMmY4NWM2OGQ4Mzc5ZDk0OGQz',
        'https://juejin.im/book/5da2f9d4f265da5b81794d48/section/5da2f9d6f265da5b794f2189',
        0, 0, '2021-05-19 00:00:00', 0, '2021-05-19 00:00:00', 0)
;
/*!40000 ALTER TABLE `nxx_carousel`
    ENABLE KEYS */;
UNLOCK TABLES;
ALTER TABLE `nxx_carousel`
    AUTO_INCREMENT = 4;

--
-- Table structure for table `nxx_favorite`
--

DROP TABLE IF EXISTS `nxx_favorite`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nxx_favorite`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
    `create_time` datetime NOT NULL COMMENT '加入收藏的时间',
    `user_id`     bigint   NOT NULL COMMENT '用户主键id',
    `idle_id`     bigint   NOT NULL COMMENT '闲置物主键id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id` (`user_id`, `idle_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 41
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nxx_favorite`
--

LOCK TABLES `nxx_favorite` WRITE;
/*!40000 ALTER TABLE `nxx_favorite`
    DISABLE KEYS */;
INSERT INTO `nxx_favorite`
VALUES (1, '2021-05-27 00:28:00', 1, 1),
       (40, '2021-05-13 14:20:09', 2, 1);
/*!40000 ALTER TABLE `nxx_favorite`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nxx_idle_item`
--

DROP TABLE IF EXISTS `nxx_idle_item`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nxx_idle_item`
(
    `id`           bigint                                                         NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `idle_name`    varchar(64)                                                    NOT NULL COMMENT '闲置物名称',
    `idle_details` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详情',
    `picture_list` varchar(1024)                                                  NOT NULL COMMENT '图集',
    `idle_price`   decimal(10, 2)                                                 NOT NULL COMMENT '价格',
    `idle_place`   varchar(32)                                                    NOT NULL COMMENT '发货地区',
    `idle_label`   int                                                            NOT NULL COMMENT '分类标签',
    `release_time` datetime                                                       NOT NULL COMMENT '发布时间',
    `idle_status`  tinyint                                                        NOT NULL COMMENT '状态（发布1、在卖2、下架3、删除0）',
    `user_id`      bigint                                                         NOT NULL COMMENT '用户主键id',
    PRIMARY KEY (`id`),
    KEY `user_id_index` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 56
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nxx_idle_item`
--

LOCK TABLES `nxx_idle_item` WRITE;
/*!40000 ALTER TABLE `nxx_idle_item`
    DISABLE KEYS */;
INSERT INTO `nxx_idle_item`
VALUES (1, '商品1', '在这改商品介绍',
        '[\"https://gxy-seec2.oss-cn-beijing.aliyuncs.com/seec2/20210502170935.jpg\",\"https://gxy-seec2.oss-cn-beijing.aliyuncs.com/seec2/20210502171024.jpg\"]',
        1000.00, '南京市', 1, '2020-12-26 23:01:01', 1, 1),
       (54, '商品2',
        '除了骁龙865处理器之外，还搭载了33W块充，配备5000毫安电池容量，让该机的续航能力成为一大特色。另外，该机的屏幕支持144Hz的超高刷新率，还能根据需求调节刷新率档次，让用户的需求得到更好地满足。除此之外，该机还提供了LPDDR5+UFS3.1，支持WIFI6，这些配置即便放在2021年，也依旧非常有竞争力。',
        '[\"http://localhost:8090/img/file1620734950559k30s.jpg\"]', 2000.00, '市辖区', 1, '2021-05-11 20:09:14', 1, 1),
       (55, '计网',
        '《计算机网络》是由谢希仁编写的国内外使用最广泛、最权威的计算机网络经典教材。自1989年首次出版以来，于1994年、1999年和2003年分别出了修订版。2006年8月本教材通过了教育部的评审，被纳入普通高等教育“十一五”国家级规划教材。《计算机网络》的第5版，在内容和结构方面都有了很大的修改。',
        '[\"http://localhost:8090/img/file1620890211370net.jpg\"]', 20.00, '市辖区', 4, '2021-05-13 15:16:53', 2, 1);
/*!40000 ALTER TABLE `nxx_idle_item`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nxx_message`
--

DROP TABLE IF EXISTS `nxx_message`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nxx_message`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `user_id`     bigint       NOT NULL COMMENT '用户主键id',
    `idle_id`     bigint       NOT NULL COMMENT '闲置主键id',
    `content`     varchar(256) NOT NULL COMMENT '留言内容',
    `create_time` datetime     NOT NULL COMMENT '留言时间',
    `to_user`     bigint       NOT NULL COMMENT '所回复的用户',
    `to_message`  bigint DEFAULT NULL COMMENT '所回复的留言',
    PRIMARY KEY (`id`),
    KEY `user_id_index` (`user_id`),
    KEY `idle_id_index` (`idle_id`),
    KEY `to_user_index` (`to_user`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 37
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nxx_message`
--

LOCK TABLES `nxx_message` WRITE;
/*!40000 ALTER TABLE `nxx_message`
    DISABLE KEYS */;
INSERT INTO `nxx_message`
VALUES (35, 1, 54, 'Hello,World!', '2021-05-12 19:51:20', 1, NULL),
       (36, 2, 1, '哈喽啊', '2021-05-12 20:22:26', 1, NULL);
/*!40000 ALTER TABLE `nxx_message`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nxx_order`
--

DROP TABLE IF EXISTS `nxx_order`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nxx_order`
(
    `id`             bigint         NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `order_number`   varchar(32)    NOT NULL COMMENT '订单编号',
    `user_id`        bigint         NOT NULL COMMENT '用户主键id',
    `idle_id`        bigint         NOT NULL COMMENT '闲置物品主键id',
    `order_price`    decimal(10, 2) NOT NULL COMMENT '订单总价',
    `payment_status` tinyint        NOT NULL COMMENT '支付状态',
    `payment_way`    varchar(16) DEFAULT NULL COMMENT '支付方式',
    `create_time`    datetime       NOT NULL COMMENT '创建时间',
    `payment_time`   datetime    DEFAULT NULL COMMENT '支付时间',
    `order_status`   tinyint        NOT NULL COMMENT '订单状态',
    `is_deleted`     tinyint     DEFAULT NULL COMMENT '是否删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 61
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nxx_order`
--

LOCK TABLES `nxx_order` WRITE;
/*!40000 ALTER TABLE `nxx_order`
    DISABLE KEYS */;
INSERT INTO `nxx_order`
VALUES (60, '1620890546177', 2, 55, 20.00, 1, '支付宝', '2021-05-13 15:22:26', '2021-05-13 15:22:28', 3, NULL);
/*!40000 ALTER TABLE `nxx_order`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nxx_order_address`
--

DROP TABLE IF EXISTS `nxx_order_address`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nxx_order_address`
(
    `id`              bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `order_id`        bigint                                                        NOT NULL COMMENT '订单id',
    `consignee_name`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '收货人',
    `consignee_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '电话',
    `detail_address`  varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货地址',
    PRIMARY KEY (`id`),
    UNIQUE KEY `orderId` (`order_id`),
    KEY `order_id_index` (`order_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 48
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nxx_order_address`
--

LOCK TABLES `nxx_order_address` WRITE;
/*!40000 ALTER TABLE `nxx_order_address`
    DISABLE KEYS */;
INSERT INTO `nxx_order_address`
VALUES (1, 1, 'Gxy', '111111', '南京大学'),
       (42, 55, 'Gxy', '12345678890', '北京市市辖区东城区江苏'),
       (43, 56, 'Gxy', '12345678890', '北京市市辖区东城区江苏'),
       (44, 57, 'Gxy', '123456789', '江苏省南京市栖霞区仙林大街163号'),
       (45, 58, 'Gxy', '123456789', '江苏省南京市栖霞区仙林大街163号'),
       (46, 59, 'Gxy', '12345678890', '北京市市辖区东城区江苏'),
       (47, 60, 'Gxy', '12345678890', '北京市市辖区东城区江苏');
/*!40000 ALTER TABLE `nxx_order_address`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nxx_user`
--

DROP TABLE IF EXISTS `nxx_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nxx_user`
(
    `id`             bigint       NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `account_number` varchar(16)  NOT NULL COMMENT '账号（手机号）',
    `user_password`  varchar(16)  NOT NULL COMMENT '登录密码',
    `nickname`       varchar(32)  NOT NULL COMMENT '昵称',
    `avatar`         varchar(256) NOT NULL COMMENT '头像',
    `sign_in_time`   datetime     NOT NULL COMMENT '注册时间',
    `user_status`    tinyint DEFAULT NULL COMMENT '状态（1代表封禁）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `account_number` (`account_number`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 34
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nxx_user`
--

LOCK TABLES `nxx_user` WRITE;
/*!40000 ALTER TABLE `nxx_user`
    DISABLE KEYS */;
INSERT INTO `nxx_user`
VALUES (1, '111111', '123456', 'Gxy', 'https://cdn.jsdelivr.net/gh/Gxy-2001/img/seec2/20210503143842.jpg',
        '2021-04-21 19:15:45', 0),
       (2, '222222', '123456', 'Yxg', 'https://cdn.jsdelivr.net/gh/Gxy-2001/img/seec2/20210503143842.jpg',
        '2021-05-03 10:42:38', 0);
/*!40000 ALTER TABLE `nxx_user`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2021-05-15 23:18:05
