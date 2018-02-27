import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDBTest {

    AccountDB db=new AccountDB();
    Customer user=new Customer("josh","b");
    Customer user1=new Customer("josh1","b1");
    Account account=new Account(user,321);
    @Test
    void put_get()
    {

        db.put(321,account);
        assertEquals(account.userID,db.get(321).userID);
        assertEquals(account.accountID,db.get(321).accountID);
    }


    @Test
    void remove()
    {
        db.remove(321);
        assertEquals(false,db.containsKey(account.accountID));
    }

    @Test
    void containsKey()
    {
        db.put(321,account);
        assertEquals(true,db.containsKey(321));
        assertEquals(false,db.containsKey(500));
    }

    @Test
    void access()
    {


        assertEquals(true,db.access(user.UserID,account.accountID));
        assertEquals(false,db.access(user1.UserID,account.accountID));
    }

    @Test
    void containsUIDKey()
    {
        db.put(321,account);
        assertEquals(true,db.containsUIDKey(user.UserID));
        assertEquals(false,db.containsUIDKey(user1.UserID));
    }

    @Test
    void rowExists()
    {
        assertEquals(true,db.rowExists(account.accountID,user.UserID));
        assertEquals(false,db.rowExists(account.accountID,user1.UserID));
    }
}