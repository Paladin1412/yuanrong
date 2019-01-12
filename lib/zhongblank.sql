/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : zhongblank

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-04-23 18:19:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for systemmenu
-- ----------------------------
DROP TABLE IF EXISTS `systemmenu`;
CREATE TABLE `systemmenu` (
  `systemMenuId` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(255) DEFAULT NULL,
  `menuPath` varchar(255) DEFAULT NULL,
  `menuIco` varchar(255) DEFAULT NULL,
  `sort` int(255) DEFAULT NULL,
  `parentMenu_id` int(11) DEFAULT NULL,
  `typeIndex` int(255) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `statusIndex` int(255) DEFAULT NULL,
  PRIMARY KEY (`systemMenuId`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of systemmenu
-- ----------------------------
INSERT INTO `systemmenu` VALUES ('1', '系统管理', null, '&#xe62e;', '1', '0', '1', '2018-04-19 20:05:10', '1');
INSERT INTO `systemmenu` VALUES ('2', '菜单管理', null, '&#xe6f5;', '1', '1', '1', '2018-04-19 20:06:14', '1');
INSERT INTO `systemmenu` VALUES ('3', '菜单列表', '/system/systemMenu_list', '', '2', '2', '1', '2018-04-19 20:06:41', '1');
INSERT INTO `systemmenu` VALUES ('4', '新增菜单', '/system/systemMenu_save', null, '2', '2', '1', '2018-04-19 20:12:55', '1');
INSERT INTO `systemmenu` VALUES ('5', '新增菜单OK', '/system/systemMenu_saveOK', '', '2', '2', '0', '2018-04-19 20:13:58', '1');
INSERT INTO `systemmenu` VALUES ('6', '修改菜单', '/system/systemMenu_update', null, '2', '2', '0', '2018-04-19 20:16:12', '1');
INSERT INTO `systemmenu` VALUES ('7', '修改菜单OK', '/system/systemMenu_updateOK', null, '2', '2', '0', '2018-04-19 20:16:56', '1');
INSERT INTO `systemmenu` VALUES ('8', '删除菜单', '/system/systemMenu_delete', null, '2', '2', '0', '2018-04-19 20:21:13', '1');
INSERT INTO `systemmenu` VALUES ('9', '用户管理', '', '&#xe62d;', '1', '1', '1', '2018-04-19 20:25:44', '1');
INSERT INTO `systemmenu` VALUES ('11', '用户列表', '/system/systemUser_list', '', '1', '9', '1', '2018-04-19 20:30:58', '1');
INSERT INTO `systemmenu` VALUES ('12', '新增用户', '/system/systemUser_save', null, '1', '9', '1', '2018-04-19 20:33:12', '1');
INSERT INTO `systemmenu` VALUES ('13', '新增用户OK', '/system/systemUser_saveOK', '', '1', '9', '0', '2018-04-19 20:33:12', '1');
INSERT INTO `systemmenu` VALUES ('14', '修改用户', '/system/systemUser_update', '', '2', '9', '0', '2018-04-19 20:16:12', '1');
INSERT INTO `systemmenu` VALUES ('15', '修改用户OK', '/system/systemUser_updateOK', '', '2', '9', '0', '2018-04-19 20:16:56', '1');
INSERT INTO `systemmenu` VALUES ('16', '删除用户', '/system/systemUser_delete', '', '2', '9', '0', '2018-04-19 20:21:13', '1');
INSERT INTO `systemmenu` VALUES ('17', '角色管理', '', '&#xe62b;', '3', '1', '1', '2018-04-19 20:25:44', '1');
INSERT INTO `systemmenu` VALUES ('18', '角色列表', '/system/systemRole_list', '', '1', '17', '1', '2018-04-19 20:06:41', '1');
INSERT INTO `systemmenu` VALUES ('19', '新增角色', '/system/systemRole_save', '', '2', '17', '1', '2018-04-19 20:12:55', '1');
INSERT INTO `systemmenu` VALUES ('20', '新增角色OK', '/system/systemRole_saveOK', '', '2', '17', '0', '2018-04-19 20:13:58', '1');
INSERT INTO `systemmenu` VALUES ('21', '修改角色', '/system/systemRole_update', '', '2', '17', '0', '2018-04-19 20:16:12', '1');
INSERT INTO `systemmenu` VALUES ('22', '修改角色OK', '/system/systemRole_updateOK', '', '2', '17', '0', '2018-04-19 20:16:56', '1');
INSERT INTO `systemmenu` VALUES ('23', '删除角色', '/system/systemRole_delete', '', '2', '17', '0', '2018-04-19 20:21:13', '1');

-- ----------------------------
-- Table structure for systemrole
-- ----------------------------
DROP TABLE IF EXISTS `systemrole`;
CREATE TABLE `systemrole` (
  `systemRoleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `modifiedCount` varchar(255) DEFAULT NULL,
  `statusIndex` int(255) DEFAULT NULL,
  PRIMARY KEY (`systemRoleId`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of systemrole
-- ----------------------------
INSERT INTO `systemrole` VALUES ('1', '管理员', '2018-04-19 18:54:36', '2018-04-19 18:54:40', '1', '1');

-- ----------------------------
-- Table structure for systemrole_systemmenu
-- ----------------------------
DROP TABLE IF EXISTS `systemrole_systemmenu`;
CREATE TABLE `systemrole_systemmenu` (
  `systemRoleId` int(11) NOT NULL,
  `systemMenuId` int(11) NOT NULL,
  PRIMARY KEY (`systemRoleId`,`systemMenuId`),
  UNIQUE KEY `sytemRole_systemMenu_Index` (`systemRoleId`,`systemMenuId`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of systemrole_systemmenu
-- ----------------------------
INSERT INTO `systemrole_systemmenu` VALUES ('1', '1');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '2');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '3');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '4');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '5');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '6');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '7');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '8');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '9');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '10');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '11');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '12');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '13');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '14');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '15');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '16');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '17');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '18');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '19');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '20');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '21');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '22');
INSERT INTO `systemrole_systemmenu` VALUES ('1', '23');

-- ----------------------------
-- Table structure for systemuser
-- ----------------------------
DROP TABLE IF EXISTS `systemuser`;
CREATE TABLE `systemuser` (
  `systemUserId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `isLoginIndex` int(11) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `modifiedCount` int(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`systemUserId`,`userName`),
  UNIQUE KEY `userNameUniqueIndex` (`userName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of systemuser
-- ----------------------------
INSERT INTO `systemuser` VALUES ('1', 'admin', 'rWd3Hb+AzNg3IXF1b5vD+g==', '管理员', '1', '2018-04-11 16:25:52', '2018-04-11 16:25:55', '1', null);
INSERT INTO `systemuser` VALUES ('2', 'abc', '2tAQSIAaptzj0zL+He2XPw==', '小明', '0', '2018-04-17 13:52:23', '2018-04-17 13:52:23', '1', null);
INSERT INTO `systemuser` VALUES ('10', 'test1', 'aaaa', '测试1', '1', '2018-04-23 10:24:16', '2018-04-23 10:24:16', '1', null);
INSERT INTO `systemuser` VALUES ('11', 'test2', 'aaaa', '测试1', '1', '2018-04-23 10:24:16', '2018-04-23 10:24:16', '1', null);
INSERT INTO `systemuser` VALUES ('13', 'test3', 'aaaa', '测试1', '1', '2018-04-23 10:27:31', '2018-04-23 10:27:31', '1', null);
INSERT INTO `systemuser` VALUES ('14', 'test4', 'aaaa', '测试1', '1', '2018-04-23 10:27:31', '2018-04-23 10:27:31', '1', null);

-- ----------------------------
-- Table structure for systemuser_systemrole
-- ----------------------------
DROP TABLE IF EXISTS `systemuser_systemrole`;
CREATE TABLE `systemuser_systemrole` (
  `systemUserId` int(11) NOT NULL,
  `systemRoleId` int(11) NOT NULL,
  PRIMARY KEY (`systemUserId`,`systemRoleId`),
  UNIQUE KEY `systemUser_systemRole_index` (`systemUserId`,`systemRoleId`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of systemuser_systemrole
-- ----------------------------
INSERT INTO `systemuser_systemrole` VALUES ('1', '1');

-- ----------------------------
-- Table structure for sytemrole_systemmenu
-- ----------------------------
DROP TABLE IF EXISTS `sytemrole_systemmenu`;
CREATE TABLE `sytemrole_systemmenu` (
  `sytemRole_id` int(11) NOT NULL,
  `systemMenu_id` int(11) NOT NULL,
  PRIMARY KEY (`sytemRole_id`,`systemMenu_id`),
  UNIQUE KEY `sytemRole_systemMenu_Index` (`sytemRole_id`,`systemMenu_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sytemrole_systemmenu
-- ----------------------------
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '1');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '2');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '3');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '4');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '5');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '6');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '7');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '8');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '9');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '10');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '11');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '12');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '13');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '14');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '15');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '16');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '17');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '18');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '19');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '20');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '21');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '22');
INSERT INTO `sytemrole_systemmenu` VALUES ('1', '23');
