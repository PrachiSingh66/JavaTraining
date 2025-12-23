package com.tcs;

import java.util.HashSet;
import java.util.Scanner;

public class RemoveDuplicate {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter values: ");
		HashSet<Integer> num=new HashSet<Integer>();
		while(true) {
			Integer n=sc.nextInt();
			if(n==0)
				break;
			num.add(n);
		}
		System.out.println("Without duplicate: ");
		num.stream().distinct();
		System.out.println(num);
		sc.close();
	}
}
