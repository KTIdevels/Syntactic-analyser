package LL1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import com.sun.javafx.collections.MappingChange.Map;

import FirstandFollow.FirstAndFollowClass;
import grammar.ContextFreeGrammar;
import grammar.Rule;
import LL1.MyRule;

public class Gramatika {
	
	
	public static ArrayList<String> spracovanieSlova(ContextFreeGrammar grammar, ArrayList<String> vstup, RozkladovaTabulka rt) {		
		ArrayList<String> z  = new ArrayList<String>(); //zasobnik
		ArrayList<String> z1  = new ArrayList<String>(); //zasobnik pomocny
		ArrayList<String> vystup = new ArrayList<String>();
		
		MyRule r = (MyRule) getStartRule(1,grammar); //zo za�iato�neho slova z�skame pravidlo
		vystup.add("E"+r.getCislo()); // na za�iatku je v�dy expanzia na zaklade prveho pravidla
		z.addAll(r.getRightSide()); //naplnime zasobnik lavou stranou prvym pravidlom 
		boolean vypinac = false;
		
		do { // cyklus opakujeme dovtedy pokial nemame prazdny vstup
		
			vypinac = false; // pomocna premena pre kontrolu cyklu, ak neprebehne ani jedna vetva podmienky cyklus ukoncujeme
			
			if ( vstup.get(0) == z.get(0) && grammar.getTerminals().contains(z.get(0)) ) {
				// vtejto vetve su rovnake symboli na 0 pozicii vo vstupe aj v zasobn�ku, a mo�eme dan� symboli vymaza�
				vystup.add("P"); 
				vstup.remove(0);
				z.remove(0);
				vypinac = true;
			} else if (grammar.getNonterminals().contains(z.get(0))) {
				// symbol v zasobniku je neterminal, musime najs� pravidlo z rozhodovaciej tabulky a prep�sa� lavu stranu pravidla pravou tzv. expanzia
				r = rt.getPravidlo(z.get(0), vstup.get(0), grammar);
				if (r == null) break;
				vystup.add("E"+r.getCislo());
				
				z.remove(0);
				z1.clear();
				z1.addAll(r.getRightSide());
				z1.addAll(z);
				z = (ArrayList<String>) z1.clone();
				if(z.get(0) == "e") z.remove(0);
				vypinac = true;
			}
		
			
		} while(!vstup.isEmpty() && vypinac == true);
		
		if(vypinac == true) vystup.add("A"); // ak pre�iel cyklus dokonca, znamena to �e sme odvodili cele vstupne slovo a na�li sme akceptovany stav
		
		return vystup;
	}
	
	
	public static Rule getStartRule(int cislo, ContextFreeGrammar grammar) {
		Rule rule = null;
		
		for(Rule r:grammar.getRules()) {
			MyRule rr = (MyRule) r;
			if (rr.getCislo() == cislo) {
				rule = r;
			}
		}
		
		return rule;
	}	
	
}
