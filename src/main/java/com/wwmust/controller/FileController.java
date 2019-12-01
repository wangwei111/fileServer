package com.wwmust.controller;

import com.wwmust.model.FileInfo;
import com.wwmust.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * 新增一个文件
     * @param file
     * @return 返回 url
     */
    @PostMapping("/insert")
    public Map<String,String> insertFile(MultipartFile file) throws IOException {
        String fileUrl = fileService.insertFile(file);
        Map<String,String> map = new HashMap<>(16);
        map.put("url",fileUrl);
        return map;
    }

    /**
     * 新增一个文件
     * @param file
     * @return 返回全部信息
     */
    @PostMapping("/insert-info")
    public Map<String,FileInfo> insertFileGetAllInfo(MultipartFile file) throws IOException {
        FileInfo fileInfo = fileService.insertFileGetAllInfo(file);
        Map<String,FileInfo> map = new HashMap<>(16);
        map.put("fileInfo",fileInfo);
        return map;
    }

    /**
     * 新增多个文件
     * @param files
     * @return 返回 String[] urls
     */
    @PostMapping("inserts")
    public Map<String,String[]> insertFiles(MultipartFile[] files) throws IOException {
        String[] fileUrls = fileService.insertFiles(files);
        Map<String,String[]> map = new HashMap<>(16);
        map.put("urls",fileUrls);
        return map;
    }

    /**
     * 新增多个文件
     * @param files
     * @return 返回全部信息
     */
    @PostMapping("inserts-info")
    public Map<String,FileInfo[]> insertFilesGetAllInfos(MultipartFile[] files) throws IOException {
        FileInfo[] fileInfos = fileService.insertFilesGetAllInfos(files);
        Map<String,FileInfo[]> map = new HashMap<>(16);
        map.put("fileInfos",fileInfos);
        return map;
    }
}
