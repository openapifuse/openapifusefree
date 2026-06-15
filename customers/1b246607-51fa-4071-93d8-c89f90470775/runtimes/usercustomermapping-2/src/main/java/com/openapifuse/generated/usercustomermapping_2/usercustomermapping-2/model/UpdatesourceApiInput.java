package com.openapifuse.generated.usercustomermapping_2.usercustomermapping_2.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdatesourceApiInput {

    @NotNull
    @NotBlank
    private String ageText;

    @NotNull
    @NotBlank
    private String bio;

    @NotNull
    @NotBlank
    private String id;


    public UpdatesourceApiInput() {}

    public String getAgeText() {
        return ageText;
    }

    public void setAgeText(String ageText) {
        this.ageText = ageText;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
