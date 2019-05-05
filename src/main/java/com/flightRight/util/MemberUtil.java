/**
 * 
 */
package com.flightRight.util;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.web.multipart.MultipartFile;

import com.flightRight.datatransferobject.MemberDTO;
import com.google.gson.Gson;

/**
 * @author neelam.awasthi
 *
 */
public class MemberUtil {
	/**
	 * @param file
	 * @return
	 */
	public static byte[] convertToByte(MultipartFile file) {
		File convertFile = new File(file.getName());
		byte[] bFile = new byte[(int) convertFile.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(convertFile);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFile;
	}
	
	/**
	 * @param memberDTO
	 * @return
	 */
	public static MemberDTO convertToObject(String memberDTO) {
		// Creating a Gson Object
		Gson gson = new Gson();

		// Converting json to object
		MemberDTO member = gson.fromJson(memberDTO, MemberDTO.class);
		return member;
	}

}
