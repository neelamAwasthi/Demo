package com.flightRight.service;

import com.flightRight.domainobject.FileDO;
import com.flightRight.domainobject.MemberDO;
import com.flightRight.exception.ConstraintsViolationException;
import com.flightRight.exception.EntityNotFoundException;

/**
 * @author neelam.awasthi
 *
 */
public interface MemberService {
	MemberDO findMemberById(Long id) throws EntityNotFoundException;

	MemberDO create(MemberDO memberDO) throws ConstraintsViolationException;

	void delete(Long id) throws EntityNotFoundException;

	Iterable<MemberDO> findAll();

	MemberDO updateMemberById(MemberDO memberDO, FileDO fileDo) throws EntityNotFoundException;
}
