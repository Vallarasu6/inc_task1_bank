package bank.database;
import bank.exception.HandledException;
import bank.interfaces.InterfaceCommon;
import bank.pojo.CustomerInfo;
import historyPojo.History;
import pojo_account.AccountInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import TransactionHistory.TransactionHistoryPojo;

public class DbStore implements InterfaceCommon {
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
    	    
    	   // System.out.println(balance+" balance");
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
    	    
    	   // System.out.println(balance+" balance");
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
					

	return customerList;
}

//show customerList - InActive
public ArrayList<CustomerInfo> getInActiveCustomerList() {
	ArrayList<CustomerInfo> customerList = new ArrayList<CustomerInfo>();
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
          customerList.add(object);

        }
    }
        catch (Exception e) {
        	System.out.println(e);
        }
					

	return customerList;
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
        System.out.println("Status changes in account");
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
System.out.print("status changed");
}
//get balance for W/D
public long withDraw(long accountNumber) {
	long acc_num = accountNumber;
	//System.out.println(acc_num+" acc");
	long balance=0;
	PreparedStatement st = null;
	ResultSet rst = null;
    try {
    	String sql1 = "SELECT * from accountInfo where customerAccountNumber= ?";
    	st = con.prepareStatement(sql1);
    	st.setLong(1, acc_num);
	    rst = st.executeQuery();
	    while(rst.next()) {
           balance = rst.getLong(5);
            
            System.out.println();
         }
	    
	   // System.out.println(balance+" balance");
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
  
    long accountNumber = acc_number;

        String sql = "UPDATE accountInfo SET customerBalance = ? WHERE  customerAccountNumber = ?";
        st = con.prepareStatement(sql);
        st.setLong(1, customerBalance);
        st.setLong(2,accountNumber);
       st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    st.close();
    System.out.print("Balance changed");
}
//  W/D  History



public void history(long accountNumber,String process,long balance) throws SQLException {
  
    PreparedStatement st = null;
    
    try {
        String sql = "INSERT INTO history (customerAccountNumber,process,amount,date) VALUES ( ?, ?, ?,now())";
        st = con.prepareStatement(sql);
        	st.setLong(1, accountNumber);
            st.setString(2, process);
            st.setLong(3, balance);
            
            st.executeUpdate();
           }
    catch (Exception e){
        System.out.println("toString(): " + e.toString());
        System.out.println("getMessage(): " + e.getMessage());
   }
   st.close();
   System.out.print("History updated");

}

//show history


public ArrayList<History> showHistory() {
	ArrayList<History> history = new ArrayList<History>();
	Statement st = null;
    ResultSet rs = null;
    try {
        st = con.createStatement();
        rs = st.executeQuery("SELECT * from history");

        while (rs.next()) {
           History object = new History();
           Integer customer_id = rs.getInt("id");
           Long accountNumber = rs.getLong("customerAccountNumber");
           String process = rs.getString("process");
           Long balance = rs.getLong("amount");
           String dateString = rs.getString("date");
           object.setId(customer_id);
           object.setAccountNumber(accountNumber);
           object.setProcess(process);
           object.setAmount(balance);
           object.setDate(dateString);
          history.add(object);

        }
    }
        catch (Exception e) {
        	System.out.println(e);
        }
					

	return history;
}

//transaction
public void transaction_history(int senderId,long sender_accountNumber,int receiverId,long receiver_accountNumber,long balance) throws SQLException {
	  
    PreparedStatement st = null;
    
    try {
        String sql = "INSERT INTO transactionHistory (senderId,senderAccountNumber,receiverId,receiverAccountNumber,amount,date) VALUES ( ?,?,?, ?, ?,now())";
        st = con.prepareStatement(sql);
        	st.setInt(1, senderId);
            st.setLong(2, sender_accountNumber);
            st.setInt(3, receiverId);
            st.setLong(4, receiver_accountNumber);          
            st.setLong(5, balance);    
            st.executeUpdate();
           }
    catch (Exception e){
        System.out.println("toString(): " + e.toString());
        System.out.println("getMessage(): " + e.getMessage());
   }
   st.close();
   System.out.print("Transaction History updated in database");

}

//show transaction history
public ArrayList<TransactionHistoryPojo> showTransactionHistory() {
	ArrayList<TransactionHistoryPojo> history = new ArrayList<TransactionHistoryPojo>();
	Statement st = null;
    ResultSet rs = null;
    try {
        st = con.createStatement();
        rs = st.executeQuery("SELECT * from transactionHistory");

        while (rs.next()) {
        	TransactionHistoryPojo object = new TransactionHistoryPojo();
           Integer id = rs.getInt("id");
           Integer sender_id = rs.getInt("senderId");
           Long senderAccountNumber = rs.getLong("senderAccountNumber");
           Integer receiver_id = rs.getInt("receiverId");
           Long receiverAccountNumber = rs.getLong("receiverAccountNumber");
           Long balance = rs.getLong("amount");
           String dateString = rs.getString("date");
           object.setId(id);
           object.setSenderId(sender_id);
           object.setSenderAccountNumber(senderAccountNumber);
           object.setReceiverAccountNumber(receiverAccountNumber);
           object.setReceiverId(receiver_id);
           object.setAmount(balance);
           object.setDate(dateString);
           history.add(object);

        }
    }
        catch (Exception e) {
        	System.out.println(e);
        }
					

	return history;
}




//end
    public HashMap<Long, AccountInfo> showFromAccountTableAll() throws Exception {
        Statement st = null;
        ResultSet rs = null;
        HashMap<Long, AccountInfo> accountInfoHashMap = new HashMap<>();

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from accountInfo");

            while (rs.next()) {
                AccountInfo pda = new AccountInfo();
                Integer customer_id = rs.getInt("customerId");
                Long accountNumber = rs.getLong("customerAccountNumber");
                String bankName = rs.getString("customerBankName");
                Long balance = rs.getLong("customerBalance");

              pda.setId(customer_id);
              pda.setAccountNumber(accountNumber);
              pda.setBankName(bankName);
              pda.setBalance(balance);
              accountInfoHashMap.put(accountNumber,pda);

            }
        }catch (Exception e){
            e.printStackTrace();

            throw new HandledException("dghghg");
        }

        rs.close();
        st.close();

        return accountInfoHashMap;

    }

    public HashMap<Long, AccountInfo> showFromAccountTable() throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        HashMap<Long, AccountInfo> accountHashMap = new HashMap<>();

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from accountInfo where status = 1 ");

            while (rs.next()) {
                AccountInfo pda = new AccountInfo();
                Integer customer_id = rs.getInt("customerId");
                Long accNumber = rs.getLong("customerAccountNumber");
                String bank = rs.getString("customerBankName");
                Long balance = rs.getLong("customerBalance");

                pda.setId(customer_id);
                pda.setAccountNumber(accNumber);
                pda.setBankName(bank);
                pda.setBalance(balance);
                accountHashMap.put(accNumber,pda);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

            rs.close();
            st.close();

        return accountHashMap;

    }
    public int[] insertToCustomerTable(ArrayList<CustomerInfo> arrayList) throws SQLException {

        con.setAutoCommit(false);
        PreparedStatement st = null;
        int size = arrayList.size();
        int cusIdArray[] = new int[size];
        int[] batchResults = new int[0];
        try {
            String sql = "INSERT INTO customerInfo (customerName,customerMobile,customerStatus) VALUES (?, ?, ?)";
            st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            for (int i = 0; i < size; i++) {
                CustomerInfo passData = arrayList.get(i);
                String name = passData.getName();
                long mobile = passData.getMobileNumber();
                st.setString(1, name);
                st.setLong(2, mobile);
                st.setInt(3,1);
                st.addBatch();

            }
            int i = 0;

            batchResults = null;

            batchResults = st.executeBatch();
           // System.out.println("Batch insert successful. Committing.");
            con.commit();
            ResultSet generatedKeysResultSet = st.getGeneratedKeys();

            while (generatedKeysResultSet.next()) {
                cusIdArray[i] = generatedKeysResultSet.getInt(1);
                //System.out.println("Your" + i + "id is " + generatedKeysResultSet.getInt(1));
                i++;
            }

        } catch (BatchUpdateException e) {
            System.out.println("Error message: " + e.getMessage());
            batchResults = e.getUpdateCounts();
            System.out.println("Rolling back batch insertion");
            con.rollback();


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Return value from inserting batch 1: " + Arrays.toString(batchResults));
        st.close();
        return cusIdArray;
    }
    public int[] insertToAccountTable(ArrayList<AccountInfo> accountInfo) throws SQLException {
        con.setAutoCommit(false);
        PreparedStatement st = null;
        int[] batchResults = new int[0];

        int size= accountInfo.size();
        int id;
        try {
            String sql = "INSERT INTO accountInfo (customerId,customerAccountNumber,customerBankName,customerBalance,status) VALUES ( ?, ?, ?, ?, ?)";
            st = con.prepareStatement(sql);
            for (int i = 0; i < size; i++) {
                AccountInfo passDataAccount = accountInfo.get(i);
                id = passDataAccount.getId();
                long accountNumber = passDataAccount.getAccountNumber();
                String bankName = passDataAccount.getBankName();
                long balance = passDataAccount.getBalance();
                st.setInt(1, id);
                st.setLong(2, accountNumber);
                st.setString(3, bankName);
                st.setLong(4, balance);
                st.setInt(5,1);
                st.addBatch();
            }
            batchResults = null;

            batchResults = st.executeBatch();
            //System.out.println("Batch insert successful. Committing.");
            con.commit();

//          int ar[] =  st.executeBatch();
//            for(int count : ar) {
//              //  if(ar[i]!=1)
//                System.out.println("There is an error in account table at id entered" +count);
//            }

            }catch (BatchUpdateException e) {
            //System.out.println("Error message: " + e.getMessage());
            batchResults = e.getUpdateCounts();
            System.out.println("Rolling back batch insertion");
            con.rollback();
        }
        catch (Exception e){
            System.out.println("toString(): " + e.toString());
            System.out.println("getMessage(): " + e.getMessage());
       }
        System.out.println("Return value from inserting batch 2: " + Arrays.toString(batchResults));
        st.close();
        return batchResults;

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
           // System.out.println("db layer dalte"+cust_id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        st.close();

    }



    public void updateCustomerStatus(int id) throws SQLException {
        PreparedStatement st = null;
        try {
            int customerId = id;
            String sql = "UPDATE customerInfo SET customerStatus = 0 WHERE customerId = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            st.executeUpdate();
            System.out.println("status changd from customer");

        }catch (SQLException e) {
            e.printStackTrace();
        }
        st.close();
    }
 

    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}