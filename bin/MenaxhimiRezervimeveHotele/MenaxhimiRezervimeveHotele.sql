create database MenaxhimiRezervimeveHotele;
use MenaxhimiRezervimeveHotele;

CREATE TABLE `administratoret` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emri` varchar(20) NOT NULL,
  `mbiemri` varchar(20) NOT NULL,
  `gjinia` varchar(1) NOT NULL,
  `mosha` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` char(128) NOT NULL,
  `salt` char(20) NOT NULL,
  `COUNT` int(11) NOT NULL,
  `bllokimi` varchar(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE administratoret AUTO_INCREMENT = 1;

INSERT INTO `menaxhimirezervimevehotele`.`administratoret` (`emri`, `mbiemri`, `gjinia`, `mosha`, `username`, `password`, `salt`, `COUNT`) 
VALUES ('Rinor', 'Rafuna', 'M', '21', 'rinor.rafuna', 'a18dcaed307ef07367542dc9b317118920c34aeb0cd546ea6f5f3c7bf836ed90632fc5ac7ba79d7005ca849a8f86702f3e67eb255254af0d33908c1d7dd11068', 'loUZXzZ1bVGHFvSAh79T', '0'),
('admin', 'admin', 'M', '21', 'admin', '7b680e7485882cd5949ffb56f26fc9d6f3289834bb62e5b8fdbcf980782aa67b261c6b22f4ae06f68d7417dd5014ed10cf00c08d5c898342806577bd4d177ee1', 'ERTs4TlJMMCaXYLPhdOf', '0'),
('admin', 'admin', 'M', '21', 'admin1', 'bc1f15734dac246dd393da1b4044d14a8987c6a9351720c1cecd35666476f814c4ab525d1fe4aeb70a2fdea3119f4e488b9f7020f0c5f518207577790a7d100f', 'v9JcTeet3q8mYfA6pXga', '0');

DELETE FROM administratoret
WHERE id=5;

create table tbl_cities 
(
qid int auto_increment,
city varchar(45),
cid int,
PRIMARY KEY (qid)
);

create table tbl_countries
(
cid int Primary key auto_increment,
country varchar(45)
);

create table tbl_guests
(
guest_id int Primary key auto_increment,
firstName varchar (45),
surname varchar (45),
gender varchar (10),
countryId int,
city varchar (45),
address varchar (45),
postalCode int,
email varchar (45),
phone varchar (45),
registrationTime datetime
);

create table tbl_reservations
(
rid int primary key auto_increment,
checkInDate date,
checkOutDate date,
nights int,
roomNumber int,
guest_id int,
Paid varchar(10),
reservationTime datetime
);

create table tbl_rooms
(
roomNumber int primary key,
roomType varchar (45),
floor int,
maxPersons int,
pricePerNight int,
available varchar (5)
);

INSERT INTO `menaxhimirezervimevehotele`.`tbl_cities` (`qId`, `City`, `cId`) 
VALUES
(1,'Prishtina',1),
(2,'Prizren',1),
(3,'Ferizaj',1),
(4,'Pejë',1),
(5,'Gjakovë',1),
(6,'Gjilan',1),
(7,'Mitrovicë',1),
(8,'Tirana',2),
(9,'Durrës',2),
(10,'Elbasan',2),
(11,'Vlora',2),
(12,'Shkodra',2),
(13,'Fier',2),
(14,'Berat',2),
(15,'Shkup',3),
(16,'Kumanova',3),
(17,'Tetova',3),
(18,'Gostivar',3),
(19,'Struga',3),
(20,'Ohri',3),
(21,'Dibra',3),
(22,'Podgorica',4),
(23,'Ulqin',4),
(24,'Tuz',4),
(25,'Tivar',4),
(26,'Plava',4),
(27,'Gusia',4),
(28,'Zurich',5),
(29,'Geneva',5),
(30,'Basel',5),
(31,'Bern',5),
(32,'Lausanne',5),
(33,'Winterthur',5),
(34,'Lucerne',5),
(35,'Berlin',6),
(36,'Hamburg',6),
(37,'Munich',6),
(38,'Sttutgart',6),
(39,'Dusseldorf',6),
(40,'Dortmund',6),
(41,'Leipzig',6);


INSERT INTO `menaxhimirezervimevehotele`.`tbl_countries` (`cId`, `Country`) 
VALUES
(1,'Kosovo'),
(2,'Albania'),
(3,'Macedonia'),
(4,'Montenegro'),
(5,'Switzerland'),
(6,'Germany');


INSERT INTO `menaxhimirezervimevehotele`.`tbl_rooms` (`RoomNumber`, `RoomType`, `Floor`, `maxPersons`, `PricePerNight`, `Available`) 
VALUES
(11,'Double',1,2,40,'YES'),
(12,'Family',1,3,50,'YES'),
(13,'Triple',1,3,45,'YES'),
(14,'Single',1,1,20,'NO'),
(15,'Single',1,1,20,'YES'),
(21,'Double',2,2,40,'YES'),
(22,'Family',2,3,50,'YES'),
(23,'Triple',2,3,45,'YES'),
(24,'KING',2,2,80,'YES'),
(25,'Single',2,1,20,'YES'),
(31,'Double',3,2,40,'YES'),
(32,'Family',3,3,50,'YES'),
(33,'Triple',3,3,45,'YES'),
(34,'KING',3,2,80,'YES'),
(35,'Single',3,1,20,'YES'),
(41,'Double',4,2,40,'YES'),
(42,'Family',4,3,50,'YES'),
(43,'Triple',4,3,45,'YES'),
(44,'Quad',4,4,60,'YES'),
(45,'Single',4,1,20,'YES'),
(51,'Double',5,2,40,'YES'),
(52,'Family',5,3,50,'YES'),
(53,'Triple',5,3,45,'YES'),
(54,'KING',5,2,120,'YES'),
(55,'Single',5,1,20,'YES'),
(61,'Double',6,2,40,'YES'),
(62,'Family',6,3,50,'YES'),
(63,'Triple',6,3,45,'YES'),
(64,'KING',6,2,150,'YES'),
(65,'Single',6,1,20,'YES');
