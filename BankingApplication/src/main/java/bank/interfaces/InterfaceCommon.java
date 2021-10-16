package bank.interfaces;

import bank.pojo.CustomerInfo;
import pojo_account.AccountInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
}
