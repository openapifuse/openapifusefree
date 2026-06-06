package com.openapifuse.generated.openapifusemapping_2.openapifusemapping_2.mapper;

public class Openapifusemapping2Mapper {

    public UpdatetargetApi2 map(UpdatesourceApi source) {
        UpdatetargetApi2 target = new UpdatetargetApi2();

        target.setName(source.getBio());
        target.setId(source.getId());

        return target;
    }
}
