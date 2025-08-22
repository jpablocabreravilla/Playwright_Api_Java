## 🧪 Jpcv_Playwright_Java

Automated UI testing project using 
**Java**, **Playwright**, **Api Testing**.

## 🚀 Technologies Used
- Java 
- Playwright 

## Api Documentation
- https://documenter.getpostman.com/view/7849298/2sB2ca7fHY#a1a3e092-a6d2-45f8-99e1-ec8b384b1e55
- https://github.com/jpablocabreravilla/BackendApiNodeJs
- Terminal: npm start

## ✅ How to Run the Tests
 mvn clean test
 mvn clean test -Dgroups=regression/smoke

 mvn allure:report
 mvn allure:serve

mvn clean test -Dgroups=regression && mvn allure:serve
