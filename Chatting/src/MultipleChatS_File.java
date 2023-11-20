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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class MultipleChatS_File extends Frame {
   TextArea display;
   Label info;
   String clientdata = "";
   String serverdata = "";
   public MultipleThreadFile SThread;
   int count = 0;
   Hashtable<String,MultipleThreadFile> hs ;
   public MultipleChatS_File() {
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
      MultipleThreadFile SThread;
      hs = new Hashtable<String,MultipleThreadFile>();
      try {
         server = new ServerSocket(5000, 100);
         try {
            while(true) {
               sock = server.accept();
               SThread = new MultipleThreadFile(this, sock, display, info, serverdata, count);
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
      MultipleChatS_File s = new MultipleChatS_File();
      s.runServer();
   }
	
   class WinListener extends WindowAdapter {
      public void windowClosing(WindowEvent e) {
         System.exit(0);
      }
   }
}

class MultipleThreadFile extends Thread {
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
   MultipleChatS_File cs;
   int num;
   String id;
   
   public MultipleThreadFile(MultipleChatS_File c, Socket s, TextArea ta, Label l, String data, int n) {
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
        	 
        	 case 1021 : {
        		 id = clientDataInfo[1];
        		 if(cs.hs.containsKey(id)) {
        			 this.output.write("login_fail\r\n");
        			 this.output.flush();
        			 break;
        		 }
        		 cs.hs.put(id, this);
        		 MultipleThreadFile mt = cs.hs.get(id);
        		 mt.output.write("login_success\r\n");
        		 mt.output.flush();
     			display.append(id+"님이 로그인 하였습니다. \r\n");
     			String loginIdSet = "";
      			for(String tableKey : cs.hs.keySet()) {
      				loginIdSet += tableKey+"  ";
      			}
      			Collection<MultipleThreadFile> tableValues = cs.hs.values();
 	            for(MultipleThreadFile value : tableValues) { //모든 클라이언트에 데이터를 전송한다.
 	                    value.output.write("참여자 목록: "+loginIdSet+"\r\n");
 	                    value.output.flush();
 			         }
     			break;
        	 }
        	 
        	 case 1022: {
        		 //파일 다운로드
        		 String path = "/User/1-314/Docments/Java_File/";
        		 String fileName = clientDataInfo[1];
        		 int fileSize = Integer.parseInt(clientDataInfo[2]);
        		 byte[] buffer = is.readNBytes(fileSize);
        		 FileOutputStream fout = null;
        		 try {
         			fout = new FileOutputStream(fileName);
         			fout.write(buffer);
         			display.append("\n"+id+"님으로부터 파일 " + fileName + "(을)를 받았습니다.\n");
         		} catch(Exception e) {
         			display.append("\n"+id+"님으로부터 파일 " + fileName + "받기를 실패했습니다.\n");
         		}
         		try {
         			if(fout != null) fout.close();
         		} catch(Exception e) {
         			e.printStackTrace();
         		}
         		break;
        	 }
        	 
        	 case 1001 : {
     			clientdata = clientDataInfo[1];
     			display.append(id+"의 메세지: "+clientdata + "\r\n");
     			Collection<MultipleThreadFile> tableValues = cs.hs.values();
 	            for(MultipleThreadFile value : tableValues) { //모든 클라이언트에 데이터를 전송한다.
 	                    MultipleThreadFile SThread = value;
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