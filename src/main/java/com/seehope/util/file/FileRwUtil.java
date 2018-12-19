package com.seehope.util.file;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 文件读写操作
 * 
 */
public class FileRwUtil {
	public static final String DEFAULT_CHARSET = "UTF-8";

	public static String readFileReader(String fileName) throws Exception {
		return readFileReader(fileName, DEFAULT_CHARSET);
	}

	/**
	 * 使用字符流以行读取文件内容
	 * @param fileName
	 * @param charset
	 * @return
	 * @throws Exception 
	 */
	public static String readFileReader(String fileName, String charset) throws Exception {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charset));
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return sb.toString();
	}

	public static String readFileBytes(String fileName) throws Exception {
		return readFileBytes(fileName, DEFAULT_CHARSET);
	}

	/**
	 * 使用字节流读取文件内容
	 * @param fileName
	 * @param charset
	 * @return
	 */
	public static String readFileBytes(String fileName, String charset) throws Exception {
		InputStream in = null;
		StringBuilder sb = new StringBuilder();
		try {
			byte[] b = new byte[1024];
			in = new FileInputStream(new File(fileName));
			while (in.read(b) != -1) {
				sb.append(new String(b));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 在文件最后追加内容
	 * @param fileName
	 * @param content
	 * @throws Exception
	 */
	public static void appendToFile(String fileName, String content) throws Exception {
		FileWriter writer = null;
		try {
			writer = new FileWriter(fileName, true);
			writer.write(content);
		} catch (IOException e) {
			throw e;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}

	}

	/**
	 * 把内容输出到文件
	 * @param fileName
	 * @param content
	 * @throws Exception
	 */
	public static void writerToFile(String fileName, String content) throws Exception {
		mkdirs(fileName);
		OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(new File(fileName)), DEFAULT_CHARSET);
		output.write(content);
		output.flush();
		output.close();
	}

	/**
	 * 使用流的型式保存文件
	 * @param in
	 * @param savePath
	 * @throws Exception
	 */
	public static void writerToFile(InputStream in, String savePath) throws Exception {
		mkdirs(savePath);
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream(savePath));
			byte[] buffer = new byte[1024 * 4];
			int count = 0;
			while ((count = in.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}
			out.flush();
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * 创建文件路径
	 * @param fileName
	 * @throws Exception
	 */
	public static void mkdirs(String fileName) throws Exception {
		String tempPath = fileName.replace('\\', '/');
		int idx = tempPath.lastIndexOf("/");
		if (idx != -1) {
			tempPath = tempPath.substring(0, idx);
			idx = tempPath.lastIndexOf("/");
			if (idx != -1) {
				File dirs = new File(tempPath);
				if (!dirs.exists()) {
					dirs.mkdirs();
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String str = "D://diretst/1.txt";
		mkdirs(str);
	}

}
