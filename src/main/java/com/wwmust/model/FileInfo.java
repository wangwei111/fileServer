package com.wwmust.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class FileInfo {
    /**
     * id 主键
     */
    private Integer id;

    /**
     * 文件id
     */
    private String fileId;

    private String fileName;

    private String fileUrl;

    private Long fileSize;

    /**
     * 记录状态 1有效 0无效
     */
    private Integer status;

    /**
     * 创建人 id
     */
    private Integer createUser;
    private Date createTime;
    /**
     * 修改人 id
     */
    private Integer updateUser;
    private Date updateTime;
}
