//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_element
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

part 'create_post_comment_request.g.dart';

/// CreatePostCommentRequest
///
/// Properties:
/// * [content]
@BuiltValue()
abstract class CreatePostCommentRequest
    implements
        Built<CreatePostCommentRequest, CreatePostCommentRequestBuilder> {
  @BuiltValueField(wireName: r'content')
  String get content;

  CreatePostCommentRequest._();

  factory CreatePostCommentRequest(
          [void updates(CreatePostCommentRequestBuilder b)]) =
      _$CreatePostCommentRequest;

  @BuiltValueHook(initializeBuilder: true)
  static void _defaults(CreatePostCommentRequestBuilder b) => b;

  @BuiltValueSerializer(custom: true)
  static Serializer<CreatePostCommentRequest> get serializer =>
      _$CreatePostCommentRequestSerializer();
}

class _$CreatePostCommentRequestSerializer
    implements PrimitiveSerializer<CreatePostCommentRequest> {
  @override
  final Iterable<Type> types = const [
    CreatePostCommentRequest,
    _$CreatePostCommentRequest
  ];

  @override
  final String wireName = r'CreatePostCommentRequest';

  Iterable<Object?> _serializeProperties(
    Serializers serializers,
    CreatePostCommentRequest object, {
    FullType specifiedType = FullType.unspecified,
  }) sync* {
    yield r'content';
    yield serializers.serialize(
      object.content,
      specifiedType: const FullType(String),
    );
  }

  @override
  Object serialize(
    Serializers serializers,
    CreatePostCommentRequest object, {
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
    required CreatePostCommentRequestBuilder result,
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
        default:
          unhandled.add(key);
          unhandled.add(value);
          break;
      }
    }
  }

  @override
  CreatePostCommentRequest deserialize(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
  }) {
    final result = CreatePostCommentRequestBuilder();
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
