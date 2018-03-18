package org.djr.retrofit2ee;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Root(strict = false)
@XmlRootElement
public class Response {
    @Element(name = "IP", required = false)
    @Json(name = "ip")
    @SerializedName("ip")
    @XmlElement(name = "IP")
    private String ip;
    @Element(name = "CountryCode", required = false)
    @Json(name = "country_code")
    @SerializedName("country_code")
    @XmlElement(name = "CountryCode", required = false)
    private String countryCode;
    @Element(name = "CountryName", required = false)
    @Json(name = "country_name")
    @SerializedName("country_name")
    @XmlElement(name = "CountryName", required = false)
    private String countryName;
    @Element(name = "RegionCode", required = false)
    @Json(name = "region_code")
    @SerializedName("region_code")
    @XmlElement(name = "RegionCode", required = false)
    private String regionCode;
    @Element(name = "RegionName", required = false)
    @Json(name = "region_name")
    @SerializedName("region_name")
    @XmlElement(name = "RegionName", required = false)
    private String regionName;
    @Element(name = "City", required = false)
    @Json(name = "city")
    @SerializedName("city")
    @XmlElement(name = "City", required = false)
    private String city;
    @Element(name = "ZipCode", required = false)
    @Json(name = "zip_code")
    @SerializedName("zip_code")
    @XmlElement(name = "ZipCode", required = false)
    private String zipCode;
    @Element(name = "TimeZone", required = false)
    @Json(name = "time_zone")
    @SerializedName("time_zone")
    @XmlElement(name = "TimeZone", required = false)
    private String timeZone;
    @Element(name = "Latitude", required = false)
    @Json(name = "latitude")
    @SerializedName("latitude")
    @XmlElement(name = "Latitude", required = false)
    private String latitude;
    @Element(name = "Longitude", required = false)
    @Json(name = "longitude")
    @SerializedName("longitude")
    @XmlElement(name = "Longitude", required = false)
    private String longitude;
    @Element(name = "MetroCode", required = false)
    @Json(name = "metro_code")
    @SerializedName("metro_code")
    @XmlElement(name = "MetroCode", required = false)
    private String metroCode;
    @Element(name = "error", required = false)
    @Json(name = "error")
    @SerializedName("error")
    @XmlElement(name = "error", required = false)
    private String error;
    @Element(name = "status", required = false)
    @Json(name = "status")
    @SerializedName("status")
    @XmlElement(name = "status", required = false)
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
