CREATE TABLE IF NOT EXISTS ExpenseCategory (
    id LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Expense (
    id LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
    amount DOUBLE NOT NULL,
    category_id LONG NOT NULL,
    category_name VARCHAR(30) NOT NULL,
    date VARCHAR(20),
    FOREIGN KEY (category_id) REFERENCES ExpenseCategory(id)
);

-- Inserts para ExpenseCategory
INSERT INTO ExpenseCategory (name) VALUES ('Comida');
INSERT INTO ExpenseCategory (name) VALUES ('Transporte');
INSERT INTO ExpenseCategory (name) VALUES ('Entretenimiento');
INSERT INTO ExpenseCategory (name) VALUES ('Salud');
INSERT INTO ExpenseCategory (name) VALUES ('Educación');

-- Inserts para Expense
INSERT INTO Expense (amount, category_id, category_name, date) VALUES (50.00, 1, 'Comida', '18/12/23');
INSERT INTO Expense (amount, category_id, category_name, date) VALUES (20.00, 2, 'Transporte', '19/12/23');
INSERT INTO Expense (amount, category_id, category_name, date) VALUES (30.00, 3, 'Entretenimiento', '18/12/23');
INSERT INTO Expense (amount, category_id, category_name, date) VALUES (10.00, 4, 'Salud', '20/12/23');
INSERT INTO Expense (amount, category_id, category_name, date) VALUES (15.00, 5, 'Educación', '14/12/23');
INSERT INTO Expense (amount, category_id, category_name, date) VALUES (40.00, 1, 'Comida', '16/12/23');
INSERT INTO Expense (amount, category_id, category_name, date) VALUES (23.00, 1, 'Comida', '18/12/23');
INSERT INTO Expense (amount, category_id, category_name, date) VALUES (39.00, 3, 'Entretenimiento', '19/12/23');
INSERT INTO Expense (amount, category_id, category_name, date) VALUES (18.00, 4, 'Salud', '19/12/23');
INSERT INTO Expense (amount, category_id, category_name, date) VALUES (150.00, 5, 'Educación', '20/12/23');