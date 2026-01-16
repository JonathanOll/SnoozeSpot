//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_element
import 'package:built_collection/built_collection.dart';
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

part 'stored_file_dto.g.dart';

/// StoredFileDTO
///
/// Properties:
/// * [uuid]
/// * [description]
/// * [path]
/// * [type]
/// * [usage]
/// * [createdAt]
@BuiltValue()
abstract class StoredFileDTO
    implements Built<StoredFileDTO, StoredFileDTOBuilder> {
  @BuiltValueField(wireName: r'uuid')
  String get uuid;

  @BuiltValueField(wireName: r'description')
  String get description;

  @BuiltValueField(wireName: r'path')
  String get path;

  @BuiltValueField(wireName: r'type')
  StoredFileDTOTypeEnum get type;
  // enum typeEnum {  UNKNOWN,  IMAGE,  VIDEO,  AUDIO,  DOCUMENT,  OTHER,  };

  @BuiltValueField(wireName: r'usage')
  StoredFileDTOUsageEnum get usage;
  // enum usageEnum {  UNKNOW,  SPOT_ATTRIBUTES_ICON,  PROFILE_PICTURE,  SPOT_MEDIA,  POST_MEDIA,  COMMENT_MEDIA,  OTHER,  };

  @BuiltValueField(wireName: r'createdAt')
  DateTime? get createdAt;

  StoredFileDTO._();

  factory StoredFileDTO([void updates(StoredFileDTOBuilder b)]) =
      _$StoredFileDTO;

  @BuiltValueHook(initializeBuilder: true)
  static void _defaults(StoredFileDTOBuilder b) => b;

  @BuiltValueSerializer(custom: true)
  static Serializer<StoredFileDTO> get serializer =>
      _$StoredFileDTOSerializer();
}

class _$StoredFileDTOSerializer implements PrimitiveSerializer<StoredFileDTO> {
  @override
  final Iterable<Type> types = const [StoredFileDTO, _$StoredFileDTO];

  @override
  final String wireName = r'StoredFileDTO';

  Iterable<Object?> _serializeProperties(
    Serializers serializers,
    StoredFileDTO object, {
    FullType specifiedType = FullType.unspecified,
  }) sync* {
    yield r'uuid';
    yield serializers.serialize(
      object.uuid,
      specifiedType: const FullType(String),
    );
    yield r'description';
    yield serializers.serialize(
      object.description,
      specifiedType: const FullType(String),
    );
    yield r'path';
    yield serializers.serialize(
      object.path,
      specifiedType: const FullType(String),
    );
    yield r'type';
    yield serializers.serialize(
      object.type,
      specifiedType: const FullType(StoredFileDTOTypeEnum),
    );
    yield r'usage';
    yield serializers.serialize(
      object.usage,
      specifiedType: const FullType(StoredFileDTOUsageEnum),
    );
    if (object.createdAt != null) {
      yield r'createdAt';
      yield serializers.serialize(
        object.createdAt,
        specifiedType: const FullType(DateTime),
      );
    }
  }

  @override
  Object serialize(
    Serializers serializers,
    StoredFileDTO object, {
    FullType specifiedType = FullType.unspecified,
  }) {
    return _serializeProperties(serializers, object,
            specifiedType: specifiedType)
        .toList();
  }

  void _deserializeProperties(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
    required List<Object?> serializedList,
    required StoredFileDTOBuilder result,
    required List<Object?> unhandled,
  }) {
    for (var i = 0; i < serializedList.length; i += 2) {
      final key = serializedList[i] as String;
      final value = serializedList[i + 1];
      switch (key) {
        case r'uuid':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(String),
          ) as String;
          result.uuid = valueDes;
          break;
        case r'description':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(String),
          ) as String;
          result.description = valueDes;
          break;
        case r'path':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(String),
          ) as String;
          result.path = valueDes;
          break;
        case r'type':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(StoredFileDTOTypeEnum),
          ) as StoredFileDTOTypeEnum;
          result.type = valueDes;
          break;
        case r'usage':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(StoredFileDTOUsageEnum),
          ) as StoredFileDTOUsageEnum;
          result.usage = valueDes;
          break;
        case r'createdAt':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(DateTime),
          ) as DateTime;
          result.createdAt = valueDes;
          break;
        default:
          unhandled.add(key);
          unhandled.add(value);
          break;
      }
    }
  }

  @override
  StoredFileDTO deserialize(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
  }) {
    final result = StoredFileDTOBuilder();
    final serializedList = (serialized as Iterable<Object?>).toList();
    final unhandled = <Object?>[];
    _deserializeProperties(
      serializers,
      serialized,
      specifiedType: specifiedType,
      serializedList: serializedList,
      unhandled: unhandled,
      result: result,
    );
    return result.build();
  }
}

class StoredFileDTOTypeEnum extends EnumClass {
  @BuiltValueEnumConst(wireName: r'UNKNOWN')
  static const StoredFileDTOTypeEnum UNKNOWN = _$storedFileDTOTypeEnum_UNKNOWN;
  @BuiltValueEnumConst(wireName: r'IMAGE')
  static const StoredFileDTOTypeEnum IMAGE = _$storedFileDTOTypeEnum_IMAGE;
  @BuiltValueEnumConst(wireName: r'VIDEO')
  static const StoredFileDTOTypeEnum VIDEO = _$storedFileDTOTypeEnum_VIDEO;
  @BuiltValueEnumConst(wireName: r'AUDIO')
  static const StoredFileDTOTypeEnum AUDIO = _$storedFileDTOTypeEnum_AUDIO;
  @BuiltValueEnumConst(wireName: r'DOCUMENT')
  static const StoredFileDTOTypeEnum DOCUMENT =
      _$storedFileDTOTypeEnum_DOCUMENT;
  @BuiltValueEnumConst(wireName: r'OTHER')
  static const StoredFileDTOTypeEnum OTHER = _$storedFileDTOTypeEnum_OTHER;

  static Serializer<StoredFileDTOTypeEnum> get serializer =>
      _$storedFileDTOTypeEnumSerializer;

  const StoredFileDTOTypeEnum._(String name) : super(name);

  static BuiltSet<StoredFileDTOTypeEnum> get values =>
      _$storedFileDTOTypeEnumValues;
  static StoredFileDTOTypeEnum valueOf(String name) =>
      _$storedFileDTOTypeEnumValueOf(name);
}

class StoredFileDTOUsageEnum extends EnumClass {
  @BuiltValueEnumConst(wireName: r'UNKNOW')
  static const StoredFileDTOUsageEnum UNKNOW = _$storedFileDTOUsageEnum_UNKNOW;
  @BuiltValueEnumConst(wireName: r'SPOT_ATTRIBUTES_ICON')
  static const StoredFileDTOUsageEnum SPOT_ATTRIBUTES_ICON =
      _$storedFileDTOUsageEnum_SPOT_ATTRIBUTES_ICON;
  @BuiltValueEnumConst(wireName: r'PROFILE_PICTURE')
  static const StoredFileDTOUsageEnum PROFILE_PICTURE =
      _$storedFileDTOUsageEnum_PROFILE_PICTURE;
  @BuiltValueEnumConst(wireName: r'SPOT_MEDIA')
  static const StoredFileDTOUsageEnum SPOT_MEDIA =
      _$storedFileDTOUsageEnum_SPOT_MEDIA;
  @BuiltValueEnumConst(wireName: r'POST_MEDIA')
  static const StoredFileDTOUsageEnum POST_MEDIA =
      _$storedFileDTOUsageEnum_POST_MEDIA;
  @BuiltValueEnumConst(wireName: r'COMMENT_MEDIA')
  static const StoredFileDTOUsageEnum COMMENT_MEDIA =
      _$storedFileDTOUsageEnum_COMMENT_MEDIA;
  @BuiltValueEnumConst(wireName: r'OTHER')
  static const StoredFileDTOUsageEnum OTHER = _$storedFileDTOUsageEnum_OTHER;

  static Serializer<StoredFileDTOUsageEnum> get serializer =>
      _$storedFileDTOUsageEnumSerializer;

  const StoredFileDTOUsageEnum._(String name) : super(name);

  static BuiltSet<StoredFileDTOUsageEnum> get values =>
      _$storedFileDTOUsageEnumValues;
  static StoredFileDTOUsageEnum valueOf(String name) =>
      _$storedFileDTOUsageEnumValueOf(name);
}
