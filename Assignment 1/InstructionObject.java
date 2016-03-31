public class InstructionObject{
	private String action;
	private String sub;
	private String obj;
	private int val;
	private static final String bad = "Bad Instruction";

	public InstructionObject(){
		setAction(""); 
		setSubject("");
		setObject("");
		setVal(-1);
	}

	public InstructionObject(String action, String subject, String object){
		setAction(action);
		setSubject(subject);
		setObject(object);
		setVal(0);
	}

	public InstructionObject(String action, String subject, String object, int value){
		setAction(action);
		setSubject(subject);
		setObject(object);
		setVal(value);
	}

	public void setAction(String a){
		this.action = a;
	}

	public String getAction(){
		return action;
	}

	public void setSubject(String s){
		this.sub = s;
	}

	public String getSubject(){
		return sub;
	}

	public void setObject(String o){
		this.obj = o;
	}

	public String getObject(){
		return obj;
	}

	public void setVal(int v){
		this.val = v;
	}

	public int getVal(){
		return val;
	}
}