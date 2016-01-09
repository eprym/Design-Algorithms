package assignment2;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class count_compare {
	public long count=0;
	
	public  int partition(Integer[] result,int left,int right){
		
			count=count+right-left;
		    //System.out.println(count);
			int k;
			if ((right-left+1)/2*2==(right-left+1))  k=(right-left+1)/2;
			else k=(right-left+2)/2;
			int[] sample={result[left],result[left+k-1],result[right]};
			int m;
			m=get_median(sample);
			//print(m);
			//swap(result[left],result[right]);
			int temp2=result[left];
			if(m==1) {
				result[left]=result[left+k-1];
				result[left+k-1]=temp2;
			}
			else{
				if(m==2) {
					result[left]=result[right];
					result[right]=temp2;
				}
			}
			//print(result[left]);
			int p=result[left];
			int i=left+1;
			for (int j=left+1;j<=right;j++){
				if (result[j]<p) {
					//swap(result[j],result[i]);
					int temp=result[j];
					result[j]=result[i];
					result[i]=temp;
					i++;
					
				}
				
			}
			//swap(result[left],result[i-1]);
			int temp1=result[left];
			result[left]=result[i-1];
			result[i-1]=temp1;
			//print(result[left]);
			//System.out.println(i-1);
			return i-1;
			
	}
	
	public void quicksort(Integer[] result,int left,int right){
		if (left<right){
			int q=partition(result,left,right);
			quicksort(result,left,q-1);
			quicksort(result,q+1,right);
		}
	}
	
	public  void swap(int a, int b){
		int temp=a;
		a=b;
		b=temp;
		
	}
	
	public static void print(int a){
		System.out.println(a);
	}
	
	public int get_median(int[] a){
		int median;
		if(a[0]<a[1]){
			if(a[1]<a[2]) median=1;
			else {
				if(a[0]>a[2]) median=0;
				else median=2;
			}
		}
		else{
			if(a[0]>a[2]){
				if(a[1]>a[2]) median=1;
				else median=2;
			}
			else median=0;
		}
		return median;
	}
	
	public static void main(String args[]) throws IOException{
		List list=new ArrayList();
		FileReader filereader=new FileReader("D:\\MOOC\\Design-Algorithms\\assignment2\\QuickSort.txt");
		BufferedReader br=new BufferedReader(filereader);
		String s;
		while ((s=br.readLine())!=null){
			list.add(new Integer(s));
		}
		filereader.close();
		Integer[] result=new Integer[list.size()];
		list.toArray(result);
	    count_compare cc=new count_compare();
	    cc.quicksort(result, 0, result.length-1);
	    System.out.println(cc.count);
//	    for (int b=0;b<result.length;b++){
//			print(result[b]);
//		}
	    
		
	}

}
