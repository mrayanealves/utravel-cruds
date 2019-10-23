-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema utravel
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `utravel` ;

-- -----------------------------------------------------
-- Schema utravel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `utravel` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `utravel` ;

-- -----------------------------------------------------
-- Table `viagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `viagem` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `viagem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_inicio` DATE NULL,
  `data_fim` DATE NULL,
  `titulo` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pessoa` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pessoa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(11) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `cpf_UNIQUE` ON `pessoa` (`cpf` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuario` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pessoa_id` INT NOT NULL,
  `telefone` VARCHAR(13) NULL,
  `email` VARCHAR(255) NULL,
  `senha` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `usuario_viagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuario_viagem` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `usuario_viagem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `viagem_id` INT NOT NULL,
  `gerencia` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `localizacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `localizacao` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `localizacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cidade` VARCHAR(100) NOT NULL,
  `estado` VARCHAR(100) NULL,
  `pais` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `viagem_destino`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `viagem_destino` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `viagem_destino` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `viagem_id` INT NOT NULL,
  `destino_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `transporte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `transporte` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `transporte` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NULL,
  `proprio` TINYINT NULL,
  `viagem_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `empresa` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `empresa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cnpj` VARCHAR(25) NULL,
  `nome` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `passagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `passagem` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `passagem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_ida` DATE NULL,
  `data_chegada` DATE NULL,
  `valor_pago` FLOAT NULL,
  `origem_id` INT NOT NULL,
  `destino_id` INT NOT NULL,
  `transporte_id` INT NOT NULL,
  `empresa_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `passeio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `passeio` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `passeio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NULL,
  `endereco` VARCHAR(45) NULL,
  `destino_id` INT NOT NULL,
  `empresa_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `viagem_passeio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `viagem_passeio` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `viagem_passeio` (
  `viagem_id` INT NOT NULL,
  `passeio_id` INT NOT NULL,
  `data` DATE NULL,
  `hora` TIME NULL,
  PRIMARY KEY (`viagem_id`, `passeio_id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `estadia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `estadia` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `estadia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(12) NULL,
  `endereco` VARCHAR(156) NULL,
  `tipo` VARCHAR(45) NULL,
  `quantidade_quartos` INT NULL,
  `valor_pago` FLOAT NULL,
  `viagem_destino_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `restaurante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurante` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `restaurante` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(100) NULL,
  `avaliacao` VARCHAR(15) NULL,
  `empresa_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `orcamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `orcamento` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `orcamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NULL,
  `valor_estimado` FLOAT NULL,
  `viagem_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `postagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `postagem` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `postagem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NULL,
  `texto` TEXT NULL,
  `data` DATE NULL,
  `usuario_viagem_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `midia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `midia` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `midia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NULL,
  `legenda` VARCHAR(45) NULL,
  `caminho` VARCHAR(45) NULL,
  `postagem_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `telefone_restaurante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `telefone_restaurante` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `telefone_restaurante` (
  `restaurante_id` INT NOT NULL,
  `telefone` VARCHAR(25) NULL,
  PRIMARY KEY (`restaurante_id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `reserva`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reserva` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `reserva` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` MEDIUMTEXT NULL,
  `data` DATE NULL,
  `hora` TIME NULL,
  `mesa` VARCHAR(25) NULL,
  `restaurante_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `viagem_reserva`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `viagem_reserva` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `viagem_reserva` (
  `viagem_id` INT NOT NULL,
  `reserva_id` INT NOT NULL)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `categoria` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NULL,
  `preco` FLOAT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `locadora`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `locadora` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `locadora` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `site` VARCHAR(45) NULL,
  `endereco` VARCHAR(45) NULL,
  `empresa_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `veiculo` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `veiculo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `modelo` VARCHAR(45) NULL,
  `cor` VARCHAR(45) NULL,
  `placa` VARCHAR(45) NULL,
  `quilometragem` VARCHAR(45) NULL,
  `categoria_id` INT NOT NULL,
  `locadora_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `viagem_veiculo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `viagem_veiculo` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `viagem_veiculo` (
  `viagem_id` INT NOT NULL,
  `veiculo_id` INT NOT NULL,
  `data_inicio_aluguel` DATE NULL,
  `data_devolucao` DATE NULL)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `telefone_locadora`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `telefone_locadora` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `telefone_locadora` (
  `locadora_id` INT NOT NULL,
  `telefone` VARCHAR(45) NULL)
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
