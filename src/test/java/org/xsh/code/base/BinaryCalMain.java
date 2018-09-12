package org.xsh.code.base;

import java.util.Arrays;

import org.junit.Test;

public class BinaryCalMain {

	//二进制表示不了十进制数
	@Test
	public void test01() {
		int i = (int) 1023.9999999999999999999999999999;
		System.out.println(i);//1024
	}
	
	@Test
	public void testHax() {
		int a = 0b11101101;
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(a>>4));
		System.out.println(Integer.toBinaryString(a>>6));
	}
	
	@Test
	public void testArr() {
		int[] a = new int[] {1,2,3};
		int i = 1;
		a[++i] = 6;
		System.out.println(Arrays.toString(a));
	}
	
	char hex[] = {
		'0','1','2','3','4','5','6','7','8','9',
		'a','b','c','d','e','f'
	};
	@Test
	public void hexByte() {
		byte b = (byte)0xf1;
		System.out.println("b = 0x" + hex[(b>>4) & 0x0f] + hex[b & 0x0f]);
	}
	char bHex[] = {
			'0','1'
	};
	@Test
	public void hexBByte() {
		byte b = (byte)0b1111111;
		System.out.println("Hex+"+bHex[(b>>7)&0x01]
				+bHex[(b>>6)&0x01]
						+bHex[(b>>5)&0x01]
								+bHex[(b>>4)&0x01]
										+bHex[(b>>3)&0x01]
												+bHex[(b>>2)&0x01]
														+bHex[(b>>1)&0x01]
																+bHex[b&0x01]);
	}
	
	// 8位 16/12位 引用4位 8字节对齐
	// L1 32KB L2 256KB L3 2MB
	
}
