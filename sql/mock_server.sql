/*
Navicat MySQL Data Transfer

Source Database       : mock_server

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-06-12 17:22:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mock_service
-- ----------------------------
DROP TABLE IF EXISTS `mock_service`;
CREATE TABLE `mock_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_interface` varchar(500) NOT NULL,
  `registry_id` int(11) NOT NULL,
  `protocol_id` int(11) NOT NULL,
  `application_name` varchar(100) NOT NULL,
  `group_name` varchar(100) DEFAULT NULL,
  `version` varchar(100) DEFAULT NULL,
  `service_status` varchar(20) DEFAULT '服务器运行状态running/stop',
  `timeout` int(11) NOT NULL,
  `retries` int(11) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mock_test_param
-- ----------------------------
DROP TABLE IF EXISTS `mock_test_param`;
CREATE TABLE `mock_test_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `par_key` varchar(200) NOT NULL,
  `par_value` text NOT NULL,
  `par_type` varchar(100) DEFAULT NULL,
  `subject_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mock_test_subject
-- ----------------------------
DROP TABLE IF EXISTS `mock_test_subject`;
CREATE TABLE `mock_test_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_subject_title` varchar(200) NOT NULL,
  `serviceId` int(11) NOT NULL,
  `method` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_service_id` (`serviceId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for protocol_config
-- ----------------------------
DROP TABLE IF EXISTS `protocol_config`;
CREATE TABLE `protocol_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `protocol_name` varchar(50) DEFAULT NULL COMMENT '协议名称',
  `protocol_port` int(11) NOT NULL COMMENT '协议端口',
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for registry_config
-- ----------------------------
DROP TABLE IF EXISTS `registry_config`;
CREATE TABLE `registry_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_protocol` varchar(200) NOT NULL,
  `registry_address` varchar(500) NOT NULL,
  `registry_timeout` int(11) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for service_methed_rule
-- ----------------------------
DROP TABLE IF EXISTS `service_methed_rule`;
CREATE TABLE `service_methed_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(11) NOT NULL,
  `method_name` varchar(100) NOT NULL,
  `when_script` text NOT NULL,
  `return_message` text NOT NULL,
  `update_time` datetime NOT NULL,
  `exec_sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8;
