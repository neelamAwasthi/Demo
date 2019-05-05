package com.flightRight.controller;

import com.flightRight.controller.mapper.MemberMapper;
import com.flightRight.datatransferobject.MemberDTO;
import com.flightRight.domainobject.FileDO;
import com.flightRight.domainobject.MemberDO;
import com.flightRight.exception.ConstraintsViolationException;
import com.flightRight.exception.EntityNotFoundException;
import com.flightRight.exception.FileFormatException;
import com.flightRight.exception.FileStorageException;
import com.flightRight.exception.InvalidDataViolationException;
import com.flightRight.exception.RequiredDataException;
import com.flightRight.service.MemberService;
import com.flightRight.util.MemberUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author neelam.awasthi
 *
 *
 * All operations with a member details will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/member")
public class MemberDetailsController {
	@Autowired
	private MemberService memberService;

	@GetMapping()
	public List<MemberDTO> getAllMember() {
		return MemberMapper.makeAllmemberDTO(memberService.findAll());
	}

	@GetMapping("/{id}")
	public MemberDTO getMember(@PathVariable long id) throws EntityNotFoundException {
		return MemberMapper.makememberDTO(memberService.findMemberById(id));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public MemberDTO createMember(@RequestParam("file") MultipartFile file, @Valid @RequestParam String memberDTO)
			throws ConstraintsViolationException, InvalidDataViolationException, RequiredDataException, IOException,
			FileStorageException, FileFormatException {
		MemberDTO member = MemberUtil.convertToObject(memberDTO);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDO dbFile = new FileDO(fileName, file.getContentType(), file.getBytes());

		MemberDO memberDO = MemberMapper.makeMemberDO(member, dbFile);
		return MemberMapper.makememberDTO(memberService.create(memberDO));
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public MemberDTO updateMember(@RequestParam("file") MultipartFile file, @Valid @RequestParam String memberDTO)
			throws EntityNotFoundException, InvalidDataViolationException, RequiredDataException, IOException,
			FileStorageException, FileFormatException {
		MemberDTO updatedMember = MemberUtil.convertToObject(memberDTO);

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDO fileDo = new FileDO(fileName, file.getContentType(), file.getBytes());

		MemberDO memberDO = MemberMapper.updateMemberDO(updatedMember, fileDo);
		return MemberMapper.makememberDTO(memberService.updateMemberById(memberDO, fileDo));

	}

	@DeleteMapping("/{id}")
	public void deleteMember(@PathVariable long id) throws EntityNotFoundException {
		memberService.delete(id);
	}

}
