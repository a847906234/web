package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import org.w3c.dom.Element;

import information.Information;
public class Jpgpicture {


	public static void control(String format,ArrayList<Information>list) {
		
		ArrayList<Information> newList= new ArrayList<Information>();
		int i=0;
		int beginCount=0;

		while(i<list.size()) {
			newList.add(list.get(i));
			i++;
			if(i==7)
				break;
			while(list.get(i).getEMSTrackNum()==null){
				newList.add(list.get(i));
				i++;
			}
			String srcImgPath = Order.orderProduct(newList.get(0).getOrderNum(),format);
			createimage(srcImgPath,newList);
			newList.clear();
		}
	}

	public static void createimage(String srcImgPath, ArrayList<Information>list) {
		float d= (float) 0.7;
		int realHeight = 200;
		int beginInterval=20;
		int textStandard =15;
		int halfwid=(int) global.Global.IMAGEWIDTH/2;
		ImageObserver observer = null;
		int gap=50;
		Image Img = null;

		BufferedImage image = new BufferedImage(global.Global.IMAGEWIDTH,global.Global.IMAGEHEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, global.Global.IMAGEWIDTH,global.Global.IMAGEHEIGHT);
		graphics.setColor(Color.black);

		graphics.setFont(new Font("黑体", Font.ITALIC, 50));
		graphics.drawString("EMS",70,100);
		graphics.setFont(new Font("黑体", Font.ITALIC, 25));
		graphics.drawString("E标准", 85, 135);

		
		

		BufferedImage QRcodeImage=QR.createCode(list.get(0).getEMSTrackNum(),300,300);

		graphics.drawImage(QRcodeImage,600,1050,325,325, observer);
		
		
		graphics.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));

		for(int i=0;i<5;i++)
			graphics.drawLine(i, 0, i,global.Global.IMAGEHEIGHT);
		for(int i=0;i<5;i++)
			graphics.drawLine(0, i, global.Global.IMAGEWIDTH,i);
		for(int i=0;i<5;i++)
			graphics.drawLine(0,global.Global.IMAGEHEIGHT,global.Global.IMAGEWIDTH, global.Global.IMAGEHEIGHT-i);
		for(int i=0;i<5;i++)
			graphics.drawLine(global.Global.IMAGEWIDTH-i, 0, global.Global.IMAGEWIDTH-i, global.Global.IMAGEHEIGHT);
		for(int i=0;i<5;i++)
			graphics.drawLine(0, realHeight-i, global.Global.IMAGEWIDTH, realHeight-i);
		for(int i=0;i<5;i++)
			graphics.drawLine(0, 2*realHeight-i,global.Global.IMAGEWIDTH, 2*realHeight-i);
		for(int i=0;i<5;i++)
			graphics.drawLine(0, 3*realHeight-i, global.Global.IMAGEWIDTH, 3*realHeight-i);
		for(int i=0;i<5;i++)
			graphics.drawLine(0, 4*realHeight-i,global.Global.IMAGEWIDTH, 4*realHeight-i);
		for(int i=0;i<5;i++)
			graphics.drawLine(0, 5*realHeight-i, global.Global.IMAGEWIDTH, 5*realHeight-i);
		for(int i=0;i<5;i++)
			graphics.drawLine(0, 6*realHeight-i,global.Global.IMAGEWIDTH-2*realHeight, 6*realHeight-i);
		for(int i=0;i<5;i++)
			graphics.drawLine(global.Global.IMAGEWIDTH-2*realHeight,1000,global.Global.IMAGEWIDTH-2*realHeight,1400);
		for(int i=0;i<5;i++)
			graphics.drawLine(halfwid,3*realHeight,halfwid,4*realHeight);
		

		graphics.setFont(new Font("黑体", Font.ITALIC,25));
		for(Information info:list) {
			graphics.drawString("内件明细："+list.get(0).getMainGoods(), beginInterval,gap+realHeight);	
		}

		graphics.drawString("收件人:"+list.get(0).getReceiName(), beginInterval, 2*realHeight+gap);
		graphics.drawString("电话:"+list.get(0).getReceiTel(),halfwid+beginInterval, 2*realHeight+gap);
		graphics.drawString("地址:"+list.get(0).getReceiAddr(), beginInterval, 2*realHeight+2*gap);
		graphics.drawString("邮编:"+list.get(0).getReceiProvinCode(), beginInterval, 2*realHeight+3*gap);
		graphics.drawString("内件数量:"+list.get(0).getNumber()+"件", beginInterval,3*realHeight+ gap);
		graphics.drawString("净重"+list.get(0).getPureWeight()+"kg",beginInterval,3*realHeight+2*gap);
		graphics.drawString("毛重"+list.get(0).getRoughWeight()+"kg",beginInterval,3*realHeight+3*gap);
		graphics.setFont(new Font("黑体",Font.ITALIC,40));
		graphics.drawString("全程", halfwid-5*beginInterval, 3*realHeight+gap);
		graphics.drawString("陆运", halfwid-5*beginInterval, 3*realHeight+2*gap);
		graphics.setFont(new Font("黑体",Font.ITALIC,25));
		graphics.drawString("收件人/代收件人:",halfwid+ beginInterval,3*realHeight+ gap/2);
		graphics.drawString("签收日期:",halfwid+ beginInterval, 4*realHeight-gap);
		graphics.drawString("		年",halfwid+12* beginInterval, 4*realHeight-gap);
		graphics.drawString("		月",halfwid+15* beginInterval, 4*realHeight-gap);
		graphics.drawString("		日",halfwid+18* beginInterval, 4*realHeight-gap);

		String lsh=Order.lsh(list.get(0).getOrderNum());
		graphics.drawString("订单号", beginInterval, 4*realHeight+gap);
		graphics.drawString(lsh, 17*beginInterval, 5*realHeight-gap);
		String tempaddr = Order.DcodeProduct(lsh);
		QR.Dencode(lsh, tempaddr, 600, 200, 100);
		try {
			FileInputStream fis = new FileInputStream(tempaddr);
			Img=ImageIO.read(fis);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		graphics.drawImage(Img, 8*beginInterval, 4*realHeight+gap/2, 600, 100, observer);

		graphics.drawString("发件人:"+list.get(0).getConsignerName(), beginInterval, 5*realHeight+gap);
		graphics.drawString("Tel:"+list.get(0).getConsignerTel(), halfwid-6*beginInterval, 5*realHeight+gap);
		graphics.drawString("发件人省市区代码"+list.get(0).getConsignerProvinCode(), beginInterval, 5*realHeight+2*gap);
		
		graphics.drawString("收件人："+list.get(0).getReceiName(),beginInterval,6*realHeight+gap);
		graphics.drawString(list.get(0).getReceiTel(), halfwid-6*beginInterval, realHeight*6+gap);
		graphics.drawString("收件地址："+list.get(0).getReceiAddr().substring(0, 15), beginInterval, realHeight*6+gap*2);
		graphics.drawString(list.get(0).getReceiAddr().substring(15), beginInterval, realHeight*6+gap*3);

		lsh=list.get(0).getEMSTrackNum();

		tempaddr = Order.DcodeProduct(lsh);
		QR.Dencode(lsh, tempaddr, 600, 200, 100);
		try {
			FileInputStream fis = new FileInputStream(tempaddr);
			Img=ImageIO.read(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}
		graphics.drawImage(Img, 16*beginInterval, gap/2, 600, 100, observer);
		graphics.drawString(lsh, 25*beginInterval, realHeight-gap);


		FileOutputStream fos;
		try {
			fos = new FileOutputStream(srcImgPath);
			saveAsJPEG(100, image,d, fos);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	public static void saveAsJPEG(Integer dpi, BufferedImage image_to_save, float d, FileOutputStream fos)
			throws IOException {
		ImageWriter imageWriter = ImageIO.getImageWritersBySuffix("jpg").next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
		imageWriter.setOutput(ios);
		IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image_to_save), null);
		if (dpi != null && !dpi.equals("")) {
			Element tree = (Element) imageMetaData.getAsTree("javax_imageio_jpeg_image_1.0");
			Element jfif = (Element) tree.getElementsByTagName("app0JFIF").item(0);
			jfif.setAttribute("Xdensity", Integer.toString(dpi));
			jfif.setAttribute("Ydensity", Integer.toString(dpi));
		}
		if (d >= 0 && d <= 1f) {
			JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
			jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
			jpegParams.setCompressionQuality(d);
		}
		imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), null);
		ios.close();
		imageWriter.dispose();
	}
}
