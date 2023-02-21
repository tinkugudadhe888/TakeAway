package com.elevate.app.takeaway.service;

import com.elevate.app.takeaway.exceptions.CustomException;
import com.elevate.app.takeaway.model.DBFile;
import com.elevate.app.takeaway.repository.DBFileRepository;
import com.elevate.app.takeaway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class DBFileStorageService {
    @Autowired
    private DBFileRepository dbFileRepository;

    @Autowired
    private UserRepository userRepository;

    public DBFile storeFile(MultipartFile file,long userId) {
        userRepository.findById(userId).orElseThrow(()-> new CustomException("User Not Found"));
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new CustomException("Sorry! Filename contains invalid path sequence " + fileName);
            }
//            Optional<DBFile> dbFiled = dbFileRepository.findById(String.valueOf(userId));
//            DBFile fileObject = null;
//            if (dbFiled.isPresent()){
//                fileObject = dbFiled.get();
//                fileObject.setData(file.getBytes());
//                fileObject.setFileType(file.getContentType());
//                fileObject.setFileType(fileName);
//            }else {
                DBFile fileObject = new DBFile(fileName, file.getContentType(), file.getBytes(),userId);
//            }

            return dbFileRepository.save(fileObject);
        } catch (IOException ex) {
            throw new CustomException("Could not store file " + fileName + ". Please try again!");
        }
    }

    public DBFile getFile(long userId) {
        return dbFileRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException("File not found with id " + userId));
    }
}
