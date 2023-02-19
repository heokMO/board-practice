create table post
(
    id              bigint unsigned not null AUTO_INCREMENT primary key,
    board_id        smallint    not null,
    title           varchar(50) not null,
    content         text        not null,
    written_user_id int unsigned,
    written_time    timestamp   not null default current_timestamp,
    update_time     timestamp   not null default current_timestamp,
    views           int unsigned not null default 0,
    non_mem_nick    varchar(24),
    non_mem_pw      varchar(20)
);
