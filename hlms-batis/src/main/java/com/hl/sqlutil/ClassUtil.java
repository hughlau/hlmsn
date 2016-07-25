package com.hl.sqlutil;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {

	public static void main(String[] args) throws Exception {
		String packageName = "com.hl.entity";
		// List<String> classNames = getClassName(packageName);
		List<String> classNames = getClassName(packageName, false);
		if (classNames != null) {
			for (String className : classNames) {
				System.out.println(className);
			}
		}
	}

	/**
	 * 鑾峰彇鏌愬寘涓嬶紙鍖呮嫭璇ュ寘鐨勬墍鏈夊瓙鍖咃級鎵�湁绫�
	 * @param packageName 鍖呭悕
	 * @return 绫荤殑瀹屾暣鍚嶇О
	 */
	public static List<String> getClassName(String packageName) {
		return getClassName(packageName, true);
	}

	/**
	 * 鑾峰彇鏌愬寘涓嬫墍鏈夌被
	 * @param packageName 鍖呭悕
	 * @param childPackage 鏄惁閬嶅巻瀛愬寘
	 * @return 绫荤殑瀹屾暣鍚嶇О
	 */
	public static List<String> getClassName(String packageName, boolean childPackage) {
		List<String> fileNames = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");
		URL url = loader.getResource(packagePath);
		if (url != null) {
			String type = url.getProtocol();
			if (type.equals("file")) {
				fileNames = getClassNameByFile(url.getPath(), null, childPackage);
			} else if (type.equals("jar")) {
				fileNames = getClassNameByJar(url.getPath(), childPackage);
			}
		} else {
			fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(), packagePath, childPackage);
		}
		return fileNames;
	}

	/**
	 * 浠庨」鐩枃浠惰幏鍙栨煇鍖呬笅鎵�湁绫�
	 * @param filePath 鏂囦欢璺緞
	 * @param className 绫诲悕闆嗗悎
	 * @param childPackage 鏄惁閬嶅巻瀛愬寘
	 * @return 绫荤殑瀹屾暣鍚嶇О
	 */
	private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		File file = new File(filePath);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				if (childPackage) {
					myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
				}
			} else {
				String childFilePath = childFile.getPath();
				if (childFilePath.endsWith(".class")) {
					childFilePath=childFilePath.replaceAll("/","\\\\");
					childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
					childFilePath = childFilePath.replace("\\", ".");
					myClassName.add(childFilePath);
				}
			}
		}

		return myClassName;
	}

	/**
	 * 浠巎ar鑾峰彇鏌愬寘涓嬫墍鏈夌被
	 * @param jarPath jar鏂囦欢璺緞
	 * @param childPackage 鏄惁閬嶅巻瀛愬寘
	 * @return 绫荤殑瀹屾暣鍚嶇О
	 */
	private static List<String> getClassNameByJar(String jarPath, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		String[] jarInfo = jarPath.split("!");
		String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
		String packagePath = jarInfo[1].substring(1);
		try {
			JarFile jarFile = new JarFile(jarFilePath);
			Enumeration<JarEntry> entrys = jarFile.entries();
			while (entrys.hasMoreElements()) {
				JarEntry jarEntry = entrys.nextElement();
				String entryName = jarEntry.getName();
				if (entryName.endsWith(".class")) {
					if (childPackage) {
						if (entryName.startsWith(packagePath)) {
							entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
							myClassName.add(entryName);
						}
					} else {
						int index = entryName.lastIndexOf("/");
						String myPackagePath;
						if (index != -1) {
							myPackagePath = entryName.substring(0, index);
						} else {
							myPackagePath = entryName;
						}
						if (myPackagePath.equals(packagePath)) {
							entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
							myClassName.add(entryName);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myClassName;
	}

	/**
	 * 浠庢墍鏈塲ar涓悳绱㈣鍖咃紝骞惰幏鍙栬鍖呬笅鎵�湁绫�
	 * @param urls URL闆嗗悎
	 * @param packagePath 鍖呰矾寰�
	 * @param childPackage 鏄惁閬嶅巻瀛愬寘
	 * @return 绫荤殑瀹屾暣鍚嶇О
	 */
	private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		if (urls != null) {
			for (int i = 0; i < urls.length; i++) {
				URL url = urls[i];
				String urlPath = url.getPath();
				// 涓嶅繀鎼滅储classes鏂囦欢澶�
				if (urlPath.endsWith("classes/")) {
					continue;
				}
				String jarPath = urlPath + "!/" + packagePath;
				myClassName.addAll(getClassNameByJar(jarPath, childPackage));
			}
		}
		return myClassName;
	}
}