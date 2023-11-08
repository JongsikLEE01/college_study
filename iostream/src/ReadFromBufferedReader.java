import java.awt.*;
import java.io.*;
import java.awt.event.*;
class ReadFromBufferedReader extends Frame implements ActionListener{
// BufferedWriter 객체를 이용해서 문자열 전송하는 방법
// 예를 들면, 파일로 전송한다.
// 첫번째, FileOutputStream 클래스로부터 객체를 만든다.
// FileOutputStream fout = new FileOutputStream("greeting.txt");
// 두번째, OutputStreamWriter 클래스를 이용해서 아래와 같이
// Outputtream 객체를 Writer 객체로 바꾸어주어야 한다.
// OutputStreamWriter osw = new OutputStreamWriter(fout);
// BufferedWriter bw = new BufferedWriter(osw);
/*
 FileOutputStream fout = new FileOutputStream("greeting.txt");
 OutputStreamWriter osw = new OutputStreamWriter(fout);
 BufferedWriter bw = new BufferedWriter(osw);
 bw.write("assdasfdsadf\r\n");
 
 FileInputStream fin = new FileInputStream("greeting.txt");
 InputStreamReader isr = new InputStreamReader(fin);
 BufferedReader br = new BufferedReader(isr);
 String d=br.readLine();
 	
 */
	
   
	static FileInputStream fin;
	static InputStreamReader isr;
	static BufferedReader br;
	Label lfile,ldata;
	TextField tfile;
	TextArea tdata;
	String filename,data;
	byte buffer[] =new byte[80];
	
	public ReadFromBufferedReader(String str) {
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
		
		
		tfile.addActionListener(this);
				
		addWindowListener(new WinListener());
	}
	public static void main(String args[]) {
		ReadFromBufferedReader text = new ReadFromBufferedReader("파일 출력");
		text.setSize(500,400);
		text.show();
	}
	public void actionPerformed(ActionEvent ae) {
		
	
		try {
			filename=tfile.getText();
			//data=tdata.getText();
			//buffer = data.getBytes();
			fin =new FileInputStream(filename);
			isr = new InputStreamReader(fin);
			br= new BufferedReader(isr);
			String d=br.readLine();
			tdata.setText(d);
			// 저장할 문자열을 입력하는 컴포넌트를 TextArea로 바꾸어서
			// 여러줄을 입력하고 입력한 여러줄을 파일로 저장하는 프로그램으로
			// 수정하시오.
			
		}catch(IOException e) {
			System.out.println(e.toString());
		}finally {
			try{
				fin.close();
				isr.close();
				br.close();
			}catch(IOException ie) {
				
			}
		}
	}
	class WinListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}
 }