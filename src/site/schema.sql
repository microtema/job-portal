drop table address;
drop table jobentry_appointment;
drop table appointment;
drop table jobentry;
drop table jobentry_tagentry;
drop table tagentry;
drop table person;
drop table salary;

CREATE TABLE Address
(
    id NUMERIC(19) PRIMARY KEY NOT NULL IDENTITY,
    createdDate DATETIME,
    updateDate DATETIME,
    version NUMERIC(19),
    city VARCHAR(255),
    country VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    mobile VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    street VARCHAR(255),
    streetNumber VARCHAR(255),
    zip VARCHAR(255)
);
CREATE TABLE Appointment
(
    id NUMERIC(19) PRIMARY KEY NOT NULL IDENTITY,
    createdDate DATETIME,
    updateDate DATETIME,
    version NUMERIC(19),
    appointed VARCHAR(255),
    date DATETIME NOT NULL,
    location VARCHAR(255) NOT NULL,
    purpose VARCHAR(255),
    time DATETIME NOT NULL
);
CREATE TABLE JobEntry
(
    id NUMERIC(19) PRIMARY KEY NOT NULL IDENTITY,
    createdDate DATETIME,
    updateDate DATETIME,
    version NUMERIC(19),
    description VARCHAR(255) NOT NULL,
    favorite INT NOT NULL,
    state VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(255),
    humanResource_id NUMERIC(19),
    jobHunter_id NUMERIC(19),
    salary_id NUMERIC(19)
);
CREATE TABLE JobEntry_Appointment
(
    JobEntry_id NUMERIC(19) NOT NULL,
    appointments_id NUMERIC(19) NOT NULL
);
CREATE TABLE JobEntry_TagEntry
(
    JobEntry_id NUMERIC(19) NOT NULL,
    labels_id NUMERIC(19) NOT NULL,
    CONSTRAINT PK_JobEntry_Labels PRIMARY KEY (JobEntry_id, labels_id)
);
CREATE TABLE Person
(
    id NUMERIC(19) PRIMARY KEY NOT NULL IDENTITY,
    createdDate DATETIME,
    updateDate DATETIME,
    version NUMERIC(19),
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    address_id NUMERIC(19)
);
CREATE TABLE Salary
(
    id NUMERIC(19) PRIMARY KEY NOT NULL IDENTITY,
    createdDate DATETIME,
    updateDate DATETIME,
    version NUMERIC(19),
    max NUMERIC(19,2) NOT NULL,
    min NUMERIC(19,2) NOT NULL
);
CREATE TABLE TagEntry
(
    id NUMERIC(19) PRIMARY KEY NOT NULL IDENTITY,
    createdDate DATETIME,
    updateDate DATETIME,
    version NUMERIC(19),
    label VARCHAR(255)
);
ALTER TABLE JobEntry ADD FOREIGN KEY (humanResource_id) REFERENCES Person (id);
ALTER TABLE JobEntry ADD FOREIGN KEY (jobHunter_id) REFERENCES Person (id);
ALTER TABLE JobEntry ADD FOREIGN KEY (salary_id) REFERENCES Salary (id);
ALTER TABLE JobEntry_Appointment ADD FOREIGN KEY (JobEntry_id) REFERENCES JobEntry (id);
ALTER TABLE JobEntry_Appointment ADD FOREIGN KEY (appointments_id) REFERENCES Appointment (id);
CREATE UNIQUE INDEX IDX_JobEntry_Appointments ON JobEntry_Appointment (appointments_id);
ALTER TABLE JobEntry_TagEntry ADD FOREIGN KEY (JobEntry_id) REFERENCES JobEntry (id);
ALTER TABLE JobEntry_TagEntry ADD FOREIGN KEY (labels_id) REFERENCES TagEntry (id);
ALTER TABLE Person ADD FOREIGN KEY (address_id) REFERENCES Address (id);