CREATE TABLE IF NOT EXISTS posts (
    id             serial primary key,
    name           varchar(2000),
    description    text,
    created        timestamp without time zone not null default now(),
    user_id INT NOT NULL REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS comments (
    id             serial primary key,
    text           text,
    created        timestamp without time zone not null default now(),
    user_id INT NOT NULL REFERENCES users(id),
    post_id INT NOT NULL REFERENCES posts(id)
);

insert into posts (name) values ('О чем этот форум?');
insert into posts (name) values ('Правила форума.');