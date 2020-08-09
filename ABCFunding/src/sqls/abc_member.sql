--drop table abc_member;
create table abc_member(
  email varchar2(51),
  pwd varchar2(20) not null,
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