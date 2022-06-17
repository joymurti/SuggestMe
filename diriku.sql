-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2022 at 05:31 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `diriku`
--

-- --------------------------------------------------------

--
-- Table structure for table `food_suggestion`
--

CREATE TABLE `food_suggestion` (
  `id` int(11) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `jenis` varchar(25) NOT NULL,
  `mood` varchar(25) NOT NULL,
  `harga` int(7) NOT NULL,
  `gambar` varchar(25) NOT NULL DEFAULT 'default_food.jpg',
  `admin_id` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food_suggestion`
--

INSERT INTO `food_suggestion` (`id`, `nama`, `jenis`, `mood`, `harga`, `gambar`, `admin_id`) VALUES
(1, 'Nasi Kuning', 'Makanan', 'Pagi Hari', 9000, 'default_food.jpg', 1),
(2, 'Nasi Bakar', 'Makanan', 'Sore Hari', 25000, 'classDiagram(FIN).png', 1),
(3, 'Jasjus', 'Minuman', 'Siang Hari', 3000, 'default_food.jpg', 1),
(4, 'Milo', 'Minuman', 'Pagi Hari', 4500, 'default_food.jpg', 1),
(5, 'Lemon Tea', 'Minuman', 'Sore Hari', 5500, 'default_food.jpg', 1),
(6, 'Asem Jawa', 'Minuman', 'Siang Hari', 4350, 'default_food.jpg', 1),
(7, 'Mie Ayam', 'Makanan', 'Malam Hari', 12000, 'default_food.jpg', 1),
(8, 'Bubur', 'Makanan', 'Pagi Hari', 10000, 'bubur.jpg', 1),
(9, 'Sate Madura', 'Makanan', 'Malam Hari', 18000, 'sate.jpg', 1),
(10, 'Spagheti', 'Makanan', 'Sore Hari', 100000, 'spagheti.jpg', 1),
(11, 'Soto', 'Makanan', 'Sore Hari', 17000, 'Soto.jpg', 1),
(12, 'Martabak Manis', 'Makanan', 'Malam Hari', 26000, 'MartabakManis.jpg', 1),
(13, 'Martabak Telor', 'Makanan', 'Sore Hari', 24000, 'chrome_tdJZRF8uwH.png', 1),
(14, 'Sate Usus', 'Makanan', 'Malam Hari', 24000, 'sateUsus.jpg', 1),
(15, 'Milkshake', 'Minuman', 'Siang Hari', 17000, 'milkShake.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `user_id` int(11) NOT NULL,
  `keterangan` varchar(150) NOT NULL,
  `perubahan_saldo` int(11) NOT NULL,
  `tipe` enum('pemasukan','pengeluaran') NOT NULL,
  `tanggal` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`user_id`, `keterangan`, `perubahan_saldo`, `tipe`, `tanggal`) VALUES
(2, 'Pembelian Nasi Kuning sebanyak: 5 buah, seharga: 9000', -45000, 'pengeluaran', '2022-06-16'),
(2, 'Uang hasil gojek', 100000, 'pemasukan', '2022-06-16'),
(2, 'Beli bakso urat', -10000, 'pengeluaran', '2022-06-16'),
(2, 'Beli tas ransel', -55000, 'pengeluaran', '2022-06-16'),
(2, 'Pembelian Nasi Kuning sebanyak: 1 buah, seharga: 9000', -9000, 'pengeluaran', '2022-06-16'),
(2, 'Dapat THR!', 100000, 'pemasukan', '2022-06-16'),
(5, 'Pembelian Jasjus sebanyak: 26 buah, seharga: 3000', -78000, 'pengeluaran', '2022-06-16'),
(4, 'Jualan Bakso', 120000, 'pemasukan', '2022-06-16'),
(8, 'Jualan Bakso Bray', 100000, 'pemasukan', '2022-06-16'),
(2, 'Beli buku tulis 4 Buah!', -12000, 'pengeluaran', '2022-06-16'),
(2, 'Hasil jualan bakso hari minggu', 120000, 'pemasukan', '2022-06-16'),
(2, 'Pembelian Nasi Kuning sebanyak: 3 buah, seharga: 9000', -27000, 'pengeluaran', '2022-06-16'),
(9, 'Pembelian Sate Madura sebanyak: 1 buah, seharga: 18000', -18000, 'pengeluaran', '2022-06-16'),
(9, 'Gajian', 120000, 'pemasukan', '2022-06-16'),
(9, 'Bayar internet', -130000, 'pengeluaran', '2022-06-16'),
(11, 'Gajian', 120000, 'pemasukan', '2022-06-17'),
(11, 'Beli Bakso', -10000, 'pengeluaran', '2022-06-17'),
(12, 'Hasil Jalan Gojek', 130000, 'pemasukan', '2022-06-17'),
(12, 'Beli Bakso!', -12000, 'pengeluaran', '2022-06-17'),
(12, 'Pembelian Bubur sebanyak: 1 buah, seharga: 10000', -10000, 'pengeluaran', '2022-06-17'),
(12, 'Pembelian Spagheti sebanyak: 1 buah, seharga: 100000', -100000, 'pengeluaran', '2022-06-17');

-- --------------------------------------------------------

--
-- Table structure for table `keuangan`
--

CREATE TABLE `keuangan` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `total_saldo` int(10) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `keuangan`
--

INSERT INTO `keuangan` (`id`, `user_id`, `total_saldo`) VALUES
(1, 2, 201000),
(2, 3, 100000),
(3, 4, 220000),
(4, 5, 22000),
(5, 6, 120000),
(6, 7, 130000),
(7, 8, 120000),
(8, 9, 92000),
(9, 10, 120000),
(10, 11, 230000),
(11, 12, 128000);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `nama_role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `nama_role`) VALUES
(1, 'admin'),
(2, 'consumer');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `telepon` varchar(15) NOT NULL,
  `gambar` varchar(25) NOT NULL DEFAULT 'profile.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `role_id`, `username`, `password`, `nama`, `alamat`, `telepon`, `gambar`) VALUES
(1, 1, 'admin', 'admin', 'Admin Sejahtera', 'Jalan Para Admin', '087825778267', 'admin.jpg'),
(2, 2, 'wijoyo', 'wijoyo0210', 'Wijoyo Raharjo Murti Indra W.', 'Jalan Taman Saturnus I No.33', '087825922428', 'joyyyyyy.jpg'),
(3, 2, 'thomas', 'thomas1111', 'Thomas Ganteng', 'Jalan Taman Batu', '087836227482', 'profile.png'),
(4, 2, 'jajang', 'jajangaja02', 'Jajang Si Pemberani', 'Jalan Setapak', '08782394912', 'profile.png'),
(5, 2, 'aa0000', 'aa909090', 'Aaaaa', 'Jalan Taman Luas', '08783264712837', 'profile.png'),
(6, 2, 'barueuy021002', 'barueuy021002', 'Baru Euy', 'Jalan Baru Euy, no.33', '087832772839', 'profile.png'),
(7, 2, 'sayauserbaru02', 'lalala021002', 'Saya User Baru CMIW', 'Jalan Para Pendatang Baru', '087825922428', 'profile.png'),
(8, 2, 'cobafitur', 'cobacoba02', 'Coba Fitur Dulu Bray', 'Jalan Fitur Dulu', '087832912441', 'profile.png'),
(9, 2, 'userrbaru02', 'userrbaru02', 'Userr Baru', 'Jln. Aspal', '0878567356', 'ClassDiagram.png'),
(10, 2, 'contohuserbaru', '123456', 'Contoh User Sudah Tidak Terbaru', 'Perumahan Sementara', '087823993242', 'I33LdkUCwA.png'),
(11, 2, 'aaa', '123', 'AAA', 'AAAAA', '08783772834', 'profile.png'),
(12, 2, 'contohuserrr', '123456', 'User Kodingan', 'Perumahan Orang Kaya', '08783274823', 'netbeans64_rav3Gs9Rlu.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `food_suggestion`
--
ALTER TABLE `food_suggestion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `keuangan`
--
ALTER TABLE `keuangan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nama_role` (`nama_role`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `food_suggestion`
--
ALTER TABLE `food_suggestion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `keuangan`
--
ALTER TABLE `keuangan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
