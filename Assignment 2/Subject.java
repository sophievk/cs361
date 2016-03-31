import java.util.BitSet;

public class Subject{
	private String sname;
	private SecurityLevel slvl;
	private int temp;
	private StringBuilder bits;
	// private BitSet bits;
	// private int bitCnt;

	public Subject(String name, SecurityLevel lvl){
		setSName(name);
		setSLevel(lvl);
		temp = 0;
		bits = new StringBuilder();
		// bits = new BitSet(8);
		// bitCnt = 0;
	}

	//sets subject's name
	public void setSName(String n){
		this.sname = n;
	}

	//sets subject's security level
	public void setSLevel(SecurityLevel l){
		this.slvl = l;
	}

	//sets the temp value
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

	//returns 0 if do not have 8 bits, otherwise returns byte
	// public int run(){
	// 	if(temp == 1){
	// 		bits.set(bitCnt);
	// 	}
	// 	if(bitCnt == 7){
	// 		String b = "";
	// 		for(int i = 0; i < 8; i++){
	// 			if(bits.get(i)){
	// 				b+="1";
	// 			}
	// 			else{
	// 				b+="0";
	// 			}
	// 		}
	// 		int val = Byte.parseByte(b, 2);
	// 		bits.clear();
	// 		bitCnt = 0;
	// 		return val;
	// 	}
	// 	bitCnt++;
	// 	return 0;
	// }
	public int run(){
		bits.append(temp);
		if(bits.length() == 8){
			int val = Byte.parseByte(bits.toString(), 2);
			bits.delete(0, bits.length());
			return val;
		}
		return 0;
	}
}
