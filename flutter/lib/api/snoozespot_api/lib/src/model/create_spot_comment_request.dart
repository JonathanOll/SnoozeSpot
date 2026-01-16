//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_element
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

part 'create_spot_comment_request.g.dart';

/// CreateSpotCommentRequest
///
/// Properties:
/// * [content]
/// * [rating]
@BuiltValue()
abstract class CreateSpotCommentRequest
    implements
        Built<CreateSpotCommentRequest, CreateSpotCommentRequestBuilder> {
  @BuiltValueField(wireName: r'content')
  String get content;

  @BuiltValueField(wireName: r'rating')
  int get rating;

  CreateSpotCommentRequest._();

  factory CreateSpotCommentRequest(
          [void updates(CreateSpotCommentRequestBuilder b)]) =
      _$CreateSpotCommentRequest;

  @BuiltValueHook(initializeBuilder: true)
  static void _defaults(CreateSpotCommentRequestBuilder b) => b;

  @BuiltValueSerializer(custom: true)
  static Serializer<CreateSpotCommentRequest> get serializer =>
      _$CreateSpotCommentRequestSerializer();
}

class _$CreateSpotCommentRequestSerializer
    implements PrimitiveSerializer<CreateSpotCommentRequest> {
  @override
  final Iterable<Type> types = const [
    CreateSpotCommentRequest,
    _$CreateSpotCommentRequest
  ];

  @override
  final String wireName = r'CreateSpotCommentRequest';

  Iterable<Object?> _serializeProperties(
    Serializers serializers,
    CreateSpotCommentRequest object, {
    FullType specifiedType = FullType.unspecified,
  }) sync* {
    yield r'content';
    yield serializers.serialize(
      object.content,
      specifiedType: const FullType(String),
    );
    yield r'rating';
    yield serializers.serialize(
      object.rating,
      specifiedType: const FullType(int),
    );
  }

  @override
  Object serialize(
    Serializers serializers,
    CreateSpotCommentRequest object, {
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
    required CreateSpotCommentRequestBuilder result,
    required List<Object?> unhandled,
  }) {
    for (var i = 0; i < serializedList.length; i += 2) {
      final key = serializedList[i] as String;
      final value = serializedList[i + 1];
      switch (key) {
        case r'content':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(String),
          ) as String;
          result.content = valueDes;
          break;
        case r'rating':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(int),
          ) as int;
          result.rating = valueDes;
          break;
        default:
          unhandled.add(key);
          unhandled.add(value);
          break;
      }
    }
  }

  @override
  CreateSpotCommentRequest deserialize(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
  }) {
    final result = CreateSpotCommentRequestBuilder();
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
