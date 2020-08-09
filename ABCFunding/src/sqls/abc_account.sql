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
