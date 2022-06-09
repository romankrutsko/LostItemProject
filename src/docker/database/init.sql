CREATE DATABASE lostitem;
use lostitem;

CREATE TABLE lostitem (
                            `id` int(9) unsigned NOT NULL AUTO_INCREMENT,
                            `user_id` int(9) NOT NULL,
                            `name_item` varchar(100) NOT NULL,
                            `keywords` varchar(100) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE user (
                        `id` int(9) unsigned NOT NULL AUTO_INCREMENT,
                        `name` varchar(100) NOT NULL,
                        `password` varchar(100) NOT NULL,
                        `role` varchar(100) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into user(name, password, role) VALUES ('admin', 'admin', 'ADMIN');
insert into user(name, password, role) VALUES ('user', 'user', 'USER');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test1', 'test,1,8');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test2', 'test,1,7');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test3', 'test,1,6');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test4', 'test,1,5');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test5', 'test,1,4');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test6', 'test,1,3');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test7', 'test,1,2');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test3', 'test,1,6');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test4', 'test,1,5');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test5', 'test,1,4');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test6', 'test,1,3');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test7', 'test,1,2');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test3', 'test,1,6');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test4', 'test,1,5');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test5', 'test,1,4');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test6', 'test,1,3');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test7', 'test,1,2');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test3', 'test,1,6');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test4', 'test,1,5');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test5', 'test,1,4');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test6', 'test,1,3');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test7', 'test,1,2');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test3', 'test,1,6');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test4', 'test,1,5');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test5', 'test,1,4');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test6', 'test,1,3');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test7', 'test,1,2');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test3', 'test,1,6');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test4', 'test,1,5');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test5', 'test,1,4');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test6', 'test,1,3');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test7', 'test,1,2');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test3', 'test,1,6');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test4', 'test,1,5');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test5', 'test,1,4');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test6', 'test,1,3');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test7', 'test,1,2');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test3', 'test,1,6');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test4', 'test,1,5');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test5', 'test,1,4');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test6', 'test,1,3');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test7', 'test,1,2');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test3', 'test,1,6');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test4', 'test,1,5');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test5', 'test,1,4');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test6', 'test,1,3');
insert into lostitem(user_id, name_item, keywords) VALUES (1, 'test7', 'test,1,2');


