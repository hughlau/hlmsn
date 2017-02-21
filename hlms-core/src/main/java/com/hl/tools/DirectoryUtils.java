package com.hl.tools;

import java.io.File;
import java.util.List;


public class DirectoryUtils {

	public static void getFilePathByArea(File file,List<String> fileList){
		File[] array=file.listFiles();
		if(array!=null){
			for (int i = 0; i < array.length; i++) {
				if(array[i].isDirectory()){
					try {
						getFilePathByArea(array[i],fileList);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}else{
					String personPath=array[i].getPath();
					fileList.add(personPath);
				}
			}
		}
	}
}
