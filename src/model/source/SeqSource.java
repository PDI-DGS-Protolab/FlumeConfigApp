package model.source;

import java.io.FileOutputStream;
import java.io.IOException;

import model.enums.SourceEnum;

public class SeqSource implements Source{

	@Override
	public SourceEnum getType() {
		return SourceEnum.Seq;
	}

	@Override
	public void writeMessage(FileOutputStream fos, int n, int nsource) {
		String message;
		try {
			message = "agent"+n+".sources.source"+nsource+ ".type = seq"+'\n';
			fos.write(message.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
