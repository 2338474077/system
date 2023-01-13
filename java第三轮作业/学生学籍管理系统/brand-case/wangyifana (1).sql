/*
 Navicat Premium Data Transfer

 Source Server         : localhost[2]
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : localhost:3306
 Source Schema         : wangyifana

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 13/01/2023 21:12:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_brand
-- ----------------------------
DROP TABLE IF EXISTS `tb_brand`;
CREATE TABLE `tb_brand`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `company_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ordered` int(255) NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `a` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `b` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 86 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_brand
-- ----------------------------
INSERT INTO `tb_brand` VALUES (40, '小红', '19808', 16546, '1', 1, '女', '2023/1/10');
INSERT INTO `tb_brand` VALUES (41, '小强', '13215', 10546, '1', 1, '女', '2023/1/10');
INSERT INTO `tb_brand` VALUES (42, '李四', '6065465', 165654, '1', 0, '女', '2023/1/16');
INSERT INTO `tb_brand` VALUES (43, '高老', '60654', 156654, '1', 1, '男', '2023/1/10');
INSERT INTO `tb_brand` VALUES (44, '王国', '246154554', 5654, '1', 1, '男', '2023/1/10');
INSERT INTO `tb_brand` VALUES (45, '育碧', '4454', 1654, 'haha', 0, '男', '2023/1/10');
INSERT INTO `tb_brand` VALUES (46, '任天野', '55445', 15987, 'haha', 0, '男', '2023/1/10');
INSERT INTO `tb_brand` VALUES (47, '欢柯', '754456', 15987401, 'haha', 0, '男', '2023/1/10');
INSERT INTO `tb_brand` VALUES (76, '太阳日', '6045645', 154645, '1', 0, '男', '2023/1/10');
INSERT INTO `tb_brand` VALUES (77, '合议庭', '6054634', 154654, '1', 0, '男', '2023/1/10');
INSERT INTO `tb_brand` VALUES (79, '程度', '645654', 154645, '1', 0, '男', '2023/1/10');
INSERT INTO `tb_brand` VALUES (82, 'admin', '19808564', 1654645, '1', 1, '男', '2023');
INSERT INTO `tb_brand` VALUES (84, '小墙1', '1364651', 4651313, '1', 1, '男', '2023/1/1');
INSERT INTO `tb_brand` VALUES (85, '郑州1', '小雨', 16890, '1', 1, '小雨', '大雨');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '123456');
INSERT INTO `tb_user` VALUES (2, '123456', '123456');
INSERT INTO `tb_user` VALUES (3, 'a', '123456');
INSERT INTO `tb_user` VALUES (4, '12345678', '123456');
INSERT INTO `tb_user` VALUES (5, 'admin16', '123456');
INSERT INTO `tb_user` VALUES (6, 'awdwa', 'dwadwa');
INSERT INTO `tb_user` VALUES (7, 'admin9', '123456');
INSERT INTO `tb_user` VALUES (8, 'admin10', '123456');
INSERT INTO `tb_user` VALUES (9, 'admin11', 'dwawadwa');
INSERT INTO `tb_user` VALUES (10, 'admin19', '123456');

SET FOREIGN_KEY_CHECKS = 1;
