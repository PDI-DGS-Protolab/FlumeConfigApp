package model.sink;

import java.io.FileOutputStream;
import java.io.IOException;

import model.enums.SinkEnum;

public class LoggerSink implements Sink{

	@Override
	public SinkEnum getType() {
		// TODO Auto-generated method stub
		return SinkEnum.Logger;
	}

	@Override
	public void writeMessage(FileOutputStream fos, int n, int nsink) {
		String message;
		try {
			message = "agent"+n+".sinks.sink"+nsink+".type = logger"+'\n';
			fos.write(message.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
