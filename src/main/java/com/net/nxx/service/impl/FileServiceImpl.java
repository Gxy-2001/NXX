package com.net.nxx.service.impl;

import com.net.nxx.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-10
 */
@Service
@PropertySource(value = "classpath:path.properties", ignoreResourceNotFound = true)
public class FileServiceImpl implements FileService {
    @Value("${localPath}")
    private String filePath;

    /**
     *
     * @param multipartFile
     * @param fileName
     * @return
     * @throws IOException
     */
    @Override
    public boolean uploadFile(MultipartFile multipartFile, String fileName) throws IOException {
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                return false;
            }
        }
        System.out.println(fileDir.getAbsolutePath()+ File.separator + fileName);
        File file = new File(fileDir.getAbsolutePath() + File.separator + fileName);
        if (file.exists()) {
            if (!file.delete()) {
                return false;
            }
        }
        if (file.createNewFile()) {
            System.out.println(multipartFile.getName());
            System.out.println(multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            return true;
        }
        return false;
    }
}
