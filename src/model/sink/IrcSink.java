package model.sink;

import model.enums.SinkEnum;

public class IrcSink implements Sink{

	private String host;
	private int port;
	
	public IrcSink(String h, int p){
		this.host=h;
		this.port=p;
	}
	
	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	@Override
	public SinkEnum getType() {
		return SinkEnum.Irc;
	}

}
