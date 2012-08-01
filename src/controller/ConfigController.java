package controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import constants.MessagesCode;

import view.ConsoleView;

import model.Agent;
import model.ConfigWriter;

public class ConfigController implements Observer{

	private ConfigWriter cw;
	private ConsoleView view;
	
	public ConfigController(){
		cw = new ConfigWriter();
		view = new ConsoleView();
	}
	
	@Override
	public void update(Observable obs, Object param) {
		
		if (obs.equals(cw)) {
			if (param.equals(MessagesCode.fileNotFound)) {
				String path=view.fileNotFound();
				cw.initConf(path);
			} else if(param.equals(MessagesCode.startSetup)) {
				view.startSetup();
			}
			
		} else if (obs.equals(view)) {
			if (param instanceof List<?>) {
				cw.writeConfig((List<Agent>)param);
			}
		}
		
	}

}
