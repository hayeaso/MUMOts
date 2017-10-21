DROP TABLE IF EXISTS `passwordresettoken`;
CREATE TABLE `passwordresettoken` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expiryDate` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `passwordToken_user_userId` (`user_userId`),
  CONSTRAINT `passwordToken_user_userId` FOREIGN KEY (`user_userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


