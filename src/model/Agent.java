package model;

import model.channel.Channel;
import model.enums.ChannelEnum;
import model.enums.Reliability;
import model.enums.SinkEnum;
import model.enums.SourceEnum;
import model.sink.Sink;
import model.source.AvroSource;
import model.source.Source;

public class Agent {

	private Source source;
	private Sink sink;

	private Channel channel;
	
	public Agent(Source so, Channel c, Sink si) {
		this.source=so;
		this.channel=c;
		this.sink=si;
	}

	public Source getSource() {
		return source;
	}

	public Sink getSink() {
		return sink;
	}

	public Channel getChannel() {
		return channel;
	}
	
}
