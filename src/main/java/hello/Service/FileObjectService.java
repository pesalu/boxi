package hello.Service;

import hello.Dao.FileObjectRepository;
import hello.Entity.FileObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FileObjectService {

    @Autowired
    private FileObjectRepository fileObjectRepository;

    public List<FileObject> getFileObjects(){
        return fileObjectRepository.findAll();
    }

}
