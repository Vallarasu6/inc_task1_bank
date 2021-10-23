<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<%@ include file = "header.jsp" %>
<%@ include file = "LoginSideNav.jsp" %> 
<div>

<div class="container">
    <div class="text-center">
    <h2>REGISTRATION FORM</h2></div>

    <!-- <form action="MyServlett" method="POST"> -->
   <!--  <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Id</label>
        <div class="col-sm-10">
            <input type="text" name = "Id" class="form-control" id="id" placeholder="Enter your name" required>
        </div>
    </div> -->
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Bank Name</label>
        <div class="col-sm-10">
            <input type="text" name = "bankname" class="form-control" id="bankname" placeholder="Enter your name" required>
        </div>
    </div>
   
  
   <!--  <div class="form-group row">
        <label for="accountNumber" class="col-sm-2 col-form-label">Account Number</label>
        <div class="col-sm-10">
            <input type="text" name="accountNumber" class="form-control" id="accountNumber" placeholder="Enter your Phone number" required>
        </div>
    </div> -->
     <div class="form-group row">
        <label for="balance" class="col-sm-2 col-form-label">Balance</label>
        <div class="col-sm-10">
            <input type="text" name="balance" class="form-control" id="balance" placeholder="Enter your Phone number" required>
        </div>
    </div>
  

    
     <br>
        <div class="form-group row">

            <a href="https://www.zoho.com/">Terms and Conditions</a>

        </div>


    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button id="accountSubmit" class="btn btn-primary" name="page" value="Submit">Submit</button>
        </div>
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-warning"> Reset</button>
        </div>
    </div>
<!-- </form> -->
</div>
</div>
<%@ include file = "footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#accountSubmit").click(function(){
		//var id = $("#id").val();
		var bankname = $("#bankname").val(); 
		//var accountNumber = $("#accountNumber").val();
		var balance = $("#balance").val(); 
		$.ajax({
			type:'POST',
			url:'MyServlett?bankname='+bankname+'&balance='+balance+'&page=Submit',
			success: function(result){
				alert(result);
			} 
		});
		
	});
});
</script>
</body>
</html>
