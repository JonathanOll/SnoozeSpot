//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_element
import 'package:snoozespot/src/model/user_dto.dart';
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

part 'auth_response_dto.g.dart';

/// AuthResponseDTO
///
/// Properties:
/// * [accessToken]
/// * [expiresIn]
/// * [user]
@BuiltValue()
abstract class AuthResponseDTO
    implements Built<AuthResponseDTO, AuthResponseDTOBuilder> {
  @BuiltValueField(wireName: r'accessToken')
  String get accessToken;

  @BuiltValueField(wireName: r'expiresIn')
  int get expiresIn;

  @BuiltValueField(wireName: r'user')
  UserDTO get user;

  AuthResponseDTO._();

  factory AuthResponseDTO([void updates(AuthResponseDTOBuilder b)]) =
      _$AuthResponseDTO;

  @BuiltValueHook(initializeBuilder: true)
  static void _defaults(AuthResponseDTOBuilder b) => b;

  @BuiltValueSerializer(custom: true)
  static Serializer<AuthResponseDTO> get serializer =>
      _$AuthResponseDTOSerializer();
}

class _$AuthResponseDTOSerializer
    implements PrimitiveSerializer<AuthResponseDTO> {
  @override
  final Iterable<Type> types = const [AuthResponseDTO, _$AuthResponseDTO];

  @override
  final String wireName = r'AuthResponseDTO';

  Iterable<Object?> _serializeProperties(
    Serializers serializers,
    AuthResponseDTO object, {
    FullType specifiedType = FullType.unspecified,
  }) sync* {
    yield r'accessToken';
    yield serializers.serialize(
      object.accessToken,
      specifiedType: const FullType(String),
    );
    yield r'expiresIn';
    yield serializers.serialize(
      object.expiresIn,
      specifiedType: const FullType(int),
    );
    yield r'user';
    yield serializers.serialize(
      object.user,
      specifiedType: const FullType(UserDTO),
    );
  }

  @override
  Object serialize(
    Serializers serializers,
    AuthResponseDTO object, {
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
    required AuthResponseDTOBuilder result,
    required List<Object?> unhandled,
  }) {
    for (var i = 0; i < serializedList.length; i += 2) {
      final key = serializedList[i] as String;
      final value = serializedList[i + 1];
      switch (key) {
        case r'accessToken':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(String),
          ) as String;
          result.accessToken = valueDes;
          break;
        case r'expiresIn':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(int),
          ) as int;
          result.expiresIn = valueDes;
          break;
        case r'user':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(UserDTO),
          ) as UserDTO;
          result.user.replace(valueDes);
          break;
        default:
          unhandled.add(key);
          unhandled.add(value);
          break;
      }
    }
  }

  @override
  AuthResponseDTO deserialize(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
  }) {
    final result = AuthResponseDTOBuilder();
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
