package com.fgbg.demo.controller;

import com.fgbg.demo.common.R;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

@RequestMapping("/fullText")
@RestController
public class FullTextController {
    /**
     * 文件访问域名(请求下载的接口)
     */
    private static final String DOMAIN = "http://localhost:9005/api_demo/fullText/file/download/";

    /**
     * 文件物理存储位置
     */
    private static final String STORE_DIR = "E:\\B站视频创作\\前后端项目构建-小功能实现\\代码\\backend\\src\\main\\resources\\pict\\";

    static class Success {
        public final int errno;
        public final Object data;
        public Success(String url) {
            this.errno = 0;
            HashMap<String, String> map = new HashMap<>();
            map.put("url", url);
            this.data = map;
        }
    }

    /**
     * 获取后缀
     */
    public static String getFileSuffix(String fileName) {
        // 检查文件名是否为null或空
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        // 查找最后一个点（.）的位置
        int dotIndex = fileName.lastIndexOf('.');

        // 检查是否找到点，且不是在字符串开头
        if (dotIndex > 0) {
            // 从点开始截取，直到字符串末尾
            return fileName.substring(dotIndex);
        }

        // 如果没有找到点，或点在字符串开头，则返回空字符串
        return "";
    }

    /**
     * 上传文件接口
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/file/upload")
    public Object uploadPict(@RequestParam("image") MultipartFile file) throws IOException {
        // 获取文件流
        InputStream is = file.getInputStream();
        // 获取文件真实名字
        String fileName = UUID.randomUUID().toString().substring(0, 10) + getFileSuffix(file.getOriginalFilename());
        // 在服务器中存储文件
        FileUtils.copyInputStreamToFile(is, new File(STORE_DIR + fileName));
        // 返回图片url
        String url = DOMAIN + fileName;
        return new Success(url);
    }

    /**
     * 文件下载接口
     * @param fileName 文件名
     * @param request
     * @param response
     */
    @GetMapping("/file/download/{fileName}")
    public void download(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
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
}
