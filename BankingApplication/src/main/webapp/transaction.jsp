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
  <%@ include file = "LoginSideNav.jsp" %>
<div style="margin-left:170px;">
  
     <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter Amount</label>
        <div class="col-sm-10">
            <input type="text" name = "amount" class="form-control" id="amount" placeholder="Enter Amount" required>
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
		
		var amount = $("#amount").val(); 
		var receiverAccountNumber = $("#receiverAccountNumber").val(); 
		$.ajax({
			type:'POST',
			url:'MyServlett?amount='+amount+'&receiverAccountNumber='+receiverAccountNumber+'&page=transactionSubmit',
			success: function(result){
				alert(result);
			} 
		});
		
	});
});
</script>
</body>
</html>