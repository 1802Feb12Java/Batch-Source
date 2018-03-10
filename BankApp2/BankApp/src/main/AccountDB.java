import java.sql.SQLException;

public class AccountDB extends Database<Integer,Account>
{
    int userID;
            //accountID, account
    void put(Integer key, Account value)
    {
            try
            {
                String selectSQL = "INSERT INTO Accounts(UserID,AccountID,Money) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setInt(1, value.userID);
                preparedStatement.setInt(2, value.accountID);
                preparedStatement.setDouble(3, value.moneyz);//might have a problem with double conversion
                preparedStatement.executeQuery();
                userID=value.userID;
                log.info("User created ID:"+userID);
            }
            catch (SQLException e)
            {
                System.out.println("An SQL exception occurred 401");
            }
    }

    Account get(Integer key)
    {
        Account account=null;
        int userid=0;
        int accountID=0;
        double money=0;
        try
        {
            String selectSQL = "Select * From Accounts Where AccountID=?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, key);
            preparedStatement.executeQuery();
            rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                userid = rs.getInt(1);
                accountID = rs.getInt(2);
                money = rs.getDouble(3);
            }

            Customer user=user_get(userid);
            account=new Account(user,accountID);
            account.moneyz=money;

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 402");
        }

        return account;
    }

    private Customer user_get(Integer key)
    {

        Customer user=null;
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
                user=new Customer(username,pword);
                user.UserID=userid;
                user.personal_info=personal_info;
            }

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 403");
        }

        return user;
    }

    void remove(Integer key)
    {
        try
        {
            String selectSQL = "Delete from Accounts where AccountID=?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, key);
            preparedStatement.executeQuery();


        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 404");
        }
    }


    //taking in account id, return true if it exists
    //tested in SQLDev
    //might be able to delete
    boolean containsKey(Integer key)
    {
        boolean found=false;
        try
        {
            String selectSQL = "SELECT count(1) FROM Accounts WHERE AccountID = ?";
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
            System.out.println("An SQL exception occurred 405");
        }

        return found;
    }

    boolean access(Integer uid,Integer accountID)
    {
        boolean found=false;
        try
        {
            String selectSQL = "SELECT count(1) FROM Accounts WHERE AccountID = ? AND UserID = ?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, accountID);
            preparedStatement.setInt(2, uid);
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
            System.out.println("An SQL exception occurred 406");
        }

        return found;
    }

    public void view(int accountID)
    {
        try
        {
            String selectSQL = "Select * FROM Accounts Inner Join UserInfo on UserInfo.UserID=Accounts.UserID where Accounts.AccountID=?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, accountID);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                int userid = rs.getInt("USERID");
                int accountid=rs.getInt("ACCOUNTID");
                double money=rs.getDouble("MONEY");
                String username=rs.getString("USERNAME");
                int level=rs.getInt("LVL");

                System.out.print("UserID:"+userid+"\tUserName:"+username+"\tAccess:");
                if(level==1)
                    System.out.println("Admin");
                else if(level==2)
                    System.out.println("Employee");
                else if(level==3)
                    System.out.println("Customer");
                System.out.println("AccountID:"+accountid+"\tFunds:"+formatter.format(money));
            }
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 407");
        }
    }

    public void view_available_accounts(int userID)
    {
        try
        {
            String selectSQL = "Select AccountID,Money FROM Accounts Inner Join UserInfo on UserInfo.UserID=Accounts.UserID where Accounts.UserID=?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userID);
            rs = preparedStatement.executeQuery();
            System.out.println("Available accounts:");
            while (rs.next())
            {
                int accountid=rs.getInt("ACCOUNTID");
                double money=rs.getDouble("MONEY");
                System.out.println("AccountID:"+accountid+"\tFunds:"+formatter.format(money));

            }

        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 407");
        }
    }

    public boolean containsUIDKey(int userID)
    {
        boolean found=false;
        try
        {
            String selectSQL = "SELECT count(1) FROM Accounts WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userID);
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
            System.out.println("An SQL exception occurred 408");
        }

        return found;
    }

    public boolean rowExists(int accountID, int userID)
    {
        boolean found=false;
        try
        {
            String selectSQL = "SELECT count(1) FROM Accounts WHERE UserID = ? AND AccountID = ?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, accountID);
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
            System.out.println("An SQL exception occurred 409");
        }

        return found;
    }
}
