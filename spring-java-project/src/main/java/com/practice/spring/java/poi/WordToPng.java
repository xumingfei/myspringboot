package com.practice.spring.java.poi;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;

import java.io.IOException;

public class WordToPng {
    public static void main(String[]args) throws IOException {
        //加载测试文档
        Document doc = new Document("C:\\Users\\Xiaofei\\Desktop\\冯羽简历_苏州-医药-市场主管.docx");

        //将文档指定页保存为Png格式的图片
//        BufferedImage image = doc.saveToImages( 0, ImageType.Bitmap);
//        File file = new File("ToPNG.png");
//        ImageIO.write(image, "PNG", file);

        //将Word转为PDF
//        doc.saveToFile("Word转PDF.pdf", FileFormat.PDF);

        //将Word保存为SVG格式
//        doc.saveToFile("ToSVG.svg",FileFormat.SVG);

        //将Word保存为RTF格式
//        doc.saveToFile("ToRTF.rtf",FileFormat.Rtf);

        //将Word保存为XPS格式
//        doc.saveToFile("ToXPS.xps",FileFormat.XPS);

        //将Word保存为XML格式
//        doc.saveToFile("ToXML.xml",FileFormat.Xml);

        //将Word保存为TXT格式
        doc.saveToFile("ToTXT.txt",FileFormat.Txt);
    }
}
