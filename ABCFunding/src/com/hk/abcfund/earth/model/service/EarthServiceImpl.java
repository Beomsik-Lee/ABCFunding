package com.hk.abcfund.earth.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.abcfund.earth.model.dao.EarthDao;
import com.hk.abcfund.earth.model.dto.ApplyBackUp;
import com.hk.abcfund.earth.model.dto.ECodes;
import com.hk.abcfund.earth.model.dto.EarthDto;
import com.hk.abcfund.earth.model.dto.EarthFile;
import com.hk.abcfund.earth.model.dto.EarthPwDto;
import com.hk.abcfund.earth.model.dto.SponsorDto;
import com.hk.abcfund.earth.util.EyeEarthParam;

/**
 * 후원 서비스 클래스
 * 
 * @author cs
 *
 */
@Service
public class EarthServiceImpl implements EarthService {
	/** 후원의 DAO */
	@Autowired
	private EarthDao dao;

	/**
	 * 후원 등록 메서드
	 * 
	 * @param dto
	 *            후원 객체
	 */
	@Override
	public void addEarth(EarthDto dto) {
		dao.addEarth(dto);
	}

	@Override
	public int getSponsorTotalCount() {
		return dao.getSponsorTotalCount();
	}

	@Override
	public List<EarthDto> getSponsorLists(EyeEarthParam pt) {
		return dao.getSponsorLists(pt);
	}

	@Override
	public EarthDto getEarth(int earthno) {
		return dao.getEarth(earthno);
	}

	@Override
	public List<HashMap> findEarthMap(String name) {
		return dao.findEarthMap(name);
	}
	
	@Override
	public EarthPwDto getEarthPw(int earthno) {
		return dao.getEarthPw(earthno);
	}
	
	@Override
	public int myearthpwconfirm(int pw,int earthno) {
		return (int)dao.myearthpwconfirm(pw,earthno);
	}
	

	@Override
	public ECodes getEcode(int earthno) {
		return dao.getEcode(earthno);
	}

	@Override
	public void updateEcode(EarthDto dto) {
		dao.updateEcode(dto);
	}

	@Override
	public void applybackup(EarthDto dto) {
		dao.applybackup(dto);
	}

	@Override
	public ApplyBackUp getApplyBackup(int earthno) {
		return dao.getApplyBackup(earthno);
	}

	@Override
	public void Updatebackup(EarthDto dto) {
		dao.Updatebackup(dto);
	}

	@Override
	public void deletebackup(int earthno) {
		dao.deletebackup(earthno);
	}

	@Override
	public void addEarthFile(EarthFile file) {
			dao.addEarthFile(file);
	}

	@Override
	public EarthFile getEarthFile(int earthno) {
		return dao.getEarthFile(earthno);
	}

	@Override
	public int getMaxEarth() {
		return dao.getMaxEarth();
	}

	@Override
	public List<SponsorDto> findSponsor(ECodes code) {
		return dao.findSponsor(code);
	}


	@Override
	public void updateMyearth(EarthDto dto) {
		dao.updateMyearth(dto);
	}

	@Override
	public void myearthDelete(int earthno) {
		dao.myearthDelete(earthno);
	}

	@Override
	public void myearthDeleteFile(int earthno) {
		dao.myearthDeleteFile(earthno);
	}
	

	@Override
	public List<EarthDto> getSponsorApplyLists(EyeEarthParam pt) {
		return dao.getSponsorApplyLists(pt);
	}

	@Override
	public int getSponsorApplyTotalCount() {
		return dao.getSponsorApplyTotalCount();
	}
///////----------------------------------------------------------------------------//

	
}
