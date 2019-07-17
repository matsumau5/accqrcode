package com.product.acc.domain.app.service.qrcode;


import com.product.acc.domain.app.model.QrRequestBean;
import com.product.acc.domain.app.model.ResultQR;

/**
 * QRcode Service
 * @author matsuoka
 * @version $Revision$
 */
public interface QRcodeService {
    ResultQR getQRcode(QrRequestBean requestBean);
}
