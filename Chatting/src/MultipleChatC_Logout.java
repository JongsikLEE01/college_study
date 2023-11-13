// step 3 로그아웃 기능을 추가하고 모든 참석자 출력 및
// 아이디 중복체크를 포함
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;


public class MultipleChatC_Logout extends Frame implements ActionListener {
	
   TextArea display;
   TextField text, loginText;
   Label mlbl,lword, loginLabel;
   BufferedWriter output;
   BufferedReader input;
   Socket client;
   String clientdata = "";
   String serverdata = "";
   String logindata = null;
   String id;
   boolean loginCheck = false;
   public MultipleChatC_Logout() {
      super("클라이언트");
      display=new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
      display.setEditable(false);
      add(display, BorderLayout.CENTER);

      mlbl = new Label("채팅상태를 보여줍니다.");
      add(mlbl,BorderLayout.NORTH);
      
      Panel ptotal = new Panel(new BorderLayout());
      Panel pword = new Panel(new BorderLayout());
      lword = new Label("대화말");
      text = new TextField(30); //전송할 데이터를 입력하는 필드
      text.addActionListener(this); //입력된 데이터를 송신하기 위한 이벤트 연결
      
      pword.add(lword, BorderLayout.WEST);
      pword.add(text, BorderLayout.EAST);
      ptotal.add(pword,BorderLayout.CENTER);
      
      Panel plabel = new Panel(new BorderLayout());
      loginLabel = new Label("로그온");
      loginText = new TextField(30);
      loginText.addActionListener(this);
      
      plabel.add(loginLabel,BorderLayout.WEST);
      plabel.add(loginText, BorderLayout.EAST);
      ptotal.add(plabel,BorderLayout.SOUTH);
      
      add(ptotal, BorderLayout.SOUTH);

      
      
      addWindowListener(new WinListener());
      setSize(400, 300);
      setVisible(true);
   }
	
   public void runClient() {
      try {
    	  mlbl.setText("접속완료! 사용할 아이디를 입력하세요.");
    	  client = new Socket(InetAddress.getLocalHost(), 5000);
         input = new BufferedReader(new InputStreamReader(client.getInputStream()));
         output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
         while(true) {
            String serverdata = input.readLine();
            if(serverdata.equals("login_fail")) {
            	mlbl.setText("중복된 닉네임입니다. 다시 시도해주세요.");
            	text.setVisible(false);
            }else if(serverdata.equals("login_success")) {
            	mlbl.setText(id +"(으)로 로그인하였습니다.");
            	text.setVisible(true);
            	loginText.setVisible(false);
            }
            else {
            	display.append("\r\n" + serverdata);
            }
            
         }
      } catch(IOException e ) {
         e.printStackTrace();
      }
      try{
         client.close();
      }catch(IOException e){
         e.printStackTrace();
      }
   }
		
   public void actionPerformed(ActionEvent ae){
      clientdata = text.getText();
      logindata = loginText.getText();
      
      if(logindata == null || logindata.isEmpty()) {
    	  mlbl.setText("로그인을 해야합니다!");
    	  text.setText("");
    	  
      }else if(ae.getSource() == text) {
	      try{
	    	  if(clientdata.startsWith("\\logout")) {
	    		  // logout 메시지입니다....
	    		  // 서버에 "1001|아이디"
	    		  display.append("로그아웃 메시지입니다.\n");
	    		  String logoutid = clientdata.substring(8);
	    		  display.append("로그아웃 아이디는 \n"+logoutid);
	    		  output.write("1001|"+logoutid+"\r\n");
	    		  output.flush();
			      text.setText("");
	    	  }else { // 대화말 메시지...
		         display.append("\r\n나: "+clientdata);
		         output.write("0001|"+clientdata+"\r\n");
		         output.flush();
		         text.setText("");
	    	  }
		   }catch(IOException e){
		      e.printStackTrace();
		   }
	      }
	  else if(ae.getSource() == loginText) {
	    id = logindata;
	    if(loginCheck) {
	    		  
	  }
	    try{
	          output.write("1021|"+logindata+"\r\n");
	          output.flush();
	    } catch(IOException e){
	         e.printStackTrace();
	    }
	 }
      
   }
		
   public static void main(String args[]) {
      MultipleChatC_Logout c = new MultipleChatC_Logout();
      c.runClient();
   }
		
   class WinListener extends WindowAdapter {
      public void windowClosing(WindowEvent e){
         System.exit(0);
      }
   }			
}