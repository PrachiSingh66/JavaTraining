package com.tcs;

import java.util.Scanner;

public class MissingNumber {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size n: ");
		int n=sc.nextInt();
		int arr[]= {1,4,3,5};
		int sum=(int)(n*(n+1))/2;
		int total=0;
		for(int a:arr) {
			total=total+a;
		}
		int missing=sum-total;
		System.out.println("Missing Number in Sequence: "+missing);
		sc.close();
	}
}
