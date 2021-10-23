package bank.logic;
import bank.database.DbStore;
import bank.exception.HandledException;
import bank.interfaces.InterfaceCommon;
import bank.pojo.CustomerInfo;
import hashMap.HashMapHandler;
import historyPojo.History;
import pojo_account.AccountInfo;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import java.util.Properties;

import javax.swing.plaf.synth.SynthFormattedTextFieldUI;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

import TransactionHistory.TransactionHistoryPojo;
import allHistoryPojo.AllHistory;

public class LogicLayer {
	
	  private InterfaceCommon db;
	    FileReader reader;
	    long depositCharges=1;
	    long withdrawCharges=1;
	    long transactionCharges=2;

	    public LogicLayer() {
	        try {
	            reader = new FileReader("/home/inc15/eclipse-workspace/BankingApplication/src/main/java/file.properties");
	            Properties properties=new Properties();
	            properties.load(reader);
	            String data = properties.getProperty("dbConnection");
	            db = (InterfaceCommon)Class.forName(data).newInstance();

	        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    }
//	
//	DbStore db = null;
//	
//   public LogicLayer() {
//	   
//	  db = new DbStore();
//	// TODO Auto-generated constructor stub
//}
 
	    //change mobile number
	    public void changeMobile(long mobile, int id) {
			db.changeMobile(mobile,id);
		}

	    //change address
	    public void changeAddress(String address, int id) {
			db.changeAddress(address,id);
		}
	    
	    
	    //check balance
	    public long checkBalance(long accountNumber) {
	    	long balance = db.checkBalance(accountNumber);
	    	return balance;
			
		}
	    
	    
//check login
	    public int checkLogin(int id,long accountNumber) {
	    	ArrayList<Long> acc_Number = db.checkLogin(id);

	    	int j=0;
	    	for(int i=0;i<acc_Number.size();i++) {
	    		if(acc_Number.get(i)==accountNumber) {
	    			j=1;
	    			break;
	    		}
	    	
	    	}
	    	if(j==1) {
	    		return 1;
	    	}else {
	    		return 0;
	    	}
			
		}
	//show history
	    
	    
	    public ArrayList<History> showHistory() {
	    	return db.showHistory();
	    }
	    
//	//transaction
//	  public void transaction_history(long sender_accountNumber,long receiver_accountNumber,long balance) throws SQLException {
//	  db.transaction_history(sender_accountNumber, receiver_accountNumber, balance);
//	  }
   
 //check account number exist
   public boolean checkAccountNumber(long acc_num) {
	   return db.checkAccountNumber(acc_num);
   }
 //check customerid exist
   public boolean checkCustomerId(int id) {
	  // System.out.print(id+" ll id");
	   return db.checkCustomerId(id);
   }

 //show customerList - Active
   public ArrayList<CustomerInfo> getCustomerList() {
	   return db.getCustomerList();
   }

 //show customerList - InActive
   public ArrayList<CustomerInfo> getInActiveCustomerList() {
	   return db.getInActiveCustomerList();
   }
   
 //show accountList - Inactive
   public ArrayList<AccountInfo> getInActiveAccountList() {
	   return db.getInActiveAccountList();
   }
   
 //show Accounts
   public ArrayList<AccountInfo> getAccountList() {
	   return db.getAccountList();
   }
   
 //Delete both tables.

   public void deleteFromAllTables(int id) throws SQLException {
	   db.deleteFromAllTables(id);
   }
   
 //Delete Account

   public void deleteFromAccountTable(long accountNumber) throws SQLException {
	   db.deleteFromAccountTable(accountNumber);
   }
   
 //Update customer status to active
   public void updateCustomerStatusToActive(int id) throws SQLException{
	   db.updateCustomerStatusToActive(id);
   }
   
 //Update account status to active
   public void updateAccountStatusToActive(int id,long accountNumber) throws SQLException{
	   db.updateAccountStatusToActive(id, accountNumber);
   }
 //get balance for W/D
   public long withDraw(long accountNumber) {
	   return db.withDraw(accountNumber);
   }
 //update balance

   public void updateBalance(long balance,long acc_number) throws SQLException {
	   db.updateBalance(balance, acc_number);
	   //history(acc_number, "WithDraw", balance+1);
   }
   
// W/D  History



//public void history(long accountNumber,String process,long balance) throws SQLException {
//	db.history(accountNumber, process, balance);
//}

//public void bankCharges(long accountNumber, String process, long balance) {
//
//	db.bankCharges(accountNumber,process,balance);
//}


//

////show transaction history
//public ArrayList<TransactionHistoryPojo> showTransactionHistory() {
//	return db.showTransactionHistory();
//}
//show single account all history
public ArrayList<History>  allHistory(long accountNumber) {
	ArrayList<History> list = db.allHistory(accountNumber);
//	//ArrayList<AllHistory> list = map.get(accountNumber);
//	for(int i=0;i<list.size();i++) {
//		History allHistory = list.get(i);
//		String dateString = allHistory.getDate();
//		//System.out.println(dateString);
//		String tempString = "";
//		for(int j=0;j<dateString.length();j++) {
//			if(dateString.charAt(j)>='0' && dateString.charAt(j)<='9') {
//				tempString = tempString+dateString.charAt(j);
//			}
//		}
//		allHistory.setDate(tempString);
//				
//	}
//	Collections.sort(list, new Comparator<History>() {
//        @Override
//        public int compare(History object1, History object2) {
//            return object2.getDate().compareTo(object1.getDate());
//        }
//});
//	

	return list;
	
}

  //use  
    public long addNewCustomers(CustomerInfo customerInfo, AccountInfo accountInfo){
//    	System.out.println(customerInfo);
//    	System.out.println(accountInfo);
        int key=0;
        long accNumber=0;
        try {
            key = db.insertToCustomerTable(customerInfo);
           
            accountInfo.setId(key);
            long bal = accountInfo.getBalance();
            //accountInfo.setBalance(bal);
       accNumber = db.insertToAccountTable(accountInfo);
       //long balance = accountInfo.getBalance();
       
       //System.out.println(accNumber+" accountNumber"+accountInfo.getBalance()+" balance");
        	history(accNumber,"Deposit",bal);
      
        }
            catch (SQLException e) {
            e.printStackTrace();
        }
        return accNumber;
    }
    public void history(long accountNumber,String process, long balance) {
    	db.history(accountNumber,process,balance);
		
	}
    
    //insert to account table
    
    
    
	public void accountInsert(AccountInfo accountInfo) {
		// TODO Auto-generated method stub
		long key=0;
		 try {
			 long bal = accountInfo.getBalance()-1;
	            accountInfo.setBalance(bal);
	           key =  db.insertToAccountTable(accountInfo);
	           System.out.println(key+" auto gen key");
	           history(key, "Deposit", bal+1);

	        }
	        catch (SQLException e){
	            e.printStackTrace();
	        }
	}

    public void deleteAccountData(int delId,long accountNumber) throws HandledException {
        int id = delId;
        long account_number = accountNumber;
        try{
            db.deleteFromAccountTable(account_number);
   }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteCustomerData(int delId){
        int id = delId;
        try {
            db.deleteFromCustomerTable(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HashMapHandler.INSTANCE.dbHashMap.remove(id);
    }


    public void balancDepositeData(int balId,long accNumber,long deposit) throws SQLException {
        HashMap<Long,AccountInfo> map = HashMapHandler.INSTANCE.output(balId);
if(map.containsKey(accNumber)) {
    long balance = map.get(accNumber).getBalance();
    balance = balance + deposit;
    db.updateBalance(balance, accNumber);
    map.get(accNumber).setBalance(balance);
    System.out.println("Your new Balance is " + balance);
}else{
    System.out.println("Please enter the correct id and acc number");
}
        }
        public void balanceWithdrawalData(int balId,long accNumber,long withdraw) throws SQLException {
        HashMap<Long,AccountInfo> map = HashMapHandler.INSTANCE.output(balId);
            if(map.containsKey(accNumber)) {
                long balance = map.get(accNumber).getBalance();
                if (balance >= withdraw) {
                    balance = balance - withdraw;
                    db.updateBalance(balance, accNumber);
                    map.get(accNumber).setBalance(balance);
                    System.out.println("Your remaining Balance is " + balance);

                } else {
                    System.out.println("Balance Insufficient");
                }
            }else{
                System.out.println("Please enter the correct id and acc number");

            }
    }
   public void updateCustomerStatusActive(int id, long accountNumber) throws SQLException {
        db.updateCustomerStatusToActive(id);
   }
   
   public boolean checkExistId(int customerId){
       if(HashMapHandler.INSTANCE.dbHashMapAll.containsKey(customerId)) {
           return true;
       }
       else{
           return false;
       }
    }
    public boolean chechIdActive(int customerId){
        if (HashMapHandler.INSTANCE.dbHashMap.containsKey(customerId)){
            return  true;
        }else{
            return false;
        }
    }
    public boolean checkAccountNumberExist(int customerId,long account_Number){
        HashMap<Long, AccountInfo> accountInfoHashMap = HashMapHandler.INSTANCE.allAcccountData(customerId);
        if(accountInfoHashMap.containsKey(account_Number)) {
            return true;
        }else{
            return false;
        }

        }




}