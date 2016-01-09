package assignment2;

public class test {
	public static void swap(int a,int b){
		int temp=a;
		a=b;
		b=temp;
	}
	
	public static void main(String args[]){
		int x=1;
		int y=2;
		swap(x,y);
		System.out.println(x);
		System.out.println(y);
	}

}
