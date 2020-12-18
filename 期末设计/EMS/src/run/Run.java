package run;

import java.io.IOException;
import java.util.ArrayList;

import information.Information;
import main.Jpgpicture;
import main.Load;
import main.Pdf;

public class Run {
	public static void main(String[] args) {
		String format=global.Global.FORMAT;
		String fileName =global.Global.FILENAME;
		ArrayList<Information>list = Load.readFromExcel(fileName);
		Jpgpicture.control(format, list);
		System.out.println("图片生成完成，请在save文件夹中查看");
		for(int i=1;i<4;i++) {
			try {
				String imgSrc=global.Global.PICPLACE+global.Global.TAG+String.valueOf(i)+global.Global.FORMAT;
				String pdfSrc = global.Global.PICPLACE+"物流面单"+String.valueOf(i)+".pdf";
				Pdf.imgToPdf(imgSrc,pdfSrc);
				System.out.println(pdfSrc+"文件生成完毕");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
