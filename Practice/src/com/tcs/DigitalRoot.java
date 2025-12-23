package com.tcs;

import java.util.*;

public class DigitalRoot {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number: ");
		int num=sc.nextInt();
		int result=calculate(num);
		if(result<10) {
			System.out.println("Digital Root calculator is: "+result);
		}
		else {
			int result2=calculate(result);
			System.out.println("Digital Root calculator is: "+result2);
		}
		
		sc.close();
	}
	public static int calculate(int num) {
		int sum=0;
		while(num!=0) {
			int d=num%10;
			sum=sum+d;
			num=num/10;
		}
		return sum;
	}
}
