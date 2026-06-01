package com.openapifuse.generated.mapping1001_2.mapping1001_2.mapper;

public class Mapping10012Mapper {

    public UpdatetargetApi2 map(UpdatesourceApi source) {
        UpdatetargetApi2 target = new UpdatetargetApi2();

        target.setName(source.getBio());
        target.setId(source.getId());

        return target;
    }
}
