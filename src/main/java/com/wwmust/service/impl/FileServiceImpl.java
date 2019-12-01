package com.wwmust.service.impl;

import com.wwmust.dao.FileDao;
import com.wwmust.model.FileInfo;
import com.wwmust.service.FileService;
import com.wwmust.utils.FileIdUtils;
import com.wwmust.utils.FileInfoUtils;
import com.wwmust.utils.FileStoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;

    /**
     * 存储单个文件
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String insertFile(MultipartFile file) throws IOException {
        //生成文件ID
        String fileId = FileIdUtils.getFileId();
        String fileUrl = insertFile(file, fileId);
        return fileUrl;
    }

    /**
     * 存储单个文件
     * @param file
     * @return
     * @throws IOException
     */
    public String insertFile(MultipartFile file,String fileId) throws IOException {
        //根据文件ID，生成新的文件名
        String fileName = FileIdUtils.getFileName(file.getOriginalFilename(), fileId);
        //存储文件
        //TODO 后期使用 消息队列 转存 到 文件服务器
        FileStoreUtils.storeFileByName(file, "D://aa", fileName);

        //封装信息
        FileInfo fileInfo = FileInfoUtils.packFileInfo(file);
        fileInfo.setFileId(fileId);
        //TODO url 使用 存储在文件服务器上的 url
        fileInfo.setFileUrl("D://aa//" + fileName);
        //存储到数据库
        fileDao.insertFileInfo(fileInfo);
        return fileInfo.getFileUrl();
    }

    @Override
    public FileInfo insertFileGetAllInfo(MultipartFile file) throws IOException {
        //生成文件ID
        String fileId = FileIdUtils.getFileId();
        //转存文件，插入数据库
        insertFile(file,fileId);
        //查询文件信息
        FileInfo info = fileDao.selectFileInfo(fileId);
        return info;
    }

    /**
     * 存储多个文件
     * @param files
     * @return
     */
    @Override
    public String[] insertFiles(MultipartFile[] files) throws IOException {
        //生成文件ID
        String fileId = FileIdUtils.getFileId();
        String[] fileUrls = insertFiles(files, fileId);
        return fileUrls;
    }

    /**
     * 转存文件
     * 插入数据库
     * @param files
     * @param fileId
     * @return 文件Urls
     * @throws IOException
     */
    private String[] insertFiles(MultipartFile[] files,String fileId) throws IOException {
        //生成文件名
        String[] fileNames = FileIdUtils.getFileNames(files);
        //存储文件
        //TODO 后期使用 消息队列 转存 到 文件服务器
        FileStoreUtils.storeFilesByNames(files,"D://aa",fileNames);

        //生成信息
        //TODO url 使用 存储在文件服务器上的 url
        String[] fileUrls = new String[files.length];
        for (int i = 0; i < fileUrls.length; i++) {
            fileUrls[i] = "D://aa//" + fileNames[i];
        }

        //装配信息
        FileInfo[] fileInfos = FileInfoUtils.packFileInfos(files,fileId,fileUrls);
        fileDao.insertFileInfos(fileInfos);
        return fileUrls;
    }

    /**
     * 转存文件
     * 插入数据库
     * 返回文件全部信息
     * @param files
     * @return
     * @throws IOException
     */
    @Override
    public FileInfo[] insertFilesGetAllInfos(MultipartFile[] files) throws IOException {
        //生成文件ID
        String fileId = FileIdUtils.getFileId();
        //转存文件，插入数据库
        insertFiles(files,fileId);
        //查询文件信息
        FileInfo[] infos = fileDao.selectFileInfos(fileId);
        return infos;
    }
}
