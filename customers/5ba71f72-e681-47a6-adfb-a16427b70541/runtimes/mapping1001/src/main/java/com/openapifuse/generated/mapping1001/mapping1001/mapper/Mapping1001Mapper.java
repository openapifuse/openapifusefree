package com.openapifuse.generated.mapping1001.mapping1001.mapper;

public class Mapping1001Mapper {

    public UpdatetargetApi2 map(UpdatesourceApi source) {
        UpdatetargetApi2 target = new UpdatetargetApi2();

        target.setName(source.getBio());
        target.setId(source.getId());

        return target;
    }
}
