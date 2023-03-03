/*
 Navicat Premium Data Transfer

 Source Server         : mysql172.16.0.101
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 172.16.0.101:3306
 Source Schema         : qyjx_v4.0_basic

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 02/03/2023 10:46:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qyjx_jjjs
-- ----------------------------
DROP TABLE IF EXISTS `qyjx_jjjs`;
CREATE TABLE `qyjx_jjjs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `faid` int(11) NULL DEFAULT NULL COMMENT '方案id',
  `khnf` int(11) NOT NULL COMMENT '考核年份',
  `khpl` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考核频率',
  `khzq` int(11) NULL DEFAULT NULL COMMENT '考核周期',
  `czr` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `bz` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `czsj` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '操作时间',
  `jgbm` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lcslid` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `khzt` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '考核状态(预留-走流程可能需要)默认0，已分配1',
  `hsfs` int(1) NULL DEFAULT 0 COMMENT '核算方式0当量补偿1公式核算',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '奖金计算' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
