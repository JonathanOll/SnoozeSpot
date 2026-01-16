// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'post_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$PostDTO extends PostDTO {
  @override
  final int id;
  @override
  final UserDTO user;
  @override
  final String content;
  @override
  final int likeCount;
  @override
  final BuiltList<StoredFileDTO> pictures;
  @override
  final BuiltList<PostCommentDTO> comments;
  @override
  final bool likedByUser;
  @override
  final DateTime? createdAt;

  factory _$PostDTO([void Function(PostDTOBuilder)? updates]) =>
      (PostDTOBuilder()..update(updates))._build();

  _$PostDTO._(
      {required this.id,
      required this.user,
      required this.content,
      required this.likeCount,
      required this.pictures,
      required this.comments,
      required this.likedByUser,
      this.createdAt})
      : super._();
  @override
  PostDTO rebuild(void Function(PostDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  PostDTOBuilder toBuilder() => PostDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is PostDTO &&
        id == other.id &&
        user == other.user &&
        content == other.content &&
        likeCount == other.likeCount &&
        pictures == other.pictures &&
        comments == other.comments &&
        likedByUser == other.likedByUser &&
        createdAt == other.createdAt;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, id.hashCode);
    _$hash = $jc(_$hash, user.hashCode);
    _$hash = $jc(_$hash, content.hashCode);
    _$hash = $jc(_$hash, likeCount.hashCode);
    _$hash = $jc(_$hash, pictures.hashCode);
    _$hash = $jc(_$hash, comments.hashCode);
    _$hash = $jc(_$hash, likedByUser.hashCode);
    _$hash = $jc(_$hash, createdAt.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'PostDTO')
          ..add('id', id)
          ..add('user', user)
          ..add('content', content)
          ..add('likeCount', likeCount)
          ..add('pictures', pictures)
          ..add('comments', comments)
          ..add('likedByUser', likedByUser)
          ..add('createdAt', createdAt))
        .toString();
  }
}

class PostDTOBuilder implements Builder<PostDTO, PostDTOBuilder> {
  _$PostDTO? _$v;

  int? _id;
  int? get id => _$this._id;
  set id(int? id) => _$this._id = id;

  UserDTOBuilder? _user;
  UserDTOBuilder get user => _$this._user ??= UserDTOBuilder();
  set user(UserDTOBuilder? user) => _$this._user = user;

  String? _content;
  String? get content => _$this._content;
  set content(String? content) => _$this._content = content;

  int? _likeCount;
  int? get likeCount => _$this._likeCount;
  set likeCount(int? likeCount) => _$this._likeCount = likeCount;

  ListBuilder<StoredFileDTO>? _pictures;
  ListBuilder<StoredFileDTO> get pictures =>
      _$this._pictures ??= ListBuilder<StoredFileDTO>();
  set pictures(ListBuilder<StoredFileDTO>? pictures) =>
      _$this._pictures = pictures;

  ListBuilder<PostCommentDTO>? _comments;
  ListBuilder<PostCommentDTO> get comments =>
      _$this._comments ??= ListBuilder<PostCommentDTO>();
  set comments(ListBuilder<PostCommentDTO>? comments) =>
      _$this._comments = comments;

  bool? _likedByUser;
  bool? get likedByUser => _$this._likedByUser;
  set likedByUser(bool? likedByUser) => _$this._likedByUser = likedByUser;

  DateTime? _createdAt;
  DateTime? get createdAt => _$this._createdAt;
  set createdAt(DateTime? createdAt) => _$this._createdAt = createdAt;

  PostDTOBuilder() {
    PostDTO._defaults(this);
  }

  PostDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _id = $v.id;
      _user = $v.user.toBuilder();
      _content = $v.content;
      _likeCount = $v.likeCount;
      _pictures = $v.pictures.toBuilder();
      _comments = $v.comments.toBuilder();
      _likedByUser = $v.likedByUser;
      _createdAt = $v.createdAt;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(PostDTO other) {
    _$v = other as _$PostDTO;
  }

  @override
  void update(void Function(PostDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  PostDTO build() => _build();

  _$PostDTO _build() {
    _$PostDTO _$result;
    try {
      _$result = _$v ??
          _$PostDTO._(
            id: BuiltValueNullFieldError.checkNotNull(id, r'PostDTO', 'id'),
            user: user.build(),
            content: BuiltValueNullFieldError.checkNotNull(
                content, r'PostDTO', 'content'),
            likeCount: BuiltValueNullFieldError.checkNotNull(
                likeCount, r'PostDTO', 'likeCount'),
            pictures: pictures.build(),
            comments: comments.build(),
            likedByUser: BuiltValueNullFieldError.checkNotNull(
                likedByUser, r'PostDTO', 'likedByUser'),
            createdAt: createdAt,
          );
    } catch (_) {
      late String _$failedField;
      try {
        _$failedField = 'user';
        user.build();

        _$failedField = 'pictures';
        pictures.build();
        _$failedField = 'comments';
        comments.build();
      } catch (e) {
        throw BuiltValueNestedFieldError(
            r'PostDTO', _$failedField, e.toString());
      }
      rethrow;
    }
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
