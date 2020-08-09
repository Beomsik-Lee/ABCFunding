package com.hk.abcfund.earth.model.service;

import java.util.HashMap;
import java.util.List;

import com.hk.abcfund.earth.model.dto.ApplyBackUp;
import com.hk.abcfund.earth.model.dto.ECodes;
import com.hk.abcfund.earth.model.dto.EarthDto;
import com.hk.abcfund.earth.model.dto.EarthFile;
import com.hk.abcfund.earth.model.dto.EarthPwDto;
import com.hk.abcfund.earth.model.dto.SponsorDto;
import com.hk.abcfund.earth.util.EyeEarthParam;

/**
 * @author cs
 *
 */
public interface EarthService {
	void addEarth(EarthDto dto);
	int getSponsorTotalCount();
	List<EarthDto> getSponsorLists(EyeEarthParam pt);
	EarthDto getEarth(int earthno);
	EarthPwDto getEarthPw(int earthno);
	List<HashMap> findEarthMap(String name);
	int myearthpwconfirm(int pw, int earthno);
	ECodes getEcode(int earthno);
	void updateEcode(EarthDto dto);
	void applybackup(EarthDto dto);
	ApplyBackUp getApplyBackup(int earthno);
	void Updatebackup(EarthDto dto);
	void deletebackup(int earthno);	
	void addEarthFile(EarthFile file);
	EarthFile getEarthFile(int earthno);
	int getMaxEarth();	
	void updateMyearth(EarthDto dto);
	void myearthDelete(int earthno);
	void myearthDeleteFile(int earthno);
	List<EarthDto> getSponsorApplyLists(EyeEarthParam pt);
	int getSponsorApplyTotalCount();

	List<SponsorDto> findSponsor(ECodes code);
	
	
///////----------------------------------------------------------------------------//

}