/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : db_study

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2015-06-03 21:49:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `title` varchar(40) default NULL COMMENT '文章标题',
  `content` text COMMENT '文章内容',
  `subject_tpye_id` int(11) default NULL COMMENT '类型编号',
  PRIMARY KEY  (`id`),
  KEY `subject_tpye_id` (`subject_tpye_id`),
  CONSTRAINT `tb_subject_ibfk_1` FOREIGN KEY (`subject_tpye_id`) REFERENCES `tb_subject_type` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('1', '语言研究的乔姆斯基时代该终结了--从刺激贫乏论说起', '刺激贫乏论是乔姆斯基语言先天论的基石。从科学盖然性标准而不是从逻辑的角度来看，刺激贫乏论的真假决定语言先天论的真假（Sampson 2002：97），决定了乔氏理论大厦的安危，因此，要终结语言研究的乔姆斯基时代必须从终结刺激贫乏论开始。由于乔氏阵营无法否定儿童语言习得中刺激的存在及其作用，刺激贫乏论在其理论中是把双刃剑，表面上支撑了纯粹的先天论，实际上使其烙上了“先天—后天交互论”的印记。在缺乏充分的前期研究基础的背景下提出的刺激贫乏论在跟踪研究中又陷入了“语言学家/语法学家模式”的泥潭。而大量研究表明，无论从质的还是量的方面来看，刺激都不贫乏。刺激贫乏论是错误的，它低估了人类一般用途学习机制的作用，或者说，它低估了儿童语言环境中提供的约束条件（即刺激）的作用（Brooks & Kempe 2014：389），基于刺激贫乏论的语言先天论也是错误的，从这个角度上来说，语言研究的乔姆斯基时代该终结了，我们应该开启语言研究的习得时代。', '1');
INSERT INTO `tb_article` VALUES ('2', '论中西方修辞之比较', '西方古典修辞学起源于公元前5世纪，中国传统修辞学萌芽于先秦时期，二者时间上有重合的阶段。由于政治制度、社会背景不同，中西方修辞学的发展呈现出不同的面貌。随着现代修辞学的建立，中西方修辞观得到了完善和发展，研究内容和视角有了前所未有的一致。', '1');
INSERT INTO `tb_article` VALUES ('3', '关于动词论元结构及习得研究的评述', '动词论元结构是句法学的重要概念，同时也受到语义学的关注，因此它是语义-句法学领域的重要课题之一。至今已有不少文献从不同方面对论元结构做出了深入研究。本研究从动词论元结构理论与习得研究两个方面进行梳理，对存在的问题和不足进行评述，并为今后进行进一步研究的方向提出看法。', '1');
INSERT INTO `tb_article` VALUES ('4', '论佛教传入对中古语言文化的影响--以《高僧传》为例', '语言是各种文明的记录和传承。某个历史时期的发展和转变基本上较早地表现在语言上，所以进行语言这种文化现象的研究是非常有意义的。中古时期是中国古代文明的一个重要区间，佛教的传入对于整个时代产生了巨大的影响，而这些深刻的历史变化毫无保留地反映在了语言文化中，《高僧传》是当时比较具有典型意义的代表书籍，它反映出的语言现象基本上可以说明佛教的传入对中古语言文化现象的诸多影响。', '1');
INSERT INTO `tb_article` VALUES ('5', '英汉衔接手段对比分析', '衔接是构成语篇的重要条件之一，衔接是语篇的有形网络，体现在语篇的表层结构上。韩礼德将衔接界定为一个语义概念，是存在语篇内部的能够使全文成为语篇的各种意义关系。衔接被看作是生成语篇的必要条件之一。在《英语的衔接》一书中，韩礼德与哈桑对英语中的衔接现象作了详细的划分和阐述。他们把英语的衔接手段划分为两类：语法衔接（包括照应、替代、省略和连接）和词汇衔接。本文以韩礼德和哈桑的衔接理论为依据，对英汉衔接手段的异同进行对比分析。掌握这些差异对英汉翻译实践、翻译理论和翻译教学等具有一定的启发。', '1');
INSERT INTO `tb_article` VALUES ('6', '抗日战争的胜利及其意义', '(1)中国人民抗日战争在世界反法西斯战争中的地位(2)抗日战争胜利的意义、原因和基本经验', '5');
INSERT INTO `tb_article` VALUES ('7', '坚持抗战、团结、进步的方针', '(1)统一战线中的独立自主原则(2)巩固抗日民族统一战线的策略总方针', '5');

-- ----------------------------
-- Table structure for tb_discipline
-- ----------------------------
DROP TABLE IF EXISTS `tb_discipline`;
CREATE TABLE `tb_discipline` (
  `id` int(11) NOT NULL auto_increment COMMENT '题目编号',
  `question` varchar(255) default NULL COMMENT '问题',
  `answers` varchar(255) default NULL COMMENT '答案',
  `score` int(11) default NULL COMMENT '分数',
  `subject_tpye_id` int(11) default NULL COMMENT '类型编号',
  PRIMARY KEY  (`id`),
  KEY `subject_tpye_id` (`subject_tpye_id`),
  CONSTRAINT `tb_subject_ibfk_2` FOREIGN KEY (`subject_tpye_id`) REFERENCES `tb_subject_type` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_discipline
-- ----------------------------
INSERT INTO `tb_discipline` VALUES ('1', '下列字母的排列顺序错误的一组是______?', 'JMMKLG', '5', '1');
INSERT INTO `tb_discipline` VALUES ('2', '下列大小形式互换完全正确的是_______?', 'Q p  R r', '5', '1');
INSERT INTO `tb_discipline` VALUES ('3', '读音节，注意声调，选出调号有错误的一组______?', '桃李争妍táo lǐ zhēng yán', '5', '1');
INSERT INTO `tb_discipline` VALUES ('4', '下面字形和读音都正确的一组是______?', '一担（dàn）', '5', '1');

-- ----------------------------
-- Table structure for tb_discipline_option
-- ----------------------------
DROP TABLE IF EXISTS `tb_discipline_option`;
CREATE TABLE `tb_discipline_option` (
  `id` int(11) NOT NULL auto_increment COMMENT '选项编号',
  `discipline_id` int(11) default NULL COMMENT '题目编号',
  `content` varchar(255) default NULL COMMENT '选项内容',
  PRIMARY KEY  (`id`),
  KEY `discipline_id` (`discipline_id`),
  CONSTRAINT `tb_discipline_option_ibfk_1` FOREIGN KEY (`discipline_id`) REFERENCES `tb_discipline` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_discipline_option
-- ----------------------------
INSERT INTO `tb_discipline_option` VALUES ('1', '1', ' ABCDEF');
INSERT INTO `tb_discipline_option` VALUES ('2', '1', 'JMMKLG');
INSERT INTO `tb_discipline_option` VALUES ('3', '1', 'OPQRST');
INSERT INTO `tb_discipline_option` VALUES ('4', '1', 'UVWXYZ');
INSERT INTO `tb_discipline_option` VALUES ('5', '2', 'T t  Wu');
INSERT INTO `tb_discipline_option` VALUES ('6', '2', 'Y y  N n');
INSERT INTO `tb_discipline_option` VALUES ('7', '2', 'G g  L I');
INSERT INTO `tb_discipline_option` VALUES ('8', '2', 'Q p  R r');
INSERT INTO `tb_discipline_option` VALUES ('9', '3', '水平如镜shuǐ píng rú jìng');
INSERT INTO `tb_discipline_option` VALUES ('10', '3', '桃李争妍táo lǐ zhēng yán');
INSERT INTO `tb_discipline_option` VALUES ('11', '3', '开卷有益kāi juǎn yǒu yì');
INSERT INTO `tb_discipline_option` VALUES ('12', '3', '完好无损wán hǎo wú sǔn');
INSERT INTO `tb_discipline_option` VALUES ('13', '4', '倾（qīng）盆大雨');
INSERT INTO `tb_discipline_option` VALUES ('14', '4', '磨（mò）房');
INSERT INTO `tb_discipline_option` VALUES ('15', '4', '一担（dàn）');
INSERT INTO `tb_discipline_option` VALUES ('16', '4', '眼眶（kuāng）');

-- ----------------------------
-- Table structure for tb_questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `tb_questionnaire`;
CREATE TABLE `tb_questionnaire` (
  `id` int(11) NOT NULL auto_increment COMMENT '问卷编号',
  `name` varchar(20) default NULL COMMENT '问卷名称',
  `subject_type_id` int(11) default NULL COMMENT '科目编号',
  PRIMARY KEY  (`id`),
  KEY `subject_type_id` (`subject_type_id`),
  CONSTRAINT `tb_questionnaire_ibfk_1` FOREIGN KEY (`subject_type_id`) REFERENCES `tb_subject_type` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_questionnaire
-- ----------------------------
INSERT INTO `tb_questionnaire` VALUES ('1', '语文问卷1', '1');
INSERT INTO `tb_questionnaire` VALUES ('2', '语文问卷2', '1');
INSERT INTO `tb_questionnaire` VALUES ('3', '语文问卷3', '1');
INSERT INTO `tb_questionnaire` VALUES ('4', '语文问卷4', '1');
INSERT INTO `tb_questionnaire` VALUES ('5', '语文问卷5', '1');
INSERT INTO `tb_questionnaire` VALUES ('6', '数学问卷1', '2');
INSERT INTO `tb_questionnaire` VALUES ('7', '数学问卷2', '2');
INSERT INTO `tb_questionnaire` VALUES ('8', '数学问卷3', '2');
INSERT INTO `tb_questionnaire` VALUES ('9', '数学问卷4', '2');
INSERT INTO `tb_questionnaire` VALUES ('10', '数学问卷5', '2');
INSERT INTO `tb_questionnaire` VALUES ('11', '英语问卷1', '3');
INSERT INTO `tb_questionnaire` VALUES ('12', '英语问卷2', '3');
INSERT INTO `tb_questionnaire` VALUES ('13', '英语问卷3', '3');
INSERT INTO `tb_questionnaire` VALUES ('14', '英语问卷4', '3');
INSERT INTO `tb_questionnaire` VALUES ('15', '英语问卷5', '3');

-- ----------------------------
-- Table structure for tb_questionnaire_discipline
-- ----------------------------
DROP TABLE IF EXISTS `tb_questionnaire_discipline`;
CREATE TABLE `tb_questionnaire_discipline` (
  `id` int(11) NOT NULL auto_increment COMMENT '问卷题目编号',
  `questionnaire_id` int(11) default NULL COMMENT '问卷编号',
  `discipline_id` int(11) default NULL COMMENT '题目编号',
  PRIMARY KEY  (`id`),
  KEY `questionnaire_id` (`questionnaire_id`),
  KEY `discipline_id` (`discipline_id`),
  CONSTRAINT `tb_questionnaire_discipline_ibfk_1` FOREIGN KEY (`questionnaire_id`) REFERENCES `tb_questionnaire` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_questionnaire_discipline_ibfk_2` FOREIGN KEY (`discipline_id`) REFERENCES `tb_discipline` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_questionnaire_discipline
-- ----------------------------
INSERT INTO `tb_questionnaire_discipline` VALUES ('1', '1', '1');
INSERT INTO `tb_questionnaire_discipline` VALUES ('2', '1', '2');
INSERT INTO `tb_questionnaire_discipline` VALUES ('3', '1', '3');
INSERT INTO `tb_questionnaire_discipline` VALUES ('4', '1', '4');

-- ----------------------------
-- Table structure for tb_subject_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_subject_type`;
CREATE TABLE `tb_subject_type` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `name` varchar(255) default NULL COMMENT '1语文,2数学,3英语,4诗歌,5政治6，历史,7健康心里,8生活百科',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_subject_type
-- ----------------------------
INSERT INTO `tb_subject_type` VALUES ('1', '语文');
INSERT INTO `tb_subject_type` VALUES ('2', '数学');
INSERT INTO `tb_subject_type` VALUES ('3', '英语');
INSERT INTO `tb_subject_type` VALUES ('4', '诗歌');
INSERT INTO `tb_subject_type` VALUES ('5', '政治');
INSERT INTO `tb_subject_type` VALUES ('6', '历史');
INSERT INTO `tb_subject_type` VALUES ('7', '健康心里');
INSERT INTO `tb_subject_type` VALUES ('8', '生活百科');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `username` varchar(20) default NULL COMMENT '用户名',
  `name` varchar(12) default NULL COMMENT '真实名字',
  `password` varchar(32) default NULL COMMENT '密码',
  `login_time` date default NULL COMMENT '登录时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '管理员', 'admin', '2015-06-03');

-- ----------------------------
-- Table structure for tb_user_collect_discipline
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_collect_discipline`;
CREATE TABLE `tb_user_collect_discipline` (
  `id` int(11) NOT NULL auto_increment,
  `discipline_id` int(11) default NULL COMMENT '文章编号',
  `user_id` int(11) default NULL COMMENT '用户编号',
  `collect_date` date default NULL COMMENT '收藏时间',
  PRIMARY KEY  (`id`),
  KEY `discipline_id` (`discipline_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_user_collect_discipline_ibfk_1` FOREIGN KEY (`discipline_id`) REFERENCES `tb_discipline` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_user_collect_discipline_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_collect_discipline
-- ----------------------------
INSERT INTO `tb_user_collect_discipline` VALUES ('1', '1', '1', '2015-06-03');

-- ----------------------------
-- Table structure for tb_user_exam
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_exam`;
CREATE TABLE `tb_user_exam` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `user_id` int(11) default NULL COMMENT '用户编号',
  `sum` int(11) default NULL COMMENT '总分',
  `create_date` date default NULL COMMENT '做题日期',
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_user_exam_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_exam
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_exam_discipline
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_exam_discipline`;
CREATE TABLE `tb_user_exam_discipline` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `exam_id` int(11) default NULL COMMENT '考试编号',
  `discipline_id` int(11) default NULL COMMENT '题目编号',
  `answers` varchar(255) default NULL COMMENT '选择的答案',
  PRIMARY KEY  (`id`),
  KEY `exam_id` (`exam_id`),
  KEY `discipline_id` (`discipline_id`),
  CONSTRAINT `tb_user_exam_discipline_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `tb_user_exam` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_user_exam_discipline_ibfk_2` FOREIGN KEY (`discipline_id`) REFERENCES `tb_discipline` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_exam_discipline
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_questionnaire`;
CREATE TABLE `tb_user_questionnaire` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `user_id` int(11) default NULL COMMENT '用户编号',
  `questionnaire_id` int(11) default NULL COMMENT '问卷编号',
  `sum` int(11) default NULL COMMENT '总分',
  `create_date` date default NULL COMMENT '做题日期',
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  KEY `questionnaire_id` (`questionnaire_id`),
  CONSTRAINT `tb_user_questionnaire_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_user_questionnaire_ibfk_2` FOREIGN KEY (`questionnaire_id`) REFERENCES `tb_questionnaire` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_questionnaire
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_read_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_read_article`;
CREATE TABLE `tb_user_read_article` (
  `id` int(11) NOT NULL auto_increment COMMENT '阅读文章编号',
  `article_id` int(11) default NULL COMMENT '文章编号',
  `user_id` int(11) default NULL COMMENT '用户编号',
  `read_date` date default NULL COMMENT '阅读时间',
  `update_date` date default NULL COMMENT '下次阅读时间',
  `read_count` int(11) default NULL COMMENT '阅读次数',
  PRIMARY KEY  (`id`),
  KEY `article_id` (`article_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_user_read_article_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `tb_article` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_user_read_article_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_read_article
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_wrong_discipline
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_wrong_discipline`;
CREATE TABLE `tb_user_wrong_discipline` (
  `id` int(11) NOT NULL auto_increment COMMENT '题目错误编号',
  `discipline_id` int(11) default NULL COMMENT '题目编号',
  `user_id` int(11) default NULL COMMENT '用户编号',
  `create_date` date default NULL COMMENT '第一次错的时间',
  `update_date` date default NULL COMMENT '下次错误的时间',
  `count` int(11) default NULL COMMENT '总共次数',
  PRIMARY KEY  (`id`),
  KEY `discipline_id` (`discipline_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_user_wrong_discipline_ibfk_1` FOREIGN KEY (`discipline_id`) REFERENCES `tb_discipline` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_user_wrong_discipline_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_wrong_discipline
-- ----------------------------
