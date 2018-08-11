create table t_admins (id varchar(20), login varchar(32), name varchar(256), password varchar(64), role varchar(20), status varchar(64), last_log_on timestamp);

insert into t_admins values ("adm1", "wulei", "吴磊", "88888888", "admin", "正常", "2018-08-04 12:24:16");
insert into t_admins values ("adm2", "qinyang", "秦阳", "88888888", "admin", "正常", "2018-08-09 11:54:48");
insert into t_admins values ("adm3", "xiaoqi", "晓琪", "88888888", "designer", "正常", "2018-07-09 18:24:21");

