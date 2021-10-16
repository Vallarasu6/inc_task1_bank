package pojo_account;
public class AccountInfo {

    public AccountInfo(){}

    private int id;
    private String bankName;
    private long accountNumber;
    private long balance;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setBankName(String bankName){
        this.bankName = bankName;
    }
    public String getBankName(){
        return this.bankName;
    }
    public void setAccountNumber(long accountNumber){
        this.accountNumber = accountNumber;
    }
    public long getAccountNumber(){
        return this.accountNumber;
    }
    public void setBalance(long balance){
        this.balance = balance;
    }
    public long getBalance(){ return this.balance; }

    @Override
    public String toString(){
        String output = this.balance+"  "+this.id;
        return output;
    }
}
