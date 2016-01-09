package assignment1;
import java.io.*;
import java.util.*;
public class Inversion {
	
	public static long sort_and_count(Integer[]A,int left,int right){
		int mid;
		long x=0,y=0,z=0;
		if(left<right){
			mid=(left+right)/2;
			x=sort_and_count(A,left,mid);
			y=sort_and_count(A,mid+1,right);
			z=count_split(A,left,mid,right);
		}
		return x+y+z;
	}
	
	public static long count_split(Integer[] A,int left,int mid,int right){
		Integer[] temp=new Integer[right-left+1];
		int i=0;
		long count=0;
		int ls=left;
		int rs=mid+1;
		while(ls<=mid && rs<=right){
			if (A[ls]<=A[rs]) {
				temp[i]=A[ls];
				ls++;
			}
			else{
				if (A[ls]>A[rs]) {
					temp[i]=A[rs];
					count=count+mid-ls+1;
					rs++;
				}
				
			}
			i++;
		}
		while(ls<=mid){
			temp[i++]=A[ls++];
		}
		while(rs<=right){
			temp[i++]=A[rs++];
		}
		
		for(int j=0,k=left;k<=right;j++,k++){
			A[k]=temp[j];
		}
		
		return count;
	}
	
	
	public static void main(String args[]) throws IOException{
		List list=new ArrayList();
		FileReader filereader=new FileReader("D:\\MOOC\\Design-Algorithms\\assignment1\\IntegerArray.txt");
		BufferedReader br=new BufferedReader(filereader);
		String s;
		while ((s=br.readLine())!=null){
			list.add(new Integer(s));
		}
		filereader.close();
		Integer[] result=new Integer[list.size()];
		list.toArray(result);
		//System.out.println(result[999]);
		long count =sort_and_count(result,0,result.length-1);
		System.out.println(count);
		//System.out.println(result[999]);
		
	}

}

		



