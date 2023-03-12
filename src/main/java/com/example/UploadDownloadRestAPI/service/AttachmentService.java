package com.example.UploadDownloadRestAPI.service;

import com.example.UploadDownloadRestAPI.Entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttchment(String fileId) throws Exception;
}
