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
