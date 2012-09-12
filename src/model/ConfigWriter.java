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
		int sourceN=1;
		int sinkN=1;
		for (Agent a : agents) {
			try{
				Channel c=a.getChannel();
				List<Source> so=a.getSource();
				List<Sink> si=a.getSink();
				String message;
				if(c instanceof FileChannel){
					message="agent"+n+".channels.ch"+channel+".type=FILE"+'\n';
					fos.write(message.getBytes());
					message="agent"+n+".channels.ch"+channel+".path="+((FileChannel)c).getPath()+'\n';
				}else if(c instanceof MemoryChannel){
					message="agent"+n+".channels.ch"+channel+".type=memory"+'\n';
					fos.write(message.getBytes());
				}else if(c instanceof JdbcChannel){
					message="agent"+n+".channels.ch"+channel+".type=jdbc"+'\n';
					fos.write(message.getBytes());
				}				

				message = "agent"+n+".channels = ch"+channel+'\n';
				fos.write(message.getBytes());
				
				for (Source s : so) {

					if(s instanceof AvroSource){
						AvroSource as=(AvroSource)s;
						message = "agent"+n+".sources.source"+source+ ".type = avro"+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sources.source"+source+ ".bind ="+as.getHostname()+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sources.source"+source+ ".port ="+as.getPort()+'\n';
						fos.write(message.getBytes());
					}else if(s instanceof ExecSource){
						ExecSource es=(ExecSource)s;
						message = "agent"+n+".sources.source"+source+ ".type = exec"+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sources.source"+source+ ".command ="+es.getCommand()+'\n';
						fos.write(message.getBytes());
					}else if(s instanceof NetCatSource){
						NetCatSource as=(NetCatSource)s;
						message = "agent"+n+".sources.source"+source+ ".type = netcat"+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sources.source"+source+ ".bind ="+as.getHostname()+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sources.source"+source+ ".port ="+as.getPort()+'\n';
						fos.write(message.getBytes());
					}else if(s instanceof SeqSource){
						message = "agent"+n+".sources.source"+source+ ".type = seq"+'\n';
						fos.write(message.getBytes());
					}else if(s instanceof SyslogSource){
						SyslogSource as=(SyslogSource)s;
						message = "agent"+n+".sources.source"+source+ ".type = syslogtcp"+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sources.source"+source+ ".host ="+as.getHostname()+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sources.source"+source+ ".port ="+as.getPort()+'\n';
						fos.write(message.getBytes());
					}
					message="agent"+n+".sources.source"+source+++ ".channels = ch"+channel+'\n';
					fos.write(message.getBytes());
				}
				message="agent"+n+".sources =";
				for (int i = 1; i <= so.size(); i++) {
					message=message.concat("source"+sourceN+++" ");
				}
				message=message.concat("\n");
				fos.write(message.getBytes());

				for (Sink s : si) {
					if(s instanceof AvroSink){
						AvroSink as=(AvroSink)s;
						message = "agent"+n+".sinks.sink"+sink+".type = avro"+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sinks.sink"+sink+".hostname ="+as.getHost()+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sinks.sink"+sink+".port ="+as.getPort()+'\n';
						fos.write(message.getBytes());
					}else if(s instanceof HdfsSink){
						HdfsSink as=(HdfsSink)s;
						message = "agent"+n+".sinks.sink"+sink+".type = hdfs"+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sinks.sink"+sink+".hdfs.path ="+as.getPath()+'\n';
						fos.write(message.getBytes());
					}else if(s instanceof FileRollSink){
						message = "agent"+n+".sinks.sink"+sink+".type = FILE_ROLL"+'\n';
						fos.write(message.getBytes());
					}else if(s instanceof IrcSink){
						IrcSink as=(IrcSink)s;
						message = "agent"+n+".sinks.sink"+sink+".type = irc"+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sinks.sink"+sink+".hostname ="+as.getHost()+'\n';
						fos.write(message.getBytes());
						message = "agent"+n+".sinks.sink"+sink+".port ="+as.getPort()+'\n';
						fos.write(message.getBytes());
					}else if(s instanceof LoggerSink){
						message = "agent"+n+".sinks.sink"+sink+".type = logger"+'\n';
						fos.write(message.getBytes());
					}else if(s instanceof NullSink){
						message = "agent"+n+".sinks.sink"+sink+".type = null"+'\n';
						fos.write(message.getBytes());
					}
					message = "agent"+n+".sinks.sink"+sink+++".channel = ch"+channel+'\n';
					fos.write(message.getBytes());
				}
				
				message="agent"+n+".sinks =";
				for (int i = 1; i <= si.size(); i++) {
					message=message.concat("sink"+sinkN+++" ");
				}
				message=message.concat("\n");
				fos.write(message.getBytes());
				
				message="\n";
				fos.write(message.getBytes());
				n++;	
				channel++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setChanged();
		notifyObservers(MessagesCode.end);
	}
}
