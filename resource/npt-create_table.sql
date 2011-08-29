drop table t_group;
create table t_group (
	id_ int primary key AUTO_INCREMENT,
	name_ varchar(50) not null,
	create_time_ datetime not null,
	permanent_ char(1) default '0'
);

drop table t_user;
create table t_user (
	id_ int primary key AUTO_INCREMENT,
	group_id_ int not null,
	name_ varchar(50) not null,
	nick_name_ varchar(50) not null,
	last_active_time_ datetime not null
);

drop table t_task;
create table t_task(
	id_ int primary key AUTO_INCREMENT, 
	group_id_ int not null,
	name_ varchar(100) not null,
	create_time_ datetime not null,
	end_time_ datetime,
	submit_user_id_ int,
	work_user_id_ int,
	status_ int ,
	notes_ varchar(600)
);

drop table t_msg;
create table t_msg (
	id_ int primary key AUTO_INCREMENT, 
	group_id_ int not null,
	content_ varchar(500) not null,
	accept_time_ datetime not null
);
