package com.lixu.tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import com.lixu.tools.apkUtil.entity.ApkInfo;
import com.lixu.tools.apkUtil.utils.ApkUtil;

public class apktool {
/**
 * 获取包名
 * 获取active
 * @param args
 */
	private  static final String pathfile="/Users/siriusl/Desktop/apkapk/";
	
	public static void main(String[] args) {
	
		try {
			/*
			 ApkInfo apkInfo = new ApkUtil().getApkInfo("/Users/siriusl/Desktop/apkapk/cscs.apk");
			//System.out.println("文件名称"+ff[i]);
			System.out.println("packageName=" + apkInfo.getPackageName());
			//System.out.println("packageName=" + apkInfo.getApplicationIcons());
			Map<String, String> map=apkInfo.getApplicationIcons();
			  for (Map.Entry<String, String> entry : map.entrySet()) {
			  System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			  }	
			System.out.println("launchableActivity="+ apkInfo.getLaunchableActivity());
			System.out.println("title="+ apkInfo.getApplicationLable());
			System.out.println("getVersionCode="+ apkInfo.getVersionCode());
			System.out.println("getVersionName="+ apkInfo.getVersionName());
			System.out.println("icon=" + apkInfo.getApplicationIcon());
			*/
		
		 File file=new File(pathfile);
		String[] ff=file.list();
		for(int i=0;i<ff.length;i++){
			ApkInfo apkInfo = new ApkUtil().getApkInfo(pathfile+ff[i]);
					//new ApkUtil().getApkInfo(pathfile+ff[i]);
//			System.out.println("文件名称="+ff[i]);
			System.out.println("Name="+ apkInfo.getApplicationLable());
			System.out.println("packageName=" + apkInfo.getPackageName());
//			System.out.println("icon=" + apkInfo.getApplicationIcon());
//			System.out.println("getVersionCode="+ apkInfo.getVersionCode());
//			System.out.println("getVersionName="+ apkInfo.getVersionName());
			System.out.println("***************************");
//			System.out.println("文件名称="+ff[i]+">>>>packageName=" + apkInfo.getPackageName());
			System.out.println("update adbaseinfo set crc32='"+getCRC32(pathfile+ff[i]).toLowerCase()+"' where packageName='"+apkInfo.getPackageName()+"';");
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getCRC32(String fileUri) {  
        CRC32 crc32 = new CRC32();  
        FileInputStream fileinputstream = null;  
        CheckedInputStream checkedinputstream = null;  
        String crc = null;  
        try {  
            fileinputstream = new FileInputStream(new File(fileUri));  
            checkedinputstream = new CheckedInputStream(fileinputstream, crc32);  
            while (checkedinputstream.read() != -1) {  
            }  
            crc = Long.toHexString(crc32.getValue()).toUpperCase();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (fileinputstream != null) {  
                try {  
                    fileinputstream.close();  
                } catch (IOException e2) {  
                    e2.printStackTrace();  
                }  
            }  
            if (checkedinputstream != null) {  
                try {  
                    checkedinputstream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return crc;  
    }  
}

