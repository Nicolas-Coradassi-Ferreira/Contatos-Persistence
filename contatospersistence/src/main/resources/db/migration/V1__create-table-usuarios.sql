CREATE TABLE usuarios(
id bigint not null auto_increment,
nome varchar(255) not null unique,
email varchar(255) not null unique,
senha varchar(255) not null,
primary key(id)
);