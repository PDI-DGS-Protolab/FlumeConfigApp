package model;

import java.util.List;

import model.channel.Channel;
import model.sink.Sink;
import model.source.Source;

public class Agent {

	private List<Source> source;
	private List<Sink> sink;

	private Channel channel;
	
	public Agent(List<Source> so, Channel c, List<Sink> si) {
		this.source=so;
		this.channel=c;
		this.sink=si;
	}

	public List<Source> getSource() {
		return source;
	}

	public List<Sink> getSink() {
		return sink;
	}

	public Channel getChannel() {
		return channel;
	}
	
}
