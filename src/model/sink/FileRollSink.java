package model.sink;

import java.io.FileOutputStream;
import java.io.IOException;

import model.enums.SinkEnum;

public class FileRollSink implements Sink{

	@Override
	public SinkEnum getType() {
		return SinkEnum.FRoll;
	}

	@Override
	public void writeMessage(FileOutputStream fos, int n, int nsink) {
		String message;
		try {
			message = "agent"+n+".sinks.sink"+nsink+".type = FILE_ROLL"+'\n';
			fos.write(message.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
