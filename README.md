# valtech-application-task

## Task Overview

While working on this task I have managed to refactor by adding new modules so I can seperate this project task into layers and focus each layers logic
to be specific for it's purpose. The new modules which I have done and in the end are used by the Application Servlet are:
1. Improved Mock Constants (improved-mock-constants) - Repository Layer
2. Improved Application Layer (improved-application-service) - Service Layer (Bussiness Logic)
3. Api Layer - Refactored the existing application-main

### Repository Layer

One of the main things which has been done in this part is introducing a Data Structure such as HashMap to persist the Mocked or Dummy Data. 
By keeping them this way it will be easier to retrieve them when a specific entity of the data is requiered. The other part which has been added is a dummy repository which 
can be used to extract the data from Data Structure in this representation of the task but maybe in future from a specific database. When implementing this
Repository Pattern interfaces are used with signature methods not concrete implementations so this layer can be open for extension.
 - HashMap has been chosen because it has the best complexity for retrieving an element by a unique identifer for that element.
 - Working with Generics, when it comes to the Repository Layer to keep this task open for extension if we have additional models not just the Customer
 - I have kept the String form of the customers to leave space for String manipulation Functions

### Service Layer 

Created an additional Service Layer to handle the logic of finding a customer by speaking to the layer below (the Repository Layer), creating a 
specific Customer by extracting the Customer Information from a String using String Manipulations, encapsulating the extracted data into a Customer object
and handling an error when a customer is not find for a given id.
 - Using a Customer class for encapsulation, to keep the data for the customers Structured and easier to use in the other layers.
 - Created a StringUtilities class which is specified for creating a Customer object from a String. Changed the substring function with a easier implementation
 of using split() function and regex.
 - Added logic when a customer is not present to throw a descriptive exception with a descriptive message

### Api Layer
 The ApiLayer should consist only of ApiLogic so only the Request Parametars are being collected in this method, communication with the service layer bellow 
 and generating the response for the sent parameters accordingly.
  - Added GenerationHTML class which is responsible for buidling the HTML Template Responses using String Format insted of String Replacements.

### Changes in the Testing Part

 - Isolated the Test Constants in a seperate File for a better isolation of the Test Code.
 - A new Test has been added to test that part of the Application when a customer cannot be found for a given id and in that case we represent a HTML_ERROR_STRING
 - Adapted the Test accordingly to the new Refactored Application Servlet doGetMethod

### Further improvements

 - Use Template Engine for displaying a HTML or maybe use a whole frontend client to keep the backend logic more focused on the backend part.
 - Persiting the Data in the Database not in Data Structures where the data is kept in memory.
