package com.tcs;

import java.util.Scanner;

public class Analyzer {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the sentence: ");
		String s=sc.nextLine().toLowerCase();
		
		int vowel=0,digit=0,consonant=0,spaces=0;
		for(int i=0;i<s.length();i++) {
			char ch=s.charAt(i);
			if(("aeiou".indexOf(ch)!=-1))
				vowel++;
			else if(ch>='0' && ch<='9')
				digit++;
			else if(ch>='a' && ch<='z')
				consonant++;
			else if(ch==' ')
				spaces++;
		}
		System.out.println("Count of vowel: "+vowel);
		System.out.println("Count of consonant: "+consonant);
		System.out.println("Count of digits: "+digit);
		System.out.println("Count of spaces: "+spaces);
		sc.close();
			
	}
}
