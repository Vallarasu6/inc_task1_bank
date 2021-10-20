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


import TransactionHistory.TransactionHistoryPojo;
import allHistoryPojo.AllHistory;

public class LogicLayer {
	
	  private InterfaceCommon db;
	    FileReader reader;

	    public LogicLayer() {
	        try {
	            reader = new FileReader("/home/inc15/eclipse-workspace/BankingApplication/src/main/java/file.properties");
	            Properties properties=new Properties();
	            properties.load(reader);
	            String data = properties.getProperty("dbConnection");
	            System.out.println(data+" Hi");
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
   
   
 //check account number exist
   public boolean checkAccountNumber(long acc_num) {
	   return db.checkAccountNumber(acc_num);
   }
 //check customerid exist
   public boolean checkCustomerId(int id) {
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
   }
   
// W/D  History



public void history(long accountNumber,String process,long balance) throws SQLException {
	db.history(accountNumber, process, balance);
}

public void bankCharges(long accountNumber, String process, long balance) {

	db.bankCharges(accountNumber,process,balance);
}

//show history


public ArrayList<History> showHistory() {
	return db.showHistory();
}

//transaction
public void transaction_history(int senderId,long sender_accountNumber,int receiverId,long receiver_accountNumber,long balance) throws SQLException {
db.transaction_history(senderId, sender_accountNumber, receiverId, receiver_accountNumber, balance);
}
//show transaction history
public ArrayList<TransactionHistoryPojo> showTransactionHistory() {
	return db.showTransactionHistory();
}
//show single account all history
public ArrayList<AllHistory>  allHistory(long accountNumber) {
	HashMap<Long, ArrayList<AllHistory>> map = db.allHistory(accountNumber);
	ArrayList<AllHistory> list = map.get(accountNumber);
	for(int i=0;i<list.size();i++) {
		AllHistory allHistory = list.get(i);
		String dateString = allHistory.getDate();
		//System.out.println(dateString);
		String tempString = "";
		for(int j=0;j<dateString.length();j++) {
			if(dateString.charAt(j)>='0' && dateString.charAt(j)<='9') {
				tempString = tempString+dateString.charAt(j);
			}
		}
		allHistory.setDate(tempString);
		
		//System.out.println(tempString+" date");
		
		
	}
	Collections.sort(list, new Comparator<AllHistory>() {
        @Override
        public int compare(AllHistory object1, AllHistory object2) {
            return object2.getDate().compareTo(object1.getDate());
        }
});
	
	
	
	
//	for(int i=0;i<list.size();i++) {
//		AllHistory allHistory = list.get(i);
//		System.out.println(allHistory.getDate());
//		
//		
//	}
	
	
	return list;
	
}
/*
 * public void allHistoryData(HashMap<Long, AllHistory> map) {
 * 
 * }
 */
   
  //use  
    public void addNewCustomers(ArrayList<CustomerInfo> customerList, ArrayList<AccountInfo> accountList){
        int key[]= new int[0];
        try {
            key = db.insertToCustomerTable(customerList);
            int size = key.length;
        AccountInfo accountinfo = new AccountInfo();
        for (int i=0;i<size;i++) {
           accountinfo = accountList.get(i);
            accountinfo.setId(key[i]);
           // System.out.println(key[i]);

        }
        int batch[] = db.insertToAccountTable(accountList);
        for(int i=0;i< batch.length;i++){
            if(batch[i]!=1){
                for(int j=0;j<size;j++){
                    deleteCustomerData(key[j]);
                    //System.out.println(key[j]);
                }
                System.out.println("No data stored, RollBacked");
                break;
            }
        }
        for (int i=0;i<size;i++) {
            HashMapHandler.INSTANCE.store(accountinfo.getAccountNumber(),accountinfo,key[i]);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void accountInsert(ArrayList<AccountInfo> accountList)  {
        try {
        	
            db.insertToAccountTable(accountList);
            int size = accountList.size();
            System.out.println("Logical layer account method worked");
            for (int i = 0; i < size; i++) {
                AccountInfo accountinfo = accountList.get(i);
                HashMapHandler.INSTANCE.store(accountinfo.getAccountNumber(), accountinfo, accountinfo.getId());
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
//    public void showDataAll() throws Exception {
//        try {
//            HashMap<Long, AccountInfo> accountInfoHashMap=db.showFromAccountTableAll();
//            //System.out.println("complete account "+accountInfoHashMap);
//            for(Long i : accountInfoHashMap.keySet()){
//                AccountInfo  accountInfo = accountInfoHashMap.get(i);
//                HashMapHandler.INSTANCE.getDbHashMapAll(accountInfo.getAccountNumber(), accountInfo, accountInfo.getId());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//    }
//    public void showData() {
//        try {
//            HashMap<Long, AccountInfo> accountInfoHashMap=db.showFromAccountTable();
//            for(Long i : accountInfoHashMap.keySet()){
//                AccountInfo  accountInfo = accountInfoHashMap.get(i);
//                HashMapHandler.INSTANCE.store(accountInfo.getAccountNumber(), accountInfo, accountInfo.getId());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


//    public void closeAll() {
//        db.closeConnection();
//    }
//    public void deleteData(int delId){
//        int id = delId;
//        try {
//            db.deleteFromAllTables(id);
//            HashMapHandler.INSTANCE.dbHashMap.remove(id);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void deleteAccountData(long accountNumber) throws HandledException{
//    	long account_number = accountNumber;
//    	
//    	
//    }
    public void deleteAccountData(int delId,long accountNumber) throws HandledException {
        int id = delId;
        long account_number = accountNumber;
        try{
            db.deleteFromAccountTable(account_number);
            
           // HashMapHandler.INSTANCE.dbHashMap.remove(id);
//            HashMap<Long, AccountInfo> hash = HashMapHandler.INSTANCE.getAccountInfo(id);
//           // if(hash.containsKey(account_number)) {
//            System.out.print(hash);
//                hash.remove(account_number);
//                System.out.println("Size is " + hash.size());
//
//                if (hash.size() == 0) {
//                    db.updateCustomerStatus(id);
//                    HashMapHandler.INSTANCE.dbHashMap.remove(id);
//                    System.out.println("Your data is completely deleted bcz of 0 accounts");
//                }
           // }
//            else{
//                System.out.println("The id is already inactive");
//            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteCustomerData(int delId){
        int id = delId;
        try {
            db.deleteFromCustomerTable(id);
            System.out.println("Logic layer delete"+id);
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