package exceptions;
/**
 * Specify a function parameter is invalid.
 * @author Skinz
 *
 */
public class InvalidParameterException extends Exception
{
	public InvalidParameterException(String message)
	{
		super(message);
	}
}
