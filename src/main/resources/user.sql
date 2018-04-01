## 用户表
CREATE  TABLE user (
	user_id VARCHAR(32) NOT NULL COMMENT '用户id',
  user_name VARCHAR(32) NOT NULL COMMENT '用户名称',
  nick_name VARCHAR(32) NOT NULL COMMENT '昵称',
  avatar VARCHAR(64) NOT NULL COMMENT '头像',
  sex INT NOT NULL COMMENT '性别',
  user_type_id VARCHAR(32) NOT NULL COMMENT '用户类型id',
  pwd VARCHAR(32) NOT NULL COMMENT '用户密码',
  phone VARCHAR(32) NOT NULL COMMENT '用户电话',
  create_year VARCHAR(32) NOT NULL COMMENT '创建年份',
  create_month VARCHAR(32) NOT NULL COMMENT '创建月份',
  create_day VARCHAR(32) NOT NULL COMMENT '创建当天',
	create_time timestamp NOT NULL default current_timestamp comment '创建时间',
	update_time timestamp NOT NULL default current_timestamp on update current_timestamp comment '更新时间',
	primary key (user_id)
) comment '用户表';
## 用户类型表
CREATE TABLE user_type(
  type_id VARCHAR(32) NOT NULL COMMENT '类型id',
  type_desc VARCHAR(32) NOT NULL COMMENT '类型描述'
);
## 用户收货地址
## 用户详细信息

## 用户浏览信息记录
CREATE TABLE user_logs (
  log_id VARCHAR(32) NOT NULL COMMENT '用户记录id',
  user_id VARCHAR(32) NOT NULL COMMENT '用户id',
  router_name VARCHAR(32) NOT NULL COMMENT '路由名称',
  page_name VARCHAR(32) NOT NULL COMMENT '页面名称',
  create_year VARCHAR(32) NOT NULL COMMENT '创建年份',
  create_month VARCHAR(32) NOT NULL COMMENT '创建月份',
  create_day VARCHAR(32) NOT NULL COMMENT '创建当天',
  create_time timestamp NOT NULL default current_timestamp comment '创建时间'
);
## 持久化用户
CREATE TABLE user_persistence (
  persistence_id VARCHAR(32) NOT NULL COMMENT '持久化id',
  vt VARCHAR(64)  COMMENT 'vt', ## vt可以为空
  user_name VARCHAR(32) NOT NULL COMMENT '用户id',
  create_year VARCHAR(32) NOT NULL COMMENT '创建年份',
  create_month VARCHAR(32) NOT NULL COMMENT '创建月份',
  create_day VARCHAR(32) NOT NULL COMMENT '创建当天',
  create_time timestamp NOT NULL default current_timestamp comment '创建时间'
);
