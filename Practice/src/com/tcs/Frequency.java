package com.tcs;

import java.util.*;

public class Frequency {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the input: ");
		String input=sc.nextLine();
		Map<Character,Integer> charCounts=countCharacters(input);
		
		for(Map.Entry<Character, Integer> entry: charCounts.entrySet())
			System.out.println(entry.getKey()+"->"+entry.getValue());
		sc.close();
	}
	public static Map<Character,Integer> countCharacters(String input){
		Map<Character,Integer> charCounts=new HashMap<>();
		for(char c:input.toCharArray()) {
			//if character repeats
			if(charCounts.containsKey(c))
				charCounts.put(c, charCounts.get(c)+1);
			else
				charCounts.put(c, 1);
		}
		return charCounts;
	}
}
