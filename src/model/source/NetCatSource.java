package model.source;

import model.enums.SourceEnum;

public class NetCatSource implements Source{
	private String hostname;
	private int port;
	
	public NetCatSource(String h, int p){
		hostname=h;
		port=p;
	}
	
	public String getHostname() {
		return hostname;
	}
	public int getPort() {
		return port;
	}
	@Override
	public SourceEnum getType() {
		return SourceEnum.Netcat;
	}
	
}
