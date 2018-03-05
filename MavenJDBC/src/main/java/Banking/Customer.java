package Banking;

import java.io.Serializable;
import java.math.BigDecimal;

public class Customer implements Serializable
{
    public static final long serialVersionUID = BankingApplication.VERSION;
    //every time you change classes, you bump the number, and java's serialization api will be able to tell that any old files cant be deserialized

    private Identity identity;  //identity is a general class for a person
    private BigDecimal balance; //BigDecimal allows large balances to be stored
    							//consists of an arbitrary precision integer unscaled value and a 32-bit integer scale
    							//useful for financial figures
    
    public BigDecimal getBalance()
    {
		return balance;
	}

	public Customer(String username, String password, String name, int id)
    {
        super();
        this.setIdentity(new Identity(username, password, name, id));
        this.balance = BigDecimal.ZERO;
        return;
    }
    
    public Customer()
    {
        super();
    }

    //format toString with the mutators/accessors
    public String toString()
    {
        return String.format("Customer {name: %s, balance: %s, username: %s, password: %s}", this.name(), balance.toString(), this.username(), this.password());
    }

    public boolean usernameMatches(String username)
    {
        return this.getIdentity().usernameMatches(username);
    }

    public boolean authenticate(String password)
    {
        return this.getIdentity().authenticate(password);
    }

    public String username()
    {
        return getIdentity().username();
    }
    public String password()
    {
        return getIdentity().password();
    }

	public int getID() 
	{
		return getIdentity().getID();
	}

	public void setID(int id) 
	{
		this.getIdentity().setID(id);
	}
    
    public String name()
    {
        return getIdentity().name();
    }
    public BigDecimal balance()
    {
        return balance;
    }
    public int approved()
    {
        return this.getIdentity().approved();
    }
    public int cancelled()
    {
        return this.getIdentity().cancelled();
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }
    public void setApproved(int approved)
    {
        this.getIdentity().setApproved(approved);
    }
    public void setCancelled(int cancelled)
    {
        this.setCancelled(cancelled);
    }

    public boolean validatePotentialTransfer(BigDecimal amount) //works for both deposits and withdrawals
    {
        boolean isPositive = (amount.signum() >= 0); //make sure transfer amount is positive, signum() should force it generally though
        boolean hasMoney = (amount.compareTo(this.balance()) <= 0);
        return isPositive && hasMoney; //returns true only if both conditions are true, else returns false
    }

    public boolean transfer (Customer recipient, BigDecimal amount)
    // returns whether or not the transfer was successful
    {
        if (validatePotentialTransfer(amount))
        {
            this.setBalance(this.balance().subtract(amount)); //remove balance from this customer
            recipient.setBalance(recipient.balance().add(amount)); //add balance to recipient
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean validatePotentialDeposit(BigDecimal amount)
    {
        if (amount.signum() >= 0) //make sure deposit amount is positive, signum() should force it generally though
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean deposit (BigDecimal amount)
    {
        if (validatePotentialDeposit(amount)) //if the deposit is valid
        {
            this.setBalance(this.balance().add(amount)); //add the amount to the balance
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean withdraw (BigDecimal amount)
    {
        if (validatePotentialTransfer(amount))
        {
            this.setBalance(this.balance().subtract(amount)); //subtract the amount from the balance
            return true;
        }
        else
        {
            return false;
        }
    }

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
}