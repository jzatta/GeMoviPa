CREATE DATABASE IF NOT EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test`.`sales` (`idsales` INT NOT NULL AUTO_INCREMENT, `fullPass` INT NOT NULL, `halfPass` INT NOT NULL, `freePass` INT NOT NULL, `departure` TIMESTAMP NOT NULL, `sellerName` VARCHAR(100) NOT NULL, `sellerEnterprise` VARCHAR(100) NOT NULL, `boatName` VARCHAR(100) NOT NULL, `boatEnterprise` VARCHAR(100) NOT NULL, PRIMARY KEY (`idsales`));
CREATE TABLE IF NOT EXISTS `test`.`tours` (`idtours` INT NOT NULL AUTO_INCREMENT, `fullPass` INT NOT NULL, `halfPass` INT NOT NULL, `freePass` INT NOT NULL, `departure` TIMESTAMP NOT NULL, `boatName` VARCHAR(100) NOT NULL, `boatEnterprise` VARCHAR(100) NOT NULL, PRIMARY KEY (`idtours`));
CREATE TABLE IF NOT EXISTS `test`.`enterprises` (`identerprises` INT NOT NULL AUTO_INCREMENT,`enterpriseName` VARCHAR(100) NOT NULL, PRIMARY KEY (`identerprises`));
CREATE TABLE IF NOT EXISTS `test`.`boats` (`idboats` INT NOT NULL AUTO_INCREMENT,`boatName` VARCHAR(100) NOT NULL,`boatEnterprise` VARCHAR(100) NOT NULL,`boatCapacity` INT NOT NULL,`tourCost` DOUBLE NOT NULL,PRIMARY KEY (`idboats`));
CREATE TABLE IF NOT EXISTS `test`.`sellers` (`idsellers` INT NOT NULL AUTO_INCREMENT, `sellerName` VARCHAR(100) NOT NULL,`sellersEnterprise` VARCHAR(100) NOT NULL, PRIMARY KEY (`idsellers`));