package com.txr.spbdatamybatis.repository.mapper;

import com.txr.spbdatamybatis.controller.condition.BondQueryCondition;
import com.txr.spbdatamybatis.repository.entity.BondInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xinrui.tian on 2019/2/15.
 *
 *  注解 示例
 */
@Repository
public interface BondInfoMapper {

    @Results(id="bondMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "bond_key", property = "bondKey"),
            @Result(column = "short_name", property = "shortName"),
            @Result(column = "full_name", property = "fullName"),
            @Result(column = "issuer_code", property = "issuerCode"),
            @Result(column = "bond_type", property = "bondType"),
            @Result(column = "bond_subtype", property = "bondSubtype"),
            @Result(column = "maturity_term", property = "maturityTerm"),
            @Result(column = "term_unit", property = "termUnit"),
            @Result(column = "maturity_date", property = "maturityDate"),
            @Result(column = "issue_start_date", property = "issueStartDate"),
            @Result(column = "issue_end_date", property = "issueEndDate"),
            @Result(column = "coupon_rate", property = "couponRate"),
            @Result(column = "modify_date", property = "modifyDate"),
            @Result(column = "create_date", property = "createDate"),
    })
    @Select({"<script>",
            "SELECT * FROM bond_info WHERE 1=1 ",
                "<if test='searchString!=null'>and full_name like #{searchString} or short_name like #{searchString}</if>",
                "<if test='startDate!=null'>and issue_start_date &gt; #{startDate} </if>",
                "<if test='endDate!=null'>and issue_start_date &lt;= #{endDate} </if>",
            "ORDER BY ${orderBy} ${sort} limit 10</script>"
    })
    List<BondInfo> getBondByCondition(BondQueryCondition bondQueryCondition);

    @ResultMap(value = "bondMap")
    @Select("SELECT * FROM bond_info")
    List<BondInfo> getBondsByPage();

    @ResultMap(value = "bondMap")
    @Select("SELECT * FROM bond_info WHERE bond_key = #{bondKey}")
    BondInfo getBondByBondKey(String bondKey);

    @Select("SELECT distinct bond_type FROM bond_info")
    List<String> getBondType();
}
