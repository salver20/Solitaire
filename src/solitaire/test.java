package solitaire;

import java.util.LinkedList;
import java.util.List;

public class test {
	
	public static void main(String[] args) {
		List pile = new LinkedList();
		pile.add(1);
		pile.add(2);
		pile.add(3);
		int i = (int) pile.remove(0);
		System.out.println(i);
		
	}

}


