# ABCFunding
"Angel Bridge to Customer" is crowd funding site for learning Spring.\
You can make a mock investment using equal repayment of principal and interest(a.k.a ERPI).

※This project was a two-person study project conducted in the summer of 2016.\
I have removed unnecessary functions and adjusted layout, but there may be some traces left.

# 📍 Features
1. Investment Simulator
By implementing calculations of ERPI in JavaScript, you can check the estimated return by entering the investable amount.

2. Spring Job Schedule
The repayment work was implemented using Spring Job Scheduler, and the logic is as follows.
![alt text](https://github.com/Beomsik-Lee/ABCFunding/blob/master/ABCFunding/WebContent/img/spring_scheduler.jpg?raw=true)

3. Login Encryption
- RSA: The login page uses RSA in Base64 format for encryption.
- Spring Security: Password storage and verification are encrypted using "BCryptPasswordEncoder".

## 🌍 Environment
- Java 8
- Spring framework 3.2.8
- jQuery
- Bootstrap 4
- Oracle 11g
- Apache Tomcat 8.5

## 🧾 Where the DDL is
/ABCFunding/src/sqls/DDL.sql
