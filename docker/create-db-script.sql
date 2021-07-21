------------------------------------------------------------------------------
--                DATA BASE SCHEMA FOR THE DASHBOARD APPLICATION            --
------------------------------------------------------------------------------

-- This is the schema
CREATE SCHEMA IF NOT EXISTS dashboard;

-- Person table. This is to keep track of the information linked to a person.
CREATE TABLE dashboard.person
(
    id            UUID UNIQUE         NOT NULL,
    username      VARCHAR(255) UNIQUE NOT NULL,
    email         VARCHAR(255) UNIQUE NOT NULL,
    password      VARCHAR(255)        NOT NULL,
    active        BOOLEAN DEFAULT FALSE,
    creation_date DATE,
    token_id      UUID UNIQUE,
    PRIMARY KEY (id),
    UNIQUE (username, password)
);

-- Role table. Within this table all the roles are stored which are used in the application.
CREATE TABLE dashboard.role
(
    id   UUID,
    name VARCHAR(20) UNIQUE NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id, name)
);

-- This table creates the link between the person table and role table.
-- These tables have both a many to many relationship.
CREATE TABLE dashboard.person_role
(
    person_id UUID NOT NULL,
    role_id   UUID NOT NULL,
    CONSTRAINT FK_person_id
        FOREIGN KEY (person_id)
            REFERENCES dashboard.person (id)
            ON DELETE CASCADE,
    CONSTRAINT FK_role_id
        FOREIGN KEY (role_id)
            REFERENCES dashboard.role (id)
            ON DELETE NO ACTION
);

-- This table is used to store tokens for the registration process.
-- Table stores only the token and a link to the person. The expire_date will be set in the code.
CREATE TABLE dashboard.token
(
    id          UUID NOT NULL,
    token       UUID NOT NULL,
    expiry_date DATE,
    person_id   UUID NOT NULL,
    CONSTRAINT FK_person_id
        FOREIGN KEY (person_id)
            REFERENCES dashboard.person (id)
            ON DELETE CASCADE
);

-- This table contains the link between the different tables
-- All necessary information for a user interactable is stored in this table
CREATE TABLE dashboard.interactable
(
    id UUID,
    PRIMARY KEY (id),
    CONSTRAINT FK_person
        FOREIGN KEY (id)
            REFERENCES dashboard.person (id)
            ON DELETE NO ACTION
);

-- This table references the interactable data necessary to use
CREATE TABLE dashboard.interactable_setting
(
    id     UUID,
    apiKey VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_interactable_id_setting
        FOREIGN KEY (id)
            REFERENCES dashboard.interactable (id)
            ON DELETE CASCADE,
    UNIQUE (id)
);

-- This table is used to display the information and actions for an interactable
CREATE TABLE dashboard.interactable_data
(
    id       UUID,
    slug     VARCHAR(255),
    title    VARCHAR(255),
    imageUrl VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_interactable_id_data
        FOREIGN KEY (id)
            REFERENCES dashboard.interactable (id)
            ON DELETE CASCADE,
    UNIQUE (id)
);

------------------------------------------------------------------------------
--                CREATED SOME TEST DATA FOR THE APPLICATION                --
------------------------------------------------------------------------------
INSERT INTO dashboard.person(id, username, email, password, active, creation_date, token_id)
VALUES ('b6e38a2a-334d-4ed2-8f05-b1ca03e9397e', 'person', 'test.person@gmail.com',
        '$2a$10$2VMmPh4CCyWvjSxMfkfEm.mBxXcg92VkiRHNhbYb.OL6a/bBiirr2', true, CURRENT_DATE, NULL),
       ('e2143053-0d87-4a7f-9d4e-60e0ea52e4ff', 'member', 'test.member@gmail.com',
        '$2a$10$s6aC3XaN/sAQC7mhA8k22eF2HSrdKmyGUmyzftorxmBN4hm2u0yeq', true, CURRENT_DATE, NULL),
       ('97abd3b4-b339-4a50-8cb6-06d71e85588c', 'admin', 'test.admin@gmail.com',
        '$2a$10$1OW8q.KehgIHbAigXyizf.cyft7rnaHPTNga4tlUoNc94uGoYD7QW', true, CURRENT_DATE, NULL);

INSERT INTO dashboard.role(id, name)
VALUES ('c2024168-35df-4c47-a2d6-aaf488921675', 'ROLE_USER'),
       ('1e61e0fb-1e5f-4729-b565-46fadb3287cc', 'ROLE_MEMBER'),
       ('7bdde250-5ed0-45e5-8c9d-0e135de1d0ed', 'ROLE_ADMIN');

INSERT INTO dashboard.person_role(person_id, role_id)
VALUES ('b6e38a2a-334d-4ed2-8f05-b1ca03e9397e', 'c2024168-35df-4c47-a2d6-aaf488921675'),
       ('e2143053-0d87-4a7f-9d4e-60e0ea52e4ff', 'c2024168-35df-4c47-a2d6-aaf488921675'),
       ('e2143053-0d87-4a7f-9d4e-60e0ea52e4ff', '1e61e0fb-1e5f-4729-b565-46fadb3287cc'),
       ('97abd3b4-b339-4a50-8cb6-06d71e85588c', 'c2024168-35df-4c47-a2d6-aaf488921675'),
       ('97abd3b4-b339-4a50-8cb6-06d71e85588c', '1e61e0fb-1e5f-4729-b565-46fadb3287cc'),
       ('97abd3b4-b339-4a50-8cb6-06d71e85588c', '7bdde250-5ed0-45e5-8c9d-0e135de1d0ed');