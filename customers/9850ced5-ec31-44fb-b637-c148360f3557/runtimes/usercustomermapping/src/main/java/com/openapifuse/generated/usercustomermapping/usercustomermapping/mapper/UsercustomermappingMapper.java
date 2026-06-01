package com.openapifuse.generated.usercustomermapping.usercustomermapping.mapper;

public class UsercustomermappingMapper {

    public UpdatetargetApi2 map(UpdatesourceApi source) {
        UpdatetargetApi2 target = new UpdatetargetApi2();

        target.setName(source.getBio());
        target.setId(source.getId());

        return target;
    }
}
