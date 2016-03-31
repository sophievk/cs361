import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ReferenceMonitor{
	// private Map object_lvl;
	// private Map subject_lvl;
	private ObjectManager manager = new ObjectManager();

	public ReferenceMonitor(){
		// object_lvl = new HashMap<String, SecurityLevel>();
		// subject_lvl = new HashMap<String, SecurityLevel>();
	}

	class ObjectManager{
		public List<Object> objects =  new ArrayList<Object>();

		public ObjectManager(){
		}

		public void createNewObject(String name, SecurityLevel lvl){
			Object o = new Object(name, lvl);
			//object_level.put(name,lvl);
			objects.add(o);
		}

		public boolean validObj(String name){
		if(getObj(name) != null){
			return true;
		}
		return false;	
		}

		public Object getObj(String name){
			for(Object o : objects){
				if((o.getName()).toLowerCase().compareTo(name)==0){
					return o;
				}
			}
			return null;	
		}

		//returns the value of the object
		public int read(String object){
			Object o = getObj(object);
			if(o != null){
				return o.getVal();
			}
			return 0;
		}

		//sets the value to the object
		public void write(String object, int val){
			Object o = getObj(object);
			if(o != null){
				o.setVal(val);
			}	
		}
	}

	public ObjectManager getManager(){
		return manager;
	}

	public int executeRead(InstructionObject ins, Subject s){
		Object obj = new Object();
		for(Object o : manager.objects){
			if((o.getName().toLowerCase()).compareTo(ins.getObject())==0){
				obj = o;
			}
		}
		if(s.getLevel().getLevel() >= obj.getLevel().getLevel()){
			return manager.read(ins.getObject());
		}
		return 0;
	}

	public int executeWrite(InstructionObject ins, Subject s){
		Object obj = new Object();
		for(Object o : manager.objects){
			if((o.getName().toLowerCase()).compareTo(ins.getObject())==0){
				obj = o;
			}
		}

		if((s.getLevel()).getLevel() <= (obj.getLevel()).getLevel()){
			manager.write(ins.getObject(), ins.getVal());
		}
		return 0;
	}

	public boolean isValid(InstructionObject ins){
		String act = ins.getAction();
		String sub = ins.getSubject();
		String obj = ins.getObject();
		int val = ins.getVal();
		if(act.compareTo("")==0 || sub.compareTo("")==0 || obj.compareTo("")==0 || val < 0){
			return false;
		}	
		return true;
	}	
	public int checkInstruction(InstructionObject ins, Subject s){
		if((ins.getAction()).compareTo("write")==0){
			if(isValid(ins)){
				return executeWrite(ins, s);
			}
		}
		else if((ins.getAction()).compareTo("read")==0){
			if(isValid(ins)){
				return executeRead(ins, s);
			}
		}
		return 0;
	}	
}
