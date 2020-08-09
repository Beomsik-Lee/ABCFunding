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