package assignment6;

import java.util.*;
import java.io.*;

public class myHashTable {
	LinkedList[] table_positive, table_negative;
	int posCapacity, negCapacity;
	
	public myHashTable(int capacity){
		this.posCapacity = this.negCapacity = capacity/2;
		table_positive = new LinkedList[posCapacity];
		table_negative = new LinkedList[negCapacity];
	}
	
	
	public void put(Long key, Long value){
		int bucket = hashFunction(key);
		if (bucket > 0){
			if (bucket >= posCapacity){
			resize(1);
			}
			if (table_positive[bucket] == null)	table_positive[bucket] = new LinkedList();
			table_positive[bucket].add(value);
		}
		else{
			if ((-bucket) >= negCapacity){
				resize(-1);
			}
			if (table_negative[-bucket] == null)	table_negative[-bucket] = new LinkedList();
			table_negative[-bucket].add(value);
		}
				
	}
	
	public LinkedList getBucket(int bucket){
		if (bucket > 0){
			return table_positive[bucket];
		}
		else return table_negative[-bucket];
	}
			
	
	public int hashFunction(Long key){
		return (int)(key/10000);
	}
	
	public void resize(int pos_or_neg){
		if (pos_or_neg == 1){
			LinkedList[] new_table_positive = new LinkedList[posCapacity*2];
			for(int i=0; i<table_positive.length; i++){
				if (table_positive[i] != null){
					new_table_positive[i] = table_positive[i];
				}
			}
				table_positive = new_table_positive;
				posCapacity *= 2;	
		}
		else{
			LinkedList[] new_table_negative = new LinkedList[negCapacity*2];
			for(int i=0; i<table_negative.length; i++){
				if (table_negative[i] != null){
					new_table_negative[i] = table_negative[i];
				}
			}
				table_negative = new_table_negative;
				negCapacity *= 2;
		}
		
	}

}
