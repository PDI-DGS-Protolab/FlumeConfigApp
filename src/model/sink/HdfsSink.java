package model.sink;

import java.io.FileOutputStream;
import java.io.IOException;

import model.enums.SinkEnum;

public class HdfsSink implements Sink{

	private String path;
	
	public HdfsSink(String p){
		this.path=p;
	}
	
	@Override
	public SinkEnum getType() {
		return SinkEnum.HDFS;
	}
	
	public String getPath(){
		return this.path;
	}

	@Override
	public void writeMessage(FileOutputStream fos, int n, int nsink) {
		String message;
		try {
			message = "agent"+n+".sinks.sink"+nsink+".type = hdfs"+'\n';
			fos.write(message.getBytes());
			
			message = "agent"+n+".sinks.sink"+nsink+".hdfs.path ="+this.getPath()+'\n';
			fos.write(message.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
