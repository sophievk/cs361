public class Subject{
	private String sname;
	private SecurityLevel slvl;
	private int temp;

	public Subject(String name, SecurityLevel lvl){
		setSName(name);
		setSLevel(lvl);
		temp = 0;
	}

	//sets subject's name
	public void setSName(String n){
		this.sname = n;
	}

	//sets subject's security level
	public void setSLevel(SecurityLevel l){
		this.slvl = l;
	}

	public void setTemp(int t){
		this.temp = t;
	}

	//returns subject's name
	public String getName(){
		return sname;
	}

	//returns subject's security level
	public SecurityLevel getLevel(){
		return slvl;
	}

	//returns temp
	public int getSTemp(){
		return temp;
	}
}