/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE
DATABASE /*!32312 IF NOT EXISTS*/`nxx` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE
`nxx`;

/*Table structure for table `nxx_admin` */

DROP TABLE IF EXISTS `nxx_admin`;

CREATE TABLE `nxx_admin`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `account_number` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员账号',
    `admin_password` varchar(16)                                                  NOT NULL COMMENT '密码',
    `admin_name`     varchar(8)                                                   NOT NULL COMMENT '管理员名字',
    PRIMARY KEY (`id`),
    UNIQUE KEY `account_number` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `nxx_admin` */

insert into `nxx_admin`(`id`, `account_number`, `admin_password`, `admin_name`)
values (1, 'admin', '123456', '管理员');

/*Table structure for table `nxx_user` */

DROP TABLE IF EXISTS `nxx_user`;

CREATE TABLE `nxx_user`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `account_number` varchar(16)  NOT NULL COMMENT '账号（手机号）',
    `user_password`  varchar(16)  NOT NULL COMMENT '登录密码',
    `nickname`       varchar(32)  NOT NULL COMMENT '昵称',
    `avatar`         varchar(256) NOT NULL COMMENT '头像',
    `sign_in_time`   datetime     NOT NULL COMMENT '注册时间',
    `user_status`    tinyint(4) DEFAULT NULL COMMENT '状态（1代表封禁）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `account_number` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `nxx_user` */

insert into `nxx_user`(`id`, `account_number`, `user_password`, `nickname`, `avatar`, `sign_in_time`, `user_status`)
values (1, '111111', '123456', 'Gxy', 'touxiang.png', now(), 0),
       (2, '222222', '123456', 'Yxg', 'touxiang.png', now(), 0);


/*Table structure for table `nxx_address` */

DROP TABLE IF EXISTS `nxx_address`;

CREATE TABLE `nxx_address`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `consignee_name`  varchar(32) NOT NULL COMMENT '收货人姓名',
    `consignee_phone` varchar(16) NOT NULL COMMENT '收货人手机号',
    `province_name`   varchar(32) NOT NULL COMMENT '省',
    `city_name`       varchar(32) NOT NULL COMMENT '市',
    `region_name`     varchar(32) NOT NULL COMMENT '区',
    `detail_address`  varchar(64) NOT NULL COMMENT '详细地址',
    `default_flag`    tinyint(4) NOT NULL COMMENT '是否默认地址',
    `user_id`         bigint(20) NOT NULL COMMENT '用户主键id',
    PRIMARY KEY (`id`),
    KEY               `user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `nxx_address` */

insert into `nxx_address`(`id`, `consignee_name`, `consignee_phone`, `province_name`, `city_name`, `region_name`,
                          `detail_address`, `default_flag`, `user_id`)
values (20, 'Gxy', '123456789', '江苏省', '南京市', '栖霞区', '仙林大街163号', 0, 1);


/*Table structure for table `nxx_favorite` */

DROP TABLE IF EXISTS `nxx_favorite`;

CREATE TABLE `nxx_favorite`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
    `create_time` datetime NOT NULL COMMENT '加入收藏的时间',
    `user_id`     bigint(20) NOT NULL COMMENT '用户主键id',
    `idle_id`     bigint(20) NOT NULL COMMENT '闲置物主键id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id` (`user_id`,`idle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `nxx_favorite` */

insert into `nxx_favorite`(`id`, `create_time`, `user_id`, `idle_id`)
values (1, '2021-05-27 00:28:00', 1, 1);


/*Table structure for table `sh_idle_item` */

DROP TABLE IF EXISTS `nxx_idle_item`;

CREATE TABLE `nxx_idle_item`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `idle_name`    varchar(64)                                                    NOT NULL COMMENT '闲置物名称',
    `idle_details` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详情',
    `picture_list` varchar(1024)                                                  NOT NULL COMMENT '图集',
    `idle_price`   decimal(10, 2)                                                 NOT NULL COMMENT '价格',
    `idle_place`   varchar(32)                                                    NOT NULL COMMENT '发货地区',
    `idle_label`   int(11) NOT NULL COMMENT '分类标签',
    `release_time` datetime                                                       NOT NULL COMMENT '发布时间',
    `idle_status`  tinyint(4) NOT NULL COMMENT '状态（发布1、下架2、删除0）',
    `user_id`      bigint(20) NOT NULL COMMENT '用户主键id',
    PRIMARY KEY (`id`),
    KEY            `user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `nxx_idle_item`(`id`, `idle_name`, `idle_details`, `picture_list`, `idle_price`, `idle_place`, `idle_label`,
                            `release_time`, `idle_status`, `user_id`)
values (1, '商品1', '在这改商品介绍',
        '[\"https://gxy-seec2.oss-cn-beijing.aliyuncs.com/seec2/20210502170935.jpg\",\"https://gxy-seec2.oss-cn-beijing.aliyuncs.com/seec2/20210502171024.jpg\"]',
        '1000.00', '南京市', 1, '2020-12-26 23:01:01', 1, 1)


/*Table structure for table `nxx_message` */

DROP TABLE IF EXISTS `nxx_message`;

CREATE TABLE `nxx_message`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `user_id`     bigint(20) NOT NULL COMMENT '用户主键id',
    `idle_id`     bigint(20) NOT NULL COMMENT '闲置主键id',
    `content`     varchar(256) NOT NULL COMMENT '留言内容',
    `create_time` datetime     NOT NULL COMMENT '留言时间',
    `to_user`     bigint(20) NOT NULL COMMENT '所回复的用户',
    `to_message`  bigint(20) DEFAULT NULL COMMENT '所回复的留言',
    PRIMARY KEY (`id`),
    KEY           `user_id_index` (`user_id`),
    KEY           `idle_id_index` (`idle_id`),
    KEY           `to_user_index` (`to_user`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `nxx_message` */

insert into `nxx_message`(`id`, `user_id`, `idle_id`, `content`, `create_time`, `to_user`, `to_message`)
values (1, 1, 1, '哈哈哈哈哈哈哈哈哈哈哈', '2021-05-03 12:34:16', 2, NULL),
       (2, 1, 1, '啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n啦啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊', '2021-05-03 13:50:42', 2, 1),
       (3, 1, 1, '啊啊啊啊啊啊\n阿凡达发', '2021-05-03 13:53:53', 2, 1),
       (4, 2, 1, 'gggggggg', '2021-05-03 14:00:39', 1, NULL),
       (5, 2, 1, 'aaaaaaa', '2021-05-03 14:01:01', 1, NULL),
       (6, 2, 1, '11111111111111111111\n22222222\n3\n25\n56', '2021-05-03 14:02:54', 1, NULL);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
