//step 4 귓속말 기능 추가
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class MultipleChatS_whisper extends Frame {
   TextArea display;
   Label info;
   String clientdata = "";
   String serverdata = "";
   public MultipleThreadWhisper SThread;
   int count = 0;
   Hashtable<String,MultipleThreadWhisper> hs ;
   public MultipleChatS_whisper() {
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
      MultipleThreadWhisper SThread;
      hs = new Hashtable<String,MultipleThreadWhisper>();
      try {
         server = new ServerSocket(5000, 100);
         try {
            while(true) {
               sock = server.accept();
               SThread = new MultipleThreadWhisper(this, sock, display, info, serverdata, count);
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
      MultipleChatS_whisper s = new MultipleChatS_whisper();
      s.runServer();
   }
	
   class WinListener extends WindowAdapter {
      public void windowClosing(WindowEvent e) {
         System.exit(0);
      }
   }
}

class MultipleThreadWhisper extends Thread {
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
   MultipleChatS_whisper cs;
   int num;
   String id, logoutid;
	
   public MultipleThreadWhisper(MultipleChatS_whisper c, Socket s, TextArea ta, Label l, String data, int n) {
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
        		 MultipleThreadWhisper mt = cs.hs.get(id);
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
     			Collection<MultipleThreadWhisper> tableValues = cs.hs.values();
 	            for(MultipleThreadWhisper value : tableValues) { //모든 클라이언트에 데이터를 전송한다.
 	            	MultipleThreadWhisper SThread = value;
 	                    if(this.num != SThread.num) {
 				            SThread.output.write(id+"의 메세지:"+clientdata + "\r\n");
 				            SThread.output.flush();
 			            }
 			     }
 	            break;
     		}

           case 1001: {
        	 // 로그아웃 메시지 수신
        	 // 수신한 아이디를 hash 테이블에서 삭제하고
        	 // 참여한 모든 클라이너트들의 아이디를 출력한다.
        	 logoutid = clientDataInfo[1];
        	 cs.hs.remove(logoutid);
        	 Enumeration <String> tKeys = cs.hs.keys();
  			 while(tKeys.hasMoreElements()) {
  				display.append(tKeys.nextElement()+"  ");
  			 }
        	 break;
         } 
           // 귓속말 프로토콜
           case 1011:{
        	    String uesrID = clientDataInfo[1];
        	    String Message = clientDataInfo[2];
        	    
        	    if (cs.hs.containsKey(uesrID)) {
        	        MultipleThreadWhisper whisperThread = cs.hs.get(uesrID);
        	        try {
        	        	whisperThread.output.write(id + "의 귓속말:" + Message + "\r\n");
        	        	whisperThread.output.flush();
        	        } catch (IOException e) {
        	            e.printStackTrace();
        	        }
        	    } else {
        	        this.output.write("1011|Server|해당 아이디를 찾을 수 없습니다 :" + uesrID + "\r\n");
        	        this.output.flush();
        	    }
        	    break;
           } // case 문 종료
      }// switch 문 종료
    } // while 문 종료
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