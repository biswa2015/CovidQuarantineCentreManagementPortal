use CQCMP;

create table admin_info(
    admin_id varchar(50),
    emailid varchar(40),
    adminname varchar(20),
    adminpassword varchar(20),
    constraint PRIMARY KEY(admin_id)
);

create table rooms(
    room_id varchar(50),
    roomNumber INT,
    floorNumber INT,
    constraint PRIMARY KEY(room_id)
);

create table students(
    student_id varchar(50),
    sname varchar(40),
    emailID varchar(40),
    contact LONG ,
    roll_num varchar(20),
    constraint PRIMARY KEY(student_id)
);

create table tests(
    test_id varchar(50),
    student_id varchar(50),
    result varchar(12),
    constraint PRIMARY KEY(test_id)
);

create table allocation(
    alloc_id varchar(50),
    room_id varchar(50),
    student_id varchar(50),
    constraint PRIMARY KEY(alloc_id)
);

alter table rooms add isFree int;

alter table tests add constraint fk_test FOREIGN KEY(student_id) references students(student_id);

alter table students modify contact char(10);

alter table allocation add constraint fk_alloc FOREIGN KEY(room_id) references rooms(room_id);

alter table allocation add constraint fk_alloc2 FOREIGN KEY(student_id) references students(student_id);

alter table allocation add startDate varchar(100);

alter table allocation add endDate varchar(100);

alter table tests add testDate varchar(100);

alter table allocation add vacated int;
