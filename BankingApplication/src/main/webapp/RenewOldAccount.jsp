<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Renew old account</title>
</head>
<body>
<form action="MyServlett" method="POST">
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter id</label>
        <div class="col-sm-10">
            <input type="text" name = "Id" class="form-control" id="name" placeholder="Enter customerId" required>
        </div>
    </div>
     <div class="form-group row">
    <label for="name" class="col-sm-2 col-form-label">Account Number</label>
        <div class="col-sm-10">
            <input type="text" name = "accountNumber" class="form-control" id="accountNUmber" placeholder="Enter Account Number" required>
        </div>
    </div>
    
    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-primary" name="page" value="RenewAccountSubmit">Submit</button>
        </div>
        </div>
        </form>
</body>
</html>