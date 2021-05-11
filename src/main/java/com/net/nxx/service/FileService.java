package com.net.nxx.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-10
 */
public interface FileService {
    /**
     * 上传文件
     *
     * @param multipartFile
     * @param fileName
     * @return
     * @throws IOException
     */
    boolean uploadFile(MultipartFile multipartFile, String fileName) throws IOException;
}
