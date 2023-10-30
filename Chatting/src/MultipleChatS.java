// Step 2
// 메시지를 이용하지 않고 다수의 클라이언트간의 체팅프로그램
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
         server = new ServerSocket(5000, 100);
         try {
            while(true) {
               sock = server.accept();
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
      cs.list.add(this);
      String clientdata;
      try {
         while((clientdata = input.readLine()) != null) {
            display.append(clientdata + "\r\n");
            int cnt = cs.list.size();
            for(int i=0; i<cnt; i++) { //모든 클라이언트에 데이터를 전송한다.
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