package Sim;

public class PendingInterest {
	private InterestMessage interestMsg;
	private SimEnt src;
	
	public PendingInterest(InterestMessage interestMessage, SimEnt src) {
		this.interestMsg = interestMessage;
		this.src = src;
	}
	
	public String getInterestName() {
		return interestMsg.getName();
	}
	
	public InterestMessage getInterestMsg() {
		
		return interestMsg;
	}
	
	public SimEnt getSource() {
		return src;
	}
}
