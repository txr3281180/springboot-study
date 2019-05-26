package com.txr.spbbasic.controller;

import com.txr.spbbasic.AuthorMapping;
import com.txr.spbbasic.controller.model.Bond;
import com.txr.spbbasic.controller.response.ResponseData;
import com.txr.spbbasic.controller.response.ResponseUtil;
import com.txr.spbbasic.global.exception.BondNotFoundException;
import com.txr.spbbasic.service.BondCacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "MainAPI")
@RestController
@RequestMapping(value = "/txr/study/main", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private BondCacheService bondCacheService;



    /**
     * @RestController = @ResponseBody ＋ @Controller
     * 1)如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，
     * 配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
     * 例如：本来应该到success.jsp页面的，则其显示success.
     * 2)如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
     * 3)如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
     *
     * @ResponseBody 作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，
     * 写入到response对象的body区，通常用来返回JSON数据或者是XML数据，需要注意的是，
     * 在使用此注解之后不会再走视图处理器，而是直接将数据写入到输入流中，
     * 它的效果等同于通过response对象输出指定格式的数据。
     *
     * @GetMapping、@PostMapping、@PutMapping、@DeleteMapping、@PatchMapping
     * 简化常用的HTTP方法的映射，并更好地表达被注解方法的语义
     * > @GetMapping = @RequestMapping(method = RequestMethod.GET)的缩写
     *
     * @RequestMapping(value = "/swagger/message", produces = {MediaType.APPLICATION_JSON_VALUE})
     * produces ： @RequestMapping 注解类的时候使用， 且方法返回值必须是json格式
     *
     * @PathVariable 路径可变参数，映射路径中的参数
     * @RequestParam 请求参数
     */

    @Autowired
    protected AuthorMapping author;

    //@ResponseBody
    @GetMapping("/author")
    public ResponseData<AuthorMapping> authorInfo() {
        return ResponseUtil.ok(author);
    }

    @ApiOperation(value = "债券编号列表", notes = "债券编号列表")
    @GetMapping("/bondKeys")
    public ResponseData<List<String>> getAllBondKeys(@ApiParam(value = "userId", required = true)
                                                     @RequestHeader String userId) {   //请求头
        logger.info("Load all bond keys");
        List<String> allBondKeys = bondCacheService.getAllBondKeys();
        return ResponseUtil.ok(allBondKeys);
    }

    @ApiOperation(value = "债券详细信息", notes = "债券详细信息")
    @PostMapping(value = "/{userName}/bondInfo")
    public ResponseData<List<Bond>> getUserInfoByUserId(@ApiParam(value = "userName", required = true)
                                                        @PathVariable String userName,
                                                        @ApiParam(value = "condtion", required = true)
                                                        @RequestBody Bond bond) throws BondNotFoundException {
        String bondKey = bond.getBondKey();
        System.out.println(userName);
        logger.info("Find bond by bondKeys:" + bondKey);
        List<Bond> bonds = bondCacheService.findBondByBondKey(bondKey);
        return ResponseUtil.ok(bonds);
    }


}
