import java.io.*;
import java.util.*;

public class CovertChannel{

	public static void main(String[] args) throws IOException{
		SecureSystem sys = new SecureSystem();

		SecurityLevel low = SecurityLevel.LOW;
		SecurityLevel high = SecurityLevel.HIGH;

		sys.createSubject("lyle", low);
		sys.createSubject("hal", high);

		if(0 < args.length){
			//non-verbose mode
			if(args.length == 1){
				String filename = args[0];
				Scanner in = new Scanner(new File(filename));
				FileWriter fw = new FileWriter(filename+".out");	

				final long startTime = System.currentTimeMillis();
				while(in.hasNext()){ //the file has more
					String line = in.nextLine();
					ByteArrayInputStream str = new ByteArrayInputStream(line.getBytes());
					int b; //one byte

					while((b = str.read()) != -1){ //until end of stream
						StringBuilder bits = new StringBuilder(Integer.toBinaryString(b));
						//if the string does not have 8 characters, add 0s to the beginning
						if(bits.length()<8){
							while(bits.length() != 8){
								bits.insert(0, 0);
							}
						}

						for(int i = 0; i < bits.length(); i++){
							char bit = bits.charAt(i);
							Subject s;

							if(bit == '0'){
								s = sys.getSub("hal");
								sys.monitor.checkInstruction(new InstructionObject("create","hal","obj"), s);
							}

							s = sys.getSub("lyle");
							sys.monitor.checkInstruction(new InstructionObject("create","lyle","obj"), s);
							sys.monitor.checkInstruction(new InstructionObject("write","lyle","obj",1), s);
							s.setTemp(sys.monitor.checkInstruction(new InstructionObject("read","lyle","obj"), s));
							sys.monitor.checkInstruction(new InstructionObject("destroy","lyle","obj"), s);
							int ch = sys.monitor.checkInstruction(new InstructionObject("run","lyle"), s);
							if(ch != 0){
								fw.write((char)ch);
							}
						}
					}
					fw.write("\n");
				}
				final long endTime = System.currentTimeMillis();
				System.out.println("Total time: " + (endTime - startTime));
				fw.close();
			}
			//verbose mode
			else if(args.length == 2){
				String filename = args[1];
				Scanner in = new Scanner(new File(filename));
				FileWriter out = new FileWriter(filename+".out");
				FileWriter log = new FileWriter("log");

				while(in.hasNext()){ //the file has more
					String line = in.nextLine();
					ByteArrayInputStream str = new ByteArrayInputStream(line.getBytes());
					int b; //one byte

					while((b = str.read()) != -1){ //until end of stream
						StringBuilder bits = new StringBuilder(Integer.toBinaryString(b));
						//if the string does not have 8 characters, add 0s to the beginning
						if(bits.length()<8){
							while(bits.length() != 8){
								bits.insert(0, 0);
							}
						}

						for(int i = 0; i < bits.length(); i++){
							char bit = bits.charAt(i);
							Subject s;
							InstructionObject ins;

							if(bit == '0'){
								s = sys.getSub("hal");

								ins = new InstructionObject("create","hal","obj");
								sys.monitor.checkInstruction(ins,s);
								log.write(ins.toString());
							}
							
							s = sys.getSub("lyle");

							ins = new InstructionObject("create","lyle","obj");
							sys.monitor.checkInstruction(ins, s);
							log.write(ins.toString());

							ins = new InstructionObject("write","lyle","obj",1);
							sys.monitor.checkInstruction(ins, s);
							log.write(ins.toString());

							ins = new InstructionObject("read","lyle","obj");
							s.setTemp(sys.monitor.checkInstruction(ins, s));
							log.write(ins.toString());

							ins = new InstructionObject("destroy","lyle","obj");
							sys.monitor.checkInstruction(ins, s);
							log.write(ins.toString());

							ins = new InstructionObject("run","lyle");
							int ch = sys.monitor.checkInstruction(ins, s);
							log.write(ins.toString());
							if(ch != 0){
								out.write((char)ch);
							}
						}
					}
					out.write("\n");
				}
				log.close();
				out.close();
			}
		}
	}
}