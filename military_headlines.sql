CREATE DATABASE  IF NOT EXISTS `military_headlines` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `military_headlines`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: military_headlines
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `checksum` varchar(45) DEFAULT NULL,
  `nickname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ida_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'2017-07-03 23:41:12','2017-07-03 23:41:12','admin','40bd001563085fc35165329ea1ff5c5ecbdbbeef','52f10719269d19320896e76c9f8901ba','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `author` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `share_count` int(10) unsigned NOT NULL DEFAULT '0',
  `like_count` int(10) unsigned NOT NULL DEFAULT '0',
  `title` varchar(200) NOT NULL,
  `image1` varchar(200) DEFAULT NULL,
  `image2` varchar(200) DEFAULT NULL,
  `image3` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'2017-06-29 20:13:24','2017-06-29 20:13:24','作者1','推荐',0,0,'标题1',NULL,NULL,NULL),(2,'2017-06-29 20:13:27','2017-06-29 20:13:27','作者2','推荐',0,1,'标题2',NULL,NULL,NULL),(3,'2017-06-29 20:13:28','2017-06-29 20:13:28','作者3','推荐',0,0,'标题3',NULL,NULL,NULL),(4,'2017-07-01 08:32:51','2017-07-01 08:32:51','作者','热点',0,0,'热点新闻',NULL,NULL,NULL),(5,'2017-07-01 08:59:37','2017-07-01 08:59:37','123','123',0,0,'123',NULL,NULL,NULL),(6,'2017-07-01 09:02:38','2017-07-01 09:02:38','军事作者','军事',0,0,'军事新闻',NULL,NULL,NULL),(7,'2017-07-01 09:03:27','2017-07-01 09:03:27','123','123',0,0,'123',NULL,NULL,NULL),(8,'2017-07-01 09:04:56','2017-07-01 09:04:56','国际作者','国际',0,0,'国际新闻',NULL,NULL,NULL),(9,'2017-07-01 09:26:18','2017-07-01 09:26:18','123','123',0,0,'123',NULL,NULL,NULL);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_content`
--

DROP TABLE IF EXISTS `article_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_content` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` longtext NOT NULL,
  `article_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_content`
--

LOCK TABLES `article_content` WRITE;
/*!40000 ALTER TABLE `article_content` DISABLE KEYS */;
INSERT INTO `article_content` VALUES (1,'2017-06-30 11:10:17','2017-06-30 11:10:17','文章1文章1文章1文章1文章1文章1文章1文章1文章1文章1文章1文章1文章1文章1文章1文章1文章1文章1文章1',1),(2,'2017-07-01 00:35:32','2017-07-01 00:35:32','作者：均云笈\n链接：https://www.zhihu.com/question/38497186/answer/76695270\n来源：知乎\n著作权归作者所有，转载请联系作者获得授权。\n\n整体军力上，不管是全球火力指数(Global Firepower)的权威评价还是美国《商业内幕》的评价，中国军力整体位居世界第三，仅次于美俄，英国《简氏防务周刊》以前把中国综合军力放在第四，倒是号称海自一周团灭中国海军的日本，《东京新闻》却声称中国综合军力世界第二，全球200多号国家，中国军队实力属于什么水平其实早有定论，其他刊物怎么评价的我不清楚，但是全球火力评价的标准囊括了除核武器以外的五十余项综合指标，用训练不够啊、没经历实战之类的借口是很难驳斥这一排名的。此外，现代战争打的是体系、信息化，2015年中国的在轨卫星超过俄罗斯为140颗，仅次于美国，北斗卫星定位系统虽然晚于美国GPS、俄罗斯GLONASS发射组网，但覆盖面积、探测精度、运用广度仅次于GPS，远超毛子的格洛纳斯，至于当年涮过中国的欧洲伽利略卫星导航系统，现在在轨卫星数量好像仅有六颗，中国北斗在用在轨卫星则有十七颗，2000年发射的北斗-1A、北斗-1B、北斗-1D已然停用，军用领域可能已经达到厘米级定位精度（官方之前的规划是2018年达到厘米级），而且95%的关键部件均为国产，再加上快舟固体机动小型运载火箭在战时可以尽可能弥补被打掉的卫星，信息化水平和保障能力上，中国排个第二问题真的不大。更别说中国多次成功的反卫星试验、反导试验、超高速飞行器试验、高能武器试验……人类目前公开的能掌握的最尖端武器的矛和盾中国都已经掌握并处于紧跟美帝的领先状态。',2),(3,'2017-07-01 00:36:53','2017-07-01 00:36:53','作者：均云笈\n链接：https://www.zhihu.com/question/38497186/answer/76695270\n来源：知乎\n著作权归作者所有，转载请联系作者获得授权。\n\n1、因为中国海军目前缺乏远洋作战的经验和能力，具体评价参加几次和外军联合军演对方的评价，百年海军不是硬件够了就能战斗力瞬间提升，瓦格良终究只是训练舰，航母运用仍需要很长时间的磨合，组建航母编队也一样需要磨合，恕我直言在正式形成战斗力之前，面对亚太地区各国强大的水下力量，航母不是威慑力，反而可能是累赘；2、中国水下核潜艇性能偏弱，具体参见对094、093性能的评价，期待095、096的服役，中国的常规潜艇老旧较多，海军反潜能力有待提高，可面对的又是世界数一数二的反潜强国（美日），同时他们也是潜艇强国；3、中国缺乏海外军事基地，也没有足够的盟友可以提供足够的军事基地进行补给，也没有足够的同盟提供支援，光靠综合补给舰补给，远洋作战能力堪忧。个人之见世界第五是比较中肯的，这些不足海军都在完善。\n二、为什么说中国新航母服役能和英国并列第二超越法俄日？\n英国：伊丽莎白女王号的平台，确实无任何槽点，很先进，可是我没想通英国人是怎么想的，不加弹射器？固定翼预警机怎么办？没有预警机航母打毛啊？舰载机全靠美帝F-35B，掉一架少一架，持续作战能力堪忧，中国航母因为国际环境，从头到尾都要自研，哪怕没有弹射器，舰载机是自己的啊，说并列是因为伊丽莎白女王号的技术确实先进，要说作战能力，性能啥都不说，伊丽莎白女王只有一艘，中国加自建航母有两艘，英国怎么解决轮值大修的问题？\n法国：戴高乐不能再吐槽了，两台K-15核潜艇动力让戴高乐天生体虚，影响整个航母编队的作战效率；戴高乐天生质量堪忧，试航掉桨叶啊，航母长度不够为起降预警机重新加长舰体啊，法国人自己都无语了，航母不是搭积木，这都是安全隐患，也出过不少问题（有兴趣可以自行收集戴高乐的黑历史），这要放到战场上就是团灭的节奏；戴高乐引进的前弹射器占用跑道，航母舰载机起飞效率完全不如美帝同类弹射器的航母，而且戴高乐和辽宁号建造时差很大，法国下代航母不见踪影而中国已然略有眉目，制造能力和经验上超过法国我认为不算太难接受。\n俄罗斯：库兹涅佐夫号已暮暮老矣，大修不断不说，舰载机都只能换米格—29K，航母海战的胜败很大程度取决于航程和先敌发现打击能力，中国有源相控阵雷达J-10B已经列装，J-16应该没问题，J-15有还是没有呢？同样有有源相控阵雷达，比比机头大小，谁能放功率更大探测能力更远的雷达？J-15的航程和载弹量和海支点比也完爆，辽宁号又是全舰重新设计改造的，比库兹涅佐夫号强一点问题不是太大吧？而俄罗斯这么多年连大型驱逐舰都快造不出来了，中国海军参观俄罗斯海军的感慨是“厚重的历史感……”航母建造能力上，中国目前超越俄罗斯一点问题都没有。\n日本：直升机航母号称准航母，但是六万吨以上的现代化大型航母，日本没有任何制造使用经验，而且直升机航母要改固定翼舰载机只能用垂直起降战斗机，舰体还需要进行针对性改造，还是那句话，航母建造不是搭积木，随意改造甲板、升降机也不是说说就能实现，实现了也存在很大的隐患，故而真的懂的人不会去信那些日本航母威胁，大多揣着明白装糊涂跟着附和下日本海军威胁多大，故而制造能力上超越日本是最轻松的。\n即使在航母战斗力上，中国新航母服役，航母战斗群形成完整战力，航母战力仅次于美英甚至超过英国都不是太大问题。',3),(4,'2017-07-01 08:32:51','2017-07-01 08:32:51','内容内容内容内容内容内容内容内容内容内容内容',4),(5,'2017-07-01 08:59:37','2017-07-01 08:59:37','123',5),(6,'2017-07-01 09:02:38','2017-07-01 09:02:38','军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻军事新闻',6),(7,'2017-07-01 09:03:27','2017-07-01 09:03:27','123',7),(8,'2017-07-01 09:04:56','2017-07-01 09:04:56','国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻国际新闻',8),(9,'2017-07-01 09:26:18','2017-07-01 09:26:18','123',9);
/*!40000 ALTER TABLE `article_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collect`
--

DROP TABLE IF EXISTS `collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collect` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(10) unsigned NOT NULL,
  `article_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect`
--

LOCK TABLES `collect` WRITE;
/*!40000 ALTER TABLE `collect` DISABLE KEYS */;
INSERT INTO `collect` VALUES (5,'2017-07-01 00:36:15','2017-07-01 00:36:15',1,2);
/*!40000 ALTER TABLE `collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` varchar(200) NOT NULL,
  `article_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'2017-06-30 11:30:56','2017-06-30 11:30:56','文章1评论1文章1评论1文章1评论1文章1评论1文章1评论1文章1评论1文章1评论1文章1评论1',1,1),(2,'2017-06-30 11:32:02','2017-06-30 11:32:02','文章1评论2文章1评论2文章1评论2文章1评论2文章1评论2文章1评论2',1,1),(3,'2017-06-30 13:01:13','2017-06-30 13:01:13','文章1评论3文章1评论3',1,1),(4,'2017-06-30 13:22:36','2017-06-30 13:22:36','文章1评论3文章1评论3',1,1),(5,'2017-07-01 00:02:19','2017-07-01 00:02:19','测试',1,1),(6,'2017-07-01 00:02:20','2017-07-01 00:02:20','测试',1,1),(7,'2017-07-01 00:03:39','2017-07-01 00:03:39','评论评论',1,1),(8,'2017-07-01 00:05:49','2017-07-01 00:05:49','666',1,1),(9,'2017-07-01 00:38:14','2017-07-01 00:38:14','评论评论',2,1),(10,'2017-07-01 00:41:23','2017-07-01 00:41:23','666',3,1),(11,'2017-07-01 11:48:33','2017-07-01 11:48:33','评论',1,1);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_article`
--

DROP TABLE IF EXISTS `like_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(10) unsigned NOT NULL,
  `article_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_article`
--

LOCK TABLES `like_article` WRITE;
/*!40000 ALTER TABLE `like_article` DISABLE KEYS */;
INSERT INTO `like_article` VALUES (19,'2017-07-01 00:41:16','2017-07-01 00:41:16',1,2);
/*!40000 ALTER TABLE `like_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `push`
--

DROP TABLE IF EXISTS `push`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `push` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(45) NOT NULL,
  `content` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `push`
--

LOCK TABLES `push` WRITE;
/*!40000 ALTER TABLE `push` DISABLE KEYS */;
/*!40000 ALTER TABLE `push` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recommend`
--

DROP TABLE IF EXISTS `recommend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recommend` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `from_article_id` int(10) unsigned NOT NULL,
  `to_article_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recommend`
--

LOCK TABLES `recommend` WRITE;
/*!40000 ALTER TABLE `recommend` DISABLE KEYS */;
/*!40000 ALTER TABLE `recommend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `checksum` varchar(45) DEFAULT NULL,
  `nickname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2017-06-30 20:34:40','2017-06-30 20:34:40','123','40bd001563085fc35165329ea1ff5c5ecbdbbeef','e3f23eb33c73f0f3cd29cf5921d9e998','aaa'),(2,'2017-06-30 20:35:11','2017-06-30 20:35:11','1234','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','946f3a701dae3e73dda896c0c20fda69','aaa'),(3,'2017-06-30 21:00:23','2017-06-30 21:00:23','12345','8cb2237d0679ca88db6464eac60da96345513964','3320c11d00a69aeffe3d8f7f2f652fda','abc'),(4,'2017-06-30 21:20:39','2017-06-30 21:20:39','123456','7c4a8d09ca3762af61e59520943dc26494f8941b','2696cbccb188f3a3e580e400f811c64b','123'),(5,'2017-06-30 22:04:47','2017-06-30 22:04:47','1234567','40bd001563085fc35165329ea1ff5c5ecbdbbeef','f4c08c41a06705b17be46dd94dc48047','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-03 23:44:15
