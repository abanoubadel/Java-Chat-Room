-- account taple
create table account (
    account_id serial constraint account_id_pk primary key ,
    
    first_name varchar(100) constraint account_first_name_nn not null ,
    last_name varchar(100)  constraint account_last_name_nn not null,
    user_name varchar(100) constraint account_user_name_nn not null constraint account_user_name_uq unique,
    password varchar(20) constraint account_password_nn not null,
    email varchar(100) constraint account_email_uq unique ,
	
    status numeric(1) constraint account_status_nn not null  
);

INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Steven', 'King', 'SKING', 'Steven', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Peter', 'Tucker', 'PTUCKER', 'Peter', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('David', 'Bernstein', 'DBERNSTE', 'David', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Nanette', 'Cambrault', 'NCAMBRAU', 'Nanette', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Oliver', 'Tuvault', 'OTUVAULT', 'Oliver', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Patrick', 'Sully', 'PSULLY', 'Patrick', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Lindsey', 'Smith', 'LSMITH', 'Lindsey', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Louise', 'Doran', 'LDORAN', 'Louise', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Sarath', 'Sewall', 'SSEWALL', 'Sarath', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Danielle', 'Greene', 'DGREENE', 'Danielle', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Christopher', 'Olsen', 'COLSEN', 'Christopher', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Clara', 'Vishney', 'CVISHNEY', 'Clara', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Eleni', 'Zlotkey', 'EZLOTKEY', 'Eleni', 3, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Janette', 'King', 'JKING', 'Janette', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Jason', 'Mallin', 'JMALLIN', 'Jason', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('John', 'Seo', 'JSEO', 'John', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Joshua', 'Patel', 'JPATEL', 'Joshua', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Guy', 'Himuro', 'GHIMURO', 'Guy', 3, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Gerald', 'Cambrault', 'GCAMBRAU', 'Gerald', 3, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Alexander', 'Hunold', 'AHUNOLD', 'Alexander', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Adam', 'Fripp', 'AFRIPP', 'Adam', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Alberto', 'Errazuriz', 'AERRAZUR', 'Alberto', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Allan', 'McEwen', 'AMCEWEN', 'Allan', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Curtis', 'Davies', 'CDAVIES', 'Curtis', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Bruce', 'Ernst', 'BERNST', 'Bruce', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Karen', 'Colmenares', 'KCOLMENA', 'Karen', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Kevin', 'Mourgos', 'KMOURGOS', 'Kevin', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Ki', 'Gee', 'KGEE', 'Ki', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Jose Manuel', 'Urman', 'JMURMAN', 'Jose Manuel', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Julia', 'Nayer', 'JNAYER', 'Julia', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('James', 'Landry', 'JLANDRY', 'James', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Neena', 'Kochhar', 'NKOCHHAR', 'Neena', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Lex', 'De Haan', 'LDEHAAN', 'Lex', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Valli', 'Pataballa', 'VPATABAL', 'Valli', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Diana', 'Lorentz', 'DLORENTZ', 'Diana', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Nancy', 'Greenberg', 'NGREENBE', 'Nancy', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Daniel', 'Faviet', 'DFAVIET', 'Daniel', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Ismael', 'Sciarra', 'ISCIARRA', 'Ismael', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Luis', 'Popp', 'LPOPP', 'Luis', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Den', 'Raphaely', 'DRAPHEAL', 'Den', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Shelli', 'Baida', 'SBAIDA', 'Shelli', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Sigal', 'Tobias', 'STOBIAS', 'Sigal', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Matthew', 'Weiss', 'MWEISS', 'Matthew', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Payam', 'Kaufling', 'PKAUFLIN', 'Payam', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Shanta', 'Vollman', 'SVOLLMAN', 'Shanta', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Irene', 'Mikkilineni', 'IMIKKILI', 'Irene', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Laura', 'Bissot', 'LBISSOT', 'Laura', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Mozhe', 'Atkinson', 'MATKINSO', 'Mozhe', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('TJ', 'Olson', 'TJOLSON', 'TJ', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Michael', 'Rogers', 'MROGERS', 'Michael', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Hazel', 'Philtanker', 'HPHILTAN', 'Hazel', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Renske', 'Ladwig', 'RLADWIG', 'Renske', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Stephen', 'Stiles', 'SSTILES', 'Stephen', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Trenna', 'Rajs', 'TRAJS', 'Trenna', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Randall', 'Matos', 'RMATOS', 'Randall', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Jonathon', 'Taylor', 'JTAYLOR', 'Jonathon', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Mattea', 'Marvins', 'MMARVINS', 'Mattea', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Sundar', 'Ande', 'SANDE', 'Sundar', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Jack', 'Livingston', 'JLIVINGS', 'Jack', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Jean', 'Fleaur', 'JFLEAUR', 'Jean', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Jennifer', 'Dilly', 'JDILLY', 'Jennifer', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Girard', 'Geoni', 'GGEONI', 'Girard', 3, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Lisa', 'Ozer', 'LOZER', 'Lisa', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Harrison', 'Bloom', 'HBLOOM', 'Harrison', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Tayler', 'Fox', 'TFOX', 'Tayler', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('William', 'Smith', 'WSMITH', 'William', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Sundita', 'Kumar', 'SKUMAR', 'Sundita', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Winston', 'Taylor', 'WTAYLOR', 'Winston', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Martha', 'Sullivan', 'MSULLIVA', 'Martha', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Nandita', 'Sarchand', 'NSARCHAN', 'Nandita', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Timothy', 'Gates', 'TGATES', 'Timothy', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Sarah', 'Bell', 'SBELL', 'Sarah', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Samuel', 'McCain', 'SMCCAIN', 'Samuel', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Vance', 'Jones', 'VJONES', 'Vance', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Donald', 'OConnell', 'DOCONNEL', 'Donald', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Douglas', 'Grant', 'DGRANT', 'Douglas', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Pat', 'Fay', 'PFAY', 'Pat', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Susan', 'Mavris', 'SMAVRIS', 'Susan', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Hermann', 'Baer', 'HBAER', 'Hermann', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Shelley', 'Higgins', 'SHIGGINS', 'Shelley', 0, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Amit', 'Banda', 'ABANDA', 'Amit', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Alyssa', 'Hutton', 'AHUTTON', 'Alyssa', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Alexis', 'Bull', 'ABULL', 'Alexis', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Anthony', 'Cabrio', 'ACABRIO', 'Anthony', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Alana', 'Walsh', 'AWALSH', 'Alana', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Charles', 'Johnson', 'CJOHNSON', 'Charles', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Britney', 'Everett', 'BEVERETT', 'Britney', 2, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Elizabeth', 'Bates', 'EBATES', 'Elizabeth', 3, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Ellen', 'Abel', 'EABEL', 'Ellen', 3, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Kimberely', 'Grant', 'KGRANT', 'Kimberely', 1, '1234');
INSERT INTO account (first_name, last_name, email, user_name, status, password) 
	VALUES ('Kelly', 'Chung', 'KCHUNG', 'Kelly', 1, '1234');

