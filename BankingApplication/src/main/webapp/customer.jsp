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
    <div>
   
    <input type="text" name= "search" id= "search"  placeholder="search here" style=margin-left:40%;>
    </div><br><br>
  <table  border = "1" width = "100%" id="one">
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
   
 
</div>
<%@ include file = "footer.jsp" %>

</body>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>

	
$(document).ready(function(){
	$('#search').keyup(function(){
		search_table($(this).val());
	});
	function search_table(value){
		$('#one tr').each(function(){
			var found = 'false';
			$(this).each(function(){
				if($(this).text().toLowerCase().indexOf(value.toLowerCase())>=0){
					found = 'true';
				}
			});
			if(found == 'true'){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
	}
});





</script>
</html>