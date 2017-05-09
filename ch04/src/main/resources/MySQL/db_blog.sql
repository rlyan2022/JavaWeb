# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: db_blog
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `db_blog`;
CREATE DATABASE `db_blog` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_blog`;

#
# Table structure for table article
#

CREATE TABLE `article` (
  `Id` int(11) NOT NULL auto_increment,
  `title` varchar(50) default NULL,
  `content` text,
  `username` varchar(50) default NULL,
  `date` datetime default NULL,
  `hasread` int(11) default '0',
  PRIMARY KEY  (`Id`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='文章表';
INSERT INTO `article` VALUES (1,'SSH整合方案（一）','<p>这段时间准备写一下SSH的整合方法<img alt=\"\" src=\"http://localhost:8480/ch04/user/fckeditor/editor/images/smiley/qq/017.jpg\" /></p>','xiaoxiao','2009-12-27 10:58:50',2);
INSERT INTO `article` VALUES (2,'SSH整合方案（二）','<p>让我们一起感受SSH整合的魅力吧！！！！！</p>','xiaoxiao','2009-12-27 10:59:41',1);
INSERT INTO `article` VALUES (3,'今天好冷啊！','<p>外面下雪了，今年的冬天来得太早了。<img alt=\"\" src=\"http://localhost:8480/ch04/user/fckeditor/editor/images/smiley/qq/005.gif\" /></p>','xiaoxiao','2009-12-27 11:00:22',1);
INSERT INTO `article` VALUES (4,'继续更新我的SSH整合方案！','','xiaoxiao','2009-12-27 11:08:43',1);
INSERT INTO `article` VALUES (5,'测试日志！！','<p>测试日志！！<img alt=\"\" src=\"http://localhost:8480/ch04/user/fckeditor/editor/images/smiley/qq/004.gif\" /></p>','xiaoxiao','2010-01-27 18:05:06',1);

#
# Table structure for table bloginfo
#

CREATE TABLE `bloginfo` (
  `username` varchar(20) NOT NULL default '',
  `blogtitle` varchar(50) default NULL,
  `idiograph` varchar(50) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个性设置表';
INSERT INTO `bloginfo` VALUES ('xiaoxiao','我是小小','我喜欢写博客');

#
# Table structure for table critique
#

CREATE TABLE `critique` (
  `Id` int(11) NOT NULL auto_increment,
  `AId` int(11) default NULL,
  `content` text,
  `username` varchar(50) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `AId` (`AId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='评论表';
INSERT INTO `critique` VALUES (1,3,'<p>是啊，太冷了，我还好！！！</p>','xiaoxiao');
INSERT INTO `critique` VALUES (2,3,'<p>我们公司的暖气坏了，好冷啊！！</p>','xiaoxiao');
INSERT INTO `critique` VALUES (3,2,'<p>测试一下！</p>','xiaoxiao');
INSERT INTO `critique` VALUES (4,5,'<p>评论！！</p>','xiaoxiao');
INSERT INTO `critique` VALUES (5,1,'<p>测试一下！！</p>','xiaoxiao');
INSERT INTO `critique` VALUES (6,5,'<p><a href=\"http://localhost:8480/ch04/\">http://localhost:8480/ch04/</a></p>','匿名');

#
# Table structure for table dianjiliang
#

CREATE TABLE `dianjiliang` (
  `Id` int(11) NOT NULL auto_increment,
  `AId` int(11) default NULL,
  `ip` varchar(255) default NULL,
  `time` date default NULL,
  PRIMARY KEY  (`Id`),
  KEY `AId` (`AId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='点击量表';
INSERT INTO `dianjiliang` VALUES (1,1,'0:0:0:0:0:0:0:1','2009-12-27');
INSERT INTO `dianjiliang` VALUES (2,3,'0:0:0:0:0:0:0:1','2009-12-27');
INSERT INTO `dianjiliang` VALUES (3,1,'0:0:0:0:0:0:0:1','2010-01-27');
INSERT INTO `dianjiliang` VALUES (4,2,'0:0:0:0:0:0:0:1','2010-01-27');
INSERT INTO `dianjiliang` VALUES (5,5,'0:0:0:0:0:0:0:1','2010-01-27');
INSERT INTO `dianjiliang` VALUES (6,4,'0:0:0:0:0:0:0:1','2010-01-27');

#
# Table structure for table user
#

CREATE TABLE `user` (
  `username` varchar(20) NOT NULL default '',
  `password` varchar(20) default NULL,
  `nickname` varchar(20) default NULL,
  `question` varchar(50) default NULL,
  `answer` varchar(50) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
INSERT INTO `user` VALUES ('xiaoxiao','xiaoxiao','厉风行','你的家乡','湖南益阳市');

#
#  Foreign keys for table article
#

ALTER TABLE `article`
ADD CONSTRAINT `article_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

#
#  Foreign keys for table bloginfo
#

ALTER TABLE `bloginfo`
ADD CONSTRAINT `bloginfo_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

#
#  Foreign keys for table critique
#

ALTER TABLE `critique`
ADD CONSTRAINT `critique_ibfk_1` FOREIGN KEY (`AId`) REFERENCES `article` (`Id`);

#
#  Foreign keys for table dianjiliang
#

ALTER TABLE `dianjiliang`
ADD CONSTRAINT `dianjiliang_ibfk_1` FOREIGN KEY (`AId`) REFERENCES `article` (`Id`);


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
