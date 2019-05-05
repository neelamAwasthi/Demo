package com.flightRight.controller.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.flightRight.datatransferobject.MemberDTO;
import com.flightRight.domainobject.FileDO;
import com.flightRight.domainobject.MemberDO;
import com.flightRight.exception.FileFormatException;
import com.flightRight.exception.InvalidDataViolationException;
import com.flightRight.exception.RequiredDataException;

/**
 * @author neelam.awasthi
 *
 */
public class MemberMapper {
	/**
	 * @param memberDTO
	 * @param dbFile
	 * @throws InvalidDataViolationException
	 * @throws RequiredDataException
	 * @throws FileFormatException
	 */
	public static MemberDO makeMemberDO(MemberDTO memberDTO, FileDO dbFile)
			throws InvalidDataViolationException, RequiredDataException, FileFormatException {
		validateRequest(memberDTO, dbFile);

		return new MemberDO(memberDTO.getFirstName(), memberDTO.getLastName(), memberDTO.getDateOfBirth(),
				memberDTO.getPostalCode(), dbFile);
	}

	/**
	 * @param memberDTO
	 * @param dbFile
	 * @throws InvalidDataViolationException
	 * @throws RequiredDataException
	 * @throws FileFormatException
	 */
	private static void validateRequest(MemberDTO memberDTO, FileDO dbFile)
			throws InvalidDataViolationException, RequiredDataException, FileFormatException {
		// Check if the file's type contains invalid format
		if (dbFile.getFileName() != null) {
			String mimetype = dbFile.getFileType();
			String type = mimetype.split("/")[0];
			if (!type.equals("image")) {
				throw new FileFormatException("Invalid file format, Please upload image file " + dbFile.getFileName());
			}
		}

		if (memberDTO.getPostalCode() != null) {
			String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(memberDTO.getPostalCode());
			if (!matcher.matches()) {
				throw new InvalidDataViolationException("Invalid PostCode , Please enter the valid postalCode");
			}
		}
		if (memberDTO.getDateOfBirth() != null) {
			String regex = "^(1[0-2]|0[1-9])/(3[01]" + "|[12][0-9]|0[1-9])/[0-9]{4}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher((CharSequence) memberDTO.getDateOfBirth());
			if (!matcher.matches()) {
				throw new InvalidDataViolationException("Invalid DateOfBirth, Please enter the valid dateOfBirth");
			}
		}
		if (memberDTO.getFirstName() == null) {
			throw new RequiredDataException("FirstName is empty, Please enter the firstName");
		}
	}

	/**
	 * @param memberDTO
	 * @param dbFile
	 * @throws InvalidDataViolationException
	 * @throws RequiredDataException
	 * @throws FileFormatException
	 */
	public static MemberDO updateMemberDO(MemberDTO memberDTO, FileDO dbFile)
			throws InvalidDataViolationException, RequiredDataException, FileFormatException {
		validateRequest(memberDTO, dbFile);
		return new MemberDO(memberDTO.getId(), memberDTO.getFirstName(), memberDTO.getLastName(),
				memberDTO.getDateOfBirth(), memberDTO.getPostalCode(), dbFile);
	}

	/**
	 * @param memberDTO
	 */
	public static MemberDTO makememberDTO(MemberDO memberDO) {
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(memberDO.getImage().getId()).toUriString();
		MemberDTO.MemberDTOBuilder memberDTOBuilder = MemberDTO.newBuilder().setId(memberDO.getId())
				.setFirstName(memberDO.getFirstName()).setLastName(memberDO.getLastName())
				.setDateOfBirth(memberDO.getDateOfBirth()).setPostalCode(memberDO.getPostalCode())
				.setImage(memberDO.getImage(), fileDownloadUri);

		return memberDTOBuilder.createMemberDTO();
	}

	/**
	 * @param memberList
	 */
	public static List<MemberDTO> makeAllmemberDTO(Iterable<MemberDO> memberList) {
		List<MemberDTO> memberDTOBuilderList = new ArrayList<>();
		for (MemberDO memberDO : memberList) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
					.path(memberDO.getImage().getId()).toUriString();
			MemberDTO.MemberDTOBuilder memberDTOBuilder = MemberDTO.newBuilder().setId(memberDO.getId())
					.setFirstName(memberDO.getFirstName()).setLastName(memberDO.getLastName())
					.setDateOfBirth(memberDO.getDateOfBirth()).setPostalCode(memberDO.getPostalCode())
					.setImage(memberDO.getImage(), fileDownloadUri);
			MemberDTO memberDto = memberDTOBuilder.createMemberDTO();
			memberDTOBuilderList.add(memberDto);
		}
		return memberDTOBuilderList;
	}
}
