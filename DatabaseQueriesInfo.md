
initial steps after installing and running service for postgres db

to open postgres: install psql
to login as super user:
sudo -u postgres psql postgres
create a database named 'flightsdb'

create a role/user as 'root' and password as 'root' or modify the file airportsScript.rb to your user credentials
CREATE USER/ROLE ROOT WITH PASSWORD 'root'; *example

on every table
GRANT ALL PRIVILEGES ON AIRPORTS TO flight
GRANT USAGE, SELECT ON SEQUENCE {table_name}_id_seq TO root
there is a different sequence for each table

if possible create a schema and use
GRANT ALL ON ALL SEQUENCES IN SCHEMA your_schema TO root
to avoid doing grants on every table

on database
GRANT ALL PRIVILEGES ON flightsdb TO root

queries for table creation:

`CREATE TABLE IF NOT EXISTS AIRPORTS(
    id SERIAL PRIMARY KEY,
    iata VARCHAR(255),
    lat VARCHAR(255),
    lon VARCHAR(255),
    name VARCHAR(255),
    city VARCHAR(255)
    );`

`CREATE TABLE IF NOT EXISTS RESERVATIONS(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    last_name VARCHAR(255),
    passengers SMALLINT,
    cost VARCHAR(255),
    email VARCHAR(255)
    );`

`CREATE TABLE IF NOT EXISTS FLIGHTS(
    id SERIAL PRIMARY KEY,
    flight_number VARCHAR(255),
    departure_date VARCHAR(255),
    arrival_date VARCHAR(255),
    departure_airport VARCHAR(255),
    arrival_airport VARCHAR(255),
    reservation_id INTEGER references RESERVATIONS(id)
    );`