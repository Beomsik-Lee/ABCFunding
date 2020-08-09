--drop table abc_invest_transaction;
--drop sequence seq_invest_transaction;

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