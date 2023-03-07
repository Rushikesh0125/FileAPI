package service;

import Entity.Attachment;
import Repository.AttachmentRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    private AttachmentRepo attachmentRepo;

    public AttachmentServiceImpl(AttachmentRepo attachmentRepo){
        this.attachmentRepo= attachmentRepo;
    }

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(fileName.contains("..")){
                throw new Exception("filename conatins invalid path sequence");
            }
            Attachment attachment = new Attachment(
                    fileName,
                    file.getContentType(),
                    file.getBytes()
            );
            return attachmentRepo.save(attachment);
        }catch(Exception e){
            throw new Exception("could not save file");
        }
    }

    @Override
    public Attachment getAttchment(String fileId) throws Exception {
        return attachmentRepo.findById(fileId).orElseThrow(() -> new Exception("file mnnot found with fileId : " + fileId));
    }
}
