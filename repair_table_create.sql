CREATE TABLE IF NOT EXISTS `landfill-data`.`IMEInspection` (
  `IMEInspectionPK` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `InspectionDate` DATETIME NULL COMMENT '',
  `Description` VARCHAR(45) NULL COMMENT '',
  `IMEShape` VARCHAR(45) NULL COMMENT '',
  `EmployeePK` INT NOT NULL COMMENT '',
  `Value` VARCHAR(45) NULL COMMENT '',
  `IMEPK` INT NULL COMMENT '',
  PRIMARY KEY (`IMEInspectionPK`, `EmployeePK`)  COMMENT '',
  INDEX `IMEInspectionEmployeePK_idx` (`EmployeePK` ASC)  COMMENT '',
  INDEX `IMEInspectionIMEPK_idx` (`IMEPK` ASC)  COMMENT '',
  CONSTRAINT `IMEInspectionEmployeePK`
    FOREIGN KEY (`EmployeePK`)
    REFERENCES `landfill-data`.`Employee` (`EmployeePK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `IMEInspectionIMEPK`
    FOREIGN KEY (`IMEPK`)
    REFERENCES `landfill-data`.`IME` (`IMEPK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `landfill-data`.`IMERepair` (
  `IMERepairPK` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Type` VARCHAR(45) NULL COMMENT '',
  `Description` VARCHAR(300) NULL COMMENT '',
  `Crew` VARCHAR(45) NULL COMMENT '',
  `Datetime` VARCHAR(45) NULL COMMENT '',
  `EnteringEmployee` int(11) NULL COMMENT '',
  `IMEInspectionPK` INT NULL COMMENT '',
  PRIMARY KEY (`IMERepairPK`)  COMMENT '',
  INDEX `repair_EnteringEmployee_idx` (`EnteringEmployee` ASC)  COMMENT '',
  INDEX `repair_IMEInspectionPK_idx` (`IMEInspectionPK` ASC)  COMMENT '',
  CONSTRAINT `repair_EnteringEmployee`
    FOREIGN KEY (`EnteringEmployee`)
    REFERENCES `landfill-data`.`Employee` (`EmployeePK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `repair_IMEInspectionPK`
    FOREIGN KEY (`IMEInspectionPK`)
    REFERENCES `landfill-data`.`IMEInspection` (`IMEInspectionPK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



ALTER TABLE IME ADD INDEX (ImeNumber);
