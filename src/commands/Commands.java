package commands;

import impl.Session;

public class Commands 
{
	@Command(name ="SELECT", role="User")
	public static void SelectPart(String param)
	{
		Session.INSTANCE.configurator.getCategories();
	}
}
