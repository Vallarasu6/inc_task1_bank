<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All history</title>
</head>
<body>
<!-- <form action="MyServlett" method="POST"> -->
    <%@ include file = "header.jsp" %>
  <%@ include file = "sidenav.jsp" %>
<div style="margin-left:175px;">
     <div class="form-group row">
    <label for="name" class="col-sm-2 col-form-label">Account Number</label>
        <div class="col-sm-10">
            <input type="text" name = "accountNumber" class="form-control" id="accountNumber" placeholder="Enter Account Number" required>
        </div>
    </div>
    
    
    
    <div class="form-group row">
        <div class="col-sm-6 text-center">
            <button id="allHistorybtn" class="btn btn-primary" name="page" value="allHistorySubmit">Submit</button>
        </div>
        </div>
   

        
        <div id="historyInfo"></div>
        
        </div>
        <%@ include file = "footer.jsp" %>
        <!-- </form> -->
         
         
         <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<script>
       $(document).ready(function(){
    	   
	$("#allHistorybtn").click(function(){
		var accountNumber = $("#accountNumber").val();
	
		
		$.ajax({
			type:'POST',
			url:'MyServlett?accountNumber='+accountNumber+'&page=allHistorySubmit',
			headers:{
				Accept:"application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},	
			success: function(result){
				alert(result);
				result=JSON.parse(result);
			    alert(result.length);
			var tbltop = "<table border='1' width=100%><tr><th>Process</th><th>Amount</th><th>Date</th></tr>";
				var main ="";
	            for (i = 0; i < result.length; i++){
	            	//alert(result[i].process);
	              main += "<tr><td>"+result[i].process+"</td><td>"+result[i].amount+"</td><td>"+result[i].date+"</td></tr>";
	            }
	            var end = "</table>"
	            var tbl = tbltop + main + end;
	           
	            $("#historyInfo").html(tbl);
	           
	            
				
			} 
		});
	});
});
       </script>
</body>
</html>