package com.tcs;

import java.util.*;

public class PrimeRange {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter two numbers: ");
		int a=sc.nextInt();
		int b=sc.nextInt();
		System.out.println("The prime numbers ranging between "+a+" and " +b+" : ");
		for(int i=a;i<b;i++) {
			int count=0;
			for(int j=2;j<=i/2;j++) {
				if(i%j==0) {
					count++;
					break;
				}
			}
			if(count!=1)
				System.out.println(i);
		}
		sc.close();
	}
}
