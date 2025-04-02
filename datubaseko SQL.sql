CREATE DATABASE BigarrenEskukoMerkatua;
USE BigarrenEskukoMerkatua;

CREATE TABLE Kategoriak (
    id_kategoria INT PRIMARY KEY AUTO_INCREMENT,
    izena VARCHAR(255) NOT NULL,
    deskribapena TEXT
);

CREATE TABLE Bezeroak (
    id_bezero INT PRIMARY KEY AUTO_INCREMENT,
    izena VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefonoa VARCHAR(20),
    helbidea TEXT,
    herria VARCHAR(100),
    posta_kodea VARCHAR(10),
    alta_data TIMESTAMP
);

CREATE TABLE Langileak (
    id_langile INT PRIMARY KEY AUTO_INCREMENT,
    izena VARCHAR(255) NOT NULL,
    kargua VARCHAR(255) NOT NULL,
    telefonoa VARCHAR(20),
    email VARCHAR(255) UNIQUE NOT NULL,
    alta_data TIMESTAMP,
    helbidea TEXT,
    herria VARCHAR(100),
    posta_kodea VARCHAR(10)
);

CREATE TABLE Produktuak (
    id_produktu INT PRIMARY KEY AUTO_INCREMENT,
    izena VARCHAR(255) NOT NULL,
    deskribapena TEXT,
    prezioa DECIMAL(10,2) NOT NULL,
    id_kategoria INT,
    egoera ENUM('berria', 'erabilia', 'hondatua') NOT NULL,
    id_saltzaile INT NOT NULL,
    FOREIGN KEY (id_kategoria) REFERENCES Kategoriak(id_kategoria) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (id_saltzaile) REFERENCES Bezeroak(id_bezero) ON DELETE CASCADE ON UPDATE CASCADE
);

  CREATE TABLE Salmentak (
    id_salmenta INT PRIMARY KEY AUTO_INCREMENT,
    id_produktu INT NOT NULL,
    id_bezero INT NOT NULL,
    data DATETIME,
    salmenta_prezioa DECIMAL(10,2),
    FOREIGN KEY (id_produktu) REFERENCES Produktuak(id_produktu) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_bezero) REFERENCES Bezeroak(id_bezero) ON DELETE CASCADE ON UPDATE CASCADE
);