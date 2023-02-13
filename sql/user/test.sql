# insert 성공
# insert into user
# (account_id, password, nickname, email)
# values
#     ('test123', 'test', 'test', 'test@sdf.com');

#
# # account_id 실패
# insert into user
# (account_id, password, nickname, email)
# values
#     ('test', 'test', 'test', 'test@sdf.com');
#
# insert into user
# (account_id, password, nickname, email)
# values
#     ('testㅆㄸ', 'test', 'test', 'test@sdf.com');
#
#
# # email 실패
# insert into user
# (account_id, password, nickname, email)
# values
#     ('test123', 'test', 'test', 'test');

# delete from user;

ALTER TABLE user AUTO_INCREMENT = 1;