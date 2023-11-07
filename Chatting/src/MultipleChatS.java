// Step 2
// 메시지를 이용하지 않고 다수의 클라이언트간의 체팅프로그램
// 서버소켓 객체를 만들어 클라이언트의 접속대기, 클라이언트가 접속 요청할 경우 소켓을 만들어 클라이언트와 연결
// 서버는 클라이언트1에게 받은 데티러를 다른 클라이언트 프로그램에게 모두 전송(run함수)
// 다른 클라이언트가 접속해도 같은 run함수를 이용하지만 run함수는 각자 다른 프로그램처럼 독립적으로 구동됨
// 이처럼 같은 기능을 독립적으로 이용할 경우 스레드 함수를 이용 (스레드 -> 현재 실행되는 프로그램, 즉 메인 함수)
// class 함수이름 extends Thread{}
// 추가할 기능) 연결된 총 클라이언트를 출력 -> // list, 즉 클라이언트가 저장된 리스트 객체에 size()를 이용해 연결된 클라이언트의 갯수를 출력
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class MultipleChatS extends Frame {
   TextArea display;
   Label info;
   String clientdata = "";
   String serverdata = "";
   List<MultipleThread> list;
   public MultipleThread SThread;
   
   public MultipleChatS() {
      super("서버");
      info = new Label();
      add(info, BorderLayout.CENTER);
      display = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
      display.setEditable(false);
      add(display, BorderLayout.SOUTH);
      addWindowListener(new WinListener());
      setSize(400,300);
      setVisible(true);
   }
	
   public void runServer() {
      ServerSocket server;
      Socket sock;
      MultipleThread SThread;
      try {
         list = new ArrayList<MultipleThread>(); 
         server = new ServerSocket(5000, 100);  // 서버소켓 객체 선언
         try {
            while(true) {
               sock = server.accept(); // 접속 요청 대기
               SThread = new MultipleThread(this, sock, display, info, serverdata);
               SThread.start();
               info.setText(sock.getInetAddress().getHostName() + " 서버는 클라이언트와 연결됨");
            }
         } catch(IOException ioe) {
            server.close();
            ioe.printStackTrace();
         }
      } catch(IOException ioe) {
         ioe.printStackTrace();
      }
   }
		
   public static void main(String args[]) {
      MultipleChatS s = new MultipleChatS();
      s.runServer();
   }
	
   class WinListener extends WindowAdapter {
      public void windowClosing(WindowEvent e) {
         System.exit(0);
      }
   }
}
// 아래 클래스의 상위 클래스는 스레드
class MultipleThread extends Thread {
   Socket sock;
   InputStream is;
   InputStreamReader isr;
   BufferedReader input;
   OutputStream os;
   OutputStreamWriter osw;
   BufferedWriter output;
   TextArea display;
   Label info;
   TextField text;
   String serverdata = "";
   MultipleChatS cs;
	
   public MultipleThread(MultipleChatS c, Socket s, TextArea ta, Label l, String data) {
      sock = s;
      display = ta;
      info = l;
      serverdata = data;
      cs = c;
      try {
         is = sock.getInputStream();
         isr = new InputStreamReader(is);       
         input = new BufferedReader(isr);
         os = sock.getOutputStream();
         osw = new OutputStreamWriter(os);
         output = new BufferedWriter(osw);
      } catch(IOException ioe) {
         ioe.printStackTrace();
      }
   }
   public void run() { 
      cs.list.add(this);  // 리스트객체에 연결된 클라이언트 저장
      String clientdata;
      display.setText(cs.list.size() +"개의 클라이언트와 접속됨\n"); // 현재 접속된 클라이언트의 TextArea에 출력
      try {
         while((clientdata = input.readLine()) != null) {  //대화말을 읽어서
            display.append(clientdata + "\r\n"); // 텍스트를 출력
            int cnt = cs.list.size(); //연결된 클라이언트를 확인
            for(int i=0; i<cnt; i++) { //연결된 모든 클라이언트에 데이터를 모두 전송한다.
               MultipleThread SThread = (MultipleThread)cs.list.get(i);
               SThread.output.write(clientdata + "\r\n");
               SThread.output.flush();
            }
         }
      } catch(IOException e) {
         e.printStackTrace();
      }
      cs.list.remove(this); //리스트에서 close된 클라이언트를 지운다.
      try{
         sock.close();   //소켓닫기
      }catch(IOException ea){
         ea.printStackTrace();
      }
   }
}