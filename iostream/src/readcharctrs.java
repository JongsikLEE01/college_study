import java.io.*;
public class readcharctrs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data;
		try {
			while((data=System.in.read()) >= 0) {
				System.out.write(data);
			}
		}catch(IOException ioe){
			System.out.println(ioe);
		}
		
	}
}
/* 2장 데이터를 송수신하는 단위는 바이트(8비트)
class OutputStream{
	void write(int a){...} -> 1바이트만큼만 데이터를 보냄
	void write(byte[] d){...} -> 바이트 배열로 전체 데이터를 보냄
 	void write(byte[] b, int start, int end){...} -> 바이트 배열로 start와 end에서 지정한 만큼만 데이터를 보냄
}
 
class InputStream{
 	int read(){...}  -> 외부 개체로부터 데이터를 받고 반환하기 위해 반환 자료형인 int를 사용
 	int read(byte[] a){....}  ->  바이트 배열로 전체 데이터를 받아 읽은 데이터의 수를 int에 반환
 	int read(byte[] a, int start, int end)  -> 바이트 배열로 start와 end에서 지정한 만큼만 데이터의 수를 받아 int에 반환
}
 
class FileoutputStream extends OutputStream{
	FiloutputStream(String fn){....} 인수로 지정된 파일을 저장
	void write(int a){...}
 	void write(byte[] d){...}
 	void write(byte[] b, int start, int end){...}
}

class FileinputStream extends InputStream{
	FileinputStream(String fn){....} 인수로 지정된 파일을 읽움
	int read(){...}
 	int read(byte[] a){....}
 	int read(byte[] a, int start, int end)
}


class DataOutputStream extends FilterOutputStream{
	DataOutputStream(OutputStream os){...}  ->  인수를 FileinputStream 객체로 받음
	void write(int a){...}
 	void write(byte[] d){...}
 	void write(byte[] b, int start, int end){...}
 	
 	// 데이터 타입별로 인수를 받아서 외부 디바이스에 전송
 	void writeUTF(String data){...}  ->  보낼 문자열을 인수로 받고 문자열을 utf방식으로 인코딩해 전송
 	void writeInt(int id){...}
 	void writeDouble(double dd){...}
 	....
}

class DataInputStream extends FilterInputStream{
	DataInputStream(InputStream os){...}  ->  인수를 FileinputStream 객체로 받음
	void read(int a){...}
 	void read(byte[] d){...}
 	void read(byte[] b, int start, int end){...}
 	
 	// 데이터 타입별로 인수를 받아서 저장
 	String readUTF(){...}
 	Int readInt(){...}
 	Double readDouble(){...}
 	....
}

// DataOutputStream 클래스를 이용해서 문자열 '안녕하세요'를 출력하고 "dosfile.txt"에 저장 및 출ㄹ력
FileOutputStream fos = new FileOutputStream("dosfile.txt");
DataOutputStream dos = new DataOutputStream(fos);
dos.writeUTF("안녕하세요.\r\n");
dos.writeInt(5000);

FileInputStream fis = new FileInputStream("dosfile.txt");
DataInputStream dis = new DataInputStream(fis);
String data = dis.readUTF();
System.out.println(data);
Int num = dis.readint();
System.out.println(num);

3장
buffered는 버퍼링을 주어 버퍼에 일정 이상의 데이터가 쌓이면 전송
file클래스는 파일을 저장
RandomAccessFile은 레코드 단위로 파일을 읽거나 쓰며 사용

4장
reader, writer는 16비트 단위(문자 단위)로 파일을 전송 및 저장
 */