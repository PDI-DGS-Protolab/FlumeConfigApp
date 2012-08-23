package model.source;

import model.enums.SourceEnum;

public class SeqSource implements Source{

	@Override
	public SourceEnum getType() {
		return SourceEnum.Seq;
	}

}
