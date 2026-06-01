package com.openapifuse.generated.usercustomermapping.usercustomermapping.controller;

import com.openapifuse.generated.usercustomermapping.usercustomermapping.mapper.UsercustomermappingMapper;
import com.openapifuse.generated.usercustomermapping.usercustomermapping.mapper.UpdatesourceApi;
import com.openapifuse.generated.usercustomermapping.usercustomermapping.mapper.UpdatetargetApi2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsercustomermappingController {

    private final UsercustomermappingMapper mapper = new UsercustomermappingMapper();

    @PostMapping("/usercustomermapping/transform")
    public ResponseEntity<UpdatetargetApi2> transform(@RequestBody UpdatesourceApi body) {
        UpdatetargetApi2 result = mapper.map(body);
        return ResponseEntity.ok(result);
    }
}
