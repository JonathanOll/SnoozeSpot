//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_element
import 'package:snoozespot_api/src/model/user_dto.dart';
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

part 'spot_comment_dto.g.dart';

/// SpotCommentDTO
///
/// Properties:
/// * [id]
/// * [user]
/// * [description]
/// * [rating]
/// * [createdAt]
@BuiltValue()
abstract class SpotCommentDTO
    implements Built<SpotCommentDTO, SpotCommentDTOBuilder> {
  @BuiltValueField(wireName: r'id')
  int get id;

  @BuiltValueField(wireName: r'user')
  UserDTO get user;

  @BuiltValueField(wireName: r'description')
  String get description;

  @BuiltValueField(wireName: r'rating')
  int get rating;

  @BuiltValueField(wireName: r'createdAt')
  DateTime? get createdAt;

  SpotCommentDTO._();

  factory SpotCommentDTO([void updates(SpotCommentDTOBuilder b)]) =
      _$SpotCommentDTO;

  @BuiltValueHook(initializeBuilder: true)
  static void _defaults(SpotCommentDTOBuilder b) => b;

  @BuiltValueSerializer(custom: true)
  static Serializer<SpotCommentDTO> get serializer =>
      _$SpotCommentDTOSerializer();
}

class _$SpotCommentDTOSerializer
    implements PrimitiveSerializer<SpotCommentDTO> {
  @override
  final Iterable<Type> types = const [SpotCommentDTO, _$SpotCommentDTO];

  @override
  final String wireName = r'SpotCommentDTO';

  Iterable<Object?> _serializeProperties(
    Serializers serializers,
    SpotCommentDTO object, {
    FullType specifiedType = FullType.unspecified,
  }) sync* {
    yield r'id';
    yield serializers.serialize(
      object.id,
      specifiedType: const FullType(int),
    );
    yield r'user';
    yield serializers.serialize(
      object.user,
      specifiedType: const FullType(UserDTO),
    );
    yield r'description';
    yield serializers.serialize(
      object.description,
      specifiedType: const FullType(String),
    );
    yield r'rating';
    yield serializers.serialize(
      object.rating,
      specifiedType: const FullType(int),
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
    SpotCommentDTO object, {
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
    required SpotCommentDTOBuilder result,
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
        case r'user':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(UserDTO),
          ) as UserDTO;
          result.user.replace(valueDes);
          break;
        case r'description':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(String),
          ) as String;
          result.description = valueDes;
          break;
        case r'rating':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(int),
          ) as int;
          result.rating = valueDes;
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
  SpotCommentDTO deserialize(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
  }) {
    final result = SpotCommentDTOBuilder();
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
