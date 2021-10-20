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
<a href="http://localhost:8080/BankingApplication/MyServlett?page=home">Home</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=customer">Customer</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=account">Account</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=inActiveCustomer">InActive Customer</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=inActiveAccount">InActive Account</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=withDraw">With Draw</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=deposit">Deposit</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=history">W/D History</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=transaction">Transaction</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=transactionHistory">Transaction History</a>
<a href="http://localhost:8080/BankingApplication/MyServlett?page=allHistory">All history</a>
</div>