  <style type="text/css">
    .sidenav {
  height: 100%;
  width: 160px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
  padding-top: 20px;
  margin-top:120px;
}

.sidenav a {
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
}

.sidenav a:hover {
  color: #f1f1f1;
  background-color: red;
  
}
.active{ background-color: red;}
.main {
  margin-left: 160px; 
  font-size: 20px; 
  padding: 0px 10px;
}

    </style>
<div class="sidenav">
<a class = "active" href="http://localhost:8080/BankingApplication/MyServlett?page=home">Home</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=customer">Customer</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=account">Account</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=inActiveCustomer">InActive Customer</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=inActiveAccount">InActive Account</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=withDraw">With Draw</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=deposit">Deposit</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=history">W/D History</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=transaction">Transaction</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=transactionHistory">Transaction History</a>
</div>