create table topicos (
    id bigint not null auto_increment,
    titulo varchar(500) unique not null,
    mensaje longtext not null,
    fecha datetime not null,
    status varchar(100) not null,
    autor varchar(200) not null,
    curso varchar(200) not null,

    primary key(id)
);