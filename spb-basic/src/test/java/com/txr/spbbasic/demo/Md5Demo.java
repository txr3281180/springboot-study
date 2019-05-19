package com.txr.spbbasic.demo;


import com.txr.spbbasic.controller.model.Bond;
import com.txr.spbbasic.repository.BondAccess;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.List;

public class Md5Demo {

    /**
       <dependency>
         <groupId>commons-codec</groupId>
         <artifactId>commons-codec</artifactId>
         <version>1.10</version>
       </dependency>
     */

    @Test
    public void testMd5 () {
        List<Bond> allBond = BondAccess.getAllBond();
        Bond bond0 = allBond.get(0);
        Bond bond1 = allBond.get(1);

        System.out.println(bond0);
        System.out.println(bond1);

        String s1 = DigestUtils.md5Hex(bond0.toString());
        String s2 = DigestUtils.md5Hex(bond1.toString());
        System.out.println(s1);
        System.out.println(s1.equals(s2));
    }
}
