CREATE DATABASE DB_BigarrenEskukoMerkatua;
USE DB_BigarrenEskukoMerkatua;

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
    alta_data TIMESTAMP,
    erablitzaile_izena VARCHAR(255),
    pasahitza VARCHAR(255)
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
    posta_kodea VARCHAR(10),
    erablitzaile_izena VARCHAR(255),
    pasahitza VARCHAR(255)
);

CREATE TABLE Produktuak (
    id_produktu INT PRIMARY KEY AUTO_INCREMENT,
    izena VARCHAR(255) NOT NULL,
    deskribapena TEXT,
    prezioa DECIMAL(10,2) NOT NULL,
    id_kategoria INT,
    egoera ENUM('berria', 'erabilia', 'hondatua') NOT NULL,
    id_saltzaile INT NOT NULL,
    salduta boolean,
    FOREIGN KEY (id_kategoria) REFERENCES Kategoriak(id_kategoria) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (id_saltzaile) REFERENCES Bezeroak(id_bezero) ON DELETE CASCADE ON UPDATE CASCADE
);

  CREATE TABLE Salmentak (
    id_salmenta INT PRIMARY KEY AUTO_INCREMENT,
    id_produktu INT NOT NULL,
    id_saltzaile INT,
    id_erosle INT ,
    data DATETIME,
    salmenta_prezioa DECIMAL(10,2),
    FOREIGN KEY (id_produktu) REFERENCES Produktuak(id_produktu) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_saltzaile) REFERENCES Bezeroak(id_bezero) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_erosle) REFERENCES Bezeroak(id_bezero) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Erabiltzaileak (
    id_erabiltzaile INT PRIMARY KEY AUTO_INCREMENT,
    izena VARCHAR(255) NOT NULL,
    erabiltzailea VARCHAR(255) UNIQUE NOT NULL,
    pasahitza VARCHAR(255) NOT NULL,
    administratzailea ENUM('bai', 'ez') NOT NULL
);


----------------------TRIGERRAK----------------------------

DELIMITER //

CREATE TRIGGER set_alta_data_langileak
BEFORE INSERT ON Langileak
FOR EACH ROW
BEGIN
    IF NEW.alta_data IS NULL THEN
        SET NEW.alta_data = NOW();
    END IF;
END;

//

DELIMITER ;


DELIMITER //

CREATE TRIGGER set_alta_data_bezeroak
BEFORE INSERT ON Bezeroak
FOR EACH ROW
BEGIN
    IF NEW.alta_data IS NULL THEN
        SET NEW.alta_data = NOW();
    END IF;
END;

//

DELIMITER ;

-- Trigger-a: Langilea gehitzean Erabiltzaile bezala gehitu
CREATE TRIGGER trg_InsertLangileak AFTER INSERT ON Langileak
FOR EACH ROW
INSERT INTO Erabiltzaileak (izena, erabiltzailea, pasahitza, administratzailea)
VALUES (NEW.izena, NEW.erablitzaile_izena, NEW.pasahitza, 'bai');

-- Trigger-a: Bezeroa gehitzean Erabiltzaile bezala gehitu
CREATE TRIGGER trg_InsertBezeroak AFTER INSERT ON Bezeroak
FOR EACH ROW
INSERT INTO Erabiltzaileak (izena, erabiltzailea, pasahitza, administratzailea)
VALUES (NEW.izena, NEW.erablitzaile_izena, NEW.pasahitza, 'ez');


DELIMITER $$
CREATE TRIGGER `after_product_sold` AFTER UPDATE ON `produktuak` FOR EACH ROW BEGIN
    
    IF NEW.salduta = TRUE AND OLD.salduta = FALSE THEN
        
        INSERT INTO Salmentak (id_produktu, id_saltzaile, id_erosle, data, salmenta_prezioa)
        VALUES (NEW.id_produktu, NEW.id_saltzaile, NULL, NOW(), NEW.prezioa);
    END IF;
END
$$
DELIMITER ;

------------------------------------------------------INSERTS-----------------------------------

-- Insertar 7 categorías
INSERT INTO Kategoriak (izena, deskribapena) VALUES
('Elektronika', 'Gailu elektronikoak eta osagarriak'),
('Altzariak', 'Etxerako altzariak'),
('Kirola', 'Kirol eta aisialdirako produktuak'),
('Jostailuak', 'Haurrentzako jostailuak'),
('Ibilgailuak', 'Autoak, motoak eta osagarriak'),
('Liburuak', 'Liburuak eta komikiak'),
('Musika', 'Musika tresnak eta osagarriak');

-- Insertar 7 bezeroak
INSERT INTO Bezeroak (izena, email, telefonoa, helbidea, herria, posta_kodea, erablitzaile_izena, pasahitza)
VALUES
('Jon', 'jon@email.com', '600123456', 'Kale Nagusia 5', 'Bilbo', '48001', 'jon123', 'password1'),
('Ane', 'ane@email.com', '600654321', 'San Mames 10', 'Bilbo', '48002', 'ane321', 'password2'),
('Mikel', 'mikel@email.com', '699112233', 'Zabalbide 22', 'Gasteiz', '01001', 'mikel789', 'password3'),
('Maite', 'maite@email.com', '688334455', 'Donostia Hiribidea 7', 'Donostia', '20003', 'maite777', 'password4'),
('Iker', 'iker@email.com', '612345678', 'Easo kalea 15', 'Donostia', '20004', 'iker001', 'password5'),
('Nerea', 'nerea@email.com', '622334455', 'Kale Kantoia 4', 'Gasteiz', '01002', 'nerea999', 'password6'),
('Unai', 'unai@email.com', '633112233', 'Etxebarri kalea 8', 'Bilbo', '48003', 'unai222', 'password7');

-- Insertar 7 langileak
INSERT INTO Langileak (izena, kargua, telefonoa, email, helbidea, herria, posta_kodea, erablitzaile_izena, pasahitza)
VALUES
('Xabier', 'Administratzailea', '645223344', 'xabier@email.com', 'Langile kalea 1', 'Bilbo', '48004', 'xabier_admin', 'adminpass1'),
('Maitane', 'Saltzailea', '655667788', 'maitane@email.com', 'Langile kalea 2', 'Gasteiz', '01003', 'maitane_salt', 'adminpass2'),
('Ander', 'Saltzailea', '611223344', 'ander@email.com', 'Langile kalea 3', 'Donostia', '20005', 'ander_salt', 'adminpass3'),
('Leire', 'Kudeatzailea', '688998877', 'leire@email.com', 'Langile kalea 4', 'Bilbo', '48005', 'leire_kud', 'adminpass4'),
('Julen', 'Garraiolaria', '677445566', 'julen@email.com', 'Langile kalea 5', 'Donostia', '20006', 'julen_garr', 'adminpass5'),
('Miren', 'Kudeatzailea', '699887766', 'miren@email.com', 'Langile kalea 6', 'Gasteiz', '01004', 'miren_kud', 'adminpass6'),
('Ibai', 'Administratzailea', '666777888', 'ibai@email.com', 'Langile kalea 7', 'Bilbo', '48006', 'ibai_admin', 'adminpass7');

-- Insertar 7 produktuak (deben estar asociados a bezeroak y kategoriak)
INSERT INTO Produktuak (izena, deskribapena, prezioa, id_kategoria, egoera, id_saltzaile, salduta)
VALUES
('Ordenagailu eramangarria', 'Intel i7, 16GB RAM, 512GB SSD', 850.00, 1, 'berria', 1, FALSE),
('Sofa hirukoitza', 'Kolore grisa, material erosoa', 300.00, 2, 'erabilia', 2, FALSE),
('Bizikleta', 'Mendiko bizikleta 26 hazbete', 500.00, 3, 'erabilia', 3, FALSE),
('Playmobil bilduma', 'Hainbat figuratxo eta eszenatoki', 100.00, 4, 'berria', 4, FALSE),
('Kotxea - BMW X5', 'Diesel, 2015eko modeloa', 25000.00, 5, 'erabilia', 5, FALSE),
('Zientzia fikziozko liburua', 'Saga ospetsuko 1. zatia', 20.00, 6, 'berria', 6, FALSE),
('Gitara elektrikoa', 'Fender Stratocaster, beltza', 900.00, 7, 'erabilia', 7, FALSE);

-- Simulación de venta (actualizar salduta y que el trigger cree la venta)
UPDATE Produktuak SET salduta = TRUE WHERE id_produktu = 1;
UPDATE Produktuak SET salduta = TRUE WHERE id_produktu = 3;
UPDATE Produktuak SET salduta = TRUE WHERE id_produktu = 5;
