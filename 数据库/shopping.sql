/*
 Navicat Premium Data Transfer

 Source Server         : 2023Java
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : localhost:3306
 Source Schema         : shopping

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 26/01/2024 16:11:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `del` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0,
  `def` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '默认地址',
  `details` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 1, '山东省', '青岛市', '城阳区', 0, 0, '中享思途');
INSERT INTO `address` VALUES (2, 1, '天津市', '市辖区', '河西区', 0, 0, '海天区');
INSERT INTO `address` VALUES (3, 1, '北京市', '市辖区', '西城区', 0, 0, '朝阳区');
INSERT INTO `address` VALUES (8, 1, '天津市', '市辖区', '河东区', 0, 1, '');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `del` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'zhangsan', '123456', 'zhangsan@qq.com', 0);

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `pid` int NULL DEFAULT -1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, 'user', '用户管理页面', -1);
INSERT INTO `authority` VALUES (2, 'add_user', '新增用户', 1);
INSERT INTO `authority` VALUES (3, 'delete_user', '删除用户', 1);
INSERT INTO `authority` VALUES (4, 'edit_user', '编辑用户', 1);
INSERT INTO `authority` VALUES (5, 'search_user', '查询用户', 1);

-- ----------------------------
-- Table structure for authority_role
-- ----------------------------
DROP TABLE IF EXISTS `authority_role`;
CREATE TABLE `authority_role`  (
  `authorityid` int NOT NULL,
  `roleid` int NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_role
-- ----------------------------
INSERT INTO `authority_role` VALUES (1, 1);
INSERT INTO `authority_role` VALUES (3, 1);
INSERT INTO `authority_role` VALUES (2, 1);
INSERT INTO `authority_role` VALUES (4, 2);
INSERT INTO `authority_role` VALUES (5, 2);
INSERT INTO `authority_role` VALUES (2, 2);
INSERT INTO `authority_role` VALUES (3, 2);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `del` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0,
  `adminid` int NULL DEFAULT NULL,
  `edittime` datetime NULL DEFAULT NULL,
  `editid` int NULL DEFAULT 0,
  `pid` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '鲜花', '101', '2023-12-20 09:49:49', 0, 1, '2024-01-14 20:38:52', 1, 0);
INSERT INTO `category` VALUES (2, '干花', '102', '2023-12-20 09:50:31', 0, 1, NULL, 0, 0);
INSERT INTO `category` VALUES (3, '生日套餐', '1001', '2023-12-20 15:22:13', 0, 1, '2023-12-21 09:28:20', 1, 1);
INSERT INTO `category` VALUES (4, '情人节', '1002', '2023-12-20 20:40:58', 0, 1, '2023-12-21 09:30:34', 1, 1);
INSERT INTO `category` VALUES (8, '开业花篮', '1003', '2023-12-21 14:42:59', 0, 1, '2023-12-21 14:43:07', 1, 2);
INSERT INTO `category` VALUES (9, '友情鲜花', '1004', '2023-12-21 14:44:21', 0, 1, '2023-12-21 14:44:30', 1, 1);
INSERT INTO `category` VALUES (10, '亲情鲜花', '1005', '2023-12-21 20:24:26', 0, 1, '2023-12-22 11:05:49', 1, 1);
INSERT INTO `category` VALUES (12, '表白套餐', '1006', '2023-12-22 11:00:30', 0, 1, '2024-01-22 19:15:49', 1, 1);
INSERT INTO `category` VALUES (14, 'aaa', '123', '2024-01-26 13:42:35', 0, 1, NULL, 0, 1);

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `gid` int NOT NULL,
  `uid` int NULL DEFAULT NULL,
  `jointime` datetime NULL DEFAULT NULL,
  `del` tinyint NULL DEFAULT 0
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES (1, 1, '2024-01-11 17:09:38', 0);
INSERT INTO `collection` VALUES (36, 1, '2024-01-11 17:21:50', 0);
INSERT INTO `collection` VALUES (38, 1, '2024-01-11 17:23:27', 0);
INSERT INTO `collection` VALUES (40, 1, '2024-01-11 20:29:09', 0);
INSERT INTO `collection` VALUES (43, 1, '2024-01-11 20:34:39', 0);
INSERT INTO `collection` VALUES (6, 1, '2024-01-12 16:55:02', 0);
INSERT INTO `collection` VALUES (2, 2, '2024-01-13 10:32:26', 0);
INSERT INTO `collection` VALUES (2, 1, '2024-01-13 22:35:28', 0);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `goodsid` int NULL DEFAULT NULL,
  `userid` int NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `del` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, 1, 'hhh', '2023-12-20 16:19:32', 0);

-- ----------------------------
-- Table structure for detail
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail`  (
  `orderid` int NOT NULL,
  `goodsid` int NOT NULL,
  `count` int NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `userid` int NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`, `goodsid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detail
-- ----------------------------
INSERT INTO `detail` VALUES (1, 1, 1, 447.00, 1);
INSERT INTO `detail` VALUES (4, 1, 2, 244.00, 1);
INSERT INTO `detail` VALUES (5, 1, 2, 244.00, 1);
INSERT INTO `detail` VALUES (6, 1, 2, 244.00, 1);
INSERT INTO `detail` VALUES (7, 2, 1, 207.00, 1);
INSERT INTO `detail` VALUES (8, 6, 2, 272.00, 1);
INSERT INTO `detail` VALUES (9, 6, 2, 272.00, 1);
INSERT INTO `detail` VALUES (10, 6, 2, 272.00, 1);
INSERT INTO `detail` VALUES (11, 37, 2, 199.00, 1);
INSERT INTO `detail` VALUES (12, 58, 1, 139.00, 1);
INSERT INTO `detail` VALUES (13, 62, 1, 146.00, 1);
INSERT INTO `detail` VALUES (14, 1, 4, 244.00, 1);
INSERT INTO `detail` VALUES (14, 2, 2, 207.00, 1);
INSERT INTO `detail` VALUES (15, 6, 3, 272.00, 1);
INSERT INTO `detail` VALUES (16, 2, 1, 207.00, 1);
INSERT INTO `detail` VALUES (17, 40, 1, 334.00, 1);
INSERT INTO `detail` VALUES (18, 2, 3, 207.00, 1);
INSERT INTO `detail` VALUES (18, 6, 4, 272.00, 1);
INSERT INTO `detail` VALUES (19, 6, 10, 272.00, 1);
INSERT INTO `detail` VALUES (20, 6, 10, 272.00, 1);
INSERT INTO `detail` VALUES (21, 6, 2, 272.00, 1);
INSERT INTO `detail` VALUES (22, 2, 1, 207.00, 1);
INSERT INTO `detail` VALUES (22, 6, 1, 272.00, 1);
INSERT INTO `detail` VALUES (23, 6, 4, 272.00, 1);
INSERT INTO `detail` VALUES (23, 36, 1, 207.00, 1);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `pid` int NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `oldprice` decimal(10, 2) NULL DEFAULT NULL,
  `stock` int NULL DEFAULT NULL,
  `state` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '0上架 1下架',
  `jointime` datetime NULL DEFAULT NULL,
  `del` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0,
  `info` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `adminid` int NULL DEFAULT NULL,
  `edittime` datetime NULL DEFAULT NULL,
  `editid` int NULL DEFAULT 0,
  `img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `cailiao` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `baozhuang` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '1001', '醇美绽放', 4, 244.00, 478.00, 56, 0, '2023-12-20 16:08:32', 0, '美人如诗，一诗一美人，便胜却人间无数！', 1, '2023-12-22 15:34:29', 1, 'http://localhost:80/easyfile/037031cd-2ead-4a6d-805c-f430c6ab4cc5.png', '6朵戴安娜7支粉康1支向日葵，3支多头白桔梗，粉色满天星适量', '内衬英文雾面纸，外层黑色牛皮纸圆形包装，红色缎带束扎');
INSERT INTO `goods` VALUES (2, '1002', '深情时光', 4, 207.00, 356.00, 53, 0, '2023-12-22 13:40:32', 1, '我愿成为你的特工，只求你愿做我的王妃！', 1, '2023-12-22 15:59:26', 1, 'http://localhost:80/easyfile/9c6fdca7-393f-4000-9ee9-47d621e1cb51.png', '内衬英文雾面纸，外层黑色牛皮纸圆形包装，红色缎带束扎', '土色雪梨纸搭配红色包装纸');
INSERT INTO `goods` VALUES (6, '1003', '恋人心', 4, 272.00, 395.00, 26, 0, '2023-12-22 15:29:01', 0, '妙龄女郎，我总是情不自禁的看你，你已经没法再可爱了...', 1, NULL, 0, 'http://localhost:80/easyfile/fdbfeb51-6996-42ec-ab44-b8df75b96f33.png', '6朵戴安娜7支粉康1支向日葵，3支多头白桔梗，粉色满天星适量', '粉色欧雅纸层次扇形包装，白色缎带束扎');
INSERT INTO `goods` VALUES (36, '1004', '一见钟情', 4, 207.00, 359.00, 49, 0, '2023-12-26 16:41:25', 0, ' 爱是晴天雨天的相扶相伴;是自得潦倒的彼此牵挽。', 1, NULL, 0, 'http://localhost:80/easyfile/a5ffff46-0073-4293-8fd7-e441a72bcf88.png', '6朵戴安娜7支粉康1支向日葵，3支多头白桔梗，粉色满天星适量', '粉色欧雅纸层次扇形包装，白色缎带束扎');
INSERT INTO `goods` VALUES (37, '1005', '三生情', 4, 199.00, 355.00, 33, 0, '2023-12-26 16:43:00', 0, ' 爱到永恒，爱到最后永远只爱你一人', 1, NULL, 0, 'http://localhost:80/easyfile/3324e3f6-3632-4272-91bf-335b6d41afe2.png', '6朵戴安娜7支粉康1支向日葵，3支多头白桔梗，粉色满天星适量', '粉色欧雅纸层次扇形包装，白色缎带束扎');
INSERT INTO `goods` VALUES (38, '1006', '爱的守护神', 4, 597.00, 839.00, 66, 0, '2023-12-26 16:44:13', 0, '我希望你，是我独家的记忆。谁也不行 ，从我这个身体中拉走你', 1, NULL, 0, 'http://localhost:80/easyfile/9c279a01-f013-4f8b-a60c-9153c4e7abd8.png', '6朵戴安娜7支粉康1支向日葵，3支多头白桔梗，粉色满天星适量', '粉色欧雅纸层次扇形包装，白色缎带束扎');
INSERT INTO `goods` VALUES (39, '1007', '命定与你', 4, 280.00, 502.00, 66, 0, '2023-12-26 16:45:13', 0, '许你一世繁华，回我一世守候，愿你一世倾情，许诺我一世笑颜如花', 1, NULL, 0, 'http://localhost:80/easyfile/be4cb8ed-1863-429a-8e00-03bd89e17bff.png', '6朵戴安娜7支粉康1支向日葵，3支多头白桔梗，粉色满天星适量', '粉色欧雅纸层次扇形包装，白色缎带束扎');
INSERT INTO `goods` VALUES (40, '1008', '爱的印记', 4, 334.00, 566.00, 49, 0, '2023-12-26 16:46:13', 0, '我愿意用一千万年等待你初春暖阳般的绽颜一笑！', 1, NULL, 0, 'http://localhost:80/easyfile/96e6a57b-1ed4-4bef-82b6-a50f8a42ceac.png', '6朵戴安娜7支粉康1支向日葵，3支多头白桔梗，粉色满天星适量', '粉色欧雅纸层次扇形包装，白色缎带束扎');
INSERT INTO `goods` VALUES (43, '1009', '时光旋律', 10, 280.00, 479.00, 60, 0, '2023-12-26 20:10:29', 0, '撩起爱情的轻纱，将真诚的吻印在岁月的额头，许下一生一世的诺言，绽放不离不弃的情花，愿用刹那芳华，换与你牵手到白头。', 1, NULL, 0, 'http://localhost/easyfile/qinqing1.jpg', '6朵戴安娜7支粉康1支向日葵，3支多头白桔梗，粉色满天星适量', '粉色欧雅纸层次扇形包装，白色缎带束扎');
INSERT INTO `goods` VALUES (44, '1010', '无私母爱', 10, 292.00, 599.00, 70, 0, '2023-12-26 20:13:18', 0, ' 是最美的面孔，伴我拼搏争成功；是最美的笑容，伴我生命去追梦；是最美的祝福，伴我天涯向前冲。母亲节到了，愿亲爱的妈妈健康快乐永幸福。', 1, NULL, 0, 'http://localhost/easyfile/qinqing2.jpg', '6朵戴安娜7支粉康1支向日葵，3支多头白桔梗，粉色满天星适量', '粉色欧雅纸层次扇形包装，白色缎带束扎');
INSERT INTO `goods` VALUES (45, '1011', '温馨时光', 10, 320.00, 789.00, 60, 0, '2023-12-26 20:15:23', 0, '坎坎坷坷的道路上，有你就有光亮；跌跌撞撞的前行中，有你就有方向。祝您节日快乐！', 1, NULL, 0, 'http://localhost/easyfile/qinqing3.jpg', '6朵戴安娜7支粉康1支向日葵，3支多头白桔梗，粉色满天星适量', ' 粉色欧雅纸韩式包装');
INSERT INTO `goods` VALUES (46, '1012', '健康长乐', 10, 273.00, 431.00, 55, 0, '2023-12-26 20:20:23', 0, '花因您而开，果因您而熟，小树因您而茁壮成长，而您却一直如绿叶般的衬托着我们无怨无悔，老师您辛苦了，祝您身体安康，心情愉快！', 1, NULL, 0, 'http://localhost/easyfile/qinqing4.jpg', '11朵粉康，8朵粉佳人，3支白桔梗，尤加利搭配', '白色雪梨纸+粉色欧雅纸包装');
INSERT INTO `goods` VALUES (47, '1013', '心怀感恩', 10, 177.00, 323.00, 66, 0, '2023-12-26 20:22:10', 0, ' 温柔的你，总是将暖意带给我。我也将我的祝福带给你，愿你健康长乐。', 1, NULL, 0, 'http://localhost/easyfile/qinqing5.jpg', '11枝红康搭配浅紫色勿忘我、尤加利', '土色雪梨纸搭配红色包装纸');
INSERT INTO `goods` VALUES (48, '1014', '与亲情相伴', 10, 207.00, 431.00, 33, 0, '2023-12-26 20:24:08', 0, '您是母亲、知己和朋友的完美结合，让清风带着我浓浓的祝福，悄悄告知您：愿您平安喜乐！', 1, NULL, 0, 'http://localhost/easyfile/qinqing6.jpg', '11朵红色康乃馨2枝多头白百合，黄莺间插，满天星点缀', '内衬英文雾面纸，外层黑色牛皮纸圆形包装，红色缎带束扎');
INSERT INTO `goods` VALUES (49, '1015', '花香清逸', 10, 231.00, 477.00, 900, 0, '2023-12-26 20:28:50', 0, '世间有一种东西叫爱,自吸引中诞生,升华中融洽', 1, NULL, 0, 'http://localhost/easyfile/qinqing7.jpg', '19朵红色康乃馨，栀子叶和黄莺丰满间插，白色满天星点缀', '内衬白色牛皮纸，外层酒红色牛皮纸圆形包装，咖啡色缎带束扎');
INSERT INTO `goods` VALUES (50, '1016', '小确幸', 9, 341.00, 623.00, 600, 0, '2023-12-26 20:32:47', 0, '人生中什么事情最美丽，莫过于遇见你', 1, NULL, 0, 'http://localhost/easyfile/youqing1.jpg', '1个苹果，11朵戴安娜粉玫瑰，3支向日葵，粉色满天星丰满，尤加利间插', '粉色欧雅纸层次扇形包装，白色缎带束扎');
INSERT INTO `goods` VALUES (51, '1017', '浓厚情谊', 9, 189.00, 346.00, 321, 0, '2023-12-26 20:35:39', 0, '永恒的爱，浓情厚谊，永不变的心', 1, NULL, 0, 'http://localhost/easyfile/youqing2.jpg', ' 6朵香槟玫瑰，3支向日葵、搭配白翠珠或者白洋甘菊、尤加利叶', '精美棕色雪梨纸搭配双色欧雅纸');
INSERT INTO `goods` VALUES (52, '1018', '快乐的味道', 9, 274.00, 479.00, 479, 0, '2023-12-26 20:36:59', 0, '快乐是一缕阳光，让你的心灵即使在寒冷的冬天也能感到温暖如春', 1, NULL, 0, 'http://localhost/easyfile/youqing3.jpg', ' 9朵香槟，3支向日葵，2枝橙色泡泡、香槟色桔梗、洋甘菊、尤加利', '粉金色欧雅纸扇形层次包装，英文丝带束扎');
INSERT INTO `goods` VALUES (53, '1019', '快乐阳光', 9, 262.00, 479.00, 479, 0, '2023-12-26 20:45:09', 0, ' 载着你彷佛载着阳光，不管到哪里都是晴天，蝴蝶自在飞，花也布满天，一朵一朵因你而香', 1, NULL, 0, 'http://localhost/easyfile/youqing4.jpg', ' 3支向日葵，5枝盛开香槟色桔梗，绿桔梗适量，粉桔梗点缀', '内衬淡粉色雪梨纸，外层豆沙色牛皮纸层次扇形包装，豆绿色及白色缎带束扎');
INSERT INTO `goods` VALUES (54, '1020', '梵高印象', 9, 393.00, 599.00, 599, 0, '2023-12-26 20:47:12', 0, ' 最永恒的幸福不是拥有你 而是拥有和你相关的回忆。', 1, NULL, 0, 'http://localhost/easyfile/youqing5.jpg', ' 12朵海洋之歌，2支向日葵，1朵蓝绣球，尤加利、高山积雪、情人草、小绿菊搭配', ' 内衬白色雾面纸，外层绿色和蓝色双面欧雅纸层次圆形包装，蓝色缎带束扎');
INSERT INTO `goods` VALUES (55, '1021', '诚挚祝福', 9, 300.00, 476.00, 476, 0, '2023-12-26 20:49:05', 0, '陪你静守皓月星辰，一起分享秋的果实，祝福你，平安喜乐', 1, NULL, 0, 'http://localhost/easyfile/youqing6.jpg', '6朵向日葵，粉色小雏菊丰满搭配，尤加利间插', '内衬咖啡色雪梨纸，外层灰色牛皮纸扇形包装，条纹缎带束扎');
INSERT INTO `goods` VALUES (56, '1022', '相见欢', 9, 236.00, 443.00, 443, 0, '2023-12-26 20:50:02', 0, ' 我喜欢有温暖的阳光，有醉人的花香，还有你的陪伴', 1, NULL, 0, 'http://localhost/easyfile/youqing7.jpg', ' 6支向日葵，2支白色洋桔梗，3支绿色雏菊，尤加利叶点缀、银叶菊搭配，栀子叶间插', '黄色雾面纸加蓝绿色雾面纸扇形包装，条纹缎带束扎');
INSERT INTO `goods` VALUES (57, '1023', '财源如春潮', 8, 198.00, 446.00, 446, 0, '2023-12-26 20:57:00', 0, '春意春前草，财源雨后泉', 1, NULL, 0, 'http://localhost/easyfile/kaiye1.jpg', '单个28支各色扶郎花，尤加利等叶间插', '三脚架花篮，红金色欧雅纸束腰');
INSERT INTO `goods` VALUES (58, '1024', '大卖-时尚大麦开业花篮 ', 8, 139.00, 799.00, 798, 0, '2023-12-26 21:00:05', 0, ' 红红火火，大卖！', 1, NULL, 0, 'http://localhost/easyfile/kaiye2.jpg', ' 大麦，单个约300支', '三角架花篮，卡其色欧雅纸韩式包装，咖啡色缎带束扎。');
INSERT INTO `goods` VALUES (59, '1025', '生意永盛', 8, 288.00, 782.00, 782, 0, '2023-12-26 21:02:18', 0, ' 门迎晓日财源广，户纳春风喜庆多。', 1, NULL, 0, 'http://localhost/easyfile/kaiye3.jpg', '28支混色扶郎，3朵粉百合，情人草、绿叶、散尾葵搭配', '三脚架开业花篮，红+金色双面欧雅纸包裹，红色英文缎带束扎');
INSERT INTO `goods` VALUES (60, '1026', '步步升高', 8, 198.00, 287.00, 278, 0, '2023-12-26 21:05:48', 0, ' 财源滚滚达三江 ，生意兴隆通四海。', 1, NULL, 0, 'http://localhost/easyfile/kaiye4.jpg', '各种扶郎花，巴西叶、散尾叶、绿叶搭配。', '开业花篮');
INSERT INTO `goods` VALUES (61, '1027', '财恒利大', 8, 498.00, 629.00, 629, 0, '2023-12-26 21:09:14', 0, '永隆大业，昌裕后人。', 1, NULL, 0, 'http://localhost/easyfile/kaiye5.jpg', '红玫瑰适量，10支红色太阳花，2支红掌，尤加利间插', ' 三脚架开业花篮，灰色韩素纸包装，黑色英文缎带束扎');
INSERT INTO `goods` VALUES (62, '1028', '前程似锦', 8, 146.00, 274.00, 264, 0, '2023-12-26 21:10:16', 0, '生意似竹笋，财源如春潮', 1, NULL, 0, 'http://localhost/easyfile/kaiye6.jpg', '各色扶郎花,粉色百合花，散尾葵丰满，搭配绿叶', '开业花篮');
INSERT INTO `goods` VALUES (63, '10060', 'aaa', 3, 121.00, 112.00, 55, 0, '2024-01-22 22:15:00', 1, NULL, 1, NULL, 0, 'http://localhost:80/easyfile/f8382fc4-0d24-480a-bf07-083da7239030.png', NULL, NULL);
INSERT INTO `goods` VALUES (64, '12321', '123', 3, 123.00, 123.00, 31, 0, '2024-01-22 22:16:39', 1, NULL, 1, NULL, 0, 'http://localhost:80/easyfile/e70276b0-9f41-438d-a8d4-a60ba002ac22.png', NULL, NULL);
INSERT INTO `goods` VALUES (65, '789', '789', 8, 77.00, 77.00, 77, 0, '2024-01-22 22:19:05', 1, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (66, '121', '12', 3, 11.00, 11.00, 11, 0, '2024-01-22 22:20:03', 1, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (67, '11', '11', 3, 11.00, 11.00, 11, 0, '2024-01-22 22:21:05', 1, 'hello2', 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (68, '123', 'qq', 0, 1.00, 1.00, 1, 0, '2024-01-22 22:21:59', 1, 'hello2', 1, NULL, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `uid` int NULL DEFAULT NULL,
  `ordertime` datetime NULL DEFAULT NULL,
  `paytime` datetime NULL DEFAULT NULL,
  `deliverytime` datetime NULL DEFAULT NULL,
  `gettime` datetime NULL DEFAULT NULL,
  `endtime` datetime NULL DEFAULT NULL,
  `state` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0,
  `paymoney` decimal(10, 2) NULL DEFAULT NULL,
  `del` tinyint NULL DEFAULT 0,
  `adminid` int NULL DEFAULT NULL,
  `oaddress` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, '101', 1, '2023-12-20 16:21:00', '2023-12-20 16:21:02', '2024-01-26 13:43:05', '2023-12-20 16:21:08', '2023-12-20 16:21:12', 2, 477.00, 0, NULL, '山东省青岛市城阳区中享思途');
INSERT INTO `order` VALUES (4, '940953331954143232', 1, '2024-01-06 10:13:42', '2024-01-06 10:13:42', NULL, '2024-01-20 11:29:56', NULL, 3, 488.00, 0, NULL, '山东省青岛市城阳区中享思途');
INSERT INTO `order` VALUES (5, '941042123553689600', 1, '2024-01-06 16:06:31', '2024-01-06 16:06:31', NULL, '2024-01-20 10:27:16', NULL, 3, 488.00, 0, NULL, '山东省青岛市城阳区中享思途');
INSERT INTO `order` VALUES (9, '941674781744877568', 1, '2024-01-08 10:00:29', '2024-01-08 10:00:29', NULL, NULL, NULL, 1, 544.00, 0, NULL, '山东省青岛市城阳区中享思途');
INSERT INTO `order` VALUES (10, '941675171991310336', 1, '2024-01-08 10:02:02', '2024-01-08 10:02:02', NULL, NULL, NULL, 1, 544.00, 0, NULL, '北京市市辖区西城区');
INSERT INTO `order` VALUES (11, '941675718056136704', 1, '2024-01-08 10:04:12', '2024-01-08 10:04:12', NULL, NULL, NULL, 1, 398.00, 0, NULL, '山东省青岛市城阳区中享思途');
INSERT INTO `order` VALUES (12, '941678469368565760', 1, '2024-01-08 10:15:08', '2024-01-08 10:15:08', NULL, NULL, NULL, 3, 139.00, 0, NULL, '北京市市辖区西城区');
INSERT INTO `order` VALUES (13, '942137080972627968', 1, '2024-01-09 16:37:29', '2024-01-09 16:37:29', NULL, NULL, NULL, 3, 146.00, 0, NULL, '北京市市辖区西城区');
INSERT INTO `order` VALUES (14, '942857550516768768', 1, '2024-01-11 16:20:23', '2024-01-11 16:20:23', NULL, NULL, NULL, 1, 1390.00, 0, NULL, '北京市市辖区西城区');
INSERT INTO `order` VALUES (15, '943228731761676288', 1, '2024-01-12 16:55:19', '2024-01-20 11:28:19', NULL, NULL, NULL, 1, 816.00, 0, NULL, '北京市市辖区西城区');
INSERT INTO `order` VALUES (16, '943271733209124864', 2, '2024-01-12 19:46:11', '2024-01-12 19:46:11', NULL, NULL, NULL, 0, 207.00, 0, NULL, '北京市市辖区西城区');
INSERT INTO `order` VALUES (17, '943653124253995008', 1, '2024-01-13 21:01:42', '2024-01-13 21:01:42', NULL, NULL, NULL, 0, 334.00, 0, NULL, '');
INSERT INTO `order` VALUES (18, '946021837179506688', 1, '2024-01-20 09:54:07', '2024-01-20 11:23:18', NULL, NULL, NULL, 1, 1709.00, 0, NULL, '山东省青岛市城阳区中享思途');
INSERT INTO `order` VALUES (19, '946762372198486016', 1, '2024-01-22 10:56:45', '2024-01-22 10:57:39', NULL, NULL, NULL, 1, 2720.00, 0, NULL, '山东省青岛市城阳区中享思途');
INSERT INTO `order` VALUES (20, '946762498161823744', 1, '2024-01-22 10:57:15', NULL, NULL, NULL, NULL, 0, NULL, 0, NULL, '');
INSERT INTO `order` VALUES (21, '947197526670888960', 1, '2024-01-23 15:45:54', NULL, NULL, NULL, NULL, 0, NULL, 0, NULL, '山东省青岛市城阳区中享思途');
INSERT INTO `order` VALUES (22, '948251865946574848', 1, '2024-01-26 13:35:28', '2024-01-26 13:35:35', NULL, NULL, NULL, 1, 479.00, 0, NULL, '天津市市辖区河西区海天区');
INSERT INTO `order` VALUES (23, '948275028264800256', 1, '2024-01-26 15:07:30', NULL, NULL, NULL, NULL, 0, NULL, 0, NULL, '天津市市辖区河东区');

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `goodsid` int NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of picture
-- ----------------------------
INSERT INTO `picture` VALUES (1, 1, 'http://localhost/easyfile/youqing2.jpg');
INSERT INTO `picture` VALUES (2, 0, NULL);
INSERT INTO `picture` VALUES (3, 1, 'http://localhost:80/easyfile/6632feb8-afec-4c66-b2c2-e5c6d13975eb.png');
INSERT INTO `picture` VALUES (4, 1, 'http://localhost:80/easyfile/cd97646a-b1f5-4fab-83c6-9e0cb7c63787.png');
INSERT INTO `picture` VALUES (5, 1, 'http://localhost:80/easyfile/cd6d3d27-9970-4138-a45b-b5d7fbf0866f.png');
INSERT INTO `picture` VALUES (6, 1, 'http://localhost:80/easyfile/cd97646a-b1f5-4fab-83c6-9e0cb7c63787.png');
INSERT INTO `picture` VALUES (7, 1, 'http://localhost:80/easyfile/cd6d3d27-9970-4138-a45b-b5d7fbf0866f.png');
INSERT INTO `picture` VALUES (8, 1, 'http://localhost:80/easyfile/cd97646a-b1f5-4fab-83c6-9e0cb7c63787.png');
INSERT INTO `picture` VALUES (9, 1, 'http://localhost:80/easyfile/2aa800a4-33ab-4860-80ba-5fc0fd1bdb83.png');
INSERT INTO `picture` VALUES (10, 1, 'http://localhost:80/easyfile/02800078-9315-42ff-88ba-b0a9c6fd2a86.png');
INSERT INTO `picture` VALUES (11, 1, 'http://localhost:80/easyfile/134aea74-c0bf-49d3-aa1b-1867a7c061fe.png');
INSERT INTO `picture` VALUES (12, 1, 'http://localhost:80/easyfile/cc49100d-2dc3-4804-b8a8-d50e7cafc7e1.png');
INSERT INTO `picture` VALUES (13, 1, 'http://localhost:80/easyfile/32133800-d640-4086-98f2-0638237c15c2.png');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `state` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_user_manager', '用户管理员', 0);
INSERT INTO `role` VALUES (2, 'ROLE_personal_manager', '人事管理员', 0);

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user`  (
  `roleid` int NOT NULL,
  `userid` int NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES (2, 2);

-- ----------------------------
-- Table structure for shoppingcar
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcar`;
CREATE TABLE `shoppingcar`  (
  `userid` int NOT NULL,
  `goodsid` int NOT NULL,
  `jointime` datetime NULL DEFAULT NULL,
  `goodsprice` decimal(10, 2) NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  `check` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`userid`, `goodsid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcar
-- ----------------------------
INSERT INTO `shoppingcar` VALUES (2, 37, '2023-12-28 19:48:24', 199.00, 2, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `upassword` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `del` tinyint UNSIGNED NULL DEFAULT 0,
  `email` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '',
  `uphone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `usex` char(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `ustate` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '是否封禁,1正常,2封禁',
  `adminid` int NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `captcha` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', 'c30845fd624f38f1d8af2f837898bfe267', 0, '15344678@163.con', '13478949877', '男', 2, NULL, 'http://localhost:80/easyfile/0891610a-dd00-4003-8cce-38d2eed2bfcf.png', NULL);
INSERT INTO `user` VALUES (2, 'wangwu', '92e9e3d32d23a22f7aef0cbb2da2209ec7', 0, '154654@163.com', '11111@136.com', 'nan', 1, NULL, 'http://localhost/easyfile/qinqing1.jpg', NULL);
INSERT INTO `user` VALUES (3, 'lisi', '123456', 0, '12312312@163.com', '15365468795', '男', 2, NULL, 'http://localhost/easyfile/qinqing2.jpg', NULL);
INSERT INTO `user` VALUES (4, 'zhaoliu', '123456', 0, '16944455@163.com', '15788845611', '男', 1, NULL, 'http://localhost/easyfile/qinqing2.jpg', NULL);
INSERT INTO `user` VALUES (22, '张光磊', 'dea7f9845a84f4dbabad54ae59ea57e57e', 0, '1364087591@qq.com', '15006909395', NULL, 1, NULL, 'http://localhost:80/easyfile/10e51d7e-99da-4c00-b93f-26a443f9155c.jpg', NULL);
INSERT INTO `user` VALUES (23, 'aaaq', 'c4bae18c16e362cd47d91aac8043c0fc35', 0, '1364087591@qq.com', '15006909396', NULL, 1, NULL, 'http://localhost:80/easyfile/7e8178d8-e12b-496c-bd5c-d085cc0e5e07.png', NULL);
INSERT INTO `user` VALUES (24, 'aaap', '26a8c797cb96d868b19870685cf34927b8', 0, '1364087591@qq.com', '15006909396', NULL, 1, NULL, NULL, NULL);
INSERT INTO `user` VALUES (25, 'qqq', '37293a58dc156c14f8e2df1bc40b1dd3d0', 0, '1364087591@qq.com', '13478947894', NULL, 1, NULL, 'http://localhost:80/easyfile/6d9a2f4f-b622-479c-b036-e4aab5d84a1b.png', NULL);

-- ----------------------------
-- Table structure for zuji
-- ----------------------------
DROP TABLE IF EXISTS `zuji`;
CREATE TABLE `zuji`  (
  `gid` int NOT NULL,
  `uid` int NULL DEFAULT NULL,
  `del` tinyint NULL DEFAULT 0,
  `jointime` datetime NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zuji
-- ----------------------------
INSERT INTO `zuji` VALUES (58, 1, 0, '2024-01-11 21:46:01');
INSERT INTO `zuji` VALUES (2, 2, 0, '2024-01-13 10:32:23');
INSERT INTO `zuji` VALUES (6, 1, 0, '2024-01-26 15:06:27');
INSERT INTO `zuji` VALUES (40, 1, 0, '2024-01-13 21:01:21');
INSERT INTO `zuji` VALUES (2, 1, 0, '2024-01-23 15:34:25');
INSERT INTO `zuji` VALUES (1, 1, 0, '2024-01-19 20:26:31');
INSERT INTO `zuji` VALUES (36, 1, 0, '2024-01-26 10:26:12');
INSERT INTO `zuji` VALUES (38, 1, 0, '2024-01-26 13:36:01');
INSERT INTO `zuji` VALUES (6, 25, 0, '2024-01-26 13:44:33');
INSERT INTO `zuji` VALUES (1, 25, 0, '2024-01-26 13:44:09');

SET FOREIGN_KEY_CHECKS = 1;
