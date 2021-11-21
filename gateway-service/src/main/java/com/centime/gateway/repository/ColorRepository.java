package com.centime.gateway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centime.gateway.entity.ColorData;

@Repository
public interface ColorRepository  extends JpaRepository<ColorData,Integer>{

	public List<ColorData> findByParentId(Integer parent_id);
}
