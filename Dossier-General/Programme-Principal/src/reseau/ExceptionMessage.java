package reseau;


/**
 * 
 * Cette classe est une classe d'exception. Elle permet simplement de créer des objets de type ExceptionMessage.
 * 
 * @author S3T-G1
 *
 */

public class ExceptionMessage extends Exception {

	
	/**
	 * 
	 * Constructeur permettant d'instancier un ExceptionMessage
	 * 
	 * @param message Message d'exception
	 */
	
	public ExceptionMessage(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
