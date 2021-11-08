package com.example.jpa.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class FileUtil {

    final static Logger log = LogManager.getLogger(FileUtil.class);

    public static String fileUploadPath;

    @Value("${file.upload.path}")
    public void setFileUploadPath(String fileUploadPath){
        this.fileUploadPath = fileUploadPath;
    }

    public static void fileUpload(String filePath, String uuid, MultipartFile multipartFile) {
        if(!multipartFile.isEmpty()){
            try{
                File dir = new File(filePath);
                if (!dir.exists()) { dir.mkdirs(); }

                File uploadFile = new File(filePath + File.separator + uuid);
                multipartFile.transferTo(uploadFile);

            }catch (FileNotFoundException fe){
                log.info("FileNotFoundException ["+fe.getClass()+"] ["+fe.getMessage()+"]");
            }catch (IOException ie){
                log.info("IOException ["+ie.getClass()+"] ["+ie.getMessage()+"]");
            }
        }
    }
}