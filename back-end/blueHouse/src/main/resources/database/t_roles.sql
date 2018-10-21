create table t_roles (id varchar(64), description varchar(64), permissions varchar(256));

insert into t_roles values ("admin", "管理员，管理后台所有功能模块，但是无法添加角色或login", "除权限管理模块以外的所有模块");
insert into t_roles values ("designer", "设计师，维护后台设计方案模块", "设计方案模块");
insert into t_roles values ("super_admin", "超级管理员，具有后台所有权限", "所有模块");

