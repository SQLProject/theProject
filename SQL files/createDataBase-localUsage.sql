SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Trivia` DEFAULT CHARACTER SET utf8 ;
USE `Trivia` ;


-- -----------------------------------------------------
-- Table `language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `language` (
  `idLanguage` TINYINT NOT NULL AUTO_INCREMENT,
  `languageName` CHAR(20) NOT NULL,
  UNIQUE INDEX `user_lang_unq` (`languageName` ASC),
  PRIMARY KEY (`idLanguage`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `location` (
  `idLocation` INT NOT NULL AUTO_INCREMENT,
  `locationName` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`idLocation`),
  INDEX `locationName_idx` USING BTREE (`locationName` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `country` (
  `idCountry` INT NOT NULL AUTO_INCREMENT,
  `idLanguage` TINYINT NULL DEFAULT NULL,
  `countryName` VARCHAR(128) NOT NULL,
  `wiki` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idCountry`),
  INDEX `idLanguage_idx` (`idLanguage` ASC),
  INDEX `countryName` (`countryName` (128)),
  CONSTRAINT `idCountry`
    FOREIGN KEY (`idCountry`)
    REFERENCES `location` (`idLocation`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `city`  TODO:Check the idCountryOfCity issue
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `city` (
  `idCity` INT NOT NULL,
  `idCountryOfCity` INT NOT NULL,
  `cityName` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`idCity`),
  INDEX `cityName` (`cityName` (128)),
  CONSTRAINT `idCountryOfCity`
    FOREIGN KEY (`idCountryOfCity`)
    REFERENCES `country` (`idCountry`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `idCity`
    FOREIGN KEY (`idCity`)
    REFERENCES `location` (`idLocation`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT,
  `personName` VARCHAR(25) NOT NULL,
  `idLocationOfPerson` INT NOT NULL,
  PRIMARY KEY (`idPerson`),
  INDEX `personName_idx` USING BTREE (`personName` ASC))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `football_player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football_player` (
  `idFootballPlayer` INT NOT NULL,
  PRIMARY KEY (`idFootballPlayer`),
  CONSTRAINT `idFootballPlayer`
    FOREIGN KEY (`idFootballPlayer`)
    REFERENCES `person` (`idPerson`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `basketball_player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basketball_player` (
  `idBasketballPlayer` INT NOT NULL,
  PRIMARY KEY (`idBasketballPlayer`),
  CONSTRAINT `idBasketballPlayer`
    FOREIGN KEY (`idBasketballPlayer`)
    REFERENCES `person` (`idPerson`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tenis_player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tenis_player` (
  `idTenisPlayer` INT NOT NULL,
  PRIMARY KEY (`idTenisPlayer`),
  CONSTRAINT `idTenisPlayer`
    FOREIGN KEY (`idTenisPlayer`)
    REFERENCES `person` (`idPerson`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `language_country` ----------------------------
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `language_country` (
  `idCountryOfLanguage` INT NOT NULL AUTO_INCREMENT,
  `idLanguageOfCountry` TINYINT NOT NULL,
  INDEX `idCountry_idx` (`idCountryOfLanguage` ASC),
  INDEX `idLanguage_idx` (`idLanguageOfCountry` ASC),
  PRIMARY KEY (`idCountryOfLanguage`, `idLanguageOfCountry`),
  CONSTRAINT `idLanguageOfCountry`
    FOREIGN KEY (`idLanguageOfCountry`)
    REFERENCES `language` (`idLanguage`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `idCountryOfLanguage`
    FOREIGN KEY (`idCountryOfLanguage`)
    REFERENCES `country` (`idCountry`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `event` (
  `idEvent` INT NOT NULL AUTO_INCREMENT,
  `eventName` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`idEvent`),
  INDEX `eventName_idx` USING BTREE (`eventName` ASC))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `football_event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `football_event` (
  `idFootballEvent` INT NOT NULL,
  PRIMARY KEY (`idFootballEvent`),
  CONSTRAINT `idFootballEvent`
    FOREIGN KEY (`idFootballEvent`)
    REFERENCES `event` (`idEvent`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `basketball_event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basketball_event` (
  `idBasketballEvent` INT NOT NULL,
  PRIMARY KEY (`idBasketballEvent`),
  CONSTRAINT `idBasketballEvent`
    FOREIGN KEY (`idBasketballEvent`)
    REFERENCES `event` (`idEvent`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tenis_event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tenis_event` (
  `idTenisEvent` INT NOT NULL,
  PRIMARY KEY (`idTenisEvent`),
  CONSTRAINT `idTenisEvent`
    FOREIGN KEY (`idTenisEvent`)
    REFERENCES `event` (`idEvent`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `event_location` ----------------------------
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `event_location` (
  `idLocationOfEvent` INT NOT NULL,
  `idEventOfLocation` INT NOT NULL,
  INDEX `idLocationOfEvent_idx` (`idLocationOfEvent` ASC),
  INDEX `idEventOfLocation_idx` (`idEventOfLocation` ASC),
  CONSTRAINT `idLocationOfEvent`
    FOREIGN KEY (`idLocationOfEvent`)
    REFERENCES `location` (`idLocation`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `idEventOfLocation`
    FOREIGN KEY (`idEventOfLocation`)
    REFERENCES `event` (`idEvent`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `person_location` ----------------------------
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `person_location` (
  `idLocationOfPerson` INT NOT NULL ,
  `idPersonOfLocation` INT NOT NULL,
  INDEX `idLocationOfPerson_idx` (`idLocationOfPerson` ASC),
  INDEX `idPersonOfLocation_idx` (`idPersonOfLocation` ASC),
  CONSTRAINT `idLocationOfPerson`
    FOREIGN KEY (`idLocationOfPerson`)
    REFERENCES `location` (`idLocation`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `idPersonOfLocation`
    FOREIGN KEY (`idPersonOfLocation`)
    REFERENCES `person` (`idPerson`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;
	