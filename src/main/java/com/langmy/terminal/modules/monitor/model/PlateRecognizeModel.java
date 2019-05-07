package com.langmy.terminal.modules.monitor.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONObject;

public class PlateRecognizeModel {
	private String plate;// 车牌号
	private String color;// 车牌颜色
	private double plateR;// 车牌识别可信度
	private List<Double> codeR;// 车牌每个字符的可信度
	private String ip;//摄像头IP
	
	private int picType;//图片类型 0:jpeg 1:bmp
	private int picLength;//图片长度
	private String pic;//获得图片的二进制流数组，将byte数组以Base64方式编码为字符串
	
	
//	 /**
//     * TODO:将byte数组以Base64方式编码为字符串
//     * @param bytes 待编码的byte数组
//     * @return 编码后的字符串
//     * */
//    public static String encode(byte[] bytes){
//        return new BASE64Encoder().encode(bytes);
//    }
	

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPlateR() {
		return plateR;
	}

	public void setPlateR(double plateR) {
		this.plateR = plateR;
	}

	public List<Double> getCodeR() {
		return codeR;
	}

	public void setCodeR(List<Double> codeR) {
		this.codeR = codeR;
	}

	public int getPicType() {
		return picType;
	}

	public void setPicType(int picType) {
		this.picType = picType;
	}

	public int getPicLength() {
		return picLength;
	}

	public void setPicLength(int picLength) {
		this.picLength = picLength;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public static void main(String[] args) {
		BASE64Encoder encoder = new sun.misc.BASE64Encoder();      
	    BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		PlateRecognizeModel model = new PlateRecognizeModel();
		model.setPlate("浙A2k581");
		model.setColor("蓝色");
		List<Double> codeR = new ArrayList<Double>();
		codeR.add(99.0d);
		codeR.add(88d);
		model.setCodeR(codeR);
		model.setPlateR(88d);
		
		model.setPicType(0);
		
		File f = new File("d://pspNoCarWithLock.jpg");             
	    BufferedImage bi;      
        try {      
            bi = ImageIO.read(f);      
            ByteArrayOutputStream baos = new ByteArrayOutputStream();      
            ImageIO.write(bi, "jpg", baos);      
            byte[] bytes = baos.toByteArray();
            model.setPicLength(bytes.length);
            String base64Str = encoder.encodeBuffer(bytes).trim();
            model.setPic(base64Str);
                  
        } catch (IOException e) {      
            e.printStackTrace();      
        }  
		model.setIp("192.168.0.141");
		System.out.println(JSONObject.toJSONString(model));
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
