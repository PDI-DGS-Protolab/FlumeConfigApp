package model;

import model.enums.Channel;
import model.enums.Reliability;
import model.enums.Sink;
import model.enums.Source;

public class Agent {

	private String host;
	private int port;
	private Source source;
	private Sink sink;
	private Reliability secLevel;
	private Channel channel;
	
	public Agent(String host, int port) {
		this.host=host;
		this.port=port;
	}
	
	public Agent(String host, int port, Source source, Sink sink) {
		this.host=host;
		this.port=port;
		this.source=source;
		this.sink=sink;
	}

	public Agent(String host, int port, Source source, Sink sink, Reliability secLevel, Channel channel) {
		this.host=host;
		this.port=port;
		this.source=source;
		this.sink=sink;
		this.secLevel=secLevel;
		this.channel=channel;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public Source getSource() {
		return source;
	}

	public Sink getSink() {
		return sink;
	}

	public Reliability getSecLevel() {
		return secLevel;
	}

	public Channel getChannel() {
		return channel;
	}
	
}
