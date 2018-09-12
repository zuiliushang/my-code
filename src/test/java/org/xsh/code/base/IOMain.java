package org.xsh.code.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import org.junit.Test;

public class IOMain {
	
	@Test
	public void tEnDeCode() {
		int i = 65423;
		//int 0xffffffff
		//byte 0xff
		byte[] bs = new byte[4];
		bs[0] = (byte) (i&0xff);
		bs[1] = (byte) ((i>>8)&(0xff));
		bs[2] = (byte) ((i>>16)&(0xff));
		bs[3] = (byte) ((i>>24)&(0xff));
		System.out.println(Arrays.toString(bs));
		int b = (bs[0]&0xff)+((bs[1]&0xff)<<8)+((bs[2]&0xff)<<16)+((bs[3]&0xff)<<24);
		System.out.println(b);
	}
	
	@Test
	public void tEnDeLongCode() {
		long i = 233332222333222L;
		byte[] bs = new byte[8];
		bs[0] = (byte)(i&0xff);
		bs[1] = (byte)((i>>8)&0xff);
		bs[2] = (byte)((i>>16)&0xff);
		bs[3] = (byte)((i>>24)&0xff);
		bs[4] = (byte)((i>>32)&0xff);
		bs[5] = (byte)((i>>40)&0xff);
		bs[6] = (byte)((i>>48)&0xff);
		bs[7] = (byte)((i>>56)&0xff);
		System.out.println(Arrays.toString(bs));
		long b = (bs[0] & 0xff) + ((bs[1]&0xff)<<8)+((bs[2]&0xff)<<16)+((long)(bs[3]&0xff)<<24)
				+(((long)bs[4]&0xff)<<32)+(((long)bs[5]&0xff)<<40)+(((long)bs[6]&0xff)<<48)+(((long)bs[7]&0xff)<<56);
		System.out.println(b);
	}
	
	@Test
	public void testInputStream() throws FileNotFoundException, IOException {
		try(InputStream in = new FileInputStream("G:\\hello.txt");
				InputStreamReader isr = new InputStreamReader(in,"gbk");){
			StringBuffer sb = new StringBuffer();
			char[] c = new char[1024];
			int length = 0;
			while((length = isr.read(c))!=-1) {
				sb.append(c, 0, length);
			}
			System.out.println(sb.toString());
		}
	}
	
	//gbk -> utf-8
	@Test
	public void testFileInputStream() throws FileNotFoundException, IOException {
		try(InputStream in = new FileInputStream("G:\\hello.txt");
				InputStreamReader inr = new InputStreamReader(in,"gbk");
				OutputStream out = new FileOutputStream("G:\\hello1.txt");
				OutputStreamWriter ouw = new OutputStreamWriter(out,"utf-8")){
			char[] charBuffer = new char[1024];
			int length = 0;
			while ((length = inr.read(charBuffer))!=-1) {
				System.out.println(new String(charBuffer,0,length));
				ouw.write(charBuffer, 0, length);
				ouw.flush();
			}
		}
	}
	
}
