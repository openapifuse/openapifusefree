package com.openapifuse.generated.mapping1001_2.mapping1001_2.mapper;

public class Mapping10012Mapper {

    public UpdatetargetApi2 map(UpdatesourceApi source) {
        UpdatetargetApi2 target = new UpdatetargetApi2();

        target.setAge(safeParseInt(source.getAgeText()));
        target.setName(source.getBio());
        target.setId(source.getId());

        return target;
    }

    private Integer safeParseInt(String value) {
        if (value == null || value.isBlank()) return null;
        try { return Integer.parseInt(value.trim()); } catch (NumberFormatException e) { return null; }
    }
}
