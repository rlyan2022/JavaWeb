# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: db_examsystem
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `db_examsystem`;
CREATE DATABASE `db_examsystem` /*!40100 DEFAULT CHARACTER SET gb2312 */;
USE `db_examsystem`;

#
# Table structure for table tb_student
#

CREATE TABLE `tb_student` (
  `studentID` varchar(20) NOT NULL default '',
  `password` varchar(20) default NULL,
  `studentName` varchar(20) default NULL,
  `result` int(11) default NULL,
  `sclass` varchar(20) default NULL,
  PRIMARY KEY  (`studentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='学生信息表';
INSERT INTO `tb_student` VALUES ('0509302*21','student','李明华',20,'0509302');
INSERT INTO `tb_student` VALUES ('05099301*21','fsdfsdf','洪林',100,'0509301');

#
# Table structure for table tb_subject
#

CREATE TABLE `tb_subject` (
  `subjectID` int(11) NOT NULL auto_increment,
  `subjectTitle` varchar(255) default NULL,
  `subjectOptionA` varchar(50) default NULL,
  `subjectOptionB` varchar(50) default NULL,
  `subjectOptionC` varchar(50) default NULL,
  `subjectOptionD` varchar(50) default NULL,
  `subjectAnswer` varchar(1) default NULL,
  `subjectParse` text,
  PRIMARY KEY  (`subjectID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=gb2312 COMMENT='试题信息表';
INSERT INTO `tb_subject` VALUES (2,'下列各进制数中最大的数是( )。','227O','1FFH','1010001B','789D','D',NULL);
INSERT INTO `tb_subject` VALUES (3,'( )不是微型计算机必须的工作环境。','恒温','良好的接地线路','远离强磁场','稳定的电源电压','A',NULL);
INSERT INTO `tb_subject` VALUES (4,'( )不是文件。','设备','文档','应用程序','窗口','D','窗口不是文件');
INSERT INTO `tb_subject` VALUES (5,'( )不属于Word2003的文档打开方式。','读写方式','只读方式','备份方式','系统方式','D','系统方式不是Word2003的文档打开方式。');
INSERT INTO `tb_subject` VALUES (6,'( )菜单中含有设置字体的命令。','格式','编辑','视图','工具','A',NULL);
INSERT INTO `tb_subject` VALUES (8,'( )的功能是将计算机外部的信息送入计算机。','输入设备','输出设备','软盘','电源线','A','');
INSERT INTO `tb_subject` VALUES (9,'( )的主要功能是使用户的计算机与远程主机相连，从而成为远程主机的终端。','E-mail','FTP','Telnet','BBS','C','');
INSERT INTO `tb_subject` VALUES (10,'( )视图方式可对文档不进行分页处理。','普通','联机版式','页面视图','大纲','B','');
INSERT INTO `tb_subject` VALUES (12,'( )是微型计算机的外存。 ','RAM','ROM','磁盘','虚拟盘','C','');
INSERT INTO `tb_subject` VALUES (13,'( )是用来存储程序及数据的装置。','输入设备','存储器','控制器','输出设备','B','');
INSERT INTO `tb_subject` VALUES (14,'NOVELLNETWARE是( )','网络操作系统','通用操作系统','实时操作系统','分时操作系统','A','');
INSERT INTO `tb_subject` VALUES (15,'预防计算机病毒的手段，错误的是( )。','要经常地对硬盘上的文件进行备份','凡不需要再写入数据的磁盘都应有写保护','将所有的.COM和.EXE文件赋以“只读”属性','对磁盘进行清洗','D','');
INSERT INTO `tb_subject` VALUES (16,'“32位微型计算机”中的32指的是( )','微机型号','内存容量','存储单位','机器字长','D','');
INSERT INTO `tb_subject` VALUES (17,'“奔腾”微型计算机采用的微处理器的型号是( )','80286','80386','80486','80586','D','');
INSERT INTO `tb_subject` VALUES (18,'“画图”程序是WINDOWS98的一个附件程序，能进行简单的图形处理，产生的文件为位图文件，扩展名为( )。','BMP','CRH','EXE','GPH','A','');
INSERT INTO `tb_subject` VALUES (19,'“开始”菜单中，注销命令将( )。','关闭系统','关闭当前窗口上的程序','关闭所有打开的程序','以上都不对','C','');
INSERT INTO `tb_subject` VALUES (20,'“文件”下拉菜单底部所显示的文件名是( )。','扩展名为DOC的全部文件','正要打印的文件名','正在使用的文件名','最近被Word处理过的文档','D','');
INSERT INTO `tb_subject` VALUES (21,'“溢出”一般是指计算机在运算过程中产生的( )。','数据量超过了内存量','文件个数超过了磁盘目录区规定的范围','数超过了机器的位所表示的范围','数超过了变量的表示范围','C','');
INSERT INTO `tb_subject` VALUES (22,'《计算机软件条例》中所称的计算机软件(简称软件)是指( )。','计算机程序','源程序和目标程序','源程序','计算机程序及其有关文档','D','');
INSERT INTO `tb_subject` VALUES (23,'10BaseF的含义是( )。','10Mbps的宽带光纤网络','10Mbps的基带同轴电缆网络','10Mbps的基带光纤网络','10Mbps的宽带同轴电缆网络','C','');
INSERT INTO `tb_subject` VALUES (24,'在微机中，主机由微处理器与( )组成。','运算器','磁盘存储器','软盘存储器','内存储器','D','');
INSERT INTO `tb_subject` VALUES (25,'1MB的存储容量相当于( )。','一百万个字节','2的10次方个字节','2的20次方个字节','1000KB','C','');
INSERT INTO `tb_subject` VALUES (26,'1nternet的分层结构是由( )这个四层组成。',' 应用层、传输层、通信子网层和物理层','应用层、表示层、传输层和网络层','物理层、数据链路层、网络层和传输层','网络接口层、网络层、传输层和应用层','D','');
INSERT INTO `tb_subject` VALUES (27,'WindowsXP中改变窗口的大小时，应操作( )。','窗口的四角或四边','窗口右上角的按钮','窗口的标题栏','窗口左上角的控制栏','A','');
INSERT INTO `tb_subject` VALUES (28,'在WindowsXP操作系统中，对话框的大小是( )。','可变的','可任意设置的','固定的','与不同的操作有关的','D','');
INSERT INTO `tb_subject` VALUES (29,'测试路考','向右转','向左转','向前','向后','D','应该向后！！！！');

#
# Table structure for table tb_teacher
#

CREATE TABLE `tb_teacher` (
  `teacherID` varchar(20) NOT NULL default '',
  `password` varchar(20) default NULL,
  PRIMARY KEY  (`teacherID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='老师信息表';
INSERT INTO `tb_teacher` VALUES ('teacher','teacher');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
