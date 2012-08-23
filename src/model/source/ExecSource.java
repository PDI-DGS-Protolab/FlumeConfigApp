package model.source;

import model.enums.SourceEnum;

public class ExecSource implements Source{

	private String command;
	
	public ExecSource(String command){
		this.command=command;
	}

	@Override
	public SourceEnum getType() {
		// TODO Auto-generated method stub
		return SourceEnum.Exec;
	}

	public String getCommand() {
		return command;
	}

}
