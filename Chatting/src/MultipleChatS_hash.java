// Step 2
// 메시지를 이용하지 않고 다수의 클라이언트간의 체팅프로그램
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class MultipleChatS_hash extends Frame {
   TextArea display;
   Label info;
   String clientdata = "";
   String serverdata = "";
   public MultipleThreadHash SThread;
   int count = 0;
   Hashtable<String,MultipleThreadHash> hs ;
   public MultipleChatS_hash() {
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
      MultipleThreadHash SThread;
      hs = new Hashtable<String,MultipleThreadHash>();
      try {
         server = new ServerSocket(5000, 100);
         try {
            while(true) {
               sock = server.accept();
               SThread = new MultipleThreadHash(this, sock, display, info, serverdata, count);
               SThread.start();
               count++;
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
      MultipleChatS_hash s = new MultipleChatS_hash();
      s.runServer();
   }
	
   class WinListener extends WindowAdapter {
      public void windowClosing(WindowEvent e) {
         System.exit(0);
      }
   }
}

class MultipleThreadHash extends Thread {
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
   MultipleChatS_hash cs;
   int num;
   String id;
	
   public MultipleThreadHash(MultipleChatS_hash c, Socket s, TextArea ta, Label l, String data, int n) {
      sock = s;
      display = ta;
      info = l;
      serverdata = data;
      cs = c;
      num = n;
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
      String clientdata;
      int command = 0;
      
      String[] clientDataInfo ;
      try {
         while((clientdata = input.readLine()) != null) {
        		 clientDataInfo = clientdata.split("\\|");
        		 command = Integer.parseInt(clientDataInfo[0]);
        	 switch(command) {
        	 // 로그온 메시지
        	 case 1021 : {
        		 id = clientDataInfo[1];
        		 if(cs.hs.containsKey(id)) {
        			 this.output.write("아이디중복:login_fail\r\n");
        			 this.output.flush();
        			 break;
        		 }
        		 cs.hs.put(id, this);
        		 MultipleThreadHash mt = cs.hs.get(id);
        		 mt.output.write("login_success\r\n");
        		 mt.output.flush();
     			display.append(id+"님이 로그인 하였습니다. \r\n");
     			
     	   		// 참여한 사람들의 아이디
     			// aaa
       		 
     			// 참여한 사람들의 아이디
     			// aaa, bbb
       		 
     			// 참여한 사람들의 아이디
     			// aaa, bbb, ccc
     			Enumeration <String> tableKeys = cs.hs.keys();
     			while(tableKeys.hasMoreElements()) {
     				display.append(tableKeys.nextElement()+"  ");
     			}
     			display.append("\n");
     			break;
        	 }
        	 //대화말 메시지
        	 case 0001 : {
     			clientdata = clientDataInfo[1];
     			display.append(id+"의 메세지: "+clientdata + "\r\n");
     			Collection<MultipleThreadHash> tableValues = cs.hs.values();
 	            for(MultipleThreadHash value : tableValues) { //모든 클라이언트에 데이터를 전송한다.
 	                    MultipleThreadHash SThread = value;
 	                    if(this.num != SThread.num) {
 				            SThread.output.write(id+"의 메세지:"+clientdata + "\r\n");
 				            SThread.output.flush();
 			            }
 			         }
 	            break;
     				}
		         }
		      }
      }catch(Exception e) {
    	  e.printStackTrace();
      }
      cs.hs.remove(id);
      try {
    	  sock.close();
      }catch(IOException ea) {
    	  ea.printStackTrace();
      }
   }
}
