package com.wwmust.utils;

import com.wwmust.exception.MyException;
import com.wwmust.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 封装文件信息Util
 */
public class FileInfoUtils {

    /**
     * 装配单个文件信息
     *
     * @param file
     * @return
     */
    public static FileInfo packFileInfo(MultipartFile file) {
        FileInfo fileInfo = new FileInfo();
        //文件名
        fileInfo.setFileName(file.getOriginalFilename());
        //文件大小
        fileInfo.setFileSize(file.getSize());
        //创建时间
        fileInfo.setCreateTime(new Date());
        return fileInfo;
    }

    /**
     * 装配文件信息
     *
     * @param file
     * @param fileId
     * @param fileUrl
     * @return
     */
    public static FileInfo packFileInfo(MultipartFile file, String fileId, String fileUrl) {
        FileInfo fileInfo = FileInfoUtils.packFileInfo(file);
        //文件 FileId
        fileInfo.setFileId(fileId);
        //文件 Url
        fileInfo.setFileUrl(fileUrl);
        return fileInfo;
    }

    /**
     * 装配多个文件信息
     *
     * @param files
     */
    public static FileInfo[] packFileInfos(MultipartFile[] files) {
        int length = files.length;
        FileInfo[] fileInfos = new FileInfo[length];
        //遍历装配信息
        MultipartFile file = null;
        FileInfo fileInfo = null;
        for (int i = 0; i < length; i++) {
            file = files[i];
            fileInfo = new FileInfo();
            //文件名
            fileInfo.setFileName(file.getOriginalFilename());
            //文件大小
            fileInfo.setFileSize(file.getSize());
            //创建时间
            fileInfo.setCreateTime(new Date());
            fileInfos[i] = fileInfo;
        }
        return fileInfos;
    }

    /**
     * 装配多个文件
     *
     * @param files
     * @param fileId
     * @param fileUrls
     * @return
     */
    public static FileInfo[] packFileInfos(MultipartFile[] files, String fileId, String[] fileUrls) {
        if (files == null || fileUrls == null || fileUrls.length < files.length) {
            throw new MyException("文件Url数组长度 != 文件数组长度");
        }
        int length = files.length;
        FileInfo[] fileInfos = new FileInfo[length];
        //遍历装配信息
        MultipartFile file = null;
        FileInfo fileInfo = null;
        String fileUrl = null;
        for (int i = 0; i < length; i++) {
            file = files[i];
            fileUrl = fileUrls[i];
            fileInfo = new FileInfo();

            //文件ID
            fileInfo.setFileId(fileId);
            //文件名
            fileInfo.setFileName(file.getOriginalFilename());
            //文件Url
            fileInfo.setFileUrl(fileUrl);
            //文件大小
            fileInfo.setFileSize(file.getSize());
            //创建时间
            fileInfo.setCreateTime(new Date());
            fileInfos[i] = fileInfo;
        }
        return fileInfos;
    }
}
