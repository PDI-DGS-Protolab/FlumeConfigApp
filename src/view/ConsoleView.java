package view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import model.Agent;
import model.channel.Channel;
import model.channel.FileChannel;
import model.channel.JdbcChannel;
import model.channel.MemoryChannel;
import model.sink.AvroSink;
import model.sink.FileRollSink;
import model.sink.HdfsSink;
import model.sink.IrcSink;
import model.sink.LoggerSink;
import model.sink.NullSink;
import model.sink.Sink;
import model.source.AvroSource;
import model.source.ExecSource;
import model.source.NetCatSource;
import model.source.SeqSource;
import model.source.Source;
import model.source.SyslogSource;


public class ConsoleView extends Observable {

	public String fileNotFound(){
		System.out.println("Config folder wasn't found. Please specify it: ");
		Scanner sc= new Scanner(System.in);
		return sc.nextLine();
	}
	
	public void startSetup(){
		List<Agent> agents=new LinkedList<Agent>();
		boolean advanced=true;
		Scanner sc= new Scanner(System.in);
		String opt;
		
		System.out.println("Introduce the number of agents: ");
		int n=Integer.valueOf(sc.nextLine());
		
		System.out.println("Do you wish to have an advanced config? [S/n] ");
		opt=sc.nextLine();
		if(opt.equalsIgnoreCase("n")) advanced=false;
		
		for (int i = 0; i < n; i++) {
			List<Source> source;
			List<Sink> sink;
			Channel channel;
			
			System.out.println("Configuring agent number "+(i+1));
			
			source=selectSource(sc);
			
			sink=selectSink(sc);
			
			if (advanced) {
				channel=selectChannel(sc);
			} else {
				channel=new MemoryChannel();
			}
			
			Agent a = new Agent(source,channel,sink);
			agents.add(a);			
		}	
		setChanged();
		notifyObservers(agents);		
	}
	
	
	private Channel selectChannel(Scanner sc){
		boolean ok=false;
		String opt;
		Channel channel=null;
		
		System.out.println("Introduce the desired Channel (press intro to select memory by default)");
		System.out.println("memory, file, jdbc, ptransaction, rmemory :");
		while(!ok){
			ok=true;
			opt=sc.nextLine();
			
			if (opt.equalsIgnoreCase("memory")){
				channel=new MemoryChannel();
			}
			else if (opt.equalsIgnoreCase("file")){
				System.out.println("Enter the path to the file in which to store the logs: ");
				String p=sc.nextLine();
				channel=new FileChannel(p);
			}
			else if (opt.equalsIgnoreCase("jdbc")){
				channel=new JdbcChannel();
			}
			else {
				System.out.println("Incorrect option. Please introduce a valid one: ");
				ok=false;
			}
		}
		
		return channel;
	}

	private List<Source> selectSource(Scanner sc){
		int n, number=1;;
		List<Source> sources=new ArrayList<Source>();
		System.out.println("Enter the number of Sources to configure: ");
		n=sc.nextInt();

		for (int i = 0; i < n; i++) {

			boolean ok=false;
			String opt;
			Source source=null;

			System.out.println('\n'+"Configuring Source "+number++ +'\n'+'\n');


			System.out.println("Enter the desired Source (press intro  to select avro by default)");
			System.out.println("Avro, Exec, Netcat, SGenerator, Syslog :"); 

			while(!ok){
				ok=true;
				opt=sc.nextLine();

				if (opt.equalsIgnoreCase("Avro")){
					System.out.println("Enter the hostname to bind to: ");
					String h=sc.nextLine();
					System.out.println("Enter the port number: ");
					int p=sc.nextInt();
					source=new AvroSource(h,p);
				}
				else if (opt.equalsIgnoreCase("Exec")){
					System.out.println("Enter the command to execute");
					String c=sc.nextLine();
					source=new ExecSource(c);
				}
				else if (opt.equalsIgnoreCase("Netcat")){
					System.out.println("Introduce the hostname to bind to: ");
					String h=sc.nextLine();
					System.out.println("Introduce the port number: ");
					int p=sc.nextInt();
					source=new NetCatSource(h,p);
				}
				else if (opt.equalsIgnoreCase("SGenerator")){
					source=new SeqSource();
				}
				else if (opt.equalsIgnoreCase("Syslog")){
					System.out.println("Introduce the hostname to bind to: ");
					String h=sc.nextLine();
					System.out.println("Introduce the port number: ");
					int p=sc.nextInt();
					source=new SyslogSource(h,p);
				}
				else {
					System.out.println("Incorrect option. Please introduce a valid one: ");
					ok=false;
				}
				if(ok)sources.add(source);
			}			
		}



		return sources;
	}

	private List<Sink> selectSink(Scanner sc){

		int n, number=1;;
		List<Sink> sinks=new ArrayList<Sink>();
		System.out.println("Enter the number of Sinks to configure: ");
		n=sc.nextInt();

		for (int i = 0; i < n; i++) {

			boolean ok=false;
			String opt;
			Sink sink=null;
			
			System.out.println("Configuring Source "+number++ +'\n'+'\n');

			System.out.println("Introduce the desired Sink (press intro to select avro by default)");
			System.out.println("HDFS, Logger, Avro, Irc, FRoll, Null, HBase :");

			while(!ok){
				opt=sc.nextLine();
				ok=true;
				if(opt.equalsIgnoreCase("HDFS")){
					System.out.println("Introduce the path in which to store the logs: ");
					String p=sc.nextLine();
					sink=new HdfsSink(p);
				}
				else if(opt.equalsIgnoreCase("Avro")){
					System.out.println("Introduce the hostname to send the logs to: ");
					String h=sc.nextLine();
					System.out.println("Introduce the port number: ");
					int p=sc.nextInt();
					sink=new AvroSink(h, p);
				}
				else if(opt.equalsIgnoreCase("FRoll")){
					sink=new FileRollSink();
				}
				else if(opt.equalsIgnoreCase("IRC")){
					System.out.println("Introduce the hostname to send the logs to: ");
					String h=sc.nextLine();
					System.out.println("Introduce the port number: ");
					int p=sc.nextInt();
					sink=new IrcSink(h, p);
				}
				else if(opt.equalsIgnoreCase("Logger")){
					sink=new LoggerSink();
				}
				else if(opt.equalsIgnoreCase("Null")){
					sink=new NullSink();
				}
				else{
					System.out.println("Incorrect option. Please introduce a valid one: ");
					ok=false;
				}
				if(ok)sinks.add(sink);
			}
		}

		return sinks;
	}
	
	public void end(){
		System.out.println("Configuration successfully created");
	}
	
}
