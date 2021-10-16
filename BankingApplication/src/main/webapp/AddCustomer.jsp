
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<h1>Add Customer</h1>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img src="zoho-logo.png" class="img-rounded" alt="zoho image" width="90" height="90" >
    </a>
    <form class="form-inline">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Vallarasu</button>
    </form>
</nav>
<div class="container">
    <div class="text-center">
    <h2>REGISTRATION FORM</h2></div>

<form action="MyServlett" method="POST">
   
   <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
            <input type="text" name = "Name" class="form-control" id="name" placeholder="Enter your name" required>
            <!-- <label id="labelname" style="color:red; visibility:hidden;">Invalid Input</label> -->
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
            <!-- <span class= "red-text accent-4" id="result">Invalid</span> -->
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
            <button type="submit"  class="btn btn-primary" name="page" value="submit" > Submit</button>
        </div>
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-warning">Reset</button>
        </div>
    </div>
</form> 
</div>
<script type="text/javascript">

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
</script>

</body>

</html>