package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Observable;

import constants.MessagesCode;

public class ConfigWriter extends Observable{
	
	private FileOutputStream fos;
	
	public ConfigWriter() {
	}

	public void initConf() {
		String env = System.getenv("FLUME_CONF_DIR");
		
		if (env == null) {
			setChanged();
			notifyObservers(MessagesCode.fileNotFound);
		} else {
			initConf(env);
		}
	}
	
	public void initConf(String path){
		try {
			this.fos = new FileOutputStream(path + "/flume.conf");
			setChanged();
			notifyObservers(MessagesCode.startSetup);
		} catch (FileNotFoundException e) {
			setChanged();
			notifyObservers(MessagesCode.fileNotFound);
		}
	}
	
	public void writeConfig(List<Agent> agents){
		int n=1;
		for (Agent agent : agents) {
			try {
				// TODO Write on the config file
				String message = "agent"+n+".channels.ch1.type = "+ agent.getChannel().name()+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".sources.source1.channels = ch1"+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".sources.source1.type = "+agent.getSource().name()+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".sources.source1.bind ="+agent.getHost()+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".sources.source1.port ="+agent.getPort()+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".sinks.sink1.channel = ch1"+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".sinks.sink1.type = "+agent.getSink().name()+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".sources.source1.channels = ch1"+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".channels = ch1"+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".sources = source1"+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".sinks = sink1"+'\n';
				fos.write(message.getBytes());
				n++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
