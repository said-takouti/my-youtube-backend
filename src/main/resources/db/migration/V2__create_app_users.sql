CREATE TABLE app_users(
    id BIGINT IDENTITY(1, 1) NOT NULL ,
    username NVARCHAR(50) NOT NULL ,
    email NVARCHAR(255),
    password_hash NVARCHAR(255) NOT NULL,
    CONSTRAINT pk_app_users PRIMARY KEY (id),
    CONSTRAINT uk_app_users_username UNIQUE (username)
);