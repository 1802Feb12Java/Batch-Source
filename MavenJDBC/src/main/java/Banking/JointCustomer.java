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
        super(primaryUsername, primaryPassword, primaryName);

        this.secondaryOwner = new Identity(secondaryUsername, secondaryPassword, secondaryName);
    }

    public Identity secondaryOwner()
    {
        return this.secondaryOwner;
    }

    @Override
    public void setApproved(boolean approved) {
        this.secondaryOwner.setApproved(approved);
    }


    @Override
    public void setCancelled(boolean cancelled) {
        this.secondaryOwner.setCancelled(cancelled);
    }

    @Override
    public String toString()
    {
        return "Secondary owner, " + this.secondaryOwner.toString();
    }

    @Override
    public boolean approved()
    {
        return super.approved() && secondaryOwner().approved();
    }

    @Override
    public boolean cancelled() //check if either account is cancelled
    {
        return super.cancelled() || secondaryOwner().cancelled();
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