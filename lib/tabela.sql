CREATE TABLE cliente 
	(id_cliente serial,
    nome varchar (50),
    cpf varchar(14) ,
    endereco varchar(25),
    email varchar (30) ,
    telefone varchar (15),
	primary key (id_cliente));
	
CREATE TABLE agenda (
    data date,
    imovel varchar,
    id_cliente int,
    foreign key(id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE
);

CREATE TABLE imovel
 	(id_imovel serial,
	 tipo_imovel varchar(30),
	 endereco varchar (30),
	 valor_Aluguel decimal(10, 2),
	 status_imovel varchar(50),
	 id_cliente int,
	primary key (id_imovel),
	foreign key (id_cliente) references cliente (id_cliente) ON DELETE CASCADE);

CREATE TABLE Contratos
    (id serial,
	data_inicio date,
	data_fim date,
	valor Numeric(10,2),
	status varchar,
	ativo varchar not null,
	primary key (id));