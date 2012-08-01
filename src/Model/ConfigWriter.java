package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Observable;

import Control.MessagesCode;

public class ConfigWriter extends Observable{
	
	private FileOutputStream fos;
	
	public ConfigWriter(){
		initConf();
	}

	@Override
	public void notifyObservers(Object obj) {
	}
	
	private void initConf(){
		String env=System.getenv("FLUME_CONF_DIR");
		if (env==null){
			notifyObservers(MessagesCode.fileNotFound);
		}else{
			initConf(env);
		}
	}
	
	public void initConf(String path){
		try {
			this.fos=new FileOutputStream(path+"flume.conf");
			notifyObservers(MessagesCode.startSetup);
		} catch (FileNotFoundException e) {
			notifyObservers(MessagesCode.fileNotFound);
		}
	}
	
	public void writeConfig(List<Agent> agents){
		
	}

	
	
}
