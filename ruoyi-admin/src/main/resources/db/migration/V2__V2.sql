ALTER TABLE `mall`.`selling_goods_info` ADD COLUMN `sort` int NULL DEFAULT NULL COMMENT '排序' AFTER `sold_num`;
ALTER TABLE `mall`.`selling_goods_info` ADD INDEX `sort`(`sort`) USING BTREE;

ALTER TABLE `mall`.`shop_info` ADD COLUMN `recommended_flag` tinyint(1) NULL DEFAULT 0 COMMENT '推荐标志 0：未推荐 1：已推荐' AFTER `sold_num`;
ALTER TABLE `mall`.`shop_info` ADD INDEX `recommended_flag`(`recommended_flag`) USING BTREE;

ALTER TABLE `mall`.`user_receive_address` ADD COLUMN `country_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '国家' AFTER `receiver_phone`;

ALTER TABLE `mall`.`user_shopping_order` ADD COLUMN `refund_status` tinyint(1) NULL DEFAULT 0 COMMENT '退款状态 0：未退款 1：申请中 2：退款成功' AFTER `order_status`;