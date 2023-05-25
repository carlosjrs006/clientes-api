package br.com.api.rest.controller;

import br.com.api.rest.execptions.MensagemException;
import br.com.api.rest.service.StorageCloudinaryService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/storege")
@CrossOrigin("https://sunny-starburst-bf91e2.netlify.app/")
public class FileController {

    @Autowired
    private StorageCloudinaryService storageCloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        try{
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if(bufferedImage == null){
                return new ResponseEntity(new MensagemException("Imagem invalida!!"), HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok().body(storageCloudinaryService.upload(multipartFile));
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable("id") String id) throws IOException {
        try{
            return ResponseEntity.ok().body(storageCloudinaryService.delete(id));
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
