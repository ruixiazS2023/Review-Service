drop table if exists review;
Create table review (
    rid char(8) primary key,
    content text,
    date timestamp,
    uid char(8),
    parent_rid char(8),
    topic_id char(8)
);
