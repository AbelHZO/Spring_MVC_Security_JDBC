CREATE DATABASE IF NOT EXISTS SpringDB;

CREATE TABLE IF NOT EXISTS Usuarios(
	idUser VARCHAR(30) NOT NULL PRIMARY KEY,
	usuario VARCHAR(70) NOT NULL,
	password VARCHAR(80) NOT NULL,
	email VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS Roles(
	idRol VARCHAR(30) NOT NULL PRIMARY KEY,
	rol VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS User_Roles(
	idUser VARCHAR(30) NOT NULL,
	idRol VARCHAR(30) NOT NULL,
	FOREIGN KEY(idUser) REFERENCES Usuarios(idUser),
	FOREIGN KEY(idRol) REFERENCES Roles(idRol),
	PRIMARY KEY(idUser, idRol)
);

/*Insertar un usuario.*/

INSERT INTO Usuarios(idUser, usuario, password, email) VALUES(UNIX_TIMESTAMP() ,"Abel HZO", "$2a$12$mWsCZBgLod26jpRiVx6OsudarXMnLXpMtSXPePfkv2eYvm/pMdnZW", "abel26.hernandez@gmail.com");

UPDATE Usuarios SET usuario = ?, password = ?, email = ? WHERE idUser = ?;

/*Insertar Roles*/

INSERT INTO Roles(idRol, rol) VALUES(UNIX_TIMESTAMP() ,"ROLE_ADMIN");

UPDATE Roles SET rol = ? WHERE idRol = ?;

/*Insertar roles a los usuarios*/

INSERT INTO User_Roles(idUser, idRol) VALUES(?, ?);

/*Listar Todos los Usuarios*/

SELECT * FROM Usuarios;

/*Para el Login de Spring*/

SELECT * FROM Usuarios WHERE usuario = "Abel HZO";

SELECT r.* FROM User_Roles ur, Roles r WHERE r.idRol = ur.idUser AND idUser = "?";

/*Listar Roles en Select*/

SELECT * FROM Roles;