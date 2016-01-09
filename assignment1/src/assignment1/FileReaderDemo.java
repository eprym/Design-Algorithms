package assignment1;
import java.io.*;
public class FileReaderDemo {
	public static void main(String args[]) throws IOException{
		FileReader filereader=new FileReader("D:\\MOOC\\Design-Algorithms\\assignment1\\test.txt");
		BufferedReader br=new BufferedReader(filereader);
		String s;
		while ((s=br.readLine())!=null){
			System.out.println(s);
		}
		filereader.close();
	}

}
