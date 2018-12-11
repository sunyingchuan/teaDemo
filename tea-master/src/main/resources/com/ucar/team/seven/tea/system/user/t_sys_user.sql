-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别：0-女，1-男',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态  0：禁用   1：正常  默认值 ：1',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_emp` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `modify_emp` bigint(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', 'admin@tedu.cn', '13624356789', '1', null, '2017-07-18 17:13:39', null, null);
INSERT INTO `t_sys_user` VALUES ('2', 'zhangli1', 'e10adc3949ba59abbe56e057f20f883e', '0', 'zhangli@tedu.cn', '13678909876', '1', '2017-07-18 10:01:51', '2018-09-21 16:42:37', null, null);
INSERT INTO `t_sys_user` VALUES ('3', 'wangke', 'e10adc3949ba59abbe56e057f20f883e', '0', 'wangke@tedu.cn', '18678900987', '1', '2017-07-18 11:40:53', '2017-07-21 17:40:28', null, null);
INSERT INTO `t_sys_user` VALUES ('4', 'zhangql', '+HBpqtPuj9KLBIpneR5X0A==', '1', 'zhangql@tedu.cn', '13678909876', '1', '2017-07-18 12:17:30', '2017-07-18 17:40:09', null, null);
INSERT INTO `t_sys_user` VALUES ('5', 'fanwei', '1acab7425d6dfae670f26bd160518902', '0', 'fanwei@tedu.cn', '13876545678', '1', '2017-07-20 17:03:22', '2017-07-20 17:03:22', null, null);
INSERT INTO `t_sys_user` VALUES ('6', 'wumei', '431ebdcccf3404787a144f9ba669a8e2', '0', 'wumei@tedu.cn', '13567898765', '1', '2017-07-21 10:57:40', '2017-07-21 10:58:21', null, null);
INSERT INTO `t_sys_user` VALUES ('7', 'jacen', 'e10adc3949ba59abbe56e057f20f883e', '1', '123@qq.com', '13898327232', '1', '2018-09-21 10:18:29', '2018-09-21 10:18:31', '1', '1');
INSERT INTO `t_sys_user` VALUES ('8', 'jey', 'e10adc3949ba59abbe56e057f20f883e', '1', '666@qq.com', '13666668888', '1', '2018-09-21 10:18:57', null, null, null);
INSERT INTO `t_sys_user` VALUES ('9', 'hani', 'e10adc3949ba59abbe56e057f20f883e', '0', 'hani@qq.com', '13888888888', '1', '2018-09-21 10:19:13', null, null, null);
INSERT INTO `t_sys_user` VALUES ('10', 'babu', 'e10adc3949ba59abbe56e057f20f883e', '0', 'buabu@qq.com', '13999999999', '1', '2018-09-21 10:19:32', null, null, null);
INSERT INTO `t_sys_user` VALUES ('11', 'tom', 'e10adc3949ba59abbe56e057f20f883e', '1', 'tom@qq.com', '13200000001', '1', '2018-09-21 10:19:53', null, null, null);
