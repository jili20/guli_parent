package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author bing  @create 2020/12/4-1:12 下午
 */
public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
