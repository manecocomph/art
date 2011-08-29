package com.maneco.art.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RandomAccessFile raf = new RandomAccessFile("C:/skype_log.txt", "rw");
			FileChannel fc = raf.getChannel();
			ByteBuffer bb = ByteBuffer.allocate(40000);
			int count = fc.read(bb);
			
			while (-1 != count) {
				bb.flip();
				
				
				while (bb.hasRemaining()) {
					System.out.print(bb.getChar());
				}
				
				bb.clear();
				count = fc.read(bb);
			}
			
			fc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
