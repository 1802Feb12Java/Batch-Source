import java.sql.CallableStatement;
import java.sql.SQLException;

public class ApplicationDB extends Database<Integer,Integer>
{

    int userID;
            //accountID, userID
    void put(Integer key, Integer value)
    {
        try
        {
            String selectSQL = "INSERT INTO Applications(UserID,ApplicationID) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, value);
            preparedStatement.setInt(2, key);
            preparedStatement.executeQuery();
            userID=value;
            log.info("Application for account "+key+" submitted ID:"+userID);
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 501");
        }
    }

    Integer get(Integer key)
    {
        //get userID given accountID
        int userID=0;
        try
        {
            //limit the rows returned by application
            String selectSQL = "SELECT * FROM Applications WHERE ApplicationID = ? Fetch First 1 Rows Only";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, key);
            rs = preparedStatement.executeQuery();
            if(rs.next())
            userID = rs.getInt(1);

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 502");
        }

        return userID;
    }

    void remove(Integer key)
    {
        try
        {
            CallableStatement callDelete = connection.prepareCall("{call deleteapp(?)}");
            callDelete.setInt(1, key);
            callDelete.execute();
            log.info("Application for account "+key+" deleted ID:"+userID);

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 503");
        }
    }

    //takes in accountID, returns true or false
    //tested in sqlDev
    boolean containsKey(Integer key)
    {
        boolean found=false;
        try
        {
            String selectSQL = "SELECT count(1) FROM Applications WHERE ApplicationID = ?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, key);
            rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                int count = rs.getInt(1);
                if (count > 0)
                    found=true;
            }
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 504");
        }

        return found;
    }

    public boolean containsKey(int accountID, int userID)
    {
        boolean found=false;
        try
        {
            String selectSQL = "SELECT count(1) FROM Applications WHERE ApplicationID = ? AND UserID = ?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, accountID);
            preparedStatement.setInt(2, userID);
            rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                int count = rs.getInt(1);
                if (count > 0)
                    found=true;
            }
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 504");
        }

        return found;
    }
}
