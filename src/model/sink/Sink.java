package model.sink;

import java.io.FileOutputStream;

import model.enums.SinkEnum;

public interface Sink {
	
	public SinkEnum getType();
	
	public void writeMessage(FileOutputStream fos, int n, int nsink);
	
}
