package com.sanqing.course.dao;

import java.util.List;

import com.sanqing.course.model.Team;
import com.sanqing.course.util.PageModel;


public interface TeamDAO {

	/**
	 * 添加班级
	 * @param
	 */
	public void addTeam(Team team) throws Exception;

	/**
	 * 修改班级
	 * @param
	 */
	public void modifyTeam(Team team) throws Exception;

	/**
	 * 删除班级
	 * @param
	 */
	public void deleteTeam(String[] teamIdList) throws Exception;


	/**
	 * 根据Id查询班级
	 * @param
	 */
	public Team findTeamById(String id) throws Exception;

	/**
	 * 查询全部班级
	 */
	public List<Team> findAllTeams() throws Exception;

	/**
	 * 根据条件查询班级
	 */
	public PageModel listTeam(int pageNo, Team team) throws Exception;

}
