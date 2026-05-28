CREATE DATABASE  IF NOT EXISTS `servicio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `servicio`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: servicio
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad` (
  `act_id` int NOT NULL AUTO_INCREMENT,
  `act_fecha_inicio` date DEFAULT NULL,
  `act_fecha_fin` date DEFAULT NULL,
  `act_descripcion` varchar(255) DEFAULT NULL,
  `act_estado` varchar(255) DEFAULT NULL,
  `act_horas` int DEFAULT NULL,
  `cat_num` varchar(255) DEFAULT NULL,
  `alu_matricula` char(4) DEFAULT NULL,
  `mae_id` char(4) DEFAULT NULL,
  `act_comentario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`act_id`),
  KEY `fk_mae_id_idx` (`mae_id`),
  KEY `alu_matricula_idx` (`alu_matricula`),
  KEY `cat_num_idx` (`cat_num`),
  CONSTRAINT `alu_matricula` FOREIGN KEY (`alu_matricula`) REFERENCES `alumno` (`alu_matricula`),
  CONSTRAINT `cat_num` FOREIGN KEY (`cat_num`) REFERENCES `categoria` (`cat_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_mae_id` FOREIGN KEY (`mae_id`) REFERENCES `maestro` (`mae_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad`
--

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
INSERT INTO `actividad` VALUES (1,'2026-05-18','2026-05-19','Reinstalar windows 11 a laptop','Pendiente',2,NULL,'4230',NULL,NULL),(2,'2026-05-18','2026-05-19','Cambiar cable de CAT5 a CAT6','Pendiente',2,'RED','4230',NULL,NULL),(4,'2026-05-19','2026-05-19','ponchar cable cat6','Pendiente',1,'RED','4230',NULL,NULL),(6,'2026-01-01','2026-01-01','prueba trigger','Pendiente',1,'MANT','4230',NULL,NULL),(7,'2026-05-18','2026-05-19','conectar cables a switch','Pendiente',2,'RED','4230',NULL,NULL),(8,'2020-01-01','2020-01-01','prueba 27/05/2026','Pendiente',1,'BD','4230',NULL,NULL),(9,'2026-05-28','2026-05-28','Reparación de computadora','Pendiente',1,'MANT','4261',NULL,NULL);
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `trg_validar_horas` BEFORE INSERT ON `actividad` FOR EACH ROW BEGIN

    IF NEW.act_horas <= 0 THEN
        SET NEW.act_horas = 1;
    END IF;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumno` (
  `alu_matricula` char(4) NOT NULL,
  `alu_login` varchar(255) DEFAULT NULL,
  `alu_contrasena` varchar(255) DEFAULT NULL,
  `alu_nombre` varchar(255) DEFAULT NULL,
  `alu_apellido` varchar(255) DEFAULT NULL,
  `mae_id` char(4) DEFAULT NULL,
  PRIMARY KEY (`alu_matricula`),
  KEY `mae_id_idx` (`mae_id`),
  CONSTRAINT `mae_id` FOREIGN KEY (`mae_id`) REFERENCES `maestro` (`mae_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES ('4230','alumno1','$2a$10$H/6FPsxr9dXM.g.5zWjI9e9AOZFy0d9ggnu/n1tcCFR3OfFSqDcem','Ricardo','Vazquez','1001'),('4261','4261','$2a$10$lVzUkoojpY6ARmsUrUQf4uHJs8f1pMh9l8Tf2TOeM97tFmLEGGX9q','Armando','Lopez','1001');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `cat_num` varchar(255) NOT NULL,
  `cat_nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cat_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES ('BD','Base de Datos'),('MANT','Mantenimiento'),('RED','Redes'),('WEB','Desarrollo Web');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evidencia`
--

DROP TABLE IF EXISTS `evidencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evidencia` (
  `evi_id` int NOT NULL AUTO_INCREMENT,
  `evi_nombre` varchar(255) DEFAULT NULL,
  `act_id` int DEFAULT NULL,
  `evidenciacol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`evi_id`),
  KEY `fk_evidencia_actividad` (`act_id`),
  CONSTRAINT `fk_evidencia_actividad` FOREIGN KEY (`act_id`) REFERENCES `actividad` (`act_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evidencia`
--

LOCK TABLES `evidencia` WRITE;
/*!40000 ALTER TABLE `evidencia` DISABLE KEYS */;
INSERT INTO `evidencia` VALUES (1,'evidencia1.jpg',7,NULL),(2,'rico.jpg',1,NULL),(3,'evidencia1.jpg',1,NULL),(4,'descarga.jpg',9,NULL),(5,'mantenimiento-de-pc-scaled.jpg',9,NULL),(6,'mantenimiento-de-pc-scaled.png',9,NULL),(7,'evidencia1.jpg',9,NULL);
/*!40000 ALTER TABLE `evidencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maestro`
--

DROP TABLE IF EXISTS `maestro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maestro` (
  `mae_id` char(4) NOT NULL,
  `mae_nombre` varchar(255) DEFAULT NULL,
  `mae_apellido` varchar(255) DEFAULT NULL,
  `mae_login` varchar(255) DEFAULT NULL,
  `mae_contrasena` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mae_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maestro`
--

LOCK TABLES `maestro` WRITE;
/*!40000 ALTER TABLE `maestro` DISABLE KEYS */;
INSERT INTO `maestro` VALUES ('1001','Alejandro','Ulises','maestro1','$2a$10$IDZLd2cCTAmkI4ddsHzmTuqQPpSBJPEcVlk.xKOyybDj6yqDFKgNi');
/*!40000 ALTER TABLE `maestro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'servicio'
--
/*!50003 DROP PROCEDURE IF EXISTS `total_horas_alumno` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `total_horas_alumno`(
    IN p_matricula VARCHAR(20)
)
BEGIN

    SELECT alu_matricula, SUM(act_horas) AS total_horas
    FROM actividad
    WHERE alu_matricula = p_matricula
    GROUP BY alu_matricula;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-28 11:21:14
