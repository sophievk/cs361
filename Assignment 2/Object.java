public class Object{
	private String oname;
	private SecurityLevel olvl;
	private int val;

	public Object(){
	}

	public Object(String name, SecurityLevel lvl){
		setOName(name);
		setLevel(lvl);
		val = 0;
	}

	//sets the name of the object
	public void setOName(String n){
		this.oname = n;
	}

	//sets the object's security level
	public void setLevel(SecurityLevel l){
		this.olvl = l;
	}

	//sets the value of the object
	public void setVal(int v){
		this.val = v;
	}

	//returns the name of the object
	public String getName(){
		return oname;
	}

	//returns the object's security level
	public SecurityLevel getLevel(){
		return olvl;
	}

	//returns the value of the object
	public int getVal(){
		return val;
	}
}