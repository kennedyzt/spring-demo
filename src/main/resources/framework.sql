/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50617
Source Host           : 127.0.0.1:3306
Source Database       : framework

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-08-31 17:43:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `authority_name` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(17) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', 'ROLE_FINANCE', '财务', '', '2016-08-08 09:42:10', '1', null, null);
INSERT INTO `authority` VALUES ('2', 'ROLE_SALE', '销售', '', '2016-08-08 09:42:46', '1', null, null);
INSERT INTO `authority` VALUES ('3', 'ROLE_SYSTEM', '系统管理', '', '2016-08-08 09:43:23', '1', null, null);
INSERT INTO `authority` VALUES ('4', 'ROLE_LOG', '日志', '', '2016-08-08 09:43:55', '1', null, null);

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(17) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('1', '管理员', '', '2016-08-08 09:36:50', '1', null, null);
INSERT INTO `group` VALUES ('2', '普通用户', '', '2016-08-08 09:38:49', '1', null, null);

-- ----------------------------
-- Table structure for group_authoritie
-- ----------------------------
DROP TABLE IF EXISTS `group_authoritie`;
CREATE TABLE `group_authoritie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `authority_id` int(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(17) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_authoritie
-- ----------------------------
INSERT INTO `group_authoritie` VALUES ('1', '1', '1', '', '2016-08-09 15:28:12', '1', null, null);
INSERT INTO `group_authoritie` VALUES ('2', '1', '2', '', '2016-08-09 15:28:45', '1', null, null);
INSERT INTO `group_authoritie` VALUES ('3', '1', '3', '', '2016-08-09 15:29:32', '1', null, null);
INSERT INTO `group_authoritie` VALUES ('4', '2', '4', '', '2016-08-09 15:56:52', '1', null, null);
INSERT INTO `group_authoritie` VALUES ('5', '2', '1', '', '2016-08-30 15:30:15', '1', null, null);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `level` int(11) unsigned DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL COMMENT '模块图标',
  `selected_icon` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `back_color` varchar(255) DEFAULT NULL COMMENT '背景颜色',
  `href` varchar(255) DEFAULT NULL,
  `select_table` tinyint(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(17) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(17) DEFAULT NULL,
  UNIQUE KEY `text` (`text`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, '1', '系统管理', null, null, null, null, null, '0', null, '2016-03-06 19:00:33', '1', null, null);
INSERT INTO `menu` VALUES ('2', '1', '2', '用户管理', null, null, null, null, null, '0', null, '2016-03-06 19:01:11', '1', null, null);
INSERT INTO `menu` VALUES ('3', null, '1', '采购订单', null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `menu` VALUES ('4', null, '1', '销售订单', null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `menu` VALUES ('5', null, '1', '销售发货单', null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `menu` VALUES ('6', null, '1', '库存发货单', null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `menu` VALUES ('7', '2', '3', '用户列表', null, null, null, null, '/user/list', '0', null, null, null, null, null);
INSERT INTO `menu` VALUES ('8', '2', null, '权限管理', null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `menu` VALUES ('9', '3', '2', '采购订单列表', null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `menu` VALUES ('100', null, '1', 'DEMO', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('10', '100', '2', 'BootStrap Validator', null, null, null, null, '/demo//bootstrapvalidator', null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('11', '100', '2', 'Layer', null, null, null, null, '/demo/layer', null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('12', '100', '2', 'AngularJs', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('13', '12', '3', 'Hello World', null, null, null, null, '/demo/angularjs/helloworld', null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('14', '100', '2', 'FileInput', null, null, null, null, '/demo/fileinput', null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('15', '100', '2', 'WebSocket', null, null, null, null, '/websocket/send', null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('16', '100', '2', '百度地图', null, null, null, null, '/bmap/show', null, null, '2016-08-18 16:24:53', '1', null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(17) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zzzz', 'zengt', '000000', '863468390@qq.com', '18384280421', '成都', '', '2016-05-30 15:40:08', '111', null, null);
INSERT INTO `user` VALUES ('2', 'jack', 'zengt', '000000', '863468390@qq.com', '18384280421', '成都', '', '2016-05-30 15:40:08', '111', null, null);
INSERT INTO `user` VALUES ('3', 'rose', 'zengt', '000000', '863468390@qq.com', '18384280421', '成都', '', '2016-05-30 15:40:08', '111', null, null);
INSERT INTO `user` VALUES ('4', 'zengt', '曾滔', '123456', '863468390@qq.com', '18384280421', '成都', '', '2016-08-08 09:45:01', null, null, null);
INSERT INTO `user` VALUES ('5', 'admin', '管理员', '123456', '863468390@qq.com', '18384280421', '成都', '', '2016-08-09 15:53:46', null, null, null);
INSERT INTO `user` VALUES ('18', '12314', null, '2414323421', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('19', 'kennedy', null, '123465', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('20', '1231646', null, '231423', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('21', 'ccccccccccc', null, 'ccccccccccccccccc', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('23', '212142341', null, '41333333333', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('24', '213414', null, '2142443', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('25', 'kkkk', null, 'kkkkk', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('26', 'dsgasd', null, 'sdgasdg', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('27', '14124', null, '124124', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('29', 'sdga', null, 'sdgw3erfawe', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('31', '1346565456', null, '12313', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('32', '333333333', null, '3333333333333', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(17) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('1', '4', '2', '', '2016-08-09 15:52:50', null, null, null);
INSERT INTO `user_group` VALUES ('2', '5', '1', '', '2016-08-09 15:54:11', null, null, null);
INSERT INTO `user_group` VALUES ('3', '5', '2', '', '2016-08-09 16:10:28', null, null, null);

-- ----------------------------
-- Table structure for user_t
-- ----------------------------
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_t
-- ----------------------------
INSERT INTO `user_t` VALUES ('1', '测试', 'sfasgfaf', '24');
