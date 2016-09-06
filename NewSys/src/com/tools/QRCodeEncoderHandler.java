package com.tools;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.swetake.util.Qrcode;

public class QRCodeEncoderHandler {
	 public BufferedImage encoderQRCode(String content) throws Exception  {
		 BufferedImage bufImg = null; 
		 Qrcode qrcodeHandler = new Qrcode();  
		 qrcodeHandler.setQrcodeErrorCorrect('M');
		 qrcodeHandler.setQrcodeEncodeMode('B');
		 qrcodeHandler.setQrcodeVersion(7);
		 byte[] contentBytes = content.getBytes("utf-8"); 
		 int imgSize = 820; 
		 bufImg= new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		 Graphics2D gs = bufImg.createGraphics(); 
		 gs.setBackground(Color.WHITE);  
         gs.clearRect(0, 0, imgSize, imgSize); 
         gs.setColor(Color.BLACK);  // 设定图像颜色
         int pixoff = 2; // 设置偏移量 不设置可能导致解析出错  
         if (contentBytes.length > 0 && contentBytes.length < 800) {  
             boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
             for (int i = 0; i < codeOut.length; i++) {  
                 for (int j = 0; j < codeOut.length; j++) {  
                     if (codeOut[j][i]) {  
                         gs.fillRect(j * 18 + pixoff, i * 18 + pixoff, 22, 22);  
                     }  
                 }  

             }  
         }else{
        	 throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800]."); 
         }
         
         gs.dispose();
         bufImg.flush();
         return bufImg; 
	 }
}
