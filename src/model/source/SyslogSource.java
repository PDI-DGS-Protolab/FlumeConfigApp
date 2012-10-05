package model.source;

import java.io.FileOutputStream;
import java.io.IOException;

import model.enums.SourceEnum;

public class SyslogSource implements Source{

	private String host;
	private int port;
	
	public SyslogSource(String h, int p){
		host=h;
		port=p;
	}
	
	public String getHostname() {
		return host;
	}
	public int getPort() {
		return port;
	}
	
	@Override
	public SourceEnum getType() {
		return SourceEnum.Syslog;
	}

	@Override
	public void writeMessage(FileOutputStream fos, int n, int nsource) {
		String message;
		try {
			message = "agent"+n+".sources.source"+nsource+ ".type = syslogtcp"+'\n';
			fos.write(message.getBytes());
			
			message = "agent"+n+".sources.source"+nsource+ ".host ="+this.getHostname()+'\n';
			fos.write(message.getBytes());
			
			message = "agent"+n+".sources.source"+nsource+ ".port ="+this.getPort()+'\n';
			fos.write(message.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
