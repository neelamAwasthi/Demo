/**
 * 
 */
package com.flightRight.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flightRight.domainobject.MemberDO;

/**
 * Database Access Object for member table.
 * <p/>
 */
public interface MemberRepository extends CrudRepository<MemberDO, Long>
{

    List<MemberDO> findById(long id);
}
