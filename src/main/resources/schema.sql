drop table basket;
select * from orders;
-- 멤버 테이블(member)
create table member
(
    id    long primary key auto_increment,
    email varchar(30)  not null,
    name  varchar(20)  not null,
    pw    varchar(100) not null,
    phone varchar(30)  not null,
    code  varchar(10)
);

-- 상품 테이블(goods)
create table goods
(
    id    long auto_increment primary key,
    name  varchar(20) not null,
    price int         not null,
    photo varchar(45) not null,
    title varchar(30) not null
);

-- 장바구니 테이블(basket)
create table basket
(
    id    LONG auto_increment primary key,
    count int  not null,
    midx  long not null,
    gidx  long not null,
    foreign key (midx) references member (id),
    foreign key (gidx) references goods (id)
);

-- 구매상품 테이블(buynow)
create table buynow
(
    gidx  long not null,
    oidx  long not null,
    count int  not null,
    foreign key (gidx) references goods (id),
    foreign key (oidx) references orders (id)
);

-- 투어 테이블(tour)
create table tour
(
    id      LONG auto_increment primary key,
    date    timestamp not null,
    current int       not null default 0,
    total   int       not null default 12,
    price   int       not null default 22000
);

-- 주소 테이블(address)
create table address
(
    id       LONG auto_increment primary key,
    address1 varchar(255) not null,
    address2 varchar(255),
    postcode varchar(20)  not null,
    midx     long         not null,
    foreign key (midx) references member (id)
);

-- 주문 테이블(order)
create table orders
(
    id       long auto_increment primary key,
    date     timestamp   not null default current_timestamp,
    category varchar(20) not null,
    price    int         not null,
    people   int,
    status   varchar(45) not null default 'pending',
    amount   int,
    tidx     long,
    midx     long        not null,
    aidx     long,
    foreign key (tidx) references tour (id),
    foreign key (midx) references member (id),
    foreign key (aidx) references address (id)
);
ALTER TABLE orders ADD FOREIGN KEY (gidx) REFERENCES goods (id);

-- 결제 테이블(payment)
create table payment
(
    id     long auto_increment primary key,
    price  int         not null,
    date   timestamp   not null,
    way    varchar(10) not null,
    status varchar(10) not null,
    oidx   int         not null,
    foreign key (oidx) references orders (id)
);

-- 리뷰 테이블(review)
create table review
(
    id       long auto_increment primary key,
    title    varchar(50) not null,
    contents longtext    not null,
    photo    varchar(45) not null,
    date     timestamp default current_timestamp,
    category varchar(20),
    rlikes   int       default 0,
    rate     smallint  default 0,
    pidx     int         not null,
    foreign key (pidx) references payment (id)
);

alter table review add name varchar(20);

-- 댓글 테이블(comment)
create table comment
(
    id   long auto_increment primary key,
    date timestamp not null default current_timestamp,
    text varchar(255) not null,
    ridx long  not null,
    midx long  not null,
    foreign key (ridx) references review (id),
    foreign key (midx) references member (id)
);

commit;

