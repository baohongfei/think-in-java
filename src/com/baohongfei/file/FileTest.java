package com.baohongfei.file;

import java.io.File;
import java.util.Collection;

public class FileTest
{
	private static final String ROOT_DIR = "c:\\com\\baohongfei\\file";

	public static void main(String[] args)
	{
		initTestFiles();

		Collection<File> listFiles = FileUtil.listFiles(ROOT_DIR);

		for (File f : listFiles)
		{
			String fileName = f.getAbsolutePath();
			System.out.println(fileName);
		}

	}

	private static void initTestFiles()
	{
		FileUtil.deleteDirRecursive(ROOT_DIR);
		FileUtil.createDirRecursive(ROOT_DIR);
		FileUtil.createFile(ROOT_DIR + "\\7-readme.txt");
		FileUtil.createFile(ROOT_DIR + "\\3-readme_again.txt");
		FileUtil.createDirRecursive(ROOT_DIR + "\\dir1");
		FileUtil.createFile(ROOT_DIR + "\\dir1\\2-readme.txt");
		FileUtil.createFile(ROOT_DIR + "\\dir1\\0-readme.txt");
		FileUtil.createDirRecursive(ROOT_DIR + "\\dir2");
		FileUtil.createFile(ROOT_DIR + "\\dir2\\5-readme.txt");
		FileUtil.createFile(ROOT_DIR + "\\dir2\\4-readme.txt");
	}

}
