# restWithKtor
To run the application navigate towards the root of this project and run the following command:
```gradle run```
or run ``src\Application.kt`` file with your IDE.

The application will listen to ``http://localhost:8080``.

# Supported API calls
\* = the URL which the application will listen to

The RESTful API support the following HTTP requests.

## POST
### Create new user 
URL = ``*/user``  
Headers = ``Content-Type: application/json``  
Body = ```{
"firstName": "a first name",
"lastName": "a last name",
"age": 0
}```

## GET
### Retrieve all users 
URL = ``*/users``  

## PUT
### Update an user 
URL = ``*/user/{id}``  
``id`` Is the unique identifier of the user in UUID format e.g. ``536ef671-41cc-4fd8-b6cc-f0d17ac501da``  
Headers = ``Content-Type: application/json``  
Body = ```{
"firstName": "a first name",
"lastName": "a last name",
"age": 0
}```  
All fields in the body are required to update an existing user.

## DELETE
### Delete an user
URL = ``*/user/{id}``  
``id`` Is the unique identifier of the user in UUID format e.g. ``536ef671-41cc-4fd8-b6cc-f0d17ac501da``  

# Sources
Used for creating this application.
https://www.novatec-gmbh.de/en/blog/creating-a-rest-application-with-ktor-and-exposed/
Additional information about the Exposed ORM.
https://github.com/JetBrains/Exposed/wiki/DSL
