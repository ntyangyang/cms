package cms.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cms.bean.ImageBack;

@Controller
public class ImageController {

	@RequestMapping(value = "/image/upload.json", method = RequestMethod.POST)
	@ResponseBody
	public ImageBack imgUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model) {
		ImageBack back = new ImageBack();
		if (!file.isEmpty()) {
			if (file.getContentType().contains("image")) {
				try {
					// 获取图片的文件名
					String fileName = file.getOriginalFilename();
					// 获取图片的扩展名
					String extensionName = StringUtils.substringAfter(fileName, ".");
					// 新的图片文件名 = 获取时间戳+"."图片扩展名
					String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
					// 数据库保存的目录
					// 文件路径
					String filePath = request.getServletContext().getRealPath("images");
					
					System.out.println(filePath);

					File dest = new File(filePath, newFileName);
					if (!dest.getParentFile().exists()) {
						dest.getParentFile().mkdirs();
					}
					// 上传到指定目录
					file.transferTo(dest);

					String data = "/images/" + newFileName;
					back.setImgUrl(data);
					back.setSuccess(true);
				} catch (IOException e) {
					back.setSuccess(false);
				}
			} else {
				back.setSuccess(false);
			}
			return back;
		} else {
			back.setSuccess(false);
			return back;
		}
	}

}
