package assignment5;

import java.io.*;
import java.util.*;

public class simpleDij {
	static FileReader filereader;
	static BufferedReader br;
	List edge, length;
	int[] A, explored;
	int numExplored = 0, vertexNum;
	
	public simpleDij(List edge, List length, int vertexNum){
		this.edge = edge;
		this.length = length;
		A = new int[vertexNum+1];
		explored = new int[vertexNum+1];
		this.vertexNum = vertexNum;
		for (int i = 0; i < vertexNum+1; i++){
			A[i] = 1000000;
			explored[i] = 0;
		}
			
	}
	
	public void findShortestPath( int source){
		if (source<1){
			System.out.println("wrong source number");
			return;
		}
		A[source] = 0;
		explored[source] = 1;
		numExplored++;
//		int vertex = source;
//		while( numExplored < vertexNum){
//			int mini = 1000000, vertex_to_choose = vertex;
//			String[] tmpLength = (String[])length.get(vertex);
//			String[] tmpVertex = (String[])edge.get(vertex);
//			for (int i = 0; i < ((String[])length.get(vertex)).length; i++){
//				if (explored[Integer.parseInt(tmpVertex[i])-1] == 0){
//					if (mini > (Integer.parseInt(tmpLength[i]) + A[vertex])){
//						mini = Integer.parseInt(tmpLength[i]) + A[vertex];
//						vertex_to_choose = Integer.parseInt(tmpVertex[i]);
//					}
//				}
//			
//			}
//			A[vertex_to_choose-1] = mini;
//			explored[vertex_to_choose-1] = 1;
//			numExplored++;
//			vertex = vertex_to_choose-1;
//		}
		
		while (numExplored < vertexNum){
			int mini = 1000000;
			int vertex;
			int vertex_to_choose = 0;
			for (int i = 1; i<vertexNum+1; i++){
				if (explored[i] == 1){
					String[] tmpVertex = (String[])edge.get(i);
					String[] tmpLength = (String[])length.get(i);
					if (tmpVertex[0] == "empty")	continue;
					String item;
					for (int j = 0; j<tmpVertex.length; j++){
						item = tmpVertex[j];
						vertex = Integer.parseInt(item);
						if ((explored[vertex] == 0) 
								&& (mini > Integer.parseInt(tmpLength[j]) + A[i])){
							mini = Integer.parseInt(tmpLength[j]) + A[i];
							vertex_to_choose = vertex;	
						}
					}
				}
			}
			A[vertex_to_choose] = mini;
			explored[vertex_to_choose] = 1;
			numExplored++;
		}
		
	}
	
	
	public void showShortestPath(int[] end){
		for (int i=0; i<end.length; i++){
			System.out.println(A[end[i]]);
		}
		
	}
	public static void main(String[] args) {
		try{
			filereader = new FileReader("./dijkstraData.txt");
		}
		catch(FileNotFoundException ex) {
			System.out.println("File not found");
			return;
			}
		br = new BufferedReader(filereader);
		String s;
		List edge = new ArrayList();
		List length = new ArrayList();
		String[] firstline = {"This is the empty line"};
		edge.add(firstline);
		length.add(firstline);
		int vertexNum = 0;
		try{
			while((s = br.readLine()) != null){
				String[] s1 = s.split("	|,");
				if (s1.length-1 == 0){
					String[] sEdge = {"empty"};
					String[] sLength = {"empty"};
					edge.add(sEdge);
					length.add(sLength);
					vertexNum++;
				}
				else{
					String[] sEdge = new String[(s1.length-1)/2];
					String[] sLength = new String[(s1.length-1)/2];
					//System.out.printf("s1 length %d\n", s1.length);
					//System.out.println(s1[s1.length-1]);
					for (int i=0; i<s1.length-1;i+=2){
						//System.out.printf("i %d\n",i);
						sEdge[i/2] = s1[i+1];
						sLength[i/2] = s1[i+2];
						}
					edge.add(sEdge);
					length.add(sLength);
					vertexNum++;
					}
				}
		}
		catch(IOException ex){
			System.out.println("IOException");
			return;
		}
		//System.out.println(vertexNum);
		simpleDij sdij = new simpleDij(edge, length, vertexNum);
		sdij.findShortestPath(1);
		int[] end = {7,37,59,82,99,115,133,165,188,197};
		//int[] end = {4};
		sdij.showShortestPath(end);
	}
		
		
}


