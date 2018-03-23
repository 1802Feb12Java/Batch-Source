package com.revature;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.clouddirectory.model.DeleteObjectRequest;
import com.amazonaws.services.iot.model.CannedAccessControlList;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

//@Service
public class AmazonClient {

    private AmazonS3 s3client;

    //@Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    //@Value("${amazonProperties.bucketName}")
    private String bucketName;
   // @Value("${amazonProperties.accessKey}")
    private String accessKey;
    //@Value("${amazonProperties.secretKey}")
    private String secretKey;

    public String uploadFile(MultipartFile multipartFile) {

        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fileUrl;
    }
    
    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
        return "Successfully deleted";
    }
    
    //@PostConstruct
    private void initializeAmazon() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        
        this.s3client = AmazonS3ClientBuilder
                        .standard()
                        .withCredentials(new AWSStaticCredentialsProvider(credentials))
                        .withRegion(Regions.US_EAST_2)
                        .build();
    }
    
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    
    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
    
    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
                
    }
    
}
