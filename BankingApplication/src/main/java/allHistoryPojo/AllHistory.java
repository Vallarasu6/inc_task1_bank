package allHistoryPojo;

public class AllHistory {
	private String process;
	private long amount;
	private String date;
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process= process;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "AllHistory [process=" + process+ ", amount=" + amount + ", date=" + date + "]";
	}
	

}
