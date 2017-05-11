package data;

import exceptions.UserAlreadyExistException;
import user.User;

/**
 * Created by akos on 2017.05.10..
 */
public interface UserDao
{
    User get(String userName);
    User add(String userName) throws UserAlreadyExistException;
    boolean remove(String userName);
    boolean find(String userName);
    boolean find(User user);
    ;
}
