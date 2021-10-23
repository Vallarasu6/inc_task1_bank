<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>With Draw</title>
</head>
<body>
<%@ include file = "header.jsp" %>
  <%@ include file = "LoginSideNav.jsp" %>
<div style="margin-left:175px;">
<!-- <form action="MyServlett" method="POST"> -->
    
     <!-- <div class="form-group row">
    <label for="name" class="col-sm-2 col-form-label">Account Number</label>
        <div class="col-sm-10">
            <input type="text" name = "accountNumber" class="form-control" id="accountNumber" placeholder="Enter Account Number" required>
        </div>
    </div> -->
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Enter amount to withdraw</label>
        <div class="col-sm-10">
            <input type="text" name = "withDrawAmount" class="form-control" id="withDraw" placeholder="Enter customerId" required>
        </div>
    </div>
    
    
    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button id="withdrawbtn" class="btn btn-primary" name="page" value="withDrawSubmit">Submit</button>
        </div>
        </div>
        </div>
        <%@ include file = "footer.jsp" %>
       <!--  </form> -->
       <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
       $(document).ready(function(){
	
	$("#withdrawbtn").click(function(){
		//var accountNumber = $("#accountNumber").val();
		var withDraw = $("#withDraw").val();
		
		$.ajax({
			type:'POST',
			url:'MyServlett?withDrawAmount='+withDraw+'&page=withDrawSubmit',
			success: function(result){
				alert(result);
			} 
		});
	});
});
       </script>
</body>
</html>