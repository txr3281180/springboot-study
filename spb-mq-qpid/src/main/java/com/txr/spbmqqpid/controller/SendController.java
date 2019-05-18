package com.txr.spbmqqpid.controller;

import com.txr.spbmqqpid.service.QpidWarapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xinrui.tian on 2019/5/18.
 */

@RestController
public class SendController {

    @Autowired
    private QpidWarapper qpidWarapper;

    @PostMapping("/qpid/{id}")
    public void sendMsg(@PathVariable String id,
                        @RequestBody Map<String, String> body) {
        qpidWarapper.send(id, body);
    }
}
