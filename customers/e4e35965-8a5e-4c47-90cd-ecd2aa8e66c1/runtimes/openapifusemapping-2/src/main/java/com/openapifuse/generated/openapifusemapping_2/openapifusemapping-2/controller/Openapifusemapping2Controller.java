package com.openapifuse.generated.openapifusemapping_2.openapifusemapping_2.controller;

import com.openapifuse.generated.openapifusemapping_2.openapifusemapping_2.mapper.Openapifusemapping2Mapper;
import com.openapifuse.generated.openapifusemapping_2.openapifusemapping_2.mapper.UpdatesourceApi;
import com.openapifuse.generated.openapifusemapping_2.openapifusemapping_2.mapper.UpdatetargetApi2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Openapifusemapping2Controller {

    private final Openapifusemapping2Mapper mapper = new Openapifusemapping2Mapper();

    @PostMapping("/openapifusemapping-2/transform")
    public ResponseEntity<UpdatetargetApi2> transform(@RequestBody UpdatesourceApi body) {
        UpdatetargetApi2 result = mapper.map(body);
        return ResponseEntity.ok(result);
    }
}
