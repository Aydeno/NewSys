package com.tools;

import java.awt.image.BufferedImage;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.paint.WidthCodedPainter;

public class BarCode {

	private BufferedImage iamge;
	public BufferedImage getIamge() {return iamge;}
	public void setIamge(BufferedImage iamge) {this.iamge = iamge;}

	public BarCode(String code,String type){
		try{
			JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
			if(type.equals("EAN13")){
				iamge = localJBarcode.createBarcode(code);
			}else if(type.equals("CODE39")){
				localJBarcode.setEncoder(Code39Encoder.getInstance());
				localJBarcode.setPainter(WideRatioCodedPainter.getInstance());
				localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
				localJBarcode.setShowCheckDigit(false);
				localJBarcode.setWideRatio(1.8);
				localJBarcode.setBarHeight(20);
				localJBarcode.setCheckDigit(false);
				iamge = localJBarcode.createBarcode(code);
			}else if(type.equals("CODE128")){
				localJBarcode.setEncoder(Code128Encoder.getInstance());
				localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
				localJBarcode.setShowCheckDigit(false);
				localJBarcode.setWideRatio(1.8);
				localJBarcode.setBarHeight(12);
				localJBarcode.setCheckDigit(false);
				iamge = localJBarcode.createBarcode(code);
			}

		}catch (Exception localException){
			localException.printStackTrace();
		}
	}
	
	

}