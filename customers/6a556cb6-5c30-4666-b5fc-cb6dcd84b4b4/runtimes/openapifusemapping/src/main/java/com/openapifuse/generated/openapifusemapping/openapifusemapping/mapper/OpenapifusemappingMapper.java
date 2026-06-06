package com.openapifuse.generated.openapifusemapping.openapifusemapping.mapper;

public class OpenapifusemappingMapper {

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
