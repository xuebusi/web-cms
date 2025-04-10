-- 创建数据库
CREATE DATABASE IF NOT EXISTS `web_cms` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `web_cms`;

-- 用户表
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '加密后的密码',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `company_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- 角色表
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 权限表
CREATE TABLE `permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '权限名称',
  `code` varchar(50) NOT NULL COMMENT '权限代码',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 用户角色关联表
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_role` (`user_id`,`role_id`),
  CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户-角色关联表';

-- 角色权限关联表
CREATE TABLE `role_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL COMMENT '角色ID',
  `permission_id` int NOT NULL COMMENT '权限ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_permission` (`role_id`,`permission_id`),
  CONSTRAINT `fk_role_permission_permission` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `fk_role_permission_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色-权限关联表';

-- 用户网站表
CREATE TABLE `user_site` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `domain` varchar(100) DEFAULT NULL COMMENT '自定义域名',
  `subdomain` varchar(50) NOT NULL COMMENT '子域名',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_subdomain` (`subdomain`),
  UNIQUE KEY `uk_domain` (`domain`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `fk_user_site_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户网站表';

-- 站点信息表
CREATE TABLE `site` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `name` varchar(100) NOT NULL COMMENT '网站名称',
  `logo` varchar(255) DEFAULT NULL COMMENT '网站LOGO路径',
  `favicon` varchar(255) DEFAULT NULL COMMENT '网站图标路径',
  `copyright` text DEFAULT NULL COMMENT '版权信息',
  `footer_content` text DEFAULT NULL COMMENT '页脚内容',
  `seo_title` varchar(255) DEFAULT NULL COMMENT 'SEO标题',
  `seo_keywords` varchar(255) DEFAULT NULL COMMENT 'SEO关键词',
  `seo_description` text DEFAULT NULL COMMENT 'SEO描述',
  `home_page_alias` varchar(50) DEFAULT 'home' COMMENT '首页别名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`user_id`),
  CONSTRAINT `fk_site_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点信息表';

-- 栏目管理表
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `parent_id` int DEFAULT 0 COMMENT '父栏目ID',
  `name` varchar(50) NOT NULL COMMENT '栏目名称',
  `alias` varchar(50) DEFAULT NULL COMMENT '栏目别名',
  `sort` int DEFAULT 0 COMMENT '排序',
  `is_nav` tinyint(1) DEFAULT 1 COMMENT '是否显示在导航栏',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `page_id` int DEFAULT NULL COMMENT '绑定的页面ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='栏目管理表';

-- 页面管理表
CREATE TABLE `page` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `title` varchar(100) NOT NULL COMMENT '页面标题',
  `alias` varchar(50) DEFAULT NULL COMMENT '页面别名',
  `description` varchar(255) DEFAULT NULL COMMENT '页面描述',
  `keywords` varchar(255) DEFAULT NULL COMMENT '页面关键词',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态：0-隐藏，1-显示',
  `layout` varchar(50) DEFAULT 'default' COMMENT '页面布局模板',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_alias` (`user_id`,`alias`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面管理表';

-- 区块管理表
CREATE TABLE `block` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `page_id` int NOT NULL COMMENT '所属页面ID',
  `type` varchar(50) NOT NULL COMMENT '区块类型',
  `title` varchar(100) DEFAULT NULL COMMENT '区块标题',
  `sort` int DEFAULT 0 COMMENT '排序',
  `config` json DEFAULT NULL COMMENT '区块配置(JSON格式)',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态：0-隐藏，1-显示',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_page_id` (`page_id`),
  KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区块管理表';

-- 轮播图表
CREATE TABLE `carousel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `block_id` int NOT NULL COMMENT '所属区块ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `image` varchar(255) NOT NULL COMMENT '图片路径',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `description` text DEFAULT NULL COMMENT '描述',
  `sort` int DEFAULT 0 COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_block_id` (`block_id`),
  KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播图表';

-- 图文区块内容表
CREATE TABLE `image_text` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `block_id` int NOT NULL COMMENT '所属区块ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `image` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `content` text DEFAULT NULL COMMENT '内容',
  `image_position` varchar(20) DEFAULT 'left' COMMENT '图片位置：left/right',
  `sort` int DEFAULT 0 COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_block_id` (`block_id`),
  KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图文区块内容表';

-- 图片网格表
CREATE TABLE `image_grid` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `block_id` int NOT NULL COMMENT '所属区块ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `image` varchar(255) NOT NULL COMMENT '图片路径',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `description` text DEFAULT NULL COMMENT '描述',
  `sort` int DEFAULT 0 COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_block_id` (`block_id`),
  KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图片网格表';

-- 文章表
CREATE TABLE `article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `category_id` int DEFAULT NULL COMMENT '所属栏目ID',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `subtitle` varchar(100) DEFAULT NULL COMMENT '副标题',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `summary` text DEFAULT NULL COMMENT '摘要',
  `content` longtext DEFAULT NULL COMMENT '内容',
  `cover_image` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态：0-草稿，1-发布',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_publish_time` (`publish_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- 表单表
CREATE TABLE `form` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `block_id` int NOT NULL COMMENT '所属区块ID',
  `name` varchar(100) NOT NULL COMMENT '表单名称',
  `fields` json NOT NULL COMMENT '表单字段配置(JSON格式)',
  `submit_text` varchar(50) DEFAULT '提交' COMMENT '提交按钮文字',
  `success_message` text DEFAULT NULL COMMENT '提交成功提示',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_block_id` (`block_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='表单表';

-- 表单提交数据表
CREATE TABLE `form_data` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `form_id` int NOT NULL COMMENT '表单ID',
  `data` json NOT NULL COMMENT '表单数据(JSON格式)',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_form_id` (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='表单提交数据表';

-- 常见问题表
CREATE TABLE `faq` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `block_id` int NOT NULL COMMENT '所属区块ID',
  `question` varchar(255) NOT NULL COMMENT '问题',
  `answer` text NOT NULL COMMENT '答案',
  `sort` int DEFAULT 0 COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_block_id` (`block_id`),
  KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='常见问题表';

-- 地图配置表
CREATE TABLE `map_config` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `block_id` int NOT NULL COMMENT '所属区块ID',
  `address` varchar(255) NOT NULL COMMENT '地址',
  `latitude` decimal(10,7) DEFAULT NULL COMMENT '纬度',
  `longitude` decimal(10,7) DEFAULT NULL COMMENT '经度',
  `zoom` int DEFAULT 15 COMMENT '缩放级别',
  `marker_title` varchar(100) DEFAULT NULL COMMENT '标记标题',
  `api_key` varchar(255) DEFAULT NULL COMMENT '地图API密钥',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_block_id` (`block_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地图配置表';

-- 初始化角色数据
INSERT INTO `role` (`name`, `description`) VALUES 
('ROLE_ADMIN', '系统管理员'),
('ROLE_USER', '普通用户');

-- 初始化权限数据
INSERT INTO `permission` (`name`, `code`, `description`) VALUES 
('站点管理', 'site:manage', '管理站点基本信息'),
('栏目管理', 'category:manage', '管理网站栏目'),
('页面管理', 'page:manage', '管理网站页面'),
('内容管理', 'content:manage', '管理网站内容'),
('用户管理', 'user:manage', '管理系统用户');

-- 初始化管理员角色权限
INSERT INTO `role_permission` (`role_id`, `permission_id`) 
SELECT r.id, p.id FROM `role` r, `permission` p 
WHERE r.name = 'ROLE_ADMIN';

-- 初始化默认管理员用户 (密码: admin123)
INSERT INTO `user` (`username`, `password`, `email`, `company_name`, `status`) 
VALUES ('admin', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'admin@example.com', '系统管理', 1);

-- 设置管理员角色
INSERT INTO `user_role` (`user_id`, `role_id`) 
VALUES (1, 1);

-- 为管理员创建默认网站
INSERT INTO `user_site` (`user_id`, `subdomain`, `status`) 
VALUES (1, 'admin', 1);

-- 创建管理员默认站点配置
INSERT INTO `site` (`user_id`, `name`, `copyright`) 
VALUES (1, '我的网站', '© 2023 我的公司');