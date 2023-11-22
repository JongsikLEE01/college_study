create table if not exists product(
	p_id varchar(10) not null,
	p_name varchar(10),
	p_unitPrice integer,
	p_description TEXT,
	p_category varchar(20),
	p_manufacturer varchar(20),
	p_unitsInStock LONG,
	p_condition varchar(20),
	p_fileName varchar(20),
	primary key (p_id)
)DEFAULT CHARSET=utf8;