package com.zys.service;

import com.zys.entitys.ImageInfo;

import java.util.List;

public interface ImageInfoService {
    int addImageInfo(ImageInfo imageInfo);
    List<ImageInfo> searchIdToUrl(Integer userId);
}
