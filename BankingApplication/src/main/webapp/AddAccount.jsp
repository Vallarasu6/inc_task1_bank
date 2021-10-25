<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<%@ include file = "header.jsp" %>
<%@ include file = "LoginSideNav.jsp" %> 
<div>

<div class="container">
    <div class="text-center">
    <h2>REGISTRATION FORM</h2></div>

    
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Bank Name</label>
        <div class="col-sm-10">
            <input type="text" name = "bankname" class="form-control" id="bankname" placeholder="Enter bank name" required>
            <p id="bankCheck" style="color:red" ></p>
        </div>
    </div>
   
  
  
     <div class="form-group row">
        <label for="balance" class="col-sm-2 col-form-label">Balance</label>
        <div class="col-sm-10">
            <input type="text" name="balance" class="form-control" id="balance" placeholder="Enter your balance" required>
            <p id="balanceCheck" style="color:red" ></p>
        </div>
    </div>
  

    
     <br>
        <div class="form-group row">

            <a href="https://www.zoho.com/">Terms and Conditions</a>

        </div>


    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button id="accountSubmit" class="btn btn-primary" onclick = "validate()" name="page" value="Submit">Submit</button>
        </div>
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-warning"> Reset</button>
        </div>
    </div>
<!-- </form> -->
</div>
</div>
<%@ include file = "footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">

function validate(){
	
	var c = document.getElementById("bankname").value;
	var d = document.getElementById("balance").value;
	
if(c==''){
	
	document.getElementById("bankCheck").innerHTML="Please enter your bank name";
	 document.getElementById("bankCheck").style.display='block';
}
 else{
	document.getElementById("bankCheck").style.display='none';
} 
if(d==''){
	
	document.getElementById("balanceCheck").innerHTML="Please enter your balance";
	 document.getElementById("balanceCheck").style.display='block';
}
 else{
	document.getElementById("balanceCheck").style.display='none';
} 
 
 if(document.getElementById("bankCheck").style.display=='none' &&document.getElementById("balanceCheck").style.display=='none'  )
save();
} 

 function save(){
		
		var bankName = $("#bankname").val();
		var balance = $("#balance").val();
		$.ajax({
			type:'POST',
			url:'MyServlett?bankname='+bankname+'&balance='+balance+'&page=Submit',
			success: function(result){
				alert(result);
			} 
		});
	} 








$(document).ready(function(){
	$("#accountSubmit").click(function(){
		var bankname = $("#bankname").val(); 
		var balance = $("#balance").val(); 
		$.ajax({
			type:'POST',
			url:'MyServlett?bankname='+bankname+'&balance='+balance+'&page=Submit',
			success: function(result){
				alert(result);
			} 
		});
		
	});
});
</script>
</body>
</html>
