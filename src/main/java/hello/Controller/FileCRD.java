package hello.Controller;

import hello.Dao.AccountRepository;
import hello.Dao.FileObjectRepository;
import hello.Entity.Account;
import hello.Entity.FileObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FileCRD {

    @Autowired
    private FileObjectRepository fileObjectRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String showFileObjects(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account account = accountRepository.findByUsername(userDetails.getUsername());
        account.getFileObjectList();

        //List<FileObject> fileObjects = fileObjectRepository.findAll();

        model.addAttribute("fileobjects", account.getFileObjectList());
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
    public String saveFileObject(@RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {
        FileObject fobject = new FileObject();

        fobject.setName(file.getOriginalFilename());
        fobject.setType(file.getContentType());
        fobject.setSize(file.getSize());
        fobject.setContent(file.getBytes());

        Account account = getAccount(authentication);
        account.addFileObject(fobject);
        accountRepository.save(account);

        //fileObjectRepository.save(fobject);

        return "redirect:/files";
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @Transactional
    public String deleteFileObject(@PathVariable Long id, Authentication authentication){

        Account account = getAccount(authentication);
        account.removeFileObject(fileObjectRepository.findOne(id));
        accountRepository.save(account);
        //fileObjectRepository.delete(id);
        return "redirect:/files";
    }

    private Account getAccount(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account account = accountRepository.findByUsername(userDetails.getUsername());
        return account;
    }

}
