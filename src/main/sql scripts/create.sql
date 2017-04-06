CREATE TABLE `clients` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `contacts` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	`address` VARCHAR(100),
	`phone` VARCHAR(20),
	`email` VARCHAR(100),
	`client_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `services` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	`price` INT,
	PRIMARY KEY (`id`)
);

CREATE TABLE `contracts` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`service` INT NOT NULL,
	`client` INT NOT NULL,
	`time_start` DATETIME NOT NULL,
	`time_end` DATETIME,
	PRIMARY KEY (`id`)
);

CREATE TABLE `servants` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	`address` VARCHAR(100),
	`phone` VARCHAR(20),
	`email` VARCHAR(100),
	`education_level` VARCHAR(50),
	`position` VARCHAR(50),
	PRIMARY KEY (`id`)
);

CREATE TABLE `contract_servant` (
	`contract` INT NOT NULL,
	`servant` INT NOT NULL
);

ALTER TABLE `contacts` ADD CONSTRAINT `contacts_fk0` FOREIGN KEY (`client_id`) REFERENCES `clients`(`id`);

ALTER TABLE `contracts` ADD CONSTRAINT `contracts_fk0` FOREIGN KEY (`service`) REFERENCES `services`(`id`);

ALTER TABLE `contracts` ADD CONSTRAINT `contracts_fk1` FOREIGN KEY (`client`) REFERENCES `clients`(`id`);

ALTER TABLE `contract_servant` ADD CONSTRAINT `contract_servant_fk0` FOREIGN KEY (`contract`) REFERENCES `contracts`(`id`);

ALTER TABLE `contract_servant` ADD CONSTRAINT `contract_servant_fk1` FOREIGN KEY (`servant`) REFERENCES `servants`(`id`);

