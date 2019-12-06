package com.wwmust.dao;

import com.wwmust.model.FileInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao {
    void insertFileInfo(FileInfo fileInfo);

    FileInfo selectFileInfo(String fileId);


    void insertFileInfos(FileInfo[] fileInfos);

    FileInfo[] selectFileInfos(String fileId);
}
