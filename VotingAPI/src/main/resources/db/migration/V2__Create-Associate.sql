CREATE TABLE `associate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3dbmmbrmnk0rwhqff0oxaxlc6` (`cpf`)
)