select * from ABC_MEMBER;

select * from ABC_ACCOUNT

select * from ABC_LOAN
select * from ABC_INVEST
select * from ABC_JUDGE

select * from ABC_LOAN_TRANSACTION
select * from ABC_INVEST_TRANSACTION

update abc_loan set request_date = to_char(sysdate,'yyyy-MM-dd')

insert into abc_member values('user10','123','테스트맨10','19800808','남자',1,0,0,1,0,0);
insert into abc_account values('101-101101-101','user10',50000000,'테스트맨10',0);

delete from abc_account where account_holder in ('이용기','범식이');
delete from abc_member where name in ('이용기','범식이');

--delete from ABC_INVEST_TRANSACTION;
--delete from ABC_LOAN_TRANSACTION;
--delete from ABC_JUDGE;
--delete from ABC_INVEST;
--delete from ABC_LOAN;

delete abc_account where loan_code != 0;
update abc_account set balance = 50000000 where email != 'admin';
update abc_account set balance = 0 where email = 'admin';
update abc_member set loan_count = 0, invest_count = 0; 
