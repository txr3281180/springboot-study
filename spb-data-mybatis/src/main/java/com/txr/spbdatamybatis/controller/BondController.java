package com.txr.spbdatamybatis.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.txr.spbdatamybatis.controller.condition.BondQueryCondition;
import com.txr.spbdatamybatis.repository.entity.BondInfo;
import com.txr.spbdatamybatis.repository.entity.IssuerInfo;
import com.txr.spbdatamybatis.service.BondInfoService;
import com.txr.spbdatamybatis.service.IssuerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by xinrui.tian on 2019/2/15
 */
@Controller
@RequestMapping(value = "/bond", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BondController {

    @Autowired
    private BondInfoService bondInfoService;

    @Autowired
    private IssuerInfoService issuerInfoService;

    @GetMapping(value = "/info")
    public String getBondsByPage(@RequestParam(value = "page") int page,
                             Map<String, Object> map) {
        Page<Object> bondsPage = bondInfoService.getBondsByPage(page);
        map.put("bonds", bondsPage.getResult());
        map.put("Pages", bondsPage.getPages());
        map.put("Total", bondsPage.getTotal());
        map.put("PageNum", bondsPage.getPageNum());
        map.put("PageSize", bondsPage.getPageSize());

        //PageInfo<Object> pageInfo = bondsPage.toPageInfo(); //默认分页导航显示8页
        PageInfo<Object> pageInfo = new PageInfo<>(bondsPage, 5); //分页导航显示5页
        map.put("FirstPage", pageInfo.getNavigateFirstPage()); //导航起始页
        map.put("CurrentPage", pageInfo.getNavigatePages());    //当前导航页
        map.put("LastPage", pageInfo.getNavigateLastPage());   //导航结束页
        return "bond_list";
    }

    @GetMapping(value = "/detail")
    public String getBondByBondKey(@RequestParam(value = "bondKey") String bondKey,
                                   Model model) {
        BondInfo bond = bondInfoService.getBondByBondKey(bondKey);
        String issuerCode = bond.getIssuerCode();
        IssuerInfo issuer = issuerInfoService.getIssuerByIssuerCode(issuerCode);

        model.addAttribute("bond", bond);
        model.addAttribute("issuer", issuer);

        List<String> bondTypes = bondInfoService.getBondType();
        model.addAttribute("bondTypes", bondTypes);

        return "bond_form";
    }

    @PostMapping(value = "/query")
    public List<BondInfo> queryBond(@RequestBody BondQueryCondition bondQueryCondition) {
        return bondInfoService.queryBond(bondQueryCondition);
    }


    //redirect: 重定向一个地址   '/'代表当前项目路径
    //forward: 转发到一个地址

}
