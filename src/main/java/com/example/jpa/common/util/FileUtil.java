package com.example.jpa.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileUtil {

    public static String fileUploadPath;

    @Value("${file.upload.path}")
    public void setFileUploadPath(String fileUploadPath){
        this.fileUploadPath = fileUploadPath;
    }

    public static void fileUpload(List<MultipartFile> files) throws Exception{
        if(!files.isEmpty()){

        }
    }


}
