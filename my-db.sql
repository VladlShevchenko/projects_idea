-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role` (
  `id` INT NOT NULL,
  `name` VARCHAR(15) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;

insert into role values(1,"admin");
insert into role values(2,"user");
insert into role values(3,"guest");
-- -----------------------------------------------------
-- Table `mydb`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(15) NOT NULL,
  `bill` FLOAT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`, `role_id`),
  INDEX `fk_account_role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `mydb`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

insert into account values(1,"Oleg", "shootest@gmail.com",1111,20000,1);
insert into account values(2,"Vova", "skiing@gmail.com",2222,20000,1);
insert into account values(3,"Angel", "1307@gmail.com",3333,20000,1);
insert into account values(4,"Vasya", "chay@gmail.com",1234,20000,2);



-- -----------------------------------------------------
-- Table `mydb`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`status` (
  `id` INT NOT NULL,
  `name` VARCHAR(20) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;
insert into status values(1,"CONFIRMED");
insert into status values(2,"IN PROGRESS");
insert into status values(3,"PAID");
insert into status values(4,"CANCELED");

-- -----------------------------------------------------
-- Table `mydb`.`receipt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`receipt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `account_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`, `account_id`, `status_id`),
  INDEX `fk_receipt_account1_idx` (`account_id` ASC) VISIBLE,
  INDEX `fk_receipt_status1_idx` (`status_id` ASC) VISIBLE,
  CONSTRAINT `fk_receipt_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `mydb`.`account` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_receipt_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `mydb`.`status` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`topic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`topic` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
insert into mydb.topic values(1,"Сільське господарство. Присадибне господарство");
insert into mydb.topic values(2,"Охорона здоров'я Медицина");
insert into mydb.topic values(3,"Дитячі та молодіжні видання");
 insert into mydb.topic values(4,"Родина. Домівка. Побут");
  insert into mydb.topic values(5,"Видання для жінок. Видання для чоловіків");
-- -----------------------------------------------------
-- Table `mydb`.`publication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`publication` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `price_for_month` FLOAT NOT NULL,
  `image` LONGBLOB NULL,
  `description` VARCHAR(1024) NULL,
  `topic_id` INT NOT NULL,
  PRIMARY KEY (`id`, `topic_id`),
  INDEX `fk_publication_topic1_idx` (`topic_id` ASC) VISIBLE,
  CONSTRAINT `fk_publication_topic1`
    FOREIGN KEY (`topic_id`)
    REFERENCES `mydb`.`topic` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;
 insert into mydb.publication values(1,"Хазяїн",26.12,"https://peredplata.ukrposhta.ua/catalog/view/theme/peredplata/img/product-skins/89102.png","Поради городникам, садівникам, майстрам, рибалкам…",1);
 insert into mydb.publication values(2,"ЗОЖ",27.29,"https://peredplata.ukrposhta.ua/catalog/view/theme/peredplata/img/product-skins/99736.png","Як правильно доглядати за своїм здоров'ям",2);
 insert into mydb.publication values(3,"Пізнайко",51,"https://peredplata.ukrposhta.ua/catalog/view/theme/peredplata/img/product-skins/40242.png","Для дітей від 6 років: пригоди, подорожі, комікси, кросворди, найкращі літературні твори, ігри та призи. Дитина прагнутиме пізнавати світ!",3);
 insert into mydb.publication values(4,"Зелена планета",13,"/images/green_planet.png","Видання, яке розкриває секрети народної медицини.",2);
  insert into mydb.publication values(5,"1000 рецептів",7.20,"/images/1000.png","Новинка, збірник рецептів для приготування дуже смачних страв. Лише перевірені рецепти читачів",4);
  insert into mydb.publication values(6,"Наша кухня",60.00,"/images/citchen.png","Корисно, просто, смачно. Смакуйте життя з нами!",4);
  insert into mydb.publication values(7,"Малятко",204.48,"/images/malyatko.png","Щомiсячний яскраво ілюстрований журнал, друкує казки, вiршi, пiзнавальнi оповiдки, саморобнi iграшки. Проводить конкурси серед читачів, вiтає їх з днем народження. Вмiщує сторiнку для батькiв",3);
  insert into mydb.publication values(8,"Кузя",12.00,"/images/kyzya.png","Безліч головоломок, пізнавальних історій та іншої цікавої інформації українською мовою для дітей",3);
  insert into mydb.publication values(9,"Burda",49.95,"/images/burda.png","Burda - легендарний журнал про моду, красу, стиль. Щомісяця нова колекція викрійок, готових для пошиву одягу та аксесуарів.",5);
  insert into mydb.publication values(10,"Абетка здоров’я та довголіття",10.25,"/images/abetka.png","Поради щодо здорового способу життя",2);
  insert into mydb.publication values(11,"Пантелеймон цілитель",17.72,"/images/panteleymon.png","Видання, яке розкриває секрети народної медицини.",2);
  insert into mydb.publication values(12,"Жінка",24.08,"/images/woman.png","Історії життя, здоров’я, сім’я, рукоділля, поради",5);
-- -----------------------------------------------------
-- Table `mydb`.`receipt_has_publication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`receipt_has_publication` (
  `receipt_id` INT NOT NULL,
  `publication_id` INT NOT NULL,
  PRIMARY KEY (`receipt_id`, `publication_id`),
  INDEX `fk_receipt_has_publication_publication1_idx` (`publication_id` ASC) VISIBLE,
  INDEX `fk_receipt_has_publication_receipt1_idx` (`receipt_id` ASC) VISIBLE,
  CONSTRAINT `fk_receipt_has_publication_receipt1`
    FOREIGN KEY (`receipt_id`)
    REFERENCES `mydb`.`receipt` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_receipt_has_publication_publication1`
    FOREIGN KEY (`publication_id`)
    REFERENCES `mydb`.`publication` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
