/*
 * Cours: LOG121
 * Session: A2016
 * Groupe: 02
 * Projet: Laboratoire 1
 * Étudiant(e)(s): Vincent Roy
 * Professeur: Vincent Lacasse
 * Num du fichier: Connection.java
 * Date création: 18-09
 * Date dern. modif.: 02-10
 */
package log121_lab1;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Interface for connecting to a server, sending commands to it and 
 * receiving responses.
 * @author Vincent Roy
 * @date 29/09/2015
 *
 */
public interface Connection {
	public boolean open(InetAddress address, int port) throws IOException;
	
	public String status();
	
	public String getResponse() throws IOException;
	
	public boolean close();
	
}
