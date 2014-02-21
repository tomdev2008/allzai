/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : allzai

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2014-02-21 18:18:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `device_app`
-- ----------------------------
DROP TABLE IF EXISTS `device_app`;
CREATE TABLE `device_app` (
  `deviceId` int(11) NOT NULL,
  `appPackName` varchar(255) DEFAULT NULL COMMENT 'app????',
  `appName` varchar(255) NOT NULL COMMENT '?????????',
  `appVersion` varchar(50) NOT NULL COMMENT '???',
  `installTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '??????',
  `uninstallTime` datetime DEFAULT NULL COMMENT '???????',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '??(0:???? 1:??????)',
  KEY `FK_USER_APP_DEVICE_DEVICEID` (`deviceId`),
  KEY `FK_USER_APP_PACKAGENAME` (`appPackName`),
  CONSTRAINT `FK_USER_APP_DEVICE_DEVICEID` FOREIGN KEY (`deviceId`) REFERENCES `mobile_device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='????APP??????';

-- ----------------------------
-- Records of device_app
-- ----------------------------

-- ----------------------------
-- Table structure for `device_config`
-- ----------------------------
DROP TABLE IF EXISTS `device_config`;
CREATE TABLE `device_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` int(11) NOT NULL COMMENT '�û�ID',
  `isLock` tinyint(1) NOT NULL DEFAULT '0' COMMENT '�Ƿ�����(0:open,1:close)',
  `msgPush` tinyint(1) NOT NULL DEFAULT '0' COMMENT '��Ϣ���ͣ�0������ 1�������ͣ�',
  `screenShow` tinyint(1) NOT NULL DEFAULT '0' COMMENT '��Ļչʾ��0������ 1����������������',
  PRIMARY KEY (`id`),
  KEY `FK_USER_SYS_CONFIG_USERID` (`deviceId`),
  CONSTRAINT `FK_user_config_deviceId` FOREIGN KEY (`deviceId`) REFERENCES `mobile_device` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=231 DEFAULT CHARSET=utf8 COMMENT='�û�ϵͳ���ñ�';

-- ----------------------------
-- Records of device_config
-- ----------------------------

-- ----------------------------
-- Table structure for `game_info`
-- ----------------------------
DROP TABLE IF EXISTS `game_info`;
CREATE TABLE `game_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `platform` varchar(255) NOT NULL DEFAULT 'android' COMMENT '承载平台',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `packageName` varchar(255) NOT NULL COMMENT '包名\n',
  `version` varchar(255) DEFAULT NULL COMMENT '版本',
  `downUrl` varchar(255) NOT NULL COMMENT '下载地址',
  `picture` varchar(255) NOT NULL COMMENT '图片',
  `thumbnail` varchar(255) NOT NULL COMMENT '缩略图',
  `detailsPicture` varchar(255) DEFAULT NULL COMMENT '详情图(示例storeId:1,2,3)',
  `owner` varchar(255) DEFAULT NULL COMMENT '物主',
  `type` int(5) DEFAULT NULL COMMENT '类型',
  `size` bigint(20) NOT NULL DEFAULT '0' COMMENT '大小(字节)',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `grade` decimal(10,1) NOT NULL DEFAULT '5.0' COMMENT '评分',
  `clickCount` int(11) NOT NULL DEFAULT '0' COMMENT '点击数',
  `downCount` int(11) NOT NULL DEFAULT '0' COMMENT '下载数',
  `like` int(11) NOT NULL DEFAULT '0' COMMENT '喜欢数',
  `dislike` int(11) NOT NULL DEFAULT '0' COMMENT '厌恶数',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '状态:1，创建，2：未审核， 3：上架，4：下架',
  `description` varchar(1000) DEFAULT NULL COMMENT '程序描述',
  `tutorial` varchar(511) DEFAULT NULL COMMENT '用户操作的指导',
  `isDeleted` int(1) NOT NULL DEFAULT '0' COMMENT '0:有效，1:废弃',
  `createTime` datetime NOT NULL COMMENT '创建时间(由程序控制)',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_APP_TYPE` (`type`),
  CONSTRAINT `FK_APP_TYPE` FOREIGN KEY (`type`) REFERENCES `game_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_info
-- ----------------------------

-- ----------------------------
-- Table structure for `game_type`
-- ----------------------------
DROP TABLE IF EXISTS `game_type`;
CREATE TABLE `game_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(100) NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `isDeleted` int(1) DEFAULT '0' COMMENT '0:有效，1:废弃',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='应用类型';

-- ----------------------------
-- Records of game_type
-- ----------------------------
INSERT INTO `game_type` VALUES ('1', 'Games', '0', '0', '2014-02-19 16:05:50', '2014-02-20 11:25:21');
INSERT INTO `game_type` VALUES ('2', 'Arcade & Action', '0', '0', '2014-02-19 16:06:36', '2014-02-20 11:25:22');
INSERT INTO `game_type` VALUES ('3', 'Brain & Puzzle', '1', '0', '2014-02-19 16:07:09', '2014-02-21 15:58:21');
INSERT INTO `game_type` VALUES ('4', 'Cards & Casino', '2', '0', '2014-02-19 16:07:40', '2014-02-20 11:25:24');
INSERT INTO `game_type` VALUES ('5', 'Casual', '3', '0', '2014-02-19 16:08:07', '2014-02-20 11:25:25');
INSERT INTO `game_type` VALUES ('6', 'Live Wallpaper', '4', '0', '2014-02-19 16:08:38', '2014-02-20 11:25:26');
INSERT INTO `game_type` VALUES ('7', 'Racing', '5', '0', '2014-02-19 16:08:59', '2014-02-20 11:25:27');
INSERT INTO `game_type` VALUES ('8', 'Sports Games', '6', '0', '2014-02-19 16:09:26', '2014-02-20 11:25:28');
INSERT INTO `game_type` VALUES ('9', 'Widgets ', '7', '0', '2014-02-19 16:09:54', '2014-02-20 11:25:30');

-- ----------------------------
-- Table structure for `login_history`
-- ----------------------------
DROP TABLE IF EXISTS `login_history`;
CREATE TABLE `login_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '��¼��ʷ������ID',
  `deviceId` int(11) NOT NULL,
  `userId` int(11) NOT NULL COMMENT '�û�ID',
  `loginIp` varchar(50) NOT NULL COMMENT '��¼IP��ַ',
  `loginTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '��¼ʱ��',
  PRIMARY KEY (`id`),
  KEY `FK_USER_LOGIN_HISTORY_USER_ID` (`userId`),
  KEY `FK_LOGIN_HHISTORY_DEVICE_ID` (`deviceId`),
  CONSTRAINT `FK_LOGIN_HHISTORY_DEVICE_ID` FOREIGN KEY (`deviceId`) REFERENCES `mobile_device` (`id`),
  CONSTRAINT `FK_USER_LOGIN_HISTORY_USER_ID` FOREIGN KEY (`userId`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=223 DEFAULT CHARSET=utf8 COMMENT='��¼��ʷ��';

-- ----------------------------
-- Records of login_history
-- ----------------------------

-- ----------------------------
-- Table structure for `mobile_device`
-- ----------------------------
DROP TABLE IF EXISTS `mobile_device`;
CREATE TABLE `mobile_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '�ֻ��豸������ID',
  `imei` varchar(255) NOT NULL DEFAULT '000000000000000' COMMENT '�豸ΨһID��Ĭ�ϱ�ʾδ֪��',
  `iccid` varchar(255) DEFAULT NULL COMMENT '���ɵ�·��ʶ���루�̻����ֻ�SIM���У� ICCIDΪIC����Ψһʶ�����\n',
  `brand` varchar(100) DEFAULT NULL COMMENT '�̱�',
  `model` varchar(100) DEFAULT NULL COMMENT '�ֻ��ͺ�',
  `osType` varchar(50) NOT NULL DEFAULT 'android' COMMENT 'ϵͳ����',
  `version` varchar(50) DEFAULT NULL COMMENT 'ϵͳ�汾',
  `sdk_ver` varchar(50) DEFAULT '1',
  `latest_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `imei` (`imei`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8 COMMENT='�ֻ��豸��Ϣ��';

-- ----------------------------
-- Records of mobile_device
-- ----------------------------

-- ----------------------------
-- Table structure for `play_history`
-- ----------------------------
DROP TABLE IF EXISTS `play_history`;
CREATE TABLE `play_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����id',
  `userId` int(11) NOT NULL COMMENT '�û�id',
  `gameId` int(11) DEFAULT NULL COMMENT '���������漰����app id',
  `deviceId` int(11) NOT NULL,
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '��ɾ��',
  `historyType` varchar(50) NOT NULL DEFAULT '' COMMENT '��������������(AD, APP, GIFT, FSHARE,TSHARE, FREE)',
  `description` varchar(1024) NOT NULL COMMENT '����������������Ϣ',
  `createTime` datetime DEFAULT NULL COMMENT '����ʱ��',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����޸�ʱ��',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `updateTime` (`updateTime`),
  KEY `FK_CRE_HHISTORY_DEVICE_ID` (`deviceId`),
  KEY `FK_play_history_gameId` (`gameId`),
  CONSTRAINT `FK_APP_INFO_AND_PLAY_HISTORY_APPID` FOREIGN KEY (`gameId`) REFERENCES `game_info` (`id`),
  CONSTRAINT `FK_CRE_HHISTORY_DEVICE_ID` FOREIGN KEY (`deviceId`) REFERENCES `mobile_device` (`id`),
  CONSTRAINT `FK_USER_CREDIT_HISTORY_USER_ID` FOREIGN KEY (`userId`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������ʷ��';

-- ----------------------------
-- Records of play_history
-- ----------------------------

-- ----------------------------
-- Table structure for `push_key`
-- ----------------------------
DROP TABLE IF EXISTS `push_key`;
CREATE TABLE `push_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` int(11) NOT NULL,
  `gcmId` varchar(1000) DEFAULT NULL,
  `iosId` varchar(1000) DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_push_key_deviceId` (`deviceId`),
  CONSTRAINT `FK_push_key_deviceId` FOREIGN KEY (`deviceId`) REFERENCES `mobile_device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of push_key
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '������ID',
  `account` varchar(100) DEFAULT NULL COMMENT '�û��ʺ�',
  `imei` varchar(100) DEFAULT NULL,
  `role` tinyint(4) NOT NULL DEFAULT '0',
  `password` varchar(255) NOT NULL COMMENT '�û�����',
  `coin` int(11) NOT NULL DEFAULT '0' COMMENT '����\n',
  `nickName` varchar(255) DEFAULT NULL COMMENT '�ǳ�',
  `firstName` varchar(255) DEFAULT NULL COMMENT '����',
  `lastName` varchar(255) DEFAULT NULL COMMENT '��',
  `age` int(3) DEFAULT '0' COMMENT '����',
  `gender` int(1) DEFAULT '1' COMMENT '�Ա�',
  `headKey` varchar(255) DEFAULT NULL COMMENT 'ͷ��洢��',
  `headPortrait` varchar(255) DEFAULT NULL COMMENT 'ͷ��',
  `phone` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL COMMENT '��ַ',
  `city` varchar(100) DEFAULT NULL COMMENT '����',
  `state` varchar(100) DEFAULT NULL COMMENT '��',
  `country` varchar(100) DEFAULT NULL COMMENT '����\n',
  `zipcode` varchar(100) DEFAULT NULL COMMENT '�������\n',
  `contact` varchar(100) DEFAULT NULL,
  `createTime` datetime NOT NULL COMMENT '����ʱ��',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����޸�ʱ��',
  PRIMARY KEY (`id`),
  KEY `user_info_account` (`account`) USING BTREE,
  KEY `user_info_imei` (`imei`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8 COMMENT='�û���Ϣ��';

-- ----------------------------
-- Records of user_info
-- ----------------------------
