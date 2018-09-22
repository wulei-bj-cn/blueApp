create table if not exists t_activities
(
	id varchar(20) not null,
	des varchar(256),
	url varchar(256),
	start_time timestamp,
	end_time timestamp
);
insert into t_activities values ("act0", "活动1:大大的优惠","www.youhui.com","2018-08-04 15:02:32", "2018-08-04 15:32:24");
insert into t_activities values ("act1", "活动2:大儿的优惠","www.youhui.com","2018-08-04 15:02:32", "2018-08-04 15:32:24");
insert into t_activities values ("act2", "活动3:大三的优惠","www.youhui.com","2018-08-04 15:02:32", "2018-08-04 15:32:24");
insert into t_activities values ("act3", "活动4:大四的优惠","www.youhui.com","2018-08-04 15:02:32", "2018-08-04 15:32:24");
insert into t_activities values ("act4", "活动5:大五的优惠","www.youhui.com","2018-08-04 15:02:32", "2018-08-04 15:32:24");
