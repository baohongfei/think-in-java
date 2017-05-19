package com.baohongfei.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TextUtil
{
	/**
	 * LINE_SEPARATOR<br/>
	 * windows:\r\n
	 */
	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	public static void writeFile(List<String> strList, String fileFullPath)
	{
		try
		{
			File writeFile = new File(fileFullPath);
			new File(writeFile.getParent()).mkdirs();
			writeFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));

			for (String str : strList)
			{
				out.write(str + LINE_SEPARATOR);
			}
			out.flush();
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static List<String> readFile(String fileFullPath)
	{
		List<String> retList = new ArrayList<String>();
		try
		{
			File inputFlie = new File(fileFullPath);

			InputStreamReader reader = new InputStreamReader(
					new FileInputStream(inputFlie));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			line = br.readLine();
			while (line != null)
			{
				retList.add(line);
				line = br.readLine();
			}
			br.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return retList;
	}
}
