-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.34-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema finantial
--

CREATE DATABASE IF NOT EXISTS finantial;
USE finantial;

--
-- Definition of table `finantial_month`
--

DROP TABLE IF EXISTS `finantial_month`;
CREATE TABLE `finantial_month` (
  `DATE` date NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `FINANTIALYEAR_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DATE`),
  KEY `FK_finantial_month_FINANTIALYEAR_ID` (`FINANTIALYEAR_ID`),
  CONSTRAINT `FK_finantial_month_FINANTIALYEAR_ID` FOREIGN KEY (`FINANTIALYEAR_ID`) REFERENCES `finantial_year` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `finantial_month`
--

/*!40000 ALTER TABLE `finantial_month` DISABLE KEYS */;
INSERT INTO `finantial_month` (`DATE`,`TITLE`,`FINANTIALYEAR_ID`) VALUES 
 ('2010-01-01','tabyear.label.jan',251),
 ('2010-02-01','tabyear.label.fev',251),
 ('2010-03-01','tabyear.label.mar',251),
 ('2010-04-01','tabyear.label.abr',251),
 ('2010-05-01','tabyear.label.mai',251),
 ('2010-06-01','tabyear.label.jun',251),
 ('2010-07-01','tabyear.label.jul',251),
 ('2010-08-01','tabyear.label.ago',251),
 ('2010-09-01','tabyear.label.set',251),
 ('2010-10-01','tabyear.label.out',251),
 ('2010-11-01','tabyear.label.nov',251),
 ('2010-12-01','tabyear.label.dez',251),
 ('2011-01-01','tabyear.label.jan',252),
 ('2011-02-01','tabyear.label.fev',252),
 ('2011-03-01','tabyear.label.mar',252),
 ('2011-04-01','tabyear.label.abr',252),
 ('2011-05-01','tabyear.label.mai',252),
 ('2011-06-01','tabyear.label.jun',252),
 ('2011-07-01','tabyear.label.jul',252),
 ('2011-08-01','tabyear.label.ago',252),
 ('2011-09-01','tabyear.label.set',252),
 ('2011-10-01','tabyear.label.out',252),
 ('2011-11-01','tabyear.label.nov',252),
 ('2011-12-01','tabyear.label.dez',252),
 ('2012-01-01','tabyear.label.jan',253),
 ('2012-02-01','tabyear.label.fev',253),
 ('2012-03-01','tabyear.label.mar',253),
 ('2012-04-01','tabyear.label.abr',253),
 ('2012-05-01','tabyear.label.mai',253),
 ('2012-06-01','tabyear.label.jun',253),
 ('2012-07-01','tabyear.label.jul',253),
 ('2012-08-01','tabyear.label.ago',253),
 ('2012-09-01','tabyear.label.set',253),
 ('2012-10-01','tabyear.label.out',253),
 ('2012-11-01','tabyear.label.nov',253),
 ('2012-12-01','tabyear.label.dez',253),
 ('2013-01-01','tabyear.label.jan',254),
 ('2013-02-01','tabyear.label.fev',254),
 ('2013-03-01','tabyear.label.mar',254),
 ('2013-04-01','tabyear.label.abr',254),
 ('2013-05-01','tabyear.label.mai',254),
 ('2013-06-01','tabyear.label.jun',254),
 ('2013-07-01','tabyear.label.jul',254),
 ('2013-08-01','tabyear.label.ago',254),
 ('2013-09-01','tabyear.label.set',254),
 ('2013-10-01','tabyear.label.out',254),
 ('2013-11-01','tabyear.label.nov',254),
 ('2013-12-01','tabyear.label.dez',254),
 ('2014-01-01','tabyear.label.jan',255),
 ('2014-02-01','tabyear.label.fev',255),
 ('2014-03-01','tabyear.label.mar',255),
 ('2014-04-01','tabyear.label.abr',255),
 ('2014-05-01','tabyear.label.mai',255),
 ('2014-06-01','tabyear.label.jun',255),
 ('2014-07-01','tabyear.label.jul',255),
 ('2014-08-01','tabyear.label.ago',255),
 ('2014-09-01','tabyear.label.set',255),
 ('2014-10-01','tabyear.label.out',255),
 ('2014-11-01','tabyear.label.nov',255),
 ('2014-12-01','tabyear.label.dez',255),
 ('2015-01-01','tabyear.label.jan',256),
 ('2015-02-01','tabyear.label.fev',256),
 ('2015-03-01','tabyear.label.mar',256),
 ('2015-04-01','tabyear.label.abr',256),
 ('2015-05-01','tabyear.label.mai',256),
 ('2015-06-01','tabyear.label.jun',256),
 ('2015-07-01','tabyear.label.jul',256),
 ('2015-08-01','tabyear.label.ago',256),
 ('2015-09-01','tabyear.label.set',256),
 ('2015-10-01','tabyear.label.out',256),
 ('2015-11-01','tabyear.label.nov',256),
 ('2015-12-01','tabyear.label.dez',256),
 ('2016-01-01','tabyear.label.jan',257),
 ('2016-02-01','tabyear.label.fev',257),
 ('2016-03-01','tabyear.label.mar',257),
 ('2016-04-01','tabyear.label.abr',257),
 ('2016-05-01','tabyear.label.mai',257),
 ('2016-06-01','tabyear.label.jun',257),
 ('2016-07-01','tabyear.label.jul',257),
 ('2016-08-01','tabyear.label.ago',257),
 ('2016-09-01','tabyear.label.set',257),
 ('2016-10-01','tabyear.label.out',257),
 ('2016-11-01','tabyear.label.nov',257),
 ('2016-12-01','tabyear.label.dez',257),
 ('2017-01-01','tabyear.label.jan',258),
 ('2017-02-01','tabyear.label.fev',258),
 ('2017-03-01','tabyear.label.mar',258),
 ('2017-04-01','tabyear.label.abr',258),
 ('2017-05-01','tabyear.label.mai',258),
 ('2017-06-01','tabyear.label.jun',258),
 ('2017-07-01','tabyear.label.jul',258),
 ('2017-08-01','tabyear.label.ago',258),
 ('2017-09-01','tabyear.label.set',258),
 ('2017-10-01','tabyear.label.out',258),
 ('2017-11-01','tabyear.label.nov',258),
 ('2017-12-01','tabyear.label.dez',258),
 ('2018-01-01','tabyear.label.jan',259),
 ('2018-02-01','tabyear.label.fev',259),
 ('2018-03-01','tabyear.label.mar',259),
 ('2018-04-01','tabyear.label.abr',259),
 ('2018-05-01','tabyear.label.mai',259),
 ('2018-06-01','tabyear.label.jun',259),
 ('2018-07-01','tabyear.label.jul',259),
 ('2018-08-01','tabyear.label.ago',259),
 ('2018-09-01','tabyear.label.set',259),
 ('2018-10-01','tabyear.label.out',259),
 ('2018-11-01','tabyear.label.nov',259),
 ('2018-12-01','tabyear.label.dez',259),
 ('2019-01-01','tabyear.label.jan',260),
 ('2019-02-01','tabyear.label.fev',260),
 ('2019-03-01','tabyear.label.mar',260),
 ('2019-04-01','tabyear.label.abr',260),
 ('2019-05-01','tabyear.label.mai',260),
 ('2019-06-01','tabyear.label.jun',260),
 ('2019-07-01','tabyear.label.jul',260),
 ('2019-08-01','tabyear.label.ago',260),
 ('2019-09-01','tabyear.label.set',260),
 ('2019-10-01','tabyear.label.out',260),
 ('2019-11-01','tabyear.label.nov',260),
 ('2019-12-01','tabyear.label.dez',260),
 ('2020-01-01','tabyear.label.jan',261),
 ('2020-02-01','tabyear.label.fev',261),
 ('2020-03-01','tabyear.label.mar',261),
 ('2020-04-01','tabyear.label.abr',261),
 ('2020-05-01','tabyear.label.mai',261),
 ('2020-06-01','tabyear.label.jun',261),
 ('2020-07-01','tabyear.label.jul',261),
 ('2020-08-01','tabyear.label.ago',261),
 ('2020-09-01','tabyear.label.set',261),
 ('2020-10-01','tabyear.label.out',261),
 ('2020-11-01','tabyear.label.nov',261),
 ('2020-12-01','tabyear.label.dez',261),
 ('2021-01-01','tabyear.label.jan',262),
 ('2021-02-01','tabyear.label.fev',262),
 ('2021-03-01','tabyear.label.mar',262),
 ('2021-04-01','tabyear.label.abr',262),
 ('2021-05-01','tabyear.label.mai',262),
 ('2021-06-01','tabyear.label.jun',262),
 ('2021-07-01','tabyear.label.jul',262),
 ('2021-08-01','tabyear.label.ago',262),
 ('2021-09-01','tabyear.label.set',262),
 ('2021-10-01','tabyear.label.out',262),
 ('2021-11-01','tabyear.label.nov',262),
 ('2021-12-01','tabyear.label.dez',262),
 ('2022-01-01','tabyear.label.jan',263),
 ('2022-02-01','tabyear.label.fev',263),
 ('2022-03-01','tabyear.label.mar',263),
 ('2022-04-01','tabyear.label.abr',263),
 ('2022-05-01','tabyear.label.mai',263),
 ('2022-06-01','tabyear.label.jun',263),
 ('2022-07-01','tabyear.label.jul',263),
 ('2022-08-01','tabyear.label.ago',263),
 ('2022-09-01','tabyear.label.set',263),
 ('2022-10-01','tabyear.label.out',263),
 ('2022-11-01','tabyear.label.nov',263),
 ('2022-12-01','tabyear.label.dez',263),
 ('2023-01-01','tabyear.label.jan',264),
 ('2023-02-01','tabyear.label.fev',264),
 ('2023-03-01','tabyear.label.mar',264),
 ('2023-04-01','tabyear.label.abr',264),
 ('2023-05-01','tabyear.label.mai',264),
 ('2023-06-01','tabyear.label.jun',264),
 ('2023-07-01','tabyear.label.jul',264),
 ('2023-08-01','tabyear.label.ago',264),
 ('2023-09-01','tabyear.label.set',264),
 ('2023-10-01','tabyear.label.out',264),
 ('2023-11-01','tabyear.label.nov',264),
 ('2023-12-01','tabyear.label.dez',264),
 ('2024-01-01','tabyear.label.jan',265),
 ('2024-02-01','tabyear.label.fev',265),
 ('2024-03-01','tabyear.label.mar',265),
 ('2024-04-01','tabyear.label.abr',265),
 ('2024-05-01','tabyear.label.mai',265),
 ('2024-06-01','tabyear.label.jun',265),
 ('2024-07-01','tabyear.label.jul',265),
 ('2024-08-01','tabyear.label.ago',265),
 ('2024-09-01','tabyear.label.set',265),
 ('2024-10-01','tabyear.label.out',265),
 ('2024-11-01','tabyear.label.nov',265),
 ('2024-12-01','tabyear.label.dez',265),
 ('2025-01-01','tabyear.label.jan',266),
 ('2025-02-01','tabyear.label.fev',266),
 ('2025-03-01','tabyear.label.mar',266),
 ('2025-04-01','tabyear.label.abr',266),
 ('2025-05-01','tabyear.label.mai',266),
 ('2025-06-01','tabyear.label.jun',266),
 ('2025-07-01','tabyear.label.jul',266),
 ('2025-08-01','tabyear.label.ago',266),
 ('2025-09-01','tabyear.label.set',266),
 ('2025-10-01','tabyear.label.out',266),
 ('2025-11-01','tabyear.label.nov',266),
 ('2025-12-01','tabyear.label.dez',266),
 ('2026-01-01','tabyear.label.jan',267),
 ('2026-02-01','tabyear.label.fev',267),
 ('2026-03-01','tabyear.label.mar',267),
 ('2026-04-01','tabyear.label.abr',267),
 ('2026-05-01','tabyear.label.mai',267),
 ('2026-06-01','tabyear.label.jun',267),
 ('2026-07-01','tabyear.label.jul',267),
 ('2026-08-01','tabyear.label.ago',267),
 ('2026-09-01','tabyear.label.set',267),
 ('2026-10-01','tabyear.label.out',267),
 ('2026-11-01','tabyear.label.nov',267),
 ('2026-12-01','tabyear.label.dez',267),
 ('2027-01-01','tabyear.label.jan',268),
 ('2027-02-01','tabyear.label.fev',268),
 ('2027-03-01','tabyear.label.mar',268),
 ('2027-04-01','tabyear.label.abr',268),
 ('2027-05-01','tabyear.label.mai',268),
 ('2027-06-01','tabyear.label.jun',268),
 ('2027-07-01','tabyear.label.jul',268),
 ('2027-08-01','tabyear.label.ago',268),
 ('2027-09-01','tabyear.label.set',268),
 ('2027-10-01','tabyear.label.out',268),
 ('2027-11-01','tabyear.label.nov',268),
 ('2027-12-01','tabyear.label.dez',268),
 ('2028-01-01','tabyear.label.jan',269),
 ('2028-02-01','tabyear.label.fev',269),
 ('2028-03-01','tabyear.label.mar',269),
 ('2028-04-01','tabyear.label.abr',269),
 ('2028-05-01','tabyear.label.mai',269),
 ('2028-06-01','tabyear.label.jun',269),
 ('2028-07-01','tabyear.label.jul',269),
 ('2028-08-01','tabyear.label.ago',269),
 ('2028-09-01','tabyear.label.set',269),
 ('2028-10-01','tabyear.label.out',269),
 ('2028-11-01','tabyear.label.nov',269),
 ('2028-12-01','tabyear.label.dez',269),
 ('2029-01-01','tabyear.label.jan',270),
 ('2029-02-01','tabyear.label.fev',270),
 ('2029-03-01','tabyear.label.mar',270),
 ('2029-04-01','tabyear.label.abr',270),
 ('2029-05-01','tabyear.label.mai',270),
 ('2029-06-01','tabyear.label.jun',270),
 ('2029-07-01','tabyear.label.jul',270),
 ('2029-08-01','tabyear.label.ago',270),
 ('2029-09-01','tabyear.label.set',270),
 ('2029-10-01','tabyear.label.out',270),
 ('2029-11-01','tabyear.label.nov',270),
 ('2029-12-01','tabyear.label.dez',270),
 ('2030-01-01','tabyear.label.jan',271),
 ('2030-02-01','tabyear.label.fev',271),
 ('2030-03-01','tabyear.label.mar',271),
 ('2030-04-01','tabyear.label.abr',271),
 ('2030-05-01','tabyear.label.mai',271),
 ('2030-06-01','tabyear.label.jun',271),
 ('2030-07-01','tabyear.label.jul',271),
 ('2030-08-01','tabyear.label.ago',271),
 ('2030-09-01','tabyear.label.set',271),
 ('2030-10-01','tabyear.label.out',271),
 ('2030-11-01','tabyear.label.nov',271),
 ('2030-12-01','tabyear.label.dez',271);
/*!40000 ALTER TABLE `finantial_month` ENABLE KEYS */;


--
-- Definition of table `finantial_year`
--

DROP TABLE IF EXISTS `finantial_year`;
CREATE TABLE `finantial_year` (
  `ID` bigint(20) NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `finantial_year`
--

/*!40000 ALTER TABLE `finantial_year` DISABLE KEYS */;
INSERT INTO `finantial_year` (`ID`,`TITLE`) VALUES 
 (251,'2010'),
 (252,'2011'),
 (253,'2012'),
 (254,'2013'),
 (255,'2014'),
 (256,'2015'),
 (257,'2016'),
 (258,'2017'),
 (259,'2018'),
 (260,'2019'),
 (261,'2020'),
 (262,'2021'),
 (263,'2022'),
 (264,'2023'),
 (265,'2024'),
 (266,'2025'),
 (267,'2026'),
 (268,'2027'),
 (269,'2028'),
 (270,'2029'),
 (271,'2030');
/*!40000 ALTER TABLE `finantial_year` ENABLE KEYS */;


--
-- Definition of table `income`
--

DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `VALUE` double DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `FINANTIALMONTH_DATE` date DEFAULT NULL,
  `TYPE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_income_USER_ID` (`USER_ID`),
  KEY `FK_income_TYPE_ID` (`TYPE_ID`),
  KEY `FK_income_FINANTIALMONTH_DATE` (`FINANTIALMONTH_DATE`),
  CONSTRAINT `FK_income_FINANTIALMONTH_DATE` FOREIGN KEY (`FINANTIALMONTH_DATE`) REFERENCES `finantial_month` (`DATE`),
  CONSTRAINT `FK_income_TYPE_ID` FOREIGN KEY (`TYPE_ID`) REFERENCES `income_type` (`ID`),
  CONSTRAINT `FK_income_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `income`
--

/*!40000 ALTER TABLE `income` DISABLE KEYS */;
/*!40000 ALTER TABLE `income` ENABLE KEYS */;


--
-- Definition of table `income_type`
--

DROP TABLE IF EXISTS `income_type`;
CREATE TABLE `income_type` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `income_type`
--

/*!40000 ALTER TABLE `income_type` DISABLE KEYS */;
INSERT INTO `income_type` (`ID`,`DESCRIPTION`) VALUES 
 (1,'salario'),
 (2,'mesada'),
 (3,'vale-refeicao'),
 (4,'vale-alimentacao'),
 (5,'vale-transporte');
/*!40000 ALTER TABLE `income_type` ENABLE KEYS */;


--
-- Definition of table `outcome`
--

DROP TABLE IF EXISTS `outcome`;
CREATE TABLE `outcome` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `VALUE` double DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `FINANTIALMONTH_DATE` date DEFAULT NULL,
  `TYPE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_outcome_FINANTIALMONTH_DATE` (`FINANTIALMONTH_DATE`),
  KEY `FK_outcome_USER_ID` (`USER_ID`),
  KEY `FK_outcome_TYPE_ID` (`TYPE_ID`),
  CONSTRAINT `FK_outcome_FINANTIALMONTH_DATE` FOREIGN KEY (`FINANTIALMONTH_DATE`) REFERENCES `finantial_month` (`DATE`),
  CONSTRAINT `FK_outcome_TYPE_ID` FOREIGN KEY (`TYPE_ID`) REFERENCES `outcome_type` (`ID`),
  CONSTRAINT `FK_outcome_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `outcome`
--

/*!40000 ALTER TABLE `outcome` DISABLE KEYS */;
/*!40000 ALTER TABLE `outcome` ENABLE KEYS */;


--
-- Definition of table `outcome_type`
--

DROP TABLE IF EXISTS `outcome_type`;
CREATE TABLE `outcome_type` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `outcome_type`
--

/*!40000 ALTER TABLE `outcome_type` DISABLE KEYS */;
INSERT INTO `outcome_type` (`ID`,`DESCRIPTION`) VALUES 
 (1,'Supermercado'),
 (2,'Luz'),
 (3,'Aluguel'),
 (4,'Telefone'),
 (5,'Faculdade'),
 (6,'Academia'),
 (7,'Roupa'),
 (8,'Eletrodomésticos'),
 (9,'Lazer');
/*!40000 ALTER TABLE `outcome_type` ENABLE KEYS */;


--
-- Definition of table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sequence`
--

/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` (`SEQ_NAME`,`SEQ_COUNT`) VALUES 
 ('SEQ_GEN','4900');
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `FULLNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`ID`,`USERNAME`,`PASSWORD`,`FULLNAME`) VALUES 
 (0,'admin','admin','Administrator'),
 (1,'rmpestano','rmp','Rafael M. Pestano');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
