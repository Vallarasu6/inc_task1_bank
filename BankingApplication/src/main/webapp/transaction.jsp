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
  <%@ include file = "sidenav.jsp" %>
<div style="margin-left:170px;">
  <!--   <form action="MyServlett" method="POST"> -->
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter your Id</label>
        <div class="col-sm-10">
            <input type="text" name = "id" class="form-control" id="id" placeholder="Enter your Id" required>
        </div>
    </div>
     <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter your account Number</label>
        <div class="col-sm-10">
            <input type="text" name = "accountNumber" class="form-control" id="accountNumber" placeholder="Enter your account Number" required>
        </div>
    </div>
     <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter Amount</label>
        <div class="col-sm-10">
            <input type="text" name = "amount" class="form-control" id="amount" placeholder="Enter Amount" required>
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
            <button id=transactionBtn class="btn btn-primary" name="page" value="transactionSubmit">Submit</button>
        </div>
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-warning">Reset</button>
        </div>
    </div>
<!-- </form> -->

</div>
<%@ include file = "footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#transactionBtn").click(function(){
		var id = $("#id").val();
		var accountNumber = $("#accountNumber").val();
		var amount = $("#amount").val(); 
		var receiverId = $("#receiverId").val(); 
		var receiverAccountNumber = $("#receiverAccountNumber").val(); 
		$.ajax({
			type:'POST',
			url:'MyServlett?id='+id+'&accountNumber='+accountNumber+'&amount='+amount+'&receiverId='+receiverId+'&receiverAccountNumber='+receiverAccountNumber+'&page=transactionSubmit',
			success: function(result){
				alert(result);
			} 
		});
		
	});
});
</script>
</body>
</html>