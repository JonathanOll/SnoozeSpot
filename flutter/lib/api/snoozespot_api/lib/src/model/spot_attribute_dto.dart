//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_element
import 'package:snoozespot/src/model/stored_file_dto.dart';
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

part 'spot_attribute_dto.g.dart';

/// SpotAttributeDTO
///
/// Properties:
/// * [id]
/// * [name]
/// * [icon]
@BuiltValue()
abstract class SpotAttributeDTO
    implements Built<SpotAttributeDTO, SpotAttributeDTOBuilder> {
  @BuiltValueField(wireName: r'id')
  int get id;

  @BuiltValueField(wireName: r'name')
  String get name;

  @BuiltValueField(wireName: r'icon')
  StoredFileDTO? get icon;

  SpotAttributeDTO._();

  factory SpotAttributeDTO([void updates(SpotAttributeDTOBuilder b)]) =
      _$SpotAttributeDTO;

  @BuiltValueHook(initializeBuilder: true)
  static void _defaults(SpotAttributeDTOBuilder b) => b;

  @BuiltValueSerializer(custom: true)
  static Serializer<SpotAttributeDTO> get serializer =>
      _$SpotAttributeDTOSerializer();
}

class _$SpotAttributeDTOSerializer
    implements PrimitiveSerializer<SpotAttributeDTO> {
  @override
  final Iterable<Type> types = const [SpotAttributeDTO, _$SpotAttributeDTO];

  @override
  final String wireName = r'SpotAttributeDTO';

  Iterable<Object?> _serializeProperties(
    Serializers serializers,
    SpotAttributeDTO object, {
    FullType specifiedType = FullType.unspecified,
  }) sync* {
    yield r'id';
    yield serializers.serialize(
      object.id,
      specifiedType: const FullType(int),
    );
    yield r'name';
    yield serializers.serialize(
      object.name,
      specifiedType: const FullType(String),
    );
    if (object.icon != null) {
      yield r'icon';
      yield serializers.serialize(
        object.icon,
        specifiedType: const FullType(StoredFileDTO),
      );
    }
  }

  @override
  Object serialize(
    Serializers serializers,
    SpotAttributeDTO object, {
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
    required SpotAttributeDTOBuilder result,
    required List<Object?> unhandled,
  }) {
    for (var i = 0; i < serializedList.length; i += 2) {
      final key = serializedList[i] as String;
      final value = serializedList[i + 1];
      switch (key) {
        case r'id':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(int),
          ) as int;
          result.id = valueDes;
          break;
        case r'name':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(String),
          ) as String;
          result.name = valueDes;
          break;
        case r'icon':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(StoredFileDTO),
          ) as StoredFileDTO;
          result.icon.replace(valueDes);
          break;
        default:
          unhandled.add(key);
          unhandled.add(value);
          break;
      }
    }
  }

  @override
  SpotAttributeDTO deserialize(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
  }) {
    final result = SpotAttributeDTOBuilder();
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
