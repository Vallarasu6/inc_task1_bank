<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Address</title>
</head>
<body>
<%@ include file = "header.jsp" %>
  <%@ include file = "LoginSideNav.jsp" %>
  <div style="margin-left:175px;">

    
    
    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button id="loanPaidButton" class="btn btn-primary" name="page" value="loanPaidSubmit">Submit</button>
        </div>
        </div>
</div>
 <%@ include file = "footer.jsp" %>
  <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
       $(document).ready(function(){
	
	$("#loanPaidButton").click(function(){
		
		$.ajax({
			type:'POST',
			url:'MyServlett?page=loanPaidSubmit',
			success: function(result){
				alert(result);
			} 
		});
	});
});
       </script>
</body>
</html>