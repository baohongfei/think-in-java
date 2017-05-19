package com.baohongfei.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileUtil
{
	public static void createDirRecursive(String dirPath)
	{
		File dir = new File(dirPath);
		// rootDir.mkdir();
		dir.mkdirs();
	}
	
	public static boolean deleteDirRecursive(String dirPath)
	{
		File dir = new File(dirPath);
		if (dir.isDirectory())
		{
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++)
			{
				boolean success = deleteDirRecursive(children[i].getAbsolutePath());
				if (!success)
				{
					return false;
				}
			}
		}
		return dir.delete();
	}
	
	/**
	 * 如果文件所在的目录不存在，创建不成功
	 * 
	 * @param fileFullPath
	 */
	public static void createFile(String fileFullPath)
	{
		File file = new File(fileFullPath);
		try
		{
			file.createNewFile();
		} catch (IOException e)
		{
			System.out.println("createFile failed,param is " + fileFullPath);
			e.printStackTrace();
		}
	}
	
	public  static Collection<File> listFiles(String rootDor)
	{
		File root = new File(rootDor);
		List<File> files = new ArrayList<File>();
		listFiles(files, root);
		return files;
	}

	private static void listFiles(List<File> files, File dir)
	{
		File[] listFiles = dir.listFiles();
		for (File f : listFiles)
		{
			if (f.isFile())
			{
				files.add(f);
			} else if (f.isDirectory())
			{
				listFiles(files, f);
			}
		}
	}

}
