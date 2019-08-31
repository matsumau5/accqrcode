package com.product.acc.web;

import com.product.acc.domain.app.model.QrRequestBean;
import com.product.acc.domain.app.service.qrcode.QRcodeService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Handles requests for the QRcode.
 */
@RestController
@RequestMapping("/api/qrcode")
public class QRcodeController {

    final QRcodeService qrcodeService;
    public QRcodeController(QRcodeService qrcodeService) {
        this.qrcodeService = qrcodeService;
    }
    /**
     * get QRcode.
     */
//	@CrossOrigin
//    @PostMapping
//    public byte[] getAllImages(@RequestBody QrRequestBean qrRequestBean) {
//        return qrcodeService.getQRcode(qrRequestBean).getImageQR();
//    }
    @PostMapping
    public HttpEntity<byte[]> getAllImages(@Validated @RequestBody QrRequestBean qrRequestBean) {
        byte[] byteImage = qrcodeService.getQRcode(qrRequestBean).getImageQR();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(byteImage.length);
        return new HttpEntity<>(byteImage, headers);
    }
}
