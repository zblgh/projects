/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.3
Source Server Version : 50537
Source Host           : 192.168.1.3:3306
Source Database       : fqmalldata

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2014-10-17 17:21:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_admin_list`
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_list`;
CREATE TABLE `sys_admin_list` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `cardid` varchar(30) DEFAULT '' COMMENT '用户账号',
  `pwd` varchar(60) DEFAULT '' COMMENT '密码',
  `user_name` varchar(30) DEFAULT '' COMMENT '姓名',
  `tel` varchar(11) DEFAULT '' COMMENT '电话',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cardno` varchar(18) DEFAULT '' COMMENT '身份证号',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别(0:未知；1：男；2：女)',
  `birthday` date DEFAULT '0000-00-00' COMMENT '出生日期',
  `user_type` tinyint(4) DEFAULT '0' COMMENT '用户分类',
  `is_enabled` tinyint(4) DEFAULT '1' COMMENT '是否启用（1：启用；0：不启用）',
  `is_enabled_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '启用(停用)时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_admin_list
-- ----------------------------
INSERT INTO `sys_admin_list` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '15224066056', '2014-09-03 10:43:18', '', '1', '1991-06-10', null, '1', '2014-09-10 14:52:29', '2014-09-10 14:52:39');
INSERT INTO `sys_admin_list` VALUES ('2', 'zbl', '386503fbafd36cd264869ef3c858437b', '张斌龙', '', '2014-09-05 14:05:48', '', '1', '1991-06-10', null, '1', '2014-09-10 14:52:35', '2014-09-10 14:52:42');
INSERT INTO `sys_admin_list` VALUES ('13', 'dyq', 'e10adc3949ba59abbe56e057f20f883e', '戴耀强', '15858207799', '2014-09-15 16:58:00', '333333333333333333', '1', '2014-09-15', '0', '1', '2014-09-15 17:00:11', '2014-09-15 17:00:11');
INSERT INTO `sys_admin_list` VALUES ('20', 'cs', '25f9e794323b453885f5181f1b624d0b', 'xds', '', '2014-09-16 10:11:18', '', '0', null, '0', '1', '2014-09-16 10:13:00', '2014-09-16 10:13:00');

-- ----------------------------
-- Table structure for `sys_admin_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_log`;
CREATE TABLE `sys_admin_log` (
  `lid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `ip_address` varchar(10) DEFAULT '' COMMENT 'ip地址',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `account` varchar(30) DEFAULT '' COMMENT '操作账号',
  `account_name` varchar(30) DEFAULT '' COMMENT '操作姓名',
  `content` varchar(100) DEFAULT '' COMMENT '操作内容',
  `operation_state` tinyint(4) DEFAULT NULL COMMENT '操作状态(0:失败；1:成功)',
  `operation_type` tinyint(4) DEFAULT NULL COMMENT '操作类型（0：登录；1：注销；2：）',
  PRIMARY KEY (`lid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户日志表';

-- ----------------------------
-- Records of sys_admin_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_admin_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_permission`;
CREATE TABLE `sys_admin_permission` (
  `upid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户权限表id',
  `rid` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
  `permission_source` tinyint(4) NOT NULL DEFAULT '0' COMMENT '权限来源，0--来自角色、1--独立添加权限',
  `is_enabled` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否启用，0--不启用、1--启用',
  `pid` varchar(200) DEFAULT '' COMMENT '权限编号',
  `cardid` varchar(30) DEFAULT '' COMMENT '用户账号',
  PRIMARY KEY (`upid`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户权限列表';

-- ----------------------------
-- Records of sys_admin_permission
-- ----------------------------
INSERT INTO `sys_admin_permission` VALUES ('10', '1', '0', '1', '', 'admin');
INSERT INTO `sys_admin_permission` VALUES ('12', '0', '1', '1', '1111', 'zbl');
INSERT INTO `sys_admin_permission` VALUES ('13', '0', '1', '1', '11', 'zbl');
INSERT INTO `sys_admin_permission` VALUES ('9', '2', '0', '1', '', 'admin');
INSERT INTO `sys_admin_permission` VALUES ('11', '1', '0', '1', '', 'zbl');

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `pid` varchar(200) NOT NULL COMMENT '权限编号，每2位数字为一级，以此扩展，如10101010',
  `permission_name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `parendid` varchar(200) NOT NULL DEFAULT '0' COMMENT '上级编号，无为0',
  `level` smallint(6) DEFAULT '1' COMMENT '层级,顶级为1',
  `sortindex` int(11) NOT NULL DEFAULT '0' COMMENT '排序，数字越小越靠前',
  `page_url` varchar(200) NOT NULL COMMENT '页面地址',
  `is_childnode` tinyint(4) DEFAULT NULL COMMENT '有无子节点(0:无；1：有)',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '删除标记(0:否；1：是)',
  PRIMARY KEY (`pid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='权限信息表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('10', '系统管理', '0', '1', '0', '', '1', '0');
INSERT INTO `sys_permission` VALUES ('1010', '用户管理', '10', '1', '0', 'manager_user_list.html', '0', '0');
INSERT INTO `sys_permission` VALUES ('1011', '角色管理', '10', '1', '0', 'manager_role_list.html', '0', '0');
INSERT INTO `sys_permission` VALUES ('1012', '菜单管理', '10', '1', '0', 'manager_menu_list.html', '0', '0');
INSERT INTO `sys_permission` VALUES ('11', '订单管理', '0', '1', '0', '', '1', '0');
INSERT INTO `sys_permission` VALUES ('1111', '订单列表', '11', '1', '0', 'manager_all_order.html', '0', '0');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `sort_index` int(11) NOT NULL DEFAULT '0' COMMENT '排序，数字越小越靠前',
  `role_describe` varchar(50) DEFAULT NULL COMMENT '角色描述',
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`rid`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统角色', '0', '系统角色', '2014-09-10 18:59:21');
INSERT INTO `sys_role` VALUES ('2', '订单角色', '0', '业务订单角色', '2014-09-11 18:59:26');
INSERT INTO `sys_role` VALUES ('4', 'js', '0', 'jsms', '2014-09-15 17:08:10');
INSERT INTO `sys_role` VALUES ('5', '测试角色', '0', '测试角色', '2014-09-15 17:10:48');
INSERT INTO `sys_role` VALUES ('6', '测试角色', '0', '测试角色', '2014-09-15 17:11:03');

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `rpid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色权限记录编号',
  `is_enabled` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否启用，0--不启用、1--启用',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色id',
  `pid` varchar(200) DEFAULT '' COMMENT '权限编号',
  PRIMARY KEY (`rpid`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1', '10');
INSERT INTO `sys_role_permission` VALUES ('2', '1', '1', '1010');
INSERT INTO `sys_role_permission` VALUES ('3', '1', '1', '1011');
INSERT INTO `sys_role_permission` VALUES ('4', '1', '1', '1012');
INSERT INTO `sys_role_permission` VALUES ('5', '1', '2', '11');
INSERT INTO `sys_role_permission` VALUES ('6', '1', '2', '1111');
