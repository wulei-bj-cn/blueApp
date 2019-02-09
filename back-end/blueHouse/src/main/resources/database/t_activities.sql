create table if not exists t_activities ( id varchar(64) not null, des varchar(256), url varchar(256), act_type varchar(32), target_url varchar(256), active_id varchar(64), start_time timestamp, end_time timestamp );

insert into t_activities values ("act0", "活动1:大大的优惠","act_pic_1.jpg", "Static", NULL, NULL, "2018-08-04 15:02:32", "2018-08-04 15:32:24");
insert into t_activities values ("act1", "活动2:大大的优惠","act_pic_2.jpg", "URL", "www.youhui2.com", NULL, "2018-08-04 15:02:32", "2018-08-04 15:32:24");
insert into t_activities values ("act2", "活动3:大大的优惠","act_pic_3.jpg", "Discounts", NULL, "sol_201xyw802", "2018-08-04 15:02:32", "2018-08-04 15:32:24");
insert into t_activities values ("act3", "活动4:大大的优惠","act_pic_4.jpg", "Product", NULL, "mat_26wo203923", "2018-08-04 15:02:32", "2018-08-04 15:32:24");
insert into t_activities values ("act4", "活动5:大大的优惠","act_pic_5.jpg", "URL", "www.youhui5.com", NULL, "2018-08-04 15:02:32", "2018-08-04 15:32:24");
