package systemuser;



import generateId.IdGenerator;

/**
 * 
 *abstract class for all general user
 *
 */
abstract public class User {

	/**
	 * related variables
	 */
    protected int id = 0;
    protected String name;
    protected String surname;
    protected UserType userType;
    

    /**
     * constructor
     * @param name takes a string value
     * @param surname takes a string value
     * @param userType takes a UserType
     */
    public User(String name, String surname, UserType userType) {
	this.name = name;
	this.surname = surname;
	this.userType = userType;
	id = new IdGenerator().getId();
    }

}
