package LL1;

import java.util.ArrayList;

import grammar.Rule;

public class MyRule extends Rule  {
	private int cislo;// roz��renie triedy Rule o atrib�t ��slo pravidla
	
	public MyRule(ArrayList<String> leftSide, ArrayList<String> rightSide, int cislo) {
		super(leftSide, rightSide);
		this.cislo = cislo;		
	}
	
	public int getCislo() {
		return cislo;
	}

}
