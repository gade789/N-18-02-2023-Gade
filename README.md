# CoducerCodingTest

This is a Spring Boot application that provides two APIs for getting a token and posting data.

#Requirements

To run this application, you will need to have the following installed on your system:

1. Java 11 or later
2. Maven 3.6 or later
3. MySQL 8 or later

#Installation

To install and run this application, follow these steps:

Clone this repository to your local machine: 

https://github.com/gade789/N-18-02-2023-Gade.git

Build the application using Maven:

mvn clean package

Run the application using Maven:

mvn spring-boot:run
This will start the application on port 8081.

#Usage


POST /service/v1/token
This API generates a token based on the provided username and password.
it generate token using User_id and encode it with user using JWT Tokenenizer

Request

POST /service/v1/token HTTP/1.1
Host: localhost:8081
Content-Type: application/json

{
    "username": "myusername",
    "password": "mypassword"
}

Response
If the request is successful, the API returns a JSON object containing the generated token:

HTTP/1.1 200 OK
Content-Type: application/json

{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}

If the request is unsuccessful, the API returns an error message:

HTTP/1.1 401 Unauthorized
Content-Type: application/json

{
    "error": "Authorization failure !!..Please Check Username Or Password"
}

If the username is not matched, the API returns an error message:

HTTP/1.1 404 Not_Found
Content-Type: application/json

{
    "error": "Invalid Username"
}



POST /service/v1/review
This API posts review to the server. if token authrization is sucessfull

Request
POST /service/v1/review HTTP/1.1
Host: localhost:8081
Content-Type: application/json
Authorization: Bearer "Your_token_here"

{

    "review": "Your_review_here"
}

Response
If the request is successful, the API returns a success message:

HTTP/1.1 200 OK
Content-Type: application/json

{
    "message": "Review created Successfully.."
}

If the token validation fails, the API returns an message:

HTTP/1.1 401 Unauthorized

If the exception occours, the API returns an error message:

HTTP/1.1 500 Internal Server Error

{
    "error": "Internal Server Error"
}



GET /service/v1/review/{id}
This API returns reviews based on user ID. if token authrization is successfull

Request
GET /service/v1/review/{id} HTTP/1.1
Host: localhost:8081
Content-Type: application/json
Authorization: Bearer "Your_token_here"

{

    "review": "this is sample review",
    
}

Response

If the request is successful, the API returns a success message:

HTTP/1.1 200 OK
Content-Type: application/json

{
    "review1": "this is sample review1",
    "review2": "this is sample review2"
}

If the token is not matched for given id, the API returns a error message:

HTTP/1.1 401 Unauthorized

If the token is not matched for given id, the API returns a error message:

HTTP/1.1 401 Unauthorized

If the exception occours, the API returns an error message:

HTTP/1.1 500 Internal Server Error

/service/v1/review



DELETE /service/v1/review
This API delete reviews by user ID. id is decoded from token using JWT Token

Request
DELETE /service/v1/review HTTP/1.1
Host: localhost:8081
Authorization: Bearer "Your_token_here"


Response

If the request is successful, the API returns a success message:

HTTP/1.1 200 OK

{
    "Review Deleted Successfully.."
}

If the token is valid, the API returns a error message:

HTTP/1.1 401 Unauthorized

If the exception occours, the API returns an error message:

HTTP/1.1 500 Internal Server Error



Conclusion
That's it! You can now use this Spring Boot application to generate a token and post data to the server. If you encounter any issues or have any questions, feel
to contact me..

