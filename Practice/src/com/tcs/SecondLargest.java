package com.tcs;

import java.util.Scanner;

public class SecondLargest {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size: ");
		int size=sc.nextInt();
		int arr[]=new int[size];
		System.out.println("Enter the numbers: ");
		for(int i=0;i<size;i++)
		{
			arr[i]=sc.nextInt();
		}
		int max=arr[0];
		int sec_max=arr[0];
		for(int i=0;i<size;i++) {
			if(arr[i]>max) {
				sec_max=max;
				max=arr[i];	
			}
			else if(arr[i]>sec_max)
				sec_max=arr[i];
		}
		System.out.println("Second largest number is: "+sec_max);
		sc.close();
	}
}
