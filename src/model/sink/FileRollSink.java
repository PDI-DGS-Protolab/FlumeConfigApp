package model.sink;

import model.enums.SinkEnum;

public class FileRollSink implements Sink{

	@Override
	public SinkEnum getType() {
		return SinkEnum.FRoll;
	}
	
}
