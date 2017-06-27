CREATE TABLE `mock_service` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT,
  `service_interface` varchar(500) NOT NULL,
  `registry_id` int(11) NOT NULL,
  `protocol_id` int(11) NOT NULL,
  `application_name` varchar(100) NOT NULL,
  `group_name` varchar(100) DEFAULT NULL,
  `version` varchar(100) DEFAULT NULL,
  `service_status` varchar(20) DEFAULT '服务器运行状态running/stop',
  `timeout` int(11) NOT NULL,
  `retries` int(11) NOT NULL,
  `update_time` datetime NOT NULL
) ;

CREATE TABLE `mock_test_param` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT,
  `par_key` varchar(200) NOT NULL,
  `par_value` text NOT NULL,
  `par_type` varchar(100) DEFAULT NULL,
  `subject_id` int(11) NOT NULL
) ;

CREATE TABLE `mock_test_subject` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT,
  `test_subject_title` varchar(200) NOT NULL,
  `serviceId` int(11) NOT NULL,
  `method` varchar(100) NOT NULL
) ;

CREATE TABLE `protocol_config` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT,
  `protocol_name` varchar(50) DEFAULT NULL ,
  `protocol_port` int(11) NOT NULL ,
  `update_time` datetime NOT NULL
) ;

CREATE TABLE `registry_config` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT,
  `registry_protocol` varchar(200) NOT NULL,
  `registry_address` varchar(500) NOT NULL,
  `registry_timeout` int(11) NOT NULL,
  `update_time` datetime NOT NULL
) ;

CREATE TABLE `service_methed_rule` (
  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT,
  `service_id` int(11) NOT NULL,
  `method_name` varchar(100) NOT NULL,
  `when_script` text NOT NULL,
  `return_message` text NOT NULL,
  `update_time` datetime NOT NULL,
  `exec_sort` int(11) DEFAULT '0'
) 
