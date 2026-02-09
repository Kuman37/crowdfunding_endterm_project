CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    role VARCHAR(20) CHECK (role IN ('creator','backer','admin')) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS category (
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS project (
    project_id SERIAL PRIMARY KEY,
    creator_id INT NOT NULL REFERENCES users(user_id),
    category_id INT NOT NULL REFERENCES category(category_id),
    title VARCHAR(200) NOT NULL,
    description TEXT,
    goal_amount NUMERIC(12,2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(20) CHECK (status IN ('draft','active','funded','failed')),
    FOREIGN KEY (creator_id) REFERENCES users(user_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE IF NOT EXISTS reward (
    reward_id SERIAL PRIMARY KEY,
    project_id INT NOT NULL REFERENCES project(project_id),
    title VARCHAR(150) NOT NULL,
    description TEXT,
    minimum_amount NUMERIC(12,2) NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(project_id)
);

CREATE TABLE IF NOT EXISTS pledge (
    pledge_id SERIAL PRIMARY KEY,
    project_id INT NOT NULL REFERENCES project(project_id),
    backer_id INT NOT NULL REFERENCES users(user_id),
    reward_id INT REFERENCES reward(reward_id),
    pledge_amount NUMERIC(12,2) NOT NULL,
    pledge_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES project(project_id),
    FOREIGN KEY (backer_id) REFERENCES users(user_id),
    FOREIGN KEY (reward_id) REFERENCES reward(reward_id)
);

CREATE TABLE IF NOT EXISTS payment (
    payment_id SERIAL PRIMARY KEY,
    pledge_id INT UNIQUE REFERENCES pledge(pledge_id),
    payment_method VARCHAR(50),
    payment_status VARCHAR(20),
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pledge_id) REFERENCES pledge(pledge_id)
);

CREATE TABLE IF NOT EXISTS comment (
    comment_id SERIAL PRIMARY KEY,
    project_id INT NOT NULL REFERENCES project(project_id),
    user_id INT NOT NULL REFERENCES users(user_id),
    comment_text TEXT NOT NULL,
    comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES project(project_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS project_update (
    update_id SERIAL PRIMARY KEY,
    project_id INT NOT NULL REFERENCES project(project_id),
    update_content TEXT NOT NULL,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES project(project_id)
);

-- Sample Data
INSERT INTO category (category_name, description) VALUES ('Technology', 'Tech gadgets and software projects');
INSERT INTO category (category_name, description) VALUES ('Art', 'Creative arts and crafts');
INSERT INTO category (category_name, description) VALUES ('Food', 'Restaurant and food-related projects');

INSERT INTO users (full_name, email, password_hash, role) VALUES 
('Admin User', 'admin@test.com', 'hashed_password', 'admin'),
('John Creator', 'john@test.com', 'hashed_password', 'creator'),
('Sarah Backer', 'sarah@test.com', 'hashed_password', 'backer');