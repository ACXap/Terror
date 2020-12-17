-- Drop table

-- DROP TABLE cdi.terror_person;

CREATE TABLE cdi.terror_person (
	id serial NOT NULL, -- Идентификатор записи террориста
	"name" varchar NULL, -- Имя терориста
	id_new int4 NULL, -- Идентификатор записи террориста из источника
	person_type_id int4 NULL, -- Идентификатор типа террориста
	is_terrorist int4 NULL, -- Это террорист?
	inn varchar NULL, -- ИНН
	birth_date varchar NULL, -- Дата рождения террориста
	description varchar NULL, -- Описание террориста
	address varchar NULL, -- Адрес террориста
	terrorist_resolution varchar NULL, -- Видимо кто и как решили, что это террорист
	birth_place varchar NULL, -- Место рождения террориста
	passport varchar NULL, -- Паспорт террориста
	changedate date NULL DEFAULT CURRENT_DATE -- Дата создания записи в MDS
);
COMMENT ON TABLE cdi.terror_person IS 'Таблица террористов';

-- Column comments

COMMENT ON COLUMN cdi.terror_person.id IS 'Идентификатор записи террориста';
COMMENT ON COLUMN cdi.terror_person."name" IS 'Имя терориста';
COMMENT ON COLUMN cdi.terror_person.id_new IS 'Идентификатор записи террориста из источника';
COMMENT ON COLUMN cdi.terror_person.person_type_id IS 'Идентификатор типа террориста';
COMMENT ON COLUMN cdi.terror_person.is_terrorist IS 'Это террорист?';
COMMENT ON COLUMN cdi.terror_person.inn IS 'ИНН';
COMMENT ON COLUMN cdi.terror_person.birth_date IS 'Дата рождения террориста';
COMMENT ON COLUMN cdi.terror_person.description IS 'Описание террориста';
COMMENT ON COLUMN cdi.terror_person.address IS 'Адрес террориста';
COMMENT ON COLUMN cdi.terror_person.terrorist_resolution IS 'Видимо кто и как решили, что это террорист';
COMMENT ON COLUMN cdi.terror_person.birth_place IS 'Место рождения террориста';
COMMENT ON COLUMN cdi.terror_person.passport IS 'Паспорт террориста';
COMMENT ON COLUMN cdi.terror_person.changedate IS 'Дата создания записи в MDS';
