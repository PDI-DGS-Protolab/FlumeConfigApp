package model.sink;

import model.enums.SinkEnum;

public class LoggerSink implements Sink{

	@Override
	public SinkEnum getType() {
		// TODO Auto-generated method stub
		return SinkEnum.Logger;
	}

}
