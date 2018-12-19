package com.seehope.util.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.zip.ZipOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {
	private static final Log _Log = LogFactory.getLog(FileUtil.class);

	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdirs();
			}
		} catch (Exception e) {
			_Log.error("����������������");
			e.printStackTrace();
		}
	}

	public static void newFile(String filePathAndName, String fileContent) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();
		} catch (Exception e) {
			_Log.error("����������������");
			e.printStackTrace();
		}
	}

	public static boolean exists(String filePath) {
		try {
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				return false;
			}
			return true;
		} catch (Exception e) {
			_Log.error("����������");
		}
		return false;
	}

	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			myDelFile.delete();
		} catch (Exception e) {
			_Log.error("����������������");
		}
	}

	public static void delFile(String path, String name) {
		try {
			File f = new File(path);
			File[] lst = f.listFiles(new FileFilterUtil(name));
			for (int i = 0; i < lst.length; i++) {
				if (lst[i].isFile()) {
					lst[i].delete();
				}
			}
		} catch (Exception e) {
			_Log.error("����������������");
		}
	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath);
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete();
		} catch (Exception e) {
			_Log.error("������������������");
		}
	}

	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);
				delFolder(path + "/" + tempList[i]);
			}
		}
	}

	/* Error */
	public static void copyFile(String oldPath, String newPath) {
		// Byte code:
		// 0: aconst_null
		// 1: astore_2
		// 2: aconst_null
		// 3: astore_3
		// 4: iconst_0
		// 5: istore 4
		// 7: new 33 java/io/File
		// 10: dup
		// 11: aload_0
		// 12: invokespecial 35 java/io/File:<init> (Ljava/lang/String;)V
		// 15: astore 5
		// 17: aload 5
		// 19: invokevirtual 37 java/io/File:exists ()Z
		// 22: ifeq +141 -> 163
		// 25: aload_1
		// 26: ldc -94
		// 28: ldc -104
		// 30: invokevirtual 164 java/lang/String:replace
		// (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
		// 33: astore_1
		// 34: aload_1
		// 35: iconst_0
		// 36: aload_1
		// 37: ldc -104
		// 39: invokevirtual 168 java/lang/String:lastIndexOf (Ljava/lang/String;)I
		// 42: invokevirtual 172 java/lang/String:substring (II)Ljava/lang/String;
		// 45: invokestatic 176 com/seehope/util/file/FileUtil:newFolder
		// (Ljava/lang/String;)V
		// 48: new 178 java/io/FileInputStream
		// 51: dup
		// 52: aload_0
		// 53: invokespecial 180 java/io/FileInputStream:<init> (Ljava/lang/String;)V
		// 56: astore_2
		// 57: new 181 java/io/FileOutputStream
		// 60: dup
		// 61: aload_1
		// 62: invokespecial 183 java/io/FileOutputStream:<init> (Ljava/lang/String;)V
		// 65: astore_3
		// 66: sipush 1024
		// 69: newarray <illegal type>
		// 71: astore 6
		// 73: goto +12 -> 85
		// 76: aload_3
		// 77: aload 6
		// 79: iconst_0
		// 80: iload 4
		// 82: invokevirtual 184 java/io/FileOutputStream:write ([BII)V
		// 85: aload_2
		// 86: aload 6
		// 88: invokevirtual 188 java/io/InputStream:read ([B)I
		// 91: dup
		// 92: istore 4
		// 94: iconst_m1
		// 95: if_icmpne -19 -> 76
		// 98: goto +65 -> 163
		// 101: astore 4
		// 103: getstatic 16 com/seehope/util/file/FileUtil:_Log
		// Lorg/apache/commons/logging/Log;
		// 106: ldc -62
		// 108: invokeinterface 46 2 0
		// 113: aload_2
		// 114: ifnull +7 -> 121
		// 117: aload_2
		// 118: invokevirtual 196 java/io/InputStream:close ()V
		// 121: aload_3
		// 122: ifnull +62 -> 184
		// 125: aload_3
		// 126: invokevirtual 197 java/io/FileOutputStream:close ()V
		// 129: goto +55 -> 184
		// 132: astore 8
		// 134: goto +50 -> 184
		// 137: astore 7
		// 139: aload_2
		// 140: ifnull +7 -> 147
		// 143: aload_2
		// 144: invokevirtual 196 java/io/InputStream:close ()V
		// 147: aload_3
		// 148: ifnull +12 -> 160
		// 151: aload_3
		// 152: invokevirtual 197 java/io/FileOutputStream:close ()V
		// 155: goto +5 -> 160
		// 158: astore 8
		// 160: aload 7
		// 162: athrow
		// 163: aload_2
		// 164: ifnull +7 -> 171
		// 167: aload_2
		// 168: invokevirtual 196 java/io/InputStream:close ()V
		// 171: aload_3
		// 172: ifnull +12 -> 184
		// 175: aload_3
		// 176: invokevirtual 197 java/io/FileOutputStream:close ()V
		// 179: goto +5 -> 184
		// 182: astore 8
		// 184: return
		// Line number table:
		// Java source line #199 -> byte code offset #0
		// Java source line #200 -> byte code offset #2
		// Java source line #203 -> byte code offset #4
		// Java source line #204 -> byte code offset #7
		// Java source line #205 -> byte code offset #17
		// Java source line #207 -> byte code offset #25
		// Java source line #208 -> byte code offset #34
		// Java source line #209 -> byte code offset #48
		// Java source line #210 -> byte code offset #57
		// Java source line #211 -> byte code offset #66
		// Java source line #213 -> byte code offset #73
		// Java source line #215 -> byte code offset #76
		// Java source line #213 -> byte code offset #85
		// Java source line #219 -> byte code offset #98
		// Java source line #220 -> byte code offset #103
		// Java source line #223 -> byte code offset #113
		// Java source line #224 -> byte code offset #121
		// Java source line #225 -> byte code offset #129
		// Java source line #221 -> byte code offset #137
		// Java source line #223 -> byte code offset #139
		// Java source line #224 -> byte code offset #147
		// Java source line #225 -> byte code offset #155
		// Java source line #226 -> byte code offset #160
		// Java source line #223 -> byte code offset #163
		// Java source line #224 -> byte code offset #171
		// Java source line #225 -> byte code offset #179
		// Java source line #227 -> byte code offset #184
		// Local variable table:
		// start length slot name signature
		// 0 185 0 oldPath String
		// 0 185 1 newPath String
		// 1 167 2 inStream java.io.InputStream
		// 3 173 3 fs FileOutputStream
		// 5 88 4 byteread int
		// 101 3 4 e Exception
		// 15 3 5 oldfile File
		// 71 16 6 buffer byte[]
		// 137 24 7 localObject Object
		// 132 1 8 localException1 Exception
		// 158 1 8 localException2 Exception
		// 182 1 8 localException3 Exception
		// Exception table:
		// from to target type
		// 4 98 101 java/lang/Exception
		// 113 129 132 java/lang/Exception
		// 4 113 137 finally
		// 139 155 158 java/lang/Exception
		// 163 179 182 java/lang/Exception
	}

	/* Error */
	public static void copyFolder(String oldPath, String newPath) {
		// Byte code:
		// 0: aconst_null
		// 1: astore_2
		// 2: aconst_null
		// 3: astore_3
		// 4: new 33 java/io/File
		// 7: dup
		// 8: aload_1
		// 9: invokespecial 35 java/io/File:<init> (Ljava/lang/String;)V
		// 12: invokevirtual 41 java/io/File:mkdirs ()Z
		// 15: pop
		// 16: new 33 java/io/File
		// 19: dup
		// 20: aload_0
		// 21: invokespecial 35 java/io/File:<init> (Ljava/lang/String;)V
		// 24: astore 4
		// 26: aload 4
		// 28: invokevirtual 130 java/io/File:list ()[Ljava/lang/String;
		// 31: astore 5
		// 33: aconst_null
		// 34: astore 6
		// 36: iconst_0
		// 37: istore 7
		// 39: goto +240 -> 279
		// 42: aload_0
		// 43: getstatic 134 java/io/File:separator Ljava/lang/String;
		// 46: invokevirtual 137 java/lang/String:endsWith (Ljava/lang/String;)Z
		// 49: ifeq +37 -> 86
		// 52: new 33 java/io/File
		// 55: dup
		// 56: new 140 java/lang/StringBuilder
		// 59: dup
		// 60: aload_0
		// 61: invokestatic 142 java/lang/String:valueOf
		// (Ljava/lang/Object;)Ljava/lang/String;
		// 64: invokespecial 146 java/lang/StringBuilder:<init> (Ljava/lang/String;)V
		// 67: aload 5
		// 69: iload 7
		// 71: aaload
		// 72: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 75: invokevirtual 151 java/lang/StringBuilder:toString ()Ljava/lang/String;
		// 78: invokespecial 35 java/io/File:<init> (Ljava/lang/String;)V
		// 81: astore 6
		// 83: goto +40 -> 123
		// 86: new 33 java/io/File
		// 89: dup
		// 90: new 140 java/lang/StringBuilder
		// 93: dup
		// 94: aload_0
		// 95: invokestatic 142 java/lang/String:valueOf
		// (Ljava/lang/Object;)Ljava/lang/String;
		// 98: invokespecial 146 java/lang/StringBuilder:<init> (Ljava/lang/String;)V
		// 101: getstatic 134 java/io/File:separator Ljava/lang/String;
		// 104: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 107: aload 5
		// 109: iload 7
		// 111: aaload
		// 112: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 115: invokevirtual 151 java/lang/StringBuilder:toString ()Ljava/lang/String;
		// 118: invokespecial 35 java/io/File:<init> (Ljava/lang/String;)V
		// 121: astore 6
		// 123: aload 6
		// 125: invokevirtual 110 java/io/File:isFile ()Z
		// 128: ifeq +83 -> 211
		// 131: new 178 java/io/FileInputStream
		// 134: dup
		// 135: aload 6
		// 137: invokespecial 212 java/io/FileInputStream:<init> (Ljava/io/File;)V
		// 140: astore_2
		// 141: new 181 java/io/FileOutputStream
		// 144: dup
		// 145: new 140 java/lang/StringBuilder
		// 148: dup
		// 149: aload_1
		// 150: invokestatic 142 java/lang/String:valueOf
		// (Ljava/lang/Object;)Ljava/lang/String;
		// 153: invokespecial 146 java/lang/StringBuilder:<init> (Ljava/lang/String;)V
		// 156: ldc -104
		// 158: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 161: aload 6
		// 163: invokevirtual 213 java/io/File:getName ()Ljava/lang/String;
		// 166: invokevirtual 27 java/lang/String:toString ()Ljava/lang/String;
		// 169: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 172: invokevirtual 151 java/lang/StringBuilder:toString ()Ljava/lang/String;
		// 175: invokespecial 183 java/io/FileOutputStream:<init> (Ljava/lang/String;)V
		// 178: astore_3
		// 179: sipush 5120
		// 182: newarray <illegal type>
		// 184: astore 8
		// 186: goto +12 -> 198
		// 189: aload_3
		// 190: aload 8
		// 192: iconst_0
		// 193: iload 9
		// 195: invokevirtual 184 java/io/FileOutputStream:write ([BII)V
		// 198: aload_2
		// 199: aload 8
		// 201: invokevirtual 216 java/io/FileInputStream:read ([B)I
		// 204: dup
		// 205: istore 9
		// 207: iconst_m1
		// 208: if_icmpne -19 -> 189
		// 211: aload 6
		// 213: invokevirtual 127 java/io/File:isDirectory ()Z
		// 216: ifeq +60 -> 276
		// 219: new 140 java/lang/StringBuilder
		// 222: dup
		// 223: aload_0
		// 224: invokestatic 142 java/lang/String:valueOf
		// (Ljava/lang/Object;)Ljava/lang/String;
		// 227: invokespecial 146 java/lang/StringBuilder:<init> (Ljava/lang/String;)V
		// 230: ldc -104
		// 232: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 235: aload 5
		// 237: iload 7
		// 239: aaload
		// 240: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 243: invokevirtual 151 java/lang/StringBuilder:toString ()Ljava/lang/String;
		// 246: new 140 java/lang/StringBuilder
		// 249: dup
		// 250: aload_1
		// 251: invokestatic 142 java/lang/String:valueOf
		// (Ljava/lang/Object;)Ljava/lang/String;
		// 254: invokespecial 146 java/lang/StringBuilder:<init> (Ljava/lang/String;)V
		// 257: ldc -104
		// 259: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 262: aload 5
		// 264: iload 7
		// 266: aaload
		// 267: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 270: invokevirtual 151 java/lang/StringBuilder:toString ()Ljava/lang/String;
		// 273: invokestatic 217 com/seehope/util/file/FileUtil:copyFolder
		// (Ljava/lang/String;Ljava/lang/String;)V
		// 276: iinc 7 1
		// 279: iload 7
		// 281: aload 5
		// 283: arraylength
		// 284: if_icmplt -242 -> 42
		// 287: goto +65 -> 352
		// 290: astore 4
		// 292: getstatic 16 com/seehope/util/file/FileUtil:_Log
		// Lorg/apache/commons/logging/Log;
		// 295: ldc -37
		// 297: invokeinterface 46 2 0
		// 302: aload_3
		// 303: ifnull +7 -> 310
		// 306: aload_3
		// 307: invokevirtual 197 java/io/FileOutputStream:close ()V
		// 310: aload_2
		// 311: ifnull +62 -> 373
		// 314: aload_2
		// 315: invokevirtual 221 java/io/FileInputStream:close ()V
		// 318: goto +55 -> 373
		// 321: astore 11
		// 323: goto +50 -> 373
		// 326: astore 10
		// 328: aload_3
		// 329: ifnull +7 -> 336
		// 332: aload_3
		// 333: invokevirtual 197 java/io/FileOutputStream:close ()V
		// 336: aload_2
		// 337: ifnull +12 -> 349
		// 340: aload_2
		// 341: invokevirtual 221 java/io/FileInputStream:close ()V
		// 344: goto +5 -> 349
		// 347: astore 11
		// 349: aload 10
		// 351: athrow
		// 352: aload_3
		// 353: ifnull +7 -> 360
		// 356: aload_3
		// 357: invokevirtual 197 java/io/FileOutputStream:close ()V
		// 360: aload_2
		// 361: ifnull +12 -> 373
		// 364: aload_2
		// 365: invokevirtual 221 java/io/FileInputStream:close ()V
		// 368: goto +5 -> 373
		// 371: astore 11
		// 373: return
		// Line number table:
		// Java source line #239 -> byte code offset #0
		// Java source line #240 -> byte code offset #2
		// Java source line #242 -> byte code offset #4
		// Java source line #243 -> byte code offset #16
		// Java source line #244 -> byte code offset #26
		// Java source line #245 -> byte code offset #33
		// Java source line #246 -> byte code offset #36
		// Java source line #247 -> byte code offset #42
		// Java source line #248 -> byte code offset #52
		// Java source line #249 -> byte code offset #83
		// Java source line #250 -> byte code offset #86
		// Java source line #253 -> byte code offset #123
		// Java source line #254 -> byte code offset #131
		// Java source line #255 -> byte code offset #141
		// Java source line #256 -> byte code offset #156
		// Java source line #255 -> byte code offset #175
		// Java source line #257 -> byte code offset #179
		// Java source line #259 -> byte code offset #186
		// Java source line #260 -> byte code offset #189
		// Java source line #259 -> byte code offset #198
		// Java source line #263 -> byte code offset #211
		// Java source line #264 -> byte code offset #219
		// Java source line #246 -> byte code offset #276
		// Java source line #267 -> byte code offset #287
		// Java source line #268 -> byte code offset #292
		// Java source line #271 -> byte code offset #302
		// Java source line #272 -> byte code offset #310
		// Java source line #273 -> byte code offset #318
		// Java source line #269 -> byte code offset #326
		// Java source line #271 -> byte code offset #328
		// Java source line #272 -> byte code offset #336
		// Java source line #273 -> byte code offset #344
		// Java source line #276 -> byte code offset #349
		// Java source line #271 -> byte code offset #352
		// Java source line #272 -> byte code offset #360
		// Java source line #273 -> byte code offset #368
		// Java source line #277 -> byte code offset #373
		// Local variable table:
		// start length slot name signature
		// 0 374 0 oldPath String
		// 0 374 1 newPath String
		// 1 364 2 input java.io.FileInputStream
		// 3 354 3 output FileOutputStream
		// 24 3 4 a File
		// 290 3 4 e Exception
		// 31 251 5 file String[]
		// 34 178 6 temp File
		// 37 243 7 i int
		// 184 16 8 b byte[]
		// 189 5 9 len int
		// 205 3 9 len int
		// 326 24 10 localObject Object
		// 321 1 11 localException1 Exception
		// 347 1 11 localException2 Exception
		// 371 1 11 localException3 Exception
		// Exception table:
		// from to target type
		// 4 287 290 java/lang/Exception
		// 302 318 321 java/lang/Exception
		// 4 302 326 finally
		// 328 344 347 java/lang/Exception
		// 352 368 371 java/lang/Exception
	}

	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/* Error */
	public static void zipFile(String oripath, String zipfile) {
		// Byte code:
		// 0: aconst_null
		// 1: astore_2
		// 2: new 33 java/io/File
		// 5: dup
		// 6: aload_0
		// 7: invokespecial 35 java/io/File:<init> (Ljava/lang/String;)V
		// 10: astore_3
		// 11: aload_3
		// 12: invokevirtual 37 java/io/File:exists ()Z
		// 15: ifne +4 -> 19
		// 18: return
		// 19: new 181 java/io/FileOutputStream
		// 22: dup
		// 23: aload_1
		// 24: invokespecial 183 java/io/FileOutputStream:<init> (Ljava/lang/String;)V
		// 27: astore 4
		// 29: new 235 java/util/zip/ZipOutputStream
		// 32: dup
		// 33: aload 4
		// 35: invokespecial 237 java/util/zip/ZipOutputStream:<init>
		// (Ljava/io/OutputStream;)V
		// 38: astore 5
		// 40: aload_3
		// 41: ldc -16
		// 43: aload 5
		// 45: invokestatic 242 com/seehope/util/file/FileUtil:writeZip
		// (Ljava/io/File;Ljava/lang/String;Ljava/util/zip/ZipOutputStream;)V
		// 48: aload 5
		// 50: invokevirtual 246 java/util/zip/ZipOutputStream:close ()V
		// 53: goto +43 -> 96
		// 56: astore 4
		// 58: aload 4
		// 60: invokevirtual 52 java/lang/Exception:printStackTrace ()V
		// 63: new 247 java/lang/RuntimeException
		// 66: dup
		// 67: aload 4
		// 69: invokespecial 249 java/lang/RuntimeException:<init>
		// (Ljava/lang/Throwable;)V
		// 72: athrow
		// 73: astore 6
		// 75: aload_2
		// 76: ifnull +17 -> 93
		// 79: aload_2
		// 80: invokevirtual 252 java/io/BufferedInputStream:close ()V
		// 83: goto +10 -> 93
		// 86: astore 7
		// 88: aload 7
		// 90: invokevirtual 52 java/lang/Exception:printStackTrace ()V
		// 93: aload 6
		// 95: athrow
		// 96: aload_2
		// 97: ifnull +17 -> 114
		// 100: aload_2
		// 101: invokevirtual 252 java/io/BufferedInputStream:close ()V
		// 104: goto +10 -> 114
		// 107: astore 7
		// 109: aload 7
		// 111: invokevirtual 52 java/lang/Exception:printStackTrace ()V
		// 114: return
		// Line number table:
		// Java source line #313 -> byte code offset #0
		// Java source line #314 -> byte code offset #2
		// Java source line #315 -> byte code offset #11
		// Java source line #317 -> byte code offset #19
		// Java source line #318 -> byte code offset #29
		// Java source line #319 -> byte code offset #40
		// Java source line #320 -> byte code offset #48
		// Java source line #321 -> byte code offset #53
		// Java source line #322 -> byte code offset #58
		// Java source line #323 -> byte code offset #63
		// Java source line #324 -> byte code offset #73
		// Java source line #326 -> byte code offset #75
		// Java source line #327 -> byte code offset #83
		// Java source line #328 -> byte code offset #93
		// Java source line #326 -> byte code offset #96
		// Java source line #327 -> byte code offset #104
		// Java source line #329 -> byte code offset #114
		// Local variable table:
		// start length slot name signature
		// 0 115 0 oripath String
		// 0 115 1 zipfile String
		// 1 100 2 bis java.io.BufferedInputStream
		// 10 31 3 floder File
		// 27 7 4 fos FileOutputStream
		// 56 12 4 e Exception
		// 38 11 5 out ZipOutputStream
		// 73 21 6 localObject Object
		// 86 3 7 e Exception
		// 107 3 7 e Exception
		// Exception table:
		// from to target type
		// 19 53 56 java/lang/Exception
		// 19 73 73 finally
		// 75 83 86 java/lang/Exception
		// 96 104 107 java/lang/Exception
	}

	public static void writeZip(String filepath, String innnerpath, String zippath) {
		try {
			File file = new File(filepath);
			FileOutputStream fos = new FileOutputStream(zippath);
			ZipOutputStream out = new ZipOutputStream(fos);
			writeZip(file, innnerpath, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/* Error */
	public static void writeZip(File file, String innnerpath, ZipOutputStream out) {
		// Byte code:
		// 0: aload_0
		// 1: invokevirtual 37 java/io/File:exists ()Z
		// 4: ifne +4 -> 8
		// 7: return
		// 8: aload_0
		// 9: invokevirtual 127 java/io/File:isDirectory ()Z
		// 12: ifne +168 -> 180
		// 15: aconst_null
		// 16: astore_3
		// 17: new 253 java/io/BufferedInputStream
		// 20: dup
		// 21: new 178 java/io/FileInputStream
		// 24: dup
		// 25: aload_0
		// 26: invokespecial 212 java/io/FileInputStream:<init> (Ljava/io/File;)V
		// 29: invokespecial 271 java/io/BufferedInputStream:<init>
		// (Ljava/io/InputStream;)V
		// 32: astore_3
		// 33: new 274 java/util/zip/ZipEntry
		// 36: dup
		// 37: new 140 java/lang/StringBuilder
		// 40: dup
		// 41: aload_1
		// 42: invokestatic 142 java/lang/String:valueOf
		// (Ljava/lang/Object;)Ljava/lang/String;
		// 45: invokespecial 146 java/lang/StringBuilder:<init> (Ljava/lang/String;)V
		// 48: aload_0
		// 49: invokevirtual 213 java/io/File:getName ()Ljava/lang/String;
		// 52: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 55: invokevirtual 151 java/lang/StringBuilder:toString ()Ljava/lang/String;
		// 58: invokespecial 276 java/util/zip/ZipEntry:<init> (Ljava/lang/String;)V
		// 61: astore 4
		// 63: aload_2
		// 64: aload 4
		// 66: invokevirtual 277 java/util/zip/ZipOutputStream:putNextEntry
		// (Ljava/util/zip/ZipEntry;)V
		// 69: ldc_w 281
		// 72: newarray <illegal type>
		// 74: astore 6
		// 76: goto +12 -> 88
		// 79: aload_2
		// 80: aload 6
		// 82: iconst_0
		// 83: iload 5
		// 85: invokevirtual 282 java/util/zip/ZipOutputStream:write ([BII)V
		// 88: aload_3
		// 89: aload 6
		// 91: invokevirtual 283 java/io/BufferedInputStream:read ([B)I
		// 94: dup
		// 95: istore 5
		// 97: iconst_m1
		// 98: if_icmpne -19 -> 79
		// 101: aload_2
		// 102: invokevirtual 284 java/util/zip/ZipOutputStream:flush ()V
		// 105: goto +54 -> 159
		// 108: astore 4
		// 110: aload 4
		// 112: invokevirtual 287 java/io/IOException:printStackTrace ()V
		// 115: aload_3
		// 116: ifnull +152 -> 268
		// 119: aload_3
		// 120: invokevirtual 252 java/io/BufferedInputStream:close ()V
		// 123: goto +145 -> 268
		// 126: astore 8
		// 128: aload 8
		// 130: invokevirtual 287 java/io/IOException:printStackTrace ()V
		// 133: goto +135 -> 268
		// 136: astore 7
		// 138: aload_3
		// 139: ifnull +17 -> 156
		// 142: aload_3
		// 143: invokevirtual 252 java/io/BufferedInputStream:close ()V
		// 146: goto +10 -> 156
		// 149: astore 8
		// 151: aload 8
		// 153: invokevirtual 287 java/io/IOException:printStackTrace ()V
		// 156: aload 7
		// 158: athrow
		// 159: aload_3
		// 160: ifnull +108 -> 268
		// 163: aload_3
		// 164: invokevirtual 252 java/io/BufferedInputStream:close ()V
		// 167: goto +101 -> 268
		// 170: astore 8
		// 172: aload 8
		// 174: invokevirtual 287 java/io/IOException:printStackTrace ()V
		// 177: goto +91 -> 268
		// 180: new 140 java/lang/StringBuilder
		// 183: dup
		// 184: aload_1
		// 185: invokestatic 142 java/lang/String:valueOf
		// (Ljava/lang/Object;)Ljava/lang/String;
		// 188: invokespecial 146 java/lang/StringBuilder:<init> (Ljava/lang/String;)V
		// 191: aload_0
		// 192: invokevirtual 213 java/io/File:getName ()Ljava/lang/String;
		// 195: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 198: getstatic 134 java/io/File:separator Ljava/lang/String;
		// 201: invokevirtual 147 java/lang/StringBuilder:append
		// (Ljava/lang/String;)Ljava/lang/StringBuilder;
		// 204: invokevirtual 151 java/lang/StringBuilder:toString ()Ljava/lang/String;
		// 207: astore_1
		// 208: aload_0
		// 209: invokevirtual 290 java/io/File:listFiles ()[Ljava/io/File;
		// 212: astore_3
		// 213: aload_3
		// 214: arraylength
		// 215: ifne +28 -> 243
		// 218: aload_2
		// 219: new 274 java/util/zip/ZipEntry
		// 222: dup
		// 223: aload_1
		// 224: invokespecial 276 java/util/zip/ZipEntry:<init> (Ljava/lang/String;)V
		// 227: invokevirtual 277 java/util/zip/ZipOutputStream:putNextEntry
		// (Ljava/util/zip/ZipEntry;)V
		// 230: goto +38 -> 268
		// 233: astore 4
		// 235: aload 4
		// 237: invokevirtual 287 java/io/IOException:printStackTrace ()V
		// 240: goto +28 -> 268
		// 243: iconst_0
		// 244: istore 4
		// 246: goto +15 -> 261
		// 249: aload_3
		// 250: iload 4
		// 252: aaload
		// 253: aload_1
		// 254: aload_2
		// 255: invokestatic 242 com/seehope/util/file/FileUtil:writeZip
		// (Ljava/io/File;Ljava/lang/String;Ljava/util/zip/ZipOutputStream;)V
		// 258: iinc 4 1
		// 261: iload 4
		// 263: aload_3
		// 264: arraylength
		// 265: if_icmplt -16 -> 249
		// 268: return
		// Line number table:
		// Java source line #356 -> byte code offset #0
		// Java source line #357 -> byte code offset #8
		// Java source line #358 -> byte code offset #15
		// Java source line #360 -> byte code offset #17
		// Java source line #361 -> byte code offset #33
		// Java source line #362 -> byte code offset #63
		// Java source line #364 -> byte code offset #69
		// Java source line #365 -> byte code offset #76
		// Java source line #366 -> byte code offset #79
		// Java source line #365 -> byte code offset #88
		// Java source line #368 -> byte code offset #101
		// Java source line #369 -> byte code offset #105
		// Java source line #370 -> byte code offset #110
		// Java source line #372 -> byte code offset #115
		// Java source line #374 -> byte code offset #119
		// Java source line #375 -> byte code offset #123
		// Java source line #376 -> byte code offset #128
		// Java source line #371 -> byte code offset #136
		// Java source line #372 -> byte code offset #138
		// Java source line #374 -> byte code offset #142
		// Java source line #375 -> byte code offset #146
		// Java source line #376 -> byte code offset #151
		// Java source line #379 -> byte code offset #156
		// Java source line #372 -> byte code offset #159
		// Java source line #374 -> byte code offset #163
		// Java source line #375 -> byte code offset #167
		// Java source line #376 -> byte code offset #172
		// Java source line #380 -> byte code offset #177
		// Java source line #381 -> byte code offset #180
		// Java source line #382 -> byte code offset #208
		// Java source line #383 -> byte code offset #213
		// Java source line #385 -> byte code offset #218
		// Java source line #386 -> byte code offset #230
		// Java source line #387 -> byte code offset #235
		// Java source line #389 -> byte code offset #240
		// Java source line #390 -> byte code offset #243
		// Java source line #391 -> byte code offset #249
		// Java source line #390 -> byte code offset #258
		// Java source line #395 -> byte code offset #268
		// Local variable table:
		// start length slot name signature
		// 0 269 0 file File
		// 0 269 1 innnerpath String
		// 0 269 2 out ZipOutputStream
		// 16 148 3 bis java.io.BufferedInputStream
		// 212 52 3 files File[]
		// 61 4 4 entry java.util.zip.ZipEntry
		// 108 3 4 e java.io.IOException
		// 233 3 4 e java.io.IOException
		// 244 18 4 i int
		// 79 5 5 count int
		// 95 3 5 count int
		// 74 16 6 data byte[]
		// 136 21 7 localObject Object
		// 126 3 8 e java.io.IOException
		// 149 3 8 e java.io.IOException
		// 170 3 8 e java.io.IOException
		// Exception table:
		// from to target type
		// 17 105 108 java/io/IOException
		// 119 123 126 java/io/IOException
		// 17 115 136 finally
		// 142 146 149 java/io/IOException
		// 163 167 170 java/io/IOException
		// 218 230 233 java/io/IOException
	}

	public static void main(String[] args) {
		newFile("d://dirtest/dirteset/1.txt", "����������");
	}
}
