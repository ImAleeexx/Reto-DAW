drop table Calendario cascade constraints;
drop table Jornada cascade constraints;
drop table Partido cascade constraints;
drop table Equipo cascade constraints;
drop table Miembro cascade constraints;
drop table Dueno cascade constraints;
drop table Entrenador cascade constraints;
drop table Jugador cascade constraints;
drop table Usuario cascade constraints;
drop table Administrador cascade constraints;

create table Calendario(
ID      INT PRIMARY KEY,
Nombre  varchar2(50)
);

create table Jornada(
ID                  INT PRIMARY KEY,
Nombre              varchar2(50),
Fecha               date,
ID_Calendario       INT,
CONSTRAINT ID_Cal_FK foreign key (ID_Calendario)
references Calendario (ID)
);

create table Equipo(
ID                      INT PRIMARY KEY,
Nombre                  varchar2(50),
Pagina_Web              varchar2(50),
Puntos                  INT,
ID_Calendario           INT,
CONSTRAINT ID_Calendario_FK foreign key (ID_Calendario)
references Calendario (ID)
);

create table Partido(
ID                      INT PRIMARY KEY,
Nombre                  varchar2(50),
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

create table Personas(
ID                  INT PRIMARY KEY,
Nombre              varchar2(20),
Nick                VARCHAR2(15),
Sueldo              NUMBER,
ID_Equipo           INT,
CONSTRAINT ID_Equipo1_FK foreign key (ID_Equipo)
references Equipo (ID)
);

create table Dueino(
IDD                  INT PRIMARY KEY,
Anios_Dueino        INT,
CONSTRAINT ID_Persona1_FK foreign key (IDD)
references Personas (ID)
);

create table Entrenador(
IDE                  INT PRIMARY KEY,
mail                VARCHAR2(25),
CONSTRAINT ID_Persona2_FK foreign key (IDE)
references Personas (ID)
);

create table Asistente(
IDA                  INT PRIMARY KEY,
Anios_Asistente     INT,
CONSTRAINT ID_Persona3_FK foreign key (IDA)
references Personas (ID)
);

create table Jugador(
IDJ                  INT PRIMARY KEY,
Rol                 varchar2(60),
CONSTRAINT ID_Persona4_FK foreign key (IDJ)
references Personas (ID)
);

create table Usuarios(
ID                  INT PRIMARY KEY,
Nombre              varchar2(20),
Contrasena         varchar2(30),
tipo                varchar2(20),
constraint tipe_ck check
(tipo in('Adminstrador','Usuario'))
);

