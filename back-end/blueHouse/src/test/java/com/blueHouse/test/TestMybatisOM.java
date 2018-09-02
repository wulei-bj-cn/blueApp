package com.blueHouse.test;

import com.blueHouse.mapper.OM_Mapper;
import com.blueHouse.pojo.orders.Measure;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created by wulei on 23/07/2018.
 */
public class TestMybatisOM {
    @Test
    public void findMeasure() throws Exception{
        String resource = "mybatis/SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        //---------------
        OM_Mapper om_mapper = session.getMapper(OM_Mapper.class);
        List<Measure> measures = om_mapper.findMeasure("usr8", "ord1");
        for (Measure measure: measures) {
            System.out.println(measure.getName());
        }
        //--------------
        session.close();
    }

}
