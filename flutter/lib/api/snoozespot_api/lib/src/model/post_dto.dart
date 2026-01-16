//
// AUTO-GENERATED FILE, DO NOT MODIFY!
//

// ignore_for_file: unused_element
import 'package:built_collection/built_collection.dart';
import 'package:snoozespot/src/model/stored_file_dto.dart';
import 'package:snoozespot/src/model/user_dto.dart';
import 'package:snoozespot/src/model/post_comment_dto.dart';
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';

part 'post_dto.g.dart';

/// PostDTO
///
/// Properties:
/// * [id]
/// * [user]
/// * [content]
/// * [likeCount]
/// * [pictures]
/// * [comments]
/// * [likedByUser]
/// * [createdAt]
@BuiltValue()
abstract class PostDTO implements Built<PostDTO, PostDTOBuilder> {
  @BuiltValueField(wireName: r'id')
  int get id;

  @BuiltValueField(wireName: r'user')
  UserDTO get user;

  @BuiltValueField(wireName: r'content')
  String get content;

  @BuiltValueField(wireName: r'likeCount')
  int get likeCount;

  @BuiltValueField(wireName: r'pictures')
  BuiltList<StoredFileDTO> get pictures;

  @BuiltValueField(wireName: r'comments')
  BuiltList<PostCommentDTO> get comments;

  @BuiltValueField(wireName: r'likedByUser')
  bool get likedByUser;

  @BuiltValueField(wireName: r'createdAt')
  DateTime? get createdAt;

  PostDTO._();

  factory PostDTO([void updates(PostDTOBuilder b)]) = _$PostDTO;

  @BuiltValueHook(initializeBuilder: true)
  static void _defaults(PostDTOBuilder b) => b;

  @BuiltValueSerializer(custom: true)
  static Serializer<PostDTO> get serializer => _$PostDTOSerializer();
}

class _$PostDTOSerializer implements PrimitiveSerializer<PostDTO> {
  @override
  final Iterable<Type> types = const [PostDTO, _$PostDTO];

  @override
  final String wireName = r'PostDTO';

  Iterable<Object?> _serializeProperties(
    Serializers serializers,
    PostDTO object, {
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
    yield r'content';
    yield serializers.serialize(
      object.content,
      specifiedType: const FullType(String),
    );
    yield r'likeCount';
    yield serializers.serialize(
      object.likeCount,
      specifiedType: const FullType(int),
    );
    yield r'pictures';
    yield serializers.serialize(
      object.pictures,
      specifiedType: const FullType(BuiltList, [FullType(StoredFileDTO)]),
    );
    yield r'comments';
    yield serializers.serialize(
      object.comments,
      specifiedType: const FullType(BuiltList, [FullType(PostCommentDTO)]),
    );
    yield r'likedByUser';
    yield serializers.serialize(
      object.likedByUser,
      specifiedType: const FullType(bool),
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
    PostDTO object, {
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
    required PostDTOBuilder result,
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
        case r'content':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(String),
          ) as String;
          result.content = valueDes;
          break;
        case r'likeCount':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(int),
          ) as int;
          result.likeCount = valueDes;
          break;
        case r'pictures':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(BuiltList, [FullType(StoredFileDTO)]),
          ) as BuiltList<StoredFileDTO>;
          result.pictures.replace(valueDes);
          break;
        case r'comments':
          final valueDes = serializers.deserialize(
            value,
            specifiedType:
                const FullType(BuiltList, [FullType(PostCommentDTO)]),
          ) as BuiltList<PostCommentDTO>;
          result.comments.replace(valueDes);
          break;
        case r'likedByUser':
          final valueDes = serializers.deserialize(
            value,
            specifiedType: const FullType(bool),
          ) as bool;
          result.likedByUser = valueDes;
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
  PostDTO deserialize(
    Serializers serializers,
    Object serialized, {
    FullType specifiedType = FullType.unspecified,
  }) {
    final result = PostDTOBuilder();
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
