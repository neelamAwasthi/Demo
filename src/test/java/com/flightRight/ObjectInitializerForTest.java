package com.flightRight;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.flightRight.controller.mapper.MemberMapper;
import com.flightRight.datatransferobject.MemberDTO;
import com.flightRight.domainobject.FileDO;
import com.flightRight.domainobject.MemberDO;
import com.flightRight.util.MemberUtil;

/**
 * @author neelam.awasthi
 *
 */

public class ObjectInitializerForTest {
	
	protected List<MemberDO> memberList;
	protected MemberDO memberDO;
	protected MemberDTO memberDTO;
	
	protected File file;
	protected String memberStr;
	protected FileInputStream input;
	protected MultipartFile multipartFile;
	protected byte[] bFile;
	protected MemberDTO member;
	protected String fileName;
	protected FileDO fileDo;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		memberList = new ArrayList<>();
		memberDO = new MemberDO();
		memberDO.setId(Long.valueOf(100));
		memberDO.setFirstName("testFirstName");
		memberDO.setLastName("testlastName");
		memberDO.setDateOfBirth("02/05/1987");
		memberDO.setPostalCode("56003");
		
		fileDo = new FileDO();
		fileDo.setId("100");
		fileDo.setFileName("spain12.png");
		fileDo.setFileType("image/png");
		
		memberDO.setImage(fileDo);
		memberList.add(memberDO);

		memberDTO = new MemberDTO(Long.valueOf(100), "testFirstName", "testlastName", "02/05/1987", "56003", fileDo, "src\\test\\resources\\orchidPic.jpg");
	
		memberStr = "{\"id\":\"1\", \"firstName\":\"testFirstName\", \"lastName\":\"testlastName\",\"dateOfBirth\":\"02/05/1987\", \"postalCode\":\"56003\"}";

		file = new File("src\\test\\resources\\orchidPic.jpg");
		input = new FileInputStream(file);
		multipartFile = new MockMultipartFile(fileDo.getFileName(), fileDo.getFileName(),fileDo.getFileType(),
				IOUtils.toByteArray(input));
		member = MemberUtil.convertToObject(memberStr);
		//fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		MemberMapper.makeMemberDO(member, fileDo);
	}


}
