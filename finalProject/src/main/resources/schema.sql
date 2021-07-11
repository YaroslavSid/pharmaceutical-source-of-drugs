CREATE TABLE manufacturer (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) NOT NULL
);

CREATE TABLE drugs (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    date_of_approval datetime,
    description varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    treatment_for varchar(255) NOT NULL,
    manufacturer_id INTEGER,
    FOREIGN KEY (manufacturer_id)  REFERENCES manufacturer (id)
);