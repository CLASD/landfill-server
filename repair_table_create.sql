CREATE TABLE `IMEInspection` (
  `IMEInspectionPK` int(11) NOT NULL AUTO_INCREMENT,
  `InspectionDate` datetime DEFAULT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `IMEShape` varchar(45) DEFAULT NULL,
  `EmployeePK` int(11) NOT NULL,
  `Value` varchar(45) DEFAULT NULL,
  `IMEPK` int(11) DEFAULT NULL,
  PRIMARY KEY (`IMEInspectionPK`,`EmployeePK`),
  KEY `IMEInspectionEmployeePK_idx` (`EmployeePK`),
  KEY `IMEInspectionIMEPK_idx` (`IMEPK`),
  CONSTRAINT `IMEInspectionEmployeePK` FOREIGN KEY (`EmployeePK`) REFERENCES `Employee` (`EmployeePK`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `IMEInspectionIMEPK` FOREIGN KEY (`IMEPK`) REFERENCES `IME` (`IMEPK`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



CREATE TABLE `IMERepair` (
  `IMERepairPK` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) DEFAULT NULL,
  `Description` varchar(300) DEFAULT NULL,
  `Crew` varchar(45) DEFAULT NULL,
  `Datetime` timestamp NULL DEFAULT NULL,
  `EnteringEmployee` int(11) DEFAULT NULL,
  `IMEInspectionPK` int(11) DEFAULT NULL,
  PRIMARY KEY (`IMERepairPK`),
  KEY `repair_EnteringEmployee_idx` (`EnteringEmployee`),
  KEY `repair_IMEInspectionPK_idx` (`IMEInspectionPK`),
  CONSTRAINT `repair_EnteringEmployee` FOREIGN KEY (`EnteringEmployee`) REFERENCES `Employee` (`EmployeePK`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `repair_IMEInspectionPK` FOREIGN KEY (`IMEInspectionPK`) REFERENCES `IMEInspection` (`IMEInspectionPK`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;



ALTER TABLE IME ADD INDEX (ImeNumber);
