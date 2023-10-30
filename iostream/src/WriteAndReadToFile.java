import java.io.*;

public class WriteAndReadToFile {
	static FileOutputStream fout;
	static FileInputStream fin;
	public static void main(String[] args) {
		try {
			int bytesRead;
			byte[] buffer = new byte[256];
			fout = new FileOutputStream("example1_9.txt",false);
			while((bytesRead = System.in.read(buffer)) >= 0) {
				fout.write(buffer, 0, bytesRead);
		//키보드로부터 데이터 입력-> 메모리 저장 -> 파일로 저장
			}
		// 파일의 내용을 읽어서 메모리에 저장하고 화면에 출력
			fin=new FileInputStream("example1_9.txt");
			while((bytesRead=fin.read(buffer))>=0) {
				System.out.write(buffer, 0, bytesRead);
			}
		}

		catch(IOException e) {
			System.err.println("스트림으로부터 데이터를 읽을 수 없습니다.");
		}finally {
			try {
				if(fout!=null) fout.close();
			}catch(IOException e) {}
		}
	}
}