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
    private int approved = 0;
    private int cancelled = 0;

	private int id;

    public Identity(String username, String password, String name, int id) //constructor
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.approved = 0;
        this.cancelled = 0;
        this.id = id;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
    public int approved() {
        return this.approved;
    }
    public int cancelled() {
        return this.cancelled;
    }
	public int getID() 
	{
		return id;
	}

	public void setID(int id) 
	{
		this.id = id;
	}

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }
}
