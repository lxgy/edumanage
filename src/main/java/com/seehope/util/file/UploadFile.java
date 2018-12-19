package com.seehope.util.file;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadFile {
	Map<String, MultipartFile> fileMap = null;
	public String uploadPath = null;
	public File saveFile = null;
	public String oriName = "";
	public String fileName = "";
	public String fileNamePrefix = "";
	public String extName = "";
	public long fileSize;
	MultipartHttpServletRequest multipartRequest;
	private static final Log _Log = LogFactory.getLog(UploadFile.class);

	public UploadFile(HttpServletRequest request, String uploadPath) {
		this.multipartRequest = ((MultipartHttpServletRequest) request);
		this.uploadPath = uploadPath;

		this.fileMap = this.multipartRequest.getFileMap();

		File myFilePath = new File(uploadPath);
		if (!myFilePath.exists()) {
			myFilePath.mkdirs();
		}
	}

	public void saveFile() throws Exception {
		if ((this.fileMap == null) || (this.fileMap.size() == 0)) {
			return;
		}
		for (Map.Entry<String, MultipartFile> entity : this.fileMap.entrySet()) {
			MultipartFile mf = (MultipartFile) entity.getValue();
			this.oriName = mf.getOriginalFilename();
			this.fileSize = mf.getSize();
			if ((this.oriName != null) && (!this.oriName.trim().equals(""))) {
				this.extName = this.oriName.substring(this.oriName.lastIndexOf(".")).toLowerCase();
				if ((this.oriName != null) && (!this.oriName.trim().equals(""))) {
					if ((this.fileName == null) || (this.fileName.length() == 0)) {
						this.fileNamePrefix = UUID.randomUUID().toString();
						this.fileName = (this.fileNamePrefix + this.extName);
					}
					try {
						this.saveFile = new File(this.uploadPath + this.fileName);
						FileCopyUtils.copy(mf.getBytes(), this.saveFile);
					} catch (Exception e) {
						_Log.error(e, e);
					}
				}
			}
		}
	}
}
