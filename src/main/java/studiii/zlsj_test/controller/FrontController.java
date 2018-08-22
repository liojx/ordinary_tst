package studiii.zlsj_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import studiii.zlsj_test.pojo.DBPO;

import java.util.ArrayList;

/**
 * Author: liaosijun
 * Description:
 * Date: Created in 2018/8/21 19:19
 * Modified By:
 */
@RestController
@RequestMapping(value = "/T31")
public class FrontController {

    @Autowired
    private DBPO dbpo;

    @RequestMapping("/req")
    public void getSomeStr(@RequestBody String name, @RequestBody String nickName, @RequestBody ArrayList<Object> items){
        System.out.println(" 前台传：" + name);
    }

    @ResponseBody
    @PostMapping("getDbProperty")
    private DBPO getProperty(){
        System.out.println("db_pwd==>"+dbpo.getPwd());
        return dbpo;
    }
}
