package com.centime.gateway.services;

import java.util.List;

import com.centime.gateway.response.ColorDataResponse;

public interface ColorService {

	public ColorDataResponse getColorData(Integer id);

	public List<ColorDataResponse> getColorDatas();

}
