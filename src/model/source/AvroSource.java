package model.source;

import java.io.FileOutputStream;
import java.io.IOException;

import model.enums.SourceEnum;

public class AvroSource implements Source{
	
	private String hostname;
	private int port;
	
	public AvroSource(String h, int p){
		hostname=h;
		port=p;
	}
	
	public String getHostname() {
		return hostname;
	}
	public int getPort() {
		return port;
	}
	@Override
	public SourceEnum getType() {
		return SourceEnum.Avro;
	}

	@Override
	public void writeMessage(FileOutputStream fos, int n, int nsource) {
		String message;
		try {
			message = "agent"+n+".sources.source"+ nsource + ".type = avro"+'\n';
			fos.write(message.getBytes());
			
			message = "agent"+n+".sources.source"+nsource+ ".bind ="+this.getHostname()+'\n';
			fos.write(message.getBytes());
			
			message = "agent"+n+".sources.source"+nsource+ ".port ="+this.getPort()+'\n';
			fos.write(message.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
