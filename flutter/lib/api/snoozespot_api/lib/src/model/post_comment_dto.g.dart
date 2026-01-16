// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'post_comment_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$PostCommentDTO extends PostCommentDTO {
  @override
  final int id;
  @override
  final UserDTO user;
  @override
  final String content;
  @override
  final DateTime? createdAt;

  factory _$PostCommentDTO([void Function(PostCommentDTOBuilder)? updates]) =>
      (PostCommentDTOBuilder()..update(updates))._build();

  _$PostCommentDTO._(
      {required this.id,
      required this.user,
      required this.content,
      this.createdAt})
      : super._();
  @override
  PostCommentDTO rebuild(void Function(PostCommentDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  PostCommentDTOBuilder toBuilder() => PostCommentDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is PostCommentDTO &&
        id == other.id &&
        user == other.user &&
        content == other.content &&
        createdAt == other.createdAt;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, id.hashCode);
    _$hash = $jc(_$hash, user.hashCode);
    _$hash = $jc(_$hash, content.hashCode);
    _$hash = $jc(_$hash, createdAt.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'PostCommentDTO')
          ..add('id', id)
          ..add('user', user)
          ..add('content', content)
          ..add('createdAt', createdAt))
        .toString();
  }
}

class PostCommentDTOBuilder
    implements Builder<PostCommentDTO, PostCommentDTOBuilder> {
  _$PostCommentDTO? _$v;

  int? _id;
  int? get id => _$this._id;
  set id(int? id) => _$this._id = id;

  UserDTOBuilder? _user;
  UserDTOBuilder get user => _$this._user ??= UserDTOBuilder();
  set user(UserDTOBuilder? user) => _$this._user = user;

  String? _content;
  String? get content => _$this._content;
  set content(String? content) => _$this._content = content;

  DateTime? _createdAt;
  DateTime? get createdAt => _$this._createdAt;
  set createdAt(DateTime? createdAt) => _$this._createdAt = createdAt;

  PostCommentDTOBuilder() {
    PostCommentDTO._defaults(this);
  }

  PostCommentDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _id = $v.id;
      _user = $v.user.toBuilder();
      _content = $v.content;
      _createdAt = $v.createdAt;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(PostCommentDTO other) {
    _$v = other as _$PostCommentDTO;
  }

  @override
  void update(void Function(PostCommentDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  PostCommentDTO build() => _build();

  _$PostCommentDTO _build() {
    _$PostCommentDTO _$result;
    try {
      _$result = _$v ??
          _$PostCommentDTO._(
            id: BuiltValueNullFieldError.checkNotNull(
                id, r'PostCommentDTO', 'id'),
            user: user.build(),
            content: BuiltValueNullFieldError.checkNotNull(
                content, r'PostCommentDTO', 'content'),
            createdAt: createdAt,
          );
    } catch (_) {
      late String _$failedField;
      try {
        _$failedField = 'user';
        user.build();
      } catch (e) {
        throw BuiltValueNestedFieldError(
            r'PostCommentDTO', _$failedField, e.toString());
      }
      rethrow;
    }
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
