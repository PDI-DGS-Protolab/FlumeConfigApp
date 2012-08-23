package model.channel;

import model.enums.ChannelEnum;

public class MemoryChannel implements Channel{
	public ChannelEnum getType(){
		return ChannelEnum.Memory;
	}
}
