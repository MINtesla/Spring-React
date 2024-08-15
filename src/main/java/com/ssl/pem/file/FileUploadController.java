package com.ssl.pem.file;

import java.io.IOException;
import java.security.cert.CertificateException;

import com.ssl.pem.MAIN.PemToDerConverter;
import com.ssl.pem.MAIN.PemToPrivateKeyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @Autowired
    PemToDerConverter pemToDerConverter;

    @Autowired
    PemToPrivateKeyConverter pemToPrivateKeyConverter;

    @PostMapping("/pemToDer")
    public ResponseEntity<FileUploadResponse> uploadFile1(
            @RequestParam("file") MultipartFile multipartFile) {
        String result = pemToDerConverter.convertPemToDer(multipartFile);

        String arr[]= result.split("\\|");
        if(arr.length<=1){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new FileUploadResponse("Invalid file type. Please upload a valid certificate.", "not a valid file", 0));

        }

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(arr[0]);
        response.setSize(Long.parseLong(arr[1]));
        response.setDownloadUri("/downloadFile/" +arr[0]);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/pemTopk8")
    public ResponseEntity<FileUploadResponse> uploadFile2(
            @RequestParam("file") MultipartFile multipartFile)
            throws IOException, CertificateException {
        if(!multipartFile.getContentType().equalsIgnoreCase("application/x-x509-ca-cert")){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new FileUploadResponse("Invalid file type. Please upload a valid certificate.", "not a valid file", 0));
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        String newFileName=fileName.substring(0,fileName.length()-3)+"pk8";
        String outputPath ="/Users/ashutoshsingh/pgdb_gcp/projects/pem/src/main/resources/"+newFileName;
        System.out.println(outputPath);
        pemToPrivateKeyConverter.convertPEMtoPK8(multipartFile,outputPath);

//        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(newFileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/" +newFileName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}