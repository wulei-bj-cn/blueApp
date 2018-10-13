package com.blueHouse.service;

import com.blueHouse.utils.Encypt;
import org.springframework.stereotype.Service;

/**
 * Created by wulei on 27/07/2018.
 */
@Service
public class MD5ServiceImpl implements MD5Service {

    public String encodeByMD5(String originString) { return (new Encypt()).encodeByMD5(originString); }
}
