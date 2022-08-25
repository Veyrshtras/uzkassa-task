drop table if exists employee;
drop table if exists company;


CREATE TABLE company
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       TIMESTAMP,
    last_modified_date TIMESTAMP,
    name               VARCHAR(255),
    address            VARCHAR(255),
    zip_code           BIGINT,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE employee
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       TIMESTAMP,
    last_modified_date TIMESTAMP,
    name               VARCHAR(255),
    username           VARCHAR(255),
    password           VARCHAR(255),
    email              VARCHAR(255),
    status             BOOLEAN,
    company_id         BIGINT                NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);

ALTER TABLE employee
    ADD CONSTRAINT uc_employee_email UNIQUE (email);

ALTER TABLE employee
    ADD CONSTRAINT FK_EMPLOYEE_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);