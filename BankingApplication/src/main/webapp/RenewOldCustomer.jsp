<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Renew old customer</title>
</head>
<body>
<!-- <form action="MyServlett" method="POST"> -->
<%@ include file = "header.jsp" %>
  <%@ include file = "sidenav.jsp" %>
<div style="margin-left:170px;">
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Id</label>
        <div class="col-sm-10">
            <input type="text" name = "Id" class="form-control" id="id" placeholder="Enter customerId" required>
        </div>
    </div>
    
    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button id="renewCustomerBtn" class="btn btn-primary" name="page" value="RenewCustomerSubmit">Submit</button>
        </div>
        </div>
        </div>
         <%@ include file = "footer.jsp" %>
      <!--   </form> -->
      <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
$(document).ready(function(){
	
	$("#renewCustomerBtn").click(function(){
		var id = $("#id").val();
		
		$.ajax({
			type:'POST',
			url:'MyServlett?Id='+id+'&page=RenewCustomerSubmit',
			success: function(result){
				alert(result);
			} 
		});
	});
});
</script>
</body>
</html>