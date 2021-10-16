package TransactionHistory;

public class TransactionHistoryPojo {
	private int id;
	private int senderId;
	private long senderAccountNumber;
	private int receiverId;
	private long receiverAccountNumber;
	private long amount;
	private String date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public long getSenderAccountNumber() {
		return senderAccountNumber;
	}
	public void setSenderAccountNumber(long senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public long getReceiverAccountNumber() {
		return receiverAccountNumber;
	}
	public void setReceiverAccountNumber(long receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
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
		return "TransactionHistoryPojo [id=" + id + ", senderId=" + senderId + ", senderAccountNumber="
				+ senderAccountNumber + ", receiverId=" + receiverId + ", receiverAccountNumber="
				+ receiverAccountNumber + ", amount=" + amount + ", date=" + date + "]";
	}
	

}
