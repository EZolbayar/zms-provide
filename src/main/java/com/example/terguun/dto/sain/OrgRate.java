package com.example.terguun.dto.sain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Байгууллагын рейтинг (протокол 3). */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrgRate {

    /** Үнэлгээ хийсэн байгууллага, Annex 2. */
    @JsonProperty("o_orgrate_agency")
    private String agency;

    @JsonProperty("o_orgrate_rating")
    private String rating;

    /** Үнэлгээний дуусах хугацаа. Үнэлгээ хийсэн үед л бөглөнө (протокол 3.3). */
    @JsonProperty("o_orgrate_rating_expdate")
    private String ratingExpDate;
}
