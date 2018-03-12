create database if not exists pointDB;


use pointDB;
create table if not exists tb_point (
	id int(11) auto_increment,
    nm_poi varchar(100) not null,
    x_coordinate int(11) not null,
    y_coordinate int(11) not null,
    primary key (id)
);