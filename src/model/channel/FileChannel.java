package model.channel;

import model.enums.ChannelEnum;

public class FileChannel implements Channel{
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

}
