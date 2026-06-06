package com.openapifuse.generated.openapifusemapping.openapifusemapping.controller;

import com.openapifuse.generated.openapifusemapping.openapifusemapping.mapper.OpenapifusemappingMapper;
import com.openapifuse.generated.openapifusemapping.openapifusemapping.mapper.UpdatesourceApi;
import com.openapifuse.generated.openapifusemapping.openapifusemapping.mapper.UpdatetargetApi2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenapifusemappingController {

    private final OpenapifusemappingMapper mapper = new OpenapifusemappingMapper();

    @PostMapping("/openapifusemapping/transform")
    public ResponseEntity<UpdatetargetApi2> transform(@RequestBody UpdatesourceApi body) {
        UpdatetargetApi2 result = mapper.map(body);
        return ResponseEntity.ok(result);
    }
}
