- Framework - JUnit with Page Object Model 
- Design Pattern - Singleton Design Pattern
- Build Tool - Maven

Working Environment:

- Java - 1.8
- Selenium - 3.4
- Firefox Browser - 57.0
- Chrome Browser  -  60.0
- Platform - macOS Sierra

Framework Description:

- config.properties file contains the configuration details like browsername and url
- Currently this framework template has capable to run the scripts on Firefox and Chrome (on MAC OS)
- Controlling browser using the config property file (webdriver.browser property in config file)
 
- BasePage class contains all the wrapper and reusable methods and this class is parent class for all the remaining page classes
- All the page classes contains respective page methods

- BaseTest class contains all the test related reusable methods like driver initialization and quit and this class is parent class for all the remaining test script classes
- TestScript class contains scripts for particular module
- TestSuite class contains all the script classes


To Run the scripts:

- Navigate to project root folder from Command Link
- And Run following command (prerequisite - Maven home path should be added to environment variables)

**mvn test**


*Note: Tested this framework template on MAC os only *

