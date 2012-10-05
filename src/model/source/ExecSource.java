package model.source;

import java.io.FileOutputStream;
import java.io.IOException;

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

	@Override
	public void writeMessage(FileOutputStream fos, int n, int nsource) {
		String message;
		try {
			message = "agent"+n+".sources.source"+nsource+ ".type = exec"+'\n';
			fos.write(message.getBytes());
			
			message = "agent"+n+".sources.source"+nsource+ ".command ="+this.getCommand()+'\n';
			fos.write(message.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
