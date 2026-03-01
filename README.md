# SauceDemo Automation Framework – QA Assignment Submission

**Candidate:** Meghana H N  
**Position:** Software Test Engineer  
**Assignment:** Advanced QA Automation Assignment  
**Submission Date:** March 01, 2026  
**GitHub Repository:** https://github.com/yourusername/SauceDemo-Automation-Framework  
(Replace with your actual repo URL)

**Application Under Test:** [Sauce Demo](https://www.saucedemo.com)

## Project Overview

This repository contains a complete end-to-end QA automation framework for the SauceDemo e-commerce application. It demonstrates advanced skills in:

- Manual test case design (15+ scenarios)
- UI automation using Selenium (Java), TestNG, Page Object Model (POM), data-driven testing (Excel + MySQL)
- API testing with Postman & Newman
- Performance/load testing with JMeter
- Database backend validation with MySQL (integrated in UI tests)
- Bug reporting (5 documented bugs)
- Test summary report
- CI/CD pipeline with Jenkins (Freestyle + Pipeline as Code)

All parts of the assignment are fully implemented and documented.

### Tech Stack

- Language: Java 17 (LTS)
- Test Framework: TestNG
- UI Automation: Selenium WebDriver 4.16+
- Build Tool: Maven
- Reporting: Extent Reports, screenshots on failure
- API Testing: Postman Collections + Newman CLI
- Performance Testing: Apache JMeter 5.6+
- Database: MySQL 8 (db4free.net sandbox)
- CI/CD: Jenkins (Docker + Freestyle + Pipeline)

## Folder Structure

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
│   ├── SauceDemoPerformance.jmx        ← JMeter test plan
│   ├── PerformanceReport.html          ← Generated HTML report
│   └── PerformanceTestAnalysis.docx    ← Analysis with bottlenecks/recommendations
├── reports/
│   ├── extent/                         ← Extent HTML reports
│   └── screenshots/                    ← Failure screenshots
├── test-artifacts/
│   ├── ManualTestCases.xlsx            ← 15+ manual test cases
│   ├── BugReport.docx                  ← 5 documented bugs
│   ├── TestSummaryReport.docx          ← Execution stats, defects, risks
│   └── TestStratergyDocument.docx                  
├── jenkin-setup/
│   ├── extent-report.html              ← All test extent report with test pass/fail
│   ├── ConsoleOutput.txt               ← Detailed output text
│   ├── Jenkin_Setup_Documentation.docx ← Installation and deployment 
│   └── SauceDemo Config - Jenkins.HTMI ← Jenkins job configuration
├── jenkinsfile                         ← pipeline approach and script
├── pom.xml                             ← Maven dependencies
├── testng.xml                          ← Sequential suite
├── testng-parallel.xml                 ← Parallel cross-browser suite
├── package.json                        ← For Newman API runs
├── README.md                           ← This file
└── .gitignore                          ← Ignore generated files
How to Run Tests

## Setup & Run Instructions

### 1. Clone Repository
```bash
git clone https://github.com/megha-hn/SauceDemo-Automation-Framework.git
cd SauceDemo-Automation-Framework

2. Install Dependencies
mvn clean install

3. Configure src/test/resources/config.properties
propertiesbaseUrl=https://www.saucedemo.com
browser=chrome
db.url=jdbc:mysql://localhost:3306/saucedemo_db
db.user=your_db_username
db.pass=your_db_password


4. Database Setup

Use db4free.net (free) or local MySQL
Run docs/db-schema.sql to create tables & insert sample data
DB validation runs automatically in LoginTest and ECommerceWorkflowTest

5. Run Tests Locally

Sequential:mvn clean test -DsuiteXmlFile=testng.xml
Parallel (Chrome + Edge):mvn clean test -DsuiteXmlFile=testng-parallel.xml

6.API Tests:npm install
npm run test:api
7.Performance (JMeter CLI): jmeter -n -t C:\Users\megha\IdeaProjects\SauceDemo-Automation-Framework\performance-tests\SauceDemoPerformance.jmx -l  C:\Users\megha\IdeaProjects\SauceDemo-Automation-Framework\performance-tests/resultstree.jtl -e -o C:\Users\megha\IdeaProjects\SauceDemo-Automation-Framework\performance-tests\report-html

8.How to Run Jenkins in Docker 
# Update WSL first (if prompted)
wsl --update

# Start Jenkins
docker run -d -p 8080:8080 -p 50000:50000 --name jenkins -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts-jdk17
