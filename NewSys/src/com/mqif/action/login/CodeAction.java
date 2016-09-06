package com.mqif.action.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.tools.Struts2Util;

@Namespace("/")
public class CodeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private ByteArrayInputStream inputstream;
	public ByteArrayInputStream getInputstream(){return inputstream;}
	public void setInputstream(ByteArrayInputStream inputstream){this.inputstream = inputstream;}

	@Action(value="code",results={
			@Result(name="code",type="stream",params={
					"contentType","image/jpeg",
					"inputName","inputstream",
					"bufferSize","4096"}),
	})
	public String code() {
		String s = "";
		int width = 170;//验证码宽度
		int height = 45;//验证码高度
		//验证码字符集
		char[] code = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', '2', '3', '4', '5', '6', '7', '8', '9' };
		
		//创建一个随机数生成器类
		Random random = new Random();

		// 随机产生codeCount数字的验证码。   
		for (int i = 0; i < 4; i++) {
			// 得到随机产生的验证码数字。   
			String strRand = String.valueOf(code[random.nextInt(code.length)]);
			// 将产生的四个随机数组合在一起。   
			s = s + strRand;
		}

		// 保存入session,用于与用户的输入进行比较.
		// 注意比较完之后清除session.
		Struts2Util.getSession().setMaxInactiveInterval(180);//有效期3分钟，过期重输，登陆成功后改为15分钟，
		Struts2Util.getSession().setAttribute("validateCode", s);

		// 定义图像buffer   
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D gra = image.createGraphics();
		// 将图像填充为白色   
		gra.setColor(Color.WHITE);
		gra.fillRect(0, 0, width, height);

		// 创建字体，字体的大小应该根据图片的高度来定。  

		//字体对象构造方法public Font(String familyName,int style,int size)
		// familyName字体名；字体名可以分成两大类：中文字体：宋体、楷体、黑体等；英文字体：Arial、Times New Roman等等；
		// style风格。PLAIN普通字体，BOLD（加粗），ITALIC（斜体），Font.BOLD+ Font.ITALIC（粗斜体）
		//size 大小

		Font font = new Font("宋体", Font.BOLD + Font.ITALIC, height - 1);//设置字体。   
		gra.setFont(font);

		// 随机产生干扰线，使图象中的认证码不易被其它程序探测到。   
		gra.setColor(Color.BLACK);
		for (int i = 0; i < 50; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(5);
			int yl = random.nextInt(5);
			gra.setColor(Color.decode("#000000"));
			gra.drawLine(x, y, x + xl, y + yl);
		}

		// 输出数字
		char c;
		for (int i = 0; i < 4; i++) {
			c = s.charAt(i);
			gra.drawString(c + "", i * 38 + 4, 38); // 25为宽度，11为上下高度位置

		}
		gra.dispose();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut;
		try {
			imageOut = ImageIO.createImageOutputStream(output);
			ImageIO.write(image, "JPEG", imageOut);
			imageOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		inputstream = new ByteArrayInputStream(output.toByteArray());

		return "code";
	}
}
