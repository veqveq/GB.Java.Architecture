create table products_tbl
(
    id_fld    bigint       not null auto_increment,
    title_fld varchar(255) not null,
    cost_fld  int      not null,
    primary key (id_fld)
);

INSERT INTO products_tbl (title_fld, cost_fld)
VALUES ('Product 1', 120),
       ('Product 2', 320),
       ('Product 3', 230),
       ('Product 4', 2021),
       ('Product 5', 220);