create database aeroporto;
create table reserva(
	int cod_voo,
	in cod_assento,
	int codreserva,
	varchar cpf
);

create table aviao(
	int codaviao,
	int qtd_assentos,
	varchar marca,
	varchar modelo
	
);

create table assentos(,
	int codassento,
	int codaviao,
	bool reservado,
);