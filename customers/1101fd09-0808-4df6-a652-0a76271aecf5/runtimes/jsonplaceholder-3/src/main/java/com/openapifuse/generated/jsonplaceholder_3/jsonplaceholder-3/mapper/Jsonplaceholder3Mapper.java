package com.openapifuse.generated.jsonplaceholder_3.jsonplaceholder_3.mapper;

public class Jsonplaceholder3Mapper {

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
