package assignment6;

import java.util.*;
import java.io.*;

public class maintainMedian {
	static FileReader filereader;
	static BufferedReader br;
	Queue<Integer> minPriorityQueue, maxPriorityQueue;
	myComparator cmp;
	int countNum = 0;
	
	public maintainMedian(){
		cmp = new myComparator();
		minPriorityQueue = new PriorityQueue<Integer>();
		maxPriorityQueue = new PriorityQueue<Integer>(cmp);
		
	}
	
	private class myComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2){
			return o2-o1;
		}
	}
	
	public void add(Integer item){
		Integer transfer;
		if(countNum == 0){
			maxPriorityQueue.add(item);
			countNum++;
			return;
		}
		if(countNum == 1){
			maxPriorityQueue.add(item);
			transfer = maxPriorityQueue.poll();
			minPriorityQueue.add(transfer);
			countNum++;
			return;
		}
		if(item <= maxPriorityQueue.peek()){
			maxPriorityQueue.add(item);
			countNum++;
			if(maxPriorityQueue.size() - minPriorityQueue.size() >= 2){
				transfer = maxPriorityQueue.poll();
				minPriorityQueue.add(transfer);
			}
		}
		else{
			minPriorityQueue.add(item);
			countNum++;
			if(minPriorityQueue.size() - maxPriorityQueue.size() >= 2){
				transfer = minPriorityQueue.poll();
				maxPriorityQueue.add(transfer);
			}
		}
	}
	
	public int calculateMedian(){
		if (countNum/2*2 == countNum){
			return maxPriorityQueue.peek();
		}
		else{
			if(maxPriorityQueue.size() > minPriorityQueue.size())	return maxPriorityQueue.peek();
			else	return minPriorityQueue.peek();
		}
	}
	
	public static void main(String[] args){
		try{
			filereader = new FileReader("./Median.txt");
			br = new BufferedReader(filereader);
		}
		catch(FileNotFoundException ex){
			System.out.println("Error : File Not Found");
			return;
		}
		String s;
		Integer tmp;
		int medianSum = 0;
		maintainMedian mtm = new maintainMedian();
		try{
			while((s = br.readLine()) != null && mtm.countNum < 10000){
				tmp = Integer.parseInt(s);
				mtm.add(tmp);
				medianSum += mtm.calculateMedian();	
			}	
			System.out.println(medianSum % 10000);
		}
		catch(IOException ex){
			System.out.println("Error : IOException");
			return;
		}
	}
	
	

}
