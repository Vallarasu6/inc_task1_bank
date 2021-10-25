<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Address</title>
</head>
<body>
<%@ include file = "header.jsp" %>
  <%@ include file = "LoginSideNav.jsp" %>
  <div style="margin-left:175px;">
<div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter your Subscribe number</label>
        <div class="col-sm-10">
            <input type="text" name = "Mobile" class="form-control" id="mobile" placeholder="Enter Subscribe Number" required>
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter amount to recharge</label>
        <div class="col-sm-10">
            <input type="text" name = "Amount" class="form-control" id="amount" placeholder="Enter amount" required>
        </div>
    </div>
    
    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button id="mobileRechargeBtn" class="btn btn-primary" name="page" value="mobileRechargeSubmit">Submit</button>
        </div>
        </div>
</div>
 <%@ include file = "footer.jsp" %>
  <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
       $(document).ready(function(){
	
	$("#mobileRechargeBtn").click(function(){
		var mobile= $("#mobile").val();
		var amount= $("#amount").val();
		
		$.ajax({
			type:'POST',
			url:'MyServlett?Mobile='+mobile+'&Amount='+amount+'&page=mobileRechargeSubmit',
			success: function(result){
				alert(result);
			} 
		});
	});
});
       </script>
</body>
</html>