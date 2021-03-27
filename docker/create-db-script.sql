------------------------------------------------------------------------------
--                DATA BASE SCHEMA FOR THE DASHBOARD APPLICATION            --
------------------------------------------------------------------------------

-- This is the schema
CREATE SCHEMA IF NOT EXISTS dashboard;

-- User table. This is to keep track of the information linked to a user.
CREATE TABLE dashboard."User"
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
CREATE TABLE dashboard."Interactable"
(
    id SERIAL,
    PRIMARY KEY (id),
    CONSTRAINT FK_User
        FOREIGN KEY (id)
            REFERENCES dashboard."User" (id)
            ON DELETE NO ACTION
);

-- This table references the interactable data necessary to use
CREATE TABLE dashboard."InteractableSetting"
(
    id     SERIAL,
    apiKey VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_Interactable_id_setting
        FOREIGN KEY (id)
            REFERENCES dashboard."Interactable" (id)
            ON DELETE CASCADE,
    UNIQUE (id)
);

-- This table is used to display the information and actions for an interactable
CREATE TABLE dashboard."InteractableData"
(
    id       SERIAL,
    slug     VARCHAR(255),
    title    VARCHAR(255),
    imageUrl VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT FK_Interactable_id_data
        FOREIGN KEY (id)
            REFERENCES dashboard."Interactable" (id)
            ON DELETE CASCADE,
    UNIQUE (id)
);

------------------------------------------------------------------------------
--                CREATED SOME TEST DATA FOR THE APPLICATION                --
------------------------------------------------------------------------------
INSERT INTO dashboard."User" (username, email, password, token, active)
VALUES ('testUser', 'testUser@gmail.com', 'testPassword', 'testToken', true);

INSERT INTO dashboard."Interactable"(id)
VALUES (1);

INSERT INTO dashboard."InteractableSetting" (apikey)
VALUES ('testApiKey');

INSERT INTO dashboard."InteractableData" (slug, title, imageurl)
VALUES ('testSlug', 'testTitle', 'testImageUrl');

------------------------------------------------------------------------------
--         HAVE A BASIC QUERY WHICH CAN BE USED TO CHECK TEST DATA          --
------------------------------------------------------------------------------
SELECT *
FROM dashboard."User" U
         JOIN dashboard."Interactable" I on U.id = I.id
         JOIN dashboard."InteractableData" D on I.id = D.id
         JOIN dashboard."InteractableSetting" S on I.id = S.id;