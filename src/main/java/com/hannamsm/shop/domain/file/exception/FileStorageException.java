package com.hannamsm.shop.domain.file.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.BusinessException;

public class FileStorageException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public FileStorageException() {
		super(ErrorCode.FILE_STORAGE_ERROR);
	}
}
