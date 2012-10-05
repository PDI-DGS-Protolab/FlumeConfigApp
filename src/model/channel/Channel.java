package model.channel;

import java.io.FileOutputStream;

import model.enums.ChannelEnum;

public interface Channel {
	
	public ChannelEnum getType();
	
	public void writeMessage(FileOutputStream fos, int n, int nchannel);
	
}
