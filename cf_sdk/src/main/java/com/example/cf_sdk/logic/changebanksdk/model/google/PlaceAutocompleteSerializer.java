package com.example.cf_sdk.logic.changebanksdk.model.google;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * Response from {@link com.example.cf_sdk.logic.changebanksdk.source.GoogleDatasource} to
 * match a search.
 */

public class PlaceAutocompleteSerializer {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public static PlaceAutocompleteSerializer create(List<Prediction> predictions) {
        return new PlaceAutocompleteSerializer(predictions);
    }

    public PlaceAutocompleteSerializer(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public class MatchedSubstring {
        @SerializedName("length")
        @Expose
        private Integer length;

        @SerializedName("offset")
        @Expose
        private Integer offset;

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

    }


    public static class Prediction {
        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("map_id")
        @Expose
        private String id;

        @SerializedName("matched_substrings")
        @Expose
        private List<MatchedSubstring> matchedSubstrings = null;

        @SerializedName("place_id")
        @Expose
        private String placeId;

        @SerializedName("reference")
        @Expose
        private String reference;

        @SerializedName("terms")
        @Expose
        private List<Term> terms = null;

        @SerializedName("types")
        @Expose
        private List<String> types = null;

        @SerializedName("structured_formatting")
        @Expose
        private StructuredFormatting structuredFormatting = null;

        public static Prediction create(String placeId,
                                        String description,
                                        StructuredFormatting structuredFormatting) {
            return new Prediction(placeId, description, structuredFormatting);
        }

        public Prediction (String placeId,
                           String description,
                           StructuredFormatting structuredFormatting) {
            this.placeId = placeId;
            this.description = description;
            this.structuredFormatting = structuredFormatting;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<MatchedSubstring> getMatchedSubstrings() {
            return matchedSubstrings;
        }

        public void setMatchedSubstrings(List<MatchedSubstring> matchedSubstrings) {
            this.matchedSubstrings = matchedSubstrings;
        }

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public List<Term> getTerms() {
            return terms;
        }

        public void setTerms(List<Term> terms) {
            this.terms = terms;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public StructuredFormatting getStructuredFormatting() {
            return structuredFormatting;
        }

        public void setStructuredFormatting(StructuredFormatting structuredFormatting) {
            this.structuredFormatting = structuredFormatting;
        }
    }

    public class Term {
        @SerializedName("offset")
        @Expose
        private Integer offset;
        @SerializedName("value")
        @Expose
        private String value;

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    public static class StructuredFormatting {
        @SerializedName("main_text")
        @Expose
        private String addressLine1;

        public static StructuredFormatting create(String addressLine1) {
            return new StructuredFormatting(addressLine1);
        }

        private StructuredFormatting(String addressLine1) {
            this.addressLine1 = addressLine1;
        }

        public String getAddressLine1() {
            return addressLine1;
        }

        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }
    }
}