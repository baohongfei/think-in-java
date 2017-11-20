package com.baohongfei.tij.file;

import java.util.ArrayList;
import java.util.List;


public class TextTest
{
	private static final String TEXT_FILE_PATH = "c:\\com\\baohongfei\\file\\text.txt";

	public static void main(String[] args)
	{
		List<String> toWriteList = new ArrayList<String>();
		toWriteList.add("在平坦的道路上");
		toWriteList.add("曲折前行");
		toWriteList.add(null);
		toWriteList.add("");
		toWriteList.add("by 许岑");
		
		TextUtil.writeFile(toWriteList, TEXT_FILE_PATH);
		List<String> read = TextUtil.readFile(TEXT_FILE_PATH);
		System.out.print(read);
	}

}
