package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Observable;

import model.channel.*;
import model.sink.*;
import model.source.*;

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
		int sink=1;
		int source=1;
		int channel=1;

		for (Agent a : agents) {
			try{
				Channel c=a.getChannel();
				Source so=a.getSource();
				Sink si=a.getSink();
				String message;

				// The channel instance writes the proper message 
				c.writeMessage(fos, n, channel);

				message="agent"+n+".sources.source"+source+ ".channels = ch"+channel+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".sinks.sink"+sink+".channel = ch"+channel+'\n';
				fos.write(message.getBytes());
				message = "agent"+n+".channels = ch"+channel++ +'\n';
				fos.write(message.getBytes());

				// The source instance writes the proper message
				so.writeMessage(fos, n, source);

				message = "agent"+n+".sources = source"+source+++'\n';
				fos.write(message.getBytes());

				// The sink instance writes the proper message
				si.writeMessage(fos, n, sink);
				
				message = "agent"+n+".sinks = sink"+sink+++'\n';
				fos.write(message.getBytes());
				message=""+'\n';
				fos.write(message.getBytes());
				n++;		
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Notifying changes
		setChanged();
		notifyObservers(MessagesCode.end);
	}
}
