package com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.controller;

import com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.mapper.Usercustomermapping2Mapper;
import com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.mapper.UpdatesourceApi;
import com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.mapper.UpdatetargetApi2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Usercustomermapping2Controller {

    private final Usercustomermapping2Mapper mapper = new Usercustomermapping2Mapper();

    @PostMapping("/usercustomermapping-2/transform")
    public ResponseEntity<UpdatetargetApi2> transform(@RequestBody UpdatesourceApi body) {
        UpdatetargetApi2 result = mapper.map(body);
        return ResponseEntity.ok(result);
    }
}
