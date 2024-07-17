-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 16/07/2024 às 21:59
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bancogerencia`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `tab_cliente`
--

CREATE TABLE `tab_cliente` (
  `num_conta` int(11) UNSIGNED NOT NULL,
  `titular` varchar(255) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `senha` varchar(6) NOT NULL,
  `saldo` double NOT NULL,
  `data_criacao` varchar(30) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tab_cliente`
--

INSERT INTO `tab_cliente` (`num_conta`, `titular`, `cpf`, `senha`, `saldo`, `data_criacao`, `status`) VALUES
(1538428275, 'johan', '12345678910', '123456', 0, '2024-07-16T16:08:00.700695300', 'Em atividade');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
