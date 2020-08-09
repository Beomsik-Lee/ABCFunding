-- 후원신청, 코드, 후원자코드 테이블 생성 
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



insert into scodes(code,name) values(10,'기업(Company)');
insert into scodes(code,name) values(20,'단체(Organization)');
insert into scodes(code,name) values(30,'개인(Person)');

insert into ecodes(code,name) values(1,'신청완료');
insert into ecodes(code,name) values(2,'처리중');
insert into ecodes(code,name) values(3,'등록완료');
insert into ecodes(code,name) values(4,'후원종료');
insert into ecodes(code,name) values(5,'마감완료');


insert into sponsors(sponsorno,sponsorid,code) values(1,'(주)아작스',10);
insert into sponsors(sponsorno,sponsorid,code) values(2,'(주)배트맨',10);
insert into sponsors(sponsorno,sponsorid,code) values(4,'(주)슈퍼맨',20);
insert into sponsors(sponsorno,sponsorid,code) values(3,'도라맨',30);
insert into sponsors(sponsorno,sponsorid,code) values(5,'오감자',10);
--INSERT INTO EARTH VALUES(SEQ_EARTHNO.NEXTVAL, '알프레드', 1234, '010-9164-6979', '금연유도 및 소방설비 후원', '(주)배트맨 화재 예방 소방설비 지원', '일반 국민 중 흡연자','참여자는 금연 시 하루 동안 저축ㄷ ㅚㄹ 수 있는 금액을 입력한다',
--'2016-07-14','2016-08-11',200,5000000,'','','미달성',1,1);
--INSERT INTO EARTH VALUES(SEQ_EARTHNO.NEXTVAL, '알프레드', 1234, '010-9164-6979', '금연유도 및 소방설비 후원', '(주)배트맨 화재 예방 소방설비 지원', '일반 국민 중 흡연자','참여자는 금연 시 하루 동안 저축ㄷ ㅚㄹ 수 있는 금액을 입력한다',
--'2016-07-14','2016-08-11',200000000,5000000,'','','미달성',1,3);
--
--SELECT * FROM EARTH; --후원 신청테이블 전체 조회 - 
--SELECT S.SPONSORID FROM SPONSORS S, EARTH E WHERE S.SPONSORNO = E.SPONSORNO; -- 스폰서조회--
--SELECT SPONSORID FROM SPONSORS, EARTH WHERE EARTH.SPONSORNO = SPONSORS.SPONSORNO AND EARTH.EARTHNO=1; -- 1번등록게시물 스폰서명 --


---------------------------------------------------------------------------------------------
-- 후원 백업 테이블 생성 
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

--insert into applybackup values(1, '알프레드', 1234, '010-9164-6979', '금연유도 및 소방설비 후원', '(주)배트맨 화재 예방 소방설비 지원', '일반 국민 중 흡연자','참여자는 금연 시 하루 동안 저축ㄷ ㅚㄹ 수 있는 금액을 입력한다',
--'2016-07-14','2016-08-11',200,5000000); -- 후원신청테이블에서 신청된 내용으로 백업이 가능한지 확인 earthno로 식별--


-------------------------------------------------------------------------------------------
--후원 댓글 목록 등록
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

insert into comments values(1,'park','910813','2016-05-14','멋잇따.','',1);
insert into comments values(2,'parkc','910313','2016-05-14','멋잇따.','',1);
insert into comments values(2,'parkc','910313','2016-05-14','멋잇따.','',3); --없는 후원번호 + 없는 등록번호 댓글신청이안됨--

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

--insert into commentfile values(1,'배트맨.jpg','c:qweqwe.배트맨.jpg','asdfsdf.배트맨.jpg');
--insert into commentfile values(2,'배트맨.jpg','c:qweqwe.배트맨.jpg','asdfsdf.배트맨.jpg'); 
--insert into commentfile values(3,'배트맨.jpg','c:qweqwe.배트맨.jpg','asdfsdf.배트맨.jpg'); --존재하는 댓글에만 추가됨--
--select * from comments;
--SELECT C.REPLYNO, C.DONATION, C.RECOMMENT, F.APPLYNAME, F.APPLYPATH, F.APPLYSAVENAME FROM COMMENTS C, COMMENTFILE F, EARTH E
--WHERE C.REPLYNO = F.REPLYNO AND E.EARTHNO = C.EARTHNO AND E.EARTHNO=1; --등록되어있는 1번 게시물의 - 댓글의 - 댓글 파일 모두조회 
-------------------------------------------------------------------------------------------
--후원 파일 테이블 생성 

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
--후원 상세 테이블 

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

insert into earthd values(1,'Y','Y',500000,'기부금액입력단명','Y','파일입력');
insert into earthd values(44,'Y','Y',500000,'기부금액입력단명','Y','파일입력'); -- 지정된 등록번호 값만으로 작성가능 --
