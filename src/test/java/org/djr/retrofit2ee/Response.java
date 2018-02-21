package org.djr.retrofit2ee;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Response {
    @Element(name = "IP", required = false)
    @Json(name = "ip")
    @SerializedName("ip")
    private String ip;
    @Element(name = "CountryCode", required = false)
    @Json(name = "country_code")
    @SerializedName("country_code")
    private String countryCode;
    @Element(name = "CountryName", required = false)
    @Json(name = "country_name")
    @SerializedName("country_name")
    private String countryName;
    @Element(name = "RegionCode", required = false)
    @Json(name = "region_code")
    @SerializedName("region_code")
    private String regionCode;
    @Element(name = "RegionName", required = false)
    @Json(name = "region_name")
    @SerializedName("region_name")
    private String regionName;
    @Element(name = "City", required = false)
    @Json(name = "city")
    @SerializedName("city")
    private String city;
    @Element(name = "ZipCode", required = false)
    @Json(name = "zip_code")
    @SerializedName("zip_code")
    private String zipCode;
    @Element(name = "TimeZone", required = false)
    @Json(name = "time_zone")
    @SerializedName("time_zone")
    private String timeZone;
    @Element(name = "Latitude", required = false)
    @Json(name = "latitude")
    @SerializedName("latitude")
    private String latitude;
    @Element(name = "Longitude", required = false)
    @Json(name = "longitude")
    @SerializedName("longitude")
    private String longitude;
    @Element(name = "MetroCode", required = false)
    @Json(name = "metro_code")
    @SerializedName("metro_code")
    private String metroCode;
    @Element(name = "error", required = false)
    @Json(name = "error")
    @SerializedName("error")
    private String error;
    @Element(name = "status", required = false)
    @Json(name = "status")
    @SerializedName("status")
    private String status;

    public Response() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMetroCode() {
        return metroCode;
    }

    public void setMetroCode(String metroCode) {
        this.metroCode = metroCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
