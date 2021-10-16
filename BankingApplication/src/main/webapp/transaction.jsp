<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction-Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<%@ include file = "header.jsp" %>


    <form action="MyServlett" method="POST">
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter your Id</label>
        <div class="col-sm-10">
            <input type="text" name = "id" class="form-control" id="id" placeholder="Enter your Id" required>
        </div>
    </div>
     <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter your account Number</label>
        <div class="col-sm-10">
            <input type="text" name = "accountNumber" class="form-control" id="name" placeholder="Enter your account Number" required>
        </div>
    </div>
     <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter Amount</label>
        <div class="col-sm-10">
            <input type="text" name = "amount" class="form-control" id="mobile" placeholder="Enter Amount" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter receiver id</label>
        <div class="col-sm-10">
            <input type="text" name = "receiverId" class="form-control" id="receiverId" placeholder="Enter receiver id" required>
        </div>
    </div>
   
  
    <div class="form-group row">
        <label for="accountNumber" class="col-sm-2 col-form-label">Enter receiver Account Number</label>
        <div class="col-sm-10">
            <input type="text" name="receiverAccountNumber" class="form-control" id="receiverAccountNumber" placeholder="Enter receiver Account Number" required>
        </div>
    </div>
     
  

    
     <br>
        <div class="form-group row">

            <a href="https://www.zoho.com/">Terms and Conditions</a>

        </div>


    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-primary" name="page" value="transactionSubmit">Submit</button>
        </div>
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-warning">Reset</button>
        </div>
    </div>
</form>
<%@ include file = "footer.jsp" %>


</body>
</html>