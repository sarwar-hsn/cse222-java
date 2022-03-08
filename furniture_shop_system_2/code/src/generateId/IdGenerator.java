package generateId;

/**
 * Class to generate random , unique id
 * @author sarwar
 *
 */
public class IdGenerator {
    /**
     * static int variable
     */
	static int id = 0;
	/**
	 * constructor
	 */
    public IdGenerator() {
	++id;
    }

    /**
     * function to get a int id
     * @return returns a int value
     */
    public int getId() {
	return IdGenerator.id;
    }

}
