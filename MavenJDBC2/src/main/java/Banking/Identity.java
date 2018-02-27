package Banking;

import java.io.Serializable;

public class Identity implements Serializable
{
	/*
	 * The identity class represents the identity of a person.
	 * Customer contains an identity instance to identity itself,
	 * and joint customer has an identity and a customer.
	 * The use of this Serializable class demonstrates a valid use of encapsulation.
	 */
    public static final long serialVersionUID = BankingApplication.VERSION;
    
    private String username, password, name;
    private boolean approved;
    private boolean cancelled;

    public Identity(String username, String password, String name) //constructor
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.approved = false;
        this.cancelled = false;
    }

    public String toString() //toString
    {
        return String.format("Identity {name: %s, username: %s, password: %s}", this.name(), this.username(), this.password());
    }

    public boolean usernameMatches(String username)
    {
        return this.username().equals(username);
    }

    public boolean authenticate(String password)
    {
        return this.password().equals(password);
    }

    //getters and setters
    public String username()
    {
        return username;
    }
    public String password()
    {
        return password;
    }
    public String name()
    {
        return name;
    }
    public boolean approved() {
        return this.approved;
    }
    public boolean cancelled() {
        return this.cancelled;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
