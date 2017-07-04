--use master
--drop database FACTURACION

-- Base de Datos
create database FACTURACION 
go
use FACTURACION
go

create table usuario(
	idLogin char(15) not null,
	nombreUser varchar(80) not null,
	apellidoUser varchar(80) not null,
	contraseña varchar(12) not null,
	estado varchar(11) check(estado='Activo' or estado='Desactivado') not null,
	nivel varchar(10)  check(nivel='Empleado' or nivel='Supervisor') not null,
	cancelado char(5) default 'No' not null
)

alter table usuario
add primary key (idLogin)

create table evento(
	idevento int identity(1,1) not null,
	fecha date not null,
	hora datetime not null,
	loginUser char(15) not null,
	evento varchar(10) check(evento='Insertar' or evento='Modificar' or evento='Eliminar') not null,
	nombreTabla varchar(60) not null,
	cancelado char(5) default 'No' not null
)
alter table evento 
add primary key (idevento)



alter table evento
add foreign key (loginUser) references usuario(idLogin)

create table unidadMedida(
	idUnidadMedida int identity(1,1)  not null,
	nombreMedida varchar(60) not null,
	cancelado char(5) default 'No' not null
)

alter table unidadMedida
add primary key (idUnidadMedida)

create table categoria(
	idCategoria int identity(1,1) not null,
	nombreCategoria varchar(30) not null,
	abreviatura char(10) null,
	cancelado char(5) default 'No' not null
)
alter table categoria
add primary key (idCategoria)

create table producto(
	idProducto int identity(1,1) not null,
	nombreProducto varchar(80) not null,
	idUnidadMedida int not null,
	precioUnitario decimal(9,2) not null,
	idCategoria int not null,
	imagenProd image null,
	cancelado char(5) default 'No' not null
)
alter table producto
add primary key (idProducto)

alter table producto
add foreign key (idUnidadMedida) references unidadMedida(idUnidadMedida),
foreign key(idCategoria) references categoria(idCategoria)


create table cliente(
	idCliente char(11) not null,
	nombreCliente varchar(150) not null,
	direccion varchar(150) null,
	telefono varchar(15) null,
	correo varchar(25) null,
	cancelado char(5) default 'No' not null
)
alter table cliente
add primary key (idCliente)

create table opcionPago(
	idOpcionPago int identity(1,1) not null,
	nombreOpcionPago varchar(50) not null,
	cancelado char(5) default 'No' not null
)
alter table opcionPago
add primary key (idOpcionPago)


create table venta(
	idVenta int identity(1,1) not null,
	tipoDocumento varchar(7) check(tipodocumento='Boleta' or tipodocumento='Factura') not null,
	fecha date not null,
	hora datetime not null,
	empleado char(15) not null,
	nombreCliente char(11) not null,
	cancelado char(5) default 'No' not null
)
alter table venta
add primary key (idVenta)
alter table venta 
add foreign key (nombreCliente) references cliente(idCliente),
foreign key (empleado) references usuario(idLogin)

create table detallePago(
	idVenta int not null,
	idCliente char(11) not null,
	idOpcionPago int not null,
	montoPagar decimal(9,2) not null,
	CantLetras int null,
	comentario text null,
	cancelado char(5) default 'No' not null
)

alter table detallePago
add foreign key (idVenta) references venta(idVenta),
foreign key (idCliente) references cliente(idCliente),
foreign key (idOpcionPago) references opcionPago(idOpcionPago)

create table detalleVenta(
	idVenta int not null,
	cantidad int not null,
	nombreProducto int not null,
	precioUnidad decimal(9,2) not null,
	cancelado char(5) default 'No' not null
)
alter table detalleVenta
add foreign key (idVenta) references venta(idVenta),
foreign key (nombreProducto) references producto(idProducto)

go


/*
-- Insert Datos 

create table usuario(
	idLogin char(15) not null,
	nombreUser varchar(80) not null,
	apellidoUser varchar(80) not null,
	contraseña varchar(12) not null,
	estado varchar(11) check(estado='Activo' or estado='Desactivado') not null,
	nivel varchar(10)  check(nivel='Empleado' or nivel='Supervisor') not null
)
*/

insert usuario values ('jGonzales','Juan Diego','Gonzales Vega','contraseña','Activo','Supervisor','No')
insert usuario values ('aRios','Augusto Mario','Rios Molino','contraseña','Activo','Empleado','No')
insert usuario values ('cCastillo','Charles Andres','Castillo Risos','contraseña','Activo','Empleado','No')
insert usuario values ('jAquiles','Juan Oscar','Aquiles Amadeus','contraseña','Activo','Empleado','No')
insert usuario values ('fGomez','Fiorela Mireya','Gomez Savedra','contraseña','Desactivado','Empleado','No')


/*
-- create table evento
	idevento int not null,
	fechayHora date not null,
	loginUser char(15) not null,
	evento varchar(10) check(evento='Insertar' or evento='Modificar' or evento='Eliminar') not null
*/


/*
-- create table unidadMedida
	idUnidadMedida int not null,
	nombreMedida varchar(60) not null
*/

insert unidadMedida values ('Kilogramo','No')
insert unidadMedida values ('Litro','No')
insert unidadMedida values ('mlitro','No')
insert unidadMedida values ('gr','No')
insert unidadMedida values ('onzas','No')

insert unidadMedida values ('Metro','No')

/*
-- create table categoria
	idCategoria int not null,
	nombreCategoria varchar(30) not null,
	abreviatura char(10) null
*/
insert categoria values ('Bebidas','Beb','No')
insert categoria values ('Frutas y Verduras','FrutVerd','No')
insert categoria values ('Panadería y Dulces.','PanDul','No')
insert categoria values ('Carnes','Car','No')
insert categoria values ('Pescado/Marisco','PesMar','No')

insert categoria values ('Cereales','Cer','No')

/*
-- create table producto
	idProducto int not null,
	nombreProducto varchar(80) not null,
	idUnidadMedida int not null,
	precioUnitario decimal(9,2) not null,
	idCategoria int not null
*/

insert producto values ('Arroz CostaSur 1Kg',2,7.99,6,null,'No')
insert producto values ('Arroz CostaSur 5KG',1,4.19,6,null,'No')
insert producto values ('Aceite Andino 1Ltr',3,7.15,1,null,'No')
insert producto values ('Aceite Andino 1/2 Ltr',1,5.19,1,null,'No')
insert producto values ('Gaseosa LimaLimon 500ml',4,1.20,1,null,'No')


/*
create table cliente(
	idCliente char(11) not null,
	nombreCliente varchar(150) not null,
	direccion varchar(150) null,
	telefono varchar(15) null,
	correo varchar(25) null
)
*/
insert cliente values ('10445577881','Imprenta Jupiter','Francisco Paula Mz B #302','276 0124',null,'No')
insert cliente values ('20076565452','Notaria Sra. Eshter','Calle Lima #4510','788 2222',null,'No')
insert cliente values ('12345678','Erick Delgado Valencia',null,null,null,'No')
insert cliente values ('11223344','Ronald Garcia Miro',null,null,null,'No')
insert cliente values ('10203040','Elisa Flores Mamani',null,null,null,'No')

/*
create table venta
	idVenta int not null,
	tipoDocumento varchar(7) check(tipodocumento='Boleta' or tipodocumento='Factura') not null,
	fecha date not null,
	hora datetime not null,
	empleado char(15) not null,
	nombreCliente char(11) not null
*/
insert venta values('Boleta','25/01/2010', '25/01/2010 10:38:15','jGonzales','12345678','No')
insert venta values('Boleta','12/02/2010', '12/02/2010 11:15:15','aRios','10203040','No')
insert venta values('Boleta','15/03/2010', '15/03/2010 12:01:15','aRios','11223344','No')
insert venta values('Boleta','18/04/2010', '18/04/2010 10:22:15','jGonzales','12345678','No')
insert venta values('Boleta','22/05/2010', '22/05/2010 10:30:15','jGonzales','12345678','No')

insert venta values('Factura','14/06/2010', '14/06/2010 10:53:15','aRios','20076565452','No')
insert venta values('Boleta','18/07/2010', '18/07/2010 12:15:15','jGonzales','10203040','No')
insert venta values('Factura','30/08/2010', '30/08/2010 11:38:15','aRios','10445577881','No')
insert venta values('Boleta','18/09/2010', '18/09/2010 10:10:15','jGonzales','12345678','No')
insert venta values('Factura','12/10/2010', '12/10/2010 09:38:15','jGonzales','10445577881','No')

insert venta values('Factura','15/11/2010', '15/11/2010 05:18:15','aRios','10445577881','No')
insert venta values('Boleta','12/12/2010', '12/12/2010 10:08:15','jGonzales','11223344','No')
insert venta values('Factura','11/02/2011', '11/02/2011 09:40:10','aRios','20076565452','No')
insert venta values('Boleta','10/06/2011', '10/06/2011 02:00:01 pm','jGonzales','12345678','No')
insert venta values('Boleta','01/09/2011', '01/09/2011 01:01:10 am','aRios','10203040','No')

insert venta values('Boleta','25/12/2012', '25/12/2012 22:01:10','aRios','10203040','No')


/* table detalleVenta
	idVenta int not null,
	cantidad int not null,
	nombreProducto int not null,
	precioUnidad decimal(9,2) not null

	*/


insert opcionPago values('Efectivo','No')
insert opcionPago values('Tarjeta Credito','No')
insert opcionPago values('Debito Cuenta Corriente','No')


--Consulta de Venta por Fecha (Dia / Mes / Año)
go
create proc ventasPorFecha (@dia int,@mes int,@año int)
as
Select v.idVenta,v.tipoDocumento,v.fecha,v.hora,u.nombreUser +' '+u.apellidoUser Empleado,c.nombreCliente,v.cancelado from venta v 
inner join usuario u on v.empleado = u.idLogin
inner join cliente c on c.idCliente=v.nombreCliente
where YEAR(v.fecha)=@año and MONTH(v.fecha)=@mes and DAY(v.fecha)=@dia

--Consulta de Ventas por Mes / Año
go
create proc ventasporMes_Año(@mes int,@año int)
as
Select v.idVenta,v.tipoDocumento,v.fecha,v.hora,u.nombreUser +' '+u.apellidoUser Empleado,c.nombreCliente,v.cancelado from venta v 
inner join usuario u on v.empleado = u.idLogin
inner join cliente c on c.idCliente=v.nombreCliente
where year(v.fecha)=@año and MONTH(v.fecha)=@mes
--Consulta de Ventas por Bimestre / Año
go
create proc ventasporBi_Año(@bi int,@año int)
as
Select v.idVenta,v.tipoDocumento,v.fecha,v.hora,u.nombreUser +' '+u.apellidoUser Empleado,c.nombreCliente,v.cancelado from venta v 
inner join usuario u on v.empleado = u.idLogin
inner join cliente c on c.idCliente=v.nombreCliente
where YEAR(v.fecha)=@año and MONTH(v.fecha) between @bi*2-1 and @bi*2

--Consulta de Ventas por Trimestre / Año
go
create proc ventasPorTrim_Año(@tri int,@año int)
as
Select v.idVenta,v.tipoDocumento,v.fecha,v.hora,u.nombreUser +' '+u.apellidoUser Empleado,c.nombreCliente,v.cancelado from venta v 
inner join usuario u on v.empleado = u.idLogin
inner join cliente c on c.idCliente=v.nombreCliente
where YEAR(v.fecha)=@año and MONTH(v.fecha) between @tri*3-2 and @tri*3

--Consulta de Ventas por Semestre / Año
go
create proc ventasPorSem_Año(@sem int,@año int)
as
Select v.idVenta,v.tipoDocumento,v.fecha,v.hora,u.nombreUser +' '+u.apellidoUser Empleado,c.nombreCliente,v.cancelado from venta v 
inner join usuario u on v.empleado = u.idLogin
inner join cliente c on c.idCliente=v.nombreCliente
where YEAR(v.fecha)=@año and MONTH(v.fecha) between @sem*6-5 and @sem*6
--Consulta de Ventas por Cliente (nombre o Id)
go
create proc ventasPorCliente(@cod char(11))
as 
Select v.idVenta,v.tipoDocumento,v.fecha,v.hora,u.nombreUser +' '+u.apellidoUser Empleado,c.nombreCliente,v.cancelado from venta v 
inner join usuario u on v.empleado = u.idLogin
inner join cliente c on c.idCliente=v.nombreCliente
where v.nombreCliente=@cod


go

/*
select p.idProducto,p.nombreProducto,um.nombreMedida,p.precioUnitario,c.nombreCategoria from producto p
inner join categoria c on c.idCategoria=p.idCategoria
inner join unidadMedida um on um.idUnidadMedida=p.idUnidadMedida
where p.idUnidadMedida=1

*/




/*
exec ventasporBi_Año 1,2010
exec ventasPorTrim_Año 1,2010
exec ventasPorSem_Año 1,2010
exec ventasporMes_Año 01,2010
exec ventasPorFecha 25,01,2010
exec ventasPorCliente 10203040


select * from venta

*/










