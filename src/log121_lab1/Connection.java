package log121_lab1;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Connection interface
 * @author Vincent Roy
 * @date 29/09/2015
 *
 */
public interface Connection {
	public boolean open(InetAddress address, int port) throws IOException;
	
	public String status();
	
	public String getResponse();
	
	public boolean close();
	
}
