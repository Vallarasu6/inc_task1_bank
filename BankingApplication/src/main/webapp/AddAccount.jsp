<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img src="zoho-logo.png" class="img-rounded" alt="zoho image" width="90" height="90" >
    </a>
    <form class="form-inline">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign In</button>
    </form>
</nav>
<div class="container">
    <div class="text-center">
    <h2>REGISTRATION FORM</h2></div>

    <form action="MyServlett" method="POST">
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Id</label>
        <div class="col-sm-10">
            <input type="text" name = "Id" class="form-control" id="name" placeholder="Enter your name" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Bank Name</label>
        <div class="col-sm-10">
            <input type="text" name = "bankname" class="form-control" id="name" placeholder="Enter your name" required>
        </div>
    </div>
   
  
    <div class="form-group row">
        <label for="accountNumber" class="col-sm-2 col-form-label">Account Number</label>
        <div class="col-sm-10">
            <input type="text" name="accountNumber" class="form-control" id="accountNumber" placeholder="Enter your Phone number" required>
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
            <button type="submit" class="btn btn-primary" name="page" value="Submit">Submit</button>
        </div>
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-warning"> Reset</button>
        </div>
    </div>
</form>
</div>
</body>
</html>
