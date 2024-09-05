CREATE TABLE supervisor (
    id_supervisor INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    registration VARCHAR(7) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(30) NOT NULL,
    entryPeriod VARCHAR(6) NOT NULL,
    id_user INT UNIQUE NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE
);