# CoducerCodingTest

This is a Spring Boot application that provides two APIs for getting a token and posting data.

## Requirements

To run this application, you will need to have the following installed on your system:

1. Java 11 or later
2. Maven 3.6 or later
3. MySQL 8 or later

## Installation

To install and run this application, follow these steps:

Clone this repository to your local machine: 
```
https://github.com/gade789/N-18-02-2023-Gade.git
```
Build the application using Maven:
```
mvn clean package
```
Run the application using Maven:
```
mvn spring-boot:run
```
This will start the application on port 8081.

## Usage

A)

**POST /service/v1/token**

This API generates a token based on the provided username and password.

It will generate token using JWT Token and encode User_id with generated token.

Token will last for `5 mins` if time is exceeded token will be expired.

> Request:

POST /service/v1/token    

HTTP/1.1

Host: localhost:8081

Content-Type: application/json

```
{
    "username": "my_username",
    "password": "my_password"
}
```

> Response:

  -  If the request is successful, the API returns a JSON object containing the generated token:

HTTP/1.1 

200 OK

Content-Type: application/json

```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
```

  -  If the username is not matched, the API returns an error message:

HTTP/1.1 

404 Not_Found

```
{
    "Invalid Username"
}
```

  -  If the username and passowrd both not matched, the API returns an error message:

HTTP/1.1 

401 Unauthorized

```
{
    "Authorization failure !!..Please Check Username Or Password"
}
```

 - If the exception occours, the API returns an error message:

HTTP/1.1 

500 Internal Server Error

```
{
    "Internal Server Error"
}
```


B)

**POST /service/v1/review**

This API posts review to the server. if token authrization is successful.

It will map review to user by using user_id and User_id is extracted from token.

Also one user can have many reviews.


> Request:

POST /service/v1/review 

HTTP/1.1

Host: localhost:8081

Content-Type: application/json

Authorization: Bearer "Your_token_here"

```
{

    "review": "Your_review_here"
}
```

> Response:

  - If the token validation fails, the API returns an status:

HTTP/1.1 

401 Unauthorized

  - If the request is successful, the API returns a success message

HTTP/1.1 

200 OK

```
{
    "Review created Successfully.."
}
```

 - If the exception occours, the API returns an error message:

HTTP/1.1 

500 Internal Server Error

```
{
   "Internal Server Error"
}
```


**GET /service/v1/review/{id}**

This API returns reviews based on user ID. if token authrization is successfull.

It will also check given id and id extracted from token is same or not.

Also one user can have many reviews.

> Request:

GET /service/v1/review/{id} 

HTTP/1.1

Host: localhost:8081

Content-Type: application/json

Authorization: Bearer "Your_token_here"


> Response:

  - If the request is successful, the API returns a success message:

HTTP/1.1 

200 OK

Content-Type: application/json

```
{
    "review1": "this is sample review1",
    "review2": "this is sample review2"
}
```

  - If the token is not matched for given id, the API returns a error message:

HTTP/1.1 

401 Unauthorized

  - If the token validation fails, the API returns an status:

HTTP/1.1 

401 Unauthorized

- If the exception occours, the API returns an error message:

HTTP/1.1 

500 Internal Server Error

```
{
    "Internal Server Error"
}
```


**DELETE /service/v1/review**

This API delete reviews by user ID.

User id is extracted from given token using JWT Token.

if user have many reviews, all reviews of that user will be deleted.

> Request:

DELETE /service/v1/review 

HTTP/1.1

Host: localhost:8081

Authorization: Bearer "Your_token_here"


> Response:

  - If the request is successful, the API returns a success message:

HTTP/1.1 

200 OK

```
{
    "Review Deleted Successfully.."
}
```

  - If the token is invalid or token validation fails, the API returns a error message:

HTTP/1.1 

401 Unauthorized

- If the exception occours, the API returns an error message:

HTTP/1.1 

500 Internal Server Error

```
{
    "Internal Server Error"
}
```



## Conclusion
That's it! You can now use this Spring Boot application to generate a token and post data to the server. If you encounter any issues or have any questions, feel
to contact me..

