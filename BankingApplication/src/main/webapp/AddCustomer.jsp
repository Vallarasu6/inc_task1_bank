
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<%@ include file = "header.jsp" %>
<%-- <%@ include file = "sidenav.jsp" %> --%>
<div>

<div class="container">
    <div class="text-center">
    <h2>REGISTRATION FORM</h2></div>
<!-- 
<form action="MyServlett" method="POST"> -->
   
   <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
            <input type="text" name = "Name" class="form-control" id="name" placeholder="Enter your name" >
           <p id="nameCheck" style="color:red" ></p>
        </div>
    </div>
     <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Mobile</label>
        <div class="col-sm-10">
            <input type="text" name = "mobile" class="form-control" id="mobile" placeholder="Enter your mobile number">
            <p id="mobileCheck" style="color:red" ></p>
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-sm-2 col-form-label">Bank Name</label>
        <div class="col-sm-10">
            <input type="text" name = "bankname" class="form-control" id="bankName" placeholder="Enter your bank name" >
            <p id="bankCheck" style="color:red" ></p>
            
        </div>
    </div>
 
     <div class="form-group row">
        <label for="balance" class="col-sm-2 col-form-label">Balance</label>
        <div class="col-sm-10">
            <input type="text" name="balance" class="form-control" id="balance" placeholder="Enter your balance" >
            <p id="balanceCheck" style="color:red" ></p>
        </div>
    </div>
    <div class="form-group row">
        <label for="balance" class="col-sm-2 col-form-label">Address</label>
        <div class="col-sm-10">
            <textarea rows="4" cols="100" name="Address" class="form-control" id="address"></textarea>
            <p id="addressCheck" style="color:red" ></p>
            
        </div>
    </div>
  

    
     <br>
        <div class="form-group row">

            <a href="https://www.zoho.com/">Terms and Conditions</a>

        </div>


    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button   id="btn1" class="btn btn-primary" onclick = "validate()" name="page" value="submit" > Submit</button>
        </div>
        <div class="col-sm-6 text-center">
            <button type="submit" class="btn btn-warning">Reset</button>
        </div>
    </div>
<!-- </form>  -->
</div>
</div>
<%@ include file = "footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<script>

function validate(){
	var a = document.getElementById("name").value;
	var b = document.getElementById("mobile").value;
	var c = document.getElementById("bankName").value;
	var d = document.getElementById("balance").value;
	var e = document.getElementById("address").value;
	
	if(a==''){
		
		document.getElementById("nameCheck").innerHTML="Please enter your name";
		 document.getElementById("nameCheck").style.display='block';
	}
	else{
		document.getElementById("nameCheck").style.display='none';
	} 
if(b==''){
		
		document.getElementById("mobileCheck").innerHTML="Please enter your mobile number";
		 document.getElementById("mobileCheck").style.display='block';
	}
	else{
		document.getElementById("mobileCheck").style.display='none';
	} 
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
 if(e==''){
	
	document.getElementById("addressCheck").innerHTML="Please enter your address";
	 document.getElementById("addressCheck").style.display='block';
}
 else{
	document.getElementById("addressCheck").style.display='none';
}  
 if(document.getElementById("nameCheck").style.display=='none' && document.getElementById("mobileCheck").style.display=='none' &&document.getElementById("bankCheck").style.display=='none' &&document.getElementById("balanceCheck").style.display=='none' && document.getElementById("addressCheck").style.display=='none' )
save();
} 

 function save(){
		var name = $("#name").val();
		var mobile = $("#mobile").val();
		var bankName = $("#bankName").val();
		var address = $("#address").val();
		var balance = $("#balance").val();
		$.ajax({
			type:'POST',
			url:'MyServlett?Name='+name+'&mobile='+mobile+'&bankname='+bankName+'&balance='+balance+'&Address='+address+'&page=submit',
			success: function(result){
				alert(result);
			} 
	 	});
	} 

</script>





</body>

</html>