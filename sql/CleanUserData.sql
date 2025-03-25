/**
  清理用户业务数据
 */
TRUNCATE TABLE seller_account_apply_record;
TRUNCATE TABLE site_message;
TRUNCATE TABLE site_message_read;
TRUNCATE TABLE user_amount;
TRUNCATE TABLE user_bank;
TRUNCATE TABLE user_bill_detail;
TRUNCATE TABLE user_dm_amount_change_record;
TRUNCATE TABLE user_favorite_goods;
TRUNCATE TABLE user_favorite_shop;
TRUNCATE TABLE user_point_change_record;
TRUNCATE TABLE user_receive_address;
TRUNCATE TABLE user_recharge;
TRUNCATE TABLE user_shopping_cart;
TRUNCATE TABLE user_shopping_order;
TRUNCATE TABLE user_shopping_order_detail;
TRUNCATE TABLE user_team_level_line;
TRUNCATE TABLE user_wallet_address;
TRUNCATE TABLE user_winnings_change_record;
TRUNCATE TABLE user_withdraw;
TRUNCATE TABLE sys_logininfor;
TRUNCATE TABLE sys_oper_log;
delete from shop_info where id > 909;
delete from selling_goods_info where shop_info_id > 909;

TRUNCATE TABLE user_info;
insert into user_info(id, user_account, phone, email, nick_name, real_name, user_pwd, with_pwd, agent_id, agent_name, invite_code, account_type, avatar, is_lock, reg_time, reg_ip, reg_address, last_login_ip, last_login_time, id_card, img1_key, img2_key, img3_key, is_active, auth_status_junior, auth_status_senior, auth_method, auth_msg, sup_user_id, no_login_info, status, need_order_amount, vip_level, credit_score, is_can_withdraw, is_agent, is_del, remark)
    value
    (1,'z159001',null,null,'z159001',null,'$2a$10$6TTm6Vy3vaPFN9BZd3WseOYnjarbH0/dmqdAOrItXdInfdS2KMcNm',null,null,null,'U705777',1,null,0,now(),null,null,null,null,null,null,null,null,0,0,0,0,null,null,null,0,0,1,100,0,0,0,null),
    (2,'z159002',null,null,'z159002',null,'$2a$10$6TTm6Vy3vaPFN9BZd3WseOYnjarbH0/dmqdAOrItXdInfdS2KMcNm',null,null,null,'U704757',1,null,0,now(),null,null,null,null,null,null,null,null,0,0,0,0,null,null,null,0,0,1,100,0,0,0,null);
ALTER TABLE user_info AUTO_INCREMENT = 1000;
ALTER TABLE shop_info AUTO_INCREMENT = 1000;
delete from sys_user where user_id not in (1,100,101,102,103);
delete from sys_user_role where user_id not in (1,100,101,102,103);

delete from sys_role where role_id not in (1,2,3,4,5,6,100,101,109);
delete from sys_role_dept where role_id not in (1,2,3,4,5,6,100,101,109);
delete from sys_role_menu where role_id not in (1,2,3,4,5,6,100,101,109);
delete from sys_user_role where role_id not in (1,2,3,4,5,6,100,101,109);