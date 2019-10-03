package com.company.springbootphotohosting;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.company.springbootphotohosting.model.Image;
import com.company.springbootphotohosting.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUpader {

    private Cloudinary cloudinary;
    private ImageRepo imageRepo;

    @Value("${cloudNameValue}")
    private String cloudNameValue;
    @Value("${apiKeyValue}")
    private String apiKeyValue;
    @Value("${apiSecretValue}")
    private String apiSecretValue;

    @Autowired
    public ImageUpader(ImageRepo imageRepo,
                       @Value("${cloudNameValue}") String cloudNameValue,
                       @Value("${apiKeyValue}") String apiKeyValue,
                       @Value("${apiSecretValue}") String apiSecretValue) {
        this.imageRepo = imageRepo;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudNameValue,
                "api_key", apiKeyValue,
                "api_secret", apiSecretValue));
    }

    public String uploadFileAndSaveToDb(String path) {     // na podstawie path twoze file
        File file = new File(path);
        Map uploadResult = null;        // wyciagam przed try/catch zeby miec do tego dostep
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepo.save(new Image(uploadResult.get("url").toString()));     // zapisywanie do DB

        } catch (IOException e) {
             // to do
        }
        return uploadResult.get("url").toString();
    }
}

