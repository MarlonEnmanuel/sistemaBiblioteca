-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema biblioteca2017
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `biblioteca2017` ;

-- -----------------------------------------------------
-- Schema biblioteca2017
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `biblioteca2017` DEFAULT CHARACTER SET utf8 ;
USE `biblioteca2017` ;

-- -----------------------------------------------------
-- Table `biblioteca2017`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca2017`.`persona` (
  `idpersona` INT NOT NULL AUTO_INCREMENT,
  `fechareg` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `codigo` VARCHAR(45) NOT NULL,
  `nombres` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `escuela` VARCHAR(45) NOT NULL,
  `facultad` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idpersona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca2017`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca2017`.`usuario` (
  `idusuario` INT NOT NULL AUTO_INCREMENT,
  `fechareg` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `estado` TINYINT(1) NOT NULL,
  `idpersona` INT NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE INDEX `user_UNIQUE` (`user` ASC),
  INDEX `fk_usuario_persona1_idx` (`idpersona` ASC),
  CONSTRAINT `fk_usuario_persona1`
    FOREIGN KEY (`idpersona`)
    REFERENCES `biblioteca2017`.`persona` (`idpersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca2017`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca2017`.`categoria` (
  `idcategoria` INT NOT NULL AUTO_INCREMENT,
  `fechareg` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` TEXT NOT NULL,
  `datos` TEXT NOT NULL,
  PRIMARY KEY (`idcategoria`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca2017`.`ejemplar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca2017`.`ejemplar` (
  `idejemplar` INT NOT NULL AUTO_INCREMENT,
  `fechareg` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `codigo` VARCHAR(45) NOT NULL,
  `titulo` TEXT NOT NULL,
  `autores` TEXT NOT NULL,
  `publicacion` DATE NOT NULL,
  `json` TEXT NOT NULL,
  `urlpdf` TEXT NOT NULL,
  `idcategoria` INT NOT NULL,
  PRIMARY KEY (`idejemplar`),
  INDEX `fk_ejemplar_categoria_idx` (`idcategoria` ASC),
  CONSTRAINT `fk_ejemplar_categoria`
    FOREIGN KEY (`idcategoria`)
    REFERENCES `biblioteca2017`.`categoria` (`idcategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca2017`.`tema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca2017`.`tema` (
  `idtema` INT NOT NULL AUTO_INCREMENT,
  `fechareg` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` TEXT NOT NULL,
  PRIMARY KEY (`idtema`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca2017`.`copia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca2017`.`copia` (
  `idcopia` INT NOT NULL AUTO_INCREMENT,
  `fechareg` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `codigo` VARCHAR(45) NOT NULL,
  `estado` TINYINT(1) NOT NULL DEFAULT 1,
  `disponible` TINYINT(1) NOT NULL,
  `idejemplar` INT NOT NULL,
  PRIMARY KEY (`idcopia`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC),
  INDEX `fk_copia_ejemplar1_idx` (`idejemplar` ASC),
  CONSTRAINT `fk_copia_ejemplar1`
    FOREIGN KEY (`idejemplar`)
    REFERENCES `biblioteca2017`.`ejemplar` (`idejemplar`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca2017`.`ejemplar_tema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca2017`.`ejemplar_tema` (
  `idejemplar` INT NOT NULL,
  `idtema` INT NOT NULL,
  PRIMARY KEY (`idejemplar`, `idtema`),
  INDEX `fk_ejemplar_has_tema_tema1_idx` (`idtema` ASC),
  INDEX `fk_ejemplar_has_tema_ejemplar1_idx` (`idejemplar` ASC),
  CONSTRAINT `fk_ejemplar_has_tema_ejemplar1`
    FOREIGN KEY (`idejemplar`)
    REFERENCES `biblioteca2017`.`ejemplar` (`idejemplar`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ejemplar_has_tema_tema1`
    FOREIGN KEY (`idtema`)
    REFERENCES `biblioteca2017`.`tema` (`idtema`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biblioteca2017`.`prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca2017`.`prestamo` (
  `idprestamo` INT NOT NULL AUTO_INCREMENT,
  `fechareg` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fechaini` DATETIME NOT NULL,
  `fechafin` DATETIME NOT NULL,
  `devuelto` TINYINT(1) NOT NULL,
  `idcopia` INT NOT NULL,
  `idpersona` INT NOT NULL,
  PRIMARY KEY (`idprestamo`),
  INDEX `fk_prestamo_copia1_idx` (`idcopia` ASC),
  INDEX `fk_prestamo_persona1_idx` (`idpersona` ASC),
  CONSTRAINT `fk_prestamo_copia1`
    FOREIGN KEY (`idcopia`)
    REFERENCES `biblioteca2017`.`copia` (`idcopia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prestamo_persona1`
    FOREIGN KEY (`idpersona`)
    REFERENCES `biblioteca2017`.`persona` (`idpersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
