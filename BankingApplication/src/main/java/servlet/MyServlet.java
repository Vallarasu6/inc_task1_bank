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
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.ws.Response;

import org.apache.catalina.valves.rewrite.Substitution.StaticElement;
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
	long depositCharges=1;
    long withdrawCharges=1;
    long transactionCharges=2;
    String applied="A";
    HashMap<Long, AccountInfo> map;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        //DbStore dbStore = new DbStore();
      LogicLayer logicLayer = new LogicLayer();
                 //get request from link   
    //applied loans list - DONE
  	
      ArrayList<AccountInfo> appliedLoanList=null;
      	try {
      		
      		appliedLoanList = logicLayer.getAppliedLoanList() ;
      
      	}
      
      catch (Exception e) {
      	
      		System.out.println(e);		

      }
    	String query = request.getParameter("page");

    	PrintWriter out = response.getWriter();
    	
    	
    	//loan Submit client
    	if(query.equals("loanSubmit")) {
    		long accountNumber = (long) request.getSession().getAttribute("acc");
    	AccountInfo accountInfo = 	map.get(accountNumber);
    	if(accountInfo.getLoanStatus().equals("NotApplied") || accountInfo.getLoanStatus().equals("Paid")) {
    		//System.out.print("loan status "+accountInfo.getLoanStatus());
    		logicLayer.loanStatusUpdate(accountNumber,"Processing");
    		//update after every process
    		map = logicLayer.clientCache(accountNumber);
    		
    			
    		out.print("100 rps - Loan applied successfully");
    	}
    	else if(accountInfo.getLoanStatus().equals("Approved")) {
    		out.print("You have already applied,You r eligible after you repay the loan");
    	}
    	
    	else {
    		out.print("You have already applied, It is under processing");
    	}
    	}
    	
    	//add new customer  - DONE
    	
    	else if(query.equals("Submit")) {
    		
    		AccountInfo accountInfo = new AccountInfo();
    		int id = (int) request.getSession().getAttribute("message");
    		//long accountNumber = (long) request.getSession().getAttribute("acc");
    		//System.out.println(id+" login id");
    		
    		String bank = request.getParameter("bankname");
    		long balance = Long.parseLong(request.getParameter("balance"));
    		accountInfo.setId(id);
    		accountInfo.setBankName(bank);
    		accountInfo.setBalance(balance);
    		logicLayer.accountInsert(accountInfo);
    		out.print("Yes!!");

    		
    	}
    	
    	//new customer details filling - DONE
    	
    	else if(query.equals("submit")) {
    		
    		CustomerInfo customerInfo = new CustomerInfo();
    		AccountInfo accountInfo = new AccountInfo();
    		
    		String name = request.getParameter("Name");
    		long mobile = Long.parseLong(request.getParameter("mobile"));
    		String bank = request.getParameter("bankname");
    		String address = request.getParameter("Address");
    				
    		long balance = Long.parseLong(request.getParameter("balance"));
    		
    		customerInfo.setName(name);
    		customerInfo.setMobileNumber(mobile);
    		customerInfo.setAddress(address);
    		accountInfo.setBankName(bank);
    		accountInfo.setBalance(balance);
    		try {
    			long accNumber = logicLayer.addNewCustomers(customerInfo, accountInfo);
    			out.print("Welcome "+name+"\nYou have registered successfully ");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    		
   		
    	}
    	
    	
    	
    	//transaction page - DONE
    	
    	else if(query.equals("transactionSubmit")) {
    		
    		long accountNumber = (long) request.getSession().getAttribute("acc");
    		long amount = Long.parseLong(request.getParameter("amount"));
    		long receiverAccountNumber = Long.parseLong(request.getParameter("receiverAccountNumber"));
    		if(accountNumber!=receiverAccountNumber) {
    		boolean d = logicLayer.checkAccountNumber(receiverAccountNumber);
    		if(d==false) {
    		long balance = logicLayer.withDraw(accountNumber);
    		long temp =0;
    		if(balance>=amount){
    			long bankCharge=1;	
    		balance = balance-amount;
    		temp=balance-transactionCharges;
    		try {
    			logicLayer.updateBalance(temp, accountNumber);
    			logicLayer.bankAccount(transactionCharges);
    			logicLayer.history(accountNumber, "Send to "+receiverAccountNumber, balance, transactionCharges);
    			
    		}catch (Exception e) {
				
    			e.printStackTrace();
			}
    		balance = logicLayer.withDraw(receiverAccountNumber);
    		balance = balance+amount;
    		temp=balance;
    		try {
    			logicLayer.updateBalance(temp, receiverAccountNumber);
    			logicLayer.history(receiverAccountNumber, "Received from "+accountNumber, balance, 0);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
//    		try {
//    			//logicLayer.transaction_history(accountNumber,receiverAccountNumber,amount);
//
//              	out.print("Succesfully!! and ur remaining balance is "+temp+" Bank charges: "+bankCharge);
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}

    		}else {
    			
              	out.print("Insufficient fund");
			}
    		
    		}else {
    			
              	out.print("No receiver account number found");
			}
    		}else {
    			out.print("Enter the valid account Number");
    		}

    	}
    	
    	//delete from both tables - DONE
    	
    	else if(query.equalsIgnoreCase("DeleteCustomerSubmit")) {
    		int id = (int) request.getSession().getAttribute("message");
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
    	
    	
//Check balance - Account
    	
    	else if(query.equalsIgnoreCase("accountBalance")) {
    		long accountNumber = (long) request.getSession().getAttribute("acc");
    		boolean b = logicLayer.checkAccountNumber(accountNumber);
    		if(b==false) {
			long balance = logicLayer.checkBalance(accountNumber);
          	out.print("Succesfully!!"
          			+ "and you Balance is : "+balance);
    		}
    		else {
              	out.print("Enter the correct Account Number! Account number not exists");
    		}

    	}
    	
    	
    	
    	
    	
    	//delete a account - DONE
    	
    	else if(query.equalsIgnoreCase("deleteAccountSubmit")) {
    		long accountNumber = (long) request.getSession().getAttribute("acc");
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
    		long acc_Number = (long) request.getSession().getAttribute("acc");
    		boolean a = logicLayer.checkAccountNumber(acc_Number);
    		long updatedBalance=0;
    		if(a==false) {
    		long amountLong = Long.parseLong(request.getParameter("withDrawAmount"));
    		
    		long balance = logicLayer.withDraw(acc_Number);

    		if(balance>=amountLong){
    		balance = balance-amountLong;
    		updatedBalance = balance-withdrawCharges;
    		try {
    			logicLayer.updateBalance(updatedBalance, acc_Number);
    			logicLayer.bankAccount(withdrawCharges);
    			logicLayer.history(acc_Number,"With Draw",amountLong,withdrawCharges);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

          	out.print("Successfully!!! and ur remaining balance is "+updatedBalance+" Bank charges: "+withdrawCharges+" Rupees");
    		}else {
    			
              	out.print(balance);
			}
    		}else {
    			
              	out.print("Account Number not found");
			}
    	}
    	
    	//deposit page - DONE
    	
    	else if(query.equalsIgnoreCase("depositSubmit")) {
    		long acc_Number = (long) request.getSession().getAttribute("acc");
    		System.out.print(acc_Number+ " acc nuber");
    		boolean a = logicLayer.checkAccountNumber(acc_Number);
    		long updatedBalance=0;
    		if(a==false) {
    		long amountLong = Long.parseLong(request.getParameter("deposit"));
    		
    		long balance = logicLayer.withDraw(acc_Number);
    		//logicLayer.bankCharges(acc_Number,"Deposit",bankCharge);
    		
    		balance = balance+amountLong;
    		updatedBalance = balance-withdrawCharges;
    		try {
    			logicLayer.updateBalance(updatedBalance, acc_Number);
    			logicLayer.bankAccount(depositCharges);
    			logicLayer.history(acc_Number,"Deposit",amountLong,withdrawCharges);
			} catch (SQLException e) {
				e.printStackTrace();
			}
          	out.print("Successfully!!! and ur new remaining balance is "+updatedBalance+" Bank charges: "+withdrawCharges+" Rupees");
    		}else {
              	out.print("Account Number not found");
			}
    	}
    	

    	//all history submit
    	else if(query.equalsIgnoreCase("allHistory")) {
    		long acc_Number = (long) request.getSession().getAttribute("acc");
    		boolean a = logicLayer.checkAccountNumber(acc_Number);
    		if(a==false) {
    			ArrayList<History> list = logicLayer.allHistory(acc_Number);
    			
    			JSONArray values= new JSONArray();
    			for(int i=0;i<list.size();i++) {
    				History allHistory = list.get(i);
    				JSONObject data = new JSONObject(allHistory);
    				values.put(data);
    				
    			}
    			
    			out.print(values.toString());
            
    			
    		}else {
    			out.print("Account number not exist!!");
    		}
    	}
    	
 
    	 //Login
    	
    	else if(query.equalsIgnoreCase("loginSubmit")) {
        	
    		int id = Integer.parseInt(request.getParameter("Id"));
    		
    		long accountNumber = Long.parseLong(request.getParameter("AccountNumber"));
    		int n = logicLayer.checkLogin(id,accountNumber);
    		if(n==1) {
    			request.getSession().setAttribute("message", id);
    			request.getSession().setAttribute("acc", accountNumber);
    			map = logicLayer.clientCache(accountNumber);
    		RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
    		}
    		else {
    			request.setAttribute("message", "wrong");
    			
    			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
                rd.forward(request, response);
                
    		}
    		
        }
    	//mobile number update submit
	else if(query.equalsIgnoreCase("mobileUpdateSubmit")) {
        
    		long newMobile = Long.parseLong(request.getParameter("mobileUpdate"));
    		int id = (int) request.getSession().getAttribute("message");
    		
    			logicLayer.changeMobile(newMobile,id);
    			
    			out.print("changed successfully!!");
            
    			
    		
        }
    	
    	
    	
    	//address update submit
    	else if(query.equalsIgnoreCase("addressUpdateSubmit")) {
            
    		String newAddress = request.getParameter("addressUpdate");
    		int id = (int) request.getSession().getAttribute("message");
    		
    			logicLayer.changeAddress(newAddress, id);
    			
    			out.print("changed successfully!!");
    			}
    	
    	//load paid
    	else if (query.equalsIgnoreCase("loanPaidSubmit")) {
    		long accountNumber = (long) request.getSession().getAttribute("acc");
    		AccountInfo accountInfo = map.get(accountNumber);
    		if(accountInfo.getLoanStatus().equals("Approved")) {
    			
    		long loanAmount=100;
    		String one="loanAccount";
    		
    		long balance = logicLayer.withDraw(accountNumber);

    		if(balance>=loanAmount){
    		balance = balance-loanAmount;
    		//updatedBalance = balance-withdrawCharges;
    		try {
    			logicLayer.updateBalance(balance, accountNumber);
    			
    			logicLayer.history(accountNumber,"Loan Paid",loanAmount,0);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
    		logicLayer.loanStatusUpdate(accountNumber,"Paid");
        	//load arraylist again for new updated list
        	appliedLoanList = logicLayer.getAppliedLoanList() ;
        	long balanceBank = logicLayer.bankAmount(one);
        	balanceBank = balanceBank+loanAmount;
        	logicLayer.updateBankAmount(balanceBank,one);
        	map = logicLayer.clientCache(accountNumber);
          	out.print("Successfully!!!Loan paid");
    		}else {
    			
              	out.print("Insufficient fund");
			}
    			
    		}else {
    			out.print("You can't pay the loan now");
    		}
		}
    	
//    	
//    	//mobile Recharge Submit
//else if(query.equalsIgnoreCase("mobileRechargeSubmit")) {
//            
//    		long mobile =Long.parseLong(request.getParameter("Mobile"));
//    		long amount =Long.parseLong(request.getParameter("Amount"));
//    		
//    			out.print("changed successfully!!");
//    			}

    	
    	
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
            		
      			//transactionHistory = logicLayer.showTransactionHistory();
      		
            	} 
            	catch (Exception e) {

            		System.out.println(e);	
      		
            	}
            	
                	//approved loans list - DONE
                	
                    ArrayList<AccountInfo> approvedLoanList=null;
                    	try {
                    		
                    		approvedLoanList = logicLayer.getApprovedLoanList() ;
                    
                    	}
                    
                    catch (Exception e) {
                    	
                    		System.out.println(e);		

                    }
                    	 ArrayList<AccountInfo> appliedLoanList=null;
                       	try {
                       		
                       		appliedLoanList = logicLayer.getAppliedLoanList() ;
                       
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
        	
        	
  //client Loan - DONE
            
            
        	else if(query.equalsIgnoreCase("clientLoan")) {
        	RequestDispatcher rd = request.getRequestDispatcher("ClientLoan.jsp");
        	rd.forward(request, response);
        }
        	
        	//show approved loans
        	else if(query.equalsIgnoreCase("approvedLoans")) {
        		
        		request.setAttribute("output", approvedLoanList);
            	RequestDispatcher rd = request.getRequestDispatcher("ShowApprovedLoans.jsp");
            	
            	rd.forward(request, response);
        	}
        	
        	//loan paid page
        	
        	else if(query.equalsIgnoreCase("loanPaidSubmit")) {
        		RequestDispatcher rd = request.getRequestDispatcher("PayLoan.jsp");
            	rd.forward(request, response);
        	}
        	
//show applied loans
        	else if(query.equalsIgnoreCase("appliedLoans")) {
        		
        		request.setAttribute("output", appliedLoanList);
            	RequestDispatcher rd = request.getRequestDispatcher("ShowAppliedLoans.jsp");
            	
            	rd.forward(request, response);
        	}
     //approve loan   	
        	else if(query.equals("loanApprove")) {
        		String one="loanAccount";
        		long loanAmount=100;
        	long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
        	logicLayer.loanStatusUpdate(accountNumber,"Approved");
        	//load arraylist again for new updated list
        	appliedLoanList = logicLayer.getAppliedLoanList() ;
        	long balanceBank = logicLayer.bankAmount(one);
        	balanceBank = balanceBank-loanAmount;
        	logicLayer.updateBankAmount(balanceBank,one);
        	long balance = logicLayer.withDraw(accountNumber);
    		balance+=100;
    			try {
					logicLayer.updateBalance(balance, accountNumber);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			logicLayer.history(accountNumber,"Loan debited",loanAmount,0);
        	request.setAttribute("output", appliedLoanList);
        	RequestDispatcher rd = request.getRequestDispatcher("ShowAppliedLoans.jsp");
        	
        	rd.forward(request, response);
        	}
        	
        	
   
    }
}
