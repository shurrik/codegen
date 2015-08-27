package com.kaoshidian.cms.manager.memberzone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kaoshidian.common.dao.base.EntityView;
import com.kaoshidian.common.dao.memberzone.ExchangeDAO;
import com.kaoshidian.common.dao.page.PageContext;
import com.kaoshidian.common.entity.memberzone.Exchange;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class ${classObject.className!}Mng {

	@Autowired
	private ${classObject.className!}DAO ${classObject.className?uncap_first!}DAO;
	
	public PageContext queryPage(Integer pageNum,Integer pageSize) throws Exception
	{
		EntityView ev = new EntityView();
		return ${classObject.className?uncap_first!}DAO.queryUsePage(ev, pageNum, pageSize);
	}
	
	public ${classObject.className!} getById(Integer id)
	{
		return ${classObject.className?uncap_first!}DAO.findById(id);
	}
	
	public ${classObject.className!} save(${classObject.className!} ${classObject.className?uncap_first!})
	{
		${classObject.className?uncap_first!}DAO.saveOrUpdate(${classObject.className?uncap_first!});
		return ${classObject.className?uncap_first!};
	}
	
	public void delete(${classObject.className!} ${classObject.className?uncap_first!})
	{
		${classObject.className?uncap_first!}DAO.delete(${classObject.className?uncap_first!});
	}
}
