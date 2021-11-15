drop table if exists books_to_customer;
drop table if exists books;
drop table if exists customers;
create table if not exists customers(

--	private int id;
--	private String name;
--	private String username;
--	private String password;
--	private String email;
--	private String cardNumber;
--	private boolean isEmployee;
--	private customer owner;

	c_id serial primary key,
	c_name varchar(50) not null,
	c_username varchar(50) unique,
	c_password varchar(50) not null,
	c_email varchar(50) not null,
	c_cardNumber varchar(50) not null,
	c_isEmployee BOOLEAN,
	c_owner integer references customers(c_id)
);

insert into customers (c_name, c_username, c_password , c_email, c_cardNumber) values ('Brayden', 'user', 'pass', 'ogarlette0@bravesites.com', '3578903365519546');
insert into customers (c_name, c_username, c_password , c_email, c_cardNumber) values ('Laurella', 'customer', 'pass', 'lhaggar1@opera.com', '3536516899323010');
insert into customers (c_name, c_username, c_password , c_email, c_cardNumber) values ('Byron', 'employee', 'pass', 'bfludder2@ihg.com', '3557554178295439');
insert into customers (c_name, c_username, c_password , c_email, c_cardNumber) values ('Emlen', 'elachaize3', 'Lachaize', 'elachaize3@live.com', '5602234579897063');
insert into customers (c_name, c_username, c_password , c_email, c_cardNumber) values ('Sheridan', 'stefft4', 'Tefft', 'stefft4@bizjournals.com', '6304155415360090');
insert into customers (c_name, c_username, c_password , c_email, c_cardNumber) values ('Linnell', 'lroget5', 'Roget', 'lroget5@theguardian.com', '3545122936738721');
insert into customers (c_name, c_username, c_password , c_email, c_cardNumber) values ('Ragnar', 'rslides6', 'Slides', 'rslides6@behance.net', '67630061008462029');
insert into customers (c_name, c_username, c_password , c_email, c_cardNumber) values ('Shepperd', 'sthebeau7', 'Thebeau', 'sthebeau7@weibo.com', '560223150571617023');
insert into customers (c_name, c_username, c_password , c_email, c_cardNumber) values ('Olly', 'obrammall8', 'Brammall', 'obrammall8@oracle.com', '3538677009278722');
insert into customers (c_name, c_username, c_password , c_email, c_cardNumber) values ('Nannette', 'ncrookes9', 'Crookes', 'ncrookes9@upenn.edu', '3564133507364330');

update customers set c_owner = 1;
update customers set c_isEmployee = false where c_id > 1;
select * from customers;


drop table if exists books;
create table if not exists books(
--	private int id;
--	private int vinNumber;
--	private String title;
--	private Genre genre;
--	private boolean isSoftCover;
--	private boolean isAvailable;
--	private String author;
--	private double price;
--	private Customer newOwner;

	b_id serial primary key,
	b_vinNumber varchar(60) not null,
	b_title varchar(2000) not null,
	b_genre varchar(50),
	b_isSoftCover BOOLEAN not null,
	b_isAvailable BOOLEAN,
	b_author varchar(100) not null,
	b_price varchar(50),
	b_newOwner integer references customers(c_id)
);

insert into books (b_vinNumber, b_title, b_isSoftCover, b_isAvailable, b_author, b_price) values ('3', 'Rumble in the Air-Conditioned Auditorium: O''Reilly vs. Stewart 2012, The', true, true, 'Jere Shurman', '$30.45');
insert into books (b_vinNumber, b_title, b_isSoftCover, b_isAvailable, b_author, b_price) values ('78484', '5th Musketeer, The (a.k.a. Fifth Musketeer, The)', true, true, 'Pierrette Balogh', '$39.36');
insert into books (b_vinNumber, b_title, b_isSoftCover, b_isAvailable, b_author, b_price) values ('7224', 'Merry Madagascar', false, true, 'Audrie Kitchen', '$34.52');
insert into books (b_vinNumber, b_title, b_isSoftCover, b_isAvailable, b_author, b_price) values ('19', 'Colourful (Karafuru)', false, true, 'Allix Woodsford', '$49.25');
insert into books (b_vinNumber, b_title, b_isSoftCover, b_isAvailable, b_author, b_price) values ('28', 'Trouble with Girls, The', false, true, 'Stephani Lusher', '$26.88');
insert into books (b_vinNumber, b_title, b_isSoftCover, b_isAvailable, b_author, b_price) values ('96', 'Omar', true, true, 'Hermia O''Corr', '$44.80');
insert into books (b_vinNumber, b_title, b_isSoftCover, b_isAvailable, b_author, b_price) values ('2161', 'Party Girl', true, true, 'Juliann Longridge', '$21.64');
insert into books (b_vinNumber, b_title, b_isSoftCover, b_isAvailable, b_author, b_price) values ('3', 'Charlie Chan in Honolulu', false, true, 'Mandi Waber', '$42.96');
insert into books (b_vinNumber, b_title, b_isSoftCover, b_isAvailable, b_author, b_price) values ('3', 'Dead Again', false, true, 'Kendre Tomkins', '$11.66');
insert into books (b_vinNumber, b_title, b_isSoftCover, b_isAvailable, b_author, b_price) values ('14', 'Crime and Punishment in Suburbia', false, true, 'Gram Thwaites', '$33.45');

update books set b_newOwner = 1 where b_id > 6;
update books set b_genre = 'FANTASY';

drop table if exists books_to_customer;
create table if not exists books_to_customer(
book_to_customers_id serial primary key,
b_id integer references books(b_id),
c_id integer references customers(c_id),
b_price decimal,
b_price_accepted boolean
);

insert into books_to_customer (b_id, c_id, b_price, b_price_accepted) values (2, 1, 45.25, false);
insert into books_to_customer (b_id, c_id, b_price, b_price_accepted) values (2, 3, 34.56, false);
insert into books_to_customer (b_id, c_id, b_price, b_price_accepted) values (2, 5, 25.71, false);
insert into books_to_customer (b_id, c_id, b_price, b_price_accepted) values (4, 1, 11.24, false);
insert into books_to_customer (b_id, c_id, b_price, b_price_accepted) values (5, 2, 12.50, false);

--delete from books where b_vinNumber = '3' and b_isAvailable = false;
























