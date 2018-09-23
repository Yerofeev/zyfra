# Zyfra
## About
APIs to work with hierarchical model of some factory:
Workshop(s)--  
            |  
            Room(s)--  
                    |  
                    Tool(s)--  
                            |  
                            Sensor(s)  
          
App uses embedded H2 db.

## Installation
Ways to run the program:
1. From your IDE
2. Using maven: 
* `maven clean package`
* `java -jar java -jar zyfra-enterprise-1.0.0.jar`
3. via Docker:
* `./build.sh`

## Usage
1. The swagger documentation will be available on: 
http://localhost:8082/swagger-ui.html#
List of APIs:
1. GET
* /entities  - returns all entities with sub-entities
* /entity/{id} -returns only one specified entity
* /entity/full/{id} - return specified entity with sub-entities
2. POST
* /entity  - create entity according to model (see Swagger/Postman for complete documentation)
3. PUT
* /entity/{id} - change specified entity according to model
4. DELETE
* /entity/{id} - delete specified entity with all sub-entities
