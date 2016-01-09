package assignment4;
import java.util.*;

public class Adlist {
	public List<List<Integer>> adlist;
	public List<List<Integer>> adlist_rev;
	
	public Adlist(List edge, int num_v, int number){
		adlist = new ArrayList<List<Integer>>(num_v);
		adlist_rev = new ArrayList<List<Integer>>(num_v);
		for (int j=1; j<=num_v; j++){
			List<Integer> to_add = new ArrayList();
			List<Integer> to_add2 = new ArrayList();
			to_add.add(j);
			to_add2.add(j);
			adlist.add(to_add);
			adlist_rev.add(to_add2);
		}
		
		for (int i=0; i<number; i++){
			int item1 = Integer.parseInt(((String[])(edge.get(i)))[0]);
			int item2 = Integer.parseInt(((String[])(edge.get(i)))[1]);
//			System.out.println("item1 "+item1);
//			System.out.println("item2 "+item2);
			adlist.get(item1-1).add(item2);
			adlist_rev.get(item2-1).add(item1);
			//System.out.println(adlist_rev.get(item2-1));
		}
	}

}
