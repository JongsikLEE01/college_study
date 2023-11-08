import java.io.*;
public class ReadCharactersBlock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] buffer = new byte[80];
		int data;
		try {
			while((data=System.in.read(buffer)) >= 0) {
				System.out.write(buffer, 0, data);
			}
		}catch(IOException ioe){
			System.out.println(ioe);
		}
		
	}
}
