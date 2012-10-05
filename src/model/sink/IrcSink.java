package model.sink;

import java.io.FileOutputStream;
import java.io.IOException;

import model.enums.SinkEnum;

public class IrcSink implements Sink{

	private String host;
	private int port;
	
	public IrcSink(String h, int p){
		this.host=h;
		this.port=p;
	}
	
	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	@Override
	public SinkEnum getType() {
		return SinkEnum.Irc;
	}

	@Override
	public void writeMessage(FileOutputStream fos, int n, int nsink) {
		String message;
		try {
			message = "agent"+n+".sinks.sink"+nsink+".type = irc"+'\n';
			fos.write(message.getBytes());
			
			message = "agent"+n+".sinks.sink"+nsink+".hostname ="+this.getHost()+'\n';
			fos.write(message.getBytes());
			
			message = "agent"+n+".sinks.sink"+nsink+".port ="+this.getPort()+'\n';
			fos.write(message.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
