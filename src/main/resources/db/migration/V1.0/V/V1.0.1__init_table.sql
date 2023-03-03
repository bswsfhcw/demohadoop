DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sfzh` varchar(32) DEFAULT NULL,
  `xm` varchar(64) DEFAULT NULL,
  `xb` int(11) DEFAULT NULL COMMENT '1男2女3未知',
  PRIMARY KEY (`id`),
  KEY `idx_user_sfz` (`sfzh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员';