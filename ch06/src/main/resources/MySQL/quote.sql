# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: quote
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `quote`;
CREATE DATABASE `quote` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `quote`;

#
# Table structure for table tb_customer
#

DROP TABLE IF EXISTS `tb_customer`;
CREATE TABLE `tb_customer` (
  `customerNO` varchar(20) NOT NULL default '',
  `address` varchar(30) default '(NULL)',
  `customerName` varchar(15) default '(NULL)',
  `otherInfo` varchar(30) default '(NULL)',
  `phone` varchar(15) default '(NULL)',
  `relationman` varchar(15) default '(NULL)',
  PRIMARY KEY  (`customerNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `tb_customer` VALUES ('0201305','北京市朝阳区XXX','周氏国际','备注1','123456789','周先生');
INSERT INTO `tb_customer` VALUES ('0201306','北京市朝阳区XXX','张氏国际','备注','123456789','张先生');
INSERT INTO `tb_customer` VALUES ('0201307','北京市朝阳区XXX','李氏国际','备注','123456789','李先生');
INSERT INTO `tb_customer` VALUES ('0201308','北京市朝阳区XXX','刘氏国际','备注','123456789','刘先生');
INSERT INTO `tb_customer` VALUES ('0201309','北京市朝阳区XXX','陈氏国际','备注','123456789','陈先生');
INSERT INTO `tb_customer` VALUES ('0201310','北京市朝阳区XXX','唐氏国际','备注信息','123456789','唐先生');

#
# Table structure for table tb_order
#

DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `orderNO` varchar(10) NOT NULL default '',
  `orderTime` datetime default NULL,
  `otherInfo` varchar(50) default '(NULL)',
  `quantity` varchar(10) default '(NULL)',
  `customerNO` varchar(20) default '(NULL)',
  `productNO` varchar(15) default '(NULL)',
  PRIMARY KEY  (`orderNO`),
  KEY `FKFA98EE3DD96DD882` (`customerNO`),
  KEY `FKFA98EE3D652AF8BA` (`productNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `tb_order` VALUES ('1010121','2009-11-08','6月10号之前必须发货','100','0201305','0104578');
INSERT INTO `tb_order` VALUES ('1010122','2009-11-08','6月10号之前必须发货','500','0201307','0104579');
INSERT INTO `tb_order` VALUES ('1010123','2009-11-08','6月13号之前必须发货','500','0201310','0104578');
INSERT INTO `tb_order` VALUES ('1010124','2009-11-08','6月14号之前必须发货','223','0201308','0104588');

#
# Table structure for table tb_product
#

DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `productNO` varchar(15) NOT NULL default '',
  `otherInfo` varchar(50) default '(NULL)',
  `price` decimal(10,2) default '0.00',
  `producingArea` varchar(20) default '(NULL)',
  `productName` varchar(20) default '(NULL)',
  `productOwner` varchar(20) default '(NULL)',
  `quantity` int(11) default '0',
  `unit` varchar(20) default '(NULL)',
  `producttypeNO` varchar(15) default '(NULL)',
  PRIMARY KEY  (`productNO`),
  KEY `FKED97341E7C52804E` (`producttypeNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `tb_product` VALUES ('0104578','备注',2999,'中国大陆','飞毛腿踏步机','飞毛腿公司',100,'台','15487');
INSERT INTO `tb_product` VALUES ('0104579','备注',1888,'美国','宝贝踏步机','宝贝集团',50,'台','15487');
INSERT INTO `tb_product` VALUES ('0104587','备注',2500,'中国大陆','桥大跑步机','桥大科技',150,'台','17894');
INSERT INTO `tb_product` VALUES ('0104588','备注',5000,'美国','汇祥跑步机','汇祥科技',20,'台','17894');

#
# Table structure for table tb_producttype
#

DROP TABLE IF EXISTS `tb_producttype`;
CREATE TABLE `tb_producttype` (
  `producttypeNO` varchar(15) NOT NULL default '',
  `producttypeName` varchar(20) default '(NULL)',
  PRIMARY KEY  (`producttypeNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `tb_producttype` VALUES ('09','测试');
INSERT INTO `tb_producttype` VALUES ('12456','拉力器');
INSERT INTO `tb_producttype` VALUES ('15123','拉力器');
INSERT INTO `tb_producttype` VALUES ('15474','健身车');
INSERT INTO `tb_producttype` VALUES ('15487','踏步机');
INSERT INTO `tb_producttype` VALUES ('17894','跑步机');

#
# Table structure for table tb_quotation
#

DROP TABLE IF EXISTS `tb_quotation`;
CREATE TABLE `tb_quotation` (
  `quotationNO` varchar(15) NOT NULL default '',
  `otherInfo` varchar(50) default '(NULL)',
  `quotationMan` varchar(15) default '(NULL)',
  `quotationTime` datetime default NULL,
  `customerNO` varchar(20) default '(NULL)',
  `productNO` varchar(15) default '',
  PRIMARY KEY  (`quotationNO`),
  KEY `FKCABDECFBD96DD882` (`customerNO`),
  KEY `FKCABDECFB652AF8BA` (`productNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `tb_quotation` VALUES ('01020304','以后多多合作','小张','2009-11-08','0201305','0104578');
INSERT INTO `tb_quotation` VALUES ('01020305','以后多多合作','小李','2009-11-08','0201306','0104579');
INSERT INTO `tb_quotation` VALUES ('01020306','以后多多合作','小孙','2009-11-08','0201310','0104588');
INSERT INTO `tb_quotation` VALUES ('01020307','以后多多合作','杨波','2009-11-08','0201308','0104578');

#
# Table structure for table tb_user
#

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `username` varchar(18) NOT NULL default '',
  `grade` int(11) NOT NULL default '0',
  `password` varchar(18) default '(NULL)',
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `tb_user` VALUES ('2222',1,'2222');
INSERT INTO `tb_user` VALUES ('3',3,'3');
INSERT INTO `tb_user` VALUES ('admin',3,'admin');
INSERT INTO `tb_user` VALUES ('daa',1,'22sa');

#
#  Foreign keys for table tb_order
#

ALTER TABLE `tb_order`
ADD CONSTRAINT `FKFA98EE3DD96DD882` FOREIGN KEY (`customerNO`) REFERENCES `tb_customer` (`customerNO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FKFA98EE3D652AF8BA` FOREIGN KEY (`productNO`) REFERENCES `tb_product` (`productNO`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table tb_product
#

ALTER TABLE `tb_product`
ADD CONSTRAINT `FKED97341E7C52804E` FOREIGN KEY (`producttypeNO`) REFERENCES `tb_producttype` (`producttypeNO`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table tb_quotation
#

ALTER TABLE `tb_quotation`
ADD CONSTRAINT `FKCABDECFBD96DD882` FOREIGN KEY (`customerNO`) REFERENCES `tb_customer` (`customerNO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FKCABDECFB652AF8BA` FOREIGN KEY (`productNO`) REFERENCES `tb_product` (`productNO`) ON DELETE NO ACTION ON UPDATE NO ACTION;


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
