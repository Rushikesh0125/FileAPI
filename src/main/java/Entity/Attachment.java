package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@Data
public class Attachment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;

    public String fileName;
    public String fileType;

    @Lob
    public byte[] data;

    public Attachment(String name, String type, byte[] data){
        this.fileName = name;
        this.fileType = type;
        this.data = data;
    }



}
