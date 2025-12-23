package com.tcs;

import java.util.Scanner;
import java.util.Stack;

public class BalancedParanthesis {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the sequence: ");
		String input=sc.nextLine();
		int count=0;
		Stack<Character> stack=new Stack<>();
		for(char ch:input.toCharArray()) {
			if(ch=='(' || ch=='{' || ch=='[')
				stack.push(ch);
			else if(ch==')' || ch=='}' || ch==']') {
				if(stack.isEmpty()) {
					System.out.println("Stack is empty");
				}
				char open=stack.pop();
				if((ch==')' && open=='(') || (ch==']' && open=='[') || (ch=='}' && open=='{'))
					count++;
			}
		}
		if(count==input.length()/2)
			System.out.println("Balanced");
		else
			System.out.println("Not Balanced");
		sc.close();
	}
}
