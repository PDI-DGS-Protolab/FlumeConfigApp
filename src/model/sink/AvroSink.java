package model.sink;

import model.enums.SinkEnum;

public class AvroSink implements Sink{

	private String host;
	private int port;
	
	public AvroSink(String h, int p){
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
		return SinkEnum.Avro;
	}

}
