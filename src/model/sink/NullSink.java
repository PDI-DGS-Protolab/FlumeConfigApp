package model.sink;

import java.io.FileOutputStream;
import java.io.IOException;

import model.enums.SinkEnum;

public class NullSink implements Sink{

	@Override
	public SinkEnum getType() {
		return SinkEnum.Null;
	}

	@Override
	public void writeMessage(FileOutputStream fos, int n, int nsink) {
		String message;
		try {
			message = "agent"+n+".sinks.sink"+nsink+".type = null"+'\n';
			fos.write(message.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
