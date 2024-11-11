CREATE TABLE vehicles(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(255),
    model VARCHAR(255),
    plate VARCHAR(255),
    vehicle_year INT,
    color VARCHAR(255),
    daily_price DECIMAL(10,2),
    available boolean
);

INSERT INTO vehicles(brand, model, plate, vehicle_year, color, daily_price, available)
VALUES ('Chevrolet', 'Celta', 'ABC-1234', 2010, 'Black', 500.00, true);

INSERT INTO vehicles(brand, model, plate, vehicle_year, color, daily_price, available)
VALUES ('Fiat', 'Uno', 'ABC-4444', 2012, 'White', 400.00, true);

CREATE TABLE person(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(255),
    telephone VARCHAR(255),
    email VARCHAR(255)
);

INSERT INTO person(name, cpf, telephone, email)
VALUES ('Vitor', '123.456.789.10', '(27)93618-4535', 'comettivitor@gmail.com');

CREATE TABLE rent(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    person_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    begin_date DATE,
    end_date DATE,
    total_price DECIMAL(10, 2),
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);

