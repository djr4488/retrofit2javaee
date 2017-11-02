package org.djr.retrofit2ee.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.ConfigFeature;

public enum JacksonFeatureEnumerations {
	/** MapperFeatures **/
	USE_ANNOTATIONS(MapperFeature.USE_ANNOTATIONS),
	AUTO_DETECT_CREATORS(MapperFeature.AUTO_DETECT_CREATORS),
	AUTO_DETECT_FIELDS(MapperFeature.AUTO_DETECT_FIELDS),
	AUTO_DETECT_GETTERS(MapperFeature.AUTO_DETECT_GETTERS),
	AUTO_DETECT_IS_GETTERS(MapperFeature.AUTO_DETECT_IS_GETTERS),
	AUTO_DETECT_SETTERS(MapperFeature.AUTO_DETECT_SETTERS),
	REQUIRE_SETTERS_FOR_GETTERS(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS),
	USE_GETTERS_AS_SETTERS(MapperFeature.USE_GETTERS_AS_SETTERS),
	CAN_OVERRIDE_ACCESS_MODIFIERS(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS),
	OVERRIDE_PUBLIC_ACCESS_MODIFIERS(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS),
	INFER_PROPERTY_MUTATORS(MapperFeature.INFER_PROPERTY_MUTATORS),
	ALLOW_FINAL_FIELDS_AS_MUTATORS(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS),
	PROPAGATE_TRANSIENT_MARKER(MapperFeature.PROPAGATE_TRANSIENT_MARKER),
	USE_STATIC_TYPING(MapperFeature.USE_STATIC_TYPING),
	DEFAULT_VIEW_INCLUSION(MapperFeature.DEFAULT_VIEW_INCLUSION),
	SORT_PROPERTIES_ALPHABETICALLY(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY),
	ACCEPT_CASE_INSENSITIVE_PROPERTIES(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES),
	USE_WRAPPER_NAME_AS_PROPERTY_NAME(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME),
	USE_STD_BEAN_NAMING(MapperFeature.USE_STD_BEAN_NAMING),
	ALLOW_EXPLICIT_PROPERTY_RENAMING(MapperFeature.ALLOW_EXPLICIT_PROPERTY_RENAMING),
	IGNORE_DUPLICATE_MODULE_REGISTRATIONS(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS),

	/** SerializationFeatures **/
	WRAP_ROOT_VALUE(SerializationFeature.WRAP_ROOT_VALUE),
	INDENT_OUTPUT(SerializationFeature.INDENT_OUTPUT),
	FAIL_ON_EMPTY_BEANS(SerializationFeature.FAIL_ON_EMPTY_BEANS),
	FAIL_ON_SELF_REFERENCES(SerializationFeature.FAIL_ON_SELF_REFERENCES),
	SERIALIZATION_WRAP_EXCEPTIONS(SerializationFeature.WRAP_EXCEPTIONS),
	FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS),
	CLOSE_CLOSEABLE(SerializationFeature.CLOSE_CLOSEABLE),
	FLUSH_AFTER_WRITE_VALUE(SerializationFeature.FLUSH_AFTER_WRITE_VALUE),
	WRITE_DATES_AS_TIMESTAMPS(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS),
	WRITE_DATE_KEYS_AS_TIMESTAMPS(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS),
	WRITE_DATES_WITH_ZONE_ID(SerializationFeature.WRITE_DATES_WITH_ZONE_ID),
	WRITE_DURATIONS_AS_TIMESTAMPS(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS),
	WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS),
	WRITE_ENUMS_USING_TO_STRING(SerializationFeature.WRITE_ENUMS_USING_TO_STRING),
	WRITE_ENUMS_USING_INDEX(SerializationFeature.WRITE_ENUMS_USING_INDEX),
	WRITE_NULL_MAP_VALUES(SerializationFeature.WRITE_NULL_MAP_VALUES),
	WRITE_EMPTY_JSON_ARRAYS(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS),
	WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED),
	/** @deprecated */
	@Deprecated
	WRITE_BIGDECIMAL_AS_PLAIN(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN),
	WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS),
	ORDER_MAP_ENTRIES_BY_KEYS(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS),
	EAGER_SERIALIZER_FETCH(SerializationFeature.EAGER_SERIALIZER_FETCH),
	USE_EQUALITY_FOR_OBJECT_ID(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID),

	/** DeserializationFeatures **/
	USE_BIG_DECIMAL_FOR_FLOATS(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS),
	USE_BIG_INTEGER_FOR_INTS(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS),
	USE_LONG_FOR_INTS(DeserializationFeature.USE_LONG_FOR_INTS),
	USE_JAVA_ARRAY_FOR_JSON_ARRAY(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY),
	READ_ENUMS_USING_TO_STRING(DeserializationFeature.READ_ENUMS_USING_TO_STRING),
	FAIL_ON_UNKNOWN_PROPERTIES(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES),
	FAIL_ON_NULL_FOR_PRIMITIVES(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES),
	FAIL_ON_NUMBERS_FOR_ENUMS(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS),
	FAIL_ON_INVALID_SUBTYPE(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE),
	FAIL_ON_READING_DUP_TREE_KEY(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY),
	FAIL_ON_IGNORED_PROPERTIES(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES),
	FAIL_ON_UNRESOLVED_OBJECT_IDS(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS),
	FAIL_ON_MISSING_CREATOR_PROPERTIES(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES),
	DESERIALIZATION_WRAP_EXCEPTIONS(DeserializationFeature.WRAP_EXCEPTIONS),
	ACCEPT_SINGLE_VALUE_AS_ARRAY(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY),
	UNWRAP_SINGLE_VALUE_ARRAYS(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS),
	UNWRAP_ROOT_VALUE(DeserializationFeature.UNWRAP_ROOT_VALUE),
	ACCEPT_EMPTY_STRING_AS_NULL_OBJECT(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT),
	ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT),
	ACCEPT_FLOAT_AS_INT(DeserializationFeature.ACCEPT_FLOAT_AS_INT),
	READ_UNKNOWN_ENUM_VALUES_AS_NULL(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL),
	READ_DATE_TIMESTAMPS_AS_NANOSECONDS(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS),
	ADJUST_DATES_TO_CONTEXT_TIME_ZONE(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE),
	EAGER_DESERIALIZER_FETCH(DeserializationFeature.EAGER_DESERIALIZER_FETCH);


	private ConfigFeature configFeature;

	private JacksonFeatureEnumerations(ConfigFeature configFeature) {
		this.configFeature = configFeature;
	}

	public ConfigFeature getConfigFeature() {
		return this.configFeature;
	}
}
