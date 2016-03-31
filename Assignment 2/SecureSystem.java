import java.io.*;
import java.util.*;

public class SecureSystem{
	//public static List<Subject> subjects = new ArrayList<Subject>();
	public static Map<String, Subject> subjects = new HashMap<String, Subject>();
	public static ReferenceMonitor monitor = new ReferenceMonitor();
	public static ReferenceMonitor.ObjectManager manager = monitor.getManager();

	public void createSubject(String name, SecurityLevel lvl){
		Subject s = new Subject(name, lvl);
		subjects.put(name, s);
		//subjects.add(s);
	}

	public static boolean validSub(String name){
		if(getSub(name) != null){
			return true;
		}
		return false;
	}

	public static Subject getSub(String name){
		return subjects.get(name);
		// for(Subject s : subjects){
		// 	if((s.getName()).toLowerCase().compareTo(name)==0){
		// 		return s;
		// 	}
		// }
		// return null;
	}

	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}

	// public static void printState(InstructionObject ins){
	// 	if((ins.getAction()).compareTo("write")==0){
	//  	System.out.println(ins.getSubject() + " writes value " + ins.getVal() + " to "+ ins.getObject());
	// 	}
	// 	else if((ins.getAction()).compareTo("read")==0){
	// 		System.out.println(ins.getSubject() + " reads " + ins.getObject());
	// 	}
	// 	else{
	// 		System.out.println("Bad Instruction");
	// 	}
	//  	System.out.println("The current state is:");
	//  	for(Object o : manager.objects){
	//  		System.out.println(o.getName()+" has value: "+o.getVal());
	//  	}
	// 	for(Subject s : subjects){
	// 		System.out.println(s.getName()+" has recently read: "+s.getSTemp());
	// 	}
	// 	System.out.println("\n");
	// }

	/*public static void main(String[] args) throws IOException{
		SecureSystem sys = new SecureSystem();

		SecurityLevel low = SecurityLevel.LOW;
		SecurityLevel high = SecurityLevel.HIGH;

		sys.createSubject("Lyle", low);
		sys.createSubject("Hal", high);

		manager.createNewObject("Lobj", low);
		manager.createNewObject("Hobj", high);

		if(0 < args.length){
			String filename = args[0];
			File file = new File(filename);
			Scanner in = new Scanner(file);
			InstructionObject ins = new InstructionObject();

			System.out.println("Reading from file: " + filename + "\n");

			while(in.hasNext()){
				String text = in.nextLine().toLowerCase();
				String[] line = text.split("\\s+");
				String subject = "";
				String object = "";
				int val = 0;
				if(line[0].equals("write")){
					if(line.length == 4){
						subject = line[1];
						object = line[2];
						if(isInteger(line[3])){
							val = Integer.parseInt(line[3]);
							ins = new InstructionObject(line[0], subject, object, val);
						}
						else{
							ins = new InstructionObject();
						}
					}
					else{
						ins = new InstructionObject();
					}
				}
				else if(line[0].equals("read")){
					if(line.length == 3){
						subject = line [1];
						object = line[2];
						ins = new InstructionObject(line[0], subject, object);
					}
					else{
						ins = new InstructionObject();
					}
				}
				else{
					ins = new InstructionObject();
				}

				Subject s = getSub(subject);
				if(validSub(subject) && manager.validObj(object)){
					if(ins.getAction().equals("read")){
						s.setTemp(monitor.checkInstruction(ins, s));
					}
					else{
						monitor.checkInstruction(ins, s);
					}
				}
				else{
					ins = new InstructionObject();
					monitor.checkInstruction(ins, s);
				}
				printState(ins);

			}
			in.close();
		}
	}*/
}
