package model.sink;

import model.enums.SinkEnum;

public class NullSink implements Sink{

	@Override
	public SinkEnum getType() {
		return SinkEnum.Null;
	}

}
