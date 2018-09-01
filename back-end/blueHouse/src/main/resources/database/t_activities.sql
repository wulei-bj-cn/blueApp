create table if not exists t_activities
(
	id int not null,
	des varchar(256),
	url varchar(256),
	start_time timestamp,
	end_time timestamp
);
insert into t_activities values (1, "活动1:大大的优惠","www.youhui.com","2018-08-04 15:02:32", "2018-08-04 15:32:24");
