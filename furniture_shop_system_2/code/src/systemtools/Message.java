package systemtools;

import systemEmployee.BranchEmployee;


/**
 * 
 * class to handle the messaging between branch employee and administrator
 *
 */
public class Message {
	
	/**
	 * relative variable
	 */
	private String productName;
	private int requiredAmount;
	private BranchEmployee sender;
	private String date;
	
	
	/**
	 * function to send message to administrator by specific patter
	 * @param productName takes a string value
	 * @param requiredAmount takes a int value
	 * @param day takes a int value
	 * @param month takes a int value
	 * @param year takes a int value
	 * @param sender takes the sender employee 
	 */
	public Message(String productName,int requiredAmount,int day,int month,int year,BranchEmployee sender) {
		this.productName = productName;
		this.requiredAmount = requiredAmount;
		this.sender = sender;
		this.date = String.valueOf(day)+"/"+String.valueOf(month)+"/"+ String.valueOf(year);
	}
	
	@Override
	public String toString() {
		String msg = "";
		msg+= "We need "+this.requiredAmount+" "+this.productName+" in "+this.sender.getBranch().getName()+" branch" +"\n";
		msg+="posted by : "+this.sender.getName()+" (id : "+this.sender.getId()+")\n";
		msg+="date : "+ this.date+"\n";
		return msg;
		
	}
}
