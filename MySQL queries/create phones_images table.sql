CREATE TABLE `phones_db`.`phones_images` (
  `phone_id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NULL,
  `phone_image` MEDIUMBLOB NULL,
  PRIMARY KEY (`phone_id`));