package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QR {

    private static final int QRCOLOR = 0xFF000000;

    private static final int BGCOLOR = 0xFFFFFFFF;

    private static final String FORMAT = "PNG";
    public static BufferedImage createCode(String qrUrl,int h,int w) {
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bm = null;
        Map<EncodeHintType, Object> hints = getDecodeHintType();
        BufferedImage image=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        try {
            multiFormatWriter = new MultiFormatWriter();

            bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, w, h, hints);
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGCOLOR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    @SuppressWarnings("deprecation")
	private static Map<EncodeHintType, Object> getDecodeHintType() {

        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();

        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 100);
        return hints;
    }

    public static void Dencode(String contents,String dest,int width,int height,int offset){
    	try{
    		contents=new String(contents.getBytes("UTF-8"),"ISO-8859-1");
        	BitMatrix matrix=new MultiFormatWriter().encode(contents,BarcodeFormat.CODE_128,width-offset, height);
        	MatrixToImageWriter.writeToStream(matrix,FORMAT,new FileOutputStream(new File(dest)));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    public static void encode(String contents,String dest,int width,int height){
    	try{
	    	contents=new String(contents.getBytes("UTF-8"),"ISO-8859-1");
	    	QRCodeWriter writer=new QRCodeWriter();
	    	BitMatrix matrix=writer.encode(contents, BarcodeFormat.QR_CODE, width, height);
	    	MatrixToImageWriter.writeToStream(matrix, FORMAT, new FileOutputStream(new File(dest)));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
}
