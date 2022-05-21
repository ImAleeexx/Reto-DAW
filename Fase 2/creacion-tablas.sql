drop table Calendario cascade constraints;
drop table Jornada cascade constraints;
drop table Partido cascade constraints;
drop table Equipo cascade constraints;
drop table Miembro cascade constraints;
drop table Dueno cascade constraints;
drop table Entrenador cascade constraints;
drop table Jugador cascade constraints;
drop table Perfil cascade constraints;


create table Calendario(
ID      INT PRIMARY KEY,
Nombre  varchar2(50)
);

create table Jornada(
ID                  INT PRIMARY KEY,
Fecha               date,
ID_Calendario       INT,
CONSTRAINT ID_Cal_FK foreign key (ID_Calendario)
references Calendario (ID)
);

create table Equipo(
ID                      INT PRIMARY KEY,
Nombre                  varchar2(50),
Pagina_Web              varchar2(50),
Patrocinador            varchar2(50),
ID_Calendario           INT,
CONSTRAINT ID_Calendario_FK foreign key (ID_Calendario)
references Calendario (ID)
);

create table Partido(
ID                      INT PRIMARY KEY,
ID_local                INT,
ID_visitante            INT,
Hora                    TIMESTAMP,
Resultado_local         int,           
Resultado_visitante     int,           
ID_Jornada              INT,
CONSTRAINT ID_Jornada_FK foreign key (ID_Jornada) 
references Jornada (ID),
CONSTRAINT local_equipo_FK foreign key (ID_local) 
references Equipo (ID),
CONSTRAINT visitante_equipo_FK foreign key (ID_visitante) 
references Equipo (ID)
);

create table Miembro(
ID               INT PRIMARY KEY,
DNI              VARCHAR2(8),
Nombre           VARCHAR2(30),
Telefono         INT,
Anios_pro		 INT,
ID_Equipo        INT,
CONSTRAINT ID_Equipo1_FK foreign key (ID_Equipo)
references Equipo (ID)
);

create table Dueno(
ID_D                  INT PRIMARY KEY,
CONSTRAINT ID_Persona1_FK foreign key (ID_D)
references Miembro (ID)
);

create table Entrenador(
ID_E                  INT PRIMARY KEY,
Sueldo 				 INT,
CONSTRAINT ID_Persona2_FK foreign key (ID_E)
references Miembro (ID)
);


create table Jugador(
ID_J                  INT PRIMARY KEY,
Nickname			varchar2(60),
Rol                 varchar2(60),
Sueldo				INT,
CONSTRAINT ID_Persona4_FK foreign key (ID_J)
references Miembro (ID)
);

create table Perfil(
ID                  INT PRIMARY KEY,
Ususario              varchar2(20),
Contrasena         varchar2(30),
Tipo                varchar2(20),
constraint tipe_ck check
(Tipo in('Adminstrador','Usuario'))
);

