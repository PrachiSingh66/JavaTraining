package com.tcs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sort {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ArrayList<String> arr=new ArrayList<String>();
		System.out.println("Enter the names: ");
		while(true) {
			String name=sc.nextLine();
			if(name.equalsIgnoreCase("quit"))
				break;
			arr.add(name);
		}
		Collections.sort(arr);
		System.out.println("Sorted array is: "+arr);
		sc.close();
	}
}
