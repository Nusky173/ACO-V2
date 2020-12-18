package commands;
import java.lang.reflect.Method;

import enums.*;

/**
 * Représente une commande et sa méthode qui lui est associée
 * @author Skinz
 *
 */
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
