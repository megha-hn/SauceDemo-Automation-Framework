SauceDemo Automation Framework
Project Overview
This is an advanced QA automation framework for the SauceDemo e-commerce application. It demonstrates skills in Selenium (Java), TestNG, Page Object Model, data-driven testing (Excel + DB), API testing with Postman/Newman, performance testing with JMeter, and database validation with MySQL.
Candidate: Meghana H N
Position: Software Test Engineer
Submission Date: February 2026
Key Features Implemented

Manual test cases (15+ in Excel)
UI automation with Selenium + TestNG + POM + Retry analyzer + Cross-browser parallel execution (Chrome/Edge)
Data-driven from Excel + MySQL DB
API testing (Postman collections + Newman CLI reports)
Performance testing (JMeter load test for 50 users)
Database validation (MySQL sandbox integration in 3 scenarios)
Bug report (5 bugs documented)
Test summary report
Logging, screenshots on failure

Prerequisites

Java JDK 17+ (LTS recommended)
Maven 3.8+
Chrome/Edge browsers installed
ChromeDriver/EdgeDriver (managed via WebDriverManager)
MySQL 8+ (local )
Postman/Newman for API (npm installed)
JMeter 5.6+ for performance
IntelliJ IDEA (or Eclipse) for development

Setup Instructions

Clone the repository:textgit clone <your-github-repo-url>
cd SauceDemo-Automation-Framework
Install Maven dependencies:textmvn clean install
Configure config.properties (src/test/resources):
baseUrl = https://www.saucedemo.com
DB details :textdb.url = jdbc:mysql://localhost:3306/saucedemo_db
db.user = <your_username>
db.pass = <your_password>

Setup MySQL Database :
local MySQL.
Run the SQL scripts from docs/db-schema.sql to create Users, Orders, Products tables and insert sample data.

For API :
Install Newman: npm install -g newman
Run: npm run test:api

For Performance :
Launch JMeter: C:\Program Files\apache-jmeter-5.6.3\bin\jmeter.bat
Open performance-tests\LoadTest.jmx
Or CLI: jmeter -n -t performance-tests\LoadTest.jmx -l performance-tests\AggregateReport.jtl -e -o performance-tests\report-html


Folder Structure
textSauceDemo-Automation-Framework/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/                  ← Page Object classes (LoginPage.java, ProductsPage.java, etc.)
│   │       └── utils/                  ← Utilities (DriverManager.java, DBUtils.java, ExcelUtils.java)
│   └── test/
│       ├── java/
│           ├── base/                   ← BaseTest.java (setup/teardown, driver init)
│           ├── listeners/              ← RetryAnalyzer.java, Extent listeners
│           └── tests/                  ← Test classes (LoginTest.java, ECommerceWorkflowTest.java)
│       └── resources/                  ← config.properties, testdata.xlsx, log4j2.xml
├── api-tests/
│   ├── postman/
│   │   ├── collections/                ← Reqres-API-Automation.postman_collection.json
│   │   └── environments/               ← Reqres-Environment.postman_environment.json
│   └── newman/
│       └── reports/                    ← newman-report.html
├── performance-tests/
│   ├── LoadTest.jmx                    ← JMeter test plan
│   ├── PerformanceReport.html          ← Generated HTML report
│   └── PerformanceAnalysis.docx        ← Analysis with bottlenecks/recommendations
├── reports/
│   ├── extent/                         ← Extent HTML reports
│   └── screenshots/                    ← Failure screenshots
├── docs/
│   ├── ManualTestCases.xlsx            ← 15+ manual test cases
│   ├── BugReport.docx                  ← 5 documented bugs
│   ├── TestSummaryReport.docx          ← Execution stats, defects, risks
│   └── db-schema.sql                   ← SQL for tables/data
├── pom.xml                             ← Maven dependencies
├── testng.xml                          ← Sequential suite
├── testng-parallel.xml                 ← Parallel cross-browser suite
├── package.json                        ← For Newman API runs
├── README.md                           ← This file
└── .gitignore                          ← Ignore generated files
How to Run Tests
UI Tests 

Sequential: mvn clean test -DsuiteXmlFile=testng.xml
Parallel (Chrome + Edge): mvn clean test -DsuiteXmlFile=testng-parallel.xml

API Tests 

Install deps: npm install
Run: npm run test:api
Report: Open api-tests/newman/reports/newman-report.html

Performance Tests

GUI: Open LoadTest.jmx in JMeter → Run
CLI: jmeter -n -t performance-tests/LoadTest.jmx -l performance-tests/AggregateReport.jtl -e -o performance-tests/report-html
Analysis: See PerformanceAnalysis.docx

Database Validation

Integrated in ECommerceWorkflowTest.java and LoginTest.java (fetched from MySQL)
Run UI tests → DB checks happen automatically (user exists, order created, inventory updated)

Manual Test Cases
See docs/ManualTestCases.xlsx for 15+ cases covering:

TC_001-TC_005: Login (valid, invalid, locked, empty fields)
TC_006-TC_010: Products (listing, sorting, filter)
TC_011-TC_013: Cart (add/remove, count update)
TC_014-TC_017: Checkout (info, payment, confirmation, negative: empty cart)

UI Automation Framework

POM with Page Factory
Data-driven from Excel/DB
Retry analyzer for flaky tests
Parallel cross-browser
Screenshots on failure
Extent reports

API Testing with Postman

Collections for Reqres.in API (CRUD users)
Environment variables
Newman CLI for automated runs + HTML report

Performance Testing with JMeter

LoadTest.jmx: 50 users, 10s ramp-up, 5 iterations, 2 min duration
Samplers: Login, Product Listing, Add to Cart
Metrics: Avg response time, 90th/95th percentile, throughput, error %
Report: PerformanceReport.html
Analysis: PerformanceAnalysis.docx (bottlenecks, recommendations)

Database Validation with MySQL

DBUtils.java for JDBC connectivity
Integrated in 3 scenarios:
Login: Verify user exists in DB
Add to Cart: Check product inventory updated
Checkout: Validate order details in DB

Data-driven from MySQL Users table

Bug Report
See docs/BugReport.docx for 5 documented bugs (e.g., empty login allowed, cart total -cart item).
Test Summary Report
See docs/TestSummaryReport.docx for stats:

