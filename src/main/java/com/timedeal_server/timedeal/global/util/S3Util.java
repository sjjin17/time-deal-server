package com.timedeal_server.timedeal.global.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class S3Util {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3Client amazonS3Client;

    // 폴더 생성
    public void createFolder(String folderName) {
        amazonS3Client.putObject(bucket, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
    }


    public String fileUpload(MultipartFile file, String folderName, String fileName) throws IOException {
        String s3FileName = fileName;
        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentType("image/png");
        objMeta.setContentLength(file.getInputStream().available());
        amazonS3Client.putObject(new PutObjectRequest(bucket + "/" +folderName, s3FileName, file.getInputStream(), objMeta)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket + "/" + folderName,  s3FileName).toString();

    }

    public void deleteFile(String url, String folderName) {
        String key = url.substring(url.lastIndexOf("/"));
        amazonS3Client.deleteObject(bucket + "/" + folderName,key.substring(1));


    }


}
