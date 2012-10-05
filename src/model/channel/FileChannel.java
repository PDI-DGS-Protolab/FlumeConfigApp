package model.channel;

import java.io.FileOutputStream;
import java.io.IOException;

import model.enums.ChannelEnum;

public class FileChannel implements Channel{
	private String type="file";
	private String path;
	
	public FileChannel(String p){
		this.path=p;
	}
	
	public String getPath(){
		return this.path;
	}
	
	@Override
	public ChannelEnum getType() {
		// TODO Auto-generated method stub
		return ChannelEnum.File;
	}

	@Override
	public void writeMessage(FileOutputStream fos, int n, int nchannel) {
		String message;
		try {
			message = "agent" + n + ".channels.ch" + nchannel + ".type=FILE" + '\n';
			fos.write(message.getBytes());
	
			message = "agent" + n + ".channels.ch" + nchannel + ".path=" + this.getPath() + '\n';
			fos.write(message.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
