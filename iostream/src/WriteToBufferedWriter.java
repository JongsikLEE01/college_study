import java.awt.*;
import java.awt.event.*;
import java.io.*;
class WriteToBufferedWriter extends Frame implements KeyListener{
// BufferedWriter 객체를 이용해서 문자열 전송하는 방법
// 예를 들면, 파일로 전송한다.
// 첫번째, FileOutputStream 클래스로부터 객체를 만든다.
// FileOutputStream fout = new FileOutputStream("greeting.txt");
// 두번째, OutputStreamWriter 클래스를 이용해서 아래와 같이
// Outputtream 객체를 Writer 객체로 바꾸어주어야 한다.
// OutputStreamWriter osw = new OutputStreamWriter(fout);
// BufferedWriter bw = new BufferedWriter(osw);
/*
202005040 B반 이종식
파일쓰기
FileOutputStrem fout = new FileOutputStrem("greeting.txt");
OutputStreamWriter osw = new OutputStreamWriter(fout, "KSC5601");
BufferedWriter bew = new BufferedWriter(osw);
bw.write("Hello\n");
bw.flush();

파일 읽기
FileInputStrem fin = new FileInputStrem("greeting.txt");
InputStreamReader isr = new InputStreamReader(fin, "KSC5601");
BufferedReader br = new BufferedReader(isr);
String s=br.readLine();
*/
	static FileOutputStream fout;
	static OutputStreamWriter osw;
	static BufferedWriter bw;
	Label lfile,ldata;
	TextField tfile;
	TextArea tdata;
	String filename,data;
	byte buffer[] =new byte[80];
	
	public WriteToBufferedWriter(String str) {
		super(str);
		setLayout(new FlowLayout());
		lfile = new Label("파일이름");
		add(lfile);
		tfile = new TextField(20);
		add(tfile);
		ldata = new Label("대화말");
		add(ldata);
		tdata = new TextArea();
		add(tdata);
		
		tdata.addKeyListener(this);
		
		addWindowListener(new WinListener());
	}
	public static void main(String args[]) {
		WriteToBufferedWriter text = new WriteToBufferedWriter("파일저장");
		text.setSize(500,300);
		text.show();
	}
	class WinListener extends WindowAdapter{
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		try {
			filename=tfile.getText();
			data=tdata.getText();
			//buffer = data.getBytes();
			fout =new FileOutputStream(filename);
			osw = new OutputStreamWriter(fout);
			bw= new BufferedWriter(osw);
			//텍스트필드 여러줄을 입력하도록 변경
			bw.write(data+"\n");
			bw.flush();
		}catch(IOException e) {
			System.out.println(e.toString());
		}finally {
			try{
				fout.close();
				osw.close();
				bw.close();
			}catch(IOException ie) {
				
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
 }
