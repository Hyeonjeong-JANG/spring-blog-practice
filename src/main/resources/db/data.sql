<<<<<<< HEAD
insert into user_tb(username, password, email, created_at) values('ssar', '1234', 'ssar@nate.com', now());
insert into user_tb(username, password, email, created_at) values('cos', '1234', 'cos@nate.com', now());
insert into user_tb(username, password, email, created_at) values('love', '1234', 'love@nate.com', now());

insert into board_tb(title, content, user_id, created_at) values('제목1', '내용1', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목2', '내용2', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목3', '내용3', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목4', '내용4', 2, now());
=======
insert into user_tb(username, password, email) values('ssar', '$2a$10$x.aGB7.OKSoaeL9loOqmTuC5/8S6uZRhflYsOee/XwUgBrgNnypU.', 'ssar@nate.com');
insert into user_tb(username, password, email) values('cos', '$2a$10$x.aGB7.OKSoaeL9loOqmTuC5/8S6uZRhflYsOee/XwUgBrgNnypU.', 'cos@nate.com');
insert into board_tb(title, content, user_id, created_at) values('제목2', '내용1', 1, now());
insert into board_tb(title, content, user_id, created_at) values('2', '내용2', 1, now());
insert into board_tb(title, content, user_id, created_at) values('22', '내용3', 1, now());
insert into board_tb(title, content, user_id, created_at) values('222', '내용4', 2, now());
insert into board_tb(title, content, user_id, created_at) values('제목5', '내용5', 2, now());
insert into reply_tb(comment, board_id, user_id) values('댓글1', 1, 1);
insert into reply_tb(comment, board_id, user_id) values('댓글2', 1, 1);
>>>>>>> 2867aa12b6ea4e573482155c016d54cfb566ed0e
