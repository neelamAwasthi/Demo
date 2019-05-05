/**
 * 
 */
package com.flightRight.service;

import java.io.IOException;
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
import com.flightRight.dataaccessobject.MemberRepository;
import com.flightRight.domainobject.MemberDO;
import com.flightRight.exception.ConstraintsViolationException;
import com.flightRight.exception.EntityNotFoundException;

/**
 * @author neelam.awasthi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DefaultMemberServiceTest extends ObjectInitializerForTest{
	
	@Mock
	private MemberRepository memberRepository;

	@Autowired
	@InjectMocks
	private DefaultMemberService defaultMemberService;

	
	/**
	 * Test method for {@link com.flightRight.service.DefaultMemberService#create(com.flightRight.domainobject.MemberDO)}.
	 * @throws ConstraintsViolationException 
	 */
	@Test
	public void testCreate() throws ConstraintsViolationException {
		Mockito.when(memberRepository.save(Matchers.any(MemberDO.class))).thenReturn(memberDO);
		defaultMemberService.create(memberDO);
	}

	/**
	 * Test method for {@link com.flightRight.service.DefaultMemberService#findAll()}.
	 */
	@Test
	public void testFindAll() {
		Mockito.when(memberRepository.findAll()).thenReturn(memberList);
		defaultMemberService.findAll();
	}

	/**
	 * Test method for {@link com.flightRight.service.DefaultMemberService#findMemberById(java.lang.Long)}.
	 * @throws EntityNotFoundException 
	 */
	public void testFindMemberById() throws EntityNotFoundException {
		defaultMemberService.findMemberById(Long.valueOf(1001));
	}
	
	/**
	 * Test method for {@link com.flightRight.service.DefaultMemberService#delete(java.lang.Long)}.
	 * @throws EntityNotFoundException 
	 */
	@Test(expected = EntityNotFoundException.class)
	public void testDelete() throws EntityNotFoundException {
		defaultMemberService.delete(Long.valueOf(10));
	}

	/**
	 * Test method for {@link com.flightRight.service.DefaultMemberService#updateMemberById(com.flightRight.domainobject.MemberDO, byte[])}.
	 * @throws IOException 
	 * @throws EntityNotFoundException 
	 */
	@Test(expected = EntityNotFoundException.class)
	public void testUpdateMemberById() throws IOException, EntityNotFoundException {
		defaultMemberService.updateMemberById(memberDO, fileDo);
	}

}
