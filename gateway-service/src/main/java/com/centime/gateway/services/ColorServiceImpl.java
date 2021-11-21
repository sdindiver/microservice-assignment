package com.centime.gateway.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centime.gateway.entity.ColorData;
import com.centime.gateway.repository.ColorRepository;
import com.centime.gateway.response.ColorDataResponse;
import com.centime.gateway.util.AppLogger;
import com.centime.gateway.util.LoggerUtil;

@Service
public class ColorServiceImpl implements ColorService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ColorServiceImpl.class);


	@Autowired
	private ColorRepository repository;

	@Override
	public ColorDataResponse getColorData(Integer id) {
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "started");
		ColorData colorData = repository.findById(id).get();
		ColorDataResponse dto = new ColorDataResponse();
		dto.setColor(colorData.getColor());
		dto.setName(colorData.getName());
		intializeObject(colorData, dto,null);
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "ended");
		return dto;
	}

	public List<ColorDataResponse> getColorDatas() {
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "started");
		List<ColorData> list = repository.findAll();
		List<ColorDataResponse> dtoList = new ArrayList<>();
		Set<String> warriorList = new HashSet<>();
		for (ColorData data : list) {
			if(!warriorList.contains(data.getName())){
				ColorDataResponse dto = new ColorDataResponse();
				dto.setColor(data.getColor());
				dto.setName(data.getName());
				warriorList.add(data.getName());
				intializeObject(data, dto,warriorList);
				dtoList.add(dto);
			}
		}
		AppLogger.info(LOG, getClass(), LoggerUtil.getMethodName(), "ended");
		return dtoList;
	}

	private void intializeObject(ColorData data, ColorDataResponse dto, Set<String> warriorList) {
		List<ColorData> list = repository.findByParentId(data.getId());
		if (list != null) {
			for (ColorData colorData : list) {
				if (colorData != null && (warriorList== null || !warriorList.contains(colorData.getName()))) {
					ColorDataResponse dto1 = new ColorDataResponse();
					dto1.setColor(colorData.getColor());
					dto1.setName(colorData.getName());
					if(warriorList!= null)
						warriorList.add(colorData.getName());
					dto.addChildColorData(dto1);
					intializeObject(colorData, dto1,warriorList);

				}
			}
		}

	}
}