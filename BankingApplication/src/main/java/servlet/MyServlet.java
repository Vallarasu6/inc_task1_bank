package servlet;
import pojo_account.*;
import bank.logic.LogicLayer;
import java.util.*;
import hashMap.*;
import historyPojo.History;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.jasper.tagplugins.jstl.core.If;

import TransactionHistory.TransactionHistoryPojo;
import bank.database.DbStore;
import bank.exception.HandledException;
import bank.logic.LogicLayer;
import bank.pojo.CustomerInfo;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException; 

public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        // reading the user input
    	
        DbStore dbStore = new DbStore();
      //customer list active
        ArrayList<CustomerInfo> customerList=null;
        try {
        	customerList = dbStore.getCustomerList();
        }
        
        catch (Exception e) {
System.out.println(e);		
}
        //customer list in-active
        ArrayList<CustomerInfo> inActiveCustomerList=null;
        try {
        	inActiveCustomerList = dbStore.getInActiveCustomerList();
        	//System.out.println(inActiveCustomerList);	
        }
        
        catch (Exception e) {
System.out.println(e);		
}
        //account list active
        ArrayList<AccountInfo> accountList=null;
        try {
        	accountList = dbStore.getAccountList();
        }
        
        catch (Exception e) {
System.out.println(e);		
}
        
        //account list in-active
        ArrayList<AccountInfo> inActiveAccountList=null;
        try {
        	inActiveAccountList = dbStore.getInActiveAccountList() ;
        }
        
        catch (Exception e) {
System.out.println(e);		
}
        
        //history list
        
        ArrayList<History> histories = null;
        try {
			histories = dbStore.showHistory();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
        //transaction list
        
        ArrayList<TransactionHistoryPojo> transactionHistory = null;
        try {
			transactionHistory = dbStore.showTransactionHistory();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);	
		}
       // PrintWriter writter=response.getWriter();
    	String query = request.getParameter("page");
    	//writter.print(query);
    	//String users = request.getParameter("user");
    	if (query.equals("delete")) {
//    		RequestDispatcher rd = request.getRequestDispatcher("delete.jsp");
//        	rd.forward(request, response);
    		//int deleteCustomerId = Integer.parseInt(request.getParameter("id"));
    		LogicLayer logicLayer = new LogicLayer();
    		//request.getParameter(rowid);
//    		writter.print(query);
//    		writter.print(request.getParameter(query));
    		int one = Integer.parseInt(request.getParameter("rowid"));
    		logicLayer.deleteData(one);
    		 RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
     	    rd.forward(request, response);
		}
    	if(query.equals("deleteAccount")) {
    		LogicLayer logicLayer = new LogicLayer();
    		int one = Integer.parseInt(request.getParameter("rowid"));
    		long two = Long.parseLong(request.getParameter("accountNumber"));
    		try {
				logicLayer.deleteAccountData(one,two);
				for(int i=0;i<accountList.size();i++) {
					AccountInfo accountInfo = accountList.get(i);
					if(accountInfo.getId()==one) {
						accountList.remove(i);
						break;
					}
				}
			} catch (HandledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
     	    rd.forward(request, response);
    		}
    	
    	
    	
    	if(query.equals("Submit")) {
    		LogicLayer logicLayer = new LogicLayer();
    		AccountInfo accountInfo = new AccountInfo();
    		ArrayList<AccountInfo> accountInfos = new ArrayList<AccountInfo>();
    		int id = Integer.parseInt(request.getParameter("Id"));
    		boolean a =dbStore.checkCustomerId(id);
    		if(a==false) {
    			
    		
    		String bank = request.getParameter("bankname");
    		long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    		boolean b = dbStore.checkAccountNumber(accountNumber);
          	if(b==false) {
          	response.setContentType("text/html");
          	PrintWriter out = response.getWriter();
          	out.print("Account number Already Exist");
          }else {
    		long balance = Long.parseLong(request.getParameter("balance"));
    		accountInfo.setId(id);
    		accountInfo.setBankName(bank);
    		accountInfo.setAccountNumber(accountNumber);
    		accountInfo.setBalance(balance);
    		accountInfos.add(accountInfo);
    		logicLayer.accountInsert(accountInfos);
    		 RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
     	    rd.forward(request, response);
    		
          }
    		}else {
    			response.setContentType("text/html");
              	PrintWriter out = response.getWriter();
              	out.print("Id not Exist");
			}
    	}
    	if(query.equals("submit")) {
    		LogicLayer logicLayer = new LogicLayer();
    		CustomerInfo customerInfo = new CustomerInfo();
    		AccountInfo accountInfo = new AccountInfo();
    		ArrayList<AccountInfo> accountInfos = new ArrayList<AccountInfo>();
    		ArrayList<CustomerInfo> customerInfos = new ArrayList<CustomerInfo>();
    		//int id = Integer.parseInt(request.getParameter("Id"));
    		String name = request.getParameter("Name");
    		long mobile = Long.parseLong(request.getParameter("mobile"));
    		String bank = request.getParameter("bankname");
    		long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    		boolean b = dbStore.checkAccountNumber(accountNumber);
          	if(b==false) {
          	response.setContentType("text/html");
          	PrintWriter out = response.getWriter();
          	out.print("Account number Already Exist");
          }else {
    		long balance = Long.parseLong(request.getParameter("balance"));
    		//customerInfo.setId(id);
    		customerInfo.setName(name);
    		customerInfo.setMobileNumber(mobile);
    		//accountInfo.setId(id);
    		accountInfo.setBankName(bank);
    		accountInfo.setAccountNumber(accountNumber);
    		accountInfo.setBalance(balance);
    		accountInfos.add(accountInfo);
    		customerInfos.add(customerInfo);
    		logicLayer.addNewCustomers(customerInfos, accountInfos);
//    		request.setAttribute("output", customerList);
    	    RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
    	    rd.forward(request, response);
          }
    	}
    	
    	//transaction
    	if(query.equals("transactionSubmit")) {
    		int senderId = Integer.parseInt(request.getParameter("id"));
    		boolean a = dbStore.checkCustomerId(senderId);
    		if(a==false) {
    		long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    		boolean b = dbStore.checkAccountNumber(accountNumber);
    		if(b==false) {
    		long amount = Long.parseLong(request.getParameter("amount"));

    		int receiverId = Integer.parseInt(request.getParameter("receiverId"));
    		boolean c = dbStore.checkCustomerId(receiverId);
    		if(c==false) {
    		long receiverAccountNumber = Long.parseLong(request.getParameter("receiverAccountNumber"));
    		boolean d = dbStore.checkAccountNumber(receiverAccountNumber);
    		if(d==false) {
    		long balance = dbStore.withDraw(accountNumber);
    		if(balance>=amount){
    		balance = balance-amount;
    		try {
				dbStore.updateBalance(balance, accountNumber);
    		}catch (Exception e) {
				// TODO: handle exception
    			e.printStackTrace();
			}
    		balance = dbStore.withDraw(receiverAccountNumber);
    		balance = balance+amount;
    		try {
				dbStore.updateBalance(balance, receiverAccountNumber);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		try {
				dbStore.transaction_history(senderId,accountNumber,receiverId,receiverAccountNumber,amount);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
     	    rd.forward(request, response);
    		}else {
    			response.setContentType("text/html");
              	PrintWriter out = response.getWriter();
              	out.print("Insufficient fund");
			}
    		
    		}else {
    			response.setContentType("text/html");
              	PrintWriter out = response.getWriter();
              	out.print("No receiver account number found");
			}
    		}else {
    			response.setContentType("text/html");
              	PrintWriter out = response.getWriter();
              	out.print("No receiver id found");
			}
    		}else {
    			response.setContentType("text/html");
              	PrintWriter out = response.getWriter();
              	out.print("Account number is not found");
			}
    		}else {
    			response.setContentType("text/html");
              	PrintWriter out = response.getWriter();
              	out.print("No sender id found");
			}
    	}
    	
    	
    	if(query.equalsIgnoreCase("DeleteCustomerSubmit")) {
    		int id = Integer.parseInt(request.getParameter("Id"));
    		try {
				dbStore.deleteFromAllTables(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		request.setAttribute("output", customerList);
    	    RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
    	    rd.forward(request, response);
    		
    	}
    	if(query.equalsIgnoreCase("DeleteAccountSubmit")) {
    		long accountNumber = Long.parseLong(request.getParameter("AccountNumber"));
    		try {
				dbStore.deleteFromAccountTable(accountNumber);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		request.setAttribute("output", accountList);
        	RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
        	rd.forward(request, response);
    	}
    	if(query.equalsIgnoreCase("RenewCustomerSubmit")) {
    		int id = Integer.parseInt(request.getParameter("Id"));
    		try {
				dbStore.updateCustomerStatusToActive(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		request.setAttribute("output", customerList);
    	    RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
    	    rd.forward(request, response);
    	}
    	
    	if(query.equalsIgnoreCase("RenewAccountSubmit")) {
    		int id = Integer.parseInt(request.getParameter("Id"));
    		long acc_Number = Long.parseLong(request.getParameter("accountNumber"));
    		try {
				dbStore.updateAccountStatusToActive(id,acc_Number);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		request.setAttribute("output", accountList);
        	RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
        	rd.forward(request, response);
    	}
    	if(query.equalsIgnoreCase("withDrawSubmit")) {
    		long acc_Number = Long.parseLong(request.getParameter("accountNumber"));
    		boolean a = dbStore.checkAccountNumber(acc_Number);
    		if(a==false) {
    		long amountLong = Long.parseLong(request.getParameter("withDrawAmount"));
    		//System.out.println(amountLong+" entered amt");
    		long balance = dbStore.withDraw(acc_Number);
//    		long tempLong = balance;
    		//System.out.println(balance+" balance");
    		if(balance>=amountLong){
    		balance = balance-amountLong;
    		//System.out.println(balance+" new balance");
    		try {
				dbStore.updateBalance(balance, acc_Number);
				dbStore.history(acc_Number,"With Draw",amountLong);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
     	    rd.forward(request, response);
    		}else {
    			response.setContentType("text/html");
              	PrintWriter out = response.getWriter();
              	out.print("Insufficient fund");
			}
    		}else {
    			response.setContentType("text/html");
              	PrintWriter out = response.getWriter();
              	out.print("Account Number not found");
			}
    	}
    	if(query.equalsIgnoreCase("depositSubmit")) {
    		long acc_Number = Long.parseLong(request.getParameter("accountNumber"));
    		boolean a = dbStore.checkAccountNumber(acc_Number);
    		if(a==false) {
    		long amountLong = Long.parseLong(request.getParameter("deposit"));
    		//System.out.println(amountLong+" entered amt");
    		long balance = dbStore.withDraw(acc_Number);
    		long tempLong = balance;
    		//System.out.println(balance+" balance");
    		balance = balance+amountLong;
    		//System.out.println(balance+" new balance");
    		try {
				dbStore.updateBalance(balance, acc_Number);
				dbStore.history(acc_Number,"Deposit",amountLong);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
     	    rd.forward(request, response);
    		}else {
    			response.setContentType("text/html");
              	PrintWriter out = response.getWriter();
              	out.print("Account Number not found");
			}
    	}
    	
    	if(query.equalsIgnoreCase("home")) {
        	RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
        	rd.forward(request, response);
        }
    if(query.equalsIgnoreCase("customer")) {
    	
    request.setAttribute("output", customerList);
    RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
    rd.forward(request, response);
    }
    if(query.equalsIgnoreCase("inActiveCustomer")) {
    	
        request.setAttribute("value", inActiveCustomerList);
      
        RequestDispatcher rd = request.getRequestDispatcher("ViewDeleteCustomer.jsp");
        rd.forward(request, response);
        }
 if(query.equalsIgnoreCase("inActiveAccount")) {
    	
        request.setAttribute("output", inActiveAccountList);
      
        RequestDispatcher rd = request.getRequestDispatcher("ViewDeleteAccounts.jsp");
        rd.forward(request, response);
        }
 if(query.equalsIgnoreCase("history")) {
 	
     request.setAttribute("output", histories);
   
     RequestDispatcher rd = request.getRequestDispatcher("History.jsp");
     rd.forward(request, response);
     }
 
 if(query.equalsIgnoreCase("transactionHistory")) {
	 	
     request.setAttribute("output", transactionHistory);
   
     RequestDispatcher rd = request.getRequestDispatcher("TransactionHistory.jsp");
     rd.forward(request, response);
     }
    
    if(query.equalsIgnoreCase("account")) {
    	request.setAttribute("output", accountList);
    	RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
    	rd.forward(request, response);
    }
    if(query.equalsIgnoreCase("transaction")) {
    	RequestDispatcher rd = request.getRequestDispatcher("transaction.jsp");
    	rd.forward(request, response);
    }
    if(query.equalsIgnoreCase("addcustomer")) {
    	RequestDispatcher rd = request.getRequestDispatcher("AddCustomer.jsp");
    	rd.forward(request, response);
    }
    if(query.equalsIgnoreCase("addaccount")) {
    	RequestDispatcher rd = request.getRequestDispatcher("AddAccount.jsp");
    	rd.forward(request, response);
    }
    if(query.equalsIgnoreCase("deleteCustomer")) {
    	RequestDispatcher rd = request.getRequestDispatcher("DeleteCustomer.jsp");
    	rd.forward(request, response);
    }
    if(query.equalsIgnoreCase("deleteAccounts")) {
    	RequestDispatcher rd = request.getRequestDispatcher("DeleteAccount.jsp");
    	rd.forward(request, response);
    }
    if(query.equalsIgnoreCase("reNewCustomer")) {
    	RequestDispatcher rd = request.getRequestDispatcher("RenewOldCustomer.jsp");
    	rd.forward(request, response);
    }
    if(query.equalsIgnoreCase("reNewAccount")) {
    	RequestDispatcher rd = request.getRequestDispatcher("RenewOldAccount.jsp");
    	rd.forward(request, response);
    }
    if(query.equalsIgnoreCase("withDraw")) {
    	RequestDispatcher rd = request.getRequestDispatcher("WithDraw.jsp");
    	rd.forward(request, response);
    }
    if(query.equalsIgnoreCase("deposit")) {
    	RequestDispatcher rd = request.getRequestDispatcher("Deposit.jsp");
    	rd.forward(request, response);
    }
    if(query.equalsIgnoreCase("transactionHistory")) {
    	RequestDispatcher rd = request.getRequestDispatcher("TransactionHistory.jsp");
    	rd.forward(request, response);
    }
                }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	     
doPost(request,response);
    }
}
