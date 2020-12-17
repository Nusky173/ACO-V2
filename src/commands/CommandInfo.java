package commands;
import java.lang.reflect.Method;

import enums.*;

public class CommandInfo
{
	public Method method;
	public RoleType requiredRole;
	
	public CommandInfo(Method method,RoleType requiredRole)
	{
		this.method =  method;
		this.requiredRole =requiredRole;
	}
}
