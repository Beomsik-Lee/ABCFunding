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