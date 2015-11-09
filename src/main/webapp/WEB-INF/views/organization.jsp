<%--
  Created by IntelliJ IDEA.
  User: mjain
  Date: 11/8/15
  Time: 7:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Organization</title>
</head>
<body>
<h2>Organization: </h2>
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
        <td>ID</td>
        <td>Name</td>
        <td>Description</td>
        <td>Street</td>
        <td>City</td>
        <td>State</td>
        <td>Zip</td>
    </tr>
    <tr>
        <td>
            ${org.id}
        </td>
        <td>
            ${org.name}
        </td>
        <td>
            ${org.description}
        </td>
        <td>
            ${org.address.street}
        </td>
        <td>
            ${org.address.city}
        </td>
        <td>
            ${org.address.state}
        </td>
        <td>
            ${org.address.zip}
        </td>
    </tr>
</table>
</body>
</html>

