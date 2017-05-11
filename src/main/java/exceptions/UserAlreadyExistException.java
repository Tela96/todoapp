package exceptions;

/**
 * Created by akos on 2017.05.10..
 */
public class UserAlreadyExistException extends Exception
{

    public UserAlreadyExistException(String message)
    {
        super(message);
    }
}
