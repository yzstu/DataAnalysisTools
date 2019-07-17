import com.baldwin.bean.RstBeanTest;
import com.baldwin.mapper.BeanTest1Mapper;
import com.common.bean.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})

public class test {
    @Autowired
    BeanTest1Mapper beanTest1Mapper;
    private void test(){
        Criteria criteria = new Criteria();
        criteria.put("bDate" , "2019-06-01");
        criteria.put("eDate" , "2019-06-31");
        ArrayList<RstBeanTest> rstList = beanTest1Mapper.getInfoList(criteria);
        int i = 0;
        for (RstBeanTest rstBeanTest : rstList){
            if (i==10){
                break;
            }
            System.out.println(rstBeanTest.getToken());
        }
    }
}
