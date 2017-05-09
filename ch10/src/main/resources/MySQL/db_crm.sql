# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: db_crm
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `db_crm`;
CREATE DATABASE `db_crm` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_crm`;


create schema dbo;
use dbo;

#
# Table structure for table cst_manager
#

DROP TABLE IF EXISTS `cst_manager`;
CREATE TABLE `cst_manager` (
  `man_id` bigint(20) NOT NULL auto_increment,
  `man_name` varchar(20) NOT NULL default '',
  PRIMARY KEY  (`man_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
INSERT INTO `cst_manager` VALUES (1,'庞小庆');
INSERT INTO `cst_manager` VALUES (2,'江波');
INSERT INTO `cst_manager` VALUES (3,'胡波');
INSERT INTO `cst_manager` VALUES (4,'李庆华');
INSERT INTO `cst_manager` VALUES (5,'刘大伟');
INSERT INTO `cst_manager` VALUES (6,'张佩军');
INSERT INTO `cst_manager` VALUES (7,'李大维');
INSERT INTO `cst_manager` VALUES (8,'胡勇');
INSERT INTO `cst_manager` VALUES (9,'张达');
INSERT INTO `cst_manager` VALUES (10,'杨晓波');

#
# Table structure for table orders
#

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `odr_id` bigint(20) NOT NULL default '0',
  `odr_customer` varchar(100) NOT NULL default '',
  `odr_date` datetime NOT NULL,
  `odr_addr` varchar(200) default '',
  `odr_status` char(1) NOT NULL default ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `orders` VALUES (1,'珠海拱北万家百货','2008-01-19','广东省珠海市拱北','6');
INSERT INTO `orders` VALUES (2,'珠海拱北万家百货','2008-02-19','广东省珠海市拱北','1');
INSERT INTO `orders` VALUES (3,'珠海拱北万家百货','2008-03-19','广东省珠海市拱北','1');
INSERT INTO `orders` VALUES (4,'珠海拱北万家百货','2008-04-19','广东省珠海市拱北','5');
INSERT INTO `orders` VALUES (5,'珠海拱北万家百货','2008-05-19','广东省珠海市拱北','1');
INSERT INTO `orders` VALUES (6,'珠海新一佳百货公司','2008-01-12','广东省珠海市香洲区南坑市场对面','1');
INSERT INTO `orders` VALUES (7,'珠海新一佳百货公司','2008-02-12','广东省珠海市香洲区南坑市场对面','6');
INSERT INTO `orders` VALUES (8,'珠海新一佳百货公司','2008-03-12','广东省珠海市香洲区南坑市场对面','1');
INSERT INTO `orders` VALUES (9,'珠海新一佳百货公司','2008-04-12','广东省珠海市香洲区南坑市场对面','5');
INSERT INTO `orders` VALUES (10,'珠海新一佳百货公司','2008-05-12','广东省珠海市香洲区南坑市场对面','1');

#
# Table structure for table sal_chance
#

DROP TABLE IF EXISTS `sal_chance`;
CREATE TABLE `sal_chance` (
  `chc_id` bigint(20) NOT NULL auto_increment,
  `chc_source` varchar(50) default '',
  `chc_cust_name` varchar(100) NOT NULL default '',
  `chc_title` varchar(200) NOT NULL default '',
  `chc_rate` int(11) NOT NULL default '0',
  `chc_linkman` varchar(50) default '',
  `chc_tel` varchar(50) default '',
  `chc_desc` text NOT NULL,
  `chc_create_by` varchar(50) NOT NULL default '',
  `chc_create_date` varchar(20) NOT NULL default '(getdate())',
  `chc_due_to` varchar(50) default '',
  `chc_due_date` varchar(20) default '',
  `chc_status` char(10) NOT NULL default '(1)',
  PRIMARY KEY  (`chc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
INSERT INTO `sal_chance` VALUES (18,'朋友介绍','珠海房产网','想买笔记本电脑',90,'刘小明','123456789','希望采购50台笔记本 <br>','admin','2009年12月13日',NULL,NULL,'1');
INSERT INTO `sal_chance` VALUES (19,'老乡介绍','中国人才资源中心','希望采购100台台式机',99,'莫小妹','789456123','&nbsp;希望采购100台台式机','admin','2009年12月13日',NULL,NULL,'1');
INSERT INTO `sal_chance` VALUES (20,'业务来往','珠海旅游局','采购10台笔记本',99,'小王','123456789','','admin','2009年12月13日',NULL,NULL,'1');

#
# Table structure for table sal_plan
#

DROP TABLE IF EXISTS `sal_plan`;
CREATE TABLE `sal_plan` (
  `pla_id` bigint(20) NOT NULL auto_increment,
  `pla_chc_id` bigint(20) NOT NULL default '0',
  `pla_date` varchar(20) NOT NULL default '',
  `pla_todo` text NOT NULL,
  `pla_result` text,
  PRIMARY KEY  (`pla_id`),
  KEY `FK_sal_plan_sal_chance` (`pla_chc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Table structure for table storage
#

DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage` (
  `stk_id` bigint(20) NOT NULL default '0',
  `stk_prod_id` bigint(20) NOT NULL default '0',
  `stk_warehouse` varchar(50) NOT NULL default '',
  `stk_ware` varchar(50) NOT NULL default '',
  `stk_count` int(11) NOT NULL default '0',
  `stk_memo` varchar(200) default ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `storage` VALUES (5,1,'北京-西直门库','EC-D2',20,'');
INSERT INTO `storage` VALUES (6,2,'北京-西直门库','EC-D3',25,'');
INSERT INTO `storage` VALUES (7,4,'北京-西直门库','EC-D5',15,'');
INSERT INTO `storage` VALUES (8,7,'北京-大钟寺库','EA-B8',5,'');
INSERT INTO `storage` VALUES (9,10,'北京-大钟寺库','EA-B5',8,'');
INSERT INTO `storage` VALUES (10,11,'北京-大钟寺库','EA-B4',9,'');
INSERT INTO `storage` VALUES (11,12,'北京-马甸库','EA-A2',11,'');
INSERT INTO `storage` VALUES (12,13,'北京-马甸库','EA-A3',11,'好棒哦');
INSERT INTO `storage` VALUES (13,14,'北京-马甸库','EA-A4',12,'');
INSERT INTO `storage` VALUES (14,15,'北京-马甸库','EA-A5',11,'');

#
# Table structure for table sys_right
#

DROP TABLE IF EXISTS `sys_right`;
CREATE TABLE `sys_right` (
  `right_code` bigint(20) NOT NULL auto_increment,
  `right_text` varchar(50) default '',
  `right_url` varchar(100) default '',
  PRIMARY KEY  (`right_code`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
INSERT INTO `sys_right` VALUES (1,'销售机会管理','/sale.do');
INSERT INTO `sys_right` VALUES (2,'客户开发计划','/plan.do');
INSERT INTO `sys_right` VALUES (3,'客户信息管理','/customer.do');
INSERT INTO `sys_right` VALUES (4,'客户流失管理','/cstLost.do');
INSERT INTO `sys_right` VALUES (5,'客户服务管理','/cstService.do');
INSERT INTO `sys_right` VALUES (6,'统计报表','/report.do');
INSERT INTO `sys_right` VALUES (7,'数据字典管理','/basdict.do');
INSERT INTO `sys_right` VALUES (8,'查询产品信息','/product.do');
INSERT INTO `sys_right` VALUES (9,'查询库存','/storage.do');
INSERT INTO `sys_right` VALUES (10,'权限管理','/right.do');

#
# Table structure for table sys_role
#

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL auto_increment,
  `role_name` varchar(50) NOT NULL default '',
  `role_desc` varchar(50) default '',
  `role_flag` int(11) default '0',
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
INSERT INTO `sys_role` VALUES (1,'系统管理员','最高权限',1);
INSERT INTO `sys_role` VALUES (2,'销售主管','客户管理、营销管理以及统计报表',1);
INSERT INTO `sys_role` VALUES (3,'客户经理','客户管理、基础数据以及统计报表',1);
INSERT INTO `sys_role` VALUES (4,'董事会董事','查看统计报表',1);
INSERT INTO `sys_role` VALUES (6,'销售员','营销管理',NULL);

#
# Table structure for table sys_role_right
#

DROP TABLE IF EXISTS `sys_role_right`;
CREATE TABLE `sys_role_right` (
  `r_r_id` bigint(20) NOT NULL auto_increment,
  `right_id` bigint(20) NOT NULL default '0',
  `roles_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`r_r_id`),
  KEY `FK_Sys_role_right_sys_role` (`roles_id`),
  KEY `FK_sys_role_right_sys_right` (`right_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
INSERT INTO `sys_role_right` VALUES (5,1,2);
INSERT INTO `sys_role_right` VALUES (6,2,2);
INSERT INTO `sys_role_right` VALUES (7,5,2);
INSERT INTO `sys_role_right` VALUES (8,6,2);
INSERT INTO `sys_role_right` VALUES (9,1,3);
INSERT INTO `sys_role_right` VALUES (10,2,3);
INSERT INTO `sys_role_right` VALUES (11,3,3);
INSERT INTO `sys_role_right` VALUES (12,4,3);
INSERT INTO `sys_role_right` VALUES (13,5,3);
INSERT INTO `sys_role_right` VALUES (14,6,3);

#
# Table structure for table sys_user
#

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `usr_id` bigint(20) NOT NULL auto_increment,
  `usr_name` varchar(50) NOT NULL default '',
  `usr_password` varchar(50) NOT NULL default '',
  `usr_role_id` bigint(20) default '0',
  `usr_flag` int(11) NOT NULL default '0',
  PRIMARY KEY  (`usr_id`),
  KEY `FK_sys_user_sys_role` (`usr_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
INSERT INTO `sys_user` VALUES (2,'admin','admin',1,1);
INSERT INTO `sys_user` VALUES (7,'wangqinghai','pwd',2,1);
INSERT INTO `sys_user` VALUES (8,'huangcaiyi','s',3,1);
INSERT INTO `sys_user` VALUES (9,'huanglonghui','pwd',4,1);

#
#  Foreign keys for table sal_plan
#

ALTER TABLE `sal_plan`
ADD CONSTRAINT `FK_sal_plan_sal_chance` FOREIGN KEY (`pla_chc_id`) REFERENCES `sal_chance` (`chc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table sys_role_right
#

ALTER TABLE `sys_role_right`
ADD CONSTRAINT `FK_Sys_role_right_sys_role` FOREIGN KEY (`roles_id`) REFERENCES `sys_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_sys_role_right_sys_right` FOREIGN KEY (`right_id`) REFERENCES `sys_right` (`right_code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table sys_user
#

ALTER TABLE `sys_user`
ADD CONSTRAINT `FK_sys_user_sys_role` FOREIGN KEY (`usr_role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
