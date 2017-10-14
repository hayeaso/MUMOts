INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (1, 'deepakkc669@gmail.com', b'1', 'admin', 'admin', '$2a$10$TSCbbjh3BkwYSc.uc.V/begrFwAI6LJOqHewuvCSTOMp8q2ZMPbpy', 'admin');
INSERT INTO `Authority` (`id`, `authority`, `userId`) VALUES (1, 'ROLE_ADMIN', 1);

INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (2, 'test@gmail.com', b'1', 'Coach1', 'TestL', '$2a$10$NKtwZv9mcnnycFK1KXfnrutCuLs26PIU5TA33xaM7wWhCWCnz.HCG', 'coach');
INSERT INTO `Authority` (`id`, `authority`, `userId`) VALUES (2, 'ROLE_COACH', 2);

--Student users
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (3, 'test1@mum.edu', b'1', 'Stu01', 'Clark', null, null);
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (4, 'test2@mum.edu', b'1', 'Stu02', 'Stark', null, null);
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (5, 'test3@mum.edu', b'1', 'Stu03', 'Oven', null, null);
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (6, 'test4@mum.edu', b'1', 'Stu04', 'Olive', null, null);
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (7, 'test5@mum.edu', b'1', 'Stu05', 'Washinton', null, null);
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (8, 'test6@mum.edu', b'1', 'Stu06', 'Trouble', null, null);
INSERT INTO `User` (`userId`, `email`, `enabled`, `firstName`, `lastName`, `password`, `username`) VALUES (9, 'test7@mum.edu', b'1', 'Stu07', 'Awesome', null, null);


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

-- Student Data Sample
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (3, 'August 2016', 1, 985001);
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (4, 'August 2016', 0, 985002);
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (5, 'August 2016', 1, 985003);
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (6, 'August 2016', 1, 985004);
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (7, 'October 2016', 1, 985015);
INSERT INTO `Student` (`userId`, `entry`, `jobSearchStatus`, `studentId`) VALUES (8, 'January 2017', 1, 985066);

-- Category Data Sample
INSERT INTO `Category` (`id`, `enabled`, `name`) VALUES (1, b'1', 'Java');
INSERT INTO `Category` (`id`, `enabled`, `name`) VALUES (2, b'1', '.Net');
INSERT INTO `Category` (`id`, `enabled`, `name`) VALUES (3, b'1', 'Python');
INSERT INTO `Category` (`id`, `enabled`, `name`) VALUES (4, b'1', 'Ruby');
INSERT INTO `Category` (`id`, `enabled`, `name`) VALUES (5, b'1', 'C');
INSERT INTO `Category` (`id`, `enabled`, `name`) VALUES (6, b'1', 'C++');
INSERT INTO `Category` (`id`, `enabled`, `name`) VALUES (7, b'1', 'C#');

-- Subcategory Data Sample
INSERT INTO `Subcategory` (`id`, `enabled`, `name`, `category_id`) VALUES (1, b'1', 'Core', 1);
INSERT INTO `Subcategory` (`id`, `enabled`, `name`, `category_id`) VALUES (2, b'1', 'JSP', 1);
INSERT INTO `Subcategory` (`id`, `enabled`, `name`, `category_id`) VALUES (3, b'1', 'Servlet', 1);
INSERT INTO `Subcategory` (`id`, `enabled`, `name`, `category_id`) VALUES (4, b'1', 'Spring', 1);

-- Question Data Sample
INSERT INTO `Question` (`id`, `description`, `subcategory_id`) VALUES (1, 'Choose appropriate datatype for value 4.5', 1);
INSERT INTO `Question` (`id`, `description`, `subcategory_id`) VALUES (2, 'Choose A', 1);
INSERT INTO `Question` (`id`, `description`, `subcategory_id`) VALUES (3, 'Choose B', 1);
INSERT INTO `Question` (`id`, `description`, `subcategory_id`) VALUES (4, 'Choose C', 2);
INSERT INTO `Question` (`id`, `description`, `subcategory_id`) VALUES (5, 'Choose D', 2);
INSERT INTO `Question` (`id`, `description`, `subcategory_id`) VALUES (6, 'Choose E', 2);
INSERT INTO `Question` (`id`, `description`, `subcategory_id`) VALUES (7, 'Choose F', 3);
INSERT INTO `Question` (`id`, `description`, `subcategory_id`) VALUES (8, 'Choose G', 3);
INSERT INTO `Question` (`id`, `description`, `subcategory_id`) VALUES (9, 'Choose H', 4);
INSERT INTO `Question` (`id`, `description`, `subcategory_id`) VALUES (10, 'Choose I', 4);

-- Choice Data Sample
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (1, b'0', 'int', 1);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (2, b'0', 'double', 1);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (3, b'1', 'float', 1);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (4, b'0', 'string', 1);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (5, b'0', '', 1);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (6, b'0', 'A', 2);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (7, b'0', 'B', 2);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (8, b'1', 'Y', 2);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (9, b'0', 'D', 2);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (10, b'0', '', 2);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (11, b'0', 'A', 3);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (12, b'0', 'B', 3);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (13, b'1', 'Y', 3);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (14, b'0', 'D', 3);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (15, b'0', '', 3);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (16, b'0', 'A', 4);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (17, b'0', 'B', 4);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (18, b'0', 'C', 4);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (19, b'1', 'Y', 4);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (20, b'0', '', 4);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (21, b'0', 'C', 5);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (22, b'0', 'D', 5);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (23, b'0', 'A', 5);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (24, b'1', 'Y', 5);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (25, b'0', 'E', 5);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (26, b'0', 'D', 6);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (27, b'0', 'C', 6);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (28, b'1', 'Y', 6);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (29, b'0', 'A', 6);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (30, b'0', 'B', 6);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (31, b'0', 'A', 7);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (32, b'0', 'D', 7);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (33, b'1', 'Y', 7);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (34, b'0', 'C', 7);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (35, b'0', '', 7);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (36, b'0', 'B', 8);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (37, b'1', 'Y', 8);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (38, b'0', 'D', 8);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (39, b'0', 'A', 8);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (40, b'0', 'G', 8);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (41, b'0', 'A', 9);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (42, b'0', 'B', 9);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (43, b'1', 'Y', 9);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (44, b'0', 'D', 9);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (45, b'0', '', 9);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (46, b'0', 'A',10);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (47, b'0', 'B', 10);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (48, b'0', 'C', 10);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (49, b'1', 'Y', 10);
INSERT INTO `Choice` (`id`, `answer`, `description`, `question_id`) VALUES (50, b'0', 'D', 10);
