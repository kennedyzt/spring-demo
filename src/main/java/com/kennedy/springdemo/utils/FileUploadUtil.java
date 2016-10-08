package com.kennedy.springdemo.utils;

import com.kennedy.springdemo.beans.common.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传Util
 */
public class FileUploadUtil {
    public static FileResponse saveImage(MultipartFile multipartFile, String path) {
        FileResponse fileResponse = new FileResponse();
        String fileName = multipartFile.getOriginalFilename().substring(0, multipartFile.getOriginalFilename().lastIndexOf("."));
        fileResponse.setFileName(fileName);
        String newFileName = fileName + "_" + System.currentTimeMillis() + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        fileResponse.setNewFileName(newFileName);
        File file = new File(path + "\\" + newFileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return fileResponse;
    }
}
