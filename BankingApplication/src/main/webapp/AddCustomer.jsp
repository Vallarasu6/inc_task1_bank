
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<%@ include file = "header.jsp" %>
<%@ include file = "sidenav.jsp" %>
<div style="margin-left:165px;">

<div class="container">
    <div class="text-center">
    <h2>REGISTRATION FORM</h2></div>
<!-- 
<form action="MyServlett" method="POST"> -->
   
   <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
            <input type="text" name = "Name" class="form-control" id="name" placeholder="Enter your name" required>
           
        </div>
    </div>
     <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Mobile</label>
        <div class="col-sm-10">
            <input type="text" name = "mobile" class="form-control" id="mobile" placeholder="Enter your name" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Bank Name</label>
        <div class="col-sm-10">
            <input type="text" name = "bankname" class="form-control" id="bankName" placeholder="Enter your name" required>
            
        </div>
    </div>
   <div class="form-group row">
        <label for="accountNumber" class="col-sm-2 col-form-label">Account Number</label>
        <div class="col-sm-10">
            <input type="text" name="accountNumber" class="form-control" id="myAccountNumber" placeholder="Enter your Phone number" required>
            <span class= "red-text accent-4" id="result"></span> 
        </div>
    </div>
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
            <button   id="btn1" class="btn btn-primary" name="page" value="submit" > Submit</button>
        </div>
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-warning">Reset</button>
        </div>
    </div>
<!-- </form>  -->
</div>
</div>
<%@ include file = "footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
$(document).ready(function(){
	
	$("#btn1").click(function(){
		var name = $("#name").val();
		var mobile = $("#mobile").val();
		var bankName = $("#bankName").val();
		var myAccountNumber = $("#myAccountNumber").val();
		var balance = $("#balance").val();
		$.ajax({
			type:'POST',
			url:'MyServlett?Name='+name+'&mobile='+mobile+'&bankname='+bankName+'&accountNumber='+myAccountNumber+'&balance='+balance+'&page=submit',
			success: function(result){
				alert(result);
			} 
		});
	});
});
</script>
<!-- <script type="text/javascript">

function validate(){
	
	var name = document.getElementById("name");
	var mobile = document.getElementById("mobile");
	var bankName = document.getElementById("bankName");
	var accountNumber = document.getElementById("myAccountNumber");
	var balance = document.getElementById("balance");

	if(name.value == "" || name.value == null){
		
	name.style.border = "solid 2px red";
	document.getElementById("labelname").style.visibility="visible";
	
	}
	
	else{
/* 		alert("Validate outside null");
 *//*  return true; */
	}
}
</script> -->

</body>

</html>