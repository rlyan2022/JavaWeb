# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: db_news2
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `db_news2`;
CREATE DATABASE `db_news2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_news2`;

#
# Table structure for table news
#

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `newsId` int(11) NOT NULL auto_increment,
  `classId` int(11) NOT NULL default '0',
  `kindId` int(11) NOT NULL default '0',
  `myOther` int(11) NOT NULL default '0',
  `headTitle` varchar(255) NOT NULL default '',
  `content` text NOT NULL,
  `connect` varchar(255) default NULL,
  `author` varchar(20) NOT NULL default '',
  `editor` varchar(20) default NULL,
  `newsFrom` varchar(40) default NULL,
  `top` int(11) default NULL,
  `newsTime` varchar(20) default NULL,
  `hits` int(11) default '0',
  `state` int(11) default '0',
  `tag` int(11) NOT NULL default '0',
  PRIMARY KEY  (`newsId`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
INSERT INTO `news` VALUES (28,1,1,0,'详解Linux回击微软','据国外媒体报道，一直以来，微软都声称自己占有93%的全球上网本操作系统市场份额。然而，日前，ABI Research发布的数据却显示，Linux占据上网本市场32%的市场份额，并且还预测Linux将会在2013年的上网本市场中取代Windows。上述事实表明，两者的数据是大相径庭的。<br><br><br>公信力<br><br>　　毋庸置疑的，做为全球最大的软件公司，微软具有很强的公信力。<br><br><br>　　而ABI Research则已经从事调查行业19年的时间了，去年，高级分析师Jeff Orr也加入了ABI Research，可以说，ABI Research是一家根基稳固的公司。<br><br><br>戴尔发表声明<br><br>　　1月份，当有消息称，在上网本市场中，Windows彻底击败了Linux的时候，戴尔公司表示，该公司有1/3的Mini 9s预装的都是Linux，而且回报率并不比Windows XP。<br><br><br>　　分析师Orr强调指出，目前，很多的Linux用户都不是美国地区的，很多美国地区以外的用户使用的是Linux操作系统。<br><br><br>孰真孰假<br><br>　　众所周知，微软的首席执行官史蒂夫鲍尔默很反对外来物品，例如苹果的iPhone手机。前段时间，鲍尔默声称，该公司已经占据移动市场60%的市场份额，但是事实上仅为14%。由此看来，鲍尔默很有可能是一个温暖、充满关怀的CEO，并不喜欢听坏消息。<br><br><br>为何坏消息就是好消息<br><br>　　“例外管理”（MBE）是一个流行的管理模式，就是指有人必须将坏消息告知于CEO。然后，CEO就会寻找解决问题的途径。因此，坏消息可能会变成好消息。<br><br><br>　　但是，在微软公司中，很少会有人将坏消息报告给CEO。然而，事实就是事实，是掩盖不了的。<br><br><br>不同角度去分析问题<br><br>　　就这个问题来讲，并不能说微软公司在撒谎，很有可能是他们分析的国家和领域不同。','','华军资讯','小王','华军资讯',0,'2009-12-16 10:22:58',6,1,1);
INSERT INTO `news` VALUES (29,1,1,0,'开源Android将干掉Windows Mobile五大原因','新闻分析：微软的Windows Mobile操作系统可能是Android崛起最容易攻击的对象，在过去的一年中，Android的市场份额稳步上升，伴随摩托罗拉采用Android操作系统的Droid手机热卖，Android的发展趋势越来越被看好，这意味着Windows Mobile在推出其7.0版本前很可能被挤出移动设备领域，而微软此前曾透露Windows Mobile 7要等到明天某个时候才会发布。<br><br>　　自采用Android操作系统的摩托罗拉Droid从11月5日上市以来，很多媒体竞相将其与苹果的iPhone进行对比，运营商Verizon更是启动了一系列的咄咄逼人的广告强调Droid的某些功能，如同时运行多个应用程序 ?? 这正是iPhone目前缺少的功能。<br><br>　　但苹果可能不是Android影响最严重的公司，Google将目标锁定的也不是苹果，而是微软。微软目前最新的Windows Mobile操作系统是6.5版，下一个版本Windows Mobile 7要等到2010年某个时候才会发布，Android目前要狙击的目标就是Windows Mobile 6.5，与苹果和其它公司不一样，微软依靠它的操作系统和附加软件捆绑到众多手机厂商和运营商的设备中，进而占据了大部分移动操作系统市场，很长一段时间内都是微软的天下，现在终于看到一个强劲的对手上场了。<br><br>　　Android的市场份额正在快速上升<br><br>　　目前诺基亚使用最多的Symbian操作系统也被证明不是Android的对手，其市场份额从2008年10月的59%下跌到2009年10月27%，表面上看起来对于微软是个好消息，但实际上据AdMob 最近的调查数据显示，Windows Mobile的市场份额也下降了70%左右。<br><br>　　微软已经意识到了这个问题，早今年9月24日举行的创业投资峰会上，微软CEO史蒂夫鲍尔默承认公司搞砸了Windows Mobile，同时公开表示Windows Mobile 7已经开始启动，微软在10月6日发布了Windows Mobile 6.5，包括扩展了触摸等功能，但这应该是微软在发布Windows Mobile 7之前不得已的一个权宜之计。<br><br>　　理论上说Windows Mobile 6.5的发布应该阻止对微软移动市场份额的侵蚀，微软还打算用Windows Mobile 7与iPhone和黑莓展开一场恶斗，但现在又多出一个Android，据AdMob的数据显示，在过去的一年里，Android的市场份额至少翻了1000%。<br><br>　　这一增长势头势必会吸引更多制造商拥抱Android，之前微软曾宣布到2010年底，将会有13家手机厂商采用Windows Mobile 6.5，包括LG，HTC和Sony。Android也不示弱，Google移动平台高级主管Andy Rubin早先估计到本年年底将会有18-20种设备使用Android，包括摩托罗拉，戴尔，宏基，诺基亚等厂家都开始尝试Android。<br><br>　　Android将会是应用程序的世界<br><br>　　微软原本计划在10月启动的Windows Marketplace中内置600个应用程序，但最终却只有250个都不到，虽然这一数字在两个月后扩大到了接近800，目前约有1000家独立ISV注册开发移动应用程序。<br><br>　　相对于其它巨头的动作，移动应用程序可能成为Windows Mobile的致命一击，即使如此，除了苹果的App Store有8万个应用程序外，微软的800个应用程序也对RIM的BlackBerry App World(1000多个应用程序)和Palm的应用程序商店(350多个)造成了冲击。<br><br>　　所有这些竞争设备都集成了它们自己的操作系统，Google Android是一个开源例外，正是由于这个原因它总是被抨击，如果不是手机制造商有意回避操作系统，Android Marketplace应该已经有2000多个应用程序了，如果Android保持其增长势头，Android应用程序将会呈大面积爆发。如果真的那样，更多手机厂家不得不转向Android，将会进一步挤压Windows Mobile的市场份额。<br><br>　　缺乏其它操作系统竞争对手<br><br>　　10月21日，Symbian基金会宣布Symbian操作系统正式开源，该平台的微内核EKA2(Epco Kernel Architecture 2)比原计划提前了9个月发布，包括遵循Eclipse公共许可的SDK。<br><br>　　正如前文提到的，Symbian操作系统的市场份额在过去的一年里和Windows Mobile一样大幅下降，Symbian最终不得不选择和Android一样走开源道路，但在开源道路上Android显然早已驾轻就熟。<br><br>　　硬件配对<br><br>　　Google Android已经和摩托罗拉Droid和HTC Droid Eris搭档成功，之前曾有权威人士将它们作为iPhone杀手称呼。在摩托罗拉Droid上市的头一周，就卖出了25万部，但还是距iPhone 3GS上市第一周热销的1600万部的记录相差甚远，不过许多分析师认为Droid的表现让人印象深刻。<br><br>　　而Windows Mobile就缺乏这样热卖的设备了，最近倒是热传微软将和Verizon联手在2010年推出一款智能手机。但除非这款手机变成实物，否则谁也不能保证其成为事实，目前有关这个合作项目Pink的消息仍然被封锁，而Android在强大的广告攻势下，每一个星期都会增加人们的心理份额，如果谷歌赶在微软之前发布自有品牌的手机，那这个心理份额将会加速上升。<br><br>　　Windows Mobile 7仍然是个未知数<br><br>　　微软已经承诺Windows Mobile 7将会带来实质性的更新，如果这次升级再给公众留下失败的印象，微软在移动领域将会处于难以维持的弱势地位，这将会给Android足够的机会赢得智能手机用户的青睐。','','Linux论坛','小王','Linux论坛',0,'2009-12-16 10:23:38',3,1,1);
INSERT INTO `news` VALUES (30,4,12,0,'甲骨文向MySQL用户、开发者和客户的十项承诺','甲骨文/SUN交易案最新动向：甲骨文向MySQL用户、开发者、客户做出十项承诺。<br>　　<br>　　甲骨文公司与欧盟委员会就甲骨文SUN公司的交易案进行了建设性的讨论，并承诺将保持MySQL在数据库市场的竞争力。为了进一步获得欧盟的批准，甲骨文公司公开其十项承诺，内容如下：<br>　　<br>　　<br>　　1、保证存储引擎API持续可用性。<br>　　甲骨文保证并阶段性加强MySQL的可插拔存储引擎架构，让用户可以灵活选择从本地和第三方产品提供存储引擎。此保证意味着MySQL当前的使用政策有效，允许符合SUN文件的存储引擎供应商接入MySQL数据库。<br>　　<br>　　2、不主张承诺。<br>　　作为版权持有人，甲骨文将改变SUN的现行政策，同时不得声称或威胁任何人，存储引擎第三方供应商必须基于GPL发布的，因为他们已经实行了应用编程接口有效，作为MySQL的可插入式存储引擎架构的一部分。商业许可无需通过Oracle请求。甲骨文将复制此承诺到与SUN有商业许可关系的存储供应商的合同当中。<br>　　<br>　　3、许可证的承诺。<br>　　当他们目前MySQL的OEM协议终止后，甲骨文将提供这些存储供应商与SUN相同的许可条件，不超过2014年。甲骨文将复制此承诺到与SUN有商业许可关系的存储供应商的合同当中。<br>　　<br>　　4、承诺在今后加强MySQL基于GPL发布。<br>　　MySQL后续版本，包括版本6，均将通过GPL发布。甲骨文公司不会在没有发布社区加强版的同时发布一个新的企业加强版。甲骨文公司将继续把MySQL社区版的所有版本的源代码公开，将免费提供。<br>　　<br>　　5、支持并不是强制性的 。<br>　　MySQL.客户不会被要求从甲骨文获得支持服务作为购买商业许可的条件。<br>　　<br>　　6、 增加对MySQL的研发支出 。 <br>　　<br>　　甲骨文承诺提供可用于MySQL持续发展的适当资金（GPL的版本和商业版本）。 在未来3年，每年，甲骨文将在MySQL的研发上投入比SUN的最近财年（2400万美元）还要多，在交易完成之前。<br>　　<br>　　7、成立MySQL客户顾问委员会 。<br>　　不迟于6个月后的一周年之际，甲骨文将创建并资助一个客户咨询委员会，包括最终用户和嵌入式客户提供指导和反馈，作为MySQL的发展优先事项，以及其他MySQL客户的重要问题。<br>　　<br>　　8、成立MySQL存储引擎供应商咨询委员会 。<br>　　不迟于6个月后的一周年之际，甲骨文将创建并资助一个存储引擎供应商咨询委员会，提供指导和反馈，作为MySQL的发展优先事项，以及其他MySQL存储引擎供应商的重要问题。<br>　　<br>　　9、 &nbsp;MySQL参考手册 。 <br>　　对MySQL参考手册，甲骨文公司将继续保持更新和免费下载，质量保持与SUN所提供的一致。<br>　　<br>　　10、 &nbsp;客户支持服务将继续 。<br>　　甲骨文将确保付费的最终用户和嵌入式客户提供MySQL的支持服务，根据客户的喜好更新订阅。<br>　　<br>　　这些承诺在全球范围内，并持续到交易完成五周年','','oschina','小王','oschina',0,'2009-12-16 10:24:53',2,1,1);
INSERT INTO `news` VALUES (31,4,12,0,'甲骨文回应欧盟质疑 承诺加强MySQL业务','甲骨文周一承诺，将会继续加强MySQL业务，并进一步以开源方式发布更新，希望借此打消欧盟委员会对该公司收购Sun的顾虑。<br><br>　　全球最大数据库厂商甲骨文今年早些时候斥资74亿美元收购了Sun，但欧盟委员会目前正在对该交易进行审查。Sun的主要业务是服务器和Java，该公司同时拥有开源数据库软件MySQL。<br><br>　　欧盟委员会此前曾表示，由于存在竞争关系，因此甲骨文收购Sun后会对免费开源软件MySQL的发展不利。甲骨文则表示，MySQL去年在欧洲的营业收入仅为1700万欧元(约合2487万美元)，无论从收入规模还是从产品档次来看，MySQL都不会对甲骨文的大型企业数据库构成威胁。<br><br>　　甲骨文周一共做出了10项承诺，包括对外公布使用MySQL所需的编程细节，另外，在第三方开发者使用这些细节信息与MySQL进行交互时，甲骨文会放弃相应的版权主张。','','ｃｎｂｅｔａ','小王','ｃｎｂｅｔａ',0,'2009-12-16 10:25:23',1,1,1);
INSERT INTO `news` VALUES (32,1,2,1,'开源Android将干掉Windows Mobile五大原因','新闻分析：微软的Windows Mobile操作系统可能是Android崛起最容易攻击的对象，在过去的一年中，Android的市场份额稳步上升，伴随摩托罗拉采用Android操作系统的Droid手机热卖，Android的发展趋势越来越被看好，这意味着Windows Mobile在推出其7.0版本前很可能被挤出移动设备领域，而微软此前曾透露Windows Mobile 7要等到明天某个时候才会发布。<br><br>　　自采用Android操作系统的摩托罗拉Droid从11月5日上市以来，很多媒体竞相将其与苹果的iPhone进行对比，运营商Verizon更是启动了一系列的咄咄逼人的广告强调Droid的某些功能，如同时运行多个应用程序 ?? 这正是iPhone目前缺少的功能。<br><br>　　但苹果可能不是Android影响最严重的公司，Google将目标锁定的也不是苹果，而是微软。微软目前最新的Windows Mobile操作系统是6.5版，下一个版本Windows Mobile 7要等到2010年某个时候才会发布，Android目前要狙击的目标就是Windows Mobile 6.5，与苹果和其它公司不一样，微软依靠它的操作系统和附加软件捆绑到众多手机厂商和运营商的设备中，进而占据了大部分移动操作系统市场，很长一段时间内都是微软的天下，现在终于看到一个强劲的对手上场了。<br><br>　　Android的市场份额正在快速上升<br><br>　　目前诺基亚使用最多的Symbian操作系统也被证明不是Android的对手，其市场份额从2008年10月的59%下跌到2009年10月27%，表面上看起来对于微软是个好消息，但实际上据AdMob 最近的调查数据显示，Windows Mobile的市场份额也下降了70%左右。<br><br>　　微软已经意识到了这个问题，早今年9月24日举行的创业投资峰会上，微软CEO史蒂夫鲍尔默承认公司搞砸了Windows Mobile，同时公开表示Windows Mobile 7已经开始启动，微软在10月6日发布了Windows Mobile 6.5，包括扩展了触摸等功能，但这应该是微软在发布Windows Mobile 7之前不得已的一个权宜之计。<br><br>　　理论上说Windows Mobile 6.5的发布应该阻止对微软移动市场份额的侵蚀，微软还打算用Windows Mobile 7与iPhone和黑莓展开一场恶斗，但现在又多出一个Android，据AdMob的数据显示，在过去的一年里，Android的市场份额至少翻了1000%。<br><br>　　这一增长势头势必会吸引更多制造商拥抱Android，之前微软曾宣布到2010年底，将会有13家手机厂商采用Windows Mobile 6.5，包括LG，HTC和Sony。Android也不示弱，Google移动平台高级主管Andy Rubin早先估计到本年年底将会有18-20种设备使用Android，包括摩托罗拉，戴尔，宏基，诺基亚等厂家都开始尝试Android。<br><br>　　Android将会是应用程序的世界<br><br>　　微软原本计划在10月启动的Windows Marketplace中内置600个应用程序，但最终却只有250个都不到，虽然这一数字在两个月后扩大到了接近800，目前约有1000家独立ISV注册开发移动应用程序。<br><br>　　相对于其它巨头的动作，移动应用程序可能成为Windows Mobile的致命一击，即使如此，除了苹果的App Store有8万个应用程序外，微软的800个应用程序也对RIM的BlackBerry App World(1000多个应用程序)和Palm的应用程序商店(350多个)造成了冲击。<br><br>　　所有这些竞争设备都集成了它们自己的操作系统，Google Android是一个开源例外，正是由于这个原因它总是被抨击，如果不是手机制造商有意回避操作系统，Android Marketplace应该已经有2000多个应用程序了，如果Android保持其增长势头，Android应用程序将会呈大面积爆发。如果真的那样，更多手机厂家不得不转向Android，将会进一步挤压Windows Mobile的市场份额。<br><br>　　缺乏其它操作系统竞争对手<br><br>　　10月21日，Symbian基金会宣布Symbian操作系统正式开源，该平台的微内核EKA2(Epco Kernel Architecture 2)比原计划提前了9个月发布，包括遵循Eclipse公共许可的SDK。<br><br>　　正如前文提到的，Symbian操作系统的市场份额在过去的一年里和Windows Mobile一样大幅下降，Symbian最终不得不选择和Android一样走开源道路，但在开源道路上Android显然早已驾轻就熟。<br><br>　　硬件配对<br><br>　　Google Android已经和摩托罗拉Droid和HTC Droid Eris搭档成功，之前曾有权威人士将它们作为iPhone杀手称呼。在摩托罗拉Droid上市的头一周，就卖出了25万部，但还是距iPhone 3GS上市第一周热销的1600万部的记录相差甚远，不过许多分析师认为Droid的表现让人印象深刻。<br><br>　　而Windows Mobile就缺乏这样热卖的设备了，最近倒是热传微软将和Verizon联手在2010年推出一款智能手机。但除非这款手机变成实物，否则谁也不能保证其成为事实，目前有关这个合作项目Pink的消息仍然被封锁，而Android在强大的广告攻势下，每一个星期都会增加人们的心理份额，如果谷歌赶在微软之前发布自有品牌的手机，那这个心理份额将会加速上升。<br><br>　　Windows Mobile 7仍然是个未知数<br><br>　　微软已经承诺Windows Mobile 7将会带来实质性的更新，如果这次升级再给公众留下失败的印象，微软在移动领域将会处于难以维持的弱势地位，这将会给Android足够的机会赢得智能手机用户的青睐。','','Linux论坛','大王','Linux论坛',0,'2010-01-31 10:48:56',1,1,1);

#
# Table structure for table newsadmin
#

DROP TABLE IF EXISTS `newsadmin`;
CREATE TABLE `newsadmin` (
  `userName` varchar(20) NOT NULL default '',
  `passWd` varchar(20) NOT NULL default '',
  `purview` int(11) NOT NULL default '0',
  `lastLogin` datetime default NULL,
  `lastLoginIp` varchar(20) default NULL,
  PRIMARY KEY  (`userName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
INSERT INTO `newsadmin` VALUES ('admin','admin',0,'2010-01-31 10:44:34','0:0:0:0:0:0:0:1');

#
# Table structure for table newsclass
#

DROP TABLE IF EXISTS `newsclass`;
CREATE TABLE `newsclass` (
  `classId` int(11) NOT NULL default '0',
  `content` varchar(20) NOT NULL default '',
  PRIMARY KEY  (`classId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
INSERT INTO `newsclass` VALUES (1,'linux入门');
INSERT INTO `newsclass` VALUES (2,'服务器配置');
INSERT INTO `newsclass` VALUES (3,'应用开发');
INSERT INTO `newsclass` VALUES (4,'数据库应用');
INSERT INTO `newsclass` VALUES (5,'系统安全');
INSERT INTO `newsclass` VALUES (6,'程序设计');

#
# Table structure for table newscommon
#

DROP TABLE IF EXISTS `newscommon`;
CREATE TABLE `newscommon` (
  `counter` int(11) NOT NULL default '0',
  `ip` varchar(20) NOT NULL default ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
INSERT INTO `newscommon` VALUES (2,'0:0:0:0:0:0:0:1');

#
# Table structure for table newskind
#

DROP TABLE IF EXISTS `newskind`;
CREATE TABLE `newskind` (
  `kindId` int(11) NOT NULL auto_increment,
  `content` varchar(255) NOT NULL default '',
  `classId` int(11) NOT NULL default '0',
  PRIMARY KEY  (`kindId`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
INSERT INTO `newskind` VALUES (1,'linux教程',1);
INSERT INTO `newskind` VALUES (2,'学习笔记',1);
INSERT INTO `newskind` VALUES (3,'windows',2);
INSERT INTO `newskind` VALUES (4,'linux',2);
INSERT INTO `newskind` VALUES (5,'unix',2);
INSERT INTO `newskind` VALUES (6,'jsp&servlet',3);
INSERT INTO `newskind` VALUES (7,'java',3);
INSERT INTO `newskind` VALUES (8,'xml',3);
INSERT INTO `newskind` VALUES (9,'delphi',3);
INSERT INTO `newskind` VALUES (10,'.net',3);
INSERT INTO `newskind` VALUES (11,'oracle',4);
INSERT INTO `newskind` VALUES (12,'mysql',4);
INSERT INTO `newskind` VALUES (13,'mssql',4);
INSERT INTO `newskind` VALUES (14,'安全防护',5);
INSERT INTO `newskind` VALUES (15,'漏洞攻击',5);
INSERT INTO `newskind` VALUES (16,'C++',6);
INSERT INTO `newskind` VALUES (18,'Java',6);
INSERT INTO `newskind` VALUES (19,'linux安全',1);

#
# Table structure for table newspopedom
#

DROP TABLE IF EXISTS `newspopedom`;
CREATE TABLE `newspopedom` (
  `gradeId` int(11) NOT NULL default '0',
  `grade` varchar(20) default NULL,
  PRIMARY KEY  (`gradeId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
INSERT INTO `newspopedom` VALUES (1,'普通会员');
INSERT INTO `newspopedom` VALUES (2,'银牌会员');
INSERT INTO `newspopedom` VALUES (3,'金牌会员');

#
# Table structure for table newsreply
#

DROP TABLE IF EXISTS `newsreply`;
CREATE TABLE `newsreply` (
  `replyId` int(11) NOT NULL auto_increment,
  `newsId` int(11) NOT NULL default '0',
  `user` varchar(20) default NULL,
  `content` varchar(100) default NULL,
  `replyTime` varchar(20) default NULL,
  PRIMARY KEY  (`replyId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Table structure for table newsusr
#

DROP TABLE IF EXISTS `newsusr`;
CREATE TABLE `newsusr` (
  `userName` varchar(20) NOT NULL default '',
  `passWd` varchar(20) NOT NULL default '',
  `sex` int(11) default NULL,
  `question` varchar(255) default NULL,
  `answer` varchar(255) default NULL,
  `emailAddr` varchar(50) default NULL,
  `qq` varchar(10) default NULL,
  `http` varchar(30) default NULL,
  `purview` int(11) default '1',
  `regTime` varchar(20) default NULL,
  PRIMARY KEY  (`userName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
INSERT INTO `newsusr` VALUES ('eggpeijun','123456',1,'11111','1111','eee@163.com','eee','eee',1,'2009-12-15 15:23:00');

#
# Table structure for table noteadmin
#

DROP TABLE IF EXISTS `noteadmin`;
CREATE TABLE `noteadmin` (
  `adminName` char(20) NOT NULL default '',
  `adminPasswd` char(20) NOT NULL default '',
  PRIMARY KEY  (`adminName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
INSERT INTO `noteadmin` VALUES ('ntsky123','ntsky123');

#
# Table structure for table noteguest
#

DROP TABLE IF EXISTS `noteguest`;
CREATE TABLE `noteguest` (
  `noteId` int(11) NOT NULL auto_increment,
  `userName` varchar(20) NOT NULL default '',
  `sex` int(11) NOT NULL default '0',
  `email` varchar(50) default NULL,
  `qq` varchar(9) default NULL,
  `url` varchar(50) default NULL,
  `headTitle` text NOT NULL,
  `content` text NOT NULL,
  `image` text,
  `noteTime` varchar(20) default NULL,
  PRIMARY KEY  (`noteId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Table structure for table notereply
#

DROP TABLE IF EXISTS `notereply`;
CREATE TABLE `notereply` (
  `replyId` int(11) NOT NULL auto_increment,
  `noteId` int(11) NOT NULL default '0',
  `content` text,
  `replyTime` varchar(20) default NULL,
  PRIMARY KEY  (`replyId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
