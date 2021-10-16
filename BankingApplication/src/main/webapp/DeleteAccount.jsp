<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Account</title>
</head>
<body>
<form action="MyServlett" method="POST">
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Id</label>
        <div class="col-sm-10">
            <input type="text" name = "AccountNumber" class="form-control" id="name" placeholder="Enter Account Number" required>
        </div>
    </div>
    
    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-primary" name="page" value="DeleteAccountSubmit">Submit</button>
        </div>
        </div>
</body>
</html>