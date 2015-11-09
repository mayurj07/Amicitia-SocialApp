#Amicitia-SocialApp

##A set of REST APIs to manage entities and relationships in a social app.

##CMPE 275 Lab - 2

Team 22 :

#####Member1 : Mayur Jain     | mayurj07@gmail.com
#####Member2 : Akanksha Singh | akanksha.singh@sjsu.edu
#####Member3 : Harkirat Singh | harkirat.singh@sjsu.edu
#####Member4 : Surbhi Garg    | garg.surbhi90@gmail.com

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

1. Create Organization
<img width="1082" alt="create org" src="https://cloud.githubusercontent.com/assets/8734557/11049775/bb96a2e8-86f5-11e5-841b-d39fc92cfd8a.png">

2. Get Organization (JSON)
<img width="1045" alt="get org" src="https://cloud.githubusercontent.com/assets/8734557/11049784/bbbe23b8-86f5-11e5-99e7-13eae66a48e6.png">

3. Get Organization (HTML)
<img width="1041" alt="get org html" src="https://cloud.githubusercontent.com/assets/8734557/11049782/bbb72ed2-86f5-11e5-866b-976f54264ef8.png">

4. Get Organization (XML)
<img width="1079" alt="get org xml" src="https://cloud.githubusercontent.com/assets/8734557/11049783/bbbaac4c-86f5-11e5-8be9-149e27922d13.png">

5. Update Organization
<img width="1049" alt="update org" src="https://cloud.githubusercontent.com/assets/8734557/11049790/bbde51a6-86f5-11e5-9bfd-9e5c205a1c8d.png">

6. Get Organization After Updating (JSON)
<img width="1045" alt="get org after update" src="https://cloud.githubusercontent.com/assets/8734557/11049781/bbb4e7b2-86f5-11e5-860c-f08203106018.png">

7. Create Person(To show input Parameters)
<img width="1085" alt="create person" src="https://cloud.githubusercontent.com/assets/8734557/11049777/bb9c6070-86f5-11e5-8cfa-2fd360955ac5.png">

8. Create Person (JSON Output)
<img width="1070" alt="create person-1" src="https://cloud.githubusercontent.com/assets/8734557/11049776/bb9b9104-86f5-11e5-9719-02203b349e61.png">

9. Update Person
<img width="1077" alt="update person" src="https://cloud.githubusercontent.com/assets/8734557/11049792/bbe6987a-86f5-11e5-8033-a874a1387c05.png">
<img width="1072" alt="update person-1" src="https://cloud.githubusercontent.com/assets/8734557/11049791/bbe0a334-86f5-11e5-9597-04f0bcd186ee.png">

10. Get Person (JSON)
<img width="1073" alt="get person json" src="https://cloud.githubusercontent.com/assets/8734557/11049786/bbcb59de-86f5-11e5-8a7e-87b972119723.png">

11. Get Person (HTML)
<img width="1074" alt="get person html" src="https://cloud.githubusercontent.com/assets/8734557/11049785/bbc3dfba-86f5-11e5-8735-9f11e5a3df77.png">

12. Get Person (XML)
<img width="1087" alt="get person xml" src="https://cloud.githubusercontent.com/assets/8734557/11049787/bbd4c668-86f5-11e5-940d-09510f269439.png">

13. Delete Person
<img width="1083" alt="delete person" src="https://cloud.githubusercontent.com/assets/8734557/11049780/bbb06944-86f5-11e5-8a15-5d95a2327147.png">

14. After Person Delete
<img width="1078" alt="after delete" src="https://cloud.githubusercontent.com/assets/8734557/11049773/bb91c688-86f5-11e5-8d99-222ad4c192af.png">

15. Delete Organization Failed since Organization have members
<img width="1071" alt="delete org bad" src="https://cloud.githubusercontent.com/assets/8734557/11049778/bba0875e-86f5-11e5-9cd5-0fe84ce117d1.png">

16. Delete Organization
<img width="1068" alt="delete org" src="https://cloud.githubusercontent.com/assets/8734557/11049779/bba99f06-86f5-11e5-8e8f-313437e22f55.png">

17. Add Friend
<img width="1077" alt="make friends" src="https://cloud.githubusercontent.com/assets/8734557/11049788/bbd76440-86f5-11e5-8709-51dc301e726c.png">

18. Attempt to make friends when already Friends.
<img width="1070" alt="already friends" src="https://cloud.githubusercontent.com/assets/8734557/11049774/bb95582a-86f5-11e5-989f-3492794f8bc8.png">

19. Delete Friend (Un-friend)
<img width="1073" alt="unfriend" src="https://cloud.githubusercontent.com/assets/8734557/11049789/bbda5ca4-86f5-11e5-9a7a-1d7f1bf9a802.png">

