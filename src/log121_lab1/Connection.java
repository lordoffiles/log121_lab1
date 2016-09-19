package log121_lab1;
import java.io.IOException;
import java.net.InetAddress;

/*
 * Demander pour documentation d'interface
 */
public interface Connection {
	public boolean open(InetAddress address, int port) throws IOException;
	
	public String status();
	
	public boolean get();
	
	public boolean close();
	
}
