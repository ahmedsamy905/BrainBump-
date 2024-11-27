# BrainBump 📚💡

**BrainBump** is a personalized problem-solving reminder system designed to help users revisit and master their solved problems. The app uses an adaptive scheduling algorithm to send reminders for solving problems based on user performance, ensuring steady learning progress.

---

## Features 🚀

- **User Management:**
  - User registration, login, and email verification.
- **Problem Management:**
  - Add, update, and delete problems.
  - Track attempts to solve problems.
- **Adaptive Scheduling:**
  - Notifications based on performance:
    - If solved successfully, the interval increases.
    - If failed, the interval decreases.
  - Reminder intervals: 1 day, 3 days, 6 days, 12 days, 24 days, 48 days, etc.
- **Email Notifications:**
  - Sends daily reminders at 12:00 AM for problems due for reattempts.
  - Includes direct links to the problems.
- **Performance Tracking:**
  - Keeps a record of attempts, status, and time taken for each problem.

---

## Tech Stack 🛠️

- **Backend:**
  - Java, Spring Boot
  - Hibernate/JPA for ORM
  - MySQL/PostgreSQL for database management
- **Email Integration:**
  - Spring Mail for email notifications
- **Scheduling:**
  - Spring Scheduler for automated reminders
- **Build Tool:**
  - Maven

---
