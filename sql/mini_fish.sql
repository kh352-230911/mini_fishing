
alter session set "_oracle_script" = true;

create user fish
identified by fish;

grant connect, resource to fish;
alter user fish quota unlimited on users;



select * from person where id = 'kmj'
-- commit;create table person (
    id varchar2(50),
    pw varchar2(50) not null,
    name varchar2(50) not null,
    score_per_second number default 0,
    sum_point number default 0,
    sum_milli number default 0,

    constraints pk_person_id primary key(id)
);
update person set score_per_second = 0, sum_point =0, sum_milli = 0;

select * from person;

-- commit;
-- drop table person;

create table fishes (
    fish_id number,
    fish_Name varchar(30) not null,
    point number,
    max_size number,
    min_size number,

    constraints uq_fishes_fishNum unique(fish_id),
    constraints uq_fishes_fishName unique(fish_name)
);
-- drop table fishes;

create table catchFishes (
    id varchar2(50) not null,
    fish_name varchar(30),
    catch_fish_size number default 0,
    point number default 0,
    catch_at date default sysdate,
    
    constraints catchFishes_id foreign key(id) references person(id)
    on delete cascade,
    constraints catchFishes_fish_name foreign key(fish_Name) references fishes(fish_name)
    on delete cascade
    
);

-- drop table catchFishes;

create table fishrank(
    id varchar2(50) not null,
    name varchar2(50) not null,
    sum_point number default 0,
    
    constraints fishrank_id foreign key(id) references person(id)
    on delete cascade
);

-- drop table fishrank;

-- drop table person;
-- drop table fishes;
-- drop table catchFishes;
-- drop table fishrank;


-- commit;
select * from fishrank;

select * from person where id = 'kmj'


select * from person;
select * from fishes;
select * from catchFishes;

insert into person values('taegong', 'taegong', '강태공', default, default, default);
insert into person values('jax', 'jax', '잭스', default, default,  default);
insert into person values('osh', '오승현', '오승현', default,  default,  default);
insert into person values('kmj', '김명준', '김명준', default,  default, default);

insert into fishes values(1, '멸치', 50, 4, 10);
insert into fishes values(2, '청어', 250, 10, 20);
insert into fishes values(3, '전어', 500, 10, 20);
insert into fishes values(4, '밴댕이', 500, 20, 30);
insert into fishes values(5, '꽁치', 1000,  20, 30);
insert into fishes values(6, '볼락', 2500, 30, 60);
insert into fishes values(7, '고등어', 2500, 20, 40);
insert into fishes values(8, '삼치', 2500, 50, 70);
insert into fishes values(9, '다랑어', 50000, 100, 200);
insert into fishes values(10, '농어', 10000, 30,  50);
insert into fishes values(11, '감성돔', 50000, 20, 30);
insert into fishes values(12, '참돔', 50000, 20, 30);
insert into fishes values(13, '돌돔', 100000, 20, 30);
insert into fishes values(14, '민어', 10000, 20, 30);
insert into fishes values(15, '참조기', 500, 20, 30);
insert into fishes values(16, '갈돔', 150000, 20, 30);
insert into fishes values(17, '날새기', 25000, 20, 30);
insert into fishes values(18, '전갱이', 500, 20, 30);
insert into fishes values(19, '방어', 10000, 20, 30);
insert into fishes values(20, '황새치', 50000, 20, 30);
insert into fishes values(21, '붉바리', 150000, 20, 30);
insert into fishes values(22, '메기', 50000, 20, 30);
insert into fishes values(23, '연어', 50000, 20, 30);
insert into fishes values(24, '점성어', 500, 20, 30);
insert into fishes values(25, '은어', 500, 20, 30);
insert into fishes values(26, '잉어', 10000, 20, 30);
insert into fishes values(27, '병어', 10000, 20, 30);
insert into fishes values(28, '보리멸', 500, 20, 30);
insert into fishes values(29, '장어', 25000, 20, 30);
insert into fishes values(30, '붕어', 250, 20, 30);
insert into fishes values(31, '임연수어', 1000, 20, 30);
insert into fishes values(32, '조기', 500, 20, 30);
insert into fishes values(33, '산천어', 20000, 20, 30);
insert into fishes values(34, '쥐치', 10000, 20, 30);
insert into fishes values(35, '열빙어', 500, 20, 30);
insert into fishes values(36, '우럭', 20000, 20, 30);
insert into fishes values(37, '광어', 30000, 20, 30);
insert into fishes values(38, '부세', 500, 20, 30);
insert into fishes values(39, '쏘가리', 200000, 20, 30);
insert into fishes values(40, '독가시치', 500, 20, 30);
insert into fishes values(41, '배스', 10000, 20, 30);
insert into fishes values(42, '가물치', 50000, 20, 30);
insert into fishes values(43, '대구', 100000, 20, 30);
insert into fishes values(44, '망상어', 1000, 20, 30);
insert into fishes values(45, '물메기', 100000, 20, 30);
insert into fishes values(46, '달고기', 30000, 20, 30);
insert into fishes values(47, '청새치', 40000, 20, 30);
insert into fishes values(48, '시샤모', 150000, 20, 30);
insert into fishes values(49, '만새기', 80000, 20, 30);
insert into fishes values(50, '재방어', 200000, 20, 30);


create or replace trigger catch_fish_point
    after 
    insert on catchFishes
    for each row
begin
    update 
        person
    set
        sum_point = sum_point + :new.point
    where
        id = :new.id;
end;
/

insert into catchFishes values('jax', '대구', default, 100000, default);
insert into catchFishes values('jax', '시샤모', default, 150000, default);
insert into catchFishes values('taegong', '쏘가리', default, 200000, default);
insert into catchFishes values('taegong', '재방어', default, 200000, default);

select * from person order by sum_point desc;
select * from fishes;
select * from catchFishes;
commit;

 SELECT * FROM USER_CONSTRAINTS
select * from tb_fish
drop table tb_fish;
