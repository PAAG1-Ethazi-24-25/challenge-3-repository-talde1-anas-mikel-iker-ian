-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-04-2025 a las 15:56:17
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_bigarreneskukomerkatua`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bezeroak`
--

CREATE TABLE `bezeroak` (
  `id_bezero` int(11) NOT NULL,
  `izena` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefonoa` varchar(20) DEFAULT NULL,
  `helbidea` text DEFAULT NULL,
  `herria` varchar(100) DEFAULT NULL,
  `posta_kodea` varchar(10) DEFAULT NULL,
  `alta_data` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `erablitzaile_izena` varchar(255) DEFAULT NULL,
  `pasahitza` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `bezeroak`
--

INSERT INTO `bezeroak` (`id_bezero`, `izena`, `email`, `telefonoa`, `helbidea`, `herria`, `posta_kodea`, `alta_data`, `erablitzaile_izena`, `pasahitza`) VALUES
(2, 'Ana', 'ane@email.com', '600654321', 'San Mames 10', 'Bilbo', '48002', '2025-04-09 10:17:30', 'ane321', 'password2'),
(3, 'Mikel', 'mikel@email.com', '699112233', 'Zabalbide 22', 'Gasteiz', '01001', '2025-04-03 06:41:14', 'mikel789', 'password3'),
(5, 'Iker', 'iker@email.com', '612345678', 'Easo kalea 15', 'Donostia', '20004', '2025-04-03 06:41:14', 'iker001', 'password5'),
(24, 'sg', 'brahim.anas@uni.eus', '45345325424', 'hkiu', 'eda', '5265', '2025-04-10 10:51:44', 'mikelito', 'sas');

--
-- Disparadores `bezeroak`
--
DELIMITER $$
CREATE TRIGGER `delete erabiltzaileak` AFTER DELETE ON `bezeroak` FOR EACH ROW DELETE FROM erabiltzaileak WHERE izena = OLD.izena
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `set_alta_data_bezeroak` BEFORE INSERT ON `bezeroak` FOR EACH ROW BEGIN
    IF NEW.alta_data IS NULL THEN
        SET NEW.alta_data = NOW();
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_InsertBezeroak` AFTER INSERT ON `bezeroak` FOR EACH ROW INSERT INTO Erabiltzaileak (izena, erabiltzailea, pasahitza, administratzailea)
VALUES (NEW.izena, NEW.erablitzaile_izena, NEW.pasahitza, 'ez')
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update erabiltzaileak` AFTER UPDATE ON `bezeroak` FOR EACH ROW UPDATE `erabiltzaileak` SET `izena`=NEW.izena ,`erabiltzailea`= NEW.erablitzaile_izena ,`pasahitza`=NEW.pasahitza WHERE izena = OLD.izena
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `erabiltzaileak`
--

CREATE TABLE `erabiltzaileak` (
  `id_erabiltzaile` int(11) NOT NULL,
  `izena` varchar(255) NOT NULL,
  `erabiltzailea` varchar(255) NOT NULL,
  `pasahitza` varchar(255) NOT NULL,
  `administratzailea` enum('bai','ez') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `erabiltzaileak`
--

INSERT INTO `erabiltzaileak` (`id_erabiltzaile`, `izena`, `erabiltzailea`, `pasahitza`, `administratzailea`) VALUES
(2, 'Ana', 'ane321', 'password2', 'ez'),
(3, 'Mikel', 'mikel789', 'password3', 'ez'),
(5, 'Iker', 'iker001', 'password5', 'ez'),
(8, 'Xabier', 'xabier_admin', 'adminpass1', 'bai'),
(9, 'Maitan', 'maitane_salt', 'adminpass2', 'bai'),
(10, 'Ander', 'ander_salt', 'adminpass3', 'bai'),
(11, 'Leire', 'leire_kud', 'adminpass4', 'bai'),
(12, 'Julen', 'julen_garr', 'adminpass5', 'bai'),
(13, 'Miren', 'miren_kud', 'adminpass6', 'bai'),
(17, 'jose', 'jose_saltz', 'josesaltz123', 'bai'),
(37, 'sg', 'mikelito', 'sas', 'ez');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kategoriak`
--

CREATE TABLE `kategoriak` (
  `id_kategoria` int(11) NOT NULL,
  `izena` varchar(255) NOT NULL,
  `deskribapena` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `kategoriak`
--

INSERT INTO `kategoriak` (`id_kategoria`, `izena`, `deskribapena`) VALUES
(1, 'Elektronika', 'Gailu elektronikoak eta osagarriak'),
(2, 'Altzariak', 'Etxerako altzariak'),
(3, 'Kirola', 'Kirol eta aisialdirako produktuak'),
(4, 'Jostailuak', 'Haurrentzako jostailuak'),
(5, 'Ibilgailuak', 'Autoak, motoak eta osagarriak'),
(6, 'Liburuak', 'Liburuak eta komikiak'),
(7, 'Musika', 'Musika tresnak eta osagarriak');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `langileak`
--

CREATE TABLE `langileak` (
  `id_langile` int(11) NOT NULL,
  `izena` varchar(255) NOT NULL,
  `kargua` enum('administratzailea','saltzailea','kudeatzailea','garraiolaria') NOT NULL,
  `telefonoa` varchar(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `alta_data` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `helbidea` text DEFAULT NULL,
  `herria` varchar(100) DEFAULT NULL,
  `posta_kodea` varchar(10) DEFAULT NULL,
  `erablitzaile_izena` varchar(255) DEFAULT NULL,
  `pasahitza` varchar(255) DEFAULT NULL,
  `argazkia` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `langileak`
--

INSERT INTO `langileak` (`id_langile`, `izena`, `kargua`, `telefonoa`, `email`, `alta_data`, `helbidea`, `herria`, `posta_kodea`, `erablitzaile_izena`, `pasahitza`, `argazkia`) VALUES
(1, 'Xabier', 'administratzailea', '645223344', 'xabier@email.com', '2025-04-10 07:08:14', 'Langile kalea 1', 'Bilbo', '48004', 'xabier_admin', 'adminpass1', '01.jpg'),
(2, 'Maitan', 'saltzailea', '655667788', 'maitane@email.com', '2025-04-10 07:08:52', 'Langile kalea 2', 'Gasteiz', '01003', 'maitane_salt', 'adminpass2', '22.jpg'),
(3, 'Ander', 'saltzailea', '611223344', 'ander@email.com', '2025-04-10 07:08:28', 'Langile kalea 3', 'Donostia', '20005', 'ander_salt', 'adminpass3', '1.jpg'),
(5, 'Julen', 'garraiolaria', '677445566', 'julen@email.com', '2025-04-10 07:10:23', 'Langile kalea 5', 'Donostia', '20006', 'julen_garr', 'adminpass5', '3.jpg'),
(6, 'Miren', 'kudeatzailea', '699887766', 'miren@email.com', '2025-04-10 07:11:16', 'Langile kalea 6', 'Gasteiz', '01004', 'miren_kud', 'adminpass6', '4.jpg'),
(8, 'jose', 'saltzailea', '346725046', 'josa@gmail.com', '2025-04-08 16:50:55', 'zumalakarregi kalea', 'donostia', '4356', 'jose_saltz', 'josesaltz123', NULL);

--
-- Disparadores `langileak`
--
DELIMITER $$
CREATE TRIGGER `deletete_erabiltzaileak` BEFORE DELETE ON `langileak` FOR EACH ROW DELETE FROM erabiltzaileak WHERE izena = OLD.izena
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `set_alta_data_langileak` BEFORE INSERT ON `langileak` FOR EACH ROW BEGIN
    IF NEW.alta_data IS NULL THEN
        SET NEW.alta_data = NOW();
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_InsertLangileak` AFTER INSERT ON `langileak` FOR EACH ROW INSERT INTO Erabiltzaileak (izena, erabiltzailea, pasahitza, administratzailea)
VALUES (NEW.izena, NEW.erablitzaile_izena, NEW.pasahitza, 'bai')
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_erabiltzaileak` BEFORE UPDATE ON `langileak` FOR EACH ROW UPDATE `erabiltzaileak` SET `izena`=NEW.izena ,`erabiltzailea`= NEW.erablitzaile_izena ,`pasahitza`=NEW.pasahitza WHERE izena = OLD.izena
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `produktuak`
--

CREATE TABLE `produktuak` (
  `id_produktu` int(11) NOT NULL,
  `izena` varchar(255) NOT NULL,
  `deskribapena` text DEFAULT NULL,
  `prezioa` decimal(10,2) NOT NULL,
  `id_kategoria` int(11) DEFAULT NULL,
  `egoera` enum('berria','erabilia','hondatua') NOT NULL,
  `id_saltzaile` int(11) NOT NULL,
  `salduta` tinyint(1) DEFAULT NULL,
  `argazkia` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `produktuak`
--

INSERT INTO `produktuak` (`id_produktu`, `izena`, `deskribapena`, `prezioa`, `id_kategoria`, `egoera`, `id_saltzaile`, `salduta`, `argazkia`) VALUES
(2, 'Sofa bikoitza', 'Kolore grisa, material erosoa', 300.00, 2, 'erabilia', 2, 1, 'sofa.webp'),
(3, 'Bizikleta', 'Mendiko bizikleta 26 hazbete', 500.00, 3, 'erabilia', 3, 1, 'descarga.jpg'),
(5, 'Kotxea - BMW X5', 'Diesel, 2015eko modeloa', 25000.00, 5, 'erabilia', 5, 1, 'bmw.jpg'),
(8, 'playstation 4', 'playstation 4 berria 3 mandoekin', 120.00, 1, 'berria', 3, 1, 'play.jpg'),
(9, 'ordenagailu portatila', 'Ordengailu erabilia', 110.00, 1, 'erabilia', 2, 0, 'portatil.jpg'),
(10, 'ordenagailu dorrea', 'Ordengailu erabilia', 50.00, 1, 'erabilia', 2, 1, NULL);

--
-- Disparadores `produktuak`
--
DELIMITER $$
CREATE TRIGGER `after_product_sold` AFTER UPDATE ON `produktuak` FOR EACH ROW BEGIN
    
    IF NEW.salduta = TRUE AND OLD.salduta = FALSE THEN
        
        INSERT INTO Salmentak (id_produktu, id_saltzaile, id_erosle, data, salmenta_prezioa)
        VALUES (NEW.id_produktu, NEW.id_saltzaile, NULL, NOW(), NEW.prezioa);
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salmentak`
--

CREATE TABLE `salmentak` (
  `id_salmenta` int(11) NOT NULL,
  `id_produktu` int(11) NOT NULL,
  `id_saltzaile` int(11) DEFAULT NULL,
  `id_erosle` int(11) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  `salmenta_prezioa` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `salmentak`
--

INSERT INTO `salmentak` (`id_salmenta`, `id_produktu`, `id_saltzaile`, `id_erosle`, `data`, `salmenta_prezioa`) VALUES
(2, 3, 3, 5, '2025-04-03 08:41:52', 500.00),
(3, 5, 5, 2, '2025-04-03 08:41:52', 25000.00),
(15, 10, 2, 3, '2025-04-10 09:27:01', 50.00);

--
-- Disparadores `salmentak`
--
DELIMITER $$
CREATE TRIGGER `set_default_prezioa_salmentan` BEFORE INSERT ON `salmentak` FOR EACH ROW BEGIN
    IF NEW.salmenta_prezioa IS NULL OR NEW.salmenta_prezioa = '' THEN
        SET NEW.salmenta_prezioa = (
            SELECT prezioa FROM produktuak WHERE id_produktu = NEW.id_produktu
        );
    END IF;
END
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bezeroak`
--
ALTER TABLE `bezeroak`
  ADD PRIMARY KEY (`id_bezero`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `erbiltzaile_unikoa` (`erablitzaile_izena`);

--
-- Indices de la tabla `erabiltzaileak`
--
ALTER TABLE `erabiltzaileak`
  ADD PRIMARY KEY (`id_erabiltzaile`),
  ADD UNIQUE KEY `erabiltzailea` (`erabiltzailea`);

--
-- Indices de la tabla `kategoriak`
--
ALTER TABLE `kategoriak`
  ADD PRIMARY KEY (`id_kategoria`);

--
-- Indices de la tabla `langileak`
--
ALTER TABLE `langileak`
  ADD PRIMARY KEY (`id_langile`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `produktuak`
--
ALTER TABLE `produktuak`
  ADD PRIMARY KEY (`id_produktu`),
  ADD KEY `id_kategoria` (`id_kategoria`),
  ADD KEY `id_saltzaile` (`id_saltzaile`);

--
-- Indices de la tabla `salmentak`
--
ALTER TABLE `salmentak`
  ADD PRIMARY KEY (`id_salmenta`),
  ADD UNIQUE KEY `unico_producto` (`id_produktu`),
  ADD KEY `id_saltzaile` (`id_saltzaile`),
  ADD KEY `id_erosle` (`id_erosle`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `bezeroak`
--
ALTER TABLE `bezeroak`
  MODIFY `id_bezero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `erabiltzaileak`
--
ALTER TABLE `erabiltzaileak`
  MODIFY `id_erabiltzaile` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `kategoriak`
--
ALTER TABLE `kategoriak`
  MODIFY `id_kategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `langileak`
--
ALTER TABLE `langileak`
  MODIFY `id_langile` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `produktuak`
--
ALTER TABLE `produktuak`
  MODIFY `id_produktu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `salmentak`
--
ALTER TABLE `salmentak`
  MODIFY `id_salmenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `produktuak`
--
ALTER TABLE `produktuak`
  ADD CONSTRAINT `produktuak_ibfk_1` FOREIGN KEY (`id_kategoria`) REFERENCES `kategoriak` (`id_kategoria`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `produktuak_ibfk_2` FOREIGN KEY (`id_saltzaile`) REFERENCES `bezeroak` (`id_bezero`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `salmentak`
--
ALTER TABLE `salmentak`
  ADD CONSTRAINT `salmentak_ibfk_1` FOREIGN KEY (`id_produktu`) REFERENCES `produktuak` (`id_produktu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `salmentak_ibfk_2` FOREIGN KEY (`id_saltzaile`) REFERENCES `bezeroak` (`id_bezero`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `salmentak_ibfk_3` FOREIGN KEY (`id_erosle`) REFERENCES `bezeroak` (`id_bezero`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
