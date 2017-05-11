package user;

import java.util.UUID;

/**
 * Created by akos on 2017.05.10..
 */
public class SimpleUserImpl extends User
{
    public SimpleUserImpl(String name)
    {
        super(name);
    }

    @Override
    public String getName()
    {
        return name;
    }
}
