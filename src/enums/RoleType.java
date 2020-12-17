package enums;

public enum RoleType
{
	User(0),
	Admin(1);
	
	private final int value;
	
    private RoleType(int value)
    {
        this.value = value;
    }

    public int getValue() 
    {
        return value;
    }
    
}
