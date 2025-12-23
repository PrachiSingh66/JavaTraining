package com.tcs;

import java.util.Scanner;

public class Palindrome {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the string: ");
		String original=sc.nextLine().toLowerCase();
		String reverse="";
		for(int i=original.length()-1;i>=0;i--) {
			reverse=reverse+original.charAt(i);
		}
		if(reverse.equals(original)) {
			System.out.println("Palindrome");
		}
		else {
			System.out.println("Not palindrome");
		}
		sc.close();
	}
}
