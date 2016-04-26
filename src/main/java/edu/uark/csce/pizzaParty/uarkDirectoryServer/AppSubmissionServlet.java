package edu.uark.csce.pizzaParty.uarkDirectoryServer;

import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("Duplicates")
@MultipartConfig
public class AppSubmissionServlet extends HttpServlet
{
	private final static String[] PARAM_VALUES = {"appName", "appDesc", "appVersion", "developer", "thumb", "image1", "image2", "image3", "image4", "image5", "apk"};
	private final static String FILE_NAME = "filename=\"";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Map<String, String> paramMap = new HashMap<>();
		Part part;
		int numImages = 0;

		for (String param : PARAM_VALUES)
		{
			switch (param)
			{
				case "thumb":
					Part thumbPart = request.getPart("thumb");
					if (thumbPart.getSize() == 0)
					{
						response.sendRedirect("error.html");
						return;
					}
					else
					{
						saveImage(thumbPart, paramMap.get("appName"));
					}
					break;
				case "image1":
					part = request.getPart("image1");
					if (part.getSize() > 0)
					{
						numImages++;
						saveImage(part, paramMap.get("appName") + numImages);
					}
					break;
				case "image2":
					part = request.getPart("image2");
					if (part.getSize() > 0)
					{
						numImages++;
						saveImage(part, paramMap.get("appName") + numImages);
					}
					break;
				case "image3":
					part = request.getPart("image3");
					if (part.getSize() > 0)
					{
						numImages++;
						saveImage(part, paramMap.get("appName") + numImages);
					}
					break;
				case "image4":
					part = request.getPart("image4");
					if (part.getSize() > 0)
					{
						numImages++;
						saveImage(part, paramMap.get("appName") + numImages);
					}
					break;
				case "image5":
					part = request.getPart("image5");
					if (part.getSize() > 0)
					{
						numImages++;
						saveImage(part, paramMap.get("appName") + numImages);
					}
					break;
				case "apk":
					part = request.getPart("apk");
					if (part.getSize() == 0)
					{
						response.sendRedirect("error.html");
						return;
					}
					else
					{
						String header = part.getHeader("content-disposition");
						String apkName = header.substring(header.indexOf(FILE_NAME) + FILE_NAME.length()).replace("\"", "");
						paramMap.put("apkName", apkName);
						InputStream apkStream = part.getInputStream();
						File apk = new File("/var/www/html/" + apkName);
						FileOutputStream apkOutputStream = new FileOutputStream(apk);
						IOUtils.copy(apkStream, apkOutputStream);
					}
					break;
				default:
					String[] paramValues = request.getParameterValues(param);
					String value = paramValues[0];
					if (value != null && !value.isEmpty())
					{
						paramMap.put(param, value);
					}
					else
					{
						response.sendRedirect("error.html");
						return;
					}
					break;
			}
		}
		if (sendRequest(paramMap.get("appName"), paramMap.get("appDesc"), paramMap.get("appVersion"), paramMap.get("developer"), numImages, paramMap.get("apkName")))
		{
			response.sendRedirect("submit.html");
		}
		else
		{
			response.sendRedirect("error.html");
		}

	}

	private void saveImage(Part imagePart, String imageName) throws IOException
	{
		InputStream inputStream = imagePart.getInputStream();
		BufferedImage bufferedImage = ImageIO.read(inputStream);
		FileOutputStream fos = new FileOutputStream(new File("/var/www/html/" + imageName + ".png"));
		ImageIO.write(bufferedImage, "png", fos);
	}

	private boolean sendRequest(String appName, String appDesc, String appVersion, String developer, int numImages, String apkName)
	{
		try
		{
			App app = new App();
			app.setName(appName);
			app.setDescription(appDesc);
			app.setVersion(appVersion);
			app.setDeveloper(developer);
			app.setCreateDate(new Date(System.currentTimeMillis()));
			app.setNumImages(numImages);
			app.setApkName(apkName);
			AppRepository appRepository = new AppRepository();
			appRepository.postApp(app);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
