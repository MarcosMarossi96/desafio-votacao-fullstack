CREATE TABLE `session` (
  `agenda_id` bigint NOT NULL,
  `end_date` datetime(6) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `start_date` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKcg74ux2wdevt57if0u3vgd0ap` (`agenda_id`),
  CONSTRAINT `FK5plplscja1fiqh514swdm6i2m` FOREIGN KEY (`agenda_id`) REFERENCES `agenda` (`id`)
)