package com.LANChat.util;

import java.io.*;

public class FileUtil {

    public static  void fileCopy(String before,String after){
        try {
            //获取源文件的输入流
            BufferedInputStream bf = new BufferedInputStream(new FileInputStream(before));
            //获取目标文件的输出流
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(after));
            //读取文件
            byte[] arr = new byte[1024];
            int len;
            //把文件读取到字节数组中
            while((len = bf.read(arr))!=-1){
                os.write(arr,0,len);
            }
            bf.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
