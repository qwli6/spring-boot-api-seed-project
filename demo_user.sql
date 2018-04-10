
create table user
(
	id int auto_increment
		primary key,
	username varchar(32) null,
	password varchar(32) null,
	nickname varchar(32) null,
	sex char null,
	age int default '0' null,
	phone_no varchar(16) null,
	email varchar(32) null,
	address varchar(256) null,
	constraint user_id_uindex
		unique (id)
);

INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (1, '关羽', 'cd73502828457d15655bbd7a63fb0bc8', '狂龙出海', '男', 34, 111, '111@qq.com', '广东肇庆');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (2, '张飞', 'e1d5be1c7f2f456670de3d53c7b54f4a', '山崩地裂', '男', 32, 112, '112@qq.com', '湖北荆州');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (3, '赵云', 'c5658c711ba9170700fc7d3ee3f63e40', '火战火轮', '男', 43, 113, '113@qq.com', '湖北武汉');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (4, '马超', 'c7d48bbf2b960adc10b0aba11bf336a5', '仙人指路', '男', 23, 114, '114@qq.com', '广西南宁');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (5, '黄忠', '0028a24e18e166c292689023e6c22e09', '百步穿杨', '男', 41, 115, '115@qq.com', '广东深圳');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (6, '张辽', '6b00b5b0d11ce52eb21b3e33d464dd83', '风卷残云', '男', 51, 116, '116@qq.com', '北京三里屯');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (7, '曹操', 'cd6a9199617a33f315fc0e644c9bf5a7', '八面斩', '男', 23, 117, '117@qq.com', '上海');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (8, '司马懿', '8f21775227a31d529b9f601e6b7199bd', '不知道叫什么', '男', 51, 118, '118@qq.com', '乌鲁木齐');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (9, '诸葛亮', '8f21775227a31d529b9f601e6b7199bd', '呼风唤雨', '男', 28, 119, '119@qq.com', '西藏');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (10, '吕布', '8f21775227a31d529b9f601e6b7199bd', '性感吕布在线跳大', '男', 37, 120, '120@qq.com', '山东');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (11, '貂蝉', 'cd6a9199617a33f315fc0e644c9bf5a7', '天女散花', '女', 47, 211, '211@qq.com', '广东广州');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (12, '彻里吉', 'cd6a9199617a33f315fc0e644c9bf5a7', '套马的汉子', '男', 35, 212, '212@qq.com', '湖南长沙');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (13, '夏侯渊', '0028a24e18e166c292689023e6c22e09', '40 米厂的大刀', '男', 23, 213, '213@qq.com', '广西宜宾');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (14, '许褚', '0028a24e18e166c292689023e6c22e09', '大胖子', '男', 21, 214, '214@qq.com', '湖南岳阳');
INSERT INTO user (id, username, password, nickname, sex, age, phone_no, email, address) VALUES (15, '孙尚香', 'e1d5be1c7f2f456670de3d53c7b54f4a', '小女人', '女', 34, 215, '215@qq.com', '湖北恩施');
