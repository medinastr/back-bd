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
    built_year INT NOT NULL,
    owner VARCHAR(100) NOT NULL,
    address_id INT UNIQUE,
    CONSTRAINT fk_stadium_address_id FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE CASCADE
);

CREATE TABLE team (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    founded_year INT NOT NULL,
    fifa_ranking INT NOT NULL,
    market_value INT NOT NULL,
    stadium_id INT UNIQUE,
    CONSTRAINT fk_team_stadium_id FOREIGN KEY (stadium_id) REFERENCES stadium (id) ON DELETE SET NULL
);

ALTER TABLE stadium
ADD COLUMN team_id INT;

ALTER TABLE stadium
ADD CONSTRAINT fk_stadium_team_id
FOREIGN KEY (team_id)
REFERENCES team (id)
ON DELETE SET NULL;

CREATE TABLE player (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(50),
    shirt_number INT,
    market_value INT,
    team_id INT,
    address_id INT UNIQUE,
    CONSTRAINT fk_player_team_id
    FOREIGN KEY (team_id)
    REFERENCES team (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_address_id FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE CASCADE
);

CREATE TABLE championship (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    edition INT,
    start_date VARCHAR(20),
    end_date VARCHAR(20)
);

CREATE TABLE team_championship (
    team_id INT,
    championship_id INT,
    PRIMARY KEY (team_id, championship_id),
    FOREIGN KEY (team_id) REFERENCES team(id) ON DELETE CASCADE,
    FOREIGN KEY (championship_id) REFERENCES championship(id) ON DELETE CASCADE
);
