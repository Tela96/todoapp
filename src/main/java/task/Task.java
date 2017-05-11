package task;

import task.States;

import java.util.UUID;

/**
 * Created by akos on 2017.05.09..
 */
public class Task
{
    private Enum state;
    private String text;
    private String ID;
    private String userName;

    //Every task is created in backlog state.
    public Task(String text, String userName)
    {
        this.text = text;
        this.userName = userName;
        this.ID = UUID.randomUUID().toString();
        state = States.BACKLOG;

    }

    public Enum getState()
    {
        return state;
    }

    public void setState(States state)
    {
        this.state = state;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getID()
    {
        return ID;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (state != null ? !state.equals(task.state) : task.state != null) return false;
        if (text != null ? !text.equals(task.text) : task.text != null) return false;
        if (ID != null ? !ID.equals(task.ID) : task.ID != null) return false;
        return userName != null ? userName.equals(task.userName) : task.userName == null;
    }

    @Override
    public int hashCode()
    {
        int result = state != null ? state.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (ID != null ? ID.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
}
