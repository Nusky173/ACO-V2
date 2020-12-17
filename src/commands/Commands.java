package commands;

public class Commands 
{
	@Command(name ="SELECT", role="ADMIN")
	public static void SelectPart()
	{
		System.out.println("we select part");
	}
}
