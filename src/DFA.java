package automata;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JFrame;

public class DFA {

	final int finalState;
	ArrayList<Transition> rules = new ArrayList<Transition>();
	SortedSet<Character> alphabet = new TreeSet<Character>();

	@SuppressWarnings("unchecked")
	public DFA(ArrayList<Transition> transitions, SortedSet<Character> alphabet, int finalState){
		this.finalState = finalState;
		this.rules = (ArrayList<Transition>) transitions.clone();
		this.alphabet = alphabet;
	}

	public DFA(int finalState){
		this.finalState = finalState;
	}

	public boolean parse(String str){

		int start = 0;		
		for (Character ch : str.toCharArray()){			
			if (!alphabet.contains(ch)) return false;
			int index = rules.indexOf(new Transition(start, -1, ch));
			if (index == -1) return false;
			else start = rules.get(index).end;
		}		
		if (start == finalState) return true;
		else return false;
	}

	private static boolean testDFA(){

		//Defines the alphabet as {a,b}
		SortedSet<Character> alphabet = new TreeSet<Character>();
		alphabet.add('a');
		alphabet.add('b');

		//Creates and defines the set of transition rules for the DFA
		ArrayList<Transition> rule = new ArrayList<Transition>();
		rule.add(new Transition(0,0,'b'));
		rule.add(new Transition(0,1,'a'));
		rule.add(new Transition(1,1,'b'));

		//Creates a DFA over {a,b} that accepts all strings containing exactly 1 'a'
		DFA dfa = new DFA(rule, alphabet, 1);

		if (!dfa.parse("bbbbbbbbbbabbbbbbbbbbbbbb")) return false;
		if (!dfa.parse("bbbbbba")) return false;
		if (!dfa.parse("a")) return false;
		if (!dfa.parse("bab")) return false;
		if (dfa.parse("abab")) return false;
		if (dfa.parse("abbabba")) return false;
		if (dfa.parse("bb")) return false;

		return true;

	}


	public static void main(String[] args){		

		/*
		 * Performs a short hard-coded test to ensure that the DFA construction
		 * works as expected
		 */
		if (!testDFA()) {
			System.out.println("Error when checking DFA consistency. Please check construction rules");
		}

		boolean graphical = true;
		if (graphical){
			
			Controller c = new Controller();
			
		} else {

			SortedSet<Character> alphabet = new TreeSet<Character>();
			ArrayList<Transition> rule = new ArrayList<Transition>();

			Scanner stream = new Scanner(System.in);
			String input = "";

			/*
			 * Allows the user to define the alphabet
			 * Strings containing non-alphabet characters cannot be accepted by its dfa
			 */
			while (alphabet.isEmpty()){
				System.out.print("Please enter the accepted alphabet: ");
				input = stream.nextLine();
				for (Character ch : input.toCharArray()) alphabet.add(ch);
				System.out.println();
			}


			/*
			 * Lets the user input the transition rules used to define the DFA
			 * Being a DFA (hence deterministic) there can only be one possible transition at each state
			 * If a rule has already been added, then it will be overwritten
			 */
			System.out.println("Please enter the set of unique transitions");
			System.out.println("Format is <start state,end state,input character>");
			System.out.println("Note: states must be numerical values. 0 denotes starting state. Enter -1,-1,* to finish");

			while (!(input = stream.nextLine()).equals("-1,-1,*")){			
				System.out.println();
				String[] info = input.split(",");
				if (info.length == 3 && info[2].length() == 1){
					try {
						int start = Integer.parseInt(info[0]);
						int end = Integer.parseInt(info[1]);					
						Character ch = info[2].charAt(0);
						if (start == -1 && end == -1 && ch == '*') break;
						else if (start < 0 || end < 0) continue;
						Transition move = new Transition(start,end,ch);
						if (!rule.contains(move)) rule.add(move);
						else {
							rule.remove(move);									//Overwrite existing transition if present
							rule.add(move);										//because DFA's are deterministic
						}
					} catch (NumberFormatException e){
						System.out.println("Invalid states listed. Must be positive integers");
					}
				}
				System.out.println("Enter the next transition: ");
				System.out.print("Note: states must be numerical values. 0 denotes starting state. Enter -1,-1,* to finish");
			}

			/*
			 * Lets the user choose what state to use as the final state
			 * If there are no transitions to the final state then all
			 * inputs will be invalid
			 */

			int finalState = -1;			
			while (finalState < 0){			
				System.out.println("Enter the numerical value of the final state: ");
				input = stream.nextLine();
				try {
					finalState = Integer.parseInt(input);
				} catch (NumberFormatException e){
					System.out.println("Invalid final state. Must be a positive integer ");
				}			
			}

			DFA dfa = new DFA(rule, alphabet, finalState);

			//Gets input strings from the user and checks for acceptance
			System.out.print("Enter the string to be parsed, q.q to quit");
			while (!(input = stream.nextLine()).equals("q.q")){			
				boolean valid = dfa.parse(input);
				if (valid) System.out.println("Valid");
				else System.out.println("Invalid");
				System.out.print("Enter the next string to be parsed, q.q to quit");
			}		

		}
	}

}

/**
 *	Transitions that can be added to a DFA
 */
class Transition {

	final int start;					//Starting state
	final int end;						//State after transition
	final char input;					//Input character consumed

	/**
	 * Creates a new transition rule that can be added to a DFA
	 * @param start the start state
	 * @param end the end state
	 * @param input the input character that the transition consumes
	 */
	public Transition(int start, int end, char input){
		this.start = start;
		this.end = end;
		this.input = input;
	}

	/* 
	 * Consider two transitions equal if they have the same start state and
	 * take the same input character
	 * Used for checking determinism
	 */
	@Override
	public boolean equals(Object other){
		Transition trans = (Transition) other;
		return this.start == trans.start && this.input == trans.input;		
	}

}