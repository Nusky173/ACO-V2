package commands;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * Représente annotation de commande (qui peut seulement être placé sur une méthode)
 * @author Skinz
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command 
{
	public String name();
    public String role();
}
