package user;

/**
 * Created by akos on 2017.05.10..
 */
public abstract class User
{
    protected String name;


    public User(String name)
    {
        this.name = name;
    }

    public abstract String getName();
}
