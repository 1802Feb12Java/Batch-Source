import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationDBTest {

    ApplicationDB db=new ApplicationDB();
    Customer user=new Customer("josh","b");
    Customer user1=new Customer("josh1","b1");
    Account account=new Account(user,321);
    @Test
    void put_get()
    {
        db.put(account.accountID,user.UserID);
        assertEquals((int)user.UserID,(int)db.get(account.accountID));
    }

    @Test
    void remove()
    {
        db.remove(account.accountID);
        assertEquals(false,db.containsKey(account.accountID));
    }

    @Test
    void containsKey()
    {
        db.put(account.accountID,user.UserID);
        assertEquals(true,db.containsKey(account.accountID));
        assertEquals(true,db.containsKey(account.accountID,user.UserID));
        assertEquals(false,db.containsKey(5000));
    }
}