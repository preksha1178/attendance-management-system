# 📘 Attendance Management System

A **Java Swing GUI-based Attendance Management System** designed for managing student attendance in educational institutions. Built using **Java**, **MySQL**, and developed inside **Eclipse IDE**, this application supports three user roles: **Admin**, **Teacher**, and **Student**.

---

## ✨ Key Features

🔐 **Role-Based Access:**
- 👨‍🏫 Admin: Manage users (students, teachers), edit attendance, view reports
- 👩‍🏫 Teacher: Mark attendance, view attendance history
- 👨‍🎓 Student: View personal attendance record

📋 **Core Functionalities:**
- Add, update, delete **students**, **teachers**, **classes**
- Mark and view **daily attendance**
- **Edit** attendance records
- **Search** student records
- Date-wise attendance summary
- Logout + About Us screens

🎨 **UI & Experience:**
- Hover effects on all buttons
- Icons for every module (Student, Teacher, Attendance, etc.)
- Custom **welcome message** using dynamic `getUser()` call
- Minimize/Close buttons styled with hover effects

📊 **Statistics:**
- View history and summarized attendance records

---

## 🛠 Technologies Used

| Tool        | Description                     |
|-------------|---------------------------------|
| Java        | Core programming + GUI (Swing)  |
| JDBC        | Database connectivity            |
| MySQL       | Data storage (XAMPP or local DB) |
| Eclipse IDE | Development environment          |
| Git + GitHub| Version control                  |

---

## 💾 Database Setup

1. Make sure **MySQL** is installed and running.
2. Create a database named: attendance


---

## 🗃️ Database Schema

```sql
CREATE DATABASE attendance;

USE attendance;

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    role VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(100)
);

CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    class_id INT
);

CREATE TABLE teacher (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    subject VARCHAR(100)
);

CREATE TABLE class (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    date DATE,
    status VARCHAR(10)
);



Example DB credentials in your code:
String url = "jdbc:mysql://localhost:3306/attendance";
String user = "root";
String pass = "";

## 🧑‍💻 Author
Preksha
🎓 3rd Year BCA Student
📬 GitHub Profile
📍 Passionate about Java, DBMS, and real-world applications


💡 Future Enhancements
Export attendance to PDF

Add password encryption for users

Email attendance reports to students

Role-based dashboards


📬 How to Run
Import project in Eclipse

Set up your MySQL DB

Run Login.java or main method

Log in as Admin/Teacher/Student and explore!

📄 License
This project is intended for academic learning purposes only.



