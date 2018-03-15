import java.sql.SQLException;

public class UserDB extends Database<Integer,User>
{
            //uid, User
    void put(Integer key, User value)
    {

        try
        {
            String selectSQL = "INSERT INTO UserInfo(UserName,Pword,PersonalInfo,Lvl) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, value.username);
            preparedStatement.setString(2, value.password);
            preparedStatement.setString(3, value.personal_info);
            preparedStatement.setInt(4, value.level);
            preparedStatement.executeQuery();
            log.info("User created ID:"+value);
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 601");
        }

    }

    //might delete if never used
    User get(Integer key)
    {
        User user=null;

        try
        {

            String selectSQL = "Select * From UserInfo Where UserID=?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, key);
            preparedStatement.executeQuery();
            rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                int userid = rs.getInt(1);
                String username = rs.getString(2);
                String pword = rs.getString(3);
                String personal_info = rs.getString(4);
                int level = rs.getInt(5);
                if(level==3)
                    user = new Customer(username, pword);
                else if(level==2)
                    user=new Employee(username,pword);
                else
                    user=new Admin(username,pword);

                user.UserID=userid;
                user.personal_info=personal_info;
            }

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 602");
        }

        return user;
    }

    int getUID(String key)
    {
        int userid=0;

        try
        {
            String selectSQL = "Select * From UserInfo Where UserName=?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, key);
            preparedStatement.executeQuery();
            rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                userid = rs.getInt(1);
            }

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 603");
        }

        return userid;
    }

    User get(String key)
    {

        User user=null;
        try
        {
            String selectSQL = "Select * From UserInfo Where UserName=?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, key);
            preparedStatement.executeQuery();
            rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                int userid = rs.getInt(1);
                String username = rs.getString(2);
                String pword = rs.getString(3);
                String personal_info = rs.getString(4);
                int level = rs.getInt(5);
                if(level==3)
                    user = new Customer(username, pword);
                else if(level==2)
                    user=new Employee(username,pword);
                else
                    user=new Admin(username,pword);

                user.UserID=userid;
                user.personal_info=personal_info;
            }

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 603");
        }

        return user;
    }

    void remove(Integer key)
    {
        try
        {
            String selectSQL1 = "Delete from Applications where UserID=?";
            String selectSQL2 = "Delete from UserInfo where UserID=?";
            String selectSQL3 = "Delete from Accounts where UserID=?";

            preparedStatement = connection.prepareStatement(selectSQL1);
            preparedStatement.setInt(1, key);
            preparedStatement.executeQuery();

            preparedStatement = connection.prepareStatement(selectSQL2);
            preparedStatement.setInt(1, key);
            preparedStatement.executeQuery();

            preparedStatement = connection.prepareStatement(selectSQL3);
            preparedStatement.setInt(1, key);
            preparedStatement.executeQuery();
            log.info("User deleted ID:"+key);

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred");
        }
    }

    //might delete if never used
    //tested in SQL DEv
    boolean containsKey(Integer key)
    {
        boolean found = false;
        try {
            String selectSQL = "SELECT count(1) FROM UserInfo  WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, key);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0)
                    found = true;
            }
        } catch (SQLException e) {
            System.out.println("An SQL exception occurred 604");
        }

        return found;
    }

    boolean containsKey(String key)
    {
        boolean found=false;
        try
        {
            String selectSQL = "SELECT count(1) FROM UserInfo  WHERE UserName = ?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, key);
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
            System.out.println("An SQL exception occurred 605");
        }

        return found;
    }

}

