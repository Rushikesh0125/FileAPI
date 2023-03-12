package com.example.UploadDownloadRestAPI.Controller;

import com.example.UploadDownloadRestAPI.Entity.Attachment;
import com.example.UploadDownloadRestAPI.Model.ResponseData;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.UploadDownloadRestAPI.service.AttachmentService;

@RestController
public class AttachmentController {

    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService){
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        Attachment attachment = null;
        String downLoadURL ="";
        attachment = attachmentService.saveAttachment(file);
        downLoadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path(attachment.getId()).
                toUriString();

        ResponseData response = new ResponseData(
                attachment.fileName,
                file.getContentType(),
                downLoadURL,
                file.getSize()
        );
        return response;
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttchment(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}
