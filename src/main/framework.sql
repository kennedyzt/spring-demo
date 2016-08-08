/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50617
Source Host           : 127.0.0.1:3306
Source Database       : framework

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-08-08 18:02:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authoritys
-- ----------------------------
DROP TABLE IF EXISTS `authoritys`;
CREATE TABLE `authoritys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(17) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authoritys
-- ----------------------------
INSERT INTO `authoritys` VALUES ('1', 'zengt', '财务', '', '2016-08-08 09:42:10', '1', null, null);
INSERT INTO `authoritys` VALUES ('2', 'wangqin', '销售', '', '2016-08-08 09:42:46', '1', null, null);
INSERT INTO `authoritys` VALUES ('3', 'kennedy', '系统管理员', '', '2016-08-08 09:43:23', '1', null, null);
INSERT INTO `authoritys` VALUES ('4', 'zengt', '销售', '', '2016-08-08 09:43:55', '1', null, null);

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
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
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('1', '管理员', '', '2016-08-08 09:36:50', '1', null, null);
INSERT INTO `groups` VALUES ('2', '普通用户', '', '2016-08-08 09:38:49', '1', null, null);

-- ----------------------------
-- Table structure for group_authorities
-- ----------------------------
DROP TABLE IF EXISTS `group_authorities`;
CREATE TABLE `group_authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(17) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_authorities
-- ----------------------------

-- ----------------------------
-- Table structure for group_members
-- ----------------------------
DROP TABLE IF EXISTS `group_members`;
CREATE TABLE `group_members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(17) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_members
-- ----------------------------

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

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
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
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'tom', 'zengt', '000000', '863468390@qq.com', '18384280421', '成都', '', '2016-05-30 15:40:08', '111', null, null);
INSERT INTO `users` VALUES ('2', 'jack', 'zengt', '000000', '863468390@qq.com', '18384280421', '成都', '', '2016-05-30 15:40:08', '111', null, null);
INSERT INTO `users` VALUES ('3', 'rose', 'zengt', '000000', '863468390@qq.com', '18384280421', '成都', '', '2016-05-30 15:40:08', '111', null, null);
INSERT INTO `users` VALUES ('4', 'zengt', '曾滔', '123456', '863468390@qq.com', '18384280421', '成都', '', '2016-08-08 09:45:01', null, null, null);
