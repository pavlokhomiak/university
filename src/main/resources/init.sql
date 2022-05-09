CREATE SCHEMA `university_db` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `department` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `deleted` bit(1) NOT NULL,
                              `type` varchar(255) DEFAULT NULL,
                              `lector_id` int NOT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FKoyufjbihj4xsass7f8qch0mf9` (`lector_id`),
                              CONSTRAINT `FKoyufjbihj4xsass7f8qch0mf9` FOREIGN KEY (`lector_id`) REFERENCES `lector` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `lector` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `deleted` bit(1) NOT NULL,
                          `degree` varchar(255) DEFAULT NULL,
                          `first_name` varchar(255) DEFAULT NULL,
                          `last_name` varchar(255) DEFAULT NULL,
                          `salary` decimal(19,2) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE `department_lector` (
                                     `lector_id` int NOT NULL,
                                     `department_id` int NOT NULL,
                                     KEY `FK57g67o2p8vdphk23xbk7o5gy` (`department_id`),
                                     KEY `FKi5jwkssy8kawtqunhx7q3ui87` (`lector_id`),
                                     CONSTRAINT `FK57g67o2p8vdphk23xbk7o5gy` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
                                     CONSTRAINT `FKi5jwkssy8kawtqunhx7q3ui87` FOREIGN KEY (`lector_id`) REFERENCES `lector` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

insert into lector (degree, first_name, last_name, salary, deleted)
values ('ASSISTANT', 'Pavlo', 'Khomiak', 2000, false),
       ('ASSISTANT', 'Petro', 'Petrov', 1800, false),
       ('ASSISTANT_PROFESSOR', 'Ivan', 'Petrenko', 7400, false),
       ('ASSISTANT_PROFESSOR', 'Bob', 'Ivanov', 200, false),
       ('PROFESSOR', 'Anna', 'Bobenko', 3489, false),
       ('PROFESSOR', 'Forman', 'Dorn', 4842, false);

insert into department (type, lector_id, deleted)
values ('MATH', 1, false),
       ('HISTORY', 6, false);

insert into department_lector (lector_id, department_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 2),
       (6, 2);

