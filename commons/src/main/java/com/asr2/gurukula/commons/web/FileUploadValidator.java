package com.asr2.gurukula.commons.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.NativeUploadedFile;
import org.primefaces.model.UploadedFile;

import com.asr2.gurukula.commons.api.Message;
import com.google.common.net.MediaType;

/**
 * Created by abhijith.nagarajan on 11/2/15.
 */
@FacesValidator("com.asr2.gurukula.commons.web.FileUploadValidator")
public class FileUploadValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value instanceof UploadedFile) {
			UploadedFile uploadedFile = (UploadedFile) value;
			if(StringUtils.isEmpty(uploadedFile.getFileName())) {
				context.validationFailed();
				throw new ValidatorException(new Message(FacesMessage.SEVERITY_ERROR, "Input file is required"));
			}

			String contentType = uploadedFile.getContentType();
			if(!MediaType.MICROSOFT_EXCEL.toString().equalsIgnoreCase(contentType) && !MediaType.OOXML_SHEET.toString()
					.equalsIgnoreCase(contentType)) {
				context.validationFailed();
				throw new ValidatorException(new Message(FacesMessage.SEVERITY_ERROR,"Input file is not an excel " +
						"file"));
			}

			long fileSize = uploadedFile.getSize();
			if(fileSize == 0L) {
				context.validationFailed();
				throw new ValidatorException(new Message(FacesMessage.SEVERITY_ERROR,"Input file is empty"));
			}

			if(fileSize > 10_000_000L) {
				context.validationFailed();
				throw new ValidatorException(new Message(FacesMessage.SEVERITY_ERROR,"Input file is too large"));
			}
		}
	}
}
