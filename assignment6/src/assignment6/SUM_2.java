package assignment6;

import java.util.*;
import java.io.*;


;public class SUM_2 {
	static FileReader filereader;
	static BufferedReader br;
	int countNum = 0;
	Long[] KEY;
	myHashTable mht = new myHashTable(10000000);
	Map map = new HashMap();
	
	public SUM_2(Long[] KEY){
		this.KEY = KEY;
		for (Long key : KEY){
			mht.put(key, key);
		}
		
	}
	

	
	public void calculateSum(){
		LinkedList<Long> original, complementary;
		for (int i=0; i<mht.table_negative.length; i++){
			if(mht.table_negative[i] != null){
				original = mht.getBucket(-i);
				int[] select_comple = {-1+i, i, 1+i};
				for (int item : select_comple){
					complementary = mht.getBucket(item);
					if(complementary != null && original != null){
					for(Long key2 : complementary){
						for (Long key1 : original){
							if (Math.abs(key1 + key2) <= 10000 && (!map.containsKey(key1+key2))){
								map.put(key1 + key2, key1 + key2);
								countNum++;
							}
						}
					}
					}
				}
				
			}
		}
		
		System.out.println(countNum);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		long startMili=System.currentTimeMillis();
		try{
			filereader = new FileReader("./algo1-programming_prob-2sum.txt");
		}
		catch (FileNotFoundException ex){
			System.out.println("Error : File not found");
			return;
		}
		
		br = new BufferedReader(filereader);
		String s;
		List<Long> list  = new ArrayList<Long>();
		try{
			while ((s = br.readLine()) != null){
				list.add(Long.parseLong(s));
			}
			Long[] KEY = new Long[list.size()];
			KEY = list.toArray(KEY);
//			System.out.println(KEY.length);
			SUM_2 sum2 = new SUM_2(KEY);
			sum2.calculateSum();
		}
		catch(IOException ex){
			System.out.println("Error : IOException");
			return;
		}
//		long endMili=System.currentTimeMillis();
//		System.out.println(endMili - startMili);
		
	}

}
