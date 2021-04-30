/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nxx` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `nxx`;

/*Table structure for table `sh_admin` */

DROP TABLE IF EXISTS `nxx_admin`;

CREATE TABLE `nxx_admin` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                            `account_number` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员账号',
                            `admin_password` varchar(16) NOT NULL COMMENT '密码',
                            `admin_name` varchar(8) NOT NULL COMMENT '管理员名字',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `account_number` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sh_admin` */

insert  into `nxx_admin`(`id`,`account_number`,`admin_password`,`admin_name`) values (1,'admin','123456','管理员');

/*Table structure for table `sh_user` */

DROP TABLE IF EXISTS `nxx_user`;

CREATE TABLE `nxx_user` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                           `account_number` varchar(16) NOT NULL COMMENT '账号（手机号）',
                           `user_password` varchar(16) NOT NULL COMMENT '登录密码',
                           `nickname` varchar(32) NOT NULL COMMENT '昵称',
                           `avatar` varchar(256) NOT NULL COMMENT '头像',
                           `sign_in_time` datetime NOT NULL COMMENT '注册时间',
                           `user_status` tinyint(4) DEFAULT NULL COMMENT '状态（1代表封禁）',
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `account_number` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sh_user` */

insert  into `nxx_user`(`id`,`account_number`,`user_password`,`nickname`,`avatar`,`sign_in_time`,`user_status`) values (1,'111111','123456','Gxy','touxiang.png',now(),0);



/*Table structure for table `sh_address` */

DROP TABLE IF EXISTS `nxx_address`;

CREATE TABLE `nxx_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `consignee_name` varchar(32) NOT NULL COMMENT '收货人姓名',
  `consignee_phone` varchar(16) NOT NULL COMMENT '收货人手机号',
  `province_name` varchar(32) NOT NULL COMMENT '省',
  `city_name` varchar(32) NOT NULL COMMENT '市',
  `region_name` varchar(32) NOT NULL COMMENT '区',
  `detail_address` varchar(64) NOT NULL COMMENT '详细地址',
  `default_flag` tinyint(4) NOT NULL COMMENT '是否默认地址',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sh_address` */

insert  into `nxx_address`(`id`,`consignee_name`,`consignee_phone`,`province_name`,`city_name`,`region_name`,`detail_address`,`default_flag`,`user_id`) values (20,'Gxy','123456789','江苏省','南京市','栖霞区','仙林大街163号',0,1);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
