# Online Test Project

## 1 To Run
--------
*	Use STS and Pivital Server
*	Import as a Maven Project
*	Need to connect to MySQL.
*	Right Click-->Maven--> Update Project
*   Right Click-->Run As--> Maven Clean
*   Right Click-->Run As--> Maven Install
*	The user name and password are 1,1 and 2 2 if you get the previous database.

## 2 Function
--------
*  Online Test System that allows students to take exams, provides progress monitoring for the coaches, allows admin access for specific tasks. Student should be able to take a test with selected categories and subcategories. When student selects major technologies, it should list the only questions related to those technology. Need attractive User Interface for coach, admin, student and dba. The system should display 50 questions from database and display with multiple choice questions. Admin, coach and dba can add question to the database either one by one or export a batch of questions via xlxs file. Admin and coach should be able to add student, import and export batch of students via excel file. There is help for users that describe the usage of the application. Once the student is done with test, system should automatically grade the test and generate the result, score and feedback. Admin can add, create, delete categories and subcategories. Scope Details: The system should allow the users to make use of the following modules based on their level of authorization. Administration module Conduct the exam module User interface module 

a) Coach 

b) Admin user 

c) Student user 

d) DBA user

*  Questions module 

    a) Add one question

    b) Import a bath of questions from the excel file

*  Student Module a) Add a new student (both admin and coach users modules) b) Import a batch of students from the Excel file

*  Help module

*  Assignments module 

    a) Assignment list 

    b) Generate an assignment 
    
    c) See the result of assignments
   
    d) Export Assignment Details to Excel

*  Category and subcategory module a) Add a new category b) Add a new subcategory

*  Technologies: This is a Spring MVC based project that utilized the following technologies:

1 MySql

2 Apache POI

3 Java

4 Spring

5 Hibernate

6 JSP

7 Rest

8 Bootstrap

9 HTML

10 CSS

11 jQuery

12 JavaScript

13 GIT

14 AJAX

---


## 3 Features of the E-Bazaar MainProject Distribution
---

1.	Controller classes contain the listeners. This strategy implements the philosophy that “controllers should be in control”. Rather than placing listeners in the Gui class that triggers them – which would, in effect, place control in the Gui – we place them in the appropriate Controller class. In this way, the responsibility of handling of important events, which involves controlling the flow of the application, is given to the appropriate Controller class.


