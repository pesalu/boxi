package hello.Controller;

import hello.Dao.FileObjectRepository;
import hello.Entity.FileObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FileCRD {

    @Autowired
    private FileObjectRepository fileObjectRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String showFileObjects(Model model) {
        List<FileObject> fileObjects = fileObjectRepository.findAll();

        model.addAttribute("fileobjects", fileObjects);
        return "files";
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        FileObject fobject = fileObjectRepository.findOne(id);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fobject.getType()));
        headers.setContentLength(fobject.getSize());

        return new ResponseEntity<byte[]>(fobject.getContent(), headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveFileObject(@RequestParam("file") MultipartFile file) throws IOException {
        FileObject fobject = new FileObject();

        fobject.setName(file.getOriginalFilename());
        fobject.setType(file.getContentType());
        fobject.setSize(file.getSize());
        fobject.setContent(file.getBytes());

        fileObjectRepository.save(fobject);

        return "redirect:/files";
    }


    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public String deleteFileObject(@PathVariable Long id){
        fileObjectRepository.delete(id);
        return "redirect:/files";
    }
}
