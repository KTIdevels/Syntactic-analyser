/**
 * 
 */
package LL1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import FirstandFollow.FirstAndFollowClass;
import grammar.ContextFreeGrammar;
import grammar.Rule;

/**
 * @author Martin
 *
 */
public class GramatikaTest {
	HashSet<String> terminals;
	HashSet<String> nonterminals;
	Rule rule1;
	Rule rule2;
	Rule rule3;
	Rule rule4;
	HashSet<Rule> rules;
	String startsymbol;
	ArrayList<String> vstupneSlovo;


	@Before
	public void setUp() throws Exception {
		terminals = new HashSet<String>(Arrays.asList(
				"begin",
				"end",
				"$",
				";",
				"p",
				"e"
				));
		nonterminals = new HashSet<String>(Arrays.asList(
				"<program>",
				"<prikazy>",
				"<prikaz>"
				));
		
		rule1 = new MyRule(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin","<prikazy>","end","$")),1);
		rule2 = new MyRule(new ArrayList<String>(Arrays.asList("<prikazy>")), new ArrayList<String>(Arrays.asList("<prikaz>",";","<prikazy>")),2);
		rule3 = new MyRule(new ArrayList<String>(Arrays.asList("<prikazy>")), new ArrayList<String>(Arrays.asList("e")),3);
		rule4 = new MyRule(new ArrayList<String>(Arrays.asList("<prikaz>")), new ArrayList<String>(Arrays.asList("p")),4);
		rules= new HashSet<Rule>(Arrays.asList(rule1,rule2,rule3,rule4));
		
		startsymbol="<program>";
		
		vstupneSlovo = new ArrayList<String>(Arrays.asList(
				"begin",
				"p",
				";",
				"p",
				";",
				"end",
				"$"
				));
		
	}

	@Test
	public void test() {
		try {
			ContextFreeGrammar g = new ContextFreeGrammar(terminals, nonterminals, rules, startsymbol);
			RozkladovaTabulka rt = new RozkladovaTabulka(g);
			

				System.out.println("First(<program>)"+FirstAndFollowClass.first(g, "<program>").toString());
				System.out.println("First(<prikazy>)"+FirstAndFollowClass.first(g, "<prikazy>").toString());
				System.out.println("First(<prikaz>)"+FirstAndFollowClass.first(g, "<prikaz>").toString());
				
				System.out.println("Follow(<program>)"+FirstAndFollowClass.Follow(g, "<program>").toString());
				System.out.println("Follow(<prikazy>)"+FirstAndFollowClass.Follow(g, "<prikazy>").toString());
				System.out.println("Follow(<prikaz>)"+FirstAndFollowClass.Follow(g, "<prikaz>").toString());
				
					
				System.out.println("Akcia:"+Gramatika.spracovanieSlova(g, vstupneSlovo, rt).toString());
 
			
			
		} catch(Exception e) {
			e.printStackTrace();
			fail("Unexpected exception");
		}
		//fail("Not yet implemented");
	}

}
