CREATE TABLE IF NOT EXISTS `taolijie`.`recommended_post` (
      `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
      `job_id` INT NULL COMMENT '',
      `sh_id` INT NULL COMMENT '',
      `resume_id` INT NULL COMMENT '',
      `apply_time` DATETIME NULL COMMENT '申请时间',
      `validation` TINYINT(1) NULL DEFAULT 0 COMMENT '是否显示在首页',
      `member_id` INT NULL COMMENT '申请人',
      `memo` VARCHAR(300) NULL COMMENT '',
      `order_index` INT NULL COMMENT '排序指数，值越 小越靠前，从0开始',
      `pass_time` DATETIME NULL COMMENT '通过审核的时间',
      `remove_time` DATETIME NULL COMMENT '下架时间',
      PRIMARY KEY (`id`) )
    ENGINE = InnoDB
