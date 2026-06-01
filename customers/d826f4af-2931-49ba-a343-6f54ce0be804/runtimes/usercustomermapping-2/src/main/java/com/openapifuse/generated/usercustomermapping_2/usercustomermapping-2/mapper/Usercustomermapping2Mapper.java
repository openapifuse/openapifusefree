package com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.mapper;

public class Usercustomermapping2Mapper {

    public UpdatetargetApi2 map(UpdatesourceApi source) {
        UpdatetargetApi2 target = new UpdatetargetApi2();

        target.setName(source.getBio());
        target.setId(source.getId());

        return target;
    }
}
