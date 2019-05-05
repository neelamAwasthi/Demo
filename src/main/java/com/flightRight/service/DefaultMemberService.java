package com.flightRight.service;

import com.flightRight.dataaccessobject.MemberRepository;
import com.flightRight.domainobject.FileDO;
import com.flightRight.domainobject.MemberDO;
import com.flightRight.exception.ConstraintsViolationException;
import com.flightRight.exception.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultMemberService implements MemberService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMemberService.class);

	@Autowired
	private MemberRepository memberRepository;

	/**
	 * Creates a new member details.
	 *
	 * @param memberDO
	 * @return
	 * @throws ConstraintsViolationException
	 *             if a member already exists with the given id, firstName, ... .
	 */
	@Override
	public MemberDO create(MemberDO memberDO) throws ConstraintsViolationException {
		MemberDO member;
		try {
			member = memberRepository.save(memberDO);
		} catch (DataIntegrityViolationException e) {
			LOGGER.warn("ConstraintsViolationException while creating a member: {}", memberDO, e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return member;
	}

	/**
	 * Selects a all member.
	 *
	 * @param id
	 * @return found member details
	 * @throws EntityNotFoundException
	 *             if no member with the given id was found.
	 */
	public Iterable<MemberDO> findAll() {
		return memberRepository.findAll();
	}

	/**
	 * Selects a member by id.
	 *
	 * @param id
	 * @return found member details
	 * @throws EntityNotFoundException
	 *             if no member with the given id was found.
	 */
	public MemberDO findMemberById(Long Id) throws EntityNotFoundException {
		return memberRepository.findById(Id)
				.orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + Id));
	}

	/**
	 * Deletes an existing member by id.
	 *
	 * @param id
	 * @throws EntityNotFoundException
	 *             if no member with the given id was found.
	 */
	@Override
	@Transactional
	public void delete(Long id) throws EntityNotFoundException {
		MemberDO memberDO = findMemberById(id);
		memberRepository.deleteById(memberDO.getId());

	}

	/**
	 * Updates a member details.
	 *
	 * @param memberDO
	 * @param fileDo
	 * @return
	 * @throws EntityNotFoundException
	 *             if a member does not exists with the given id ... .
	 */
	@Override
	public MemberDO updateMemberById(MemberDO memberDO, FileDO fileDo) throws EntityNotFoundException {
		MemberDO existingMemberDO = findMemberById(memberDO.getId());
		if (existingMemberDO != null) {
			existingMemberDO.setFirstName(memberDO.getFirstName());
			existingMemberDO.setLastName(memberDO.getLastName());
			existingMemberDO.setDateOfBirth(memberDO.getDateOfBirth());

			existingMemberDO.setPostalCode(memberDO.getPostalCode());

			FileDO fileUpdated = new FileDO();
			fileUpdated.setId(memberDO.getImage().getId());
			fileUpdated.setFileName(fileDo.getFileName());
			fileUpdated.setFileType(fileDo.getFileType());
			fileUpdated.setData(fileDo.getData());

			existingMemberDO.setImage(fileUpdated);
			memberRepository.save(existingMemberDO);
		} else {
			throw new EntityNotFoundException("Could not find entity with id: " + memberDO.getId());
		}

		return existingMemberDO;

	}

}
