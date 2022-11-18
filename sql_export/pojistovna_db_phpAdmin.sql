-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Stř 16. lis 2022, 10:30
-- Verze serveru: 10.4.24-MariaDB
-- Verze PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `pojistovna_db`
--
CREATE DATABASE IF NOT EXISTS `pojistovna_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `pojistovna_db`;

DELIMITER $$
--
-- Procedury
--
CREATE PROCEDURE `SeradPodlePrijmeniJmena` (IN `preskocit_radku` INT, IN `zobrazit_radku` INT)   BEGIN
   SELECT * FROM `pojistnici`
   ORDER BY `prijmeni`, `jmeno` LIMIT preskocit_radku, zobrazit_radku;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabulky `delete_pojistnici`
--

CREATE TABLE `delete_pojistnici` (
  `id` int(11) NOT NULL,
  `prijmeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `jmeno` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `datum_narozeni` date NOT NULL,
  `ulice` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `psc` int(10) NOT NULL,
  `mesto` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `telefon` varchar(20) COLLATE utf8_czech_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- VZTAHY TABULKY `delete_pojistnici`:
--

--
-- Vypisuji data pro tabulku `delete_pojistnici`
--

INSERT INTO `delete_pojistnici` (`id`, `prijmeni`, `jmeno`, `datum_narozeni`, `ulice`, `psc`, `mesto`, `telefon`) VALUES
(16, 'Novák', 'Pavel', '1975-10-04', 'Požárníků  180', 27301, 'Lhota', '774256936');

-- --------------------------------------------------------

--
-- Struktura tabulky `delete_smlouvy`
--

CREATE TABLE `delete_smlouvy` (
  `id` int(11) NOT NULL,
  `typ_pojisteni_id` int(11) NOT NULL,
  `pojistny_limit` int(12) NOT NULL,
  `platnost_od` date NOT NULL,
  `platnost_do` date NOT NULL,
  `pojistnik_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- VZTAHY TABULKY `delete_smlouvy`:
--

--
-- Vypisuji data pro tabulku `delete_smlouvy`
--

INSERT INTO `delete_smlouvy` (`id`, `typ_pojisteni_id`, `pojistny_limit`, `platnost_od`, `platnost_do`, `pojistnik_id`) VALUES
(20, 5, 6000000, '2022-11-01', '2031-01-31', 14);

-- --------------------------------------------------------

--
-- Struktura tabulky `pojistnici`
--

CREATE TABLE `pojistnici` (
  `id` int(11) NOT NULL,
  `prijmeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `jmeno` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `datum_narozeni` date NOT NULL,
  `ulice` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `psc` int(10) NOT NULL,
  `mesto` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `telefon` varchar(20) COLLATE utf8_czech_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- VZTAHY TABULKY `pojistnici`:
--

--
-- Vypisuji data pro tabulku `pojistnici`
--

INSERT INTO `pojistnici` (`id`, `prijmeni`, `jmeno`, `datum_narozeni`, `ulice`, `psc`, `mesto`, `telefon`) VALUES
(1, 'Novák', 'Pavel', '1975-10-04', 'Požárníků  180', 27301, 'Lhota', '774256936'),
(2, 'Mokrý', 'Jakub', '1986-04-23', 'Hlavní 6/122', 62400, 'Brno - Komín', '603589124'),
(3, 'Drnovská', 'Kristýna', '1958-08-05', 'Plucárna 3560/1', 69501, 'Hodonín', '728569321'),
(4, 'Šedý', 'Michal', '1990-06-25', 'Na Roudné 210/135', 30100, 'Plzeň - Severní Předměstí', '777589641'),
(5, 'Svobodová', 'Jana', '1977-03-01', 'K Juliáně 127', 16400, 'Praha 6 - Přední Kopanina', '605893254'),
(6, 'Veselý', 'Jan', '1967-07-12', 'Krajinská 35/1', 37001, 'České Budějovice', '605897412'),
(7, 'Černý', 'Karel', '1992-04-30', 'Míru 701', 28903, 'Městec Králové', '603325672'),
(8, 'Kučerová', 'Vendula', '2000-01-05', '1. máje 500', 73961, 'Třinec - Staré Město', '728222365'),
(9, 'Dvořáková', 'Dana', '1954-09-27', 'Na Mýtině 2892/18', 46601, 'Jablonec nad Nisou', '603969632'),
(10, 'Procházková', 'Monika', '1995-06-15', 'K lipám 223/45', 19000, 'Praha 9 - Střížkov', '776554211'),
(11, 'Novák', 'Pavel', '1995-04-20', 'Dlouhá 6', 39002, 'Tábor', '608523600'),
(12, 'Novák', 'Pavel', '1999-06-10', 'Krátka 1', 12000, 'Praha 2', '776369123'),
(13, 'Krutina', 'Martin', '1996-12-06', 'Nová 65', 42501, 'Nová Paka', '603569874'),
(14, 'Drnovská', 'Petra', '1986-08-14', 'Jilmová 15', 14000, 'Praha 4', '603222145'),
(15, 'Krátká', 'Jana', '1989-05-14', 'Novodvorská 264/5', 14000, 'Praha 4', '604522145'),
(17, 'Houdek', 'Jan', '2001-10-05', 'Dlouhá 5', 32401, 'Královice', '604589221'),
(18, 'Bečvářová', 'Jarmila', '2004-06-25', 'Krátká 251/9', 25512, 'Dolní Hradec', '777582452'),
(19, 'Kučera', 'Tomáš', '1997-06-23', 'Jabloňová 15', 14000, 'Praha 4', '60589663');

--
-- Triggery `pojistnici`
--
DELIMITER $$
CREATE TRIGGER `after_delete_pojistnici` AFTER DELETE ON `pojistnici` FOR EACH ROW BEGIN
    INSERT INTO delete_pojistnici
    VALUES (OLD.`id`, OLD.`prijmeni`, OLD.`jmeno`, OLD.`datum_narozeni`, OLD.`ulice`, OLD.`psc`, OLD.`mesto`, OLD.`telefon`);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Zástupná struktura pro pohled `pojistnici_smlouvy`
-- (Vlastní pohled viz níže)
--
CREATE TABLE `pojistnici_smlouvy` (
`id_pojistnika` int(11)
,`prijmeni` varchar(50)
,`jmeno` varchar(50)
,`datum_narozeni` date
,`id_smlouvy` int(11)
,`id_produktu` int(11)
,`typ_pojisteni` varchar(50)
,`pojistny_limit` int(12)
,`platnost_od` date
,`platnost_do` date
);

-- --------------------------------------------------------

--
-- Struktura tabulky `produkty`
--

CREATE TABLE `produkty` (
  `id` int(11) NOT NULL,
  `typ_pojisteni` varchar(50) COLLATE utf8_czech_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- VZTAHY TABULKY `produkty`:
--

--
-- Vypisuji data pro tabulku `produkty`
--

INSERT INTO `produkty` (`id`, `typ_pojisteni`) VALUES
(1, 'Pojištění nemovitého majetku'),
(2, 'Pojištění odpovědnosti'),
(3, 'Úrazové pojištění'),
(4, 'Cestovní pojištění'),
(5, 'Životní pojištění');

-- --------------------------------------------------------

--
-- Struktura tabulky `smlouvy`
--

CREATE TABLE `smlouvy` (
  `id` int(11) NOT NULL,
  `typ_pojisteni_id` int(11) NOT NULL,
  `pojistny_limit` int(12) NOT NULL,
  `platnost_od` date NOT NULL,
  `platnost_do` date NOT NULL,
  `pojistnik_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- VZTAHY TABULKY `smlouvy`:
--   `pojistnik_id`
--       `pojistnici` -> `id`
--   `typ_pojisteni_id`
--       `produkty` -> `id`
--

--
-- Vypisuji data pro tabulku `smlouvy`
--

INSERT INTO `smlouvy` (`id`, `typ_pojisteni_id`, `pojistny_limit`, `platnost_od`, `platnost_do`, `pojistnik_id`) VALUES
(1, 1, 1000000, '0000-00-00', '0000-00-00', 1),
(2, 2, 2500000, '2020-08-01', '2025-08-31', 8),
(3, 1, 5000000, '2017-01-01', '2022-12-31', 2),
(4, 5, 2000000, '2019-10-01', '2032-10-31', 2),
(5, 4, 5000000, '2022-11-12', '2022-11-19', 10),
(6, 1, 10000000, '2015-04-01', '2025-11-30', 6),
(7, 5, 2500000, '2014-09-01', '2032-12-31', 3),
(8, 2, 1000000, '2022-10-12', '2026-09-30', 4),
(9, 3, 3000000, '2020-01-01', '2030-12-31', 5),
(10, 1, 5000000, '2018-07-01', '2027-12-31', 7),
(11, 5, 230000, '2015-01-01', '2035-12-31', 9),
(12, 2, 1500000, '2018-07-01', '2027-12-31', 7),
(13, 2, 2000000, '2015-04-01', '2025-11-30', 6),
(14, 5, 3500000, '2022-01-01', '2042-12-31', 7),
(15, 4, 5000000, '2022-10-05', '2022-10-20', 4),
(16, 1, 5000000, '2022-01-01', '2040-12-31', 11),
(17, 3, 2000000, '2022-11-16', '2024-01-12', 19),
(18, 2, 2500000, '2022-09-01', '2023-04-30', 12),
(19, 5, 5000000, '2017-11-01', '2028-01-31', 13),
(22, 1, 6000000, '2014-05-01', '2026-01-31', 18);

--
-- Triggery `smlouvy`
--
DELIMITER $$
CREATE TRIGGER `after_delete_smlouvy` AFTER DELETE ON `smlouvy` FOR EACH ROW BEGIN
    INSERT INTO delete_smlouvy
    VALUES (OLD.`id`,OLD.`typ_pojisteni_id`,OLD.`pojistny_limit`,OLD.`platnost_od`,OLD.`platnost_do`,OLD.`pojistnik_id`); 
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura pro pohled `pojistnici_smlouvy`
--
DROP TABLE IF EXISTS `pojistnici_smlouvy`;

CREATE VIEW `pojistnici_smlouvy`  AS SELECT `pojistnici`.`id` AS `id_pojistnika`, `pojistnici`.`prijmeni` AS `prijmeni`, `pojistnici`.`jmeno` AS `jmeno`, `pojistnici`.`datum_narozeni` AS `datum_narozeni`, `smlouvy`.`id` AS `id_smlouvy`, `produkty`.`id` AS `id_produktu`, `produkty`.`typ_pojisteni` AS `typ_pojisteni`, `smlouvy`.`pojistny_limit` AS `pojistny_limit`, `smlouvy`.`platnost_od` AS `platnost_od`, `smlouvy`.`platnost_do` AS `platnost_do` FROM (`pojistnici` join (`smlouvy` join `produkty`)) WHERE `pojistnici`.`id` = `smlouvy`.`pojistnik_id` AND `smlouvy`.`typ_pojisteni_id` = `produkty`.`id``id`  ;

--
-- Indexy pro exportované tabulky
--

--
-- Indexy pro tabulku `pojistnici`
--
ALTER TABLE `pojistnici`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pro tabulku `produkty`
--
ALTER TABLE `produkty`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pro tabulku `smlouvy`
--
ALTER TABLE `smlouvy`
  ADD PRIMARY KEY (`id`),
  ADD KEY `typ_pojisteni` (`typ_pojisteni_id`),
  ADD KEY `typ_pojisteni_id` (`typ_pojisteni_id`),
  ADD KEY `smlouvyPojistnici` (`pojistnik_id`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `pojistnici`
--
ALTER TABLE `pojistnici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pro tabulku `produkty`
--
ALTER TABLE `produkty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pro tabulku `smlouvy`
--
ALTER TABLE `smlouvy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `smlouvy`
--
ALTER TABLE `smlouvy`
  ADD CONSTRAINT `smlouvyPojistnici` FOREIGN KEY (`pojistnik_id`) REFERENCES `pojistnici` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `smlouvyProdukty` FOREIGN KEY (`typ_pojisteni_id`) REFERENCES `produkty` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
