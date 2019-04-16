/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50173
 Source Host           : localhost:3306
 Source Schema         : hsystem

 Target Server Type    : MySQL
 Target Server Version : 50173
 File Encoding         : 65001

 Date: 16/01/2019 15:41:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hbzk
-- ----------------------------
DROP TABLE IF EXISTS `hbzk`;
CREATE TABLE `hbzk`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `dwbh` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位编号',
  `dwmc` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  `dwyb` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `dwdz` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `dwbgsdh` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办公室电话',
  `dwjjzxdh` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '急救中心电话',
  `dwfwzxdh` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务中心电话',
  `dwjb` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '级别（二级、三级）',
  `dwjbsj` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '获取时间',
  `dwpsbz` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采用评审标准',
  `dwfr` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人姓名',
  `dwfrlxdh` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人电话',
  `dwfrsfzh` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人身份证号',
  `dwurl` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网址',
  `dwlogo` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'LOGO',
  `dwemail` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `tcpip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改IP地址',
  `czy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改操作员',
  `czybt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改操作员副标题',
  `rqtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`, `dwbh`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hbzk
-- ----------------------------
INSERT INTO `hbzk` VALUES (1, '0001', '贵阳市第三人民医院', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for hcsk
-- ----------------------------
DROP TABLE IF EXISTS `hcsk`;
CREATE TABLE `hcsk`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `llb` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数类别',
  `bzlb` int(1) NULL DEFAULT 1 COMMENT '为1真表示标准',
  `xslb` int(1) NULL DEFAULT 0 COMMENT '为1真表示用于人员档案显示字段定义',
  `dm` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数代码',
  `mc` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数名称',
  `jm` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数简码',
  `bz` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数备注',
  `Kqymc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考勤员姓名（当llb=’考勤单位’时有效）',
  `Kqymcbt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考勤员标题（当llb=’考勤单位’时有效）',
  `tcpip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'tcpip',
  `czy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `czybt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员副标题',
  `rqtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hcsk
-- ----------------------------
INSERT INTO `hcsk` VALUES (25, '性别', 1, 0, '01', '男', 'n', NULL, NULL, NULL, '127.0.0.1', '管理员', NULL, '2019-01-16 14:26:19');
INSERT INTO `hcsk` VALUES (26, '性别', 1, 0, '02', '女', 'n', NULL, NULL, NULL, '127.0.0.1', '管理员', NULL, '2019-01-16 14:26:19');
INSERT INTO `hcsk` VALUES (27, '名族', 1, 0, '01', '汉族', 'hz', NULL, NULL, NULL, '127.0.0.1', '管理员', NULL, '2019-01-16 14:26:19');
INSERT INTO `hcsk` VALUES (28, '名族', 1, 0, '02', '苗族', 'mz', NULL, NULL, NULL, '127.0.0.1', '管理员', NULL, '2019-01-16 14:26:19');
INSERT INTO `hcsk` VALUES (29, '名族', 1, 0, '03', '羌族', 'qz', NULL, NULL, NULL, '127.0.0.1', '管理员', NULL, '2019-01-16 14:26:19');


-- ----------------------------
-- Table structure for hnum
-- ----------------------------
DROP TABLE IF EXISTS `hnum`;
CREATE TABLE `hnum`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `orderName` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hnum
-- ----------------------------
INSERT INTO `hnum` VALUES (99, 'wg2019011700001', 'wg');
INSERT INTO `hnum` VALUES (100, 'wg2019011700002', 'wg');
INSERT INTO `hnum` VALUES (101, 'wg2019011700003', 'wg');
INSERT INTO `hnum` VALUES (102, 'wg2019011700004', 'wg');
INSERT INTO `hnum` VALUES (103, 'wg2019011600001', 'wg');
INSERT INTO `hnum` VALUES (104, 'wg2019011600002', 'wg');
INSERT INTO `hnum` VALUES (105, 'wg2019011600003', 'wg');
INSERT INTO `hnum` VALUES (106, 'wg2019011600004', 'wg');
INSERT INTO `hnum` VALUES (107, 'wg2019011600005', 'wg');
INSERT INTO `hnum` VALUES (108, 'wg2019011600006', 'wg');
INSERT INTO `hnum` VALUES (109, 'rs2019011600001', 'rs');
INSERT INTO `hnum` VALUES (110, 'rs2019011600002', 'rs');
INSERT INTO `hnum` VALUES (111, 'rs2019011600003', 'rs');
INSERT INTO `hnum` VALUES (112, 'rs2019011600004', 'rs');
INSERT INTO `hnum` VALUES (113, 'rs2019011600005', 'rs');

-- ----------------------------
-- Table structure for hqx
-- ----------------------------
DROP TABLE IF EXISTS `hqx`;
CREATE TABLE `hqx`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dwbh` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位编号',
  `dm` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代码',
  `jm` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简码',
  `yhm` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `mc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `xmm` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码(MD5)',
  `xb` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `csrq` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `sfzh` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `lxfs` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `bmmc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `zzmc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务名称',
  `zt` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '使用状态',
  `mmcwcs` int(2) DEFAULT 0 COMMENT '密码错误次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hqx
-- ----------------------------
INSERT INTO `hqx` VALUES (1, '0001', '1000', 'WLQ','system', '王良庆', 'e6e061838856bf47e1de730719fb2609', '男', '1985-06-11', '522101198506114438', '18984841520', NULL, NULL,'0',0);

-- ----------------------------
-- Table structure for hrw
-- ----------------------------
DROP TABLE IF EXISTS `hrw`;
CREATE TABLE `hrw`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一zid',
  `djhm` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单据号',
  `lb` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核类别：1-人事 2-文档',
  `czy` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '待审核操作员',
  `tjczy` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提交操作员',
  `tjrqtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提交日期',
  `jhczy` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '稽核人',
  `jhrqtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '稽核日期',
  `jhtcpip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '稽核IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hrw
-- ----------------------------
INSERT INTO `hrw` VALUES (1, '0bdc03e2-1961-11e9-a3e2-d2d9cb6dc181', 'rs2019011600005', '1', '王良庆', '管理员', '2019-01-16 15:34:59', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for hw01wd
-- ----------------------------
DROP TABLE IF EXISTS `hw01wd`;
CREATE TABLE `hw01wd`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一zid',
  `lb` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '（11-14国家、省、市、县来文，21-行业规范及标准、31-院内文件、32-上墙制度、33-新闻及公告，51-项目材料，61-科室材料，81-个人邮件）',
  `mlmc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目录名称',
  `xmmc` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属项目，可多选',
  `sybm` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用部门，可多选',
  `wdmj` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档密级',
  `wddw` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发文单位',
  `wdwh` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文号',
  `wdmc` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档名称',
  `wdztc` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题词',
  `wdhz` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档后缀',
  `wdbc` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档版次',
  `wdylhz` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档预览文字',
  `wdsxbzbit` int(2) NULL DEFAULT 0 COMMENT '文档失效标志',
  `wdedit` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文档可编辑状态附件',
  `zt` int(2) NULL DEFAULT 0 COMMENT '1-传阅，2-公示',
  `yjgdtsint` int(2) NULL DEFAULT 0 COMMENT '预计公示天数',
  `bz` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `czy` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `tcpip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入操作员IP',
  `rqtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入操作员日期',
  `jhczy` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `jhtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核日期',
  `jhtcpip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hw01wd
-- ----------------------------
INSERT INTO `hw01wd` VALUES (1, '8e2f0cc5-1961-11e9-a3e2-d2d9cb6dc181', '33', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


/**
  统一资源权限管理数据结构
  uaam_org：视图，数据来源于hbzk，单位信息表
  uaam_users: 用户表，来源于hqx的视图
  uaam_resources : 资源表
  uaam_roles：角色表
  uaam_roleresources:角色资源关系表
  uaam_userresources:用户资源关系表
  uaam_userroles：用户角色关系表
*/

create or replace view uaam_org
as
select id,null as parentid,dwbh as orgcode,dwmc as orgname,null as admindivision,null as remark from hbzk;

create or replace view uaam_users
as 
select id as userid,yhm as username,mc as personname,xb as gender,xmm as password,sfzh as cardnum,dwbh as organizecode,zt as status,null as remark,mmcwcs as pwderrortimes from hqx;

drop table if exists uaam_resources;
create table uaam_resources
(
  resid     int(6) not null auto_increment comment '自增id',
  parentid  int(6),
  restype   varchar(10),
  resname   varchar(100),
  reslevel  int(2),
  resurl    varchar(100),
  resdomain varchar(30),
  remark    varchar(200),
  status    char(1),
  resorder  int(5),
  primary key (resid) using btree
)engine = innodb AUTO_INCREMENT = 46 character set = utf8 collate = utf8_general_ci row_format = compact comment='统一认证授权管理(资源表)';

insert into uaam_resources values(1,NULL,'APP','统一认证授权管理系统',1,NULL,'uaams',NULL,'1',1);
insert into uaam_resources values(2,1,'MENU','配置管理',2,NULL,'uaams',NULL,'1',1);
insert into uaam_resources values(3,2,'MENU','用户管理',3,'config/user.action','uaams',NULL,'1',1);
insert into uaam_resources values(4,2,'MENU','角色管理',3,'config/role.action','uaams',NULL,'1',2);
insert into uaam_resources values(5,2,'MENU','资源管理',3,'config/resource.action','uaams',NULL,'1',3);

insert into uaam_resources VALUES (6, 1, 'MENU', '系统数据管理', 2,NULL,'uaams',NULL,'1',2);
insert into uaam_resources VALUES (7, 6, 'MENU', '系统参数维护', 3,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (8, 7, 'MENU', '单位参数维护', 4,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (9, 7, 'MENU', '使用人员维护', 4,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (10, 7, 'MENU', '监测系统运行', 4,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (11, 6, 'MENU', '系统标准维护', 3,NULL,'uaams',NULL,'1',2);
insert into uaam_resources VALUES (12, 11, 'MENU', '标准数据维护', 4,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (13, 11, 'MENU', '标准数据维护1', 4,NULL,'uaams',NULL,'1',2);

insert into uaam_resources VALUES (14, 1, 'MENU', '人力资源系统', 2,NULL,'uaams',NULL,'1',3);
insert into uaam_resources VALUES (15, 14, 'MENU', '人事档案', 3,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (16, 15, 'MENU', '人事档案', 4,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (17, 15, 'MENU', '薪酬记录', 4,NULL,'uaams',NULL,'1',2);
insert into uaam_resources VALUES (18, 15, 'MENU', '考勤记录', 4,NULL,'uaams',NULL,'1',3);
insert into uaam_resources VALUES (19, 14, 'MENU', '组织架构', 3,NULL,'uaams',NULL,'1',2);
insert into uaam_resources VALUES (20, 19, 'MENU', '部门管理', 4,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (21, 19, 'MENU', '职务及岗位', 4,NULL,'uaams',NULL,'1',2);
insert into uaam_resources VALUES (22, 19, 'MENU', '模型化管理', 4,NULL,'uaams',NULL,'1',3);
insert into uaam_resources VALUES (23, 14, 'MENU', '合同管理', 3,NULL,'uaams',NULL,'1',3);
insert into uaam_resources VALUES (24, 23, 'MENU', '劳动合同', 4,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (25, 23, 'MENU', '培训合同', 4,NULL,'uaams',NULL,'1',2);
insert into uaam_resources VALUES (26, 23, 'MENU', '保密协议', 4,NULL,'uaams',NULL,'1',3);

insert into uaam_resources VALUES (27, 1, 'MENU', '职务导航', 2,NULL,'uaams',NULL,'1',4);
insert into uaam_resources VALUES (28, 27, 'MENU', '制度墙', 3,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (29, 27, 'MENU', '资料柜', 3,NULL,'uaams',NULL,'1',2);
insert into uaam_resources VALUES (30, 27, 'MENU', '台账册', 3,NULL,'uaams',NULL,'1',3);
insert into uaam_resources VALUES (31, 27, 'MENU', '任务栏', 3,NULL,'uaams',NULL,'1',4);
insert into uaam_resources VALUES (32, 27, 'MENU', '工作台', 3,NULL,'uaams',NULL,'1',5);
insert into uaam_resources VALUES (33, 32, 'MENU', '会议通知', 4,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (34, 32, 'MENU', '任务通知', 4,NULL,'uaams',NULL,'1',2);
insert into uaam_resources VALUES (35, 32, 'MENU', '文档传阅', 4,NULL,'uaams',NULL,'1',3);
insert into uaam_resources VALUES (36, 32, 'MENU', '资料归档', 4,NULL,'uaams',NULL,'1',4);

insert into uaam_resources VALUES (37, 1, 'MENU', '组织导航', 2,NULL,'uaams',NULL,'1',5);
insert into uaam_resources VALUES (38, 37, 'MENU', '制度墙', 3,NULL,'uaams',NULL,'1',1);
insert into uaam_resources VALUES (39, 37, 'MENU', '资料柜', 3,NULL,'uaams',NULL,'1',2);
insert into uaam_resources VALUES (40, 37, 'MENU', '花名册', 3,NULL,'uaams',NULL,'1',3);

insert into uaam_resources VALUES (41, 1, 'MENU', '文档中心', 2,'doc/index.action','uaams',NULL,'1',6);

insert into uaam_resources VALUES (42, 1, 'MENU', '公共资源', 2,NULL,'uaams',NULL,'1',7);

insert into uaam_resources VALUES (43, 1, 'MENU', '基础配置', 2,NULL,'uaams',NULL,'1',0);
insert into uaam_resources VALUES (44, 43, 'MENU', '单位信息设置', 3,'base/organization.action','uaams',NULL,'1',1);
insert into uaam_resources VALUES (45, 43, 'MENU', '员工管理', 3,'base/employee.action','uaams',NULL,'1',2);

drop table if exists uaam_roles;
create table uaam_roles
(
  roleid   int(6) not null auto_increment comment '自增id',
  rolename varchar(50),
  remark   varchar(200),
  primary key (roleid) using btree
)engine = innodb AUTO_INCREMENT = 2 character set = utf8 collate = utf8_general_ci row_format = compact comment='统一认证授权管理(角色表)';

insert into uaam_roles values(1,'系统管理员','最高权限用户');

drop table if exists uaam_roleresources;
create table uaam_roleresources
(
  refid  int(6) not null auto_increment comment '自增id',
  roleid int(6),
  resid  int(6),
  primary key (refid) using btree
)engine = innodb AUTO_INCREMENT = 46 character set = utf8 collate = utf8_general_ci row_format = compact comment='统一认证授权管理(角色资源表)';

alter table uaam_roleresources
  add constraint fk_rr_resid foreign key (resid)
  references uaam_resources (resid);
alter table uaam_roleresources
  add constraint fk_rr_rid foreign key (roleid)
  references uaam_roles (roleid);

insert into uaam_roleresources values(1,1,1);
insert into uaam_roleresources values(2,1,2);
insert into uaam_roleresources values(3,1,3);
insert into uaam_roleresources values(4,1,4);
insert into uaam_roleresources values(5,1,5);
insert into uaam_roleresources values(6,1,6);
insert into uaam_roleresources values(7,1,7);
insert into uaam_roleresources values(8,1,8);
insert into uaam_roleresources values(9,1,9);
insert into uaam_roleresources values(10,1,10);
insert into uaam_roleresources values(11,1,11);
insert into uaam_roleresources values(12,1,12);

insert into uaam_roleresources values(13,1,13);
insert into uaam_roleresources values(14,1,14);
insert into uaam_roleresources values(15,1,15);
insert into uaam_roleresources values(16,1,16);
insert into uaam_roleresources values(17,1,17);
insert into uaam_roleresources values(18,1,18);
insert into uaam_roleresources values(19,1,19);
insert into uaam_roleresources values(20,1,20);
insert into uaam_roleresources values(21,1,21);
insert into uaam_roleresources values(22,1,22);
insert into uaam_roleresources values(23,1,23);
insert into uaam_roleresources values(24,1,24);

insert into uaam_roleresources values(25,1,25);
insert into uaam_roleresources values(26,1,26);
insert into uaam_roleresources values(27,1,27);
insert into uaam_roleresources values(28,1,28);
insert into uaam_roleresources values(29,1,29);
insert into uaam_roleresources values(30,1,30);
insert into uaam_roleresources values(31,1,31);
insert into uaam_roleresources values(32,1,32);
insert into uaam_roleresources values(33,1,33);
insert into uaam_roleresources values(34,1,34);
insert into uaam_roleresources values(35,1,35);
insert into uaam_roleresources values(36,1,36);

insert into uaam_roleresources values(37,1,37);
insert into uaam_roleresources values(38,1,38);
insert into uaam_roleresources values(39,1,39);
insert into uaam_roleresources values(40,1,40);
insert into uaam_roleresources values(41,1,41);
insert into uaam_roleresources values(42,1,42);

insert into uaam_roleresources values(43,1,43);
insert into uaam_roleresources values(44,1,44);
insert into uaam_roleresources values(45,1,45);

drop table if exists uaam_userresources;
create table uaam_userresources
(
  refid  int(6) not null auto_increment comment '自增id',
  userid varchar(50),
  resid  int(6),
  primary key (refid) using btree
)engine = innodb character set = utf8 collate = utf8_general_ci row_format = compact comment='统一认证授权管理(用户资源表)';

alter table uaam_userresources
  add constraint fk_ures_resid foreign key (resid)
  references uaam_resources (resid);
alter table uaam_userresources
  add constraint fk_users_uid foreign key (userid)
  references hqx (id);


drop table if exists uaam_userroles;
create table uaam_userroles
(
  refid  int(6) not null auto_increment comment '自增id',
  userid varchar(50),
  roleid int(6),
  primary key (refid) using btree
)engine = innodb AUTO_INCREMENT = 2 character set = utf8 collate = utf8_general_ci row_format = compact comment='统一认证授权管理(用户角色关联表)';

alter table uaam_userroles
  add constraint fk_ur_rid foreign key (roleid)
  references uaam_roles (roleid);
alter table uaam_userroles
  add constraint fk_user_uid1 foreign key (userid)
  references hqx (id);
  
insert into uaam_userroles values(1,1,1);

---结束uaams---

-- ----------------------------
-- Procedure structure for Get_orderNo
-- ----------------------------
DROP PROCEDURE IF EXISTS `Get_orderNo`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Get_orderNo`(IN `orderNamePre` char(2),IN `num` int,OUT `newOrderNo` varchar(25))
BEGIN
	  DECLARE currentDate varCHAR (15) ;-- 当前日期,有可能包含时分秒   
		DECLARE maxNo INT DEFAULT 0 ; -- 离现在最近的满足条件的订单编号的流水号最后5位，如：SH2013011000002的maxNo=2   
--  DECLARE l_orderNo varCHAR (25) ;-- 新生成的订单编号   
--  DECLARE oldDate DATE ;-- 离现在最近的满足条件的订单编号的日期   
		DECLARE oldOrderNo VARCHAR (25) DEFAULT '' ;-- 离现在最近的满足条件的订单编号   
    
  if num = 8 then -- 根据年月日生成订单编号   
    SELECT DATE_FORMAT(NOW(), '%Y%m%d') INTO currentDate ;-- 订单编号形式:前缀+年月日+流水号，如：SH2013011000002   
  elseif num = 14 then -- 根据年月日时分秒生成订单编号   
    SELECT DATE_FORMAT(NOW(), '%Y%m%d%H%i%s') INTO currentDate ; -- 订单编号形式：前缀+年月日时分秒+流水号，如：SH2013011010050700001,个人不推荐使用这种方法生成流水号   
  else -- 根据年月日时分生成订单编号   
    SELECT DATE_FORMAT(NOW(), '%Y%m%d%H%i') INTO currentDate ;-- 订单形式：前缀+年月日时分+流水号,如：SH20130110100900005   
  end if ;  
    
  SELECT IFNULL(orderNo, '') INTO oldOrderNo   
  FROM hnum   
  WHERE SUBSTRING(orderNo, 3, num) = currentDate   
    AND SUBSTRING(orderNo, 1, 2) = orderNamePre   
    and length(orderNo) = 7 + num  
  ORDER BY id DESC LIMIT 1 ; -- 有多条时只显示离现在最近的一条   
    
  IF oldOrderNo != '' THEN   
    SET maxNo = CONVERT(SUBSTRING(oldOrderNo, -5), DECIMAL) ;-- SUBSTRING(oldOrderNo, -5)：订单编号如果不为‘‘截取订单的最后5位   
  END IF ;  
  SELECT   
    CONCAT(orderNamePre, currentDate,  LPAD((maxNo + 1), 5, '0')) INTO newOrderNo ; -- LPAD((maxNo + 1), 5, '0')：如果不足5位，将用0填充左边   
    
  INSERT INTO hnum (orderNo, orderName) VALUES (newOrderNo, orderNamePre); -- 向订单表中插入数据   
--    set newOrderNo = l_orderNo;   
  SELECT   
    newOrderNo;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
