package assignment4;
import java.util.*;

public class Node implements Comparable<Node> {
	public Node leader;
	public int f_value;
	public boolean explored;
	public int pos;
	public static final Comparator<Node> LEADER_ORDER= new leader_order();
	public Node(){
		 explored = false;
	}
	
	public void set_explored(){
		explored = true;
	}
	
	public void clear_explored(){
		explored = false;
	}
	
	public boolean is_explored(){
		return explored == true;
	}
	
	public void set_fvalue(int fvalue){
		f_value = fvalue;
		
	}
	
	public void set_leader(Node s){
		leader = s;
	}
	
	public int compareTo(Node that){
		if (this.f_value < that.f_value){
			return -1;
		}
		if (this.f_value == that.f_value){
			return 0;
		}
		else  return 1;
	}
	
	private static class leader_order implements Comparator<Node>{
		public int compare(Node v, Node w){
			if (v.leader.f_value < w.leader.f_value)    return -1;
			if (v.leader.f_value == w.leader.f_value)   return 0;
			else                                   return 1;
		}
		
	}

	
}
