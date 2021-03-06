package com.ggiriggiri.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ggiriggiri.web.dao.MemberDao;
import com.ggiriggiri.web.entity.Member;

@Repository
public class MybatisMemberDao implements MemberDao{

	private MemberDao mapper;
	
	@Autowired
	public MybatisMemberDao(SqlSession session) {
		mapper = session.getMapper(MemberDao.class);
	}
	
	@Override
	public int insert(Member member) {
		// TODO Auto-generated method stub
		return mapper.insert(member);
	}

	@Override
	public int update(Member member) {
		// TODO Auto-generated method stub
		return mapper.update(member);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return mapper.delete(id);
	}

	@Override
	public List<Member> getList() {
		// TODO Auto-generated method stub
		return mapper.getList(1, 10, "nickname", "");
	}

	@Override
	public List<Member> getList(int offset, int size, String field, String query) {
		return mapper.getList(offset, size, field, query);
	}

	@Override
	public Member get(int id) {
		return mapper.get(id);
	}

	@Override
	public int check(String field, String query) {
		return mapper.check(field, query);
	}

	@Override
	public Member login(String email, String password) {
		// TODO Auto-generated method stub
		return mapper.login(email, password);
	}

	@Override
	public Member getByEmail(String email) {
		// TODO Auto-generated method stub
		return mapper.getByEmail(email);
	}

	@Override
	public int getLast() {
		// TODO Auto-generated method stub
		return mapper.getLast();
	}

	@Override
	public int getCount(String field,String query) {
		// TODO Auto-generated method stub
		return mapper.getCount(field,query);
	}

}
