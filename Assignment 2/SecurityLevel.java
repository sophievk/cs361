public class SecurityLevel{
	public static final SecurityLevel HIGH = new SecurityLevel(100);
	public static final SecurityLevel LOW = new SecurityLevel(0);

	private int level;
	private SecurityLevel(int lvl) {
		setLevel(lvl);
	}

	public void setLevel(int lvl){
		this.level = lvl;
	}

	public int getLevel(){
		return level;
	}
}