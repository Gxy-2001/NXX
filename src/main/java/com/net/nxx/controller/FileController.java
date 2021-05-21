package com.net.nxx.controller;

import com.net.nxx.common.exception.ErrorMsg;
import com.net.nxx.service.FileService;
import com.net.nxx.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @program: nxx
 * @description:
 * @author: Gxy-2001
 * @create: 2021-05-10
 */
@RestController
@PropertySource(value = "classpath:path.properties", ignoreResourceNotFound = true)
public class FileController {
    @Value("${localPath}")
    private String userFilePath;

    @Value("${Path}")
    private String baseUrl;

    @Resource
    private FileService fileService;

    @PostMapping("/file")
    public Result uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        String uuid = "file" + String.valueOf(System.currentTimeMillis());
        System.out.println("File id" + uuid);
        String fileName = uuid+ multipartFile.getOriginalFilename();
        try {
            if (fileService.uploadFile(multipartFile, fileName)) {
                return Result.success(baseUrl + "/image?imageName=" + fileName);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return Result.fail(ErrorMsg.SYSTEM_ERROR);
        }
        return Result.fail(ErrorMsg.FILE_UPLOAD_ERROR);
    }

    @GetMapping("/image")
    public void getImage(@RequestParam("imageName") String imageName,
                         HttpServletResponse response) throws IOException {
        File fileDir = new File(userFilePath);
        File image = new File(fileDir.getAbsolutePath() + "/" + imageName);
        if (image.exists()) {
            FileInputStream fileInputStream = new FileInputStream(image);
            byte[] bytes = new byte[fileInputStream.available()];
            if (fileInputStream.read(bytes) > 0) {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes);
                outputStream.close();
            }
            fileInputStream.close();
        }
    }
}
