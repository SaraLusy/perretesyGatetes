package com.empresa.perretesGatetes.persistence;

import com.empresa.perretesGatetes.business.IBaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class IBaseRepositoryImpl implements IBaseRepository {
    @PersistenceContext
    public EntityManager em;

}
