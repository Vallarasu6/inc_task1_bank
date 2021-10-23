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
        <label for="name" class="col-sm-2 col-form-label">Enter Your new address</label>
        <div class="col-sm-10">
            <input type="text" name = "addressUpdate" class="form-control" id="address_update" placeholder="Enter new address" required>
        </div>
    </div>
    
    
    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button id="addressUpdateBtn" class="btn btn-primary" name="page" value="addressUpdateSubmit">Submit</button>
        </div>
        </div>
</div>
 <%@ include file = "footer.jsp" %>
  <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
       $(document).ready(function(){
	
	$("#addressUpdateBtn").click(function(){
		//var accountNumber = $("#accountNumber").val();
		var addressNew = $("#address_update").val();
		
		$.ajax({
			type:'POST',
			url:'MyServlett?addressUpdate='+addressNew+'&page=addressUpdateSubmit',
			success: function(result){
				alert(result);
			} 
		});
	});
});
       </script>
</body>
</html>