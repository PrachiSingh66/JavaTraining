package com.tcs;

import java.util.Scanner;

public class Pattern {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size: ");
		int size=sc.nextInt();
		for(int i=1;i<=size;i++) {
			for(int j=size-i;j>0;j--) {
				System.out.print(" ");
			}
			for(int k=1;k<=2*i-1;k++) {
				System.out.print("*");
			}
			System.out.println();
		}
		sc.close();
	}
}
