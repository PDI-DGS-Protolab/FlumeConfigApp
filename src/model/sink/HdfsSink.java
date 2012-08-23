package model.sink;

import model.enums.SinkEnum;

public class HdfsSink implements Sink{

	private String path;
	
	public HdfsSink(String p){
		this.path=p;
	}
	
	@Override
	public SinkEnum getType() {
		return SinkEnum.HDFS;
	}
	
	public String getPath(){
		return this.path;
	}

}
