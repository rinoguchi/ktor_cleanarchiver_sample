drop table if exists memo;

create table memo (
  id serial
  , body text
  , primary key (id)
);

insert into memo (body)
values
  ('sample1')
  , ('sample2')
  , ('sample3')
;