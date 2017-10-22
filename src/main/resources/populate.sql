-- Admin DBA and Coach users
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (1, 'admin@gmail.com', b'1', 'admin', 'admin', '$2a$10$TSCbbjh3BkwYSc.uc.V/begrFwAI6LJOqHewuvCSTOMp8q2ZMPbpy', 'admin');
INSERT INTO `Authority` (`id`, `authority`, `userId`) VALUES (1, 'ROLE_ADMIN', 1);


INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (2, 'coach@gmail.com', b'1', 'coach', 'coach', '$2a$10$NKtwZv9mcnnycFK1KXfnrutCuLs26PIU5TA33xaM7wWhCWCnz.HCG', 'coach');
INSERT INTO `Authority` (`id`, `authority`, `userId`) VALUES (2, 'ROLE_COACH', 2);


INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (3, 'dba@gmail.com', b'1', 'dba', 'dba', '$2a$10$s4iVVd1/AVQQetD6nmFolOstiHel71H4G43yzSBQg2iZcm9xQJcem', 'dba');
INSERT INTO `Authority` (`id`, `authority`, `userId`) VALUES (3, 'ROLE_DBA', 3);


-- Student users
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (4, 'juliachenjing@gmail.com', b'1', 'Julia', 'Chen', null, null);
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (5, 'bimalparajulise@gmail.edu', b'1', 'Bimal', 'Parajuli', null, null);
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (6, 'kloem@mum.edu', b'1', 'Stu04', 'Stu04', null, null);
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (7, 'test5@mum.edu', b'1', 'Stu05', 'Stu05', null, null);
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (8, 'test6@mum.edu', b'1', 'Stu06', 'Stu06', null, null);
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (9, 'test7@mum.edu', b'1', 'Stu07', 'Stu07', null, null);

-- Student Data Sample
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (4, 'August 2016', 0, 985002);
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (5, 'August 2016', 1, 985003);
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (6, 'August 2016', 1, 985004);
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (7, 'October 2016', 1, 985015);
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (8, 'January 2017', 1, 985066);
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (9, 'January 2017', 1, 985067);



INSERT INTO `Grade` (`name`, `rangeFrom`, `rangeTo`) VALUES ('A', 95, 98);
INSERT INTO `Grade` (`name`, `rangeFrom`, `rangeTo`) VALUES ('A+', 98, 100);
INSERT INTO `Grade` (`name`, `rangeFrom`, `rangeTo`) VALUES ('A-', 90, 94);
INSERT INTO `Grade` (`name`, `rangeFrom`, `rangeTo`) VALUES ('B', 80, 84);
INSERT INTO `Grade` (`name`, `rangeFrom`, `rangeTo`) VALUES ('B+', 85, 89);
INSERT INTO `Grade` (`name`, `rangeFrom`, `rangeTo`) VALUES ('B-', 75, 79);
INSERT INTO `Grade` (`name`, `rangeFrom`, `rangeTo`) VALUES ('C', 65, 69);
INSERT INTO `Grade` (`name`, `rangeFrom`, `rangeTo`) VALUES ('C+', 70, 74);
INSERT INTO `Grade` (`name`, `rangeFrom`, `rangeTo`) VALUES ('C-', 60, 64);
INSERT INTO `Grade` (`name`, `rangeFrom`, `rangeTo`) VALUES ('NC', 0, 59);


-- Category Data Sample
INSERT INTO `Category` (`id`, `enabled`, `name`) VALUES (1, b'1', 'Java');
INSERT INTO `Category` (`id`, `enabled`, `name`) VALUES (2, b'1', 'DOT Net');
INSERT INTO `Category` (`id`, `enabled`, `name`) VALUES (3, b'1', 'PHP');
INSERT INTO `Category` (`id`, `enabled`, `name`) VALUES (4, b'1', 'DATABASE');

-- Subcategory Data Sample
INSERT INTO `Subcategory` (`id`, `enabled`, `name`, `category_id`) VALUES (1, b'1', 'C#', 2);
INSERT INTO `Subcategory` (`id`, `enabled`, `name`, `category_id`) VALUES (2, b'1', 'JAVA', 1);
INSERT INTO `Subcategory` (`id`, `enabled`, `name`, `category_id`) VALUES (3, b'1', 'PHP', 3);
INSERT INTO `Subcategory` (`id`, `enabled`, `name`, `category_id`) VALUES (4, b'1', 'MSSQL', 4);

