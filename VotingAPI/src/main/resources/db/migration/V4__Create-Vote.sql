CREATE TABLE `vote` (
  `vote` bit(1) NOT NULL,
  `associate_id` bigint NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `session_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5ki8q5kgx4bmjd1jh473hqbra` (`associate_id`),
  KEY `FK52gw0umav8w85f9jjxspuj86f` (`session_id`),
  CONSTRAINT `FK52gw0umav8w85f9jjxspuj86f` FOREIGN KEY (`session_id`) REFERENCES `session` (`id`),
  CONSTRAINT `FK5ki8q5kgx4bmjd1jh473hqbra` FOREIGN KEY (`associate_id`) REFERENCES `associate` (`id`)
)