package Banking;

import java.io.Serializable;

public class JointCustomer extends Customer implements Serializable
//jointCustomer extends from the Customer class
{
    public static final long serialVersionUID = BankingApplication.VERSION;
    //every time you change classes, you bump the number, and java's serialization api will be able to tell that any old files cant be deserialized

    private Identity secondaryOwner; //identity is a general class for a person
    
    //A joint customer is exactly as it sounds, two customers wrapped in one object
    //extended from the customer class

    public JointCustomer(
        String primaryUsername,
        String primaryPassword,
        String primaryName,
        String secondaryUsername,
        String secondaryPassword,
        String secondaryName)
    {
        super(primaryUsername, primaryPassword, primaryName, 1);

        this.secondaryOwner = new Identity(secondaryUsername, secondaryPassword, secondaryName,1);
    }

    public Identity secondaryOwner()
    {
        return this.secondaryOwner;
    }

    @Override
    public void setApproved(int approved) {
        this.secondaryOwner.setApproved(approved);
    }


    @Override
    public void setCancelled(int cancelled) {
        this.secondaryOwner.setCancelled(cancelled);
    }

    @Override
    public String toString()
    {
        return "Secondary owner, " + this.secondaryOwner.toString();
    }

    @Override
    public int approved()
    {
        if(super.approved() == 1 && secondaryOwner().approved() == 1)
        	return 1;
        return 0;
    }

    @Override
    public int cancelled() //check if either account is cancelled
    {
        if(super.cancelled() == 1 || secondaryOwner().cancelled() == 1)
        	return 0;
        return 1;
    }

    @Override
    public boolean authenticate(String password)
    {
        return super.authenticate(password) || secondaryOwner().authenticate(password);
    } //returns true if password authenticates on either user

    @Override
    public boolean usernameMatches(String username)
    {
        return super.usernameMatches(username) || secondaryOwner.usernameMatches(username);
    } //returns true if username authenticates on either user

}