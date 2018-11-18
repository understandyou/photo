package com.zys.service.impl;

import com.zys.dao.ImageInfoMapper;
import com.zys.entitys.ImageInfo;
import com.zys.service.ImageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ImageInfoService")
public class ImageInfoServiceImpl implements ImageInfoService {
    @Autowired
    ImageInfoMapper imageInfoMapper;
    @Override
    public int addImageInfo(ImageInfo imageInfo) {
        return imageInfoMapper.insertSelective(imageInfo);
    }

    @Override
    public List<ImageInfo> searchIdToUrl(Integer userId) {
        return imageInfoMapper.searchUrlToId(userId);
    }
}
