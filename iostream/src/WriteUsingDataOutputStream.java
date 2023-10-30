import java.io.*;
public class WriteUsingDataOutputStream {
	static FileOutputStream fos;
	static DataOutputStream dos;
	static DataInputStream dis;
	static FileInputStream fis;
	public static void main(String[] args) {
		try {
			// DataOutputStream 클래스이 writeUTF() 함수를 이용해서
			// 문자열 "안녕하세요.. 좋은 아침입니다."를
			// woobin.txt 파일로 저장하세요.
			fos = new FileOutputStream("woobin.txt");
			dos=new DataOutputStream(fos);
			dos.writeUTF("안녕하세요.. 좋은 아침입니다.\r\n");
			dos.writeInt(3700);
			//dos.writeUTF("\r\n");
			dos.writeDouble(27.88);
			
			fis = new FileInputStream("woobin.txt");
			dis=new DataInputStream(fis);
			String text=dis.readUTF();
			System.out.println(text);
			int dtata=dis.readInt();
			System.out.println(dtata);
			double xdata=dis.readDouble();
			System.out.println(xdata);
				
			
		}catch(IOException e) {
			System.err.println("스트림으로부터 데이터를 읽을 수 없습니다.");
		}finally {
			try {
				if(dos!=null) dos.close();
			}catch(IOException e) {}
		}
	}
}
