-- MySQL Script generated by MySQL Workbench
-- Sun Apr 15 173934 2018
-- Model New Model    Version 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema buzz
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema buzz
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `buzz` DEFAULT CHARACTER SET utf8 ;
USE `buzz` ;

-- -----------------------------------------------------
-- Table `buzz`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `buzz`.`order` (
  `OrderId` INT NOT NULL AUTO_INCREMENT,
  `BuyerName` VARCHAR(120) NOT NULL,
  `BuyerEmail` VARCHAR(45) NOT NULL,
  `OrderDate` DATE NOT NULL,
  `OrderTotalValue` FLOAT NOT NULL,
  `Adress` VARCHAR(200) NOT NULL,
  `Postcode` INT NOT NULL,
  PRIMARY KEY (`OrderId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `buzz`.`order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `buzz`.`order_item` (
  `OrderItemId` INT NOT NULL AUTO_INCREMENT,
  `OrderId` INT NOT NULL,
  `SalePrice` FLOAT NOT NULL,
  `ShippingPrice` FLOAT NOT NULL,
  `TotalItemPrice` FLOAT NOT NULL,
  `SKU` VARCHAR(45) NOT NULL,
  `Status` ENUM('IN_STOCK', 'OUT_OF_STOCK') NOT NULL,
  PRIMARY KEY (`OrderItemId`),
  INDEX `fk_order_item_order_idx` (`OrderId` ASC),
  CONSTRAINT `fk_order_item_order`
    FOREIGN KEY (`OrderId`)
    REFERENCES `buzz`.`order` (`OrderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
