/**
  初始化系统配置数据
 */
TRUNCATE TABLE bonus_config;
TRUNCATE TABLE gen_table;
TRUNCATE TABLE gen_table_column;
TRUNCATE TABLE ip_black_list;
update site_info set ios_download_url='https://www.mall.com/download/mall.mobileconfig',android_download_url ='https://www.mall.com/#/?install=1',android_apk_download_url='',app_url='https://www.mall.com',website_url='https://www.website.com';
update web_menu_config set jump_url='https://www.mall.com/fast/mall' where id = 5;
update web_menu_config set jump_url='https://www.mall.com/h5' where id = 14;
update web_menu_config set jump_url='https://www.mall.com' where id = 18;
update web_menu_config set jump_url='https://www.mall.com' where id = 27;