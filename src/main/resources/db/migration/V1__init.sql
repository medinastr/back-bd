CREATE TABLE address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL
);

CREATE TABLE stadium (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    capacity INT NOT NULL,
    address_id INT UNIQUE,
    FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE CASCADE
);

CREATE TABLE team (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    founded_year INT NOT NULL,
    stadium_id INT UNIQUE,
    FOREIGN KEY (stadium_id) REFERENCES stadium (id) ON DELETE SET NULL
);

CREATE TABLE player (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(50) NOT NULL,
    team_id INT,
    address_id INT UNIQUE,
    FOREIGN KEY (team_id) REFERENCES team (id) ON DELETE SET NULL,
    FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE CASCADE
);

CREATE TABLE championship (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE team_championship (
    team_id INT,
    championship_id INT,
    PRIMARY KEY (team_id, championship_id),
    FOREIGN KEY (team_id) REFERENCES team(id) ON DELETE CASCADE,
    FOREIGN KEY (championship_id) REFERENCES championship(id) ON DELETE CASCADE
);
