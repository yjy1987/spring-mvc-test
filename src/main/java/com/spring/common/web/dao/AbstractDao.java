package com.spring.common.web.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractDao extends SqlSessionDaoSupport{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*@Autowired SqlSession sqlSession;
	
	public SqlSession getSession() {
		return sqlSession;
	}*/
	
	protected void printQueryId(String queryId) {
		if(logger.isDebugEnabled()) {
			logger.debug("\t QuertyId  \t" + queryId);
		}
	}
	
	public Object insert(String queryId, Object params){
        printQueryId(queryId);
       
        return super.getSqlSession().insert(queryId, params);
    }
     
    public Object update(String queryId, Object params){
        printQueryId(queryId);
        return super.getSqlSession().update(queryId, params);
    }
     
    public Object delete(String queryId, Object params){
        printQueryId(queryId);
        return super.getSqlSession().delete(queryId, params);
    }
     
    public Object selectOne(String queryId){
        printQueryId(queryId);
        return super.getSqlSession().selectOne(queryId);
    }
     
    public Object selectOne(String queryId, Object params){
        printQueryId(queryId);
        return super.getSqlSession().selectOne(queryId, params);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId){
        printQueryId(queryId);
        return super.getSqlSession().selectList(queryId);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId, Object params){
        printQueryId(queryId);
        return super.getSqlSession().selectList(queryId,params);
    }


}
