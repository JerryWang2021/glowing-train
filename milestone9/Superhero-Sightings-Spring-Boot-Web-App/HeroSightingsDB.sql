DROP DATABASE IF EXISTS heroSightingsDB;
CREATE DATABASE heroSightingsDB;

USE heroSightingsDB;

CREATE TABLE superpower(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`description` VARCHAR(255)
);

CREATE TABLE hero(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`description` VARCHAR(255),
superpowerId INT NOT NULL,
FOREIGN KEY (superpowerId) REFERENCES superpower(id)
);

CREATE TABLE organization(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`description` VARCHAR(255),
`isMember` BOOLEAN NOT NULL,
`address` VARCHAR(255),
`contact` VARCHAR(255)
);

CREATE TABLE hero_organization(
heroId INT NOT NULL,
organizationId INT NOT NULL,
PRIMARY KEY(heroId, organizationId),
FOREIGN KEY (heroId) REFERENCES hero(id),
FOREIGN KEY (organizationId) REFERENCES organization(id)
);

CREATE TABLE location(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`description` VARCHAR(255),
`address` VARCHAR(255),
`latitude` DECIMAL(9,7) NOT NULL,
`longitude` DECIMAL(10,7) NOT NULL
);

CREATE TABLE sighting(
id INT PRIMARY KEY AUTO_INCREMENT,
`time` DATE NOT NULL,
heroId INT NOT NULL,
locationId INT NOT NULL,
FOREIGN KEY(heroId) REFERENCES hero(id),
FOREIGN KEY(locationId) REFERENCES location(id)
);
