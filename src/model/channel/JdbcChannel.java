package model.channel;

import model.enums.ChannelEnum;

public class JdbcChannel implements Channel{
	@Override
	public ChannelEnum getType() {
		return ChannelEnum.JDBC;
	}

}
