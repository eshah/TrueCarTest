# TrueCarTest
##Prerequistes:
 - Download Java http://www.oracle.com/technetwork/java/javase/downloads/index.html
 - Download Eclipse Go to http://www.eclipse.org/downloads
 - Download Selenium JAVA Jar Files http://www.seleniumhq.org/download
 - Download TestNG Plugin in Eclipse http://testng.org/doc/download.html
 
##Installation and Running
- Git clone Project locally
- Import Project to Eclipse:
- Open Eclipse
- File -> Import
- Select Existing Projects into Workspace.
- Select root directory

once project is imported, Add Extrernal Jar files to Project
- Right click on Project ->Properties ->Add External Jar Files -> Add Selenium Jar Files 
- Properties -> Add Library -> TestNG

Running:
- Open the MyTrueCar Project
- In the Project there is a "testing.xml" file 
- click on it and Run as Testng Suite
- This will start running the Test.

##To verify Output and Reports:
- After Running the test Refresh" your Project
- Project -> right click -> refresh
- test-output folder is generated which has report html file.
- Copy the Location of the Index.html 
- paste it in browser and reports of the test run will be generated

Also. you can verify running test by having Console open in Eclipse
- TestNg console : "Results of running suite"
- esha test

## Brief Project Overview:
- Created a Selenium Webdriver Project using JAVA and Testng framework

Project has 2 packages which includes configuration files and testcase suites in the package

###Configuration package includes
- configuration properties of the testing website
- also include locators file which has properties of the element for the website to be tested(eg: xpath of elements)

###Testcase Package Includes
- BaseTest as super class which initialize properties of the project (initializing Webdriver, Loading Propteries data)
- and other class extends the base class to run the scenarios 

###Used Testng framework which has few features such as:
- Annotation 
- Report Generation
- Data Provider Annotation
- Can run testcase in a complete suite no need to run one by one.

## Future Enhancments
- Adding more Validation and Assertions
- Creating different classes depending on scenarios
- Data Class to provide Testdata 

## Compromise
- hard coded data 
- need more handling for exception
- cover only positive scenarios which will pass, so need to add more negative scenarios to manage proper testing, failures.

