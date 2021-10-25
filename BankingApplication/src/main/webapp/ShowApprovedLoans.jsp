<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show approved loans</title>
</head>
<body>
<%@ include file = "header.jsp" %>
  <%@ include file = "sidenav.jsp" %>
      <div class="main">
  
  <table border = "1" width = "100%">
         <tr>
            <th>Id</th>
            <th>Bank Name</th>
            <th>Account NUmber</th>
              <th>Balance</th>
              <th>Status</th>
         </tr>
         
         <c:forEach items = "${output}" var = "row">
            <tr>
               <td>${row.id}</td>
                 <td> ${row.bankName}</td>
                   <td>${row.accountNumber}</td>
                     <td>${row.balance}</td>
                     <td>Approved</td>
                    
            </td>
                	
            </tr>
         </c:forEach>
      </table>
      <br><br>
     
 
</div>
<%@ include file = "footer.jsp" %>

</body>
</html>