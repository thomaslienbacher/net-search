-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Erstellungszeit: 19. Mrz 2020 um 18:07
-- Server-Version: 10.1.38-MariaDB
-- PHP-Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `netsearch_db`
--

--
-- Daten für Tabelle `devices`
--

INSERT INTO `devices` (`id_device`, `name`, `mac`) VALUES
(1, 'Thomas Laptop', 'f0:76:1c:f1:c3:27'),
(2, 'Elvir Laptop', 'ec:8e:b5:52:11:59'),
(3, 'Noa Laptop', '04:92:26:70:1d:4d'),
(4, 'Test Laptop', '00:11:22:33:44:55'),
(5, 'Test Laptop 2', '00:1a:2b:3c:4d:5c');

--
-- Daten für Tabelle `rooms`
--

INSERT INTO `rooms` (`id_room`, `name`) VALUES
(1, '101'),
(2, '102'),
(3, '103'),
(4, '104');

--
-- Daten für Tabelle `switches`
--

INSERT INTO `switches` (`id_switch`, `name`, `ip`, `community_string`) VALUES
(1, 'Cisco SF300-24', '192.168.1.254', 'private'),
(2, 'Netgear Beispiel', '192.168.0.1', 'public'),
(3, 'Test Switch', '10.100.55.37', 'test');

--
-- Daten für Tabelle `port_connections`
--

INSERT INTO `port_connections` (`id_port_connection`, `switch_id`, `room_id`, `port`) VALUES
(1, 1, 1, 1),
(2, 1, 1, 2),
(3, 1, 2, 3),
(4, 1, 2, 4),
(5, 2, 3, 1),
(6, 2, 4, 2),
(7, 2, 4, 3),
(8, 2, 2, 4),
(9, 3, 4, 1),
(10, 3, 3, 2),
(11, 3, 3, 3),
(12, 3, 2, 4);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
