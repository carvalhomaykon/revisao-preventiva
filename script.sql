DROP SCHEMA IF EXISTS revisao_preventiva;
CREATE SCHEMA revisao_preventiva;
USE revisao_preventiva;

create table users(
	id BIGINT primary key auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    senha varchar(100) not null,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table autidoria_users(
	id BIGINT primary key auto_increment,
    user_id BIGINT,
    nome varchar(100) not null,
    email varchar(100) not null,
    senha varchar(100) not null,
    operacao varchar(10),
    modificado_em timestamp default current_timestamp,
    usuario varchar(100)
);

create table veiculos(
	id BIGINT primary key auto_increment,
    user_id BIGINT,
    tipo varchar(45), -- Carro, moto
    modelo varchar(100),
    quilometragem decimal(10,2),
    quilometragem_diaria decimal(10,2),
    dias_uso_semana int,
    ultima_revisao date,
    ano_fabricacao int,
    criado_em timestamp default current_timestamp,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key (user_id) references users(id) on delete cascade on update cascade
);

create table autidoria_veiculos(
	id BIGINT primary key auto_increment,
    user_id BIGINT,
    tipo varchar(45), -- Carro, moto
    modelo varchar(100),
    quilometragem decimal(10,2),
    quilometragem_diaria decimal(10,2),
    dias_uso_semana int,
    ultima_revisao date,
    ano_fabricacao int,
    operacao varchar(10),
    modificado_em timestamp default current_timestamp,
    usuario varchar(100)
);

create table revisoes(
	id BIGINT primary key auto_increment,
    veiculo_id BIGINT,
    status_revisao varchar(100),
    data_prevista_revisao date,
    km_fazer_revisao decimal(10,2),
    foreign key (veiculo_id) references veiculos(id) on delete cascade on update cascade
);

create table autidoria_revisoes(
	id BIGINT primary key auto_increment,
    veiculo_id BIGINT,
    status_revisao varchar(100),
    data_prevista_revisao date,
    km_fazer_revisao decimal(10,2),
    operacao varchar(10),
    modificado_em timestamp default current_timestamp,
    usuario varchar(100)
);

-- TRIGGER USERS
DELIMITER $$
create trigger after_insert_user
	after insert on users
	for each row begin
    insert into autidoria_users(user_id, nome, email, senha, operacao, usuario)
		values (new.id, new.nome, new.email, new.senha, 'INSERT', user());
	end $$
DELIMITER ;

DELIMITER $$
create trigger after_update_user
	after update on users
	for each row begin
    insert into autidoria_users(user_id, nome, email, senha, operacao, usuario)
		values (new.id, new.nome, new.email, new.senha, 'UPDATE', user());
	end $$
DELIMITER ;

DELIMITER $$
create trigger after_delete_user
	after delete on users
	for each row begin
    insert into autidoria_users(user_id, nome, email, senha, operacao, usuario)
		values (old.id, old.nome, old.email, old.senha, 'DELETE', user());
	end $$
DELIMITER ;

-- ####################################################################

-- TRIGGER VEICULOS
DELIMITER $$
create trigger after_insert_veiculos
	after insert on veiculos
	for each row begin
    insert into autidoria_veiculos
		(user_id, tipo, modelo, quilometragem, quilometragem_diaria, dias_uso_semana, ultima_revisao, ano_fabricacao, operacao, usuario)
		values (new.user_id, new.tipo, new.modelo, new.quilometragem, new.quilometragem_diaria, new.dias_uso_semana, new.ultima_revisao, new.ano_fabricacao,'INSERT', user());
	
		call getDataPrevistaRevisao(new.id);
	end $$
DELIMITER ;

DELIMITER $$
create trigger after_update_veiculos
	after update on veiculos
	for each row 
    begin
		declare km_revisao decimal(10,2);
        declare novo_status varchar(50);
        declare data_prevista date;
        declare id_ultima_revisao INT;
		
        -- Auditoria
		insert into autidoria_veiculos
			(user_id, tipo, modelo, quilometragem, quilometragem_diaria, dias_uso_semana, ultima_revisao, ano_fabricacao, operacao, usuario)
			values (new.user_id, new.tipo, new.modelo, new.quilometragem, new.quilometragem_diaria, new.dias_uso_semana, new.ultima_revisao, new.ano_fabricacao,'UPDATE', user());
			
		select id, data_prevista_revisao, km_fazer_revisao
		into id_ultima_revisao, data_prevista, km_revisao
		from revisoes
		where veiculo_id = new.id
		order by id desc
		limit 1;
		
        if id_ultima_revisao is not null then
			call getStatus(new.id, km_revisao, novo_status, data_prevista);
			
			update revisoes
			set status_revisao = novo_status
			where id = id_ultima_revisao;
		end if;
        
        if novo_status = 'Revisão Feita' then
			call getDataPrevistaRevisao(new.id);
		end if;
	end $$
DELIMITER ;

DELIMITER $$
create trigger after_delete_veiculos
	after delete on veiculos
	for each row begin
    insert into autidoria_veiculos
		(user_id, tipo, modelo, quilometragem, quilometragem_diaria, dias_uso_semana, ultima_revisao, ano_fabricacao, operacao, usuario)
		values (old.user_id, old.tipo, old.modelo, old.quilometragem, old.quilometragem_diaria, old.dias_uso_semana, old.ultima_revisao, old.ano_fabricacao, 'DELETE', user());
	end $$
DELIMITER ;

-- TRIGGER Revisões
DELIMITER $$
create trigger after_insert_revisoes
	after insert on revisoes
	for each row begin
    insert into autidoria_revisoes(veiculo_id, status_revisao, data_prevista_revisao, km_fazer_revisao, operacao, usuario)
		values (new.veiculo_id, new.status_revisao, new.data_prevista_revisao, new.km_fazer_revisao, 'INSERT', user());
	end $$
DELIMITER ;

DELIMITER $$
create trigger after_update_revisoes
	after update on revisoes
	for each row begin
    insert into autidoria_revisoes(veiculo_id, status_revisao, data_prevista_revisao, km_fazer_revisao, operacao, usuario)
		values (new.veiculo_id, new.status_revisao, new.data_prevista_revisao, new.km_fazer_revisao, 'UPDATE', user());
	end $$
DELIMITER ;

DELIMITER $$
create trigger after_delete_revisoes
	after delete on revisoes
	for each row begin
    insert into autidoria_revisoes(veiculo_id, status_revisao, data_prevista_revisao, km_fazer_revisao, operacao, usuario)
		values (old.veiculo_id, old.status_revisao, old.data_prevista_revisao, old.km_fazer_revisao, 'DELETE', user());
	end $$
DELIMITER ;

-- ####################################################################

-- PROCEDURES
-- Calcular a data prevista para revisão
DELIMITER $$
	create procedure getDataPrevistaRevisao(in veiculo_id int)
    begin
		declare auxKmDia decimal(10,2);
        declare auxDiasUso int;
        declare km_semanal decimal(10,2);
        declare semanas decimal(10,4);
        declare dias_corridos int;
        declare km_meta int default 10000;
        declare auxAnoFabri int;
        declare anos_uso int;
        declare data_prevista date;
        declare auxUltimaRevisao date;
        declare dias_restantes int;
        
        -- Buscar veículo
        select quilometragem_diaria, dias_uso_semana, ano_fabricacao, ultima_revisao
        into auxKmDia, auxDiasUso, auxAnoFabri, auxUltimaRevisao
        from veiculos
        where id = veiculo_id;
        
        set anos_uso = year(curdate()) - auxAnoFabri;
        if anos_uso > 5 then
			set km_meta = 6000;
		end if;
        
        set dias_corridos = ceil(km_meta / auxKmDia);
        set semanas = floor(dias_corridos / auxDiasUso);
        set dias_restantes = dias_corridos % auxDiasUso;
        set dias_corridos = (semanas * 7) + dias_restantes;
        
        set data_prevista = date_add(auxUltimaRevisao, interval dias_corridos day);
        
        call getKmFazerRevisao(veiculo_id,@km_fazer_revisao);
        call getStatus(veiculo_id, @km_fazer_revisao, @status_revisao, data_prevista);
        
		insert into revisoes(veiculo_id, status_revisao, data_prevista_revisao, km_fazer_revisao) 
			values (veiculo_id, @status_revisao, data_prevista, @km_fazer_revisao);
	END $$
DELIMITER ;

DELIMITER $$
    -- Verificar quilometragem
    create procedure getKmFazerRevisao(
		in veiculo_id int, 
        out km_fazer_revisao int
	)
    begin
		declare auxKm decimal(10,2);
        declare kmMeta int default 10000;
        declare AuxTipo varchar(50);
        declare auxAnoFabri int;
        declare anos_uso int;
	
        select quilometragem, tipo, ano_fabricacao
        into auxKm, AuxTipo, auxAnoFabri 
        from veiculos
        where id = veiculo_id;
        
        set anos_uso = year(curdate()) - auxAnoFabri;
        
		if anos_uso > 5 then
			set KmMeta = 6000;
		end if;
		
		set km_fazer_revisao = kmMeta + auxKm;
		
	END $$
DELIMITER ;

DELIMITER $$
	create procedure getStatus(
		in id_veiculo int,
		in km_fazer_revisao decimal(10,2), 
        out status_revisao varchar(50),
        in data_prevista date
    )
    begin
		declare auxKm decimal(10,2);
        declare auxUltimaRevisao date;
		
        select quilometragem, ultima_revisao into auxKm, auxUltimaRevisao
        from veiculos
        where id = id_veiculo;
        
        if auxUltimaRevisao >= data_prevista then
			set status_revisao = 'Revisão Feita';
        
        elseif auxKm > km_fazer_revisao or auxUltimaRevisao > data_prevista then
			set status_revisao = 'Revisão em Atraso';
            
        else
			set status_revisao = 'Revisão Em Dia';
        end if;
        
    END $$
DELIMITER ;

