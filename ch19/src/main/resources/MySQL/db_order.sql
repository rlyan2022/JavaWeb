# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: db_order
# ------------------------------------------------------
# Server version 5.0.67-community-nt

DROP DATABASE IF EXISTS `db_order`;
CREATE DATABASE `db_order` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_order`;

#
# Table structure for table aboutme
#

DROP TABLE IF EXISTS `aboutme`;
CREATE TABLE `aboutme` (
  `ID` int(11) NOT NULL auto_increment,
  `TITLE` varchar(50) NOT NULL default '',
  `CONTENT` text NOT NULL,
  `SEQUENCE` tinyint(4) default NULL COMMENT '排序',
  `IS_DISPLAY` char(1) default NULL,
  `CREATE_TIME` datetime default NULL COMMENT '创建时间',
  `RECORD_MAN` varchar(20) default NULL,
  `EDITOR` int(11) default NULL,
  `EDITTIME` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_ABOUTME_EDITOR` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
INSERT INTO `aboutme` VALUES (4,'天天使用网上订餐系统订餐','有了天鼎网上订餐系统，我天天使用网上订餐系统订餐',2,'1','2009-10-14 16:19:48','admin',NULL,NULL);
INSERT INTO `aboutme` VALUES (5,'购买系统方式','<p>联系电话：020-87811132&nbsp;&nbsp;&nbsp;&nbsp; 18925050569</p>\r\n<p>QQ1：1273708322</p>\r\n<p>QQ2：861516626</p>\r\n<p>公司官网：<a href=\"http://www.tienting.com\">www.tienting.com</a></p>\r\n<p>请与我们联系！因为专业！所以信赖！</p>\r\n',1,'1','2009-10-14 16:50:17','admin',NULL,NULL);

#
# Table structure for table articles
#

DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
  `ID` int(10) NOT NULL auto_increment COMMENT '主键',
  `TITLE` varchar(150) NOT NULL default '' COMMENT '标题',
  `CONTENTS` text NOT NULL COMMENT '内容',
  `EDITTIME` varchar(50) NOT NULL default '' COMMENT '编辑时间',
  `EDITOR` int(10) NOT NULL default '0' COMMENT '编辑人',
  PRIMARY KEY  (`ID`),
  KEY `FK_ARTICLES_EDITOR_ID` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `articles` VALUES (1,'test','<p>test222222222222</p>','2009-06-24 10:42:50',1);
INSERT INTO `articles` VALUES (2,'111111111111','<p>111111111111111111111111111</p>','2009-08-03 17:03:39',1);
INSERT INTO `articles` VALUES (3,'二二','<p>韦尔奇</p>','2009-08-18 18:37:13',1);

#
# Table structure for table assignman
#

DROP TABLE IF EXISTS `assignman`;
CREATE TABLE `assignman` (
  `ID` int(10) NOT NULL auto_increment,
  `NAME` varchar(20) default NULL COMMENT '系统用户名称',
  `PHONE` varchar(30) default NULL COMMENT '联系电话',
  `SERIAL` tinyint(4) default NULL COMMENT '排序',
  `STATUS` char(1) default NULL COMMENT '状态',
  `EDITOR` int(10) default NULL COMMENT '编辑人',
  `EDITTIME` datetime default NULL COMMENT '编辑时间',
  `CODE` varchar(20) default NULL,
  `SEX` char(1) default NULL COMMENT 'M=男',
  `PHOTO` varchar(50) default NULL,
  `EMAIL` varchar(50) default NULL,
  `REMARK` text,
  PRIMARY KEY  (`ID`),
  KEY `FK_ASSIGNMAN_EDITOR_USERS` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
INSERT INTO `assignman` VALUES (8,'小黄','321456',1,'1',1,'2009-09-18 22:21:45','001','1',NULL,'','大法官法帝国');
INSERT INTO `assignman` VALUES (9,'小李','87818778',2,'1',NULL,'2009-09-18 22:21:48','002','1',NULL,'','大法师根深蒂固');
INSERT INTO `assignman` VALUES (10,'小吴','',3,'1',NULL,'2009-10-14 17:13:15','003','1',NULL,'','啊啊啊啊啊大家富士康');
INSERT INTO `assignman` VALUES (11,'小王','15914381568',1,'2',NULL,'2009-10-14 17:13:30','001','1',NULL,'kander@163.com','吃苦耐劳');

#
# Table structure for table authorities
#

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `ID` int(10) NOT NULL auto_increment COMMENT '主键',
  `NAME` varchar(40) NOT NULL default '' COMMENT '权限标志',
  `DISPLAY_NAME` varchar(40) NOT NULL default '' COMMENT '权限名字',
  `TYPE` varchar(20) default NULL COMMENT '权限类型',
  `PARENT_ID` int(10) default NULL COMMENT '父ID',
  `LAYER_CODE` varchar(20) default NULL COMMENT '层级代码',
  `URL` varchar(255) default NULL COMMENT 'url',
  PRIMARY KEY  (`ID`),
  KEY `FK_AUTHORITIES_PARENT_ID_ID` (`PARENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
INSERT INTO `authorities` VALUES (1,'FRAME','系统后台主页面','url',NULL,'0000000000','/frame*');
INSERT INTO `authorities` VALUES (2,'USER_MANAGE','用户管理','url',28,'0201000000','/security/user*');
INSERT INTO `authorities` VALUES (4,'ROLE_MANAGE','权限管理','url',28,'0202000000','/security/role*');
INSERT INTO `authorities` VALUES (13,'AUTHORITY','安全管理_1','url',28,'0203000000','/security/authority*');
INSERT INTO `authorities` VALUES (23,'USER_DELETE','删除用户','url',2,'0201030000','/security/user!delete.action');
INSERT INTO `authorities` VALUES (27,'ROLE_DELETE','删除角色(权限)','url',4,'0202030000','/security/role!delete.action');
INSERT INTO `authorities` VALUES (28,'SYSTEM_MANAGE','系统管理','',NULL,'0200000000','');
INSERT INTO `authorities` VALUES (29,'RESTAURANT_MANAGE','餐店信息','url',32,'0101000000','/restaurant/restaurant*');
INSERT INTO `authorities` VALUES (30,'ORDER_MANAGE','订单管理','',NULL,'0400000000','');
INSERT INTO `authorities` VALUES (31,'POSFORMPRINT','今日订单','url',30,'0401000000','/orderform/orderformnote*');
INSERT INTO `authorities` VALUES (32,'BASEINFO_MANAGE','基本信息管理','',NULL,'0100000000','');
INSERT INTO `authorities` VALUES (33,'MENU_MANAGE','菜单管理','',NULL,'0300000000','');
INSERT INTO `authorities` VALUES (34,'STATISTICS_MANAGE','统计管理','',NULL,'0500000000','');
INSERT INTO `authorities` VALUES (35,'HEALTHDRINK_MANAGE','健康饮食','',NULL,'0600000000','');
INSERT INTO `authorities` VALUES (36,'MEMBER_MANAGE','会员管理','',NULL,'0700000000','');
INSERT INTO `authorities` VALUES (41,'ABOUTME_MANAGE','餐店简介','url',32,'0102000000','/restaurant/aboutme*');
INSERT INTO `authorities` VALUES (46,'NOTICE_MANAGE','餐店公告','url',32,'0103000000','/notice/notice*');
INSERT INTO `authorities` VALUES (50,'EATGUIDE_MANAGE','订餐指南','url',32,'0104000000','/eatguide/eatguide*');
INSERT INTO `authorities` VALUES (54,'USER_ADD','增加用户','url',2,'0201010000','/security/user!input.action');
INSERT INTO `authorities` VALUES (55,'USER_UPDATE','修改用户','url',2,'0201020000','/security/user!input.action');
INSERT INTO `authorities` VALUES (56,'FETCHTIME_MANAGE','送餐时间','url',32,'0105000000','/restaurant/fetchtime*');
INSERT INTO `authorities` VALUES (57,'ROLE_ADD','增加角色(权限)','url',4,'0202010000','/security/role!input.action');
INSERT INTO `authorities` VALUES (58,'ROLE_UPDATE','修改角色(权限)','url',4,'0202020000','/security/role!input.action');
INSERT INTO `authorities` VALUES (59,'ASSIGNMAN_MANAGE','送餐员管理','url',30,'0402000000','/restaurant/assignman*');
INSERT INTO `authorities` VALUES (60,'MENUTYPE_MANAGE','菜单类型','url',33,'0301000000','/menu/menutype*');
INSERT INTO `authorities` VALUES (61,'MENU_INFO','菜单','url',33,'0302000000','/menu/submenu*');
INSERT INTO `authorities` VALUES (62,'MENUEVALUATE_MANAGE','菜单评价','url',33,'0303000000','/menu/menuevaluate*');
INSERT INTO `authorities` VALUES (63,'MENU_SALE','今日菜单销售情况','url',30,'0304000000','/frame!viewDefault.action');
INSERT INTO `authorities` VALUES (64,'SALESTATISTICS','销售统计','url',34,'0501000000','/statistics/salestatistics*');
INSERT INTO `authorities` VALUES (65,'HEALTHDRINKTYPE','饮食类别','url',35,'0601000000','/healthdrink/healthdrinktype*');
INSERT INTO `authorities` VALUES (66,'HEALTHDRINK','饮食文章','url',35,'0602000000','/healthdrink/healthdrink*');
INSERT INTO `authorities` VALUES (67,'MEMBER','会员信息','url',36,'0701000000','/member/member_operate*');
INSERT INTO `authorities` VALUES (68,'INTEGRALSET','积分兑换设置','url',36,'0702000000','/integral/integralset*');
INSERT INTO `authorities` VALUES (70,'LEAVEWORD','留言管理','url',36,'0704000000','/member/leaveword*');
INSERT INTO `authorities` VALUES (71,'ISLEGAL','安全管理_2','url',28,'0204000000','/restaurant/islegal*');

#
# Table structure for table eatguide
#

DROP TABLE IF EXISTS `eatguide`;
CREATE TABLE `eatguide` (
  `ID` int(11) NOT NULL auto_increment,
  `TITLE` varchar(100) NOT NULL default '',
  `EXPERT` varchar(100) default NULL,
  `SOURCE` varchar(100) default NULL,
  `SUMMARY` varchar(255) default NULL COMMENT '摘要',
  `CONTENT` text NOT NULL,
  `RECORD_TIME` datetime default NULL COMMENT '发布时间',
  `RECORD_MAN` varchar(20) default NULL,
  `IS_DISPLAY` char(1) default NULL COMMENT '1=显示',
  `EDITOR` int(11) default NULL,
  `EDITTIME` datetime default NULL,
  `IS_TOP` char(1) default NULL COMMENT '1=置顶',
  PRIMARY KEY  (`ID`),
  KEY `FK_EATGUIDE_EDITOR` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
INSERT INTO `eatguide` VALUES (6,'订餐流程','','','订餐流程','<p>系统购买方式</p>\r\n<p>联系电话：020-87811132&nbsp;&nbsp;&nbsp;&nbsp; 18925050569</p>\r\n<p>QQ1：1273708322</p>\r\n<p>QQ2：861516626</p>\r\n<p>公司官网：<a href=\"http://www.tienting.com/\">www.tienting.com</a></p>\r\n<p>请与我们联系！因为专业！所以信赖！</p>\r\n','2009-10-14 16:53:33','admin','1',NULL,'2009-10-15 23:41:36','2');

#
# Table structure for table fetchtime
#

DROP TABLE IF EXISTS `fetchtime`;
CREATE TABLE `fetchtime` (
  `ID` int(11) NOT NULL auto_increment,
  `FETCHTIME` varchar(10) NOT NULL default '',
  `EDITOR` int(11) default NULL,
  `EDITTIME` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_fetchtime_editor` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
INSERT INTO `fetchtime` VALUES (16,'23:55',7,'2009-10-14 16:58:09');
INSERT INTO `fetchtime` VALUES (18,'11:00',7,'2009-10-14 16:59:07');
INSERT INTO `fetchtime` VALUES (19,'12:00',7,'2009-10-14 16:59:13');
INSERT INTO `fetchtime` VALUES (20,'13:00',7,'2009-10-14 16:59:18');
INSERT INTO `fetchtime` VALUES (24,'10:30',1,'2009-10-17 16:50:19');
INSERT INTO `fetchtime` VALUES (25,'10:40',1,'2009-10-17 16:50:40');
INSERT INTO `fetchtime` VALUES (26,'10:50',1,'2009-10-17 16:50:45');
INSERT INTO `fetchtime` VALUES (27,'11:10',1,'2009-10-17 16:50:53');
INSERT INTO `fetchtime` VALUES (28,'11:20',1,'2009-10-17 16:51:00');
INSERT INTO `fetchtime` VALUES (29,'11:30',1,'2009-10-17 16:51:11');
INSERT INTO `fetchtime` VALUES (30,'11:40',1,'2009-10-17 16:51:39');
INSERT INTO `fetchtime` VALUES (31,'12:10',1,'2009-10-17 16:51:45');
INSERT INTO `fetchtime` VALUES (32,'12:30',1,'2009-10-17 16:51:51');
INSERT INTO `fetchtime` VALUES (33,'16:30',1,'2009-10-17 16:52:04');
INSERT INTO `fetchtime` VALUES (34,'16:45',1,'2009-10-17 16:52:16');
INSERT INTO `fetchtime` VALUES (35,'17:00',1,'2009-10-17 16:52:21');
INSERT INTO `fetchtime` VALUES (36,'17:15',1,'2009-10-17 16:52:29');
INSERT INTO `fetchtime` VALUES (37,'17:30',1,'2009-10-17 16:52:36');
INSERT INTO `fetchtime` VALUES (38,'17:45',1,'2009-10-17 16:52:49');
INSERT INTO `fetchtime` VALUES (39,'18:00',1,'2009-10-17 16:52:54');
INSERT INTO `fetchtime` VALUES (40,'18:20',1,'2009-10-17 16:53:10');

#
# Table structure for table healthdrink
#

DROP TABLE IF EXISTS `healthdrink`;
CREATE TABLE `healthdrink` (
  `ID` int(10) NOT NULL auto_increment COMMENT '主键',
  `HEALTHDRINKTYPE` int(10) default NULL COMMENT '新闻类型',
  `TITLE` varchar(150) NOT NULL default '' COMMENT '标题',
  `AUTH` varchar(30) default NULL COMMENT '作者',
  `CONTENT` text,
  `IS_TOP` char(1) default '2' COMMENT '是否置顶',
  `TITLE_ICO` varchar(60) default NULL COMMENT '图片',
  `SOURCE` varchar(20) default NULL COMMENT '来源',
  `ISSUE_MAN` varchar(20) default NULL COMMENT '发布人',
  `ISSUE_TIME` datetime default NULL COMMENT '发布时间',
  `IS_DISPLAY` char(1) default '2' COMMENT '是否隐藏',
  `EDITOR` int(10) default NULL COMMENT '编辑人',
  `EDITTIME` datetime default NULL COMMENT '编辑时间',
  PRIMARY KEY  (`ID`),
  KEY `FK_NEWS_EDITOR` (`EDITOR`),
  KEY `FK_healthdrink_HEALTHDRINKTYPE` (`HEALTHDRINKTYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
INSERT INTO `healthdrink` VALUES (26,5,'越吃越瘦的健康夜宵','','当今现代人由于工作强度比较大，往往会熬夜甚至通宵为了完成某些工作或者任务，如果吃晚饭与睡觉的时间相隔超过四小时，可以选择十至十一点钟左右吃点宵夜，因为有至少一小时让食物消化，减少积聚脂肪的机会。<p>　　此外，建议宵夜食物宜选择一含糖质或纤维素丰富的食物，因为含糖质食物可以令你容易入睡，而高纤食物可以令你有饱腹感又吃不胖!以下人气美食为大家推荐三款瘦身宵夜。</p>\r\n<p><strong>　　鱼片粥1碗/160kcal</strong></p>\r\n<p>　　在家晚饭后可以切4片草鱼或是其他淡水鱼类的鱼片，与粥一起煮，到11点左右即可开锅吃了。</p>\r\n<p>　　由于这草鱼是低脂鱼，而且含不饱和脂肪，低胆固醇，对心脏有益。且4片鱼片的热量只有80kcal，比起4片肥牛肉(200kcal)低的多。此外，五谷类含质，糖对脑部有安定作用，所以喝粥还有令人放松，帮助入睡的功效。</p>\r\n<p><strong>　　高纤麦皮加脱脂奶三汤匙麦片+半杯脱脂奶/170kcal</strong></p>\r\n<p>　　这道宵夜做法非常的简单，只要冲冲泡泡即可，不过人气美食还是要提醒大家，由于市面很好多即食麦片含糖分高，所以选择时要小心，尽量选用无糖、无奶麦片，而麦片中除了有丰富的营养外，还有2克左右的纤维，很容易让人有饱腹感，可以减轻人贪吃的食欲。</p>\r\n<p>　　此外，牛奶内含有一种称Serotonin的胺基酸，可令脑部呈休息、放松状态，令你产生睡意。</p>\r\n<p><strong>　　镇静香蕉皇帝蕉一只/50kcal</strong></p>\r\n<p>　　一般人都误以为吃香蕉会使人肥胖，其实一只香蕉只含100kcal，比食饭少一半，而纤维比饭多一至两倍。</p>\r\n<p>　　但营养师同时指出，晚上夜宵吃一根香蕉还是太多，建议吃一根皇帝蕉就好，且香蕉内含有钾质;钾质是矿物质，没热量，但却有维持神经运输正常，令心跳正常的功效，所以睡前吃根皇帝蕉可以帮您“心境平和”。</p>\r\n','2',NULL,'','admin','2009-09-12 18:14:53','1',NULL,NULL);
INSERT INTO `healthdrink` VALUES (27,5,'吸光你脂肪的食物','','除了节食减肥外，食物真的不能多碰吗。其实有些食物是可以帮我们消耗脂肪的，现在为大家寻来可以帮助减少脂肪吸收的天然食物，让你穿衣服更有型起来！<p>　　<strong>1.冻豆腐</strong></p>\r\n<p>　　豆腐经过冷冻，能产生一种酸性物质，这种酸性物质能破坏人体的脂肪，如能经常吃冻豆腐，有利于脂肪排泄，使体内积蓄的脂肪不断减少，达到减肥的目的。冻豆腐具有孔隙多、营养丰富、热量少等特点，不会造成明显的饥饿感。</p>\r\n<p>　　<strong>2.竹笋</strong></p>\r\n<p>　　竹笋具有低脂肪、低糖、多纤维的特点，食用竹笋不仅能促进肠道蠕动，帮助消化，去积食，防便秘，并有预防大肠癌的功效。竹笋含脂肪、淀粉很少，属天然低脂、低热量食品，是肥胖者减肥的佳品.</p>\r\n<p>　　<strong>3.腌渍类蔬菜</strong></p>\r\n<p>　　植物性脂肪在制作过程被分解了，但水肿型肥胖者不能吃，以免体液滞留。</p>\r\n<p>　　<strong>4.绿豆芽</strong></p>\r\n<p>　　有清除血管壁中胆固醇和脂肪的堆积、防止心血管病变的作用。经常食用绿豆芽可清热解毒，利尿除湿，解酒毒热毒。多嗜烟酒肥腻者，如果常吃绿豆芽，就可以起到清肠胃、解热毒、洁牙齿的作用，同时可防止脂肪在皮下形成。</p>\r\n<p>　　<strong>5.木瓜</strong></p>\r\n<p>　　木瓜中的木瓜蛋白酶，可将脂肪分解为脂肪酸；木瓜中含有一种酵素，能消化蛋白质，有利于人体对食物进行消化和吸收，故有健脾消食之功。同时还可治水肿、脚气病，且可改善关节。</p>\r\n<p>　　<strong>6.菠萝</strong></p>\r\n<p>　　菠萝果实营养丰富，含有人体必需的维生素C、胡萝卜素、硫胺素、尼克酸等维生素。以及易为人体吸收的钙、铁、镁等微量元素。菠萝果汁、果皮及茎所含有的蛋白酶，能帮助蛋白质的消化，能分解鱼、肉，适合吃过大餐后食用。</p>\r\n<p>　　<strong>7.陈皮</strong></p>\r\n<p>　　陈皮含有挥发油、橙皮甙、维生素B、C等成分，它所含的挥发油对胃肠道有温和刺激作用，可促进消化液的分泌帮助消化，排除胃气，还可减少腹部脂肪堆积。</p>\r\n<p>　　<strong>8.乌贼</strong></p>\r\n<p>　　乌贼脂肪含量很低，蛋白质含量高，具有较高的营养价值，是减肥时的好食物。</p>\r\n<p>　　<strong>9.薏仁</strong></p>\r\n<p>　　性味甘淡微寒，有利水消肿、健脾去湿、舒筋除痹、清热排脓等功效。所以对水肿型肥胖有效。</p>\r\n<p>　　<strong>10黄瓜</strong></p>\r\n<p>　　黄瓜含有维生素C、维生素B族及许多微量矿物质，它所含的营养成分丰富，生吃口感清脆爽口。从营养学角度出发，黄瓜皮所含营养素丰富，应当保留生吃。但了预防农药残留对人体的伤害，黄瓜应先在盐水中泡15-20分钟再洗净生食。用盐水泡黄瓜时切勿掐头去根，要保持黄瓜的完整，以免营养素在泡的过程中从切面流失。另外，凉拌菜应现做现吃，不要做好后长时间放置，这样也会促使维生素损失。</p>\r\n<p>　　<strong>11.西红柿</strong></p>\r\n<p>　　西红柿中维生素A较丰富，维生素A对视力保护及皮肤晒后修复有好处。凉拌西红柿不撒糖更好，否则甜味可能影响食欲。肥胖者、糖尿病人、高血压病人都不宜吃被称为“雪漫火焰山”的加糖凉拌西红柿。</p>\r\n<p>　　<strong>12.柿子椒或尖椒</strong></p>\r\n<p>　　辣椒是所有蔬菜中维生素C含量最丰富的食物。维生素C可提高人体免疫力，帮助抵御各种疾病。夏天人们容易热伤风，而且经常外出，接触外界环境多了，感染病毒的机会也增多，所以需要提高自身免疫力。</p>\r\n<p>　　<strong>13.芹菜</strong></p>\r\n<p>　　芹菜富含粗纤维、钾、维生素B2、维生素(也叫尼克酸)等成分。夏季天气炎热，人们易上火，造成大便干燥。同时，天热时人们失水多，容易造成钠钾失衡。芹菜可帮助人们润肠通便，调节钠钾平衡。维生素对人的皮肤、神经系统和食欲都有影响，如果人体缺乏维生素B2，就容易引起疲劳乏力和口腔溃疡。芹菜叶所含的营养素比茎多，弃之可惜，可焯一下凉拌吃。</p>\r\n<p>　　<strong>14.大白菜</strong></p>\r\n<p>　　大白菜中膳食纤维和维生素A含量高，阳光刺眼的夏季多吃新鲜的大白菜，对护眼、养颜有益。不过，不要吃储存太久、营养素损失过多的大白菜。另外，消化性溃疡者也不宜生食大白菜，以免粗纤维的剐蹭刺激胃肠道创面。</p>\r\n<p>　　<strong>15.茄子</strong></p>\r\n<p>　　茄子所含硒较其他蔬菜要高。将鲜嫩的圆茄子削皮切成丝，加适量盐和香油凉拌。硒具有抗氧化作用，能保持人体细胞的正常功能，提高免疫力，对人体有防病、抗衰老作用，通过体内代谢，还可以发挥抗癌作用。</p>\r\n','2',NULL,'','admin','2009-09-12 18:16:20','1',NULL,NULL);
INSERT INTO `healthdrink` VALUES (28,5,'7种最差的饮食方法','','<strong>7种最差的饮食方法</strong><p>　　许多饮食方法常常会导致我们体重增加甚至引起消化疾病，美国的一篇文章列举了以下7种最差的饮食方法。文章还告诉人们，最好的饮食方法是坐在餐桌前聚精会神地吃饭。</p>\r\n<p><strong>　　1.在厨房里吃东西</strong></p>\r\n<p>　　站在电冰箱或者炉子前一边吃饭一边准备饭菜常常会在无意中增加人们摄入的卡路里。同样地，面对剩菜，人们所摄入的卡路里也会增加许多。“当一个勤俭节约的妈妈在饭后清理饭桌的时候，她常常会想到：将这么多鸡块扔掉是一件多么可耻的事情啊。于是，她就开始吃这些鸡块了。”美国营养协会发言人邦妮说。</p>\r\n<p><strong>　　2.吃东西太过匆忙</strong></p>\r\n<p>　　当研究人员要求参与者迅速吃下一大盘意大利面时，他们发现这些人平均在9分钟之内摄入了646卡路里，而那些被要求慢慢享受的人则平均在29分钟之内摄入了579卡路里。此外，匆忙就餐还导致消化不良以及胃痛。</p>\r\n<p><strong>　　3.在工作时吃东西</strong></p>\r\n<p>　　从早上开会时的免费油炸圈饼到中间休息时的免费饮料等，整个工作时间似乎成了一个不断摄入卡路里的过程。事实上，办公室免费食品能够使人体摄入的卡路里迅速增加。同时，由于对免费饮食的诱惑难以抵御，人们在办公室里的食欲会大大增加。美国康奈尔大学2006年的一项研究发现，那些座位邻近办公室果盘的人往往会吃下许多糖果，但他们对自己究竟吃下多少却一无所知。</p>\r\n<p><strong>　　4.在昏暗中吃东西</strong></p>\r\n<p>　　美国某大学2002年的一项研究发现，那些热衷于暴饮暴食的人往往喜欢在灯光昏暗的环境中就餐。研究人员认为，在吃饭的时候，昏暗的灯光会减少人们的害羞感。</p>\r\n<p><strong>　　5.吃东西嚼得太少</strong></p>\r\n<p>　　大量的研究表明，充分咀嚼食物有助于消化并防止腹胀和胃痛，“当你未经充分咀嚼而吞下一大块食物时，这些食物就很难被充分分解。”邦妮说。因为，食物经过充分咀嚼过后与唾液中的消化酶接触的面积就会增加。那么，怎样才算得上是充分咀嚼呢？一般来说，嚼上25次使食物呈现糊状最好。</p>\r\n<p><strong>　　6.在餐馆里吃东西</strong></p>\r\n<p>　　大量的研究表明，经常性外出就餐与身体肥胖、脂肪增多以及其他身体指数增高有着密切的联系。美国孟菲斯大学的研究人员发现，那些每周外出就餐6次到13次的妇女平均每天多摄入290卡路里的能量。</p>\r\n<p><strong>　　7.在屏幕前吃东西</strong></p>\r\n<p>　　无论是在电脑还是电视机屏幕前，边吃东西，边长时间沉迷于网络或电视节目将大大增加无意识的饮食。一些主要研究机构的研究人员发现，看电视是导致肥胖的一个危险因素，对于青少年尤其如此。边吃东西，边看电视会带来双重危害：它会增加无意识的饮食并占用了那些用来进行消耗卡路里的活动时间。</p>\r\n','2',NULL,'','admin','2009-09-12 18:17:38','1',NULL,NULL);
INSERT INTO `healthdrink` VALUES (29,5,'午饭后喝杯酸奶助消化利健康','','早晚喝一杯牛奶已经成为很多人养成的好习惯。可是，午饭时或午饭后喝一杯酸奶，对健康也能起到重要的作用。<p>　　根据统计资料显示，我们每天早餐的热能供应占当日总热能需求的25%—30%，因此，早餐时喝一杯牛奶能够有效地补充热能；晚上临睡前喝一杯则有助于增加睡眠质量，让你进入深度熟睡状态，还能保证牛奶营养的充分吸收和消化。</p>\r\n<p>　　午餐时喝酸奶有什么好处呢？专家指出，酸奶中含有大量的乳酸、醋酸等有机酸，它们不仅赋予了酸奶清爽的酸味，还能帮助它形成细嫩的凝乳，从而抑制有害微生物的繁殖，同时，使肠道的碱性降低，酸性增加，促进胃肠蠕动和消化液的分泌。此外，随着酸奶的生产技术、生产工艺不断进步，一些乳品大品牌，已经将其酸奶产品中的益生菌由两种变成了4种，这样不仅酸奶的营养价值比同类产品有了明显提高，其帮助消化、抑制有害菌的作用也得到了更一步的加强。</p>\r\n','2',NULL,'','admin','2009-09-12 18:18:03','1',NULL,NULL);
INSERT INTO `healthdrink` VALUES (30,5,'健康提醒：煮鸡蛋5分钟最好','','煮鸡蛋看似简单，却不好把握火候，时间过短会使蛋黄不熟，时间过长会使鸡蛋变老不好吃。对此，北京大学第三医院运动医学研究所常翠青博士介绍，其实煮鸡蛋的最佳时机很好把握。凉水下锅，水开后算好5分钟，煮出来的鸡蛋既被杀死了有害致病菌，又能比较完整地保存营养素。<p>　　如果鸡蛋在沸水中煮超过10分钟，内部会发生一系列的化学变化。蛋白质结构变得更紧密，不容易与胃液中蛋白质消化酶接触，所以较难消化。蛋品中蛋白质含有较多的蛋氨酸，经过长时间加热后，它会分解出硫化物，它与蛋黄中的铁发生反应，形成人体不易吸收的硫化铁，营养损失较多。煮不熟的鸡蛋危害更大。生鸡蛋不但存在沙门氏菌污染问题，还有抗酶蛋白和抗生物素蛋白两种有害物。前者会影响蛋白质的消化吸收；后者能与食物中的生物素结合，导致人体生物素缺乏，产生精神倦怠、肌肉酸痛等症状。而鸡蛋一经煮熟，上述两种物质才会被破坏。</p>\r\n<p>　　此外，煮蛋时还可掌握以下技巧，以防止营养素的流失：水必须没过蛋，否则浸不到水的地方蛋白质不易凝固，影响消化；煮前把蛋放人冷水浸泡一会儿，以降低蛋内气压；然后用中等火候，冷水煮沸，即可防止蛋壳破裂，避免营养素流失。</p>\r\n','2',NULL,'','admin','2009-09-12 18:18:20','1',NULL,NULL);
INSERT INTO `healthdrink` VALUES (31,5,'进食时间不恰当更易发胖','','对于追求苗条身材的人而言，“晚上吃得少”的忠告的确是有科学依据的。最近，美国科学家通过小鼠实验发现，进食时间与生物钟有密切联系，在不恰当的时间进食，会更容易发胖。这一科研成果昨天在线发表在学术期刊《肥胖》上。<p>　　“吃饭没规律更容易胖”、“减肥切忌深夜吃东西”，听起来好像上了年纪的妈妈的唠叨。不过，美国西北大学生物睡眠和昼夜规律研究中心的一个研究小组，在实验小鼠身上验证了这句话的正确性。研究人员让12只小鼠从出生开始，就生活在昼夜平分的环境中。9周后，他们将小鼠分成2组，给予相同的高脂肪饮食(脂肪提供总热量的60%)，一组在白天进食，而另一组则在夜间。</p>\r\n<p>　　过了6周后，他们发现，白天进食的小鼠要比夜间喂食的体重明显增加——在运动量基本相同的情况下，与夜间进食小鼠相比，白天进食的小鼠平均多长了7.8%的“肥肉”！文章作者指出，小鼠的习惯是昼伏夜出，正好与人类相反。这意味着，在生物钟走到“睡眠”时段时，吃下的食物更容易变成脂肪。</p>\r\n<p>　　这个实验，仅仅是为了验证一个常识吗？中科院上海生科院营养科学研究所研究员郭非凡告诉记者，其实它更大的意义在于建立了一个动物模型，“通过这个动物模型，科学家有可能从一个新的角度，深入研究肥胖的生命机理。”她说，“以前有过一些从环境、基因角度探讨生物钟和进食的研究，但一直没有深入到生理机制的层面。”如果可以通过动物模型，找到这些规律、机制，将可以为人类解决肥胖问题，提供更好的方法。</p>\r\n<p>　　研究人员指出，体重的规律变化也有自己的生物钟，有一些“生物钟基因”会对代谢综合征产生影响。虽然有很多问题值得深入研究，但可以给减肥者的建议是：在合适的时间进食，对减肥效果会有意想不到的提升。</p>\r\n','2',NULL,'','admin','2009-09-12 18:18:47','1',NULL,NULL);
INSERT INTO `healthdrink` VALUES (32,5,'梨和甘蔗去秋燥但有人不适宜','','根据传统的养生原则，在秋天要多吃些水果，可增加肝脏的功能，可抵御过盛肺气之侵袭。但并不是所有的水果都适合人们食用。各种水果该怎么吃才是健康之道呢?<p>　　专家从中医的角度，对秋季的吃水果问题做了全面的分析。专家表示，从传统养生的角度看，秋季的主要特点是“燥”和“干”，因此人体常常会出现如皮肤干燥、口干唇燥、咽喉肿痛等症状。所以在秋季一定要少吃一些如葱、姜、蒜、韭、椒等辛味的食品，以防肺气过胜。而吃些生津止渴、润喉去燥的水果，就会使人顿觉清爽舒适。“最适合在秋季吃的水果，就数梨和甘蔗了。”专家强调。</p>\r\n<p><strong>　　梨和甘蔗虽好有人不适宜</strong></p>\r\n<p><strong>　　●梨</strong></p>\r\n<p>　　功能：中医认为，梨性味甘寒而润，有生津止渴、止咳化痰、清热降火、润肺去燥等功能。</p>\r\n<p>　　最适宜人群：对肺热咳嗽、小儿风热、咽干喉痛、大便燥结等症较为适宜。对高血压、心肺病，肝炎、肝硬化病人，常吃梨大有好处。肝炎病人吃梨能起到保肝、助消化，增食欲的作用。</p>\r\n<p>　　不适宜人群：因梨性寒，故有胃寒呕吐、寒咳、脾虚泄泻、便溏、妇人产后、小儿痘后、金疮等患者，宜慎食或忌食。</p>\r\n<p><strong>　　●甘蔗</strong></p>\r\n<p>　　功能：性味甘凉，有清热润肠、化痰充液的作用。汁多味甜营养丰富。</p>\r\n<p>　　最适宜人群：对于大便干结、小便不利、反胃呕吐、虚热咳嗽和高热烦渴等病症有一定的疗效。劳累过度或饥饿头晕的人，只要吃上两节甘蔗就会使精神重新振作起来。</p>\r\n<p>　　不适宜人群：甘蔗性寒，不适合胃寒、呕吐、腹泻、咳嗽、痰多等症的病人。</p>\r\n<p><strong>　　根据自身体质选择水果</strong></p>\r\n<p>　　专家对记者表示，吃水果应该根据自己的身体体质进行选择。而人的体质从中医来说，一般分为偏寒型和实热型两类。对于这两种体质不同的人来说，所选择的水果应该是不同的。</p>\r\n','2',NULL,'','admin','2009-09-12 18:19:22','1',NULL,NULL);
INSERT INTO `healthdrink` VALUES (33,5,'常见的豆浆的10种制作方法','','<strong>常见的豆浆的10种制作方法：</strong><p><strong>　　1、黄豆浆：</strong></p>\r\n<p>　　用料：黄豆85克，水1200毫升(容量可根据个人需要随意增减)，糖适量。</p>\r\n<p>　　功效：补虚、清热化痰、通淋、利大便、降血压、增乳汁。</p>\r\n<p>　　建议：加3-5粒杏仁于用料中，则所熬豆浆更鲜、更浓。</p>\r\n<p><strong>　　2、花生豆奶：</strong></p>\r\n<p>　　用料：黄豆、花生各45克，牛奶200克，水1200毫升，糖适量。</p>\r\n<p>　　制作：黄豆浸泡6-16小时，备用;把浸泡过的黄豆、花生放入豆浆机，加入适量水，打碎煮熟，再用豆浆滤网过滤后即可食用。</p>\r\n<p>　　功效：润肤，盒肺气、补虚。</p>\r\n<p><strong>　　3、芝麻黑豆浆：</strong></p>\r\n<p>　　用料：黑芝麻、花生各10克，黑豆80克，水1200毫升，糖适量。</p>\r\n<p>　　制作：将花生与黑豆浸泡6-16小时，备用;将黑芝麻与浸泡过的花生、黑豆一起放入豆浆机，加入适量水，打碎煮熟，再用豆浆滤网过滤后即可食用。</p>\r\n<p>　　功效：乌发养发，润肤美颜，补肺盒气，滋补肝肾、润肠通便、养血增乳。</p>\r\n<p><strong>　　4、豆浆冰糖米粥：</strong></p>\r\n<p>　　用料：黄豆85克，大米与冰糖各50克，水1200毫升。</p>\r\n<p>　　制作：先照第1种方法制作好黄豆浆，再将黄豆浆与米(可浸泡半个小时)、冰糖一起放入锅内，慢火熬煮到粘稠状即可。</p>\r\n<p>　　功效：养颜润肺，盒肺气。</p>\r\n<p><strong>　　5、芝麻蜂蜜豆浆：</strong></p>\r\n<p>　　用料：豆浆70克，黑芝麻20克，蜂蜜40克，水1200毫升。</p>\r\n<p>　　制作：黄豆浸泡6-16小时，备用;将黑芝麻与浸泡过的黄豆放入豆浆机，加入适量水，打碎煮熟，再用豆浆滤网过滤后即可食用。</p>\r\n<p>　　功效：养颜润肤、乌发养发。</p>\r\n<img src=\"http://pic.enorth.com.cn/0/06/19/42/6194234_968219.jpg\" width=\"350\" height=\"262\" /><br />\r\n<p><strong>　　6、密法黑芝麻糊：</strong></p>\r\n<p>　　用料：蜂蜜1勺，黑芝麻20克，黄豆60克，清水适量，糖适量。</p>\r\n<p>　　制法：黄豆浸泡6-16小时，备用;将黑芝麻与泡好的黄豆一起放入豆浆机，加入适量水，打碎煮熟，再用豆浆滤网过滤，稍凉后加入蜂蜜即可。</p>\r\n<p>　　功效：养颜美容、益智健脑。</p>\r\n<p><strong>　　7、五豆豆浆：</strong></p>\r\n<p>　　用料：黄豆30克，黑豆10克，青豆10克，豌豆10克，花生米10克，水1200毫升，糖适量。</p>\r\n<p>　　制法：五种豆类浸泡6-16小时，备用;将浸泡好的五豆一起放入豆浆机，加入适量水，打碎煮熟，再用豆浆滤网过滤后即可食用。</p>\r\n<p>　　功效：降脂降压、强筋健脾、保护心血管。</p>\r\n<p><strong>　　8、枸杞豆浆：</strong></p>\r\n<p>　　用料：黄豆60克，枸杞10克，水1200毫升，糖适量。</p>\r\n<p>　　制法：黄豆浸泡6-16小时，备用;将泡好的黄豆和枸杞一起放入豆浆机，加入适量水，打碎煮熟，再用豆浆滤网过滤后即可食用。</p>\r\n<p>　　功效：滋补肝肾、益精明目、增强免疫能力。</p>\r\n<p><strong>　　9、红枣枸杞豆浆：</strong></p>\r\n<p>　　用料：黄豆45克，红枣15克，枸杞10克，水1200毫升，糖适量。</p>\r\n<p>　　制法：黄豆浸泡6-16小时;将红枣洗净去核，枸杞洗净备用;将泡好的黄豆、红枣和枸杞一起放入豆浆机，加入适量水，打碎煮熟，再用豆浆滤网过滤后即可食用。</p>\r\n<p>　　功效：补虚益气、安神补肾、改善心肌营养、防治心血管疾病。</p>\r\n<p><strong>　　10、红枣莲子豆浆：</strong></p>\r\n<p>　　用料：红枣(去核)15克，莲子肉15克，黄豆50克，白糖50克，清水适量，糖适量。</p>\r\n<p>　　制法：黄豆浸泡6-16小时;将莲子肉泡至发软;将红枣洗净与莲子肉、黄豆一起放入豆浆机，加入适量水，打碎煮熟，再用豆浆滤网过滤;趁热往杯内加入白糖，搅匀即成;不愿喝甜的也可以不加糖。</p>\r\n<p>　　功效：滋阴益气、养血安神、补脾胃、清热解毒。</p>\r\n','2',NULL,'','admin','2009-09-12 18:19:57','1',NULL,NULL);
INSERT INTO `healthdrink` VALUES (34,5,'百日咳药膳私家方','','百日咳是小儿时期常见的呼吸道传染病之一。临床以阵发性痉挛性咳嗽，咳后有特殊的吸气性吼声，即鸡鸣样的回声，最后倾吐痰沫而止为特征。本病四季都可发生，但冬春季尤多。在临床过程中,我们可以通过中医的辩证论治,给予不同的药膳治疗,往往能够达到一定的疗效.<p>　<strong>　初咳期</strong></p>\r\n<p>　　[临床表现]：初起咳嗽、喷嚏、流涕，或发热等伤风感冒症状。 2-3日后咳嗽日渐增剧，痰稀白、量不多、或痰稠不易咯出、咳声不畅，咳嗽以入夜为重，苔薄白。</p>\r\n<strong>　</strong>　[食疗药膳] <p>　　1.萝卜蜂蜜饮：白萝卜1个(捣烂绞汁)取汁25毫升，蜂蜜12毫升调匀，1次服完，每日1—2次。</p>\r\n<p>　　2.鱼腥草苏叶绿豆粥：鱼腥草(鲜品)50克，苏叶15克，绿豆60克，粳米60克，冰糖30克。将鱼腥草、苏叶水煎20分钟取汁，再煎30分钟共取浓汁300毫升，加适量清水和绿豆，粳米煮粥，熟时加冰糖溶化调匀服食，每日1—2次。</p>\r\n<p>　　3.芹菜饮：芹菜(连根叶)1把，洗净捣汁30毫升，加食盐少许，隔水蒸热，早晚各服1次。连服3日。</p>\r\n<p>　　4.冰糖蒸鸡胆：冰糖20克，鸡苦胆1个。将鸡苦胆和冰糖同蒸熟，分2次服，每日1料。</p>\r\n<strong>　　痉咳期</strong> <p>　　[临床表现]：阵发性咳嗽，日轻夜重，咳剧时伴有深吸气样的鸡鸣声，必待吐出痰涎及食物后，痉咳才得暂时缓解，但不久又复发作，而且一次比一次加剧。并可见眼角青紫及结膜下出血。婴幼儿时期还可引起窒息和抽风。</p>\r\n<strong>　</strong>　[食疗药膳] ： <p>　　1.柿饼罗汉果汤：柿饼30克，罗汉果1个，冰糖25克。将罗汉果和柿饼水煎30分钟，加上冰糖溶化搅匀即可服用。</p>\r\n<p>　　2.橄榄炖冰糖;生橄榄10粒(打碎)，冰糖25克，隔水炖50分钟服用，每日2次。</p>\r\n<p>　　3.川贝鸡蛋蒸：川贝6克(研末)，鸡蛋1枚，将鸡蛋敲一孔如花生仁大小，川贝末入于鸡蛋内，外用湿纸封闭，放在饭上蒸熟。每次吃1枚。每日2次。</p>\r\n<p>　　4.桑白杏仁茶：桑白皮10克，杏仁10克(打碎)，绿茶12克，冰糖20克。前3味水煎去渣，入冰糖溶化，即可饮服，每日1-2次，连服6日1疗程。</p>\r\n<p><strong>　　恢复期</strong></p>\r\n<p>　　[临床表现]顿咳症状开始好转，咳嗽逐渐减轻，一般需经过3周才咳止。</p>\r\n<p>　　[食疗药膳]</p>\r\n<p>　　1.百合炖麻雀：百合20克，麻雀2只(去毛及内脏)，冰糖25克，同隔水炖熟食用，每日1次。</p>\r\n<p>　　2.太子参黄芪鸽蛋汤：太子参15克，黄芪15克，鸽蛋3枚。先水煎太子参、黄芪、取药汁煮鸽蛋，熟时饮汤食鸽蛋。</p>\r\n<p>　　3.沙参百合玉竹粥：北沙参15克，百合15克，玉竹10克，粳米30克，先水煎北沙参、百合、玉竹取药液和粳米煎煮成稀粥食用，连服3日。</p>\r\n','2',NULL,'','admin','2009-09-12 18:20:44','1',NULL,NULL);
INSERT INTO `healthdrink` VALUES (35,5,'常吃四类食物容易缺钙','','<strong>常吃四类食物容易缺钙</strong><p>　　钙是人体骨骼生长发育不可缺少的无机物，它还参与运动神经兴奋性活动。钙在体液的调解下处在动态的平衡状态，不断地被吸收、排泻，血浆钙离子浓度保持不变，使骨生长、骨钙不断沉积与释放。</p>\r\n<p>　　小儿骨生长迅速时需要的钙就多。如不能及时补充，则会发生缺钙，引起疾病。成人若肠道对钙的吸收发生障碍，为维持血浆钙离子的浓度，骨钙就会不断释出，这样就会造成骨质疏松；承重骨的骨髓端骨缘钙沉积过多，形成骨刺，骨膜钙化，甚至发生压缩性骨折等骨退行性骨关节病。</p>\r\n<p>　　胎儿期、新生儿、婴幼儿骨生长发育迅速，钙往往不能满足需要，因此要补充钙剂。</p>\r\n<p>　　人到40岁以后，身体各器官功能均在缓慢地减退，肠道对钙的吸收能力也逐渐地降低，为维持血浆钙离子浓度，保障神经兴奋性活动的需要，骨钙会不断释出，结果会出现前面所述的骨退行性骨关节病，或易发生骨折。</p>\r\n<p>　　为预防此种情况发生，人自40岁以后，应适当补充易于吸收的活性钙。</p>\r\n<p>　　具体地说，妊娠期、新生儿、婴幼儿要在医生指导下适当补充钙剂、晒太阳、服用鱼肝油，以利于骨胳生长发育。</p>\r\n<p>　　40岁以后，为帮助肠道吸收钙，需服用活性钙。因为食物中的钙量虽然已足够多，由于肠道吸收功能减退，则补充的钙需经活化后才易被吸收。</p>\r\n<p>　　此时也应在医生的指导下按疗程补充钙剂，以免发生钙摄入过多引起的疾病。</p>\r\n<p>　　◆提问：饮食中妨碍钙吸收的因素有哪些？</p>\r\n<p>　　◆回答：</p>\r\n<p>　　1、过多摄入植物性食物：植物性食物中的草酸、植酸、膳食纤维等会妨碍钙的吸收。</p>\r\n<p>　　2、吃太咸的食物：食盐的成分为氯化钠，高钠摄入会影响钙的吸收。</p>\r\n<p>　　3、饮大量咖啡：咖啡中含有咖啡因，过多的咖啡因对钙吸收有影响。</p>\r\n<p>　　4、太多的肉类：肉类中蛋白质和磷的含量都较高，膳食中适量的蛋白有助于钙吸收，但过多的蛋白质和磷可妨碍钙的吸收。</p>\r\n','2',NULL,'','admin','2009-09-12 18:21:11','1',NULL,NULL);
INSERT INTO `healthdrink` VALUES (36,5,'秋季常食莲子羹可除皱嫩肤','','用料：莲子30克，芡实30克，薏仁米50克，桂圆肉10克，蜂蜜适量。做法：先将莲子、芡实、薏仁米用清水浸泡30分钟，再将桂圆肉一同放入锅内，加入适量清水，用文火煮至烂熟，加蜂蜜调味食用。<img src=\"http://pic.enorth.com.cn/0/06/23/01/6230186_802624.jpg\" width=\"300\" height=\"291\" /><br />\r\n<p>　　莲子羹功效：桂圆肉大补元气，莲子补脾养胃，薏仁米、芡实为健脾利水之品。现代药理研究表明，芡实中含有美容必需的维生素A、维生素C、B族维生素，蜂蜜中含有胶原蛋白和酶类等物质，可刺激皮肤细胞的生长，促进新陈代谢。此羹是较理想的美容药膳，经常食用有消除皱纹、白嫩肌肤的作用。</p>\r\n','2',NULL,'','admin','2009-09-12 18:21:32','1',NULL,NULL);

#
# Table structure for table healthdrinktype
#

DROP TABLE IF EXISTS `healthdrinktype`;
CREATE TABLE `healthdrinktype` (
  `ID` int(10) NOT NULL auto_increment,
  `TYPE_NAME` varchar(40) NOT NULL default '' COMMENT '新闻类型',
  `IS_DISPLAY` char(1) default NULL COMMENT '状态',
  `SEQUENCE` tinyint(4) default NULL COMMENT '排序',
  `EDITOR` int(10) default NULL COMMENT '编辑人',
  `EDITTIME` datetime default NULL COMMENT '编辑时间',
  `DESCRIPTION` varchar(255) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_NEWSTYPE_EDITOR_ID` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
INSERT INTO `healthdrinktype` VALUES (5,'健康饮食','1',1,NULL,NULL,'健康饮食');

#
# Table structure for table integralnote
#

DROP TABLE IF EXISTS `integralnote`;
CREATE TABLE `integralnote` (
  `ID` int(11) NOT NULL auto_increment,
  `MEMBER` int(9) default NULL COMMENT '会员',
  `CHANGE_INTEGRAL` int(6) NOT NULL default '0' COMMENT '兑换积分',
  `MEAL_CURRENCY` float(6,1) NOT NULL default '0.0' COMMENT '生成餐币',
  `EDITOR` int(9) default NULL,
  `EDITTIME` datetime default NULL,
  `CHANGE_TIME` datetime default NULL COMMENT '兑换日期',
  PRIMARY KEY  (`ID`),
  KEY `FK_INTEGRALNOTE_MEMBER` (`MEMBER`),
  KEY `FK_INTEGRALNOTE_EDITOR` (`EDITOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Table structure for table integralset
#

DROP TABLE IF EXISTS `integralset`;
CREATE TABLE `integralset` (
  `ID` int(11) NOT NULL auto_increment,
  `INTEGRAL` int(5) NOT NULL default '0' COMMENT '兑换积分',
  `MEAL_CURRENCY` float(5,1) NOT NULL default '0.0' COMMENT '餐币',
  `EDITOR` int(9) default NULL,
  `EDITTIME` datetime default NULL,
  `REMARK` varchar(255) default NULL,
  `STATUS` char(1) default NULL COMMENT '1=可用',
  PRIMARY KEY  (`ID`),
  KEY `FK_INTEGRALSET_EDITOR` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `integralset` VALUES (1,100,10,7,'2009-10-17 13:17:49','','2');
INSERT INTO `integralset` VALUES (2,100,2,7,'2009-10-17 13:17:56','','2');
INSERT INTO `integralset` VALUES (3,100,100,7,'2009-10-17 13:18:04','','1');

#
# Table structure for table islegal
#

DROP TABLE IF EXISTS `islegal`;
CREATE TABLE `islegal` (
  `ID` int(11) NOT NULL auto_increment,
  `WEBSITE` varchar(50) default NULL,
  `EDITOR` int(11) default NULL,
  `EDITTIME` datetime default NULL,
  `SERIAL` varchar(20) default NULL,
  `LEGALCODE` varchar(100) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_ISLEGAL_EDITOR` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `islegal` VALUES (3,'172.16.6.146',NULL,'2009-09-25 15:36:47','tt001','6c66a010d253e8e2651d3fb1bfa5dfd1');

#
# Table structure for table leaveword
#

DROP TABLE IF EXISTS `leaveword`;
CREATE TABLE `leaveword` (
  `ID` int(11) NOT NULL auto_increment,
  `MEMBER` int(11) default NULL,
  `NAME` varchar(20) default NULL,
  `PHONE` varchar(30) default NULL,
  `EMAIL` varchar(50) default NULL,
  `LEAVE_TIME` datetime default NULL,
  `TYPE` varchar(1) default NULL COMMENT '1=意见2=建议3=咨询',
  `STATUS` varchar(1) default NULL COMMENT '1=显示',
  `EDITOR` int(11) default NULL,
  `EDITTIME` datetime default NULL,
  `CONTENT` varchar(255) NOT NULL default '',
  `ANSWER` varchar(255) default NULL,
  `MEMBER_CODE` varchar(20) default NULL,
  `ANSWER_MAN` varchar(30) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_LEAVEWORD_MEMBER` (`MEMBER`),
  KEY `FK_LEAVEWORD_EDITOR` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
INSERT INTO `leaveword` VALUES (11,6,'小黄','88887777','88887777@QQ.com','2009-09-26 07:44:46','3','1',7,'2009-10-12 12:03:11','请问订餐系统是免费的吗？','你好！\r\n天鼎订餐系统\"测试版\"是免费的，仅供学习交流使用，假如用于商业使用需向我们公司进行购买。\r\nQQ：1273708322 \r\n联系电话：18925050569','2222','admin');
INSERT INTO `leaveword` VALUES (12,NULL,'舜','15914381568','kander@163.com','2009-10-09 12:40:52','1','1',7,'2009-10-12 12:01:07','請問我該如何購買','您好！详细购买方式以及价格请参照我们的官方网站www.tienting.com\r\n请在购买前与我们的客服联系。谢谢您的关注~\r\nQQ：1273708322 \r\n联系电话：18925050569',NULL,'admin');
INSERT INTO `leaveword` VALUES (13,NULL,'小黄','87811132','xiaohuang2009@126.com','2009-10-11 12:04:24','3','1',7,'2009-10-12 12:00:39','请问订餐系统怎么购买啊？或者测试版在哪里可以下载啊？','您好！详细购买方式以及价格请参照我们的官方网站www.tienting.com\r\n请在购买前与我们的客服联系。谢谢您的关注~\r\nQQ：1273708322 \r\n联系电话：18925050569',NULL,'admin');
INSERT INTO `leaveword` VALUES (16,NULL,'王先生','15914381568','kander@163.com','2009-10-12 11:57:24','3','1',7,'2009-10-12 12:00:55','请问你们的联系方式是?','您好！详细购买方式以及价格请参照我们的官方网站www.tienting.com\r\n请在购买前与我们的客服联系。谢谢您的关注~\r\nQQ：1273708322 \r\n联系电话：18925050569',NULL,'admin');
INSERT INTO `leaveword` VALUES (17,NULL,'王先生','15914381568','kander@163.com','2009-10-12 12:03:18','3','1',7,'2009-10-12 12:04:17','请问你们的联系方式是?','您好！详细购买方式以及价格请参照我们的官方网站www.tienting.com\r\n请在购买前与我们的客服联系。谢谢您的关注~\r\nQQ：1273708322 \r\n联系电话：18925050569',NULL,'admin');
INSERT INTO `leaveword` VALUES (18,NULL,'何先生','02085421585','eopg@163.com','2009-10-13 23:06:32','1','1',7,'2009-10-15 13:01:45','请问这订餐系统多少钱卖？','您好~系统原价1080元，10月份到11月份是推广期所以在此期间购买仅需560元，详情请与我们联系。\r\nQQ：1273708322\r\n联系电话：18925050569',NULL,'admin');

#
# Table structure for table member
#

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `ID` int(11) NOT NULL auto_increment,
  `LOGIN_CODE` varchar(20) NOT NULL COMMENT '账号',
  `MEMBER_NAME` varchar(20) default NULL,
  `SEX` char(1) default NULL COMMENT '1=女',
  `IDCARD_NO` varchar(20) default NULL,
  `PASSWORD` varchar(60) NOT NULL,
  `PHOTO` varchar(50) default NULL,
  `ASSIGN_ADDRESS` varchar(50) default NULL,
  `RELATION_PHONE` varchar(30) default NULL,
  `EMAIL` varchar(50) default NULL,
  `MOBILE` varchar(30) default NULL,
  `MEAL_CURRENCY` float(6,1) default '0.0' COMMENT '餐币',
  `INTEGRAL` int(6) default '0' COMMENT '可用积分',
  `HISTORY_INTEGRAL` int(7) default '0' COMMENT '历史积分',
  `STATUS` char(1) default NULL COMMENT '1=可用',
  `REMARK` varchar(255) default NULL,
  `REGISTER_TIME` datetime default NULL,
  `LOGIN_IP` varchar(20) default NULL,
  `LAST_IP` varchar(20) default NULL,
  `LOGIN_COUNT` int(6) default NULL,
  `LAST_TIME` datetime default NULL,
  `FINALLY_TIME` datetime default NULL,
  `EDITOR` int(9) default NULL,
  `EDITTIME` datetime default NULL,
  `OPERATE_RECORD` text COMMENT '操作记录',
  PRIMARY KEY  (`ID`),
  KEY `FK_MEMBER_EDITOR` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
INSERT INTO `member` VALUES (6,'2222','黄经理','1','3214','db7bf5dfe50b344a7bbac818e465b273','20090903231724.jpg','广从路13号','18781829555','22222@2.cn','3421',92,2030,0,'1','4545hjfh222','2009-08-29 18:52:25','127.0.0.1','127.0.0.1',54,'2009-10-17 15:51:17','2009-10-17 16:16:10',7,'2009-09-12 16:30:00','<p>您在 2009-08-29 成功注册为：天鼎订餐系统 的会员。</p><p>您在 2009-08-30 16:30:57 成功修改您的个人信息。</p><p>您在 2009-08-30 16:32:54 成功修改您的个人信息。</p><p>您在 2009-08-30 16:38:40 成功修改您的个人信息。</p>您在 2009-08-30 16:43:34 成功修改您的个人信息。<br/>您在 2009-09-03 23:01:50 成功修改您的个人信息。<br/>您在 2009-09-03 23:17:24 成功修改您的个人信息。<br/>您在 2009-09-05 11:59:46 成功修改您的个人信息。<br/>');
INSERT INTO `member` VALUES (7,'hy03','小何','2','0337310611','bfe5724f8fae8fc5412ffd518c0f63a9',NULL,'紫泉27栋404','87878888','xiaohe@vip.163.com','13812345678',NULL,6,0,'1','','2009-09-19 16:32:11','127.0.0.1','127.0.0.1',0,'2009-09-19 16:37:48','2009-09-19 16:55:04',1,'2009-09-19 16:32:11',NULL);
INSERT INTO `member` VALUES (8,'jw09','小黄','2','010316459','0106ed89225c8eb88452d8cb1ef494f9',NULL,'','','','',NULL,0,0,'1','','2009-09-19 16:34:50',NULL,NULL,NULL,NULL,NULL,1,'2009-09-19 16:34:50',NULL);
INSERT INTO `member` VALUES (9,'kh09','小航','1','','a0efd618a73c1d362e1c314753f961a4',NULL,'','','xiaohe@vip.163.com','',NULL,0,0,'1','','2009-09-19 17:23:09','127.0.0.1',NULL,NULL,NULL,NULL,1,'2009-09-30 20:24:44','<p>您在 2009-09-19 成功注册为：天鼎订餐系统 的会员。</p>');
INSERT INTO `member` VALUES (10,'yh09','小黄','1',NULL,'9d102f9741f6a5857fb932d24eb1566f',NULL,'','','xiaohe@vip.163.com',NULL,NULL,0,0,'1',NULL,'2009-09-19 17:24:15','127.0.0.1',NULL,NULL,NULL,NULL,NULL,NULL,'<p>您在 2009-09-19 成功注册为：天鼎订餐系统 的会员。</p>');
INSERT INTO `member` VALUES (11,'hr2009','小曾','1',NULL,'24980b06836746d567bd9206634c6082',NULL,'北京路','13888888888','13888888888@139.com',NULL,NULL,0,0,'1',NULL,'2009-09-30 08:14:47','127.0.0.1',NULL,NULL,NULL,NULL,NULL,NULL,'<p>您在 2009-09-30 成功注册为：天鼎网上订餐系统（天天网上订餐） 的会员。</p>');
INSERT INTO `member` VALUES (12,'xh88','胡女士','1',NULL,'73df7730c769e10f6013ddb765c79da5',NULL,'','','1@1.com',NULL,NULL,14,0,'1',NULL,'2009-10-09 08:14:12','219.137.116.11','219.137.116.11',0,'2009-10-09 08:14:44','2009-10-09 08:14:44',NULL,NULL,'<p>您在 2009-10-09 成功注册为：天鼎网上订餐系统（天天网上订餐） 的会员。</p>');
INSERT INTO `member` VALUES (13,'xiaoh','小花','1','','aa8154f3b4146d4d62253cbc958d16aa',NULL,'','','433@df.com','',0,0,0,'1','','2009-10-09 08:22:01','219.137.116.11',NULL,NULL,NULL,NULL,1,'2009-10-10 20:00:41','<p>您在 2009-10-09 成功注册为：天鼎网上订餐系统（天天网上订餐） 的会员。</p>');
INSERT INTO `member` VALUES (16,'txc623','小谭','1',NULL,'70e01d947ac13abd850729b0394b1483',NULL,'','','222@eee.com',NULL,0,0,0,'1',NULL,'2009-10-11 20:10:30','127.0.0.1',NULL,NULL,NULL,NULL,NULL,NULL,'您在 2009-10-11 成功注册为：天鼎网上订餐系统（天天网上订餐） 的会员。<br/>');
INSERT INTO `member` VALUES (18,'www','刘先生','2',NULL,'728cf12a660f994b984dc7d162dabfd9',NULL,'广州市','12345678','123@163.com',NULL,0,16,0,'1',NULL,'2009-10-14 13:56:26','219.137.116.11','219.137.116.11',0,'2009-10-14 13:57:43','2009-10-14 13:57:43',NULL,NULL,'您在 2009-10-14 成功注册为：天鼎网上订餐系统（天天订餐） 的会员。<br/>');
INSERT INTO `member` VALUES (19,'kander','王先生','2',NULL,'dad40c6f05723b026c9c02bb2306c6fe',NULL,'','','kander@163.com',NULL,0,130,0,'1',NULL,'2009-10-17 13:16:02','58.248.64.128','58.248.64.128',0,'2009-10-17 13:16:10','2009-10-17 13:16:10',NULL,NULL,'您在 2009-10-17 成功注册为：天鼎网上订餐系统（天天订餐） 的会员。<br/>');

#
# Table structure for table menuevaluate
#

DROP TABLE IF EXISTS `menuevaluate`;
CREATE TABLE `menuevaluate` (
  `ID` int(11) NOT NULL auto_increment,
  `SUBMENU` int(11) default NULL,
  `MEMBER` int(11) default NULL,
  `CONTENT` varchar(255) NOT NULL default '',
  `EVALUATE_TIME` datetime default NULL,
  `STATUS` char(1) default NULL,
  `EDITOR` int(11) default NULL,
  `EDITTIME` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_MENUEVALUATE_SUBMENU` (`SUBMENU`),
  KEY `FK_MENUEVALUATE_MEMBER` (`MEMBER`),
  KEY `FK_MENUEVALUATE_EDITOR` (`EDITOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Table structure for table menutype
#

DROP TABLE IF EXISTS `menutype`;
CREATE TABLE `menutype` (
  `ID` int(10) NOT NULL auto_increment,
  `CODE` varchar(20) NOT NULL default '' COMMENT '编号',
  `NAME` varchar(40) NOT NULL default '' COMMENT '名称',
  `DESCRIPTION` text COMMENT '描述',
  `SEQUENCE` tinyint(4) default NULL COMMENT '排序',
  `STATUS` char(1) default NULL COMMENT '状态',
  `EDITOR` int(10) default NULL COMMENT '编辑人',
  `EDITTIME` datetime default NULL COMMENT '编辑时间',
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `CODE` (`CODE`),
  UNIQUE KEY `NAME` (`NAME`),
  KEY `FK_MENUTYPE_EDITOR_USERS` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
INSERT INTO `menutype` VALUES (13,'001','鱼类海鲜','鱼类海鲜',1,'1',7,'2009-10-17 12:55:33');
INSERT INTO `menutype` VALUES (14,'002','热菜肉类','热菜肉类',2,'1',NULL,NULL);
INSERT INTO `menutype` VALUES (15,'003','汤羹菜谱','汤羹菜谱',3,'1',NULL,NULL);
INSERT INTO `menutype` VALUES (16,'004','主食菜单','主食菜单',4,'1',NULL,'2009-09-14 15:03:05');
INSERT INTO `menutype` VALUES (17,'005','潮汕小吃','潮汕美味\r\n',1,'1',7,'2009-10-14 14:07:40');

#
# Table structure for table notice
#

DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `ID` int(11) NOT NULL auto_increment,
  `TITLE` varchar(100) NOT NULL COMMENT '标题',
  `TITLE_ICO` varchar(50) default NULL,
  `SUMMARY` varchar(200) default NULL COMMENT '简介',
  `CONTENT` text,
  `RECORD_TIME` datetime default NULL,
  `RECORD_MAN` varchar(20) default NULL,
  `IS_DISPLAY` char(1) default NULL COMMENT '1=显示',
  `EDITOR` int(11) default NULL,
  `EDITTIME` datetime default NULL,
  `IS_TOP` char(1) default NULL COMMENT '1=置顶',
  PRIMARY KEY  (`ID`),
  KEY `FK_notice_EDITOR` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
INSERT INTO `notice` VALUES (5,'如何使用该系统',NULL,'如何使用该系统','<p>系统购买方式</p>\r\n<p>联系电话：020-87811132&nbsp;&nbsp;&nbsp;&nbsp; 18925050569</p>\r\n<p>QQ1：1273708322</p>\r\n<p>QQ2：861516626</p>\r\n<p>公司官网：<a href=\"http://www.tienting.com/\">www.tienting.com</a></p>\r\n<p>请与我们联系！因为专业！所以信赖！</p>\r\n','2009-10-14 16:53:08','admin','1',NULL,NULL,'1');
INSERT INTO `notice` VALUES (6,'欢迎大家来订餐',NULL,'欢迎大家来订餐','<p>深圳市圣古科技有限公司是一家集计算机软件开发、硬件及网络维护、生产、贸易、技术支持为一体的现代化高新科技企业。公司为扩大经营范围，于2009年6月在广州市从化区成立软件研发基地。</p>\r\n<p>圣古以软件技术为核心，通过软件与服务的结合，软件与制造的结合，技术与行业管理能力的结合，提供行业解决方案和产品工程解决方案以及相关产品与服务。</p>\r\n<p>面向行业客户，我们提供安全、可靠、高质量、易扩展的行业解决方案，帮助客户实现信息化管理最佳实践，以满足客户业务快速发展的不同需求。行业解决方案涵盖的领域包括：电信、制造业与商贸流通业、医疗卫生、教育等行业。</p>\r\n<p>在产品工程解决方案领域，圣古旗下的天鼎<a href=\"http://www.tienting.cn/\" target=\"_blank\">网上订餐系统</a>开创了电子商务的新领域----一个即时、快捷以及不需要物流的电子商务新局面。</p>\r\n<p>在软件业务流程外包方面，圣古面向行业客户提供多种类的软件外包业务。主要包括：</p>\r\n<b><ul><li>MIS（Management Information System，管理信息系统） </li><li>OA（Office Automation，办公自动化系统） </li><li>ERP（Enterprise Resource Planning，企业资源计划系统的研发） </li><li>CRM（Customer Relationship Management，客户关系管理系统） </li></ul>\r\n</b>','2009-10-17 16:19:40','admin','1',NULL,NULL,'2');

#
# Table structure for table orderformlist
#

DROP TABLE IF EXISTS `orderformlist`;
CREATE TABLE `orderformlist` (
  `ID` int(10) NOT NULL auto_increment,
  `ORDERFORMNOTE` int(10) NOT NULL default '0' COMMENT '订单号',
  `POS_PRINT_CODE` varchar(20) NOT NULL default '' COMMENT 'POS打单号',
  `SUBMENU` int(10) NOT NULL default '0' COMMENT '菜单表ID',
  `PRICE` float(7,1) NOT NULL default '0.0' COMMENT '主菜价格',
  `COUNT` tinyint(4) default '0' COMMENT '数量',
  `COST` float(7,1) default '0.0' COMMENT '估计成本',
  `PROFIT` float(7,1) default '0.0' COMMENT '估计利润',
  `MAIN_MENU_NAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_ORDERFORMLIST_SUBMENU` (`SUBMENU`),
  KEY `FK_ORDERFORMNOTE` (`ORDERFORMNOTE`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8;
INSERT INTO `orderformlist` VALUES (132,59,'001',32,10,1,5,5,'老鸭冬笋汤');
INSERT INTO `orderformlist` VALUES (133,59,'002',18,10,1,5,5,'薄荷香煎金丝鱼');
INSERT INTO `orderformlist` VALUES (134,59,'003',28,20,1,5,15,'茄汁免治猪扒');
INSERT INTO `orderformlist` VALUES (135,59,'004',24,30,1,10,20,'冰梅秘酱蒸鲶鱼');
INSERT INTO `orderformlist` VALUES (136,59,'005',26,6,1,2,4,'金牌好味骨');
INSERT INTO `orderformlist` VALUES (137,60,'001',30,10,1,2,8,'黑木耳红枣汤');
INSERT INTO `orderformlist` VALUES (138,61,'001',26,6,1,2,4,'金牌好味骨');
INSERT INTO `orderformlist` VALUES (139,61,'002',32,10,1,5,5,'老鸭冬笋汤');
INSERT INTO `orderformlist` VALUES (140,61,'003',31,8,1,2,6,'葫芦海鲜汤');
INSERT INTO `orderformlist` VALUES (141,61,'004',30,10,1,2,8,'黑木耳红枣汤');
INSERT INTO `orderformlist` VALUES (142,62,'001',26,6,1,2,4,'金牌好味骨');
INSERT INTO `orderformlist` VALUES (143,62,'002',31,8,1,2,6,'葫芦海鲜汤');

#
# Table structure for table orderformnote
#

DROP TABLE IF EXISTS `orderformnote`;
CREATE TABLE `orderformnote` (
  `ID` int(10) NOT NULL auto_increment,
  `ORDER_FORM_NO` varchar(50) NOT NULL default '' COMMENT '订单号',
  `PRICE` float(7,1) default '0.0' COMMENT '总价格',
  `ORDER_DATE` datetime default NULL COMMENT '订单日期',
  `RELATION_MAN` varchar(30) default NULL COMMENT '联系人',
  `RELATION_PHONE` varchar(30) default NULL COMMENT '送餐电话',
  `ASSIGN_ADDRESS` varchar(50) default NULL COMMENT '送到时间',
  `STATUS` char(1) default NULL COMMENT '订单状态',
  `REASON` varchar(50) default NULL COMMENT '理由',
  `ASSIGNMAN` int(10) default NULL COMMENT '送餐员',
  `FETCH_TIME` varchar(10) default NULL COMMENT '送到时间',
  `IS_PRINT` char(1) default NULL COMMENT '打印标记',
  `PAY_MODE` char(1) default NULL COMMENT '付款方式1=现金',
  `REMARK` varchar(100) default NULL COMMENT '备注',
  `EDITOR` int(10) default NULL,
  `EDITTIME` datetime default NULL,
  `MEMBER` int(9) default NULL,
  `MEMBER_NAME` varchar(20) default NULL,
  `ASSIGN_NAME` varchar(20) default NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `ORDER_FORM_NO` (`ORDER_FORM_NO`),
  KEY `FK_EDITOR` (`EDITOR`),
  KEY `FK_orderformnote_ASSIGNMAN` (`ASSIGNMAN`),
  KEY `FK_orderformnote_MEMBER` (`MEMBER`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
INSERT INTO `orderformnote` VALUES (59,'091017163400001',76,'2009-10-17 16:34:20','见过大爷','87634346','34asdf','1',NULL,8,'23:55',NULL,'1','sla',7,'2009-10-17',NULL,'游客','小黄');
INSERT INTO `orderformnote` VALUES (60,'091017164900001',10,'2009-10-17 16:49:35','小黄','45632145','dddd','1',NULL,NULL,'23:55',NULL,'1','',NULL,NULL,NULL,'游客',NULL);
INSERT INTO `orderformnote` VALUES (61,'091220163100001',34,'2009-12-20 16:31:11','张小山','1234567898','北京市朝阳区','1',NULL,NULL,'17:30',NULL,'1','',NULL,NULL,NULL,'游客',NULL);
INSERT INTO `orderformnote` VALUES (62,'100201155800001',14,'2010-02-01 15:58:21','小王','010-1234567','北京市昌平区龙锦苑','1',NULL,NULL,'17:30',NULL,'1','务必在6点前送到',NULL,NULL,NULL,'游客',NULL);

#
# Table structure for table resources
#

DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `ID` int(10) NOT NULL auto_increment,
  `RESOURCE_TYPE` varchar(20) NOT NULL default '',
  `VALUE` varchar(255) NOT NULL default '',
  `ORDER_NUM` float default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
INSERT INTO `resources` VALUES (1,'url','/security/user!save*',1.2);
INSERT INTO `resources` VALUES (2,'url','/security/user!delete*',1.1);
INSERT INTO `resources` VALUES (3,'url','/security/user*',1);
INSERT INTO `resources` VALUES (4,'url','/security/role!save*',2.2);
INSERT INTO `resources` VALUES (5,'url','/security/role!delete*',2.1);
INSERT INTO `resources` VALUES (6,'url','/security/role*',2);
INSERT INTO `resources` VALUES (7,'url','/frame.action',0.1);

#
# Table structure for table resources_authorities
#

DROP TABLE IF EXISTS `resources_authorities`;
CREATE TABLE `resources_authorities` (
  `AUTHORITY_ID` int(10) NOT NULL default '0',
  `RESOURCE_ID` int(10) NOT NULL default '0',
  PRIMARY KEY  (`AUTHORITY_ID`,`RESOURCE_ID`),
  KEY `FK_RESOURCES_AUTHORITIES_RESOURCE` (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `resources_authorities` VALUES (1,7);
INSERT INTO `resources_authorities` VALUES (2,3);
INSERT INTO `resources_authorities` VALUES (4,6);
INSERT INTO `resources_authorities` VALUES (23,1);
INSERT INTO `resources_authorities` VALUES (23,2);
INSERT INTO `resources_authorities` VALUES (27,4);
INSERT INTO `resources_authorities` VALUES (27,5);

#
# Table structure for table restaurant
#

DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant` (
  `ID` int(10) NOT NULL auto_increment,
  `ADDRESS` varchar(100) default NULL COMMENT '地址',
  `MAP` varchar(50) default NULL COMMENT '地图',
  `PRINCIPAL` varchar(20) default NULL COMMENT '负责人',
  `PHONE` varchar(30) default NULL COMMENT '联系电话',
  `MOBILE` varchar(30) default NULL COMMENT '手机',
  `EMAIL` varchar(50) default NULL COMMENT '邮箱',
  `EDITOR` int(10) default NULL COMMENT '编辑人',
  `EDITTIME` datetime default NULL COMMENT '编辑时间',
  `NAME` varchar(100) default NULL COMMENT '店名',
  `DESCRIPTION` text COMMENT '描述',
  `LOGO` varchar(45) default NULL COMMENT 'LOGO图片',
  `SERVICE` varchar(255) default NULL COMMENT '温馨提示',
  `AD` varchar(45) default NULL COMMENT '广告图片',
  `FOOTERITEM` varchar(100) default NULL COMMENT '页脚导航',
  `ADLINK` varchar(45) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_RESTAURANT_EDITOR_USERS` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
INSERT INTO `restaurant` VALUES (6,'广州广从大道100000号100','map.jpg','小伟子','7616641679846/6476131','13912345678','xiaohuang@tienting.com',7,'2009-10-15 13:45:51','网上订餐系统','<p>&nbsp;</p>\r\n<p>&nbsp;&nbsp;&nbsp; 天鼎特色农家天鼎特色农家 </p>\r\n<p>&nbsp;&nbsp;&nbsp; 天鼎特色农家</p>\r\n','logo.jpg','订餐电话：8888888*，8888777*<br/>\r\n提醒各位街坊,为了能让您在用餐时间能准时的享受到我们提供的餐食,请您提前半个小时订餐!  \r\n','ad.jpg',NULL,NULL);

#
# Table structure for table restaurantsalecount
#

DROP TABLE IF EXISTS `restaurantsalecount`;
CREATE TABLE `restaurantsalecount` (
  `ID` int(10) NOT NULL auto_increment,
  `START_DATE` datetime default NULL COMMENT '起始日期',
  `END_DATE` datetime default NULL COMMENT '终止日期',
  `START_TIME` varchar(5) default NULL COMMENT '起始时间',
  `END_TIME` varchar(5) default NULL COMMENT '终止时间',
  `BOOKING_COUNT` int(7) default '0' COMMENT '订单量(含)',
  `MENU_COUNT` int(8) default '0' COMMENT '菜单量',
  `AMOUNT` float(10,1) default '0.0' COMMENT '销售总额',
  `COST` float(10,1) default '0.0' COMMENT '估计成本',
  `PROFIT` float(10,1) default NULL COMMENT '估计利润',
  `REMARK` varchar(255) default NULL COMMENT '备注',
  `EDITOR` int(10) default NULL COMMENT '编辑人',
  `EDITTIME` datetime default NULL COMMENT '编辑时间',
  `OLDSTATUS` char(1) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_RESTAURANTSALECOUNT_EDITOR` (`EDITOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Table structure for table roles
#

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `ID` int(10) NOT NULL auto_increment COMMENT '主键',
  `NAME` varchar(40) NOT NULL default '' COMMENT '角色名',
  `CNNAME` varchar(40) default NULL COMMENT '角色中文名',
  `REMARK` varchar(100) default NULL COMMENT '备注',
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
INSERT INTO `roles` VALUES (1,'supermanager','超级管理员','拥有系统中所有的权限');
INSERT INTO `roles` VALUES (7,'admin','管理员','');

#
# Table structure for table roles_authorities
#

DROP TABLE IF EXISTS `roles_authorities`;
CREATE TABLE `roles_authorities` (
  `ROLE_ID` int(10) NOT NULL default '0' COMMENT '角色主键',
  `AUTHORITY_ID` int(10) NOT NULL default '0' COMMENT '权限主键',
  KEY `FK_ROLES_AUTHORITIES_ROLE` (`ROLE_ID`),
  KEY `FK_ROLES_AUTHORITIES_AUTHORITIES` (`AUTHORITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `roles_authorities` VALUES (1,2);
INSERT INTO `roles_authorities` VALUES (1,4);
INSERT INTO `roles_authorities` VALUES (1,13);
INSERT INTO `roles_authorities` VALUES (1,1);
INSERT INTO `roles_authorities` VALUES (1,1);
INSERT INTO `roles_authorities` VALUES (1,2);
INSERT INTO `roles_authorities` VALUES (1,4);
INSERT INTO `roles_authorities` VALUES (1,13);
INSERT INTO `roles_authorities` VALUES (1,28);
INSERT INTO `roles_authorities` VALUES (7,1);
INSERT INTO `roles_authorities` VALUES (1,32);
INSERT INTO `roles_authorities` VALUES (1,29);
INSERT INTO `roles_authorities` VALUES (1,46);
INSERT INTO `roles_authorities` VALUES (1,50);
INSERT INTO `roles_authorities` VALUES (1,56);
INSERT INTO `roles_authorities` VALUES (1,54);
INSERT INTO `roles_authorities` VALUES (1,55);
INSERT INTO `roles_authorities` VALUES (1,23);
INSERT INTO `roles_authorities` VALUES (1,57);
INSERT INTO `roles_authorities` VALUES (1,58);
INSERT INTO `roles_authorities` VALUES (1,27);
INSERT INTO `roles_authorities` VALUES (1,71);
INSERT INTO `roles_authorities` VALUES (1,33);
INSERT INTO `roles_authorities` VALUES (1,60);
INSERT INTO `roles_authorities` VALUES (1,61);
INSERT INTO `roles_authorities` VALUES (1,62);
INSERT INTO `roles_authorities` VALUES (1,63);
INSERT INTO `roles_authorities` VALUES (1,30);
INSERT INTO `roles_authorities` VALUES (1,31);
INSERT INTO `roles_authorities` VALUES (1,59);
INSERT INTO `roles_authorities` VALUES (1,34);
INSERT INTO `roles_authorities` VALUES (1,64);
INSERT INTO `roles_authorities` VALUES (1,35);
INSERT INTO `roles_authorities` VALUES (1,65);
INSERT INTO `roles_authorities` VALUES (1,66);
INSERT INTO `roles_authorities` VALUES (1,36);
INSERT INTO `roles_authorities` VALUES (1,67);
INSERT INTO `roles_authorities` VALUES (1,68);
INSERT INTO `roles_authorities` VALUES (1,70);
INSERT INTO `roles_authorities` VALUES (7,32);
INSERT INTO `roles_authorities` VALUES (7,29);
INSERT INTO `roles_authorities` VALUES (7,41);
INSERT INTO `roles_authorities` VALUES (7,46);
INSERT INTO `roles_authorities` VALUES (7,50);
INSERT INTO `roles_authorities` VALUES (7,56);
INSERT INTO `roles_authorities` VALUES (7,28);
INSERT INTO `roles_authorities` VALUES (7,2);
INSERT INTO `roles_authorities` VALUES (7,54);
INSERT INTO `roles_authorities` VALUES (7,55);
INSERT INTO `roles_authorities` VALUES (7,23);
INSERT INTO `roles_authorities` VALUES (7,4);
INSERT INTO `roles_authorities` VALUES (7,57);
INSERT INTO `roles_authorities` VALUES (7,58);
INSERT INTO `roles_authorities` VALUES (7,27);
INSERT INTO `roles_authorities` VALUES (7,13);
INSERT INTO `roles_authorities` VALUES (7,71);
INSERT INTO `roles_authorities` VALUES (7,33);
INSERT INTO `roles_authorities` VALUES (7,60);
INSERT INTO `roles_authorities` VALUES (7,61);
INSERT INTO `roles_authorities` VALUES (7,62);
INSERT INTO `roles_authorities` VALUES (7,63);
INSERT INTO `roles_authorities` VALUES (7,30);
INSERT INTO `roles_authorities` VALUES (7,31);
INSERT INTO `roles_authorities` VALUES (7,59);
INSERT INTO `roles_authorities` VALUES (7,34);
INSERT INTO `roles_authorities` VALUES (7,64);
INSERT INTO `roles_authorities` VALUES (7,35);
INSERT INTO `roles_authorities` VALUES (7,65);
INSERT INTO `roles_authorities` VALUES (7,66);
INSERT INTO `roles_authorities` VALUES (7,36);
INSERT INTO `roles_authorities` VALUES (7,67);
INSERT INTO `roles_authorities` VALUES (7,68);
INSERT INTO `roles_authorities` VALUES (7,70);
INSERT INTO `roles_authorities` VALUES (1,41);

#
# Table structure for table salestatistics
#

DROP TABLE IF EXISTS `salestatistics`;
CREATE TABLE `salestatistics` (
  `ID` int(11) NOT NULL auto_increment,
  `OLD_STATUS` char(1) default NULL,
  `PAY_MODE` char(1) default NULL,
  `START_DATE` datetime default NULL,
  `END_DATE` datetime default NULL,
  `BOOKING_COUNT` int(7) default '0',
  `MENU_COUNT` int(8) default '0',
  `PRICE` float(10,1) default '0.0',
  `COST` float(10,1) default '0.0',
  `PROFIT` float(10,1) default '0.0',
  `REMARK` varchar(200) default NULL,
  `EDITOR` int(11) default '0',
  `EDITTIME` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_salestatistics_EDITOR` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
INSERT INTO `salestatistics` VALUES (3,'','','2009-09-01','2009-09-30',23,35,418,143,269,'',1,'2009-09-30 23:37:22');
INSERT INTO `salestatistics` VALUES (4,'','','2009-10-01','2009-10-10',8,42,455,119.2,289.8,'dfa',1,'2009-10-10 21:21:59');

#
# Table structure for table submenu
#

DROP TABLE IF EXISTS `submenu`;
CREATE TABLE `submenu` (
  `ID` int(10) NOT NULL auto_increment,
  `MENUTYPE` int(10) default NULL COMMENT '菜单类型',
  `CODE` varchar(20) NOT NULL default '' COMMENT '编号',
  `NAME` varchar(80) NOT NULL COMMENT '名称',
  `PHOTO` varchar(40) default NULL COMMENT '照片',
  `PRICE` float(6,1) default '0.0' COMMENT '单价',
  `COST` float(6,1) default '0.0' COMMENT '估计成本',
  `PROFIT` float(6,1) default '0.0' COMMENT '估计利润',
  `SERIAL` tinyint(4) default NULL COMMENT '排序',
  `DESCRIPTION` text COMMENT '描述',
  `STATUS` char(1) default NULL COMMENT '状态',
  `STOCK` tinyint(4) default '0' COMMENT '库存量',
  `SELL_NUMBER` int(11) default '0' COMMENT '销售量',
  `EDITOR` int(10) default NULL COMMENT '编辑人',
  `EDITTIME` datetime default NULL COMMENT '编辑时间',
  `IS_RECOMMEND` char(1) default '2' COMMENT '是否推荐菜单',
  `RECOMMEND_REASON` varchar(50) default NULL COMMENT '推荐理由',
  `SUMMARY` varchar(100) default NULL COMMENT '摘要',
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `CODE` (`CODE`),
  UNIQUE KEY `MENUTYPE` (`MENUTYPE`,`NAME`),
  KEY `FK_SUBMENU_EDITOR_USERS` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
INSERT INTO `submenu` VALUES (18,13,'0011','薄荷香煎金丝鱼','20090930232730.jpg',10,5,5,1,'薄荷香煎金丝鱼','1',86,14,7,'2009-10-14 10:03:59','2','美味可口',NULL);
INSERT INTO `submenu` VALUES (23,17,'0012','干煎盐酥带鱼','20090912171744.jpg',20,10,10,2,'干煎盐酥带鱼','1',100,1,NULL,'2009-10-05 22:59:24','2','干煎盐酥带鱼',NULL);
INSERT INTO `submenu` VALUES (24,13,'0013','冰梅秘酱蒸鲶鱼','20090912171819.jpg',30,10,20,3,'冰梅秘酱蒸鲶鱼','1',88,17,NULL,'2009-09-30 09:32:22','1','冰梅秘酱蒸鲶鱼',NULL);
INSERT INTO `submenu` VALUES (25,13,'0014','煎封秋刀鱼','20090912171908.jpg',5,1,4,4,'煎封秋刀鱼','1',13,7,NULL,NULL,'2','煎封秋刀鱼',NULL);
INSERT INTO `submenu` VALUES (26,13,'0021','金牌好味骨','20090912172231.jpg',6,2,4,1,'金牌好味骨','1',74,36,NULL,'2009-10-17 16:43:23','1','金牌好味骨',NULL);
INSERT INTO `submenu` VALUES (27,14,'0022','啤酒鸡翅','20090912172315.jpg',5,2,3,4,'啤酒鸡翅','1',94,7,NULL,'2009-09-30 09:32:30','2','啤酒鸡翅',NULL);
INSERT INTO `submenu` VALUES (28,14,'0023','茄汁免治猪扒','20090912172407.jpg',20,5,15,5,'茄汁免治猪扒','1',89,14,NULL,'2009-09-30 09:32:34','1','茄汁免治猪扒',NULL);
INSERT INTO `submenu` VALUES (29,14,'0024','三鲜蒸冬瓜','20090912172454.jpg',4,1,3,7,'三鲜蒸冬瓜','1',86,15,NULL,'2009-09-30 09:32:39','1','三鲜蒸冬瓜',NULL);
INSERT INTO `submenu` VALUES (30,15,'0031','黑木耳红枣汤','20090912172854.jpg',10,2,8,7,'黑木耳红枣汤','1',84,17,NULL,'2009-09-30 09:32:44','2','黑木耳红枣汤',NULL);
INSERT INTO `submenu` VALUES (31,15,'0032','葫芦海鲜汤','20090912172926.jpg',8,2,6,8,'葫芦海鲜汤','1',80,23,NULL,'2009-09-30 09:32:54','1','葫芦海鲜汤',NULL);
INSERT INTO `submenu` VALUES (32,15,'0033','老鸭冬笋汤','20090912173032.jpg',10,5,5,9,'老鸭冬笋汤','1',77,27,NULL,'2009-09-30 09:32:58','2','',NULL);
INSERT INTO `submenu` VALUES (33,15,'0034','莲藕黄豆排骨汤','20090912173144.jpg',10,2,8,9,'','1',100,NULL,NULL,'2009-09-30 09:33:00','2','',NULL);
INSERT INTO `submenu` VALUES (34,15,'0035','芋艿白果老鸭煲','20090912173217.jpg',9,2,7,8,'','1',99,1,NULL,'2009-09-30 09:32:51','2','',NULL);
INSERT INTO `submenu` VALUES (35,15,'0036','猪蹄花生煲','20090912173301.jpg',20,5,15,7,'','1',99,1,NULL,'2009-09-30 09:32:43','2','',NULL);
INSERT INTO `submenu` VALUES (36,16,'0041','大寒糯米饭','20090912173902.jpg',10,2,8,1,'','1',100,NULL,NULL,'2009-09-30 09:33:03','2','',NULL);
INSERT INTO `submenu` VALUES (37,16,'0042','荷香糯米鸡','20090912173928.jpg',15,5,10,1,'','1',99,1,NULL,'2009-09-30 09:33:05','2','',NULL);
INSERT INTO `submenu` VALUES (38,16,'0043','烤鸭腿盖饭','20090912173956.jpg',20,5,15,2,'','1',99,1,NULL,'2009-09-30 09:33:13','2','',NULL);
INSERT INTO `submenu` VALUES (39,14,'0044','腊肉豌豆饭','20090912174024.jpg',15,2,13,2,'','1',100,NULL,NULL,'2009-10-17 16:43:48','2','',NULL);
INSERT INTO `submenu` VALUES (40,16,'0045','蜜汁鸡腿饭','20090912174044.jpg',10,5,5,5,'','1',100,NULL,NULL,'2009-09-30 09:33:24','2','',NULL);
INSERT INTO `submenu` VALUES (41,16,'0046','洋葱牛肉饭','20090912174107.jpg',20,15,5,5,'','1',100,NULL,NULL,'2009-09-30 09:33:17','2','',NULL);
INSERT INTO `submenu` VALUES (42,16,'0047','紫薯红枣饭','20090912174128.jpg',5,2,3,2,'','1',100,NULL,NULL,'2009-09-30 09:33:11','2','',NULL);
INSERT INTO `submenu` VALUES (43,17,'0051','春饼','20090912175252.jpg',1,0.2,0.8,1,'','1',93,7,NULL,NULL,'1','',NULL);
INSERT INTO `submenu` VALUES (44,17,'0052','牛肉汤河粉','20090912175351.jpg',5,2,3,2,'','1',100,NULL,NULL,NULL,'2','',NULL);
INSERT INTO `submenu` VALUES (45,17,'0053','牛肉丸汤粉丝','20090912175433.jpg',5,1,4,1,'','1',100,NULL,NULL,'2009-09-30 09:33:26','2','',NULL);
INSERT INTO `submenu` VALUES (46,17,'0054','潮州大鱼丸','20090912175514.jpg',10,2,8,1,'','1',99,1,NULL,'2009-09-30 09:33:28','2','',NULL);
INSERT INTO `submenu` VALUES (47,17,'0055','茶香海参','20090912175537.jpg',20,5,15,1,'','1',100,NULL,NULL,'2009-09-30 09:33:30','2','',NULL);

#
# Table structure for table users
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(10) NOT NULL auto_increment COMMENT '主键',
  `LOGIN_NAME` varchar(20) NOT NULL default '' COMMENT '登录名',
  `PASSWORD` varchar(60) default NULL COMMENT '密码',
  `NAME` varchar(20) default NULL COMMENT '真实姓名',
  `EMAIL` varchar(50) default NULL COMMENT '电邮',
  `LEVEL` char(1) default NULL COMMENT '用户级别',
  `SEX` char(1) default NULL COMMENT '性别',
  `PHONE` varchar(30) default NULL COMMENT '联系电话',
  `LOGINCOUNT` int(7) default NULL COMMENT '登录次数',
  `LASTTIME` datetime default NULL COMMENT '上次登录时间',
  `FINALLYTIME` datetime default NULL COMMENT '最后一次登录时间',
  `EDITOR` int(10) default NULL COMMENT '编辑人',
  `EDITTIME` datetime default NULL COMMENT '编辑时间',
  `REMARK` varchar(255) default NULL COMMENT '备注',
  `ENABLED` char(1) default NULL COMMENT '是否启用',
  `ACCOUNT_NON_EXPIRED` char(1) default NULL COMMENT '帐号是否过期',
  `ACCOUNT_NON_LOCKED` char(1) default NULL COMMENT '帐号是否锁定',
  `CREDENTIALS_NON_EXPIRED` char(1) default NULL COMMENT '证件号是否过期',
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `LOGIN_NAME` (`LOGIN_NAME`),
  KEY `FK_USERS_EDITOR` (`EDITOR`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
INSERT INTO `users` VALUES (1,'tienting','c46c1e6d98c969101fa725ec7984ab84','admin','',NULL,NULL,NULL,2,'2009-10-17 16:18:48','2009-10-17 16:42:49',NULL,NULL,NULL,'1','1','1','1');
INSERT INTO `users` VALUES (7,'admin','b594510740d2ac4261c1b2fe87850d08','admin','',NULL,NULL,NULL,3,'2009-10-17 16:32:40','2009-10-17 16:33:00',NULL,NULL,NULL,'1','1','1','1');
INSERT INTO `users` VALUES (9,'wei','bcd8652394e5e14d5b452ee492030ae1','hjw','hjw@tienting.com',NULL,NULL,NULL,0,'2009-10-14 17:47:27','2009-10-14 21:50:06',NULL,NULL,NULL,'1','1','1','1');

#
# Table structure for table users_roles
#

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `USER_ID` int(10) NOT NULL default '0' COMMENT '用户',
  `ROLE_ID` int(10) NOT NULL default '0' COMMENT '角色',
  KEY `FK_USERS_ROLES_ROLES` (`ROLE_ID`),
  KEY `FK_USERS_ROLES_USERS` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `users_roles` VALUES (1,1);
INSERT INTO `users_roles` VALUES (7,7);
INSERT INTO `users_roles` VALUES (9,7);

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
