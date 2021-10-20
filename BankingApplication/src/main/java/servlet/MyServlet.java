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
import org.json.JSONArray;
import org.json.JSONObject;

import TransactionHistory.TransactionHistoryPojo;
import allHistoryPojo.AllHistory;
import bank.database.DbStore;
import bank.exception.HandledException;
import bank.logic.LogicLayer;
import bank.pojo.CustomerInfo;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException; 

public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        //DbStore dbStore = new DbStore();
      LogicLayer logicLayer = new LogicLayer();
    //customer list active - done
      
//      ArrayList<CustomerInfo> customerList=null;
//      	try {
//      		
//      		customerList = logicLayer.getCustomerList();
//      		
//      	}
//      
//      	catch (Exception e) {
//      		
//      		System.out.println(e);		
//      		
//      	}
//      	
//      //customer list in-active - DONE
//      	
//      ArrayList<CustomerInfo> inActiveCustomerList=null;
//      	try {
//      	
//      		inActiveCustomerList = logicLayer.getInActiveCustomerList();
//      		
//      	}
//      
//      	catch (Exception e) {
//      		
//      		System.out.println(e);		
//
//      	}
//      	
//      //account list active - DONE
//      
//      ArrayList<AccountInfo> accountList=null;
//      	try {
//      		
//      		accountList = logicLayer.getAccountList();
//      
//      	}
//      
//      catch (Exception e) {
//      	
//      		System.out.println(e);		
//      
//      }
//      
//      //account list in-active - DONE
//      	
//      ArrayList<AccountInfo> inActiveAccountList=null;
//      	try {
//      		
//      		inActiveAccountList = logicLayer.getInActiveAccountList() ;
//      
//      	}
//      
//      catch (Exception e) {
//      	
//      		System.out.println(e);		
//
//      }
//      
//      //history list
//      
//      ArrayList<History> histories = null;
//      	try {
//      		
//      		histories = logicLayer.showHistory();
//		
//      	}
//      	catch (Exception e) {
//
//      		System.out.println(e);
//		
//      	}
//      //transaction list
//      
//      ArrayList<TransactionHistoryPojo> transactionHistory = null;
//      	try {
//      		
//			transactionHistory = logicLayer.showTransactionHistory();
//		
//      	} 
//      	catch (Exception e) {
//
//      		System.out.println(e);	
//		
//      	}
//   
             //get request from link   
       
    	String query = request.getParameter("page");

    	PrintWriter out = response.getWriter();
    	
    	//add new customer  - DONE
    	
    	if(query.equals("Submit")) {
    		//LogicLayer logicLayer = new LogicLayer();
    		AccountInfo accountInfo = new AccountInfo();
    		ArrayList<AccountInfo> accountInfos = new ArrayList<AccountInfo>();
    		int id = Integer.parseInt(request.getParameter("Id"));
    		boolean a =logicLayer.checkCustomerId(id);
    		if(a==false) {
    		String bank = request.getParameter("bankname");
    		long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    		boolean b = logicLayer.checkAccountNumber(accountNumber);
          	if(b==false) {
          	
          	
          	out.print("Account number Already Exist");
          }else {
    		long balance = Long.parseLong(request.getParameter("balance"));
    		accountInfo.setId(id);
    		accountInfo.setBankName(bank);
    		accountInfo.setAccountNumber(accountNumber);
    		accountInfo.setBalance(balance);
    		accountInfos.add(accountInfo);
    		logicLayer.accountInsert(accountInfos);
    		//call deposit method
    		try {
				logicLayer.history(accountNumber,"Deposit",balance);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          	out.print("Successfully!!");
    		
          }
    		}else {

              	out.print("Id not Exist");
			}
    	}
    	
    	//new customer details filling - DONE
    	
    	else if(query.equals("submit")) {
    		//LogicLayer logicLayer = new LogicLayer();
    		CustomerInfo customerInfo = new CustomerInfo();
    		AccountInfo accountInfo = new AccountInfo();
    		ArrayList<AccountInfo> accountInfos = new ArrayList<AccountInfo>();
    		ArrayList<CustomerInfo> customerInfos = new ArrayList<CustomerInfo>();
    		String name = request.getParameter("Name");
    		long mobile = Long.parseLong(request.getParameter("mobile"));
    		String bank = request.getParameter("bankname");
    		long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    		
    		boolean b = logicLayer.checkAccountNumber(accountNumber);

          		
    			if(b==false) {
          	
    				out.print("Account number Already Exist");
    			}
    			else {
    				
    		long balance = Long.parseLong(request.getParameter("balance"));
    		
    		customerInfo.setName(name);
    		customerInfo.setMobileNumber(mobile);
    		accountInfo.setBankName(bank);
    		accountInfo.setAccountNumber(accountNumber);
    		accountInfo.setBalance(balance);
    		accountInfos.add(accountInfo);
    		customerInfos.add(customerInfo);
    		logicLayer.addNewCustomers(customerInfos, accountInfos);
    		out.print("Successfully Added");
          }
    	}
    	
    	//transaction page - DONE
    	
    	else if(query.equals("transactionSubmit")) {
    		int senderId = Integer.parseInt(request.getParameter("id"));
    		boolean a = logicLayer.checkCustomerId(senderId);
    		if(a==false) {
    		long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
    		boolean b =logicLayer.checkAccountNumber(accountNumber);
    		if(b==false) {
    		long amount = Long.parseLong(request.getParameter("amount"));

    		int receiverId = Integer.parseInt(request.getParameter("receiverId"));
    		boolean c = logicLayer.checkCustomerId(receiverId);
    		if(c==false) {
    		long receiverAccountNumber = Long.parseLong(request.getParameter("receiverAccountNumber"));
    		boolean d = logicLayer.checkAccountNumber(receiverAccountNumber);
    		if(d==false) {
    		long balance = logicLayer.withDraw(accountNumber);
    		long temp =0;
    		if(balance>=amount){
    			long bankCharge=2;	
    		balance = balance-amount;
    		temp=balance-bankCharge;
    		try {
    			logicLayer.updateBalance(temp, accountNumber);
    			logicLayer.bankCharges(accountNumber,"Transact to "+receiverAccountNumber, bankCharge);
    		}catch (Exception e) {
				
    			e.printStackTrace();
			}
    		balance = logicLayer.withDraw(receiverAccountNumber);
    		balance = balance+amount;
    		try {
    			logicLayer.updateBalance(balance, receiverAccountNumber);
    			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
    		try {
    			logicLayer.transaction_history(senderId,accountNumber,receiverId,receiverAccountNumber,amount);

              	out.print("Succesfully!! and ur remaining balance is "+temp+" Bank charges: "+bankCharge);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

    		}else {
    			
              	out.print("Insufficient fund");
			}
    		
    		}else {
    			
              	out.print("No receiver account number found");
			}
    		}else {
    			
              	out.print("No receiver id found");
			}
    		}else {
    			
              	out.print("Account number is not found");
			}
    		}else {
    			
              	out.print("No sender id found");
			}
    	}
    	
    	//delete from both tables - DONE
    	
    	else if(query.equalsIgnoreCase("DeleteCustomerSubmit")) {
    		int id = Integer.parseInt(request.getParameter("Id"));
    		boolean b = logicLayer.checkCustomerId(id);
    		if(b==false) {
    			
    		
    		try {
    			logicLayer.deleteFromAllTables(id);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
          	out.print("Succesfully!!");
    		}else {
              	out.print("Enter the correct id! Id not exists");
    		}

    		
    	}
    	
    	//delete a account - DONE
    	
    	else if(query.equalsIgnoreCase("DeleteAccountSubmit")) {
    		long accountNumber = Long.parseLong(request.getParameter("AccountNumber"));
    		boolean b = logicLayer.checkAccountNumber(accountNumber);
    		if(b==false) {
    		try {
    			logicLayer.deleteFromAccountTable(accountNumber);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
          	out.print("Succesfully!!");
    		}else {
              	out.print("Enter the correct Account Number! Account number not exists");
    		}

    	}
    	
    	//renew a customer - DONE
    	
    	else if(query.equalsIgnoreCase("RenewCustomerSubmit")) {
    		int id = Integer.parseInt(request.getParameter("Id"));
    		boolean b = logicLayer.checkCustomerId(id);
    		if(b==false) {
    		try {
    			logicLayer.updateCustomerStatusToActive(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          	out.print("Succesfully!!");
    		}else {
              	out.print("Enter the correct id! Id not exists");
    		}
    		
			
    	}
    	
    	// activate account - DONE
    	
    	else if(query.equalsIgnoreCase("RenewAccountSubmit")) {
    		int id = Integer.parseInt(request.getParameter("Id"));
    		long acc_Number = Long.parseLong(request.getParameter("accountNumber"));
    		boolean b = logicLayer.checkCustomerId(id);
    		if(b==false) {
    			boolean bb =logicLayer.checkAccountNumber(acc_Number);
    			if(bb==false) {
    		try {
    			logicLayer.updateAccountStatusToActive(id,acc_Number);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          	out.print("Succesfully!!");
    			}
    		else {
                  	out.print("Enter the correct Account Number! Account number not exists");
				}
    		}
    		else {
              	out.print("Enter the correct id! Id not exists");
    		}

    	}
    	
    	//withdraw page - DONE
    	
    	else if(query.equalsIgnoreCase("withDrawSubmit")) {
    		long acc_Number = Long.parseLong(request.getParameter("accountNumber"));
    		boolean a = logicLayer.checkAccountNumber(acc_Number);
    		long bankCharge=1,updatedBalance=0;
    		if(a==false) {
    		long amountLong = Long.parseLong(request.getParameter("withDrawAmount"));
    		
    		long balance = logicLayer.withDraw(acc_Number);

    		if(balance>=amountLong){
    		balance = balance-amountLong;
    		updatedBalance = balance-bankCharge;
    		try {
    			logicLayer.updateBalance(updatedBalance, acc_Number);
    			logicLayer.history(acc_Number,"With Draw",amountLong);
    			logicLayer.bankCharges(acc_Number,"With Draw",bankCharge);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

          	out.print("Successfully!!! and ur remaining balance is "+updatedBalance+" Bank charges: "+bankCharge+" Rupees");
    		}else {
    			
              	out.print(balance);
			}
    		}else {
    			
              	out.print("Account Number not found");
			}
    	}
    	
    	//deposit page - DONE
    	
    	else if(query.equalsIgnoreCase("depositSubmit")) {
    		long acc_Number = Long.parseLong(request.getParameter("accountNumber"));
    		boolean a = logicLayer.checkAccountNumber(acc_Number);
    		long bankCharge=1,updatedBalance=0;
    		if(a==false) {
    		long amountLong = Long.parseLong(request.getParameter("deposit"));
    		long balance = logicLayer.withDraw(acc_Number);
    		logicLayer.bankCharges(acc_Number,"Deposit",bankCharge);
    		//long tempLong = balance;
    		
    		balance = balance+amountLong;
    		updatedBalance = balance-bankCharge;
    		try {
    			logicLayer.updateBalance(updatedBalance, acc_Number);
    			logicLayer.history(acc_Number,"Deposit",amountLong);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

          	out.print("Successfully!!! and ur new remaining balance is "+updatedBalance+" Bank charges: "+bankCharge+" Rupees");
    		}else {

              	out.print("Account Number not found");
			}
    	}
    	
//    	//Home - DONE
//    	
//    	else if(query.equalsIgnoreCase("home")) {
//        	RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
//        	rd.forward(request, response);
//        }
    	
//    	//show customer list - active - DONE
//    	
//    	else if(query.equalsIgnoreCase("customer")) {
//    	
//    request.setAttribute("output", customerList);
//    RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
//    rd.forward(request, response);
//    }
    
//    //show inactive customer - DONE
//    
//    	else if(query.equalsIgnoreCase("inActiveCustomer")) {
//    	
//        request.setAttribute("value", inActiveCustomerList);
//      
//        RequestDispatcher rd = request.getRequestDispatcher("ViewDeleteCustomer.jsp");
//        rd.forward(request, response);
//        }
//    
//  //show inactive Account - DONE
//    
//    	else if(query.equalsIgnoreCase("inActiveAccount")) {
//    	
//        request.setAttribute("output", inActiveAccountList);
//      
//        RequestDispatcher rd = request.getRequestDispatcher("ViewDeleteAccounts.jsp");
//        rd.forward(request, response);
//        }
// 
// //show withdraw deposit histories - DONE
// 
//    	else if(query.equalsIgnoreCase("history")) {
// 	
//     request.setAttribute("output", histories);
//   
//     RequestDispatcher rd = request.getRequestDispatcher("History.jsp");
//     rd.forward(request, response);
//     }
// 
// //show transaction list - DONE
// 
// 
//    	else if(query.equalsIgnoreCase("transactionHistory")) {
//	 	
//     request.setAttribute("output", transactionHistory);
//   
//     RequestDispatcher rd = request.getRequestDispatcher("TransactionHistory.jsp");
//     rd.forward(request, response);
//     }
// 
// //Show account list - active - DONE
// 
//    	else if(query.equalsIgnoreCase("account")) {
//    	request.setAttribute("output", accountList);
//    	RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
//    	rd.forward(request, response);
//    }
//    
//    //show transaction page-DONE
//    
//    	else if(query.equalsIgnoreCase("transaction")) {
//    	RequestDispatcher rd = request.getRequestDispatcher("transaction.jsp");
//    	rd.forward(request, response);
//    }
    
//    //add a new customer page-DONE
//    
//    	else if(query.equalsIgnoreCase("addcustomer")) {
//    	RequestDispatcher rd = request.getRequestDispatcher("AddCustomer.jsp");
//    	rd.forward(request, response);
//    }
    
//    //add new account for existing customer - DONE
//    
//    	else if(query.equalsIgnoreCase("addaccount")) {
//    	RequestDispatcher rd = request.getRequestDispatcher("AddAccount.jsp");
//    	rd.forward(request, response);
//    }
    
//    //delete page - DONE
//    
//    	else if(query.equalsIgnoreCase("deleteCustomer")) {
//    	RequestDispatcher rd = request.getRequestDispatcher("DeleteCustomer.jsp");
//    	rd.forward(request, response);
//    }
//    
//    //delete account page - DONE
//    
//    
//    	else if(query.equalsIgnoreCase("deleteAccounts")) {
//    	RequestDispatcher rd = request.getRequestDispatcher("DeleteAccount.jsp");
//    	rd.forward(request, response);
//    }
//    
    	
//    //activate a customer - DONE
//    
//    	else if(query.equalsIgnoreCase("reNewCustomer")) {
//    	RequestDispatcher rd = request.getRequestDispatcher("RenewOldCustomer.jsp");
//    	rd.forward(request, response);
//    }
    
//    //Activate an account - DONE
//    
//    	else if(query.equalsIgnoreCase("reNewAccount")) {
//    	RequestDispatcher rd = request.getRequestDispatcher("RenewOldAccount.jsp");
//    	rd.forward(request, response);
//    }
    
//    //withdraw page - DONE
//    
//    	else if(query.equalsIgnoreCase("withDraw")) {
//    	RequestDispatcher rd = request.getRequestDispatcher("WithDraw.jsp");
//    	rd.forward(request, response);
//    }
//    
//    //deposit page - DONE
//    
//    	else if(query.equalsIgnoreCase("deposit")) {
//    	RequestDispatcher rd = request.getRequestDispatcher("Deposit.jsp");
//    	rd.forward(request, response);
//    }
    
//    //transaction history page - DONE
//    
//    	else if(query.equalsIgnoreCase("transactionHistory")) {
//    	RequestDispatcher rd = request.getRequestDispatcher("TransactionHistory.jsp");
//    	rd.forward(request, response);
//    }
    	
//    //all history
//    	else if(query.equalsIgnoreCase("allHistory")) {
//    		RequestDispatcher rd = request.getRequestDispatcher("AllHistory.jsp");
//        	rd.forward(request, response);
//    	}
    	
    	//all history submit
    	else if(query.equalsIgnoreCase("allHistorySubmit")) {
    		long acc_Number = Long.parseLong(request.getParameter("accountNumber"));
    		boolean a = logicLayer.checkAccountNumber(acc_Number);
    		if(a==false) {
    			ArrayList<AllHistory> list = logicLayer.allHistory(acc_Number);
    			
    			//out.print(list);
    			JSONArray values= new JSONArray();
    			for(int i=0;i<list.size();i++) {
    				AllHistory allHistory = list.get(i);
    				JSONObject data = new JSONObject(allHistory);
    				values.put(data);
    				
    			}
    			
    			out.print(values.toString());
            
    			
    		}else {
    			out.print("Account number not exist!!");
    		}
    	}
    	
    	

    	
    	
                }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	     
    	String query = request.getParameter("page");
    	LogicLayer logicLayer = new LogicLayer();
        //customer list active - done
          
          ArrayList<CustomerInfo> customerList=null;
          	try {
          		
          		customerList = logicLayer.getCustomerList();
          		
          	}
          
          	catch (Exception e) {
          		
          		System.out.println(e);		
          		
          	}
            //customer list in-active - DONE
          	
            ArrayList<CustomerInfo> inActiveCustomerList=null;
            	try {
            	
            		inActiveCustomerList = logicLayer.getInActiveCustomerList();
            		
            	}
            
            	catch (Exception e) {
            		
            		System.out.println(e);		

            	}
            	
            //account list active - DONE
            
            ArrayList<AccountInfo> accountList=null;
            	try {
            		
            		accountList = logicLayer.getAccountList();
            
            	}
            
            catch (Exception e) {
            	
            		System.out.println(e);		
            
            }
            
            //account list in-active - DONE
            	
            ArrayList<AccountInfo> inActiveAccountList=null;
            	try {
            		
            		inActiveAccountList = logicLayer.getInActiveAccountList() ;
            
            	}
            
            catch (Exception e) {
            	
            		System.out.println(e);		

            }
            
            //history list
            
            ArrayList<History> histories = null;
            	try {
            		
            		histories = logicLayer.showHistory();
      		
            	}
            	catch (Exception e) {

            		System.out.println(e);
      		
            	}
            //transaction list
            
            ArrayList<TransactionHistoryPojo> transactionHistory = null;
            	try {
            		
      			transactionHistory = logicLayer.showTransactionHistory();
      		
            	} 
            	catch (Exception e) {

            		System.out.println(e);	
      		
            	}
         

    	
    	
    	
          //Home - DONE
        	
        	if(query.equalsIgnoreCase("home")) {
            	RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
            	rd.forward(request, response);
            }
        	//show customer list - active - DONE
        	
        	else if(query.equalsIgnoreCase("customer")) {
        	
        request.setAttribute("output", customerList);
        RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
        rd.forward(request, response);
        }
        
            //show inactive customer - DONE
            
        	else if(query.equalsIgnoreCase("inActiveCustomer")) {
        	
            request.setAttribute("value", inActiveCustomerList);
          
            RequestDispatcher rd = request.getRequestDispatcher("ViewDeleteCustomer.jsp");
            rd.forward(request, response);
            }
        
      //show inactive Account - DONE
        
        	else if(query.equalsIgnoreCase("inActiveAccount")) {
        	
            request.setAttribute("output", inActiveAccountList);
          
            RequestDispatcher rd = request.getRequestDispatcher("ViewDeleteAccounts.jsp");
            rd.forward(request, response);
            }
     
     //show withdraw deposit histories - DONE
     
        	else if(query.equalsIgnoreCase("history")) {
     	
         request.setAttribute("output", histories);
       
         RequestDispatcher rd = request.getRequestDispatcher("History.jsp");
         rd.forward(request, response);
         }
     
     //show transaction list - DONE
     
     
        	else if(query.equalsIgnoreCase("transactionHistory")) {
    	 	
         request.setAttribute("output", transactionHistory);
       
         RequestDispatcher rd = request.getRequestDispatcher("TransactionHistory.jsp");
         rd.forward(request, response);
         }
     
     //Show account list - active - DONE
     
        	else if(query.equalsIgnoreCase("account")) {
        	request.setAttribute("output", accountList);
        	RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
        	rd.forward(request, response);
        }
            //add a new customer page-DONE
            
        	else if(query.equalsIgnoreCase("addcustomer")) {
        	RequestDispatcher rd = request.getRequestDispatcher("AddCustomer.jsp");
        	rd.forward(request, response);
        }
        
        	
        	  //add new account for existing customer - DONE
            
        	else if(query.equalsIgnoreCase("addaccount")) {
        	RequestDispatcher rd = request.getRequestDispatcher("AddAccount.jsp");
        	rd.forward(request, response);
        }
        	
        	
            //delete page - DONE
            
        	else if(query.equalsIgnoreCase("deleteCustomer")) {
        	RequestDispatcher rd = request.getRequestDispatcher("DeleteCustomer.jsp");
        	rd.forward(request, response);
        }	
        	
        	
            //delete account page - DONE
            
            
        	else if(query.equalsIgnoreCase("deleteAccounts")) {
        	RequestDispatcher rd = request.getRequestDispatcher("DeleteAccount.jsp");
        	rd.forward(request, response);
        }
        
            //activate a customer - DONE
            
        	else if(query.equalsIgnoreCase("reNewCustomer")) {
        	RequestDispatcher rd = request.getRequestDispatcher("RenewOldCustomer.jsp");
        	rd.forward(request, response);
        }
            //Activate an account - DONE
            
        	else if(query.equalsIgnoreCase("reNewAccount")) {
        	RequestDispatcher rd = request.getRequestDispatcher("RenewOldAccount.jsp");
        	rd.forward(request, response);
        }	
        	
        	  //withdraw page - DONE
            
        	else if(query.equalsIgnoreCase("withDraw")) {
        	RequestDispatcher rd = request.getRequestDispatcher("WithDraw.jsp");
        	rd.forward(request, response);
        }
        
        //deposit page - DONE
        
        	else if(query.equalsIgnoreCase("deposit")) {
        	RequestDispatcher rd = request.getRequestDispatcher("Deposit.jsp");
        	rd.forward(request, response);
        }
    	
            //show transaction page-DONE
            
        	else if(query.equalsIgnoreCase("transaction")) {
        	RequestDispatcher rd = request.getRequestDispatcher("transaction.jsp");
        	rd.forward(request, response);
        }
        	
        	  //transaction history page - DONE
            
        	else if(query.equalsIgnoreCase("transactionHistory")) {
        	RequestDispatcher rd = request.getRequestDispatcher("TransactionHistory.jsp");
        	rd.forward(request, response);
        }
        	  //all history
        	else if(query.equalsIgnoreCase("allHistory")) {
        		RequestDispatcher rd = request.getRequestDispatcher("AllHistory.jsp");
            	rd.forward(request, response);
        	}
        	
        	
//doPost(request,response);


   
    }
}
