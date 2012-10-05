package model.source;

import java.io.FileOutputStream;

import model.enums.SourceEnum;

public interface Source {
	
	public SourceEnum getType();
	
	public void writeMessage(FileOutputStream fos, int n, int nsource);
	
}
