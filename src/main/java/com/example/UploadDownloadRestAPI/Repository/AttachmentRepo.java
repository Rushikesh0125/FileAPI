package com.example.UploadDownloadRestAPI.Repository;

import com.example.UploadDownloadRestAPI.Entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepo extends JpaRepository<Attachment, String> {
}
