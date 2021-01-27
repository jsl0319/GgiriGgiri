package com.ggiriggiri.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggiriggiri.web.dao.ProjectDao;
import com.ggiriggiri.web.dao.ProjectLanguageDao;
import com.ggiriggiri.web.dao.ProjectSkillDao;
import com.ggiriggiri.web.entity.Project;
import com.ggiriggiri.web.entity.ProjectView;

@Service
public class ProjectServiceImp implements ProjectService{

	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private ProjectLanguageDao projectLanguageDao;
	
	@Autowired
	private ProjectSkillDao projectSkilldao;
	
	@Override
	public int insert(Project project) {
		// TODO Auto-generated method stub
		return projectDao.insert(project);
	}

	@Override
	public int update(Project project) {
		// TODO Auto-generated method stub
		return projectDao.update(project);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return projectDao.delete(id);
	}

	@Override
	public Project get(int id) {
		Project p = projectDao.get(id);
		p.setLanguages(projectLanguageDao.getListByProjectId(p.getId()));
		p.setSkills(projectSkilldao.getListByProjectId(p.getId()));
		
		return p;
	}

	@Override
	public List<Project> getList(int page, int size, String field, String query) {
		int offset = (page-1)*10;
		
		List<Project> list = projectDao.getList(offset, size, field, query);
		
		for(Project p : list) {
			p.setLanguages(projectLanguageDao.getListByProjectId(p.getId()));
			p.setSkills(projectSkilldao.getListByProjectId(p.getId()));
		}
			
		return list;
	}

//	@Override
//	public List<Project> getViewList(int page, int size, String field, String query) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public int getCount(String title, String query, String[] field, String[] skill, String[] language) {
		int[] fdProjectIds = projectDao.getByProjectIds(field);
		if(fdProjectIds.length==0)
			return 0;
		int[] skProjectIds = projectSkilldao.getByProjectIds(fdProjectIds,skill);
		if(skProjectIds.length==0)
			return 0;
		int[] ids = projectLanguageDao.getByProjectIds(skProjectIds, language);
		if(ids.length==0)
			return 0;
		
		return projectDao.getCount(ids,title,query);
	}

	@Override
	public List<ProjectView> getViewList(int page, int size, String title, String query, String[] field, String[] skill,
			String[] language) {
		
		int[] fdProjectIds = projectDao.getByProjectIds(field);
		if(fdProjectIds.length==0)
			return null;
		
		int[] skProjectIds = projectSkilldao.getByProjectIds(fdProjectIds,skill);
		if(skProjectIds.length==0)
			return null;
		
		int[] ids = projectLanguageDao.getByProjectIds(skProjectIds,language);
		if(ids.length==0)
			return null;
		
		
		int offset = (page-1) * size;
		List<ProjectView> list = projectDao.getViewList(ids, offset, size, title, query);
		
		for(ProjectView p : list) {
			p.setSkills(projectSkilldao.getListByProjectId(p.getId()));
			p.setLanguages(projectLanguageDao.getListByProjectId(p.getId()));
		}
			
		return list;
	}

	@Override
	public ProjectView getView(int id) {
		
		ProjectView pv = projectDao.getView(id);
		
		
		pv.setLanguages(projectLanguageDao.getListByProjectId(pv.getId()));
		pv.setSkills(projectSkilldao.getListByProjectId(pv.getId()));
		
		
		return pv;
	}

	@Override
	public ProjectView getPrev(int id) {
		
		return projectDao.getPrev(id);
	}

	@Override
	public ProjectView getNext(int id) {
		return projectDao.getNext(id);
	}
}
