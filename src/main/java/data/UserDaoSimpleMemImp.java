package data;

import exceptions.UserAlreadyExistException;
import user.SimpleUserImpl;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by akos on 2017.05.10..
 */
public class UserDaoSimpleMemImp implements UserDao
{
    private List<User> users = new ArrayList<>();



    @Override
    public User get(String userName)
    {
        return users.stream().filter(user -> user.getName().equals(userName)).collect(Collectors.toList()).get(0);
    }

    @Override
    public User add(String userName) throws UserAlreadyExistException
    {
        User newUser = new SimpleUserImpl(userName);
        if (!find(userName)){
        users.add(newUser);
        return newUser;}
        else throw new UserAlreadyExistException("Name in use, pick another username");
    }

    @Override
    public boolean remove(String userName)
    {
        return users.remove(users.stream()
            .filter(user -> user.getName().equals(userName))
            .collect(Collectors.toList()).get(0));
    }

    @Override
    public boolean find(String userName)
    {
        return users.stream()
            .anyMatch(user -> user.getName().equals(userName));
    }

    @Override
    public boolean find(User user)
    {
        return users.stream()
            .anyMatch(listUser -> listUser.equals(user));
    }
}
