create table orders (id bigint generated by default as identity, no varchar(255), total numeric(38,2), primary key (id));

alter table if exists orders drop constraint if exists UK43egxxciqr9ncgmxbdx2avi8n;
alter table if exists orders add constraint UK43egxxciqr9ncgmxbdx2avi8n unique (no);

insert into orders (no,total,id) values (?,?,default);
create sequence order_seq start with 1 increment by 50;