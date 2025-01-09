# OIB-SIP-JAVA-TASKS

Oasis Internship - Java Task
Overview
This repository contains Java-based projects developed as part of the Oasis Internship program. These projects demonstrate proficiency in Java programming, GUI development using Swing, and implementation of real-world use cases.

Projects
1. Online Examination System
The Online Examination System is a desktop application that simulates an online test-taking environment with features like timed assessments, multiple-choice questions, and automatic scoring.

Features
User Authentication: Users must log in using a username and password.
Dynamic Question Display: Displays questions one at a time with multiple-choice options.
Timer Enforcement: A countdown timer ensures the test is completed within the specified time (10 minutes).
Automatic Submission: If the timer expires, the test is automatically submitted, and the score is displayed.
Score Calculation: Keeps track of correct answers and shows the final score.
Logout Option: Allows users to log out after completing the test.
How to Run
Compile the code using javac.
bash
Copy code
javac OnlineExam.java
Run the program:
bash
Copy code
java OnlineExam
Files
OnlineExam.java: Contains the complete implementation of the login system and test interface.
2. ATM Interface
The ATM Interface is a command-line-based application that simulates basic ATM functionalities like balance inquiry, withdrawal, deposit, and account creation.

Features
Account Management: Create new accounts with a unique ID and initial balance.
Transaction Support:
Check balance.
Deposit money.
Withdraw money with sufficient balance checks.
User-Friendly Interface: Prompts guide the user through the operations.
How to Run
Compile the ATM interface program using javac.
bash
Copy code
javac ATMInterface.java
Run the program:
bash
Copy code
java ATMInterface
Files
ATMInterface.java: Implements the functionality of an ATM system.
Technologies Used
Programming Language: Java
Framework: Swing (for GUI in the Online Examination System)
Development Environment: IDEs such as IntelliJ IDEA, Eclipse, or any text editor with a Java compiler.
How to Use
Clone the repository:
bash
Copy code
git clone https://github.com/your-repo/oasis-internship-java-tasks.git
Navigate to the desired project directory.
Compile and run the program as instructed above.
Future Enhancements
Online Examination System
Integrate user authentication with a database.
Allow question bank customization and addition of new questions.
Save scores and results for future reference.
ATM Interface
Add a GUI interface using Swing or JavaFX.
Implement account persistence using a database or file storage.
Support for multiple accounts and admin functionalities.
Contributors
[loa]
Feel free to modify or expand this README based on the specific requirements or additional features
