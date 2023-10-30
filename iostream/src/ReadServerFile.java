import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class ReadServerFile extends Frame implements ActionListener
{
   private TextField enter;
   private TextArea contents;
   public ReadServerFile(){
      super("호스트 파일 읽기");
      setLayout( new BorderLayout() );
      enter = new TextField( "URL를 입력하세요!" );
      enter.addActionListener( this );
      add( enter, BorderLayout.NORTH );
      contents=new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
      add( contents, BorderLayout.CENTER );
      addWindowListener(new WinListener());
      setSize(350, 150);
      setVisible(true);
   }
   public void actionPerformed( ActionEvent e ) {
      URL url;
      InputStream is;
      BufferedReader input;
      String line;
      StringBuffer buffer = new StringBuffer();
      String location = e.getActionCommand(); // 텍스트 필드에 입력된 URL를 구함
      try {
         url = new URL( location );
         is = url.openStream(); // location(호스트)과 연결시키는 InputStream 객체생성
         input = new BufferedReader(new InputStreamReader(is));
         contents.setText( "파일을 읽는 중입니다...." );
         while ( ( line = input.readLine() ) != null ) // 파일(웹페이지)을 읽는다.
            buffer.append( line ).append( '\n' );
         contents.setText( buffer.toString() ); // 읽은 파일을 텍스트 에리어에 출력
         input.close();
      }catch(MalformedURLException mal) {
         contents.setText("URL 형식이 잘못되었습니다.");
      }catch ( IOException io ) {
         contents.setText( io.toString() );
      }catch ( Exception ex ) {
         contents.setText( "호스트 컴퓨터의 파일만을 열 수 있습니다." );
      }
   }
   public static void main(String args[]){
      ReadServerFile read = new ReadServerFile();
   }
   class WinListener extends WindowAdapter
   {
      public void windowClosing(WindowEvent we){
         System.exit(0);
      }
   }
}