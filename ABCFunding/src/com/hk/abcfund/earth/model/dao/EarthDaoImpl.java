package com.hk.abcfund.earth.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.abcfund.earth.model.dto.ApplyBackUp;
import com.hk.abcfund.earth.model.dto.ECodes;
import com.hk.abcfund.earth.model.dto.EarthDto;
import com.hk.abcfund.earth.model.dto.EarthFile;
import com.hk.abcfund.earth.model.dto.EarthPwDto;
import com.hk.abcfund.earth.model.dto.SponsorDto;
import com.hk.abcfund.earth.util.EyeEarthParam;

/**
 * @author cs
 */
@Repository
public class EarthDaoImpl implements EarthDao {
	private Map<String, Integer> map;
	
	@Autowired
	private SqlSession sqlSession;

	/** MyBatis 후원 네임스페이스 */
	private String nameSpace = "Earth.";

	@Override
	public void addEarth(EarthDto dto) {
		sqlSession.insert(nameSpace + "addEarth", dto);

	}

	@Override
	public int getSponsorTotalCount() {
		return (int) sqlSession.selectOne(nameSpace + "getSponsorTotalCount");
	}

	@Override
	public List<EarthDto> getSponsorLists(EyeEarthParam pt) {
		return sqlSession.selectList(nameSpace + "getSponsorLists", pt);
	}

	@Override
	public EarthDto getEarth(int earthno) {
		System.out.println((EarthDto) sqlSession.selectOne(nameSpace + "getEarth", earthno));
		return (EarthDto) sqlSession.selectOne(nameSpace + "getEarth", earthno);
	}

	@Override
	public List<HashMap> findEarthMap(String name) {
		return sqlSession.selectList(nameSpace + "findEarthMap", name);
	}
	
	@Override
	public EarthPwDto getEarthPw(int earthno) {
		return (EarthPwDto) sqlSession.selectOne(nameSpace + "getEarthPw", earthno);
	}
	
	@Override
	public int myearthpwconfirm(int pw, int earthno) {
		map = new HashMap<String, Integer>();
		map.put("pw", pw);
		map.put("earthno", earthno);
		//System.out.println("들어갔어?"+map.get("earthno")+map.get("pw"));
		return (int)sqlSession.selectOne(nameSpace + "myearthpwconfirm", map);
	}

	@Override
	public ECodes getEcode(int earthno) {
		return (ECodes) sqlSession.selectOne(nameSpace + "getEcode", earthno);
	}

	@Override
	public void updateEcode(EarthDto dto) {
		sqlSession.update(nameSpace + "updateEcode", dto);
	}

	@Override
	public void applybackup(EarthDto dto) {
		sqlSession.insert(nameSpace + "applybackup", dto);
	}

	@Override
	public ApplyBackUp getApplyBackup(int earthno) {
		return (ApplyBackUp) sqlSession.selectOne(nameSpace + "getApplyBackup", earthno);
	}

	@Override
	public void Updatebackup(EarthDto dto) {
		sqlSession.update(nameSpace + "Updatebackup", dto);
	}

	@Override
	public void deletebackup(int earthno) {
		sqlSession.delete(nameSpace + "deletebackup", earthno);
	}

	@Override
	public void addEarthFile(EarthFile file) {
		sqlSession.insert(nameSpace + "addEarthFile", file);
	}

	@Override
	public EarthFile getEarthFile(int earthno) {
		return (EarthFile) sqlSession.selectOne(nameSpace + "getEarthFile", earthno);
	}

	@Override
	public int getMaxEarth() {
		return (int) sqlSession.selectOne(nameSpace + "getMaxEarth");
	}

	@Override
	public List<SponsorDto> findSponsor(ECodes code) {
		return sqlSession.selectList(nameSpace + "findSponsor", code);
	}

	@Override
	public void updateMyearth(EarthDto dto) {
		sqlSession.update(nameSpace + "updateMyearth", dto);
	}

	@Override
	public void myearthDelete(int earthno) {
		sqlSession.delete(nameSpace + "myearthDelete", earthno);
	}

	@Override
	public void myearthDeleteFile(int earthno) {
		sqlSession.delete(nameSpace + "myearthDeleteFile", earthno);
	}

	@Override
	public List<EarthDto> getSponsorApplyLists(EyeEarthParam pt) {
		return sqlSession.selectList(nameSpace + "getSponsorApplyLists", pt);
	}

	@Override
	public int getSponsorApplyTotalCount() {
		return (int) sqlSession.selectOne(nameSpace + "getSponsorApplyTotalCount");
	}
	
///////----------------------------------------------------------------------------//

}