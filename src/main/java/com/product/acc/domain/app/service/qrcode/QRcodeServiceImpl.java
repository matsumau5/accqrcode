package com.product.acc.domain.app.service.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.product.acc.domain.app.model.QrRequestBean;
import com.product.acc.domain.app.model.ResultQR;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 * QRcode Service Implements
 * @author matsuoka
 * @version $Revision$
 */
@Service
class QRcodeServiceImpl implements QRcodeService {

    /**
     * QRコード出力設定定義
     */
    private enum SettingQR {
        HEIGHT(160),     // 高さ
        WIDTH(160),      // 横幅
        MARGIN(4);       // マージン
        private int value;
        private SettingQR(int value) {
            this.value = value;
        }
    }

    /**
     * QRコード出力ストリーム設定定義
     */
    @AllArgsConstructor
    @Getter
    private enum SettingStreamQR {
        PNG("png");     // PNG
        private String value;
    }

    @Override
    public ResultQR getQRcode(QrRequestBean requestBean) {
        ResultQR result = new ResultQR();
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, SettingQR.MARGIN.value);
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(
            		requestBean.getRequestKey(), BarcodeFormat.QR_CODE, SettingQR.WIDTH.value, SettingQR.HEIGHT.value, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, SettingStreamQR.PNG.value, output);
            result.setImageQR(output.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException we) {
            we.printStackTrace();
        }
        return result;
    }
}
