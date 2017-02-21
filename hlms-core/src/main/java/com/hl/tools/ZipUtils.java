package com.hl.tools;

import java.io.*;
import java.util.logging.Logger;
import java.util.zip.*;


public class ZipUtils {

	 private final static Logger logger = Logger.getLogger(ZipUtils.class.getName());
	    private static final int BUFFER = 1024*10;
	    /**
	     * 将指定目录压缩到和该目录同名的zip文件，自定义压缩路径
	     * @param sourceFilePath  目标文件路径
	     * @param zipFilePath     指定zip文件路径
	     * @return
	     */
	    public static boolean zip(String sourceFilePath,String zipFilePath){
	        boolean result=false;
	        File source=new File(sourceFilePath);
	        if(!source.exists()){
	            logger.info(sourceFilePath+" doesn't exist.");
	            return result;
	        }
	        if(!source.isDirectory()){
	            logger.info(sourceFilePath+" is not a directory.");
	            return result;
	        }
	        File zipFile=new File(zipFilePath+"/"+source.getName()+".zip");
	        if(zipFile.exists()){
	            logger.info(zipFile.getName()+" is already exist.");
	            return result;
	        }else{
	            if(!zipFile.getParentFile().exists()){
	                if(!zipFile.getParentFile().mkdirs()){
	                    logger.info("cann't create file "+zipFile.getName());
	                    return result;
	                }
	            }
	        }
	        logger.info("creating zip file...");
	        FileOutputStream dest=null;
	        ZipOutputStream out =null;
	        try {
	            dest = new FileOutputStream(zipFile);
	            CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
	            out=new ZipOutputStream(new BufferedOutputStream(checksum));
	            out.setMethod(ZipOutputStream.DEFLATED);
	            compress(source,out,source.getName());
	            result=true;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }finally {
	            if (out != null) {
	                try {
	                    out.closeEntry();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	                try {
	                    out.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        if(result){
	            logger.info("done.");
	        }else{
	            logger.info("fail.");
	        }
	        return result;
	    }
	    private static void compress(File file,ZipOutputStream out,String mainFileName) {
	        if(file.isFile()){
	            FileInputStream fi= null;
	            BufferedInputStream origin=null;
	            try {
	                fi = new FileInputStream(file);
	                origin=new BufferedInputStream(fi, BUFFER);
	                int index=file.getAbsolutePath().indexOf(mainFileName);
	                String entryName=file.getAbsolutePath().substring(index);
	                System.out.println(entryName);
	                ZipEntry entry = new ZipEntry(entryName);
	                out.putNextEntry(entry);
	                byte[] data = new byte[BUFFER];
	                int count;
	                while((count = origin.read(data, 0, BUFFER)) != -1) {
	                    out.write(data, 0, count);
	                }
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }finally {
	                if (origin != null) {
	                    try {
	                        origin.close();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }else if (file.isDirectory()){
	            File[] fs=file.listFiles();
	            if(fs!=null&&fs.length>0){
	                for(File f:fs){
	                    compress(f,out,mainFileName);
	                }
	            }
	        }
	    }
	    /**
	     * 将zip文件解压到指定的目录，该zip文件必须是使用该类的zip方法压缩的文件
	     * @param zipFile
	     * @param destPath
	     * @return
	     */
	    public static boolean unzip(File zipFile,String destPath){
	        boolean result=false;
	        if(!zipFile.exists()){
	            logger.info(zipFile.getName()+" doesn't exist.");
	            return result;
	        }
	        File target=new File(destPath);
	        if(!target.exists()){
	            if(!target.mkdirs()){
	                logger.info("cann't create file "+target.getName());
	                return result;
	            }
	        }
	        String mainFileName=zipFile.getName().replace(".zip","");
	        File targetFile=new File(destPath+"/"+mainFileName);
	        if(targetFile.exists()){
	            logger.info(targetFile.getName()+" already exist.");
	            return result;
	        }
	        ZipInputStream zis =null;
	        logger.info("start unzip file ...");
	        try {
	            FileInputStream fis= new FileInputStream(zipFile);
	            CheckedInputStream checksum = new CheckedInputStream(fis, new Adler32());
	            zis = new ZipInputStream(new BufferedInputStream(checksum));
	            ZipEntry entry;
	            while((entry = zis.getNextEntry()) != null) {
	                int count;
	                byte data[] = new byte[BUFFER];
	                String entryName=entry.getName();
	                int index=entryName.indexOf(mainFileName);
	                String newEntryName=destPath+"/"+entryName.substring(index);
	                System.out.println(newEntryName);
	                File temp=new File(newEntryName).getParentFile();
	                if(!temp.exists()){
	                    if(!temp.mkdirs()){
	                        throw new RuntimeException("create file "+temp.getName() +" fail");
	                    }
	                }
	                FileOutputStream fos = new FileOutputStream(newEntryName);
	                BufferedOutputStream dest = new BufferedOutputStream(fos,BUFFER);
	                while ((count = zis.read(data, 0, BUFFER)) != -1) {
	                    dest.write(data, 0, count);
	                }
	                dest.flush();
	                dest.close();
	            }
	            result=true;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally {
	            if (zis != null) {
	                try {
	                    zis.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        if(result){
	            logger.info("done.");
	        }else{
	            logger.info("fail.");
	        }
	        return result;
	    }
	    
}
