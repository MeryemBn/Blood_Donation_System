-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 09 mai 2024 à 16:42
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `blooddonation`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` int(10) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin1234');

-- --------------------------------------------------------

--
-- Structure de la table `donneur`
--

CREATE TABLE `donneur` (
  `id` int(11) NOT NULL,
  `nom` varchar(230) NOT NULL,
  `groupe_sanguin` varchar(250) DEFAULT NULL,
  `sex` varchar(240) NOT NULL,
  `age` int(11) NOT NULL,
  `adresse` varchar(250) NOT NULL,
  `numtel` bigint(20) DEFAULT NULL,
  `date` date DEFAULT curdate(),
  `username` varchar(250) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `donneur`
--

INSERT INTO `donneur` (`id`, `nom`, `groupe_sanguin`, `sex`, `age`, `adresse`, `numtel`, `date`, `username`, `password`) VALUES
(1, 'Meryem Benhadia', 'A+', 'Female', 20, 'Riad Salam', 613254954, '2024-04-19', 'meryem', 'don1234'),
(2, 'Zineb Saidi', 'O+', 'Female', 21, 'Ait melloul', 782526750, '2024-03-08', 'zineb', 'don1234'),
(3, 'Hiba Rais', 'B+', 'Female', 20, 'El houda', 623567818, '2024-04-20', 'hiba', 'don1234'),
(4, 'Dounia Kritet', 'B+', 'Female', 22, 'XXXXX', 634910734, '2024-04-18', 'dounia', 'don1234'),
(5, 'Jamal Idrissi', 'A+', 'Male', 33, 'Marrakech', 615348890, '2024-04-12', 'jamal', 'don1234'),
(6, 'Hicham Fadel', 'A+', 'Male', 40, 'El jadida', 614678822, '2024-03-29', 'hicham', 'don1234'),
(18, 'Othmane Benhadia', 'A+', 'Male', 20, 'Riad Salam', 658974213, '2024-05-04', 'Othmane Benhadia', 'don1234');

-- --------------------------------------------------------

--
-- Structure de la table `historiquedonation`
--

CREATE TABLE `historiquedonation` (
  `id` int(11) NOT NULL,
  `groupe_sanguin` varchar(250) NOT NULL,
  `donneur` int(11) NOT NULL,
  `date` date NOT NULL,
  `heure` time DEFAULT curtime(),
  `qte_donnee` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `historiquedonation`
--

INSERT INTO `historiquedonation` (`id`, `groupe_sanguin`, `donneur`, `date`, `heure`, `qte_donnee`) VALUES
(1, 'A+', 1, '2024-04-19', '23:42:47', 1),
(2, 'O+', 2, '2024-03-11', '13:55:34', 2),
(3, 'A+', 1, '2024-04-25', '13:58:09', 3),
(4, 'B+', 3, '2024-03-08', '22:53:34', 1),
(5, 'A+', 1, '2024-04-15', '23:05:55', 5),
(6, 'B+', 3, '2024-04-19', '13:54:29', 4),
(18, 'O+', 2, '2024-05-07', '01:23:51', 1);

-- --------------------------------------------------------

--
-- Structure de la table `pack_disponible`
--

CREATE TABLE `pack_disponible` (
  `id` int(11) NOT NULL,
  `groupe_sanguin` varchar(250) NOT NULL,
  `qte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `pack_disponible`
--

INSERT INTO `pack_disponible` (`id`, `groupe_sanguin`, `qte`) VALUES
(1, 'A+', 20),
(2, 'B+', 21),
(3, 'AB+', 40),
(4, 'O+', 42),
(5, 'A-', 18),
(6, 'B-', 35),
(7, 'AB-', 130),
(8, 'O-', 24);

-- --------------------------------------------------------

--
-- Structure de la table `rendezvous`
--

CREATE TABLE `rendezvous` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `groupe_sanguin` varchar(5) DEFAULT NULL,
  `date_rendezvous` date DEFAULT NULL,
  `heure_rendezvous` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `rendezvous`
--

INSERT INTO `rendezvous` (`id`, `nom`, `prenom`, `groupe_sanguin`, `date_rendezvous`, `heure_rendezvous`) VALUES
(2, 'Rais', 'Hiba', 'B+', '2024-04-30', '15:04:51'),
(19, 'Benhadia', 'Meryem', 'A+', '2024-05-02', '13:26:29'),
(20, 'Saidi', 'Zineb', 'O+', '2024-05-02', '13:54:21');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `donneur`
--
ALTER TABLE `donneur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `historiquedonation`
--
ALTER TABLE `historiquedonation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `donneur` (`donneur`);

--
-- Index pour la table `pack_disponible`
--
ALTER TABLE `pack_disponible`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `rendezvous`
--
ALTER TABLE `rendezvous`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `donneur`
--
ALTER TABLE `donneur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT pour la table `historiquedonation`
--
ALTER TABLE `historiquedonation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `pack_disponible`
--
ALTER TABLE `pack_disponible`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `rendezvous`
--
ALTER TABLE `rendezvous`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `historiquedonation`
--
ALTER TABLE `historiquedonation`
  ADD CONSTRAINT `historiquedonation_ibfk_1` FOREIGN KEY (`donneur`) REFERENCES `donneur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
