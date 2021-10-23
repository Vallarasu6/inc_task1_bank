package bank.database;
import bank.exception.HandledException;
import bank.interfaces.InterfaceCommon;
import bank.logic.LogicLayer;
import bank.pojo.CustomerInfo;
import historyPojo.History;
import pojo_account.AccountInfo;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.security.auth.message.callback.PrivateKeyCallback.AliasRequest;
import javax.sound.midi.Soundbank;
import javax.swing.text.html.HTMLDocument.Iterator;

import TransactionHistory.TransactionHistoryPojo;
import allHistoryPojo.AllHistory;

public class DbStore implements InterfaceCommon{
    static Connection con = null;
    public DbStore(){

        try {
            if(con==null) {
            	Class.forName("com.mysql.jdbc.Driver");
            
                con = DriverManager.getConnection("jdbc:mysql://localhost/zoho", "root", "vallaK@6");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //update change ADDRESS
    
    public void changeAddress(String address, int id) {
PreparedStatement st = null;
        
        try {

            String sql = "UPDATE customerInfo SET address = ? WHERE customerId = ?";
            st = con.prepareStatement(sql);

            st.setString(1,address);
            st.setInt(2, id);
            st.executeUpdate();
            System.out.println("Status changes in account");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
//update change mobile number
    
    
    public void changeMobile(long mobile, int id) {
    	PreparedStatement st = null;
        
        try {

            String sql = "UPDATE customerInfo SET customerMobile = ? WHERE customerId = ?";
            st = con.prepareStatement(sql);

            st.setLong(1,mobile);
            st.setInt(2, id);
            st.executeUpdate();
            System.out.println("Status changes in account");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    //check account number exist
    public boolean checkAccountNumber(long acc_num) {
    	PreparedStatement st = null;
    	ResultSet rst = null;
    	int i=0;
        try {
        	String sql1 = "SELECT * from accountInfo where customerAccountNumber= ?";
        	st = con.prepareStatement(sql1);
        	st.setLong(1, acc_num);
    	    rst = st.executeQuery();
    	    if(rst.next()) {
              i=1;
             }
    	    else {
				i=0;
			}
    	    
        } catch (Exception e) {
    		// TODO: handle exception
    		 e.printStackTrace();
    	}
        if(i==1) {
        	return false;
        }else {
			return true;
		}
	}
    
    
    
    
    
    //check customerid exist
    public boolean checkCustomerId(int id) {
    	PreparedStatement st = null;
    	ResultSet rst = null;
    	int i=0;
        try {
        	String sql1 = "SELECT * from customerInfo where customerId= ?";
        	st = con.prepareStatement(sql1);
        	st.setLong(1, id);
    	    rst = st.executeQuery();
    	    if(rst.next()) {
              i=1;
             }
    	    else {
				i=0;
			}
    	    
    	} catch (Exception e) {
    		// TODO: handle exception
    		 e.printStackTrace();
    	}
        if(i==1) {
        	return false;
        }else {
			return true;
		}
	}
    
    //check balance
    public long checkBalance(long accountNumber) {
    	PreparedStatement st = null;
    	ResultSet rst = null;
    	long balance=0;
    	try {
	    	String sql1 = "SELECT * from accountInfo where customerAccountNumber= ? and status=1";
	    	st = con.prepareStatement(sql1);
	    	st.setLong(1, accountNumber);
	    	rst = st.executeQuery();
	 	   while(rst.next()) {
	 		balance = rst.getLong(4); 
	 		   }
	 }
	 catch (Exception e) {
		 System.out.print(e+" Error msg");
	}
    	return balance;
	}
    
    
    
    
    
    
    //check login
    public ArrayList<Long> checkLogin(int id) {
    	PreparedStatement st = null;
    	ResultSet rst = null;
    	int i=0;
    	ArrayList<Long> accountNumberArrayList = new ArrayList<Long>();
    	 try {
    	    	String sql1 = "SELECT * from accountInfo where customerId= ? and status=1";
    	    	st = con.prepareStatement(sql1);
    	    	st.setInt(1, id);
    	    	rst = st.executeQuery();
    	 	   while(rst.next()) {
    	 		 accountNumberArrayList.add(rst.getLong(2)); 
    	 		   }
    	 }
    	 catch (Exception e) {
    		 System.out.print(e+" Error msg");
		}
    	 
    	 return accountNumberArrayList;
	}
    
    //show customerList - Active
public ArrayList<CustomerInfo> getCustomerList() {
	ArrayList<CustomerInfo> customerList = new ArrayList<CustomerInfo>();
	Statement st = null;
    ResultSet rs = null;
    try {
        st = con.createStatement();
        rs = st.executeQuery("SELECT * from customerInfo where customerStatus=1");

        while (rs.next()) {
            CustomerInfo object = new CustomerInfo();
            Integer customer_id = rs.getInt("customerId");
            String name = rs.getString("customerName");
            Long mobile = rs.getLong("customerMobile");

          object.setId(customer_id);
          object.setName(name);
          object.setMobileNumber(mobile);
          customerList.add(object);

        }
    }
        catch (Exception e) {
        	System.out.println(e);
        }
					
   // System.out.print(customerList);
	return customerList;
}
//Total history
public ArrayList<History> showHistory() {
	ArrayList<History> list = new ArrayList<History>();
	History history;
	PreparedStatement st = null;
	ResultSet rst = null;
	
    try {
    	String sql1 = "SELECT * FROM history";
    	st = con.prepareStatement(sql1);
    	
    	rst = st.executeQuery();
	   while(rst.next()) {
		   int id = rst.getInt(1);
		   long accountNumber= rst.getLong(2);
		   String process = rst.getString(3);
		   long balance= rst.getLong(4);
		   String date = rst.getString(6);
		  // process = id+" Acc Number "+accountNumber+" "+ process;
		   history = new History();
		   history.setId(id);
		   history.setAccountNumber(accountNumber);
		   history.setProcess(process);
		   history.setAmount(balance);
		   history.setDate(date);
		   list.add(history);
			   }
		   
			   
		   
		   
	   
	   
	   }catch (Exception e) {
		// TODO: handle exception
		   System.out.println(e);
	}
	return list;
    
}



//particular person history
public ArrayList<History> allHistory(long accountNumber) {
	ArrayList<History> list = new ArrayList<History>();
	History history;
	PreparedStatement st = null;
	ResultSet rst = null;
	
    try {
    	String sql1 = "SELECT * FROM history where customerAccountNumber=? order by date DESC";
    	st = con.prepareStatement(sql1);
    	st.setLong(1, accountNumber);
    	rst = st.executeQuery();
	   while(rst.next()) {
		  
		   String process = rst.getString(3);
		   long balance= rst.getLong(4);
		   String date = rst.getString(6);
		   history = new History();
		   history.setProcess(process);
		   history.setAmount(balance);
		   history.setDate(date);
		   list.add(history);
			   }
		   
			   
		   
		   
	   
	   
	   }catch (Exception e) {
		// TODO: handle exception
		   System.out.println(e);
	}
	return list;
    
}

//show customerList - InActive
public ArrayList<CustomerInfo> getInActiveCustomerList() {
	ArrayList<CustomerInfo> customerList1 = new ArrayList<CustomerInfo>();
	Statement st = null;
    ResultSet rs = null;
    try {
        st = con.createStatement();
        rs = st.executeQuery("SELECT * from customerInfo where customerStatus=0");

        while (rs.next()) {
            CustomerInfo object = new CustomerInfo();
            Integer customer_id = rs.getInt("customerId");
            String name = rs.getString("customerName");
            Long mobile = rs.getLong("customerMobile");

          object.setId(customer_id);
          object.setName(name);
          object.setMobileNumber(mobile);
          customerList1.add(object);

        }
    }
        catch (Exception e) {
        	System.out.println(e);
        }
					
    //System.out.print(customerList1);
	return customerList1;
	
}
//show accountList - Inactive
public ArrayList<AccountInfo> getInActiveAccountList() {
	ArrayList<AccountInfo> accountList = new ArrayList<AccountInfo>();
	Statement st = null;
    ResultSet rs = null;
    try {
        st = con.createStatement();
        rs = st.executeQuery("SELECT * from accountInfo where status=0");

        while (rs.next()) {
           AccountInfo object = new AccountInfo();
           Integer customer_id = rs.getInt("customerId");
           Long accountNumber = rs.getLong("customerAccountNumber");
           String bankName = rs.getString("customerBankName");
           Long balance = rs.getLong("customerBalance");
           object.setId(customer_id);
           object.setAccountNumber(accountNumber);
           object.setBankName(bankName);
           object.setBalance(balance);
           accountList.add(object);

        }
    }
        catch (Exception e) {
        	System.out.println(e);
        }
					

	return accountList;
}


//show Accounts
public ArrayList<AccountInfo> getAccountList() {
	ArrayList<AccountInfo> accountList = new ArrayList<AccountInfo>();
	Statement st = null;
    ResultSet rs = null;
    try {
        st = con.createStatement();
        rs = st.executeQuery("SELECT * from accountInfo where status=1");

        while (rs.next()) {
           AccountInfo object = new AccountInfo();
           Integer customer_id = rs.getInt("customerId");
           Long accountNumber = rs.getLong("customerAccountNumber");
           String bankName = rs.getString("customerBankName");
           Long balance = rs.getLong("customerBalance");
           object.setId(customer_id);
           object.setAccountNumber(accountNumber);
           object.setBankName(bankName);
           object.setBalance(balance);
           accountList.add(object);

        }
    }
        catch (Exception e) {
        	System.out.println(e);
        }
					

	return accountList;
}
//Delete both tables.

public void deleteFromAllTables(int id) throws SQLException {
    PreparedStatement st = null;
    PreparedStatement st1 = null;
        int cust_id = id;
    try {
        String sql1 = "UPDATE customerInfo SET customerStatus = 0 WHERE customerId = ?";
        st1 = con.prepareStatement(sql1);
        st1.setInt(1,cust_id);
        st1.executeUpdate();
        String sql = "UPDATE accountInfo SET status = 0 WHERE customerId = ?";
        st = con.prepareStatement(sql);
        st.setInt(1,cust_id);
        st.executeUpdate();


    } catch (SQLException e) {
        e.printStackTrace();
    }
    st.close();
    st1.close();
}

//Delete Account

public void deleteFromAccountTable(long accountNumber) throws SQLException {
    PreparedStatement st = null;
    long acc_Number = accountNumber;
    try {

        String sql = "UPDATE accountInfo SET status = 0 WHERE customerAccountNumber = ?";
        st = con.prepareStatement(sql);

        st.setLong(1,acc_Number);
        st.executeUpdate();
        System.out.println("Status changes in account");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    st.close();

}

//Update customer status to active
public void updateCustomerStatusToActive(int id) throws SQLException{
   
	PreparedStatement st = null;
    int cus_id = id;
    try {

        String sql = "UPDATE customerInfo SET customerStatus = 1 WHERE customerId = ?";
        st = con.prepareStatement(sql);

        st.setLong(1,cus_id);
        st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    st.close();
}
//Update account status to active
public void updateAccountStatusToActive(int id,long accountNumber) throws SQLException{
PreparedStatement st = null;
PreparedStatement st1 = null;
int cus_id = id;
    long acc_num = accountNumber;
try {
    String sql1 = "UPDATE customerInfo SET customerStatus = 1 WHERE customerId = ?";
    st1 = con.prepareStatement(sql1);
    st1.setInt(1,cus_id);
    st1.executeUpdate();
    String sql = "UPDATE accountInfo SET status = 1 WHERE customerAccountNumber = ?";
    st = con.prepareStatement(sql);
    st.setLong(1,acc_num);
    st.executeUpdate();


} catch (SQLException e) {
    e.printStackTrace();
}
st.close();
st1.close();
}
//get balance for W/D
public long withDraw(long accountNumber) {
	long acc_num = accountNumber;
	long balance=0;
	PreparedStatement st = null;
	ResultSet rst = null;
    try {
    	String sql1 = "SELECT * from accountInfo where customerAccountNumber= ?";
    	st = con.prepareStatement(sql1);
    	st.setLong(1, acc_num);
	    rst = st.executeQuery();
	    while(rst.next()) {
           balance = rst.getLong(4);
         }
	    
	} catch (Exception e) {
		// TODO: handle exception
		 e.printStackTrace();
	}
    return balance;
}



//update balance

public void updateBalance(long balance,long acc_number) throws SQLException {
    PreparedStatement st = null;
    try {
   long customerBalance = balance;
  
    

        String sql = "UPDATE accountInfo SET customerBalance = ? WHERE  customerAccountNumber = ?";
        st = con.prepareStatement(sql);
        st.setLong(1, customerBalance);
        st.setLong(2,acc_number);
       st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    st.close();
    //System.out.print("Balance changed");
}






public void history(long accountNumber,String process,long balance) {
PreparedStatement st = null;
    
    try {
        String sql = "INSERT INTO history (customerAccountNumber,process,balance,bankCharge,date) VALUES (?, ?, ?, ?,now())";
        st = con.prepareStatement(sql);
            st.setLong(1, accountNumber);
            st.setString(2, process); 
            st.setLong(3, balance); 
            st.setLong(4, 1); 
               
            st.executeUpdate();
           }
    catch (Exception e){
        System.out.println("toString(): " + e.toString());
        System.out.println("getMessage(): " + e.getMessage());
   }
   try {
	st.close();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
 
    //use
    
    public int insertToCustomerTable(CustomerInfo customerInfo) throws SQLException {
    	 CustomerInfo passData = customerInfo;
        //con.setAutoCommit(false);
        PreparedStatement st = null;
        int cusIdArray=0;
        int[] batchResults=new int[1];
        try {
            String sql = "INSERT INTO customerInfo (customerName,customerMobile,customerStatus,address) VALUES (?, ?, ?, ?)";
            st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                String name = passData.getName();
                long mobile = passData.getMobileNumber();
                String address = passData.getAddress();
                st.setString(1, name);
                st.setLong(2, mobile);
                st.setInt(3,1);
                st.setString(4,address);
                st.addBatch();

            batchResults = st.executeBatch();
            //con.commit();
            ResultSet generatedKeysResultSet = st.getGeneratedKeys();

            while (generatedKeysResultSet.next()) {
                cusIdArray = generatedKeysResultSet.getInt(1);
                
            }

        } catch (BatchUpdateException e) {
            System.out.println("Error message: " + e.getMessage());
            batchResults = e.getUpdateCounts();
            System.out.println("Rolling back batch insertion");
           // con.rollback();


        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Return value from inserting batch 1: " + Arrays.toString(batchResults));
        st.close();
        return cusIdArray;
    }
    
    //use
    
    public int insertToAccountTable(AccountInfo accountInfo) throws SQLException {
    	 AccountInfo passDataAccount = accountInfo;
        //con.setAutoCommit(false);
        PreparedStatement st = null;
        int[] batchResults = new int[1];
        int cusAccNumber=0;
        try {
            String sql = "INSERT INTO accountInfo (customerId,customerBankName,customerBalance,status) VALUES ( ?, ?, ?, ?)";
            st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            st = con.prepareStatement(sql);
               
                int id = passDataAccount.getId();
                String bankName = passDataAccount.getBankName();
                long balance = passDataAccount.getBalance();
                st.setInt(1, id);
                st.setString(2, bankName);
                st.setLong(3, balance);
                st.setInt(4,1);
                st.addBatch();
         
            batchResults = null;

            batchResults = st.executeBatch();
            //con.commit();
            ResultSet generatedKeysResultSet = st.getGeneratedKeys();
            while (generatedKeysResultSet.next()) {
                cusAccNumber = generatedKeysResultSet.getInt(1);
                
            }

            }catch (BatchUpdateException e) {
            batchResults = e.getUpdateCounts();
            System.out.println("Rolling back batch insertion");
            //con.rollback();
        }
        catch (Exception e){
            System.out.println("toString(): " + e.toString());
            System.out.println("getMessage(): " + e.getMessage());
       }
        st.close();
        return cusAccNumber;

    }

    public void deleteFromCustomerTable(int id) throws SQLException {
     con.setAutoCommit(false);
        PreparedStatement st = null;

        int cust_id = id;
        try {
            String sql = "DELETE FROM customerInfo WHERE customerId = ?";
            st = con.prepareStatement(sql);
            st.setInt(1,cust_id);
            st.executeUpdate();
          con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        st.close();

    }
	@Override
	public HashMap<Long, AccountInfo> showFromAccountTable() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateCustomerStatus(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public HashMap<Long, AccountInfo> showFromAccountTableAll() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ArrayList<TransactionHistoryPojo> showTransactionHistory() {
//		// TODO Auto-generated method stub
//		return null;
//	}


//  //bank Charges history
//  public void bankCharges(long accountNumber, String process, long balance) {
//  	PreparedStatement st = null;
//      
//      try {
//          String sql = "INSERT INTO charges (customerAccountNumber,process,amount,date) VALUES ( ?, ?, ?,now())";
//          st = con.prepareStatement(sql);
//          	st.setLong(1, accountNumber);
//              st.setString(2, process);
//              st.setLong(3, balance);
//              
//              st.executeUpdate();
//             }
//      catch (Exception e){
//          System.out.println("toString(): " + e.toString());
//          System.out.println("getMessage(): " + e.getMessage());
//     }
//     try {
//		st.close();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//     System.out.print("History updated");
//  }
//  
  /////////////////////////////////////////////////////////////////////////////////

////show transaction history
//public ArrayList<TransactionHistoryPojo> showTransactionHistory() {
//	ArrayList<TransactionHistoryPojo> history = new ArrayList<TransactionHistoryPojo>();
//	Statement st = null;
//    ResultSet rs = null;
//    try {
//        st = con.createStatement();
//        rs = st.executeQuery("SELECT * from transactionHistory");
//
//        while (rs.next()) {
//        	TransactionHistoryPojo object = new TransactionHistoryPojo();
//           Integer id = rs.getInt("id");
//           Long senderAccountNumber = rs.getLong("senderAccountNumber");
//           Long receiverAccountNumber = rs.getLong("receiverAccountNumber");
//           Long balance = rs.getLong("amount");
//           String dateString = rs.getString("date");
//           object.setId(id);
//           object.setSenderAccountNumber(senderAccountNumber);
//           object.setReceiverAccountNumber(receiverAccountNumber);
//           object.setAmount(balance);
//           object.setDate(dateString);
//           history.add(object);
//
//        }
//    }
//        catch (Exception e) {
//        	System.out.println(e);
//        }
//					
//
//	return history;
//}
//
//   ////////////////////////////////////////////////////////////////////////////
	/*
	 * // W/D History
	 * 
	 * 
	 * 
	 * public void history(long accountNumber,String process,long balance) throws
	 * SQLException { PreparedStatement st = null;
	 * 
	 * try { String sql =
	 * "INSERT INTO history (customerAccountNumber,process,amount,date) VALUES ( ?, ?, ?,now())"
	 * ; st = con.prepareStatement(sql); st.setLong(1, accountNumber);
	 * st.setString(2, process); st.setLong(3, balance);
	 * 
	 * int i = st.executeUpdate(); System.out.print(i+" value i"); } catch
	 * (Exception e){ e.printStackTrace();
	 * 
	 * } st.close();
	 * 
	 * }
	 *//////////////////////////////////////////////////////////////////
////show history
//
//
//public ArrayList<History> showHistory() {
//	ArrayList<History> history = new ArrayList<History>();
//	Statement st = null;
//    ResultSet rs = null;
//    try {
//        st = con.createStatement();
//        rs = st.executeQuery("SELECT * from history");
//
//        while (rs.next()) {
//           History object = new History();
//           Integer customer_id = rs.getInt("id");
//           Long accountNumber = rs.getLong("customerAccountNumber");
//           String process = rs.getString("process");
//           Long balance = rs.getLong("amount");
//           String dateString = rs.getString("date");
//           object.setId(customer_id);
//           object.setAccountNumber(accountNumber);
//           object.setProcess(process);
//           object.setAmount(balance);
//           object.setDate(dateString);
//          history.add(object);
//          //System.out.print(object+" printining object in show history");
//
//        }
//    }
//        catch (Exception e) {
//        	System.out.println(e);
//        }
//					
//   // System.out.print(history+" printining list in show history");
//	return history;
//}
	
	
	
	
	
////transaction
//public void transaction_history(long sender_accountNumber,long receiver_accountNumber,long balance) throws SQLException {
//	  
//    PreparedStatement st = null;
//    
//    try {
//        String sql = "INSERT INTO transactionHistory (senderAccountNumber,receiverAccountNumber,amount,date) VALUES ( ?, ?, ?,now())";
//        st = con.prepareStatement(sql);
//        	//st.setInt(1, senderId);
//            st.setLong(1, sender_accountNumber);
//            //st.setInt(3, receiverId);
//            st.setLong(2, receiver_accountNumber);          
//            st.setLong(3, balance);    
//            st.executeUpdate();
//           }
//    catch (Exception e){
//        System.out.println("toString(): " + e.toString());
//        System.out.println("getMessage(): " + e.getMessage());
//   }
//   st.close();
//   //System.out.print("Transaction History updated in database");
//
//}
}