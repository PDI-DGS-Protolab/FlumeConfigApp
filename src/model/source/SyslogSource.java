package model.source;

import model.enums.SourceEnum;

public class SyslogSource implements Source{

	private String host;
	private int port;
	
	public SyslogSource(String h, int p){
		host=h;
		port=p;
	}
	
	public String getHostname() {
		return host;
	}
	public int getPort() {
		return port;
	}
	
	@Override
	public SourceEnum getType() {
		return SourceEnum.Syslog;
	}
	
}
