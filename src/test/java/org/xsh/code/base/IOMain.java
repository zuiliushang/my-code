package org.xsh.code.base;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.RandomAccess;

import org.junit.Test;

public class IOMain implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Test
	public void testAscii() {
		char a = 65;
		System.out.println(a);
		char b = 66;
		System.out.println(b);
		char enter = 13;
		System.out.println(enter);
		char zero = 48;
		System.out.println(zero);
		char nine = 57;
		System.out.println(nine);
		char blank = 10;
		System.out.println(blank);
	}
	
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
	public void tEnDeShortCode() {
		short i = 23342;
		byte[] bs = new byte[2];
		bs[0] = (byte)(i&0xff);
		bs[1] = (byte)((i>>8)&0xff);
		System.out.println(Arrays.toString(bs));
		short b = (short) ( (bs[0]&0xff)+((bs[1]&0xff)<<8));
		System.out.println(b);
	}
	
	//byte -> char
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
	
	@Test
	public void testWriter(){
		try(OutputStream os = new FileOutputStream("G:\\out.txt");
				OutputStreamWriter osw = new OutputStreamWriter(os,"utf-8");){
			String p = "哈喽我的";
			osw.write(p);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 
	@Test
	public void testChar2Byte() throws FileNotFoundException, IOException {
		try(InputStream in = new FileInputStream("G:\\hello.txt");
				InputStreamReader isr = new InputStreamReader(in, "gbk");
				OutputStream out = new FileOutputStream("G:\\hello1.txt");
				OutputStreamWriter osw = new OutputStreamWriter(out,"utf-8");){
			char[] c = new char[64];
			int length = 0;
			while ((length = isr.read(c))!=-1) {
				System.out.println(new String(c,0,length));
				osw.write(c, 0, length);
				osw.flush();
			}
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
	
	@Test
	public void testByteArray() throws FileNotFoundException, IOException {
		try(InputStream in = new FileInputStream("G:\\hello.txt");
				ByteArrayOutputStream bos = new ByteArrayOutputStream();){
			byte[] bs = new byte[1024];
			int length = 0;
			while((length = in.read(bs)) != -1) {
				bos.write(bs, 0, length);
			}
			byte[] datas = bos.toByteArray();
			System.out.println(Arrays.toString(datas));
		}
	}
	
	@Test
	public void testReadLine() throws FileNotFoundException, IOException {
		try(FileReader r = new FileReader("G:\\hello.txt");
				LineNumberReader reader = new LineNumberReader(r);){
			String s=null;
			while ((s=reader.readLine())!=null) {
				System.out.println(new String(s.getBytes("gb2312"),"utf-8"));
			}
		}
	}
	
	@Test
	public void testObjectStream() throws FileNotFoundException, IOException {
		User user = new User("zuiliushang", "halouworld");
		User user1 = new User("hahahahah", "Asdasd");
		try(
				OutputStream out = new FileOutputStream("G://object.txt");
				ObjectOutputStream oos = new ObjectOutputStream(out);){
			oos.writeObject(user);
			oos.writeObject(user1);
			oos.flush();
		}
	}
	
	@Test
	public void testObjectInStream() throws FileNotFoundException, IOException, ClassNotFoundException {
		try(InputStream in = new FileInputStream("G://object.txt");
				ObjectInputStream ois = new ObjectInputStream(in);){
			User user = (User) ois.readObject();
			System.out.println(user);
			user = (User) ois.readObject();
			System.out.println(user);
		}
	}
	
	/**
	 * {@link XMLEncoder}
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testTrandition() throws FileNotFoundException, IOException {
		try(	OutputStream os = new FileOutputStream("G://object.xml");
				XMLEncoder xe = new XMLEncoder(os, "GBK", true, 0);){
			User user1 = new User("raindrops", "raindropsssss");
			xe.writeObject(user1);
	/*		User user2 = new User("zuiliushang111", "zuiliushang111");
			xe.writeObject(user2);*/
		}
	}
	
	@Test
	public void testTranditionObject() throws FileNotFoundException, IOException {
		try(	InputStream is = new FileInputStream("G://object.xml");
				XMLDecoder xd = new XMLDecoder(is);){
			User user = (User) xd.readObject();
			System.out.println(user);
		/*	user = (User)xd.readObject();
			System.out.println(user);*/
		}
	}
	
	
	@Test
	public void testRandomAccessFile() throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile(new File("G://randomAccessFile.txt"), "rw");
		String cn = "中国";
		randomAccessFile.write(cn.getBytes());
		cn = "测试";
		randomAccessFile.write(cn.getBytes());
		byte[] buffer = new byte[64];
		int length = 0;
		StringBuffer sb = new StringBuffer();
		randomAccessFile.seek("中国".getBytes().length);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while((length = randomAccessFile.read(buffer))!=-1) {
			out.write(buffer, 0, length);
		}
		out.flush();
		System.out.println(new String(out.toByteArray()));
	}
	
	
	
	
	class User implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String name;
		
		private String info;

		public User(String name, String info) {
			super();
			this.name = name;
			this.info = info;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		@Override
		public String toString() {
			return "User [name=" + name + ", info=" + info + "]";
		}
		
	}
	
	
	
	
}
