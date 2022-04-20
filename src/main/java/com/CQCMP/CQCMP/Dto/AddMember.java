package com.CQCMP.CQCMP.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddMember {

    @JsonProperty("sName")
    private String sName;

    @JsonProperty("sEmail")
    private String sEmail;

    @JsonProperty("sRoll")
    private String sRoll;

    @JsonProperty("sContact")
    private String sContact;
}
