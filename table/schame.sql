CREATE TABLE `t_user`(
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL UNIQUE,
  `password` VARCHAR(100) NOT NULL,
  `role_id` BIGINT NOT NULL,
  `locked` VARCHAR(100) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

CREATE TABLE `t_role`(
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL UNIQUE,
  `description` VARCHAR(20) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

CREATE TABLE `t_resource`(
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `url` VARCHAR(100) NOT NULL UNIQUE,
  `description` VARCHAR(255)
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

CREATE TABLE `t_role_resource`(
  `role_id` BIGINT NOT NULL,
  `resource_id` BIGINT NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE = InnoDB DEFAULT CHARSET = UTF8;

-- oAuth2 start
create table `oauth2_user` (
  id bigint auto_increment,
  username varchar(100),
  password varchar(100),
  salt varchar(100),
  constraint pk_oauth2_user primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_oauth2_user_username on oauth2_user(username);

create table oauth2_client (
  id bigint auto_increment,
  client_name varchar(100),
  client_id varchar(100),
  client_secret varchar(100),
  constraint pk_oauth2_client primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_oauth2_client_client_id on oauth2_client(client_id);
-- oAuth2 end