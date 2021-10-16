<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Customer</title>
</head>
<body>

<form action="MyServlett" method="POST">
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Id</label>
        <div class="col-sm-10">
            <input type="text" name = "Id" class="form-control" id="name" placeholder="Enter customerId" required>
        </div>
    </div>
    
    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-primary" name="page" value="DeleteCustomerSubmit">Submit</button>
        </div>
        </div>
        </form>

</body>
</html>