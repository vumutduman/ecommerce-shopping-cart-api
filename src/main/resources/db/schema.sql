CREATE USER IF NOT EXISTS ecommerce_app_usr PASSWORD 'ecommerce_app_usr';

ALTER USER ecommerce_app_usr ADMIN TRUE;

CREATE SCHEMA IF NOT EXISTS ecommerce AUTHORIZATION ecommerce_app_usr;