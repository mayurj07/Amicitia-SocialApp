#Amicitia-SocialApp

##A set of REST APIs to manage entities and relationships in a social app.

##CMPE 275 Lab - 2

Team 22 :

#####Member1 : Mayur Jain     | 009991059 | mayurj07@gmail.com
#####Member2 : Akanksha Singh | 010030839 | akanksha.singh@sjsu.edu,
#####Member3 : Harkirat Singh | 010027823 | harkirat.singh@sjsu.edu

Note: This assignment was created using IntelliJ IDEA IDE.

###How the project is build:
1) Select spring maven project.
2) Add all the maven dependencies (refer this pom.xml)


###How application works:
1)PersonController class contains all the APIs related to person. It contains API for -
              Create Person
              Delete Person
              Update Person Info
              Get Person Info

2) All the APIs in PersonController call menthods of PersonService, Which further calls methods of PersonDAO.

3) OrganizationController has  all the APIs related to organization data. It contains following APIs -
              Create Organization
              Delete Organization
              Update Organization Info
              Get Organization Info

4)All the methods in OrganizationController call methods of OrganizationService, which further calls methods of OrganizationDAO.

5)FriendshipController has  all the APIs related to organization data. It contains following APIs -
              Add Friendship
              Remove Friendship

 6)All the methods in FriendshipController call methods of FriendshipService, which further calls methods of FriendshipDAO.

-------package: edu.sjsu.cmpe275.lab2;


Note: Mysql dump file also included (filename:    SQLscript.sql)


-pom.xml: All the maven dependencies go inside this file.


Maven must be installed.

###How to Run:

```java
1. $ mvn clean install.

2. Start Tomcat server.
```

###Application Tests Screenshots


