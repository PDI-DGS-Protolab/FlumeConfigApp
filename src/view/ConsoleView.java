package view;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import model.Agent;
import model.enums.Channel;
import model.enums.Reliability;
import model.enums.Sink;
import model.enums.Source;


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
		
		System.out.println("Introduce the number of agents: ");
		int n=sc.nextInt();
		
		System.out.println("Do you wish to have an advanced config? [S/n] ");
		String opt=sc.nextLine();
		if(opt.equalsIgnoreCase("n")) advanced=false;
		
		int port;
		for (int i = 0; i < n; i++) {
			String host;
			Source source;
			Sink sink;
			Channel channel;
			Reliability rLevel;
			
			System.out.println("Introduce the hostname: ");
			host=sc.nextLine();

			System.out.println("Introduce the port number: ");
			port=sc.nextInt();

			source=selectSource(sc);
			sink=selectSink(sc);
			
			if (advanced) {
				channel=selectChannel(sc);
				rLevel=selectLevel(sc);
			} else {
				channel=Channel.Memory;
				rLevel=Reliability.DFO;
			}
			
			Agent a = new Agent(host,port,source,sink,rLevel,channel);
			agents.add(a);			
		}	

	}
	
	
	private Channel selectChannel(Scanner sc){
		boolean ok=false;
		String opt;
		Channel channel=null;
		
		System.out.println("Introduce the desired Channel");
		System.out.println("memory, file, jdbc, ptransaction, rmemory: ");
		while(!ok){
			ok=true;
			opt=sc.nextLine();
			
			if (opt.equalsIgnoreCase("memory")) channel=Channel.Memory;
			else if (opt.equalsIgnoreCase("file")) channel=Channel.File;
			else if (opt.equalsIgnoreCase("jdbc")) channel=Channel.JDBC;
			else if (opt.equalsIgnoreCase("ptransaction")) channel=Channel.PTransaction;
			else if (opt.equalsIgnoreCase("rmemory")) channel=Channel.RMemory;
			else {
				System.out.println("Incorrect option. Please introduce a valid one: ");
				ok=false;
			}
		}
		
		return channel;
	}
	
	private Source selectSource(Scanner sc){
		boolean ok=false;
		String opt;
		Source source=null;
		
		System.out.println("Introduce the desired Source: ");
		System.out.println("Avro, Exec, Netcat, SGenerator, Syslog"); 
		
		while(!ok){
			ok=true;
			opt=sc.nextLine();
			
			if (opt.equalsIgnoreCase("Avro")) source=Source.Avro;
			else if (opt.equalsIgnoreCase("Exec")) source=Source.Exec;
			else if (opt.equalsIgnoreCase("Netcat")) source=Source.Netcat;
			else if (opt.equalsIgnoreCase("SGenerator")) source=Source.SGenerator;
			else if (opt.equalsIgnoreCase("Syslog")) source=Source.Syslog;
			else {
				System.out.println("Incorrect option. Please introduce a valid one: ");
				ok=false;
			}
		}
		
		return source;
	}
	
	private Sink selectSink(Scanner sc){
		boolean ok=false;
		String opt;
		Sink sink=null;
		
		System.out.println("Introduce the desired Sink: ");
		System.out.println("HDFS, Logger, Avro, Irc, FRoll, Null, HBase,");
		
		while(!ok){
			opt=sc.nextLine();
			
			if(opt.equalsIgnoreCase("HDFS")) sink=Sink.HDFS;
			else if(opt.equalsIgnoreCase("Avro")) sink=Sink.Avro;
			else if(opt.equalsIgnoreCase("FRoll")) sink=Sink.FRoll;
			else if(opt.equalsIgnoreCase("HBase")) sink=Sink.HBase;
			else if(opt.equalsIgnoreCase("IRC")) sink=Sink.Irc;
			else if(opt.equalsIgnoreCase("Logger")) sink=Sink.Logger;
			else if(opt.equalsIgnoreCase("Null")) sink=Sink.Null;
		}
		
		return sink;
	}
	
	private Reliability selectLevel(Scanner sc){
		boolean ok=false;
		String opt;
		Reliability rLevel=null;
		
		System.out.println("Introduce the desired reliability level: ");
		System.out.println("BE, DFO, E2E");
		
		while (!ok) {
			ok=true;
			opt=sc.nextLine();
			
			if (opt.equalsIgnoreCase("be"))rLevel=Reliability.BE;
			else if (opt.equalsIgnoreCase("e2e"))rLevel=Reliability.E2E;
			else if (opt.equalsIgnoreCase("dfo"))rLevel=Reliability.DFO;
			else {
				System.out.println("Incorrect option. Please introduce a valid one: ");
				ok=false;
			}
		}
		
		return rLevel;
	}

	@Override
	public void notifyObservers(Object obj) {
	}
	
}
