CREATE TABLE `phones_db`.`clients_passwords` (
  `client_id` INT NOT NULL,
  `client_email` VARCHAR(45) NULL,
  `client_password` VARCHAR(45) NULL,
  PRIMARY KEY (`client_id`));