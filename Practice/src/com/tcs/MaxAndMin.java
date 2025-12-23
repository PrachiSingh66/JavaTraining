package com.tcs;

import java.util.Scanner;

public class MaxAndMin {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size of the array: ");
		int size=sc.nextInt();
		int arr[]=new int[size];
		System.out.println("Enter the values of array: ");
		for(int i=0;i<size;i++) {
			arr[i]=sc.nextInt();
		}
		int max=arr[0];
		int min=arr[0];
		for(int i=0;i<size;i++) {
			if(arr[i]>max)
				max=arr[i];
			else if(arr[i]<min)
				min=arr[i];
		}
		System.out.println("The max value is: "+max);
		System.out.println("The min value is: "+min);
		sc.close();
	}
}
