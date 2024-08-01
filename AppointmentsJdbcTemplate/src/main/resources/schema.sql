
CREATE TABLE sede (
    sede_id SERIAL PRIMARY KEY,
    nome_sede VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL
);

CREATE TABLE users(
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE prenotazione (
    pren_id SERIAL PRIMARY KEY,
    sede_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    data DATE NOT NULL,
    orario TIME NOT NULL,
    FOREIGN KEY (sede_id) REFERENCES sede(sede_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);