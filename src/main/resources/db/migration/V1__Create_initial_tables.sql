-- Tabela para Papéis (Roles)
CREATE TABLE app_role (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(50) NOT NULL UNIQUE
);

-- Tabela para Usuários (Users)
CREATE TABLE app_user (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(150) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          password VARCHAR(255) NOT NULL, -- Para senhas criptografadas (BCrypt gera hashes longos)
                          active BOOLEAN NOT NULL DEFAULT TRUE,
                          birth_date DATE NULL -- Data de nascimento do usuário do sistema
);

-- Tabela de Junção para Usuários e Papéis (User_Roles)
CREATE TABLE app_user_roles (
                                user_id BIGINT NOT NULL,
                                role_id BIGINT NOT NULL,
                                PRIMARY KEY (user_id, role_id),
                                FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE,
                                FOREIGN KEY (role_id) REFERENCES app_role(id) ON DELETE CASCADE
);

-- Tabela para Membros da Igreja (Members)
CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(150) NOT NULL,
                        birth_date DATE NOT NULL,
                        address VARCHAR(255) NULL,
                        whatsapp_contact VARCHAR(20) NULL,
                        email VARCHAR(100) NULL,
                        baptism_date DATE NULL,
                        ministry VARCHAR(200) NULL
);

-- Tabela para Destinatários de Notificação de Aniversário
CREATE TABLE birthday_notification_recipient (
                                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                 user_id BIGINT NOT NULL UNIQUE, -- Garante que um usuário só pode ter um registro
                                                 active BOOLEAN NOT NULL DEFAULT TRUE,
                                                 FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE
);