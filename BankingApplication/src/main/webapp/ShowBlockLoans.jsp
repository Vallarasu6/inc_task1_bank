<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Blocked loans</title>
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
              <th>Un Block</th>
         </tr>
         
         <c:forEach items = "${output}" var = "row">
            <tr>
               <td>${row.id}</td>
                 <td> ${row.bankName}</td>
                   <td>${row.accountNumber}</td>
                     <td>${row.balance}</td>
                      <td><a href="http://localhost:8080/BankingApplication/MyServlett?page=loanReject&accountNumber=${row.accountNumber}" id="rejectBtn">UnBlock</a>

                    
            </td>
                	
            </tr>
         </c:forEach>
      </table>
      <br><br>
     
 
</div>
<%@ include file = "footer.jsp" %>

</body>
</html>