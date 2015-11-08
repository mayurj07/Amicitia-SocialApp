<%--
  Created by IntelliJ IDEA.
  User: mjain
  Date: 11/8/15
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Person Table</title>
</head>
<body>
<h2>Person: </h2>
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    th, td {
        padding: 15px;
    }
</style>
<table style="width:100%">
    <tr>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Email</td>
        <td>Description</td>
        <td>Street</td>
        <td>City</td>
        <td>State</td>
        <td>Zip</td>
        <td>Organization Id</td>
        <td>Organization Name</td>

    </tr>
    <tr>
        <td>
            ${person.firstname}
        </td>
        <td>
            ${person.lastname}
        </td>
        <td>
            ${person.email}
        </td>
        <td>
            ${person.description}
        </td>
        <td>
            ${person.address.street}
        </td>
        <td>
            ${person.address.city}
        </td>
        <td>
            ${person.address.state}
        </td>
        <td>
            ${person.address.zip}
        </td>
        <td>
            ${person.org.id}
        </td>
        <td>
            ${person.org.name}
        </td>
    </tr>
</table>
</body>
</html>
