SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Trivia` DEFAULT CHARACTER SET utf8 ;
USE `Trivia` ;



-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `location` (
  `idLocation` INT NOT NULL,
  `locationName` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`idLocation`),
  INDEX `locationName_idx` USING BTREE (`locationName` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `country` (
  `idCountry` INT NOT NULL,
  `countryName` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`idCountry`),
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
  `cityName` VARCHAR(128) NOT NULL,
  `idCountryOfCity` INT,
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
  `idPerson` INT NOT NULL ,
  `personName` VARCHAR(128) NOT NULL,
  `idLocationOfPerson` INT NOT NULL,
  `birthYear` INT NOT NULL,
  `idbirthCity` INT NOT NULL,
  `idCurrentCity` INT NOT NULL,
  `sportField` CHAR(24) NOT NULL,
  `idTeam` INT NOT NULL,
  `idAward` INT NOT NULL,
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
-- Table `coach`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `coach` (
  `idCoach` INT NOT NULL,
  PRIMARY KEY (`idCoach`),
  CONSTRAINT `idCoach`
    FOREIGN KEY (`idCoach`)
    REFERENCES `person` (`idPerson`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `award`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `award` (
  `idAward` INT NOT NULL ,
  `AwardName` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`idAward`),
  INDEX `awardName_idx` USING BTREE (`awardName` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `award_player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `award_player` (
	`idAwardOfPlayer` INT NOT NULL,
    `idPersonOfAward` INT NOT NULL,
	CONSTRAINT `idAwardOfPlayer`
		FOREIGN KEY (`idAwardOfPlayer`)
		REFERENCES `award` (`idAward`)
		ON DELETE CASCADE
		ON UPDATE RESTRICT,
	CONSTRAINT `idPersonOfAward`
		FOREIGN KEY (`idPersonOfAward`)
		REFERENCES `person` (`idPerson`)
		ON DELETE CASCADE
		ON UPDATE RESTRICT);

-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `event` (
  `idEvent` INT NOT NULL ,
  `eventName` VARCHAR(128) NOT NULL,
  `sportField` CHAR(25) NOT NULL,
  `idLocation` INT NOT NULL,
  PRIMARY KEY (`idEvent`),
  INDEX `eventName_idx` USING BTREE (`eventName` ASC))
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

-- -----------------------------------------------------
-- Table `stadium`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stadium` (
  `idStadium` INT NOT NULL ,
  `stadiumName` VARCHAR(128) NOT NULL,
  `idCityOfStadium` INT NOT NULL,
  PRIMARY KEY (`idStadium`),
  INDEX `stadiumName_idx` USING BTREE (`stadiumName` ASC),
  CONSTRAINT `idCityOfStadium`
    FOREIGN KEY (`idCityOfStadium`)
    REFERENCES `city` (`idCity`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `team` (
  `idTeam` INT NOT NULL ,
  `teamName` VARCHAR(128) NOT NULL,
  `sportField` CHAR(25) NOT NULL,
  `idCityOfTeam` INT NOT NULL,
  `idStadiumOfTeam` INT NOT NULL,
  PRIMARY KEY (`idTeam`),
  INDEX `teamName_idx` USING BTREE (`teamName` ASC),
  CONSTRAINT `idCityOfTeam`
    FOREIGN KEY (`idCityOfTeam`)
    REFERENCES `city` (`idCity`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `idStadiumOfTeam`
    FOREIGN KEY (`idStadiumOfTeam`)
    REFERENCES `stadium` (`idstadium`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `person_team` ----------------------------
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `person_team` (
  `idTeamOfPerson` INT NOT NULL ,
  `idPersonOfTeam` INT NOT NULL,
  INDEX `idTeamOfPerson_idx` (`idTeamOfPerson` ASC),
  INDEX `idPersonOfTeam_idx` (`idPersonOfTeam` ASC),
  CONSTRAINT `idTeamOfPerson`
    FOREIGN KEY (`idTeamOfPerson`)
    REFERENCES `team` (`idTeam`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `idPersonOfTeam`
    FOREIGN KEY (`idPersonOfTeam`)
    REFERENCES `person` (`idPerson`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users` (
  `idUsers` INT NOT NULL AUTO_INCREMENT,
  `userName` CHAR(24) NOT NULL,
  `hashPassword` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`idUsers`),
  INDEX `userName_idx` USING BTREE (`userName` ASC))
ENGINE = MyISAM;

