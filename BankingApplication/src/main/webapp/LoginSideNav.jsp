  <style type="text/css">
    .sidenav {
  height: 80%;
  width: 169px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 1	;
  background-color:#ffe6b3;
  overflow-x: hidden;
  padding-top: 20px;
  margin-top:120px;
}

.sidenav a {
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  font-size: 18px;
  color: black;
  display: block;
}

.sidenav a:hover {
  color: black;
  background-color: white;
  
}
.active{ background-color: red;}
.main {
  margin-left: 160px; 
  font-size: 20px; 
  padding: 0px 10px;
}

    </style>
<div class="sidenav">

<a href="http://localhost:8080/BankingApplication/MyServlett?page=addaccount">Add New Account</a>
            
<!-- <a href="http://localhost:8080/BankingApplication/MyServlett?page=inActiveCustomer">InActive Customer</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=inActiveAccount">InActive Account</a> -->
<a href="http://localhost:8080/BankingApplication/MyServlett?page=withDraw">With Draw</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=deposit">Deposit</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=transaction">Transaction</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=allHistory">All history</a>
<a href="#"id="balancebtn">Check Account Balance</a>
<select id="update" name="updates" onchange="update(this.value)" style="border=none;">
<option value="">Update Profile</option>
<option value="mobile">Mobile Num</option>

<option value="address" align="center">Address</option>


</select>
<p id="updt"></p>
 <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
function update(x){
	/* $("#update").click(function(){ */
		alert(x);
		/* var a = x; */
		if(x=="mobile"){
			window.location.href = "http://localhost:8080/BankingApplication/MobileUpdate.jsp";
			
		}
		else if(x=="address"){
			window.location.href = "http://localhost:8080/BankingApplication/AddressUpdate.jsp";
		}
		

}




$(document).ready(function(){
	
	$("#balancebtn").click(function(){
		//var id = $("#id").val();
		
		$.ajax({
			type:'POST',
			url:'MyServlett?page=accountBalance',
			success: function(result){
				alert(result);
			} 
		});
	});
});
</script>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=deleteAccounts">Delete Account</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=deleteCustomer">Delete Customer</a>
           
 <a href="http://localhost:8080/BankingApplication/MyServlett?page=home">Logout</a>
</div>