<%@page import="com.mysql.cj.result.Row"%>
<%@page import="bank.pojo.CustomerInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer-Details</title>
</head>
<body>
    <%@ include file = "header.jsp" %>
    <%@ include file = "sidenav.jsp" %>
    <div style="margin-left:185px; margin-top:20px;">
  <table  border = "1" width = "100%">
         <tr>
            <th>Id</th>
            <th>Name</th>
            <th>mobile</th>
           <!--  <th>Delete</th> -->
         </tr>
         
         <c:forEach items = "${output}" var = "row">
            <tr>
               <td>${row.id}</td>
                 <td> ${row.name}</td>
                   <td>${row.mobileNumber}</td>
               
                   
           
            </tr>
         </c:forEach>
      </table>
      <br><br>
      <table width = "100%">
      <tr>
            <th><button><a href="http://localhost:8080/BankingApplication/MyServlett?page=addcustomer">Add New Customer</a>
            </button></th>
            <th><button><a href="http://localhost:8080/BankingApplication/MyServlett?page=deleteCustomer">Delete Customer</a>
            </button></th>
            
       </tr>
         
      </table>
 
</div>
<%@ include file = "footer.jsp" %>

</body>
</html>