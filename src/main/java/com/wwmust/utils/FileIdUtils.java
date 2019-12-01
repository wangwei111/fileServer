package com.wwmust.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 获取文件ID Util
 */
public class FileIdUtils {
    /**
     * 生成文件Id
     *
     * @return
     */
    public static String getFileId() {
        String uuid = UUID.randomUUID().toString();
        String fileId = uuid.replace("-", "");
        return fileId;
    }

    /**
     * 替换真实文件名，保留后缀
     * 示例：证书.jpg  → 9ea66de965394027a512f845fa910c64.jpg
     * @param originalFileName 真实文件名
     * @param fileId           文件Id
     * @return 文件名.后缀
     */
    public static String getFileName(String originalFileName, String fileId) {
        String fileSuffix = FileIdUtils.getFileSuffix(originalFileName);
        String fileName = fileId + fileSuffix;
        return fileName;
    }

    /**
     * 生成文件名
     * @param files
     * @return
     */
    public static String[] getFileNames(MultipartFile[] files) {
        int length = files.length;
        String[] fileNames = new String[length];
        for (int i = 0; i < length; i++) {
            String originalFilename = files[i].getOriginalFilename();
            String fileSuffix = FileIdUtils.getFileSuffix(originalFilename);
            fileNames[i] = FileIdUtils.getFileId() + fileSuffix;
        }
        return fileNames;
    }

    /**
     * 获取文件名后缀
     * 示例：证书.jpg  →  .jpg
     * @param originalFileName 文件名
     * @return
     */
    public static String getFileSuffix(String originalFileName) {
        //根据 . 分割文件后缀
        String[] split = originalFileName.split("\\.");
        String fileSuffix = split[split.length - 1];
        fileSuffix = "." + fileSuffix;
        return fileSuffix;
    }

    /**
     * 获取文件名后缀
     * @param file 文件
     * @return
     */
    public static String getFileSuffix(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String fileSuffix = FileIdUtils.getFileSuffix(originalFilename);
        return fileSuffix;
    }


}
