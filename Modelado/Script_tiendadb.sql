/*
 * Script BBDD tienda_animales
 * */

create database tiendaDB;
use tiendaDB;

-----------------------------------------------------

/* USUARIO */
/*drop table usuario;*/

CREATE TABLE usuario (
	codigo_usuario BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	codigo_rol BIGINT NOT NULL,
	codigo_metodo_pago BIGINT NOT NULL,
	dni VARCHAR(20) UNIQUE NOT NULL,
	nombre VARCHAR(100) DEFAULT '',
	apellidos VARCHAR(100) DEFAULT '', 
	telefono VARCHAR(15) DEFAULT '',
	fecha_nacimiento DATE,
	contrasenia VARCHAR(100) DEFAULT '',
	email VARCHAR(100) DEFAULT ''
) engine=InnoDB charset=UTF8MB4;

/* ROL */
-- drop table rol
 
CREATE TABLE rol (
	codigo_rol BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(10) DEFAULT '',
	descripcion VARCHAR(100) DEFAULT ''
) engine=InnoDB charset=UTF8MB4;


/* DIRECCION */
-- drop table direccion
  
CREATE TABLE direccion (
	codigo_direccion BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	codigo_tipo_direccion BIGINT NOT NULL,
	codigo_postal VARCHAR(5) NOT null default '00000',
	localidad VARCHAR(100) DEFAULT '',
	comunidad VARCHAR(100),
	direccion VARCHAR(200) not null DEFAULT '',
	numero INT default null,
	escalera VARCHAR(10) DEFAULT '',
	piso VARCHAR(10) DEFAULT '',
	letra VARCHAR(1) DEFAULT ''
)engine=InnoDB charset=UTF8MB4;


/* TIPO_DIRECCION */
-- drop table tipo_direccion
  
CREATE TABLE tipo_direccion (
	codigo_tipo_direccion BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) DEFAULT '',
	descripcion VARCHAR(100) DEFAULT ''
)engine=InnoDB charset=UTF8MB4;

/* PEDIDO */
-- drop table pedido

CREATE TABLE pedido (
	codigo_pedido BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	codigo_metodo_pago BIGINT NOT NULL,
	codigo_usuario BIGINT NOT NULL,
	codigo_direccion BIGINT NOT NULL,
	importe_total DECIMAL(9,2) DEFAULT 0.0,
	peso_total DECIMAL(9,2) DEFAULT 0.0,
	fecha_pedido DATETIME,
	fecha_estimada_entrega DATETIME,
	fecha_modificacion DATETIME 
) engine=InnoDB charset=UTF8MB4;

/* PEDIDOS_ESTADOS */
-- drop table pedidos_estados
  
CREATE TABLE pedidos_estados (
	codigo_pedido BIGINT NOT NULL,
	codigo_estado_pedido BIGINT NOT NULL,
	codigo_usuario BIGINT NOT NULL,
	fecha_cambio_estado DATETIME
)engine=InnoDB charset=UTF8MB4;

--Indices de la tabla "pedidos_estados"

alter table  pedidos_estados add primary key pk_pedidos_estados (codigo_estado_pedido, codigo_pedido, codigo_usuario);

/* ESTADO_PEDIDO */
-- drop table estado_pedido
  
CREATE TABLE estado_pedido (
	codigo_estado_pedido BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(2) DEFAULT '',
	descripcion VARCHAR(100) DEFAULT ''
)engine=InnoDB charset=UTF8MB4;

/* METODO_PAGO */
-- drop table metodo_pago

CREATE TABLE metodo_pago (
	codigo_metodo_pago BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(10) NOT NULL,
	descripcion VARCHAR(100) DEFAULT ''
)engine=InnoDB charset=UTF8MB4

/* PEDIDOS_ARTICULOS */
-- drop table pedidos_articulos

CREATE TABLE pedidos_articulos (
	codigo_articulo BIGINT NOT NULL,
	codigo_pedido BIGINT NOT NULL,
	cantidad INT default 0
)engine=InnoDB charset=UTF8MB4

--Indice tabla "pedidos_articulos"

ALTER TABLE pedidos_articulos ADD CONSTRAINT PK_pedidos_articulos PRIMARY KEY (codigo_articulo, codigo_pedido);

/* ARTICULO */
-- drop table articulo
  
CREATE TABLE articulo (
	codigo_articulo BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	codigo_especie_animal BIGINT NOT NULL,
	nombre VARCHAR(100) default '',
	descripcion VARCHAR(100) default '',
	precio DECIMAL(9,2) default 0.0,
	peso_unitario DECIMAL(9,2) default 0.0
)engine=InnoDB charset=UTF8MB4

/* ESPECIE_ANIMAL */
-- drop table especie_animal
  
CREATE TABLE especie_animal (
	codigo_especie_animal BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) default '',
	descripcion VARCHAR(100) default ''
)engine=InnoDB charset=UTF8MB4;

-------------------------------------------------

--Filtros para la tabla "usuario" 

ALTER TABLE usuario ADD FOREIGN KEY (codigo_rol) REFERENCES rol (codigo_rol);

ALTER TABLE usuario ADD FOREIGN KEY (codigo_metodo_pago) REFERENCES metodo_pago (codigo_metodo_pago);
  
  
-------------------------------------------------

  --Filtros para la tabla "direccion" 

ALTER TABLE direccion  ADD FOREIGN KEY (codigo_tipo_direccion) REFERENCES tipo_direccion (codigo_tipo_direccion);

ALTER TABLE direccion  ADD FOREIGN KEY (codigo_usuario) REFERENCES usuario (codigo_usuario);
  
--------------------------------------------------
  
  --Filtros para la tabla "pedido" 

ALTER TABLE pedido  ADD FOREIGN KEY (codigo_metodo_pago) REFERENCES metodo_pago (codigo_metodo_pago);

ALTER TABLE pedido  ADD FOREIGN KEY (codigo_usuario) REFERENCES usuario (codigo_usuario);
 
ALTER TABLE pedido  ADD FOREIGN KEY (codigo_direccion) REFERENCES direccion (codigo_direccion);
  
------------------------------------------------

--Filtros para la tabla "pedidos_estados" 

ALTER table pedidos_estados  ADD FOREIGN KEY (codigo_pedido) REFERENCES pedido (codigo_pedido);
  
ALTER TABLE pedidos_estados  ADD FOREIGN KEY (codigo_estado_pedido) REFERENCES estado_pedido (codigo_estado_pedido);
  
ALTER TABLE pedidos_estados  ADD FOREIGN KEY (codigo_usuario) REFERENCES usuario (codigo_usuario);
  
------------------------------------------------  
  


--Filtros para la tabla "pedidos_articulos" 
ALTER TABLE pedidos_articulos  ADD FOREIGN KEY (codigo_pedido) REFERENCES pedido (codigo_pedido);
  
ALTER TABLE pedidos_articulos  ADD FOREIGN KEY (codigo_articulo) REFERENCES articulo (codigo_articulo);
  
------------------------------------------------

--Filtros para la tabla "articulo" 

ALTER TABLE articulo  ADD FOREIGN KEY (codigo_especie_animal) REFERENCES especie_animal (codigo_especie_animal);
  