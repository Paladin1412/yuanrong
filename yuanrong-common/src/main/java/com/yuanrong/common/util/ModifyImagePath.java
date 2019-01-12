package com.yuanrong.common.util;

import com.yuanrong.common.oss.CloudStorageServiceAbstract;
import fr.opensagres.poi.xwpf.converter.core.ImageManager;

import java.io.File;
import java.io.IOException;

/*
 *@author songwq
 *@date 2018/10/16
 *@time 18:29
 *@description
 */
public class ModifyImagePath extends ImageManager {
    public ModifyImagePath(File baseDir, String imageSubDir , CloudStorageServiceAbstract ossServices) {
        super(baseDir, imageSubDir);
        this.ossServices = ossServices;
    }
    String imagesPath;
    CloudStorageServiceAbstract ossServices;

    public void extract(String imagePath, byte[] imageData) throws IOException {
        String imageName = imagePath.substring(imagePath.lastIndexOf("/")+1);
        imagesPath = ossServices.upload(imageData,System.currentTimeMillis()+"/"+imageName);
    }

    private String getImageRelativePath(String imagePath) {
        return imagesPath;
    }

    private String getFileName(String imagePath) {
        return (new File(imagePath)).getName();
    }

    public String resolve(String uri) {
        return imagesPath;
    }
}
