create table t_roles (id varchar(64), description varchar(64), permissions varchar(256));

insert into t_roles values ("admin", "管理员，管理后台所有功能模块，但是无法添加角色或login", "1|2|3|4|5|6");
insert into t_roles values ("designer", "设计师，维护后台设计方案模块", "5");
insert into t_roles values ("super_admin", "超级管理员，具有后台所有权限", "1|2|3|4|5|6|7");

