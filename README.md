# Applicant test

## Task Description
You should be able to start the example application by executing com.Right.RightTestApplication, which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoints.

The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring Boot
* Database H2 (In-Memory)
* Maven


You should be aware of the following conventions while you are working on this exercise:

 * Member having an ID.
 * The architecture of the web service is built with the following components:
 	* DataTransferObjects: Objects which are used for outside communication via the API
    * Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
    * Service: Implements the business logic and handles the access to the DataAccessObjects.
    * DataAccessObjects: Interface for the database. Inserts, updates, deletes and reads objects from the database.
    * DomainObjects: Functional Objects which might be persisted in the database.
 * TestDrivenDevelopment, to test the code.
 * Developed the code using eclipse IDE.
 * Uploaded the code using Git repositoty

---


## Task 1
 * Write a new Controller for maintaining member (CRUD).
● Create a new member
● Read an existing member
● Update an existing member
● Delete member(s) which are no longer used
● List existing members
 * Add example data to resources/data.sql
 
 
 ● RESTful Web Service with Spring/Spring Boot
● Member has the following attributes:
○ First name
○ Last name
○ Date of birth
○ Postal code (ZIP)
○ Picture (as File)
● Accepts JSON or XML
● Response in JSON or XML
● Input validation
● Authentication for REST calls
● Error handling via HTTP status code and specific message constraints
● Write tests
● JDK8 or higher
● Build with Maven or Gradle
● Data storage: (in memory) database
● Documentation how to start and how to call the service
● Code should be in a repository
 
 
******************************Validation scnerios ********************************************************
1) Member will create with id as primary id and firstName as unique key
2) Zipcode will be validated by reqular expression
   If zipcode doesnot matched with reqular expression, then it will throw the custom exception with http code.
3) FirstName is mandatory attribute.
4) Loading the file(image) file, param as MultipartFile.
5) If we load the file which is not image file then service will throw custom exception with http code.

*******************************For testcases**************************************************************
1) For TDD, i used mockito and junit api.
2) Tested Coverage report: 78.4%


*******************************Lets start the Member API***************************************************
    
 
Step 1:

I am using spring security with OAuth 2.0 concept:

Step1:  When you will hit this curl to get the all members
Curl:

curl -X GET "http://localhost:8080/v1/member" -H "accept: */*"

Response: 

it will throw the error:
Error:

Response body:

Download
{
  "error": "unauthorized",
  "error_description": "An Authentication object was not found in the SecurityContext"
}

Response headers
 cache-control: no-store 
 content-type: application/json;charset=UTF-8 
 date: Sat, 04 May 2019 13:00:39 GMT 
 pragma: no-cache 
 transfer-encoding: chunked 
 www-authenticate: Bearer realm="oauth2-resource", error="unauthorized", error_description="An Authentication object was not found in the SecurityContext" 
 x-content-type-options: nosniff 
 x-frame-options: DENY 
 x-xss-protection: 1; mode=block 



Step 2: Generate the access token and refresh token by executing below url:
Curl:
HTTP Method: POST 
URL: http://localhost:8080/oauth/token?grant_type=password&username=testNeel&password=123456

IN AUTHORIZATION:
Username: my-trusted-client
Password: secret

Response: 

{
    "access_token": "7c496013-d79a-419e-b604-e89dad409208",
    "token_type": "bearer",
    "refresh_token": "4e236c68-247c-481c-a092-c05f88d922fa",
    "expires_in": 119,
    "scope": "read write trust"
}
 
************************************** TESTED BY POSTMAN *****************************************************

Scnerios 1: CREATE A MEMBER RECORD  --> 
 Curl:
 HTTP Method: POST 
 URL: http://localhost:8080/v1/member/create?access_token=7c496013-d79a-419e-b604-e89dad409208

Input: 
 file: orchidPic.jpg
 memberDTO:
 {"firstName":"neelam", "lastName":"awasthi",  "dateOfBirth":"02/05/1987", "postalCode":"67989"}
 
Output:

 Response:
{
    "id": 1,
    "firstName": "neelam",
    "lastName": "awasthi",
    "dateOfBirth": "02/05/1987",
    "postalCode": "67989",
    "image": {
        "id": "b6e08443-0fe5-4990-812f-08bd405a7e8b",
        "fileName": "orchidPic.jpg",
        "fileType": "image/jpeg",
        "data": "/9j/4AAQSkZJRgABAQEBLAEsAA...."
       },
    "imageUrl": "http://localhost:8080/downloadFile/b6e08443-0fe5-4990-812f-08bd405a7e8b"
}
        

Scnerios 2: CREATE A MEMBER RECORD --> When you pass invalid postalcode while creating member
Curl:
HTTP Method: POST 
URL: http://localhost:8080/v1/member/create?access_token=b71b01a7-d4dc-49df-ba44-06552182d875
 
Input:
 file: orchidPic.jpg 
 
 memberDTO:
 {"firstName":"neelam", "lastName":"awasthi",  "dateOfBirth":"02/05/1987", "postalCode":"6798"}

Output:
 
 Response:
 {
    "timestamp": "2019-05-05T21:45:45.433+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Invalid PostCode , Please enter the valid postalCode",
    "path": "/v1/member/create"
}

Scnerios 3:CREATE A MEMBER RECORD --> When user pass the details without firstname while creating member(firstName is mandatory)

Curl:
HTTP Method: POST 
URL: http://localhost:8080/v1/member/create?access_token=b71b01a7-d4dc-49df-ba44-06552182d875

Input:
 
 file: orchidPic.jpg
 
 memberDTO:
 { "lastName":"awasthi",  "dateOfBirth":"02/05/1987", "postalCode":"63798"}
 
Output:
 Response:
 {
    "timestamp": "2019-05-05T21:47:11.093+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "FirstName is empty, Please enter the firstName",
    "path": "/v1/member/create"
}

Scnerios 3: CREATE A MEMBER RECORD --> When user pass the details invalid passcode while creating member(passcode is validated as per the regular expression)

Curl:
HTTP Method: POST 
URL: http://localhost:8080/v1/member/create?access_token=b71b01a7-d4dc-49df-ba44-06552182d875
 
Input:
 file: orchidPic.jpg
 
 memberDTO:
 {"firstName":"neelam", "lastName":"awasthi",  "dateOfBirth":"02/05/19", "postalCode":"67989"}
 
Output:
 Response:
{
    "timestamp": "2019-05-05T21:49:17.136+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Invalid DateOfBirth, Please enter the valid dateOfBirth",
    "path": "/v1/member/create"
}

Scnerios 4: GET MEMBER RECORD --> Get member details by id = 1
Curl:
 HTTP Method: GET 
 URL : http://localhost:8080/v1/member/1?access_token=88b7c77d-617b-4ed1-b3eb-9d69e098e25e
 
Output:
 Response:
 {
    "id": 1,
    "firstName": "neelam",
    "lastName": "awasthi",
    "dateOfBirth": "02/05/1988",
    "postalCode": "67989",
    "image": {
        "id": "b27a0f0c-b068-4084-aa6b-cc971a808f79",
        "fileName": "orchidPic.jpg",
        "fileType": "image/jpeg",
        "data": "/9j/4AAQSkZJRgABAQEBLAEsAA....."
         },
    "imageUrl": "http://localhost:8080/downloadFile/b27a0f0c-b068-4084-aa6b-cc971a808f79"
}


Scnerios 6: GET ALL MEMBER RECORD --> Get all member details
Curl:
 HTTP Method: GET 
 URL: http://localhost:8080/v1/member?access_token=5c619738-7383-406f-bce8-291f38931257
 
Output:
 Response:
 [
    {
        "id": 1,
        "firstName": "neelam",
        "lastName": "awasthi",
        "dateOfBirth": "02/05/1988",
        "postalCode": "67989",
        "image": {
            "id": "b27a0f0c-b068-4084-aa6b-cc971a808f79",
            "fileName": "orchidPic.jpg",
            "fileType": "image/jpeg",
            "data": "/9j/4AAQSkZJRgABAQEBLAEsAAD..."
             },
        "imageUrl": "http://localhost:8080/downloadFile/b27a0f0c-b068-4084-aa6b-cc971a808f79"
    },
    {
        "id": 3,
        "firstName": "neelam1",
        "lastName": "awasthi",
        "dateOfBirth": "02/05/1987",
        "postalCode": "67989",
        "image": {
            "id": "23d08334-acca-4b35-99b3-4f292e35b638",
            "fileName": "orchidPic.jpg",
            "fileType": "image/jpeg",
            "data": "/9j/4AAQSkZJRgABAQEBLAEsAAD..."},
        "imageUrl": "http://localhost:8080/downloadFile/23d08334-acca-4b35-99b3-4f292e35b638"
    }
]

Scnerios 7: Update a member details
Curl:
 HTTP Method: UPDATE 
 URL: http://localhost:8080/v1/member/update?access_token=418c5e2d-a8db-4b73-8532-4831aebe1129
 
Input:
 file: orchidPic.jpg

 {"id":1,"firstName":"neelamL", "lastName":"awasthi",  "dateOfBirth":"02/05/1988", "postalCode":"67989"}
 
Output:
 Response:
 {
    "id": 1,
    "firstName": "neelamL",
    "lastName": "awasthi",
    "dateOfBirth": "02/05/1988",
    "postalCode": "67989",
    "image": {
        "id": "abcbbaf4-21ae-4fee-ae9c-4644ba2c96c9",
        "fileName": "orchidPic.jpg",
        "fileType": "image/jpeg",
        "data": "/9j/4AAQSkZJRgABAQEBLAEsAA..."
        },
    "imageUrl": "http://localhost:8080/downloadFile/abcbbaf4-21ae-4fee-ae9c-4644ba2c96c9"
}

Scnerios 8: UPDATE MEMBER DETAILS --> Update a member details if member id does not exists
Curl:
HTTP Method: UPDATE 
URL:  http://localhost:8080/v1/member/update?access_token=418c5e2d-a8db-4b73-8532-4831aebe1129
 
Input:
 file: orchidPic.jpg

 {"id":2,"firstName":"neelam", "lastName":"awasthi",  "dateOfBirth":"02/05/1988", "postalCode":"67989"}
 
Output:
 Response:
 {
    "timestamp": "2019-05-05T21:42:48.608+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Could not find entity with id: 2",
    "path": "/v1/member/update"
}


Scnerios 7:Delete the member by id
Curl:
HTTP Method: D 
 http://localhost:8080/v1/member/1?access_token=d616dd33-3008-4d51-8b90-9d87c589b154
 
Check whether data got deleted or not.
 
Curl: GET MEMBER DETAILS ---> get the member by id =1
URL: http://localhost:8080/v1/member/1?access_token=32af1d4e-d545-48ee-ae7c-fdbafe8bc8e2
 
 Response:
 {
    "timestamp": "2019-05-05T22:06:57.665+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Could not find entity with id: 1",
    "path": "/v1/member/1"
 }
 
 

---


Thanks,
Neelam



