package com.yuanrong.common.util;

import java.io.*;
import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 文件工具类
 */
public class FileUtil {

	/**
	 * 删除指定路径的文件
	 * @param strPath
	 */
	public static void remove(String strPath) {
		File fl = new File(strPath);
		if (fl.exists()) {
			fl.delete();
		}
	}
	
	public static String replaceToRelativePath(String fullFileName) {
		return fullFileName.replaceAll("\\\\","/");
	}

	public static String getClassFilePath(Class classObject) throws UnsupportedEncodingException {
		    String basePath = classObject.getProtectionDomain().getCodeSource().getLocation().getPath();
		    basePath = URLDecoder.decode(basePath, "utf-8");
		    if (basePath.endsWith(".jar")) {
		      basePath = basePath.substring(0, basePath.lastIndexOf("/") + 1);
		     }
		     File f = new File(basePath);
		    basePath = f.getAbsolutePath();
		    return basePath;
	}
    /**   
     * 删除单个文件   
     * @param   fileName    被删除文件的文件名   
     * @return 单个文件删除成功返回true,否则返回false   
     */    
    public static boolean deleteFile(String fileName){     
        File file = new File(fileName);     
        if(file.isFile() && file.exists()){     
            file.delete();     
            //System.out.println("删除单个文件"+fileName+"成功！");     
            return true;     
        }else{     
            //System.out.println("删除单个文件"+fileName+"失败！");     
            return false;     
        }     
    } 
	
    /**   
     * 删除目录（文件夹）以及目录下的文件   
     * @param   dir 被删除目录的文件路径   
     * @return  目录删除成功返回true,否则返回false   
     */    
    public static boolean deleteDirectory(String dir){     
        //如果dir不以文件分隔符结尾，自动添加文件分隔符     
        if(!dir.endsWith(File.separator)){     
            dir = dir+File.separator;     
        }     
        File dirFile = new File(dir);     
        //如果dir对应的文件不存在，或者不是一个目录，则退出     
        if(!dirFile.exists() || !dirFile.isDirectory()){     
            //System.out.println("删除目录失败"+dir+"目录不存在！");     
            return false;     
        }     
        boolean flag = true;     
        //删除文件夹下的所有文件(包括子目录)     
        File[] files = dirFile.listFiles();   
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				// 删除子文件
				if (files[i].isFile()) {
					flag = deleteFile(files[i].getAbsolutePath());
					if (!flag) {
						break;
					}
				} else {
					// 删除子目录
					flag = deleteDirectory(files[i].getAbsolutePath());
					if (!flag) {
						break;
					}
				}
			}
		}
             
        if(!flag){     
            //System.out.println("删除目录失败");     
            return false;     
        }     
             
        //删除当前目录     
        if(dirFile.delete()){     
            //System.out.println("删除目录"+dir+"成功！");     
            return true;     
        }else{     
            //System.out.println("删除目录"+dir+"失败！");     
            return false;     
        }     
    }

	/**
	 * 创建文件
	 * @param flFlag
	 * @param strContent
	 */
	public static void createFile(String strFilePath, String strContent) {
		// 文件输出流
		FileOutputStream fos = null;
		try {
			// 创建文件输出流
			fos = new FileOutputStream(strFilePath);
			// 输出内容
			fos.write(strContent.getBytes());
			fos.flush();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			// 关闭文件输出流
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * 复制文件
	 * @param strSrcFile
	 * @param strDesFile
	 */
	public static void copyFile(String strSrcFile, String strDesFile) {
		// 文件流对象
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// 源文件流
			File flSrc = new File(strSrcFile);
			fis = new FileInputStream(flSrc);
			bis = new BufferedInputStream(fis);
			// 文件输出流
			File flDes = new File(strDesFile);
			File flParent = flDes.getParentFile();
			if (!flParent.exists()) {
				flParent.mkdirs();
			}
			fos = new FileOutputStream(flDes);
			bos = new BufferedOutputStream(fos);
			// 读取 写入
			byte[] baBuffer = new byte[100 * 1024];
			while (true) {
				// 读取
				int iValidLength = bis.read(baBuffer);
				if (iValidLength > 0) {
					bos.write(baBuffer, 0, iValidLength);
					bos.flush();
				} else if (iValidLength == -1) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将制定的文件复制到指定的文件
	 * @param string
	 * @param string2
	 */
	public static void copy(String strSrcFile, String strDesFile) {
		// 源文件对象
		File flSrc = new File(strSrcFile);
		if (flSrc.isFile()) {
			// 文件
			copyFile(strSrcFile, strDesFile);
		} else if (flSrc.isDirectory()) {
			// 目录
			// 读取文件下所有文件对象
			File[] fls = flSrc.listFiles();
			// 逐一复制
			if (fls != null && fls.length > 0) {
				for (int i = 0; i < fls.length; i++) {
					if (fls[i].isFile()) {
						// 文件
						copyFile(fls[i].getPath(), strDesFile + "/" + fls[i].getName());
					} else if (fls[i].isDirectory()) {
						// 目录
						copy(fls[i].getPath(), strDesFile + "/" + fls[i].getName());
					}
				}
			}
		}
	}

	/**
	 * 读取没有后缀的文件名
	 * @param strFileName
	 * @return
	 */
	public static String getNoSuffixFileName(String strFileName) {
		String[] stra = strFileName.split("[.]");
		int iLength = (stra == null) ? 0 : stra.length;
		String strNoSuffixFileName = null;
		if (iLength > 1) {
			StringBuffer strb = new StringBuffer();
			for (int i = 0; i < iLength - 1; i++) {
				if (i > 0) {
					strb.append(".");
				}
				strb.append(stra[i]);
			}
			strNoSuffixFileName = strb.toString();
		} else {
			strNoSuffixFileName = stra[0];
		}
		return strNoSuffixFileName;
	}

	/**
	 * 获得去掉路径的文件名
	 * @param strFileName 包含路径的文件名
	 * @return 去掉路径的文件名
	 */
	public static String getFileNameWithoutPath(String strFileName) {
		
		String temp[] = strFileName.replaceAll("\\\\","/").split("/");
		if (temp.length > 1) {
		    return temp[temp.length - 1];
		} else {
			return strFileName;
		}
	}
	/**
	 * 读取文件名后缀
	 * @param strFileName
	 * @return
	 */
	public static String getFileNameSuffix(String strFileName) {
		String[] stra = strFileName.split("[.]");
		int iLength = (stra == null) ? 0 : stra.length;
		if (iLength > 1) {
			return stra[stra.length - 1];
		} else {
			return "";
		}
	}

	/**
	 * 读取文件内容作为二进制流
	 * @param file 文件
	 * @return 文件二进制流
	 */
	public static ByteArrayInputStream readContent(File file) throws IOException{
		ByteArrayInputStream bais = null;
		InputStream inputStream = null;
		try {
		    inputStream = new FileInputStream(file);
		
		    byte[] buffer = getBytesFromFile(file);
		    if (inputStream.read(buffer) > 0) {
		        bais = new ByteArrayInputStream(buffer);
		    }
		} finally {
		    if (null != inputStream) {
		        inputStream.close();
		    }
		}
		return bais;
	}
	
	/**
     * 读取文件流
     * 
     * @param file 文件
     * @return 文件流
     * @throws IOException
     */
    private static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = null;
        
        byte[] bytes = null;
        try {
            is = new FileInputStream(file);
            
            // 取得文件长度
            long length = file.length();
            
            if (length > Integer.MAX_VALUE) {
                throw new IOException("文件超过最大限度");
            }
        
            bytes = new byte[(int)length];
        
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            if (null != is) {
                is.close();
            }           
        }
        
        return bytes;
    }
	/**
	 * 读取SQL内容
	 * @param strPath
	 * @param strCodeType
	 * @return
	 */
	public static String readContent(String strPath, String strCodeType) {
        // 创建File对象
        File fl = new File(strPath);
        if (!fl.exists()) {
            //System.out.println("指定的文件路径：[" + strPath + "]不存在！");
            return null;
        }
        // 函数返回值
        String strResult = "";
        // 文件输入流对象
        FileInputStream fis = null;
        try {
            // 创建文件输入流对象
            fis = new FileInputStream(fl);
            // 创建字符串缓存对象
            StringBuffer strbTmp = new StringBuffer("");
            // 逐一读取数据
            while (true) {
                try {
                    // 创建缓存字节数组
                    byte[] ba = new byte[50000];
                    int iLength = fis.read(ba, 0, 50000);
                    if (iLength == -1) {
                        break;
                    }
                    // 去除多余的
                    ba = trimByteArray(ba, iLength);
                    String strTmp = new String(ba, strCodeType);
                    if (strTmp != null) {
                        strbTmp.append(strTmp);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 转换到字符串
            strResult = strbTmp.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 关闭输入流
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 返回函数值
        return strResult;
	}
	
    /**
     * 读取SQL内容
     * @param strPath
     * @param strCodeType
     * @return
     */
    public static String getSqlContent(String strPath, String strCodeType) {
        // 创建File对象
        File fl = new File(strPath);
        if (!fl.exists()) {
            //System.out.println("指定的文件路径：[" + strPath + "]不存在！");
            return null;
        }
        // 函数返回值
        String strResult = "";
        // 文件输入流对象
        FileInputStream fis = null;
        BufferedReader bf = null;
        try {
//          // 创建文件输入流对象
//          fis = new FileInputStream(fl);
//          // 创建字符串缓存对象
//          StringBuffer strbTmp = new StringBuffer("");
//          // 逐一读取数据

//          // 转换到字符串
//          strResult = strbTmp.toString();
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(fl), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            
//            while (true) {
//                try {
//                    // 创建缓存字节数组
//                    char[] ba = new char[50000];
//                    int iLength = bf.read(ba, 0, 50000);
//                    if (iLength == -1) {
//                        break;
//                    }
//                    sb.append(ba, 0, iLength);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            String sqlLine = null;
            sqlLine = bf.readLine();
            if (sqlLine != null && !"".equals(sqlLine)) {
                byte[] lineBytes = sqlLine.getBytes();
                if (lineBytes.length > 3) {
                    // 因为byte是带符号的，所以，-17 为16进制的EF，69为16进制的BB，-65为16进制的BF 
                    if (lineBytes[0] == -17 && lineBytes[1] == -69 && lineBytes[2] == -65) {
                        sqlLine = new String(lineBytes, 3, lineBytes.length -3);
                    } 
                }
            }
            while (sqlLine != null) {
                sb.append(sqlLine);
                sb.append(" ");
                sqlLine = bf.readLine();
            }
            
            strResult = sb.toString();
            //System.out.println(strResult);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭输入流
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        // 返回函数值
        return strResult;
    }
	/**
	 * 只读取有效字符
	 * @param ba
	 * @param iLength
	 * @return
	 */
	private static byte[] trimByteArray(byte[] ba, int iLength) {
		// 字节数据不能为空
		if (ba == null || ba.length == 0 || iLength <= 0) {
			return null;
		}
		// 创建新的字节数组
		byte[] baNew = new byte[iLength];
		// 赋值
		for (int i = 0; i < iLength; i++) {
			baNew[i] = ba[i];
		}
		// 返回函数值
		return baNew;
	}

	/**
	 * 查询所有指定文件名的文件对象列表
	 * @param lstFl
	 * @param flPath
	 * @param strFileName
	 * @return
	 */
	public static List<File> queryFiles(List<File> lstFl, File flPath, String strFileName) {
		// 当前目录是否有该文件
		String strSeparator = System.getProperty("file.separator");
		File fl = new File(flPath.getPath() + strSeparator + strFileName);
		if (fl.exists()) {
			lstFl.add(fl);
		}
		// 读取所有目录
		File[] fls = flPath.listFiles();
		int iLenght = (fls == null) ? 0 : fls.length;
		for (int i = 0; i < iLenght; i++) {
			// 读取文件名称
			//String strName = fls[i].getName();
			if (fls[i].isDirectory()) {
				lstFl = queryFiles(lstFl, fls[i], strFileName);
			}
		}
		// 返回函数值
		return lstFl;
	}

	/**
	 * 文件名后缀是否相吻合
	 * @param fl
	 * @return
	 */
	public static boolean isSuffix(File fl, String strSuffix) {
		boolean b = false;
	    b = Pattern.matches("(.*)(\\." + strSuffix + ")", fl.getName());
		return b;
	}
	
	/**
	 * 文件名后缀是否相吻合
	 * @param fl
	 * @return
	 */
	public static boolean isSuffix(String fl, String strSuffix, boolean ignoreCase) {
		boolean b = false;
		if (ignoreCase) {
		    b = strSuffix.toLowerCase().equals(fl.toLowerCase());
		} else {
		    b = strSuffix.equals(fl);
		}
		return b;
	}
	
	/**
	 * 文件名后缀是否相吻合
	 * @param fl
	 * @return
	 */
	public static boolean isSuffix(File fl, String strSuffix[]) {
		int length = strSuffix.length;
		for (int i=0; i<length;i++) {
			if(!isSuffix(fl,strSuffix[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 文件名后缀是否相吻合
	 * @param fl
	 * @return
	 */
	public static boolean isSuffix(String fl, String strSuffix[]) {
		int length = strSuffix.length;
		for (int i=0; i<length;i++) {
			if(isSuffix(fl,strSuffix[i],false)) {
				return true;
			}
		}
		return false;
	}
	
    /**
     * 文件名后缀是否相吻合
     * @param fl
     * @return
     */
    public static boolean isSuffix(String fl, String strSuffix[], boolean ignoreCase) {
        int length = strSuffix.length;
        for (int i=0; i<length;i++) {
            if(isSuffix(fl,strSuffix[i],ignoreCase)) {
                return true;
            }
        }
        return false;
    }
    
	/**
	 * 判断指定路径的文件是否存在
	 * @param string
	 * @return
	 */
	public static boolean exist(String strPath) {
		File fl = new File(strPath);
		return fl.exists();
	}

	/**
	 * 是否是一个文件
	 * @param strPath
	 * @return
	 */
	public static boolean isFile(String strPath) {
		File fl = new File(strPath);
		return fl.isFile();
	}

	/**
	 * 是否是一个目录
	 * @param publishRemind
	 * @return
	 */
	public static boolean isDirectory(String strPath) {
		File fl = new File(strPath);
		return fl.isDirectory();
	}

	/**
	 * 创建指定的路径
	 * @param strPath
	 */
	public static void mkdirs(String strPath) {
		File fl = new File(strPath);
		fl.mkdirs();
	}

	/**
	 * 在文件中加一个序号
	 * @param fileName
	 * @param iIndex
	 * @return
	 */
	public static String addNameIndex(String strFileName, int iIndex) {
		// 不能为空
		if (strFileName == null) {
			return null;
		}
		if (strFileName.trim().equals("")) {
			return strFileName;
		}
		// 分割文件名
		String[] stra = strFileName.split("[.]");
		StringBuffer strb = new StringBuffer();
		if (stra.length == 1) {
			strb.append(strFileName);
			strb.append("_");
			strb.append(iIndex);
		} else {
			int i = 0;
			for (String str : stra) {
				if (i > 0) {
					strb.append(".");
				}
				strb.append(str);
				if (i == stra.length - 2) {
					strb.append("_");
					strb.append(iIndex);
				}
				i++;
			}
		}
		// 返回函数值
		return strb.toString();
	}

	public static void main(String[] args) {
		//System.out.println(addNameIndex("ddd.dd.ddd", 10));
	}

}
