package data;

import exceptions.UserAlreadyExistException;
import user.SimpleUserImpl;
import user.User;

import java.sql.*;

/**
 * Created by akos on 2017.05.11..
 */
public class UserDaoSimleDBImpl implements UserDao
{

    private Connection conn = null;
    private static final String DATABASE = "jdbc:mysql://localhost/tododb?useSSL=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USER = "todomanager";
    private static final String DB_PASSWORD = "todomanager";
    private String query;

    public UserDaoSimleDBImpl()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public User get(String userName)
    {
        try
        {
            User temp = null;
            query = "SELECT * FROM Users WHERE Name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                temp = new SimpleUserImpl(rs.getString("Name"));
                return temp;
            }
            return null;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User add(String userName) throws UserAlreadyExistException
    {
        try
        {
            query = "INSERT INTO Users VALUES(NULL, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.execute();
            return new SimpleUserImpl(userName);
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remove(String userName)
    {
        return false;
    }

    @Override
    public boolean find(String userName)
    {
        if (get(userName) == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean find(User user)
    {
        if (get(user.getName()) == null)
        {
            return false;
        }
        return true;
    }
}
