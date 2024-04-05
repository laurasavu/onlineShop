Portfolio
My portfolio as an aspiring Junior Java Developer

About Me
CV
Skills
Tools
Courses
Examples of my work-OnlineShop-Spring Boot application providing a REST API and how it performed using Postman


<font size="20">**About Me**</font>

in today's society, the ability to adapt professionally is an important quality and for me a professional reconversion responds to the new requirements and opportunities in the labor market as well as the needs of my current life. Thus began the interaction with the java programming language as well as the development of knowledge in the field that I want to apply in my professional future.


CV

here is a shortcut to my <a href="https://www.linkedin.com/in/laura-savu-816503105/">CV</a>








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























