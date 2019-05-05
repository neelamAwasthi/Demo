/**
 * 
 */
package com.flightRight.controller.mapper;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.flightRight.ObjectInitializerForTest;
import com.flightRight.datatransferobject.MemberDTO;
import com.flightRight.domainobject.MemberDO;
import com.flightRight.exception.FileFormatException;
import com.flightRight.exception.InvalidDataViolationException;
import com.flightRight.exception.RequiredDataException;

/**
 * @author neelam.awasthi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MemberMapperTest extends ObjectInitializerForTest{
	
	/**
	 * Test method for {@link com.flightRight.controller.mapper.MemberMapper#makeMemberDO(com.flightRight.datatransferobject.MemberDTO, byte[], java.lang.String)}.
	 */
	@Test
	public void testMakeMemberDO() {
		 List<MemberDTO> memberDTOList = MemberMapper.makeAllmemberDTO(memberList);
		 assertEquals(1, memberDTOList.size());
	}

	/**
	 * Test method for {@link com.flightRight.controller.mapper.MemberMapper#updateMemberDO(long, com.flightRight.datatransferobject.MemberDTO, byte[], java.lang.String)}.
	 * @throws IOException 
	 * @throws RequiredDataException 
	 * @throws InvalidDataViolationException 
	 * @throws FileFormatException 
	 */
	@Test
	public void testUpdateMemberDO() throws IOException, InvalidDataViolationException, RequiredDataException, FileFormatException {
		MemberDO memberDO  = MemberMapper.updateMemberDO(memberDTO, fileDo);
		assertNotNull(memberDO);
	}

	/**
	 * Test method for {@link com.flightRight.controller.mapper.MemberMapper#makememberDTO(com.flightRight.domainobject.MemberDO)}.
	 */
	@Test
	public void testMakememberDTO() {
		MemberDTO memberDTO = MemberMapper.makememberDTO(memberDO);
		assertNotNull(memberDTO);
	}

	/**
	 * Test method for {@link com.flightRight.controller.mapper.MemberMapper#makeAllmemberDTO(java.lang.Iterable)}.
	 */
	@Test
	public void testMakeAllmemberDTO() {
		List<MemberDTO> memberDTOList = MemberMapper.makeAllmemberDTO(memberList);
		assertEquals(1, memberDTOList.size());
	}

}
