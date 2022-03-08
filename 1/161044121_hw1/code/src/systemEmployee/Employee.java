package systemEmployee;

import java.util.*;

import systemtools.Message;
import systemuser.User;
import systemuser.UserType;

/**
 * 
 *Abstract class 
 *
 */
public abstract class Employee extends User{
	
	/**
	 * holds a array list of type message for central messaging
	 */
	protected static List<Message> messages = new ArrayList<Message>();

	/**
	 * constructor 
	 * @param name takes a string value
	 * @param surname takes a string 
	 * @param userType takes  a UserType
	 */
	public Employee(String name, String surname, UserType userType) {
		super(name, surname, userType);
	}

}
