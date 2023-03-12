package com.example.UploadDownloadRestAPI.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
    private String fileName;
    private String fileType;
    private String downloadURL;
    private long fileSize;
}
