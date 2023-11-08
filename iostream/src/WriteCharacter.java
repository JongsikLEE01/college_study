import java.io.*;
public class WriteCharacter {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "한글 문서 파일";
		try {
			/* FileWriter fw = new FileWriter("fwfile.txt");
			fw.write(text, 0, text.length());
			fw.close();
			*/
			//FileReader int read(char[] b)함수를 이용해 fwfile.txt에 저장된 내용을 읽어서 출력
			char[] b = new char[80];
			int num;
			FileReader fr = new FileReader("fwfile.txt");
			while((num = fr.read(b)) > -1) {
				System.out.println(b);
			}
			fr.close();

		}catch(IOException e){
			System.out.println(e);
		}
	}
}