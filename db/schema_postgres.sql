/**
 * Author:  Miguel
 * Created: Oct 3, 2020
 */
 
  
CREATE TABLE "user" (
    user_id SERIAL PRIMARY KEY,	
    name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    birthday DATE NOT NULL,
    gender VARCHAR(2),
    phone VARCHAR(25),
    cell_phone VARCHAR(25),
    email VARCHAR(256) NOT NULL,
    address_id INTEGER NOT NULL,
    created_by INTEGER NOT NULL,
    creation_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER NOT NULL,
    last_update TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(5) NULL DEFAULT 'AC' CHECK (status='AC' OR status='IN'),
    deleted BOOLEAN NULL DEFAULT false
);


/* 
 * TODO: On the data_load.sql, before create the defult user, the constraint user_account_user_id_fk mut be disable and enable it when default user is created
 */
CREATE TABLE user_account (
    user_account_id SERIAL PRIMARY KEY,
	user_id INTEGER NOT NULL,
    username VARCHAR(30) NOT NULL UNIQUE, /* generar index para esta columna */
    password VARCHAR(100) NOT NULL,
    password_exp_date TIMESTAMP NULL,
    password_reset BOOLEAN NOT NULL,
    security_questions_reset BOOLEAN NOT NULL,
    failed_auth_count INT NULL,
    last_auth_date TIMESTAMP NULL,
    ip_address VARCHAR(100) NULL,
    created_by INTEGER,
    creation_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER,
    last_update TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(5) NULL DEFAULT 'AC' CHECK (status='AC' OR status='IN'),
    deleted BOOLEAN NULL DEFAULT false
);
/* ALTER TABLE user_account ADD CONSTRAINT user_account_user_id_fk FOREIGN KEY(user_id) REFERENCES user(user_id);*/
ALTER TABLE user_account ADD CONSTRAINT user_account_created_by_fk FOREIGN KEY(created_by) REFERENCES user_account(user_account_id);
ALTER TABLE user_account ADD CONSTRAINT user_account_updated_by_fk FOREIGN KEY(updated_by) REFERENCES user_account(user_account_id);

ALTER TABLE "user" ADD CONSTRAINT user_created_by_fk FOREIGN KEY(created_by) REFERENCES user_account(user_account_id);
ALTER TABLE "user" ADD CONSTRAINT user_updated_by_fk FOREIGN KEY(updated_by) REFERENCES user_account(user_account_id);

CREATE TABLE role (
    role_id SERIAL PRIMARY KEY,    
    name VARCHAR(100) NOT NULL, 
    description VARCHAR(256) NULL,
    created_by INTEGER NOT NULL,
    creation_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER NOT NULL,
    last_update TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(5) NULL DEFAULT 'AC' CHECK (status='AC' OR status='IN'),
    deleted BOOLEAN NULL DEFAULT false
);
ALTER TABLE role ADD CONSTRAINT role_created_by_fk FOREIGN KEY(created_by) REFERENCES user_account(user_account_id);
ALTER TABLE role ADD CONSTRAINT role_updated_by_fk FOREIGN KEY(updated_by) REFERENCES user_account(user_account_id);

CREATE TABLE role_group (
    role_group_id SERIAL PRIMARY KEY,    
    name VARCHAR(100) NOT NULL, 
    description VARCHAR(256) NULL,
    created_by INTEGER NOT NULL,
    creation_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER NOT NULL,
    last_update TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(5) NULL DEFAULT 'AC' CHECK (status='AC' OR status='IN'),
    deleted BOOLEAN NULL DEFAULT false
);
ALTER TABLE role_group ADD CONSTRAINT role_group_created_by_fk FOREIGN KEY(created_by) REFERENCES user_account(user_account_id);
ALTER TABLE role_group ADD CONSTRAINT role_group_updated_by_fk FOREIGN KEY(updated_by) REFERENCES user_account(user_account_id);


CREATE TABLE role_group_role (
    role_group_role_id SERIAL PRIMARY KEY,    
    role_group_id INTEGER NOT NULL, 
    role_id INTEGER NOT NULL,     
    created_by INTEGER NOT NULL,
    creation_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER NOT NULL,
    last_update TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(5) NULL DEFAULT 'AC' CHECK (status='AC' OR status='IN'),
    deleted BOOLEAN NULL DEFAULT false
);
ALTER TABLE role_group_role ADD CONSTRAINT role_group_role_role_group_id_fk FOREIGN KEY(role_group_id) REFERENCES role_group(role_group_id);
ALTER TABLE role_group_role ADD CONSTRAINT role_group_role_role_id_fk FOREIGN KEY(role_id) REFERENCES role(role_id);
ALTER TABLE role_group_role ADD CONSTRAINT role_group_role_created_by_fk FOREIGN KEY(created_by) REFERENCES user_account(user_account_id);
ALTER TABLE role_group_role ADD CONSTRAINT role_group_role_updated_by_fk FOREIGN KEY(updated_by) REFERENCES user_account(user_account_id);

CREATE TABLE permission (
    permission_id SERIAL PRIMARY KEY,    
    name VARCHAR(100) NOT NULL, 
    description VARCHAR(256) NULL,
    created_by INTEGER NOT NULL,
    creation_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER NOT NULL,
    last_update TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(5) NULL DEFAULT 'AC' CHECK (status='AC' OR status='IN'),
    deleted BOOLEAN NULL DEFAULT false
);
ALTER TABLE permission ADD CONSTRAINT permission_created_by_fk FOREIGN KEY(created_by) REFERENCES user_account(user_account_id);
ALTER TABLE permission ADD CONSTRAINT permission_updated_by_fk FOREIGN KEY(updated_by) REFERENCES user_account(user_account_id);

/*
* Mapping between Role and permission. When a role is assigned to a user, the permissions associated to the role are assigned to the user as well.
*/
CREATE TABLE role_permission (
    role_permission_id SERIAL PRIMARY KEY,
    role_id INTEGER NOT NULL,     
    created_by INTEGER NOT NULL,
    creation_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER NOT NULL,
    last_update TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(5) NULL DEFAULT 'AC' CHECK (status='AC' OR status='IN'),
    deleted BOOLEAN NULL DEFAULT false
);
ALTER TABLE role_permission ADD CONSTRAINT role_permission_role_id_fk FOREIGN KEY(role_id) REFERENCES role(role_id);
ALTER TABLE role_permission ADD CONSTRAINT role_permission_created_by_fk FOREIGN KEY(created_by) REFERENCES user_account(user_account_id);
ALTER TABLE role_permission ADD CONSTRAINT role_permission_updated_by_fk FOREIGN KEY(updated_by) REFERENCES user_account(user_account_id);

CREATE TABLE user_permission (
    user_permission_id SERIAL PRIMARY KEY,
    permission_id INTEGER NOT NULL,
	user_account_id INTEGER NOT NULL,
	effective_from TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	effective_to TIMESTAMP NULL,
    created_by INTEGER NOT NULL,
    creation_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER NOT NULL,
    last_update TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(5) NULL DEFAULT 'AC' CHECK (status='AC' OR status='IN'),
    deleted BOOLEAN NULL DEFAULT false
);
ALTER TABLE user_permission ADD CONSTRAINT user_permission_permission_id_fk FOREIGN KEY(permission_id) REFERENCES permission(permission_id);
ALTER TABLE user_permission ADD CONSTRAINT user_permission_user_acct_id_fk FOREIGN KEY(user_account_id) REFERENCES user_account(user_account_id);
ALTER TABLE user_permission ADD CONSTRAINT user_permission_created_by_fk FOREIGN KEY(created_by) REFERENCES user_account(user_account_id);
ALTER TABLE user_permission ADD CONSTRAINT user_permission_updated_by_fk FOREIGN KEY(updated_by) REFERENCES user_account(user_account_id);


CREATE TABLE user_role_group (
    user_role_group_id SERIAL PRIMARY KEY,
    role_group_id INTEGER NOT NULL,
	user_account_id INTEGER NOT NULL,
	effective_from TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	effective_to TIMESTAMP NULL,
    created_by INTEGER NOT NULL,
    creation_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER NOT NULL,
    last_update TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(5) NULL DEFAULT 'AC' CHECK (status='AC' OR status='IN'),
    deleted BOOLEAN NULL DEFAULT false
);
ALTER TABLE user_role_group ADD CONSTRAINT user_role_group_role_group_id_fk FOREIGN KEY(role_group_id) REFERENCES role_group(role_group_id);
ALTER TABLE user_role_group ADD CONSTRAINT user_role_group_user_acct_id_fk FOREIGN KEY(user_account_id) REFERENCES user_account(user_account_id);
ALTER TABLE user_role_group ADD CONSTRAINT user_role_group_created_by_fk FOREIGN KEY(created_by) REFERENCES user_account(user_account_id);
ALTER TABLE user_role_group ADD CONSTRAINT user_role_group_updated_by_fk FOREIGN KEY(updated_by) REFERENCES user_account(user_account_id);


CREATE TABLE user_role (
    user_role_id SERIAL PRIMARY KEY,
    role_id INTEGER NOT NULL,
	user_account_id INTEGER NOT NULL,
	effective_from TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	effective_to TIMESTAMP NULL,
    created_by INTEGER NOT NULL,
    creation_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by INTEGER NOT NULL,
    last_update TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(5) NULL DEFAULT 'AC' CHECK (status='AC' OR status='IN'),
    deleted BOOLEAN NULL DEFAULT false
);
ALTER TABLE user_role ADD CONSTRAINT user_role_role_id_fk FOREIGN KEY(role_id) REFERENCES role(role_id);
ALTER TABLE user_role ADD CONSTRAINT user_role_user_acct_id_fk FOREIGN KEY(user_account_id) REFERENCES user_account(user_account_id);
ALTER TABLE user_role ADD CONSTRAINT user_role_created_by_fk FOREIGN KEY(created_by) REFERENCES user_account(user_account_id);
ALTER TABLE user_role ADD CONSTRAINT user_role_updated_by_fk FOREIGN KEY(updated_by) REFERENCES user_account(user_account_id);