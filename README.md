## 🧪 Jpcv_Playwright_Java

Automated UI testing project using 
**Java**, **Playwright**, **Api Testing**.

## 🚀 Technologies Used
- Java 
- Playwright 

## ✅ How to Run the Tests

```bash
 mvn clean test
 mvn clean test -Dheadless=true/false
 mvn clean test -Dgroups=Tag
 mvn allure:report
 mvn allure:serve

mvn clean test -Dheadless=true -Dgroups=E2E && mvn allure:serve
mvn clean test -Dheadless=false -Dgroups=E2E && mvn allure:serve
