package data;

import task.States;
import task.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akos on 2017.05.11..
 */
public class TaskDaoDBImpl implements TaskDao
{
    private Connection conn = null;
    private static final String DATABASE = "jdbc:mysql://localhost/tododb?useSSL=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USER = "todomanager";
    private static final String DB_PASSWORD = "todomanager";
    private String query;


    public TaskDaoDBImpl()
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
    public List<Task> getAll(String userName)
    {
        try
        {
            query = "SELECT * FROM Todos WHERE Owner = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            System.out.println("Statement for get:\t" + stmt.toString());
            ResultSet rs = stmt.executeQuery();
            System.out.println("Result set after getting:\t" + rs.toString());
            return createResultList(rs);
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Task get(String ID)
    {
        Task temp = null;
        String owner;
        String text;
        States state;
        try
        {
            query = "SELECT * FROM Todos WHERE TodoID = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                text = rs.getString("Text");
                owner = rs.getString("Owner");
                state = States.valueOf(rs.getString("State"));
                temp = new Task(text, owner);
                temp.setState(state);
                System.out.println(temp.toString());
            }
            return temp;
        }catch (Exception e)
        {
            e.printStackTrace();
            return temp;
        }
    }

    @Override
    public boolean add(Task task)
    {
        try{
        query = "INSERT INTO Todos VALUES(?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, task.getID());
        stmt.setString(2, task.getText());
        stmt.setString(3, task.getState().toString());
        stmt.setString(4, task.getUserName());
        System.out.println(stmt.toString());
        stmt.execute();
        return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean remove(String ID)
    {
        return false;
    }

    @Override
    public boolean remove(Task task)
    {
        return false;
    }

    @Override
    public List<Task> getBacklog(String userName)
    {
        try
        {
            query = "SELECT * FROM Todos WHERE Owner = ? AND State = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, "BACKLOG");
            ResultSet rs = stmt.executeQuery();
            return createResultList(rs);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Task> getActive(String userName)
    {
        try
        {
            query = "SELECT * FROM Todos WHERE Owner = ? AND State = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, "ACTIVE");
            ResultSet rs = stmt.executeQuery();
            return createResultList(rs);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Task> getDone(String userName)
    {

        try
        {
            query = "SELECT * FROM Todos WHERE Owner = ? AND State = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, "DONE");
            ResultSet rs = stmt.executeQuery();
             return createResultList(rs);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private List<Task> createResultList(ResultSet rs) throws SQLException
    {
        List<Task> results = new ArrayList<>();
        Task temp = null;
        String ID;
        String owner;
        String text;
        States state;
        while (rs.next())
        {
            text = rs.getString("Text");
            owner = rs.getString("Owner");
            ID = rs.getString("TodoID");
            state = States.valueOf(rs.getString("State"));
            temp = new Task(text, owner);
            temp.setState(state);
            temp.setID(ID);
            results.add(temp);
        }
        System.out.println("Results for creating list out of set:\t" + results.toString());
        return results;
    }

    @Override
    public void setState(String ID, String state, String userName)
    {
        try
        {
            System.out.println("bentvagyunk a setstétben");
            query = "UPDATE Todos SET State = ? WHERE TodoID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, state);
            stmt.setString(2, ID);
            System.out.println("Update exec update with querry: " + stmt.toString());
            stmt.executeUpdate();
            System.out.println("Megérkeztünk Gyöngyöspatára!");
        }catch (SQLException e)
        {
            System.out.println("ELBASZODOTT EZ A GECI A KURVA ANYJAT MEGBASZOM");
            e.printStackTrace();
        }
    }

    @Override
    public void editText(String ID, String newText, String userID)
    {
        try
        {
            query = "UPDATE Todos SET Text = ? WHERE TodoID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newText);
            stmt.setString(2, ID);
            stmt.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println("ELBASZODOTT EZ A GECI A KURVA ANYJAT MEGBASZOM");
            e.printStackTrace();
        }
    }
}
