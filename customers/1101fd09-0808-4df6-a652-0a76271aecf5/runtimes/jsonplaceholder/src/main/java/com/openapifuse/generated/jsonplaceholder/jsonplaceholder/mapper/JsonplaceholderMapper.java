package com.openapifuse.generated.jsonplaceholder.jsonplaceholder.mapper;

public class JsonplaceholderMapper {

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
