package com.asr2.gurukula.commons.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.asr2.gurukula.commons.api.Message;

/**
 * Created by abhijith.nagarajan on 8/12/15.
 */
public class GenericDAO<T extends SysEntity> implements Serializable {

	@Inject
	private EntityManager em;

	public EntityManager getEm() {
		return this.em;
	}

	public Result<T> save(T branch) {

		Result<T> result = new Result<T>();
		try {
			em.persist(branch);
			em.flush();
			result.setIsTxSuccessful(true);
			result.addMessage(new Message(FacesMessage.SEVERITY_INFO, "Record successfully saved"));
		} catch (PersistenceException e) {
			result.setIsTxSuccessful(false);
			result.addMessage(new Message(FacesMessage.SEVERITY_ERROR, e.getMessage()));
		}
		return result;
	}

	public Result<T> update(T branch) {

		Result<T> result = new Result<T>();
		try {
			em.merge(branch);
			em.flush();
			result.setIsTxSuccessful(true);
			result.addMessage(new Message(FacesMessage.SEVERITY_INFO, "Record successfully updated"));
		} catch (PersistenceException e) {
			result.setIsTxSuccessful(false);
			result.addMessage(new Message(FacesMessage.SEVERITY_ERROR, e.getMessage()));
		}
		return result;
	}

	public Result<T> delete (Class<T> klas, String primaryKey) {
		Result<T> result = new Result<T>();
		try {
			Optional<T> entity = Optional.ofNullable(em.find(klas, primaryKey));
			if(entity.isPresent()) {
				em.remove(entity.get());
				result.setIsTxSuccessful(true);
				result.addMessage(new Message(FacesMessage.SEVERITY_INFO, "Record successfully deleted"));
			} else {
				result.setIsTxSuccessful(false);
				result.addMessage(new Message(FacesMessage.SEVERITY_WARN, "Record does not exist"));
			}
		} catch (PersistenceException e) {
			result.setIsTxSuccessful(false);
			result.addMessage(new Message(FacesMessage.SEVERITY_ERROR, e.getMessage()));
		}
		return result;
	}

	public Result<T> saveAll(Collection<T> collection) {
		Result<T> result = new Result<T>();
		try {

			collection.forEach(em::merge);
			result.setIsTxSuccessful(true);
			result.addMessage(new Message(FacesMessage.SEVERITY_INFO, "Record successfully added"));

		} catch (PersistenceException e) {
			result.setIsTxSuccessful(false);
			result.addMessage(new Message(FacesMessage.SEVERITY_ERROR, e.getMessage()));
		}

		return result;
	}
}
