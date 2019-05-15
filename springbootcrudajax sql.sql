create database springbootajaxcrud;
use springbootajaxcrud;
desc student;
select * from student;

create table student(id int primary key auto_increment,name varchar(50),city varchar(50));
insert into student(name,city)value('rajasekar','chennai');
insert into student(name,city)value('sathish','bangolore');
insert into student(name,city)value('ranjith','mumbai');





