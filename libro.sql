-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 31-10-2023 a las 18:13:45
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `isbn` bigint(14) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `autor` varchar(100) NOT NULL,
  `anio` year(4) NOT NULL,
  `genero` varchar(100) NOT NULL,
  `cantEjemplares` int(11) NOT NULL,
  `editorial` varchar(100) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`isbn`, `titulo`, `autor`, `anio`, `genero`, `cantEjemplares`, `editorial`, `estado`) VALUES
(1, 'Fausto', 'Goethe, Wolfgang', '1968', 'Tragedia', 1, 'Ramón Sopena', 1),
(9789500718226, 'La Muerte y Otras Sorpresas', 'Benedetti, Mario', '2000', 'Narrativa Breve', 3, 'Sudamericana', 1),
(9789500755757, 'Ficciones', 'Borges, Jorge Luis', '2016', 'Narrativa Breve', 4, 'Sudamericana', 1),
(9789500755764, 'El Aleph', 'Borges, Jorge Luis', '2011', 'Narrativa Breve', 4, 'Sudamericana', 1),
(9789872425616, 'El Príncipe', 'Maquiavelo, Nicolás', '2008', 'Política', 2, 'BEEME', 1),
(9789874832696, 'El Principito', 'Saint-Exupery, Antoine de', '2022', 'Infantil', 2, 'Libertador', 1),
(9789875505506, 'El Libro de Monelle', 'SchWob, Marcel', '1968', 'Narrativa Breve', 3, 'Ramón Sopena', 1),
(9789875806092, 'El Libro del Fantasma', 'Dolina, Alejandro', '2013', 'Narrativa Breve', 2, 'Terramar', 1),
(9789876170123, 'Alicia en el País de las Maravillas', 'Carroll, Lewis', '2016', 'Infantil', 5, 'Terramar', 1),
(9789876620642, 'La Vuelta al Mundo en Ochenta Días', 'Verne, Julio', '2016', 'Aventura', 2, 'Centro Editor de Cultura', 1),
(9789876884518, 'El Principito', 'Saint-Exupery, Antoine de', '2021', 'Infantil', 2, 'UniRío Editora', 1),
(9789877053952, 'El Principito', 'Saint-Exupery, Antoine de', '2015', 'Infantil', 4, 'El Gato de Hojalata', 1),
(9789877673883, 'Cazador de nubes', 'Vedia, Fernando de', '2023', 'Infantil', 2, 'Planeta Lector', 1),
(9789877673951, 'El Principito', 'Saint-Exupery, Antoine de', '2023', 'Infantil', 4, 'Planeta Lector', 1),
(9789878481654, 'El Principito ha regresado', 'Núñez, Ramón Avelino', '2022', 'Infantil', 1, 'Editorial D', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`isbn`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
