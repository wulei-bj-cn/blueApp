package com.blueHouse.service;

import com.blueHouse.mapper.Browse_Mapper;
import com.blueHouse.pojo.browse.T_Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
@Service
public class BrowseServiceImpl implements BrowseService {

    @Resource
    @Autowired
    private Browse_Mapper browse_mapper;

    public List<T_Material> browseAllMaterials() {
        return browse_mapper.browseAllMaterials();
    }

}
