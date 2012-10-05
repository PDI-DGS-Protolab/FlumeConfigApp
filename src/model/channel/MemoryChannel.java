package model.channel;

import java.io.FileOutputStream;
import java.io.IOException;

import model.enums.ChannelEnum;

public class MemoryChannel implements Channel{
	public ChannelEnum getType(){
		return ChannelEnum.Memory;
	}

	@Override
	public void writeMessage(FileOutputStream fos, int n, int nchannel) {
		String message;
		try {
			message = "agent" + n + ".channels.ch" + nchannel + ".type=memory" + '\n';
			fos.write(message.getBytes());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
