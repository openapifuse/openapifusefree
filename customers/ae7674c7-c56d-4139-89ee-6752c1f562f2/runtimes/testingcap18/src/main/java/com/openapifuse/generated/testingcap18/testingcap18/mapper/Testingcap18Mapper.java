package com.openapifuse.generated.testingcap18.testingcap18.mapper;

public class Testingcap18Mapper {

    public Targetoperationjsonplaceholder map(Targetoperationjsonplaceholder source) {
        Targetoperationjsonplaceholder target = new Targetoperationjsonplaceholder();

        target.setPath(new Path());

        target.setBody(source.getBody());
        target.setTitle(source.getTitle());
        target.setUserId(source.getUserId());
        target.getPath().setId(source.getPath().getId());

        return target;
    }
}
