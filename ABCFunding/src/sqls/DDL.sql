-----------------------------------------------ABC_MEMBER-------------------------------------------------------
create table abc_member(
  email varchar2(51),
  pwd varchar2(60) not null,
  name varchar2(12) not null,
  birth varchar2(8) not null,
  gender varchar2(4) not null,
  credit_rating number(1) not null,
  loan_count number not null,
  invest_count number not null,
  support_count number not null,
  grade number(1) not null,
  auth_code varchar2(50) not null
);

alter table abc_member
add constraint pk_member_email primary key(email);

comment on table abc_member is 'Member Table';
comment on column abc_member.email is 'email';
comment on column abc_member.name is 'name';
comment on column abc_member.pwd is 'password';
comment on column abc_member.birth is 'birth date';
comment on column abc_member.gender is 'gender';
comment on column abc_member.credit_rating is 'credit rate';
comment on column abc_member.loan_count is 'number of loan';
comment on column abc_member.invest_count is 'number of investment';
comment on column abc_member.grade is 'grade';
comment on column abc_member.auth_code is 'authentication code';

-----------------------------------------------ABC_MEMBER_MANAGE-------------------------------------------------------
create table abc_member_manage(
  member_manage_seq number,
  email varchar2(51) not null,
  total_invest_count number,
  current_invest_count number,
  total_profit number,
  stack_invest_money number,
  total_loan_count number,
  current_loan_count number,
  stack_loan_money number,
  stack_repay number
);

alter table abc_member_manage
add constraint pk_member_seq primary key( member_manage_seq);

alter table abc_member_manage
add constraint fk_member_manage_email foreign key(email) references abc_member(email);

create sequence seq_member_mange;

comment on table abc_member_manage is 'Member Management Table';
comment on column abc_member_manage.member_manage_seq is 'Sequence primary key';
comment on column abc_member_manage.email is 'email';
comment on column abc_member_manage.total_invest_count is 'total investment count';
comment on column abc_member_manage.current_invest_count is 'current investment count';
comment on column abc_member_manage.total_profit is 'total profit';
comment on column abc_member_manage.stack_invest_money is 'stack investment money';
comment on column abc_member_manage.total_loan_count is 'total loan count';
comment on column abc_member_manage.current_loan_count is 'current loan count';
comment on column abc_member_manage.stack_loan_money is 'stack loan money';
comment on column abc_member_manage.stack_repay is 'stack repay';

-----------------------------------------------ABC_ACCOUNT-------------------------------------------------------
create table abc_account(
    account_no varchar2(14) not null,
    email varchar2(51) not null,
    balance number not null,
    account_holder varchar2(12) not null,
    loan_code number
);

alter table abc_account
add constraint pk_account_no primary key(account_no);

alter table abc_account
add constraint fk_account_email foreign key(email) references abc_member(email);

comment on table abc_account is 'account table';
comment on column abc_account.account_no is 'account number';
comment on column abc_account.email is 'email.';
comment on column abc_account.balance is 'balance';
comment on column abc_account.account_holder is 'account holder';
comment on column abc_account.loan_code is 'loan code';

-----------------------------------------------ABC_LOAN-------------------------------------------------------
create table abc_loan(
    loan_code number not null,
    email varchar2(51) not null,
    progress varchar2(12) not null,
    current_money number(8) not null,
    join_count number not null,
    round number(2) not null,
    request_date varchar2(10) not null,
    
    employ_type varchar2(12) not null,
    scale varchar2(8) not null,
    serve number(2) not null,
    salary number(7) not null,
    
    loan_type varchar2(12) not null,
    repay_type varchar2(14) not null,
    interest_rate number(1) not null,
    loan_money number(8) not null,
    loan_date number(2) not null,
    repay number(2) not null,
    
    title varchar2(100) not null,
    fname varchar2(4000) not null,
    intro varchar2(4000) not null,
    expiry_date number(1) not null
);


alter table abc_loan
add constraint pk_loan_code primary key(loan_code);

alter table abc_loan
add constraint fk_loan_email foreign key(email) references abc_member(email);

create sequence seq_loan;

comment on table abc_loan is 'Loan table';
comment on column abc_loan.loan_code is 'loan code';
comment on column abc_loan.email is 'email';
comment on column abc_loan.progress is 'progression of loan';
comment on column abc_loan.current_money is 'current money';
comment on column abc_loan.join_count is 'join count';
comment on column abc_loan.round is 'round';
comment on column abc_loan.request_date is 'request date';
comment on column abc_loan.employ_type is 'employ type';
comment on column abc_loan.scale is 'scale';
comment on column abc_loan.serve is 'serve';
comment on column abc_loan.salary is 'salary';
comment on column abc_loan.loan_type is 'loan type';
comment on column abc_loan.repay_type is 'repay type';
comment on column abc_loan.interest_rate is 'interest rate';
comment on column abc_loan.loan_money is 'loan money';
comment on column abc_loan.loan_date is 'loan date';
comment on column abc_loan.repay is 'repay';
comment on column abc_loan.title is 'title';
comment on column abc_loan.fname is 'file name';
comment on column abc_loan.intro is 'intro';
comment on column abc_loan.expiry_date is 'expiry date';

-----------------------------------------------ABC_LOAN_TRANSACTION---------------------------------------------------
create table abc_loan_transaction(
    loan_seq number not null,
    loan_code number not null,
    stack_repay_rate number not null,
    stack_repay_origin number not null,
    progress varchar2(12) not null,
    collect_rate number(3) not null,
    round number(2) not null
);

create sequence seq_loan_transaction;

alter table abc_loan_transaction add(
    constraint pk_loan_seq primary key(loan_seq),
    constraint fk_loan_code foreign key(loan_code) references abc_loan(loan_code)
);

comment on table abc_loan_transaction is 'Loan transaction table';
comment on column abc_loan_transaction.loan_seq is 'loan sequence';
comment on column abc_loan_transaction.loan_code is 'loan code';
comment on column abc_loan_transaction.stack_repay_rate is 'stack repay rate';
comment on column abc_loan_transaction.stack_repay_origin is 'stack repay origin';
comment on column abc_loan_transaction.progress is 'proression of loan';
comment on column abc_loan_transaction.collect_rate is 'collect rate';
comment on column abc_loan_transaction.round is 'round';

-----------------------------------------------ABC_INVEST-------------------------------------------------------
create table abc_invest(
    invest_seq number not null,
    email varchar2(51) not null,
    loan_code number not null,
    invest_money number(8) not null
);

create sequence seq_invest;

alter table abc_invest add(
    constraint pk_invest_seq primary key(invest_seq),
    constraint fk_invest_email foreign key(email) references abc_member(email),
    constraint fk_invest_loan_code foreign key(loan_code) references abc_loan(loan_code)
);

comment on table abc_invest is 'Investment table';
comment on column abc_invest.invest_seq is 'investment sequence';
comment on column abc_invest.email is 'email';
comment on column abc_invest.loan_code is 'loan code';
comment on column abc_invest.invest_money is 'invest money';


-----------------------------------------------ABC_INVEST_TRANSACTION--------------------------------------------------
create table abc_invest_transaction(
    invest_transaction_seq number not null,
    invest_seq number not null,
    intend_profit number not null,
    stack_collect number not null,
    progress varchar2(12) not null,
    collect_rate number(3) not null,
    round number(2) not null
);

create sequence seq_invest_transaction;

alter table abc_invest_transaction add(
    constraint pk_invest_transaction_seq primary key(invest_transaction_seq),
    constraint fk_invest_seq foreign key(invest_seq) references abc_invest(invest_seq)
);

comment on table abc_invest_transaction is 'Investment transaction table';
comment on column abc_invest_transaction.invest_transaction_seq is 'investment transaction sequence';
comment on column abc_invest_transaction.invest_seq is 'investment sequence';
comment on column abc_invest_transaction.intend_profit is 'intend profit';
comment on column abc_invest_transaction.stack_collect is 'stack collect';
comment on column abc_invest_transaction.progress is 'progression of investment';
comment on column abc_invest_transaction.collect_rate is 'collect rate';
comment on column abc_invest_transaction.round is 'rounds';

-----------------------------------------------ABC_JUDGE-------------------------------------------------------
create table abc_judge(
    judge_seq number not null,
    loan_code number not null,
    commentary varchar2(2000) not null,
    result number(1) not null
);

create sequence seq_judge;

alter table abc_judge add(
    constraint pk_judge_seq primary key(judge_seq),
    constraint fk_judge_loan_code foreign key(loan_code) references abc_loan(loan_code)
);

comment on table abc_judge is 'Examination of loan table';
comment on column abc_judge.judge_seq is 'judge sequence';
comment on column abc_judge.loan_code is 'loan code';
comment on column abc_judge.commentary is 'commentary';
comment on column abc_judge.result is 'result of examination';

