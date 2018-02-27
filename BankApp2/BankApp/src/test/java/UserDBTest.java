import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDBTest {

    Customer user=new Customer("zboi","theking");
    UserDB db=new UserDB();
    @Test
    void put_get()
    {
        db.put(user.UserID,user);
        assertEquals(user.UserID,db.get(user.UserID).UserID);
    }

    @Test
    void getUID()
    {
        assertEquals(user.UserID,db.getUID(user.username));
    }


    @Test
    void remove_containsKey()
    {
        db.remove(user.UserID);
        assertEquals(false,db.containsKey(user.UserID));
    }
}