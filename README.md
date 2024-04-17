## **Portfolio**

<font size="20"></font>
My portfolio as an aspiring Junior Java Developer

* About Me
* CV
* Skills
* Tools
* Courses
* Examples of my work-OnlineShop-Spring Boot application providing a REST API and how it performed using Postman


## **About Me**

<font size="20"></font>

Engineer with experience in research and development, specialized in data collection, processing, and interpretation. I demonstrate abilities in collaborating with multidisciplinary and multicultural teams for identifying and implementing technical solutions

## **CV**

<font size="20"></font>

Here is a shortcut to my <a href="https://www.linkedin.com/in/laura-savu-816503105/">CV</a>

## **Skills**

<font size="20"></font>

You can find examples of my work in Examples of my work

JAVA and Spring Boot: Proficient in Java programming language with expertise in Spring Boot framework 

Unit Testing:
* Proficient in using frameworks like JUnit to construct basic unit tests, ensuring individual components function correctly.
* Test-Driven Development (TDD): I employ TDD principles, composing tests before code implementation, guiding development and fostering code testability.
* Mocking utilizing tools such as Mockito to fabricate mock objects 
* Integration Testing basic understanding in developing integration tests to validate interactions between components
* Test Coverage: I aim for comprehensive test coverage 

API Testing
*familiar with Postman requests



## **Tools**

<font size="20"></font>

* Postman
familiar with Postman requests

* GIT

* Postgresql Database

* Cloud Computing:
 Proficient in basic cloud computing concepts and services,
 with basic experience in utilizing cloud platforms for hosting and managing applications, expecialy Azure and Google Cloud Platform.

* E-commerce Development with WordPress

## **Courses**

<font size="20"></font>

* Java Developer Certification
        Issuer: Bittnet Training
        Date Issued: May 2023

Skills: JUnit, Spring Boot, Back-End Web Development, Postman, Unit Testing, REST APIs, Spring Framework, Model-View-Controller (MVC), Maven, Java Development, Hibernate, Git, GitHub, Application Programming Interfaces (API)

* Microsoft Certified: Azure Fundamentals
        Issuer: Microsoft
        Date Issued: Sep 2022

Skills: Cloud Development, Cloud Computing IaaS, Cloud Computing, Microsoft Azure

* Google IT Support Specialization
        Issuer: Coursera
        Date Issued: Sep 2021
        Credential ID: PRWQEDX4D9ZX

Skills: Programming

* IBM SkillsBuild - What is artificial intelligence?
        Issuer: IBM SkillsBuild
   
* "Java Programming Masterclass covering Java 11 & Java 17" course on Udemy
a comprehensive Java programming course that covers both Java 11 and Java 17 versions. 
This course is designed to provide learners with a solid foundation in Java programming, including both core concepts and advanced topics.


## **Examples of my work**

<font size="20"></font>





OnlineShop is a Spring Boot application providing a REST API.
It provides endpoints for various opperations on Products and Orders and uses a Postgresql Database to save data.

Requirements

        Spring Framework
        SpringBoot

Dependencies

        Postgresql database
        There are a number of third-party dependencies used in the project. 
        Browse the Maven pom.xml file for details of libraries and versions used.

Building the project.You will need:

        Java JDK 19 or higher
        Maven 3.8.1 or higher
        Git
        Postgresql Database

Clone the project and use Maven to build the server

The application listens on the 8080 port.

The project has tests for ProductController, ProductService and ProductRepository.

REST API - The REST API to the OnlineShop app is described below.


Create a new Product

            Request
            POST /product/customerId
            localhost:8080/product/1

                Body
                {
                "code":"newProduct",
                "description": "a description2",
                "price": 23,
                "stock": 21,
                "valid":true,
                "currency":"EUR"
                }

            Response
            Status: 200 OK

Get a specific Product
            Request

            GET /product/productCode
            localhost:8080/product/prod2

            Response
            {"id":59,"code":"prod2","description":"a description2","price":23.0,"stock":21,"valid":true,"currency":"EUR"}

Get a non-existent Product
            Request
            GET /product/productCode
            localhost:8080/product/prod25

            Response
            Status: 400 Bad Request
             Product not Found

Get list of Products

            Request
            GET /product
            localhost:8080/product
            Response
            [{"id":56,"code":"ygi75gff5ly","description":"a new prd","price":12.0,"stock":0,"valid":true,"currency":"EUR"},{"id":59,"code":"prcod2","description":"a description","price":23.0,"stock":25,"valid":true,"currency":"EUR"},{"id":61,"code":"product22","description":"a description2","price":23.0,"stock":21,"valid":true,"currency":"EUR"}]

Change a Product's state
            Request
            PUT /product/customerId
            Body:
            localhost:8080/product/1
            {
            "code":"product22",
            "description": "a description",
            "price": 23,
            "stock": 25,
            "valid":true,
            "currency":"EUR"
            }
            Response
            Status: 200 OK


Attempt to change a Product as Customer

            Request
            PUT /product/customerId
            localhost:8080/product/2
            Body:
            localhost:8080/product/2
            {
            "code":"product22",
            "description": "a description",
            "price": 23,
            "stock": 25,
            "valid":true,
            "currency":"EUR"
            }

            Response
            Status: Status: 400 Bad Request
            user not allowed to update product

Attempt to change a Product using invalid params

            Request
            PUT /Product/customerId
            Body:
            localhost:8080/product/2
            {
            "code":"invalidProduct",
            "description": "a description",
            "price": 23,
            "stock": 25,
            "valid":true,
            "currency":"EUR"
            }

            Response
            Status: 400 BAD Request
            Product not Found

Delete a Product
            Request
            DELETE /product/productCode/customerId
            localhost:8080/product/product22/1

            Response
            Status: 200 OK

            Try to delete same Product again

            Request
            DELETE /product/productCode/customerId
            localhost:8080/product/product22/1

            Response
            Status: 400 BAD Request
            Product not Found

Get deleted Product

            Request
            GET /product/productCode
            localhost:8080/product/product22

            Response
            Status: 400 BAD Request
            Product not Found

            Create a new Order 

            Request
            POST /order/customerId
            localhost:8080/order/2
            Body
            {
            "userId":2,
            "idTOQuantity":{
                    "59":8
                }
            }
            Response
            Status: 200 OK

Create a new Order when the amount of products ordered is greater than the stock

            Request
            POST /order/customerId
            localhost:8080/order/2
            Body
            {
            "userId":2,
            "idTOQuantity":{
                    "56":8
                }
            }
            Response
            Status: 400 BAD Request
            Invalid stock for this product

Adding stock

            Request
            PatchMapping/product/productCode/quantity/customerId
            localhost:8080/product/product22/23/1
            Response
            Status: 200 OK

            Adding stock when user is customer

            Request
            PatchMapping/product/productCode/quantity/customerId
            localhost:8080/product/product22/23/1
            Response
            Status: 400 BAD Request
            Customer not allowed to add stock

Adding stock when product not in DB

            Request
            PatchMapping/product/productCode/quantity/customerId
            localhost:8080/product/product2/23/2
            Response
            Status: 400 BAD Request
            Product not found























