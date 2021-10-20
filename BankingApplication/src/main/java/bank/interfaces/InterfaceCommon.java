package bank.interfaces;

import bank.pojo.CustomerInfo;
import historyPojo.History;
import pojo_account.AccountInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import TransactionHistory.TransactionHistoryPojo;
import allHistoryPojo.AllHistory;

public interface InterfaceCommon {

    HashMap<Long, AccountInfo> showFromAccountTable() throws SQLException;
    int[] insertToCustomerTable(ArrayList<CustomerInfo> arrayList) throws SQLException;
    int[] insertToAccountTable(ArrayList<AccountInfo> accountInfo)throws SQLException;
    void closeConnection();
    void deleteFromAllTables(int id) throws SQLException;
    void deleteFromCustomerTable(int id) throws SQLException;
    void updateBalance(long balance, long accNumber) throws SQLException;
    void deleteFromAccountTable(long account_number) throws SQLException;

    void updateCustomerStatus(int id) throws SQLException;

    void updateCustomerStatusToActive(int id) throws SQLException;
    HashMap<Long, AccountInfo> showFromAccountTableAll() throws SQLException, Exception;
	boolean checkAccountNumber(long acc_num);
	boolean checkCustomerId(int id);
	ArrayList<CustomerInfo> getCustomerList();
	ArrayList<AccountInfo> getInActiveAccountList();
	ArrayList<AccountInfo> getAccountList();
	void updateAccountStatusToActive(int id, long accountNumber) throws SQLException;
	long withDraw(long accountNumber);
	void history(long accountNumber, String process, long balance) throws SQLException;
	ArrayList<History> showHistory();
	void transaction_history(int senderId, long sender_accountNumber, int receiverId, long receiver_accountNumber,
			long balance) throws SQLException;
	ArrayList<TransactionHistoryPojo> showTransactionHistory();
	HashMap<Long, ArrayList<AllHistory>> allHistory(long accountNumber);
	void bankCharges(long accountNumber, String process, long balance);
	ArrayList<CustomerInfo> getInActiveCustomerList();
}
