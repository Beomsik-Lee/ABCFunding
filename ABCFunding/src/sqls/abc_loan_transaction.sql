--drop table abc_loan_transaction;
--drop sequence seq_loan_transaction;
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