package Control;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Model.Agent;
import Model.ConfigWriter;
import View.ConsoleView;

public class Controller implements Observer{

	private ConfigWriter cw;
	private ConsoleView view;
	
	public Controller(){
		cw = new ConfigWriter();
		view = new ConsoleView();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg0.equals(cw)){
			if(arg1.equals(MessagesCode.fileNotFound)){
				String path=view.fileNotFound();
				cw.initConf(path);
			}else if(arg1.equals(MessagesCode.startSetup)){
				view.startSetup();
			}
		}else if (arg0.equals(view)){
			if(arg1 instanceof List<?>){
				cw.writeConfig((List<Agent>)arg1);
			}
		}
		
	}

}
