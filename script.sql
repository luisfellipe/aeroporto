create database aeroporto;

create table aviao(
	codaviao int primary key,
	qtdassentos int not null,
	marca varchar(45),
	modelo varchar(45)
);

create table voo(
	codvoo int primary key,
	codaviao int not null,
	origem varchar(45),
	destino varchar(45),
	datasaida varchar(45),
	datachegada varchar(45),
    foreign key (codaviao) references aviao(codaviao)
);

create table assento(
	codassento int primary key,
	codaviao int not null,
	codvoo int not null,
	reservado bool not null,
     foreign key (codaviao) references aviao(codaviao),
	foreign key (codvoo) references voo(codvoo)
);

create table reserva(
	codvoo int  primary key,
	codassento int not null,
	codreserva int  not null,
	cpf varchar(15),
    foreign key (codvoo) references voo(codvoo),
    foreign key (codassento) references assento(codassento)
);
