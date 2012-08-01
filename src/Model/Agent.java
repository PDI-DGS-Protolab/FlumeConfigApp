package Model;

public class Agent {

	private String host;
	private int port;
	private Reliability secLevel;
	private Source source;
	private Sink sink;
	private Channel channel;
	
	public Agent (String host, int port){
		this.host=host;
		this.port=port;
	}
	
	public Agent(String host, int port, Source source, Sink sink){
		this.host=host;
		this.port=port;
		this.sink=sink;
		this.source=source;
	}

	public Agent(String host, int port, Source source, Sink sink, Reliability secLevel, Channel channel){
		this.channel=channel;
		this.secLevel=secLevel;
		this.host=host;
		this.port=port;
		this.sink=sink;
		this.source=source;
	}
}
