import java.util.Properties;
import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.NumberFormat;

abstract class Database<K,V>
{
    static Logger log = Logger.getLogger("Database");
    static PreparedStatement preparedStatement=null;
    static ResultSet rs=null;
    static Connection connection = null;
    static boolean database_initialized=false;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();

    Database()
    {

        if(database_initialized == false)
        {
            initialize_db();
            database_initialized =true;
        }
    }

    abstract void put(K key,V value);
    abstract V get(K key);
    abstract void remove(K key);
    abstract boolean containsKey(K key);

    private void initialize_db()
    {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("src/Database.properties");
            // load a properties file
            prop.load(input);

            // get the property value and print it out
            String url=prop.getProperty("url");
            String username=prop.getProperty("username");
            String pword=prop.getProperty("pword");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        try
        {
            System.out.println("-------- Connecting --------");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@feb12usf.cw5gk9yjnm6f.us-east-2.rds.amazonaws.com:1521:ORCL", "zaaaain4321", "four4444");
            if (connection != null)
            {
                System.out.println("---- Connection Successful ----\n\n\n");
            }
            else
            {
                System.out.println("Failed to make connection!");
            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class not found");
        }
        catch (SQLException e)
        {
            System.out.println("An SQL exception occurred 701");
        }
    }




}



