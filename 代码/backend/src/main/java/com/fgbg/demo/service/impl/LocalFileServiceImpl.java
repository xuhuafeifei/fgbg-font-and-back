package com.fgbg.demo.service.impl;

import com.fgbg.demo.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 基于本地的文件管理服务
 */
@Service("localFileService")
public class LocalFileServiceImpl implements FileService {
    /**
     * 文件访问域名(请求下载的接口)
     */
    private static final String DOMAIN = "http://localhost:9005/api_demo/common/file/download/";

    /**
     * 文件物理存储位置
     */
    private static final String STORE_DIR = "E:\\B站视频创作\\前后端项目构建-小功能实现\\代码\\backend\\src\\main\\resources\\pict\\";

    /**
     * 上传图片, 返回url
     *
     * @param file
     * @param fileName
     */
    @Override
    public String uploadFile(MultipartFile file, String fileName) throws IOException {
        // 获取文件流
        InputStream is = file.getInputStream();
        // 在服务器中存储文件
        FileUtils.copyInputStreamToFile(is, new File(STORE_DIR + fileName));
        // 返回图片url
        String url = DOMAIN + fileName;
        System.out.println("文件url: " + url);
        return url;
    }

    /**
     * 下载图片
     *
     * @param fileName
     */
    @Override
    public void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response) {
        // 获取真实的文件路径
        String filePath = STORE_DIR + fileName;
        System.out.println("++++完整路径为："+filePath);

        try {
            // 下载文件
            // 设置响应头
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            // 读取文件内容并写入输出流
            Files.copy(Paths.get(filePath), response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            response.setStatus(404);
        }
    }

    /**
     * 删除图片
     *
     * @param fileName
     */
    @Override
    public boolean deleteFile(String fileName) {
        // 获取真实的文件路径
        String filePath = STORE_DIR + fileName;
        System.out.println("++++完整路径为："+filePath);

        File file = new File(filePath);
        return file.delete();
    }
}
