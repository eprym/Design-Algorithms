package assignment4;
import java.io.*;
import java.util.*;


public class Scc {
	Node s = new Node();
	int num_node;
	int num_edge;
	int ftime = 0;
	Node[] total_node;
	Node[] total_node_copy;
	public Scc(int number, int number_v){
		total_node = new Node[number_v];
		total_node_copy = new Node[number_v];
		for (int k=0; k<number_v; k++){
			
			total_node[k] = new Node();
			total_node[k].pos = k;
		}
		num_node = number_v;
		num_edge = number;
	}
	public void DFS1(Adlist ad, Node i){
		i.set_explored();
		List<Integer> to_explore = ad.adlist_rev.get(i.pos);
		for (int j=1; j<to_explore.size(); j++){
			int choose = to_explore.get(j);
			if (!total_node[choose-1].is_explored()){
				DFS1(ad, total_node[choose-1]);
			}
		}

		ftime++;
		i.set_fvalue(ftime);
	}
	
	public void DFS2(Adlist ad, Node i){
		i.set_explored();
		i.set_leader(s);
		List<Integer> to_explore = ad.adlist.get(i.pos);
		for (int j=1; j<to_explore.size(); j++){
			int choose = to_explore.get(j);
			if (!total_node_copy[choose-1].is_explored()){
				DFS2(ad, total_node_copy[choose-1]);
			}
		}

	}
	
	public void DFS1_loop(Adlist ad){
		for (int i=num_node; i>0; i--){
			if (!total_node[i-1].is_explored()){
				//s = total_node[i-1];
				DFS1(ad, total_node[i-1]);
			}
		}
	}
	
	public void DFS2_loop(Adlist ad){
		for (int i=0; i<num_node; i++){
			total_node[i].clear_explored();
		}
		System.arraycopy(total_node, 0, total_node_copy, 0, num_node);
		Arrays.sort(total_node);
		for (int p=num_node; p>0; p--){
			if (!total_node[p-1].is_explored()){
				s = total_node[p-1];
				DFS2(ad, total_node[p-1]);
			}
		}
	}
	public static void main(String args[]) throws IOException, InterruptedException{
		Thread t = new Thread(null, null, "Scc", 10000000){
			@Override
			public void run(){
				try{
					FileReader filereader = new FileReader("D:\\MOOC\\Design-Algorithms\\assignment4\\SCC.txt");
					BufferedReader br = new BufferedReader(filereader);
				
				
		try{
		String s;
		int number = 0;
		List edge = new ArrayList();
		while ((s=br.readLine())!=null){
			//System.out.println(s);
			String[] s1 = s.split(" ");
			edge.add(s1);
			
			number++;
		}
		//System.out.println(((String[])(edge.get(i-1)))[0]);
		int number_v=Integer.parseInt(((String[])(edge.get(number-1)))[0]);        
		//int number_v = 8;
		//System.out.println(number);
		Adlist ad = new Adlist(edge, number_v, number);
		Scc scc = new Scc(number, number_v);
		scc.DFS1_loop(ad);
		scc.DFS2_loop(ad);
		Arrays.sort(scc.total_node, Node.LEADER_ORDER);
		int num_equal=1;
		List <Integer>save_equal = new ArrayList<Integer>();
		for (int a=0; a<number_v-1; a++){
			//System.out.println(scc.total_node[a].leader.f_value);
			if (scc.total_node[a].leader.f_value == scc.total_node[a+1].leader.f_value){
				num_equal++;
			}
			else{
				save_equal.add(num_equal);
				num_equal = 1;
				//System.out.println("add");
			}
			if (a==number_v-2){
				save_equal.add(num_equal);
			}
		}
		
		Integer[] save_equal2 = new Integer[save_equal.size()];
		save_equal2 = save_equal.toArray(save_equal2);
		Arrays.sort(save_equal2);
		
		if (save_equal2.length >=5){
			for (int b=save_equal2.length; b>save_equal2.length-5; b--){
				System.out.println(save_equal2[b-1]);
			}
		}
		
		else{
			//System.out.println(save_equal2.length);
			for (int b=save_equal2.length; b>0; b--){
				System.out.println(save_equal2[b-1]);
			}
		}
		}
		catch(IOException e){
			System.out.println("IOException");
		}
	}
				catch(FileNotFoundException e){
					System.out.println("File not found");
				}
			}
	};
	t.start();
	t.join();

}
}
