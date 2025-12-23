package com.tcs;

import java.util.Scanner;

public class Armstrong {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number: ");
		int num=sc.nextInt();
		int n=num;
		int sum=0,count=0;
		while(num!=0) {
			int d=num%10;
			sum=sum+(int)Math.pow(d, 3);
			num=num/10;
			count++;
		}	
		if(count==3 && n==sum)
			System.out.println("Armstrong Number");
		else
			System.out.println("Not Armstrong number");
		sc.close();
	}
}
