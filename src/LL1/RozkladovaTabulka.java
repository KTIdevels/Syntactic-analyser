package LL1;

import java.util.ArrayList;
import java.util.HashSet;

import FirstandFollow.FirstAndFollowClass;
import grammar.ContextFreeGrammar;
import grammar.Rule;

public class RozkladovaTabulka {
	
	private Integer [][]tabulka;
	private ArrayList<String>riadok;
	private ArrayList<String>stlpec;
	
	public RozkladovaTabulka(ContextFreeGrammar grammar) {
		//deklaracia rozhodovacej tabulky
		tabulka = new Integer[grammar.getNonterminals().size()][grammar.getTerminals().size()];
		// inicializacia hodnot
		for(int i=0;i<grammar.getNonterminals().size();i++) {
			for(int j=0;j<grammar.getTerminals().size();j++) {
				tabulka[i][j] = 0;
			}
		}
		riadok = new ArrayList<String>();
		stlpec = new ArrayList<String>();
		
		riadok.addAll(grammar.getNonterminals());
		stlpec.addAll(grammar.getTerminals());
		
		ArrayList<String>faf = new ArrayList<String>();// ulozenie terminalov z first alebo follow
		
		for (Rule r: grammar.getRules()) {
			MyRule rr = (MyRule)r;
			faf.clear();
			for (int i=0;i<r.getRightSide().size();i++) {
				faf.addAll(FirstAndFollowClass.first(grammar,rr.getRightSide().get(i))); // najprv najdeme mnozinu first 
				if(!faf.isEmpty()) break;
			}
			if( faf.contains("e") ) {
				faf.remove("e");
				try {
					faf.addAll(FirstAndFollowClass.Follow(grammar, rr.getLeftSide().get(0))); // ak obsahuje epsilon, tak aj follow
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tabulka[riadok.indexOf(rr.getLeftSide().get(0))][stlpec.indexOf(faf.get(0))] = rr.getCislo(); //cislo pravidla ulozime do tabulky
			} else {
				tabulka[riadok.indexOf(rr.getLeftSide().get(0))][stlpec.indexOf(faf.get(0))] = rr.getCislo();
			}
			
			
		}
		
	}
	
	public MyRule getPravidlo(String n,String t, ContextFreeGrammar grammar) {
		MyRule rr = null;
		int i=-1;
		int j=-1;
		if (riadok.contains(n)) {
			i = riadok.indexOf(n);
		}
		if (stlpec.contains(t)) {
			j = stlpec.indexOf(t); 
		}
		
		if(tabulka[i][j] != null) {
			for(Rule r:grammar.getRules()) {
				MyRule lv_r = (MyRule) r;
				if(lv_r.getCislo() == tabulka[i][j]) {
					rr = lv_r;
				}
			}
		}  	
				
		return rr;
	}
	


}
