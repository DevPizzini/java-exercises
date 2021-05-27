ws-db
=====

A Spring-boot webservice with restTemplate, jdbcTemplate and Lombok

Setup
---
Create db 'mydb' user 'mydb' pass: 'mydb'

```
create database mydb;
create user mydb with encrypted password 'mydb';
grant all privileges on database mydb to mydb;
```

create table
```
CREATE TABLE employee (	
	name varchar(25) NOT NULL,
	role varchar(25) NOT null,
	department varchar(15) null,
	CONSTRAINT name_pkey PRIMARY KEY (name)	
);
```

Test
----

`mvn clean test -Dspring.profiles.active=it`

in your IDE add `spring.profiles.active=it` environment variable 

Compile
---
`mvn clean package -DskipTests`

Run
----

`java -Dspring.profiles.active=local -jar target/ws-db-0.0.1-SNAPSHOT.jar`

add new Employee:

`curl -v -X POST localhost:8080/employee/3 -H 'Content-Type:application/json' -d '{"name": "bob", "role": "developer"}'`

read Employee by name:

`curl -v -X GET localhost:8080/employee/bob`

read all Employees by role:

`curl -v -X GET localhost:8080/employee/role/developer`

throw an error:

`curl -v -X POST localhost:8080/employee/3 -H 'Content-Type:application/json' -d '{"name": "", "role": " "}'`

TODO
----
* Add `id` field (type _UUID_) in Employee table and use it as primary key instead of _name_ filed (change create table in BaseTestClass too). Add a validator for `id`. Remove _id_ in _EmployeeController.addEmployee_ method. Add `create_date` in employee table.
* Add `DELETE` in _EmployeeController_ and modify unit test.
* Dump every 5 minutes the `employee` table into a `CSV` file (define the file name in `application-{env}.properties`).
