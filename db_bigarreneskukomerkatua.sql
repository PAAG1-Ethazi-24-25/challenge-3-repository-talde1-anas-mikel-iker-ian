-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-04-2025 a las 08:43:05
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
(1, 'Jon', 'jon@email.com', '600123456', 'Kale Nagusia 5', 'Bilbo', '48001', '2025-04-03 06:41:14', 'jon123', 'password1'),
(2, 'Ane', 'ane@email.com', '600654321', 'San Mames 10', 'Bilbo', '48002', '2025-04-03 06:41:14', 'ane321', 'password2'),
(3, 'Mikel', 'mikel@email.com', '699112233', 'Zabalbide 22', 'Gasteiz', '01001', '2025-04-03 06:41:14', 'mikel789', 'password3'),
(4, 'Maite', 'maite@email.com', '688334455', 'Donostia Hiribidea 7', 'Donostia', '20003', '2025-04-03 06:41:14', 'maite777', 'password4'),
(5, 'Iker', 'iker@email.com', '612345678', 'Easo kalea 15', 'Donostia', '20004', '2025-04-03 06:41:14', 'iker001', 'password5'),
(6, 'Nerea', 'nerea@email.com', '622334455', 'Kale Kantoia 4', 'Gasteiz', '01002', '2025-04-03 06:41:14', 'nerea999', 'password6'),
(7, 'Unai', 'unai@email.com', '633112233', 'Etxebarri kalea 8', 'Bilbo', '48003', '2025-04-03 06:41:14', 'unai222', 'password7');

--
-- Disparadores `bezeroak`
--
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
(1, 'Jon', 'jon123', 'password1', 'ez'),
(2, 'Ane', 'ane321', 'password2', 'ez'),
(3, 'Mikel', 'mikel789', 'password3', 'ez'),
(4, 'Maite', 'maite777', 'password4', 'ez'),
(5, 'Iker', 'iker001', 'password5', 'ez'),
(6, 'Nerea', 'nerea999', 'password6', 'ez'),
(7, 'Unai', 'unai222', 'password7', 'ez'),
(8, 'Xabier', 'xabier_admin', 'adminpass1', 'bai'),
(9, 'Maitane', 'maitane_salt', 'adminpass2', 'bai'),
(10, 'Ander', 'ander_salt', 'adminpass3', 'bai'),
(11, 'Leire', 'leire_kud', 'adminpass4', 'bai'),
(12, 'Julen', 'julen_garr', 'adminpass5', 'bai'),
(13, 'Miren', 'miren_kud', 'adminpass6', 'bai'),
(14, 'Ibai', 'ibai_admin', 'adminpass7', 'bai');

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
  `kargua` varchar(255) NOT NULL,
  `telefonoa` varchar(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `alta_data` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `helbidea` text DEFAULT NULL,
  `herria` varchar(100) DEFAULT NULL,
  `posta_kodea` varchar(10) DEFAULT NULL,
  `erablitzaile_izena` varchar(255) DEFAULT NULL,
  `pasahitza` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `langileak`
--

INSERT INTO `langileak` (`id_langile`, `izena`, `kargua`, `telefonoa`, `email`, `alta_data`, `helbidea`, `herria`, `posta_kodea`, `erablitzaile_izena`, `pasahitza`) VALUES
(1, 'Xabier', 'Administratzailea', '645223344', 'xabier@email.com', '2025-04-03 06:41:28', 'Langile kalea 1', 'Bilbo', '48004', 'xabier_admin', 'adminpass1'),
(2, 'Maitane', 'Saltzailea', '655667788', 'maitane@email.com', '2025-04-03 06:41:28', 'Langile kalea 2', 'Gasteiz', '01003', 'maitane_salt', 'adminpass2'),
(3, 'Ander', 'Saltzailea', '611223344', 'ander@email.com', '2025-04-03 06:41:28', 'Langile kalea 3', 'Donostia', '20005', 'ander_salt', 'adminpass3'),
(4, 'Leire', 'Kudeatzailea', '688998877', 'leire@email.com', '2025-04-03 06:41:28', 'Langile kalea 4', 'Bilbo', '48005', 'leire_kud', 'adminpass4'),
(5, 'Julen', 'Garraiolaria', '677445566', 'julen@email.com', '2025-04-03 06:41:28', 'Langile kalea 5', 'Donostia', '20006', 'julen_garr', 'adminpass5'),
(6, 'Miren', 'Kudeatzailea', '699887766', 'miren@email.com', '2025-04-03 06:41:28', 'Langile kalea 6', 'Gasteiz', '01004', 'miren_kud', 'adminpass6'),
(7, 'Ibai', 'Administratzailea', '666777888', 'ibai@email.com', '2025-04-03 06:41:28', 'Langile kalea 7', 'Bilbo', '48006', 'ibai_admin', 'adminpass7');

--
-- Disparadores `langileak`
--
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
  `salduta` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `produktuak`
--

INSERT INTO `produktuak` (`id_produktu`, `izena`, `deskribapena`, `prezioa`, `id_kategoria`, `egoera`, `id_saltzaile`, `salduta`) VALUES
(1, 'Ordenagailu eramangarria', 'Intel i7, 16GB RAM, 512GB SSD', 850.00, 1, 'berria', 1, 1),
(2, 'Sofa hirukoitza', 'Kolore grisa, material erosoa', 300.00, 2, 'erabilia', 2, 0),
(3, 'Bizikleta', 'Mendiko bizikleta 26 hazbete', 500.00, 3, 'erabilia', 3, 1),
(4, 'Playmobil bilduma', 'Hainbat figuratxo eta eszenatoki', 100.00, 4, 'berria', 4, 0),
(5, 'Kotxea - BMW X5', 'Diesel, 2015eko modeloa', 25000.00, 5, 'erabilia', 5, 1),
(6, 'Zientzia fikziozko liburua', 'Saga ospetsuko 1. zatia', 20.00, 6, 'berria', 6, 0),
(7, 'Gitara elektrikoa', 'Fender Stratocaster, beltza', 900.00, 7, 'erabilia', 7, 0);

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
(1, 1, 1, NULL, '2025-04-03 08:41:52', 850.00),
(2, 3, 3, NULL, '2025-04-03 08:41:52', 500.00),
(3, 5, 5, NULL, '2025-04-03 08:41:52', 25000.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bezeroak`
--
ALTER TABLE `bezeroak`
  ADD PRIMARY KEY (`id_bezero`),
  ADD UNIQUE KEY `email` (`email`);

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
  ADD KEY `id_produktu` (`id_produktu`),
  ADD KEY `id_saltzaile` (`id_saltzaile`),
  ADD KEY `id_erosle` (`id_erosle`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `bezeroak`
--
ALTER TABLE `bezeroak`
  MODIFY `id_bezero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `erabiltzaileak`
--
ALTER TABLE `erabiltzaileak`
  MODIFY `id_erabiltzaile` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `kategoriak`
--
ALTER TABLE `kategoriak`
  MODIFY `id_kategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `langileak`
--
ALTER TABLE `langileak`
  MODIFY `id_langile` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `produktuak`
--
ALTER TABLE `produktuak`
  MODIFY `id_produktu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `salmentak`
--
ALTER TABLE `salmentak`
  MODIFY `id_salmenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
