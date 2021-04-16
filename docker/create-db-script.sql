------------------------------------------------------------------------------
--                DATA BASE SCHEMA FOR THE DASHBOARD APPLICATION            --
------------------------------------------------------------------------------

-- This is the schema
CREATE SCHEMA IF NOT EXISTS dashboard;

-- User table. This is to keep track of the information linked to a user.
CREATE TABLE dashboard."user"
(
    id       SERIAL,
    userName VARCHAR(255) UNIQUE NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    token    VARCHAR(255),
    active   BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id),
    UNIQUE (email, userName)
);

-- This table contains the link between the different tables
-- All necessary information for a user interactable is stored in this table
CREATE TABLE dashboard."interactable"
(
    id SERIAL,
    PRIMARY KEY (id),
    CONSTRAINT FK_User
        FOREIGN KEY (id)
            REFERENCES dashboard."user" (id)
            ON DELETE NO ACTION
);

-- This table references the interactable data necessary to use
CREATE TABLE dashboard."interactable-setting"
(
    id     SERIAL,
    apiKey VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_Interactable_id_setting
        FOREIGN KEY (id)
            REFERENCES dashboard."interactable" (id)
            ON DELETE CASCADE,
    UNIQUE (id)
);

-- This table is used to display the information and actions for an interactable
CREATE TABLE dashboard."interactable-data"
(
    id       SERIAL,
    slug     VARCHAR(255),
    title    VARCHAR(255),
    imageUrl VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_Interactable_id_data
        FOREIGN KEY (id)
            REFERENCES dashboard."interactable" (id)
            ON DELETE CASCADE,
    UNIQUE (id)
);

------------------------------------------------------------------------------
--                CREATED SOME TEST DATA FOR THE APPLICATION                --
------------------------------------------------------------------------------
INSERT INTO dashboard."user" (username, email, password, token, active)
VALUES ('testUser', 'testUser@gmail.com', 'testPassword', 'testToken', true);

INSERT INTO dashboard."interactable"(id)
VALUES (1);

INSERT INTO dashboard."interactable-setting" (apikey)
VALUES ('testApiKey');

INSERT INTO dashboard."interactable-data" (slug, title, imageurl)
VALUES ('testSlug', 'testTitle', 'testImageUrl');

------------------------------------------------------------------------------
--         HAVE A BASIC QUERY WHICH CAN BE USED TO CHECK TEST DATA          --
------------------------------------------------------------------------------
SELECT *
FROM dashboard."user" U
         JOIN dashboard."interactable" I on U.id = I.id
         JOIN dashboard."interactable-data" D on I.id = D.id
         JOIN dashboard."interactable-setting" S on I.id = S.id;