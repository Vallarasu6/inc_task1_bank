<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  
</head>
<style>

html{
  height:100%
}
body{
  height:100%;
}
.intro{
  height:100%
}

.left{
  display : flex;
  justify-content : center;
  align-items : center;
  background-color:#3d231b;
  height : 100%;
  color : #FFF;

}
.right{
  display : flex;
  justify-content : center;
  align-items : center;
  background-color:#FFF;
  height : 100%;
  color : #3d231b;
}
@media (max-width: 1200px) { 
  .left{
    height:50%
  }
  .right{
    height:50%
  }
}
.about{
  display : flex;
  justify-content : center;
  align-items : center;
  background-color:#000;
  color : #FFF;
  height:100%
}


</style>
<body>

<%@ include file = "header.jsp" %>


 
   
     <div class="container">
  <form action="MyServlett" method="POST">
    <div class="col">
    <div class="container">
    <div class="text-center">
       <div class="form-group row" >
        <label for="name" class="col-sm-12 col-form-label fw-bold mb-2 text-uppercase "><b>User Login</b></label>
       
    </div>
    <div class="form-group row" >
        <label for="name" class="col-sm-3 col-form-label">Id</label>
        <div class="col-sm-9" >
            <input type="text" name = "Id" class="form-control" id="id" placeholder="Enter your id" required>
           
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-sm-3 col-form-label">Acc Number</label>
        <div class="col-sm-9">
            <input type="text" name = "AccountNumber" class="form-control" id="accountNumber" placeholder="Enter your Account Number" required>
           
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-12">
            <button type="submit"  id="btn1" class="btn btn-primary" name="page" value="loginSubmit" > Login</button><br><br>
            <p>Don't you have account?<a href="http://localhost:8080/BankingApplication/MyServlett?page=addcustomer">Register here</a></p>
          
        </div>
        </div>
    </div>
  
  </div>
 
 </div>
 </form>
 </div>
 <div class="container">
 <p>
 Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam euismod malesuada auctor. Nullam elementum elit felis, in convallis nisl volutpat vitae. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Nulla facilisi. Pellentesque vel cursus elit. In euismod ex sed nisi rhoncus, sit amet ultrices sem tincidunt. Fusce pulvinar ex quis dui sollicitudin tincidunt. Etiam metus lorem, venenatis ac erat nec, dapibus semper arcu. Suspendisse auctor massa quis neque molestie, non accumsan nisl vulputate. Vestibulum sed euismod quam.

Etiam porttitor ipsum sollicitudin ipsum dictum tincidunt. Aenean ullamcorper libero id blandit posuere. Cras accumsan tincidunt justo, id lobortis massa feugiat ut. Suspendisse elit sem, ultricies id sodales tempor, auctor eu risus. Curabitur in elementum diam, vel vulputate diam. Pellentesque porttitor eget elit non finibus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae;

consectetur. Etiam vitae volutpat sapien. Suspendisse sapien odio, iaculis ut ornare eu, tincidunt ac ligula. Mauris viverra interdum quam quis consectetur. Donec aliquet ex nisi, id ullamcorper enim laoreet elementum. Nulla facilisi. Duis feugiat elit eget diam pulvinar laoreet. Proin id varius quam, a consequat nisl. Curabitur imperdiet, lorem sed placerat pulvinar, tortor nunc placerat tortor, sed fringilla sapien nulla sit amet elit. Ut porttitor, dui sed cursus consequat, nisl lacus commodo ante, ut consequat sem nibh ac mi. Nunc bibendum arcu at purus eleifend aliquet. Donec urna ante, malesuada eu mauris quis, sodales pharetra purus. Proin sed elit vitae leo auctor pellentesque. Duis nec pulvinar ante.
 </p>


    <div>
    <button> <a href="http://localhost:8080/BankingApplication/MyServlett?page=customer">Admin</a></button>
   </div>
   
    

    </div>


<%@ include file = "footer.jsp" %>




</body>
</html>


























<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  
</head>
<style>

html{
  height:100%
}
body{
  height:100%;
}
.intro{
  height:100%
}

.left{
  display : flex;
  justify-content : center;
  align-items : center;
  background-color:#3d231b;
  height : 100%;
  color : #FFF;

}
.right{
  display : flex;
  justify-content : center;
  align-items : center;
  background-color:#FFF;
  height : 100%;
  color : #3d231b;
}
@media (max-width: 1200px) { 
  .left{
    height:50%
  }
  .right{
    height:50%
  }
}
.about{
  display : flex;
  justify-content : center;
  align-items : center;
  background-color:#000;
  color : #FFF;
  height:100%
}


</style>
<body>

<%@ include file = "header.jsp" %>

<div class="container">
  <form action="MyServlett" method="POST">
    <div class="col">
    <div class="container">
    <div class="text-center">
       <div class="form-group row" >
        <label for="name" class="col-sm-12 col-form-label fw-bold mb-2 text-uppercase "><b>User Login</b></label>
       
    </div>
    <div class="form-group row" >
        <label for="name" class="col-sm-3 col-form-label">Id</label>
        <div class="col-sm-9" >
            <input type="text" name = "Id" class="form-control" id="id" placeholder="Enter your id" required>
           
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-sm-3 col-form-label">Acc Number</label>
        <div class="col-sm-9">
            <input type="text" name = "AccountNumber" class="form-control" id="accountNumber" placeholder="Enter your Account Number" required>
           
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-12">
            <button type="submit"  id="btn1" class="btn btn-primary" name="page" value="loginSubmit" > Login</button><br><br>
            <p>Don't you have account?<a href="http://localhost:8080/BankingApplication/MyServlett?page=addcustomer">Register here</a></p>
          
        </div>
        </div>
    </div>
  
  </div>
 
 </div>
 </form>
 </div>

 <div class="container">
 <p>
 Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam euismod malesuada auctor. Nullam elementum elit felis, in convallis nisl volutpat vitae. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Nulla facilisi. Pellentesque vel cursus elit. In euismod ex sed nisi rhoncus, sit amet ultrices sem tincidunt. Fusce pulvinar ex quis dui sollicitudin tincidunt. Etiam metus lorem, venenatis ac erat nec, dapibus semper arcu. Suspendisse auctor massa quis neque molestie, non accumsan nisl vulputate. Vestibulum sed euismod quam.

Etiam porttitor ipsum sollicitudin ipsum dictum tincidunt. Aenean ullamcorper libero id blandit posuere. Cras accumsan tincidunt justo, id lobortis massa feugiat ut. Suspendisse elit sem, ultricies id sodales tempor, auctor eu risus. Curabitur in elementum diam, vel vulputate diam. Pellentesque porttitor eget elit non finibus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae;

consectetur. Etiam vitae volutpat sapien. Suspendisse sapien odio, iaculis ut ornare eu, tincidunt ac ligula. Mauris viverra interdum quam quis consectetur. Donec aliquet ex nisi, id ullamcorper enim laoreet elementum. Nulla facilisi. Duis feugiat elit eget diam pulvinar laoreet. Proin id varius quam, a consequat nisl. Curabitur imperdiet, lorem sed placerat pulvinar, tortor nunc placerat tortor, sed fringilla sapien nulla sit amet elit. Ut porttitor, dui sed cursus consequat, nisl lacus commodo ante, ut consequat sem nibh ac mi. Nunc bibendum arcu at purus eleifend aliquet. Donec urna ante, malesuada eu mauris quis, sodales pharetra purus. Proin sed elit vitae leo auctor pellentesque. Duis nec pulvinar ante.
 </p>


    <div>
    <button> <a href="http://localhost:8080/BankingApplication/MyServlett?page=customer">Admin</a></button>
   </div>
   
    

    </div>


<%@ include file = "footer.jsp" %>




</body>
</html>












 --%>