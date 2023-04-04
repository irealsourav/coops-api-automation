# coops-api-automation

## pre-requisites::
install java11, maven version >= 3.86
set the Environment variable path as well

## Run from local
download the code from git and go to the root of the folder structure.
Now, after installation of java 11 and maven run this command from command prompt :: mvn clean test

## code folder structure ::

### Dependencies
jackson-databind,testng,rest-assured

I have created four packages :: 
 1. com.takeaway.APIAutomation.endpoints ==> contains class with all endpoints used to automate the scripts
 2. com.takeaway.APIAutomation.model.response ==> verify the response model data 
 3. com.takeaway.APIAutomation.test ==> all test classes with setup as well
 4. com.takeaway.APIAutomation.util ==> To write /read to properties file 
 
### Test flow ::
####At First I created one application inside coops website and got all the OAuth Credential to use.
####I kept all these data in config.properties grant_type=client_credentials,client_secret=b16695b487196bc05dbab7d349c07705,client_id=JustDoIt
####After that I am getting Authtoken , which I am saving as it in config file .
####After that I am calling the /api/me to get the user id and again saving it to config. properties
####Then comes the main apis which needs the user id to function .
####To verify the testdata after receiving the response I am validating with data created in created testData.properties file.
####I have also used invocationCount form TestNg framework to validate API response which is different if call the same api consecutively twice. 

## Report viewer ::
I only used TestNG reporter with maven surefire plgin to check the report.To view it go to root of the folder i.e. coops-api-automation and then go to target\surefire-reports\index.html.

