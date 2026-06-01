package com.openapifuse.generated.mapping1001.mapping1001.controller;

import com.openapifuse.generated.mapping1001.mapping1001.mapper.Mapping1001Mapper;
import com.openapifuse.generated.mapping1001.mapping1001.mapper.UpdatesourceApi;
import com.openapifuse.generated.mapping1001.mapping1001.mapper.UpdatetargetApi2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mapping1001Controller {

    private final Mapping1001Mapper mapper = new Mapping1001Mapper();

    @PostMapping("/mapping1001/transform")
    public ResponseEntity<UpdatetargetApi2> transform(@RequestBody UpdatesourceApi body) {
        UpdatetargetApi2 result = mapper.map(body);
        return ResponseEntity.ok(result);
    }
}
