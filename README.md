
# rest-assignment
- The following project demonstrates testing the GET, POST, PUT and DELETE services from https://jsonplaceholder.typicode.com/ 
- The project uses REST-Assured and Junit framework to test the services.

# APIs automated 
- GET service :
    - To fetch all records : https://jsonplaceholder.typicode.com/posts 
    - To fetch specific record : https://jsonplaceholder.typicode.com/posts/1
    - To trigger invalid call : https://jsonplaceholder.typicode.com/invalidposts 
- POST service :
    - create a record : https://jsonplaceholder.typicode.com/posts
      body  { 
              "title":"foo", 
              "body": "bar",
              "userId": 1
            }
 - PUT service :
      - update a record : https://jsonplaceholder.typicode.com/posts
        
        body  { 
                "title":"abc", 
                "body": "xyz",
                "userId": 1
              }
  - DELETE service :
       - delete a record : https://jsonplaceholder.typicode.com/posts/1 
        
        
        
# Requirements
- JAVA [version: 1.8]
- Maven [version: 3.3.9]

# Running the project:

  1. setting JAVA_HOME : 
  - Follow the steps provided in the link url [here](https://docs.oracle.com/cd/E19182-01/821-0917/inst_jdk_javahome_t/index.html)
    
  2. setting M2_HOME : 
  - Follow the steps provided in the link [here](http://websystique.com/maven/maven-installation-and-setup-windows-unix/)
     
  3. Pull the project from repository, navigate till the location of "pom.xml" file.
  ```
  git clone https://github.com/karthik-raman/rest-assignment.git
  cd rest-assignment
  ```
  
  4. run the following mvn command: 
   ```
   mvn clean install
   ```
   
# when the command is executing the testers will be excuted, and the log file for the testers can be found in "logs/resttest.html"
