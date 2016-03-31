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
		//public List<Object> objects =  new ArrayList<Object>();
		public Map<String, Object> objects = new HashMap<String, Object>();
		//try HashTable

		public ObjectManager(){
		}

		//creates a new object and adds it to the list
		public void createNewObject(String name, SecurityLevel lvl){
			Object o = new Object(name, lvl);
			objects.put(name, o);
			//objects.add(o);
		}

		//determines if the object is found in the list
		public boolean validObj(String name){
		if(getObj(name) != null){
			return true;
		}
		return false;
		}

		//returns the object
		public Object getObj(String name){
			return objects.get(name);
			// for(Object o : objects){
			// 	if((o.getName()).toLowerCase().compareTo(name)==0){
			// 		return o;
			// 	}
			// }
			// return null;
		}

		//creates a new object with the same SecurityLevel of the creating subject
		public void create(Subject s, String object){
			createNewObject(object, s.getLevel());
		}

		//removes the object from the list
		public boolean destroy(String object){
			// Object o = getObj(object);
			// return objects.remove(o);
			if(objects.containsKey(object)){
					objects.remove(object);
					return true;
			}
			return false;
		}

		//returns the value of the object
		public int read(String object){
			Object o = getObj(object);
			if(validObj(object)){
				return o.getVal();
			}
			return 0;
		}

		//sets the value to the object
		public void write(String object, int val){
			Object o = getObj(object);
			if(validObj(object)){
				o.setVal(val);
			}
		}
	}

	//returns the ObjectManager
	public ObjectManager getManager(){
		return manager;
	}

	//determines if the subject can read
	public boolean canRead(InstructionObject ins, Subject s){
		Object obj = manager.getObj(ins.getObject());
		if(obj != null && s.getLevel().getLevel() >= obj.getLevel().getLevel()){
			return true;
		}
		return false;
	}

	//determines if the subject can write
	public boolean canWrite(InstructionObject ins, Subject s){
		Object obj = manager.getObj(ins.getObject());
		if(obj!= null && s.getLevel().getLevel() <= obj.getLevel().getLevel()){
			return true;
		}
		return false;
	}

	//returns 1 if object created; otherwise, 0
	public int executeCreate(InstructionObject ins, Subject s){
		if(!manager.validObj(ins.getObject().toLowerCase())){
			manager.create(s, ins.getObject());
			return 1;
		}
		return 0;
	}

	//returns 1 if the object was destroyed; otherwise, 0
	public int executeDestroy(InstructionObject ins, Subject s){
		if(canWrite(ins, s)){
			if(manager.destroy(ins.getObject())){
				return 1;
			}
		}
		return 0;
	}

	//returns 0 if bits not full; otherwise, returns the value
	public int executeRun(Subject s){
		return s.run();
	}

	//determines the object and if it has the correct level; then calls read
	public int executeRead(InstructionObject ins, Subject s){
		if(canRead(ins, s)){
			return manager.read(ins.getObject());
		}
		return 0;
	}

	//determines the object and if it has the correct level; then calls write
	public int executeWrite(InstructionObject ins, Subject s){
		if(canWrite(ins, s)){
			manager.write(ins.getObject(), ins.getVal());
		}
		return 0;
	}

	//checks if it is a valid instruction set
	/*public boolean isValid(InstructionObject ins){
		String act = ins.getAction();
		String sub = ins.getSubject();
		String obj = ins.getObject();
		int val = ins.getVal();
		if(act.compareTo("")==0 || sub.compareTo("")==0 || obj.compareTo("")==0 || val < 0){
			return false;
		}
		return true;
	}*/

	public int checkInstruction(InstructionObject ins, Subject s){
		if((ins.getAction()).compareTo("write")==0){
			//if(isValid(ins)){
				return executeWrite(ins, s);
			//}
		}
		else if((ins.getAction()).compareTo("read")==0){
			//if(isValid(ins)){
				return executeRead(ins, s);
			//}
		}
		else if((ins.getAction()).compareTo("create")==0){
			//if(isValid(ins)){
				return executeCreate(ins, s);
			//}
		}
		else if((ins.getAction()).compareTo("destroy")==0){
			//if(isValid(ins)){
				return executeDestroy(ins, s);
			//}
		}
		else if((ins.getAction()).compareTo("run")==0){
			//if(isValid(ins)){
				return executeRun(s);
			//}
		}
		return 0;
	}
}
