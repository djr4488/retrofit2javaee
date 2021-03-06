// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: Response.proto at 5:1
package org.djr.retrofit2ee.wire;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import okio.ByteString;

public final class Response extends Message<Response, Response.Builder> {
  public static final ProtoAdapter<Response> ADAPTER = new ProtoAdapter_Response();

  private static final long serialVersionUID = 0L;

  public static final String DEFAULT_IP = "";

  public static final String DEFAULT_COUNTRYCODE = "";

  public static final String DEFAULT_COUNTRYNAME = "";

  public static final String DEFAULT_REGIONCODE = "";

  public static final String DEFAULT_REGIONNAME = "";

  public static final String DEFAULT_CITY = "";

  public static final String DEFAULT_ZIPCODE = "";

  public static final String DEFAULT_TIMEZONE = "";

  public static final String DEFAULT_LATITUDE = "";

  public static final String DEFAULT_LONGITUDE = "";

  public static final String DEFAULT_METROCODE = "";

  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String ip;

  @WireField(
      tag = 2,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String countryCode;

  @WireField(
      tag = 3,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String countryName;

  @WireField(
      tag = 4,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String regionCode;

  @WireField(
      tag = 5,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String regionName;

  @WireField(
      tag = 6,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String city;

  @WireField(
      tag = 7,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String zipCode;

  @WireField(
      tag = 8,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String timeZone;

  @WireField(
      tag = 9,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String latitude;

  @WireField(
      tag = 10,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String longitude;

  @WireField(
      tag = 11,
      adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  public final String metroCode;

  public Response(String ip, String countryCode, String countryName, String regionCode, String regionName, String city, String zipCode, String timeZone, String latitude, String longitude, String metroCode) {
    this(ip, countryCode, countryName, regionCode, regionName, city, zipCode, timeZone, latitude, longitude, metroCode, ByteString.EMPTY);
  }

  public Response(String ip, String countryCode, String countryName, String regionCode, String regionName, String city, String zipCode, String timeZone, String latitude, String longitude, String metroCode, ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    this.ip = ip;
    this.countryCode = countryCode;
    this.countryName = countryName;
    this.regionCode = regionCode;
    this.regionName = regionName;
    this.city = city;
    this.zipCode = zipCode;
    this.timeZone = timeZone;
    this.latitude = latitude;
    this.longitude = longitude;
    this.metroCode = metroCode;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.ip = ip;
    builder.countryCode = countryCode;
    builder.countryName = countryName;
    builder.regionCode = regionCode;
    builder.regionName = regionName;
    builder.city = city;
    builder.zipCode = zipCode;
    builder.timeZone = timeZone;
    builder.latitude = latitude;
    builder.longitude = longitude;
    builder.metroCode = metroCode;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof Response)) return false;
    Response o = (Response) other;
    return unknownFields().equals(o.unknownFields())
        && Internal.equals(ip, o.ip)
        && Internal.equals(countryCode, o.countryCode)
        && Internal.equals(countryName, o.countryName)
        && Internal.equals(regionCode, o.regionCode)
        && Internal.equals(regionName, o.regionName)
        && Internal.equals(city, o.city)
        && Internal.equals(zipCode, o.zipCode)
        && Internal.equals(timeZone, o.timeZone)
        && Internal.equals(latitude, o.latitude)
        && Internal.equals(longitude, o.longitude)
        && Internal.equals(metroCode, o.metroCode);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (ip != null ? ip.hashCode() : 0);
      result = result * 37 + (countryCode != null ? countryCode.hashCode() : 0);
      result = result * 37 + (countryName != null ? countryName.hashCode() : 0);
      result = result * 37 + (regionCode != null ? regionCode.hashCode() : 0);
      result = result * 37 + (regionName != null ? regionName.hashCode() : 0);
      result = result * 37 + (city != null ? city.hashCode() : 0);
      result = result * 37 + (zipCode != null ? zipCode.hashCode() : 0);
      result = result * 37 + (timeZone != null ? timeZone.hashCode() : 0);
      result = result * 37 + (latitude != null ? latitude.hashCode() : 0);
      result = result * 37 + (longitude != null ? longitude.hashCode() : 0);
      result = result * 37 + (metroCode != null ? metroCode.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (ip != null) builder.append(", ip=").append(ip);
    if (countryCode != null) builder.append(", countryCode=").append(countryCode);
    if (countryName != null) builder.append(", countryName=").append(countryName);
    if (regionCode != null) builder.append(", regionCode=").append(regionCode);
    if (regionName != null) builder.append(", regionName=").append(regionName);
    if (city != null) builder.append(", city=").append(city);
    if (zipCode != null) builder.append(", zipCode=").append(zipCode);
    if (timeZone != null) builder.append(", timeZone=").append(timeZone);
    if (latitude != null) builder.append(", latitude=").append(latitude);
    if (longitude != null) builder.append(", longitude=").append(longitude);
    if (metroCode != null) builder.append(", metroCode=").append(metroCode);
    return builder.replace(0, 2, "Response{").append('}').toString();
  }

  public static final class Builder extends Message.Builder<Response, Builder> {
    public String ip;

    public String countryCode;

    public String countryName;

    public String regionCode;

    public String regionName;

    public String city;

    public String zipCode;

    public String timeZone;

    public String latitude;

    public String longitude;

    public String metroCode;

    public Builder() {
    }

    public Builder ip(String ip) {
      this.ip = ip;
      return this;
    }

    public Builder countryCode(String countryCode) {
      this.countryCode = countryCode;
      return this;
    }

    public Builder countryName(String countryName) {
      this.countryName = countryName;
      return this;
    }

    public Builder regionCode(String regionCode) {
      this.regionCode = regionCode;
      return this;
    }

    public Builder regionName(String regionName) {
      this.regionName = regionName;
      return this;
    }

    public Builder city(String city) {
      this.city = city;
      return this;
    }

    public Builder zipCode(String zipCode) {
      this.zipCode = zipCode;
      return this;
    }

    public Builder timeZone(String timeZone) {
      this.timeZone = timeZone;
      return this;
    }

    public Builder latitude(String latitude) {
      this.latitude = latitude;
      return this;
    }

    public Builder longitude(String longitude) {
      this.longitude = longitude;
      return this;
    }

    public Builder metroCode(String metroCode) {
      this.metroCode = metroCode;
      return this;
    }

    @Override
    public Response build() {
      return new Response(ip, countryCode, countryName, regionCode, regionName, city, zipCode, timeZone, latitude, longitude, metroCode, super.buildUnknownFields());
    }
  }

  private static final class ProtoAdapter_Response extends ProtoAdapter<Response> {
    ProtoAdapter_Response() {
      super(FieldEncoding.LENGTH_DELIMITED, Response.class);
    }

    @Override
    public int encodedSize(Response value) {
      return (value.ip != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, value.ip) : 0)
          + (value.countryCode != null ? ProtoAdapter.STRING.encodedSizeWithTag(2, value.countryCode) : 0)
          + (value.countryName != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, value.countryName) : 0)
          + (value.regionCode != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, value.regionCode) : 0)
          + (value.regionName != null ? ProtoAdapter.STRING.encodedSizeWithTag(5, value.regionName) : 0)
          + (value.city != null ? ProtoAdapter.STRING.encodedSizeWithTag(6, value.city) : 0)
          + (value.zipCode != null ? ProtoAdapter.STRING.encodedSizeWithTag(7, value.zipCode) : 0)
          + (value.timeZone != null ? ProtoAdapter.STRING.encodedSizeWithTag(8, value.timeZone) : 0)
          + (value.latitude != null ? ProtoAdapter.STRING.encodedSizeWithTag(9, value.latitude) : 0)
          + (value.longitude != null ? ProtoAdapter.STRING.encodedSizeWithTag(10, value.longitude) : 0)
          + (value.metroCode != null ? ProtoAdapter.STRING.encodedSizeWithTag(11, value.metroCode) : 0)
          + value.unknownFields().size();
    }

    @Override
    public void encode(ProtoWriter writer, Response value) throws IOException {
      if (value.ip != null) ProtoAdapter.STRING.encodeWithTag(writer, 1, value.ip);
      if (value.countryCode != null) ProtoAdapter.STRING.encodeWithTag(writer, 2, value.countryCode);
      if (value.countryName != null) ProtoAdapter.STRING.encodeWithTag(writer, 3, value.countryName);
      if (value.regionCode != null) ProtoAdapter.STRING.encodeWithTag(writer, 4, value.regionCode);
      if (value.regionName != null) ProtoAdapter.STRING.encodeWithTag(writer, 5, value.regionName);
      if (value.city != null) ProtoAdapter.STRING.encodeWithTag(writer, 6, value.city);
      if (value.zipCode != null) ProtoAdapter.STRING.encodeWithTag(writer, 7, value.zipCode);
      if (value.timeZone != null) ProtoAdapter.STRING.encodeWithTag(writer, 8, value.timeZone);
      if (value.latitude != null) ProtoAdapter.STRING.encodeWithTag(writer, 9, value.latitude);
      if (value.longitude != null) ProtoAdapter.STRING.encodeWithTag(writer, 10, value.longitude);
      if (value.metroCode != null) ProtoAdapter.STRING.encodeWithTag(writer, 11, value.metroCode);
      writer.writeBytes(value.unknownFields());
    }

    @Override
    public Response decode(ProtoReader reader) throws IOException {
      Builder builder = new Builder();
      long token = reader.beginMessage();
      for (int tag; (tag = reader.nextTag()) != -1;) {
        switch (tag) {
          case 1: builder.ip(ProtoAdapter.STRING.decode(reader)); break;
          case 2: builder.countryCode(ProtoAdapter.STRING.decode(reader)); break;
          case 3: builder.countryName(ProtoAdapter.STRING.decode(reader)); break;
          case 4: builder.regionCode(ProtoAdapter.STRING.decode(reader)); break;
          case 5: builder.regionName(ProtoAdapter.STRING.decode(reader)); break;
          case 6: builder.city(ProtoAdapter.STRING.decode(reader)); break;
          case 7: builder.zipCode(ProtoAdapter.STRING.decode(reader)); break;
          case 8: builder.timeZone(ProtoAdapter.STRING.decode(reader)); break;
          case 9: builder.latitude(ProtoAdapter.STRING.decode(reader)); break;
          case 10: builder.longitude(ProtoAdapter.STRING.decode(reader)); break;
          case 11: builder.metroCode(ProtoAdapter.STRING.decode(reader)); break;
          default: {
            FieldEncoding fieldEncoding = reader.peekFieldEncoding();
            Object value = fieldEncoding.rawProtoAdapter().decode(reader);
            builder.addUnknownField(tag, fieldEncoding, value);
          }
        }
      }
      reader.endMessage(token);
      return builder.build();
    }

    @Override
    public Response redact(Response value) {
      Builder builder = value.newBuilder();
      builder.clearUnknownFields();
      return builder.build();
    }
  }
}
