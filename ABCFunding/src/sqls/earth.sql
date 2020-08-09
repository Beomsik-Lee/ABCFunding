-- �Ŀ���û, �ڵ�, �Ŀ����ڵ� ���̺� ���� 
drop table earth cascade constraint;
drop table sponsors cascade constraint;
drop table scodes cascade constraint;
drop table ecodes cascade constraint;
drop sequence seq_earthno;
drop sequence seq_sponsorno;
create sequence seq_earthno 
start with 1 increment by 1;

create sequence seq_sponsorno
start with 1 increment by 1 ;


create table earth(
	earthno number not null,
	name varchar2(12) not null,
	pw number not null,
	phone varchar2(13) not null,
	title varchar2(200) not null,
	purpose varchar2(400) not null,
	condition varchar2(100) not null,
	content varchar2(4000) not null,
	earths date not null,
	earthe date not null,
	targetno number,
	targetsum number,
	resultno number,
	resultsum number,
	result varchar2(10) not null,
	sponsorno number,
	code number not null,
	primary key(earthno)
);

create table sponsors(
	sponsorno number not null,
	sponsorid varchar2(40) not null,
	code number not null,
	primary key(sponsorno)
);

alter table earth 
add constraint fk_earth_sponsors foreign key(sponsorno) references sponsors(sponsorno);

create table scodes(
	code number not null,
	name varchar2(20) not null,
	primary key(code)
);


alter table sponsors 
add constraint fk_sponsors_codes foreign key(code)  references scodes(code);


create table ecodes(
	code number not null,
	name varchar2(20) not null,
	primary key(code)
);

alter table earth 
add constraint fk_earth_ecodes foreign key(code)  references ecodes(code);



insert into scodes(code,name) values(10,'���(Company)');
insert into scodes(code,name) values(20,'��ü(Organization)');
insert into scodes(code,name) values(30,'����(Person)');

insert into ecodes(code,name) values(1,'��û�Ϸ�');
insert into ecodes(code,name) values(2,'ó����');
insert into ecodes(code,name) values(3,'��ϿϷ�');
insert into ecodes(code,name) values(4,'�Ŀ�����');
insert into ecodes(code,name) values(5,'�����Ϸ�');


insert into sponsors(sponsorno,sponsorid,code) values(1,'(��)���۽�',10);
insert into sponsors(sponsorno,sponsorid,code) values(2,'(��)��Ʈ��',10);
insert into sponsors(sponsorno,sponsorid,code) values(4,'(��)���۸�',20);
insert into sponsors(sponsorno,sponsorid,code) values(3,'�����',30);
insert into sponsors(sponsorno,sponsorid,code) values(5,'������',10);
--INSERT INTO EARTH VALUES(SEQ_EARTHNO.NEXTVAL, '��������', 1234, '010-9164-6979', '�ݿ����� �� �ҹ漳�� �Ŀ�', '(��)��Ʈ�� ȭ�� ���� �ҹ漳�� ����', '�Ϲ� ���� �� ����','�����ڴ� �ݿ� �� �Ϸ� ���� ���ध �ʤ� �� �ִ� �ݾ��� �Է��Ѵ�',
--'2016-07-14','2016-08-11',200,5000000,'','','�̴޼�',1,1);
--INSERT INTO EARTH VALUES(SEQ_EARTHNO.NEXTVAL, '��������', 1234, '010-9164-6979', '�ݿ����� �� �ҹ漳�� �Ŀ�', '(��)��Ʈ�� ȭ�� ���� �ҹ漳�� ����', '�Ϲ� ���� �� ����','�����ڴ� �ݿ� �� �Ϸ� ���� ���ध �ʤ� �� �ִ� �ݾ��� �Է��Ѵ�',
--'2016-07-14','2016-08-11',200000000,5000000,'','','�̴޼�',1,3);
--
--SELECT * FROM EARTH; --�Ŀ� ��û���̺� ��ü ��ȸ - 
--SELECT S.SPONSORID FROM SPONSORS S, EARTH E WHERE S.SPONSORNO = E.SPONSORNO; -- ��������ȸ--
--SELECT SPONSORID FROM SPONSORS, EARTH WHERE EARTH.SPONSORNO = SPONSORS.SPONSORNO AND EARTH.EARTHNO=1; -- 1����ϰԽù� �������� --


---------------------------------------------------------------------------------------------
-- �Ŀ� ��� ���̺� ���� 
drop table applybackup cascade constraint; 
create table applybackup(
	earthno number not null,
	name varchar2(12) not null,
	pw number not null,
	phone varchar2(13) not null,
	title varchar2(200) not null,
	purpose varchar2(400) not null,
	condition varchar2(100) not null,
	content varchar2(4000) not null,
	earths date not null,
	earthe date not null,
	targetno number,
	targetsum number,
	primary key(earthno)
);

alter table applybackup 
add constraint fk_applybackup_earth foreign key(earthno) references earth(earthno);

--insert into applybackup values(1, '��������', 1234, '010-9164-6979', '�ݿ����� �� �ҹ漳�� �Ŀ�', '(��)��Ʈ�� ȭ�� ���� �ҹ漳�� ����', '�Ϲ� ���� �� ����','�����ڴ� �ݿ� �� �Ϸ� ���� ���ध �ʤ� �� �ִ� �ݾ��� �Է��Ѵ�',
--'2016-07-14','2016-08-11',200,5000000); -- �Ŀ���û���̺��� ��û�� �������� ����� �������� Ȯ�� earthno�� �ĺ�--


-------------------------------------------------------------------------------------------
--�Ŀ� ��� ��� ���
drop table comments cascade constraint; 
create table comments(
	replyno number not null, 
	name varchar2(12) not null,
	birth varchar2(8) not null,
	regdate date not null,
	recomment varchar2(1000) not null,
	donation number,
	earthno number not null,
	primary key(replyno)
);
alter table comments 
add constraint fk_comments_earth foreign key(earthno) references earth(earthno);

insert into comments values(1,'park','910813','2016-05-14','���յ�.','',1);
insert into comments values(2,'parkc','910313','2016-05-14','���յ�.','',1);
insert into comments values(2,'parkc','910313','2016-05-14','���յ�.','',3); --���� �Ŀ���ȣ + ���� ��Ϲ�ȣ ��۽�û�̾ȵ�--

drop table commentfile cascade constraint; 
create table commentfile(
	replyno number not null,  
	applyname varchar2(100) not null,
	applypath varchar2(100) not null,
	applysavename varchar2(100) not null,
	primary key(replyno)
);

alter table commentfile 
add constraint fk_commentfile_comments foreign key(replyno) references comments(replyno);

--insert into commentfile values(1,'��Ʈ��.jpg','c:qweqwe.��Ʈ��.jpg','asdfsdf.��Ʈ��.jpg');
--insert into commentfile values(2,'��Ʈ��.jpg','c:qweqwe.��Ʈ��.jpg','asdfsdf.��Ʈ��.jpg'); 
--insert into commentfile values(3,'��Ʈ��.jpg','c:qweqwe.��Ʈ��.jpg','asdfsdf.��Ʈ��.jpg'); --�����ϴ� ��ۿ��� �߰���--
--select * from comments;
--SELECT C.REPLYNO, C.DONATION, C.RECOMMENT, F.APPLYNAME, F.APPLYPATH, F.APPLYSAVENAME FROM COMMENTS C, COMMENTFILE F, EARTH E
--WHERE C.REPLYNO = F.REPLYNO AND E.EARTHNO = C.EARTHNO AND E.EARTHNO=1; --��ϵǾ��ִ� 1�� �Խù��� - ����� - ��� ���� �����ȸ 
-------------------------------------------------------------------------------------------
--�Ŀ� ���� ���̺� ���� 

drop table earthfile cascade constraint; 

create table earthfile(
	earthno number not null,
	applyname varchar2(100) not null, 
	applypath varchar2(100) not null,
	applysavename varchar2(100) not null,
	reginame varchar2(100),
	regipath varchar2(100),
	regisavename varchar2(100),
	resultname varchar2(100),
	resultpath varchar2(100),
	resultsavename varchar2(100),
	primary key(earthno)
);

alter table earthfile 
add constraint fk_earthfile_earth foreign key(earthno) references earth(earthno);

-------------------------------------------------------------------------------------------
--�Ŀ� �� ���̺� 

drop table earthd cascade constraint; 

create table earthd(
	earthno number not null,
	donationcheck char(1) not null,
	standard char(1) not null,
	donationmax number,
	donationtext varchar2(200),
	filecheck char(1) not null,
	filetext varchar2(200),
	primary key(earthno)
);

alter table earthd 
add constraint fk_earthd_earth foreign key(earthno) references earth(earthno);

insert into earthd values(1,'Y','Y',500000,'��αݾ��Է´ܸ�','Y','�����Է�');
insert into earthd values(44,'Y','Y',500000,'��αݾ��Է´ܸ�','Y','�����Է�'); -- ������ ��Ϲ�ȣ �������� �ۼ����� --
