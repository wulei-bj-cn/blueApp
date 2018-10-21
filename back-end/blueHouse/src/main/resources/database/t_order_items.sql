create table t_order_items (order_id varchar(64), item_id varchar(64), item_class varchar(20), start_time timestamp, end_time timestamp, status varchar(32));

insert into t_order_items values ("ord1", "des1", "designs", "2018-08-04 15:32:24", "2018-08-04 15:32:24", "进行中");
insert into t_order_items values ("ord1", "des2", "designs", "2018-08-04 15:32:24", "2018-08-04 15:32:24", "进行中");
insert into t_order_items values ("ord1", "con1", "contracts", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord1", "con2", "contracts", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord1", "con3", "contracts", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord1", "con4", "contracts", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord1", "con5", "contracts", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord1", "dis1", "disclaims", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord1", "mat1", "materials", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord1", "mat2", "materials", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord1", "pro1", "projects", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord1", "pro2", "projects", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord1", "pro3", "projects", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord1", "mea1", "measures", "2018-07-04 12:24:16", "2018-07-14 12:24:16", "进行中");

insert into t_order_items values ("ord2", "des1", "designs", "2018-08-04 15:32:24", "2018-08-04 15:32:24", "进行中");
insert into t_order_items values ("ord2", "con1", "contracts", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord2", "con3", "contracts", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord2", "con5", "contracts", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord2", "dis1", "disclaims", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord2", "mat1", "materials", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord2", "pro1", "projects", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord2", "pro3", "projects", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
insert into t_order_items values ("ord2", "mea1", "measures", "2018-07-04 12:24:16", "2018-07-14 12:24:16", "进行中");

insert into t_order_items values ("ord3", "mea1", "projects", "2018-08-04 12:24:16", "2018-08-04 12:24:16", "进行中");
