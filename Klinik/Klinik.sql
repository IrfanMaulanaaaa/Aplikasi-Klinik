-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 14 Nov 2023 pada 15.47
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: 'Klinik'
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id_admin` varchar(5) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL,
  `email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id_admin`, `username`, `password`, `email`) VALUES
('a002', 'irfan', '1', 'irfan@gmail.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dokter`
--

CREATE TABLE `dokter` (
  `id_dokter` varchar(15) NOT NULL,
  `nama_dokter` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(15) NOT NULL,
  `bidang_spesialisasi` varchar(30) NOT NULL,
  `no_kontak` varchar(15) NOT NULL,
  `jadwal_kunjungan_kode_jadwal` varchar(5) DEFAULT NULL,
  `dokter_id_dokter` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `dokter`
--

INSERT INTO `dokter` (`id_dokter`, `nama_dokter`, `username`, `password`, `bidang_spesialisasi`, `no_kontak`, `jadwal_kunjungan_kode_jadwal`, `dokter_id_dokter`) VALUES
('D001', 'irfan', '123', '123', '123123', '123', NULL, NULL),
('D003', 'a', 'aa', '1', 'a', '1', NULL, NULL),
('D005', 'a', 'a', 'a', 'a', '123', NULL, NULL),
('D006', 'a', 'a', '1', 'a', '123', NULL, NULL),
('D007', 'a', 'a', 'a', 'a', '1', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwal_kunjungan`
--

CREATE TABLE `jadwal_kunjungan` (
  `kode_jadwal` varchar(5) NOT NULL,
  `nama_pasien` varchar(30) NOT NULL,
  `waktu_kunjungan` datetime NOT NULL,
  `admin_id_admin` varchar(5) NOT NULL,
  `dokter_id_dokter` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `jadwal_kunjungan`
--

INSERT INTO `jadwal_kunjungan` (`kode_jadwal`, `nama_pasien`, `waktu_kunjungan`, `admin_id_admin`, `dokter_id_dokter`) VALUES
('D003', 'a', '2222-12-12 00:00:00', 'a002', 'D003'),
('D006', 'irfan', '2222-12-12 00:00:00', 'a002', 'D006'),
('D007', 'irfan', '2222-12-12 12:00:00', 'a002', 'D007');

--
-- Trigger `jadwal_kunjungan`
--
DELIMITER $$
CREATE TRIGGER `fkntm_jadwal_kunjungan` BEFORE UPDATE ON `jadwal_kunjungan` FOR EACH ROW BEGIN 
  SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Non Transferable FK constraint on table Jadwal_Kunjungan is violated'; 
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasien`
--

CREATE TABLE `pasien` (
  `id_pasien` varchar(5) NOT NULL,
  `nama_depan` varchar(20) NOT NULL,
  `nama_belakang` varchar(20) NOT NULL,
  `no_telepon` varchar(15) NOT NULL,
  `no_kerabat` varchar(15) DEFAULT NULL,
  `alamat` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `tanggal_periksa` date NOT NULL,
  `admin_id_admin` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pasien`
--

INSERT INTO `pasien` (`id_pasien`, `nama_depan`, `nama_belakang`, `no_telepon`, `no_kerabat`, `alamat`, `jenis_kelamin`, `tanggal_lahir`, `tanggal_periksa`, `admin_id_admin`) VALUES
('1', 'a', 'a', 'a', 'a', 'a', 'Perempuan', '2004-12-12', '2004-12-12', 'a002');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indeks untuk tabel `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`id_dokter`),
  ADD UNIQUE KEY `dokter__idx` (`jadwal_kunjungan_kode_jadwal`),
  ADD KEY `dokter_dokter_fk` (`dokter_id_dokter`);

--
-- Indeks untuk tabel `jadwal_kunjungan`
--
ALTER TABLE `jadwal_kunjungan`
  ADD PRIMARY KEY (`kode_jadwal`),
  ADD UNIQUE KEY `jadwal_kunjungan__idx` (`dokter_id_dokter`),
  ADD KEY `jadwal_kunjungan_admin_fk` (`admin_id_admin`);

--
-- Indeks untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`id_pasien`),
  ADD KEY `pasien_admin_fk` (`admin_id_admin`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `dokter`
--
ALTER TABLE `dokter`
  ADD CONSTRAINT `dokter_dokter_fk` FOREIGN KEY (`dokter_id_dokter`) REFERENCES `dokter` (`id_dokter`) ON DELETE CASCADE,
  ADD CONSTRAINT `dokter_jadwal_kunjungan_fk` FOREIGN KEY (`jadwal_kunjungan_kode_jadwal`) REFERENCES `jadwal_kunjungan` (`kode_jadwal`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `jadwal_kunjungan`
--
ALTER TABLE `jadwal_kunjungan`
  ADD CONSTRAINT `jadwal_kunjungan_admin_fk` FOREIGN KEY (`admin_id_admin`) REFERENCES `admin` (`id_admin`) ON DELETE CASCADE,
  ADD CONSTRAINT `jadwal_kunjungan_dokter_fk` FOREIGN KEY (`dokter_id_dokter`) REFERENCES `dokter` (`id_dokter`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD CONSTRAINT `pasien_admin_fk` FOREIGN KEY (`admin_id_admin`) REFERENCES `admin` (`id_admin`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
