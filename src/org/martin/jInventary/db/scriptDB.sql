/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  martin
 * Created: 04-01-2017
 */
drop database if exists dbInventario;
create database dbInventario;
use dbInventario

create table userType(
    id tinyint unsigned not null auto_increment primary key,
    name varchar(30) not null
);

insert into userType values(null, 'Administrador');

create table user(
    id int unsigned not null auto_increment primary key,
    idType tinyint unsigned not null,
    name varchar(50),
    nick varchar(30) not null,
    email varchar(40) not null,
    password blob,
    enabled bit,
    foreign key(idType) references userType(id)
);

insert into user values(null, 1, 'Pablo Pizarro Acevedo', 'pepa2305', 'pablopizarroa@gmail.com', (AES_ENCRYPT('powerx7', '1234')), 1);

create table productType(
    id tinyint unsigned not null auto_increment primary key,
    name varchar(40) not null,
    enabled bit not null
);

insert into productType values(null, 'Sin Tipo', 1);
insert into productType values(null, 'Artículo de aseo', 1);
insert into productType values(null, 'Ingrediente de repostería', 1);
insert into productType values(null, 'Elemento de repostería', 1);
insert into productType values(null, 'Ingrediente cocina', 1);
insert into productType values(null, 'Alimento', 1);

create table eventType(
    id tinyint unsigned not null auto_increment primary key,
    name varchar(40) not null
);

insert into eventType values(null, 'Registro producto');
insert into eventType values(null, 'Recepción de producto');
insert into eventType values(null, 'Consumo');
insert into eventType values(null, 'Cambio de Stock');
insert into eventType values(null, 'Eliminacion producto');
insert into eventType values(null, 'Deshabilitación producto');
insert into eventType values(null, 'Actualización producto');
insert into eventType values(null, 'Registro usuario');
insert into eventType values(null, 'Habilitación Usuario');
insert into eventType values(null, 'Deshabilitación Usuario');
insert into eventType values(null, 'Cambio de clave');


create table product(
    id int unsigned not null auto_increment primary key,
    name varchar(40),
    idType tinyint unsigned not null,
    quantity int unsigned not null,
    minQuantity int unsigned not null,
    enabled bit not null,
    foreign key(idType) references productType(id)
);

insert into product values(null, 'Lavaloza Quix 1L', 1, 1, 1, 1);
insert into product values(null, 'Shampoo Ballerina 1L', 1, 1, 1, 1);
insert into product values(null, 'Manjar Nestle 1K', 2, 10, 1, 1);
insert into product values(null, 'Saco Harina 50K', 2, 1, 1, 1);
insert into product values(null, 'Azucar Iansa 1L', 4, 5, 1, 1);

/* Considerar más adelante si se desea agregar un precio
a un producto la idea es hacer otra tabla
aparte con productos que si tendran precio
y al momento de registrar validad el tipo para
saber en que tabla se deben registrar */

create table eventHistoryProduct(
    id int unsigned not null auto_increment primary key,
    idType tinyint unsigned,
    datetime datetime not null,
    idProduct int unsigned,
    foreign key(idType) references eventType(id),
    foreign key(idProduct) references product(id)
);

create table eventHistoryUser(
    id int unsigned not null auto_increment primary key,
    idType tinyint unsigned,
    datetime datetime not null,
    idUser int unsigned,
    foreign key(idType) references eventType(id),
    foreign key(idUser) references user(id)
);


/*--------------------Vistas--------------------*/
-- 1.- Ver todos los productos habilitados ordenados
-- por tipo alfabéticamente.
create view productView as
select p.id, p.name, t.name as type, p.quantity, p.minQuantity
from product p, productType t
where p.idType = t.id and p.enabled = 1 order by t.name asc;
/*--------------------Vistas--------------------*/

delimiter //
create trigger dbInventario.trAddEvent
after insert on dbInventario.product
for each row
begin
    insert into dbInventario.eventHistory values(null, 1, (select now());
end//
delimiter ;


delimiter //

create function isRightLogin(_user varchar(40), _passw varchar(30)) returns bit
begin
    return (select count(id) from user where (nick = _user or email = _user) and 
    (AES_ENCRYPT('powerx7', _passw) = password)) > 0;
end//


delimiter ;