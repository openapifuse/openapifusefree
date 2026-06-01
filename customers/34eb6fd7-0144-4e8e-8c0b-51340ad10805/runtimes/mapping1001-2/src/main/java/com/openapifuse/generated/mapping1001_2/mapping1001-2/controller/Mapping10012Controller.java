package com.openapifuse.generated.mapping1001_2.mapping1001_2.controller;

import com.openapifuse.generated.mapping1001_2.mapping1001_2.mapper.Mapping10012Mapper;
import com.openapifuse.generated.mapping1001_2.mapping1001_2.mapper.UpdatesourceApi;
import com.openapifuse.generated.mapping1001_2.mapping1001_2.mapper.UpdatetargetApi2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mapping10012Controller {

    private final Mapping10012Mapper mapper = new Mapping10012Mapper();

    @PostMapping("/mapping1001-2/transform")
    public ResponseEntity<UpdatetargetApi2> transform(@RequestBody UpdatesourceApi body) {
        UpdatetargetApi2 result = mapper.map(body);
        return ResponseEntity.ok(result);
    }
}
