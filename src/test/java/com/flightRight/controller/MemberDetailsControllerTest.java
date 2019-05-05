/**
 * 
 */
package com.flightRight.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.flightRight.ObjectInitializerForTest;
import com.flightRight.domainobject.FileDO;
import com.flightRight.domainobject.MemberDO;
import com.flightRight.exception.ConstraintsViolationException;
import com.flightRight.exception.EntityNotFoundException;
import com.flightRight.exception.FileFormatException;
import com.flightRight.exception.FileStorageException;
import com.flightRight.exception.InvalidDataViolationException;
import com.flightRight.exception.RequiredDataException;
import com.flightRight.service.MemberService;

/**
 * @author neelam.awasthi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MemberDetailsControllerTest extends ObjectInitializerForTest{

	@Mock
	private MemberService memberService;

	@Autowired
	@InjectMocks
	private MemberDetailsController memberDetailsController;

	/**
	 * Test method for
	 * {@link com.flightRight.controller.MemberDetailsController#getMember(long)}.
	 * 
	 * @throws EntityNotFoundException
	 */
	@Test
	public void testGetMember() throws EntityNotFoundException {
		Mockito.when(memberService.findMemberById(Matchers.anyLong())).thenReturn(memberDO);
		assertEquals(memberDTO.getId(), memberDetailsController.getMember(100).getId());

	}

	/**
	 * Test method for
	 * {@link com.flightRight.controller.MemberDetailsController#getAllMember()}.
	 */
	@Test
	public void testGetAllMember() {
		Mockito.when(memberService.findAll()).thenReturn(memberList);
		assertEquals(1, memberDetailsController.getAllMember().size());
	}

	/**
	 * Test method for
	 * {@link com.flightRight.controller.MemberDetailsController#createMember(org.springframework.web.multipart.MultipartFile, java.lang.String)}.
	 * 
	 * @throws ConstraintsViolationException
	 * @throws SQLException
	 * @throws IOException
	 * @throws InvalidDataViolationException 
	 * @throws RequiredDataException 
	 * @throws FileFormatException 
	 * @throws FileStorageException 
	 */
	@Test
	public void testCreateMember() throws ConstraintsViolationException, IOException, SQLException, InvalidDataViolationException, RequiredDataException, FileStorageException, FileFormatException {
		Mockito.when(memberService.create(Matchers.any(MemberDO.class))).thenReturn(memberDO);
		assertEquals(member.getFirstName(),
				memberDetailsController.createMember(multipartFile, memberStr).getFirstName());
	}

	/**
	 * Test method for
	 * {@link com.flightRight.controller.MemberDetailsController#updateLocation(long, org.springframework.web.multipart.MultipartFile, java.lang.String)}.
	 * @throws IOException 
	 * @throws EntityNotFoundException 
	 * @throws ConstraintsViolationException 
	 * @throws SQLException 
	 * @throws InvalidDataViolationException 
	 * @throws RequiredDataException 
	 * @throws FileFormatException 
	 * @throws FileStorageException 
	 */
	@Test
	public void testUpdateLocation() throws IOException, EntityNotFoundException, SQLException, ConstraintsViolationException, InvalidDataViolationException, RequiredDataException, FileStorageException, FileFormatException {
		Mockito.when(memberService.updateMemberById(Matchers.any(MemberDO.class), Matchers.any(FileDO.class))).thenReturn(memberDO);
		assertEquals(member.getFirstName(),
				memberDetailsController.updateMember(multipartFile, memberStr).getFirstName());
	}

	 /**
	 * Test method for {@link
	 com.flightRight.controller.MemberDetailsController#deleteMember(long)}.
	 * @throws EntityNotFoundException 
	 */
	 @Test
	 public void testDeleteMember() throws EntityNotFoundException {		 
		 Mockito.doNothing().when(memberService).delete(
	                Matchers.any(Long.class));
		 memberDetailsController.deleteMember(memberDO.getId());
	 }

}
