package com.example.cf_sdk.logic.changebanksdk.model.google;

import com.example.cf_sdk.logic.changebanksdk.parameter.google.PlaceDetailParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddAddressParameters;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * Response from {@link com.example.cf_sdk.logic.changebanksdk.source.GoogleDatasource} to
 * retrieve address selected.
 */

public class PlaceDetailSerializer {

    @SerializedName("result")
    private Place result;

    @SerializedName("status")
    private String status;

    public void setPlace(Place result) {
        this.result = result;
    }

    public Place getPlace() {
        return result;
    }

    public static PlaceDetailSerializer create(Place place) {
        return new PlaceDetailSerializer(place);
    }

    public PlaceDetailSerializer(Place place) {
        setPlace(place);
    }

    public AddAddressParameters convertToAddAddressParameters(PlaceDetailParameters parameters) {

        Map<String, String> headers = parameters.getHeaders();
        String street = parameters.getAddressLine1();
        String unit =  parameters.getUnit();
        String zipCode = null;
        String city = null;
        String state = null;
        String country = null;

        for (AddressComponent component : getPlace().getAddressComponents()) {
            if (Arrays.asList(component.getTypes()).contains("locality")) {
                city = component.getLong_name();
            } else if (Arrays.asList(component.getTypes()).contains("administrative_area_level_1")) {
                state = component.getShort_name();
            } else if (Arrays.asList(component.getTypes()).contains("country")) {
                country = component.getShort_name();
            } else if (Arrays.asList(component.getTypes()).contains("postal_code")) {
                zipCode = component.getLong_name();
            }
        }

        return new AddAddressParameters(headers, street, unit, zipCode, city, state, country,"USA");
    }

    public class Geometry {
        public class Location {
            Double lat;
            Double lng;
        }

        Location location;
    }

    public static class AddressComponent {
        @SerializedName("long_name")
        String long_name;

        @SerializedName("short_name")
        String short_name;

        @SerializedName("types")
        String[] types;

        public static AddressComponent create(String[] types, String shortName, String longName) {
            return new AddressComponent(types, shortName, longName);
        }

        public AddressComponent(String[] types, String shortName, String longName) {
            this.types = types;
            this.short_name = shortName;
            this.long_name = longName;
        }

        public String getLong_name() {
            return long_name;
        }

        public String getShort_name() {
            return short_name;
        }

        public String[] getTypes() {
            return types;
        }
    }

    public class Photo {
        int height;
        int width;
        String photo_reference;
    }

    public static class Place {
        @SerializedName("place_id")
        private String mPlaceId;

        @SerializedName("name")
        private String mName;

        @SerializedName("address_components")
        private List<AddressComponent> mAddressComponents;

        @SerializedName("formatted_address")
        private String mFormattedAddress;

        @SerializedName("international_phone_number")
        private String mPhoneNumber;

        @SerializedName("photos")
        private List<Photo> mPhotos;

        @SerializedName("geometry")
        private Geometry mGeometry;

        @SerializedName("icon")
        private String mIcon;

        @SerializedName("rating")
        Double mRating;

        @SerializedName("types")
        private String[] mTypes;

        @SerializedName("url")
        private String mUrl;

        @SerializedName("vicinity")
        private String mVicinity;

        @SerializedName("website")
        private String mWebsite;

        public static Place create(String placeId,
                                   String name,
                                   List<AddressComponent> addressComponents) {
            return new Place(placeId, name, addressComponents);
        }

        public Place(String placeId,
                     String name,
                     List<AddressComponent> addressComponents) {
            mPlaceId = placeId;
            mName = name;
            mAddressComponents = addressComponents;
        }

        public String getID() {
            return mPlaceId;
        }

        public String getmName() {
            return mName;
        }

        public String getAddress() {
            return mFormattedAddress;
        }

        public List<AddressComponent> getAddressComponents(){
            return mAddressComponents;
        }

        public void setAddressComponents(List<AddressComponent> addressComponents) {
            mAddressComponents = addressComponents;
        }

        @Override
        public String toString() {
            return "Place{" +
                    "mName='" + mName + '\'' +
                    '}';
        }
    }

}