// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$UserDTO extends UserDTO {
  @override
  final String uuid;
  @override
  final String username;
  @override
  final int karma;
  @override
  final DateTime createdAt;
  @override
  final BuiltList<SpotDTO> spots;
  @override
  final BuiltList<PostDTO> posts;
  @override
  final BuiltList<UserDTO> following;
  @override
  final BuiltList<UserDTO> followers;
  @override
  final String? email;
  @override
  final StoredFileDTO? profilePicture;

  factory _$UserDTO([void Function(UserDTOBuilder)? updates]) =>
      (UserDTOBuilder()..update(updates))._build();

  _$UserDTO._(
      {required this.uuid,
      required this.username,
      required this.karma,
      required this.createdAt,
      required this.spots,
      required this.posts,
      required this.following,
      required this.followers,
      this.email,
      this.profilePicture})
      : super._();
  @override
  UserDTO rebuild(void Function(UserDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  UserDTOBuilder toBuilder() => UserDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is UserDTO &&
        uuid == other.uuid &&
        username == other.username &&
        karma == other.karma &&
        createdAt == other.createdAt &&
        spots == other.spots &&
        posts == other.posts &&
        following == other.following &&
        followers == other.followers &&
        email == other.email &&
        profilePicture == other.profilePicture;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, uuid.hashCode);
    _$hash = $jc(_$hash, username.hashCode);
    _$hash = $jc(_$hash, karma.hashCode);
    _$hash = $jc(_$hash, createdAt.hashCode);
    _$hash = $jc(_$hash, spots.hashCode);
    _$hash = $jc(_$hash, posts.hashCode);
    _$hash = $jc(_$hash, following.hashCode);
    _$hash = $jc(_$hash, followers.hashCode);
    _$hash = $jc(_$hash, email.hashCode);
    _$hash = $jc(_$hash, profilePicture.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'UserDTO')
          ..add('uuid', uuid)
          ..add('username', username)
          ..add('karma', karma)
          ..add('createdAt', createdAt)
          ..add('spots', spots)
          ..add('posts', posts)
          ..add('following', following)
          ..add('followers', followers)
          ..add('email', email)
          ..add('profilePicture', profilePicture))
        .toString();
  }
}

class UserDTOBuilder implements Builder<UserDTO, UserDTOBuilder> {
  _$UserDTO? _$v;

  String? _uuid;
  String? get uuid => _$this._uuid;
  set uuid(String? uuid) => _$this._uuid = uuid;

  String? _username;
  String? get username => _$this._username;
  set username(String? username) => _$this._username = username;

  int? _karma;
  int? get karma => _$this._karma;
  set karma(int? karma) => _$this._karma = karma;

  DateTime? _createdAt;
  DateTime? get createdAt => _$this._createdAt;
  set createdAt(DateTime? createdAt) => _$this._createdAt = createdAt;

  ListBuilder<SpotDTO>? _spots;
  ListBuilder<SpotDTO> get spots => _$this._spots ??= ListBuilder<SpotDTO>();
  set spots(ListBuilder<SpotDTO>? spots) => _$this._spots = spots;

  ListBuilder<PostDTO>? _posts;
  ListBuilder<PostDTO> get posts => _$this._posts ??= ListBuilder<PostDTO>();
  set posts(ListBuilder<PostDTO>? posts) => _$this._posts = posts;

  ListBuilder<UserDTO>? _following;
  ListBuilder<UserDTO> get following =>
      _$this._following ??= ListBuilder<UserDTO>();
  set following(ListBuilder<UserDTO>? following) =>
      _$this._following = following;

  ListBuilder<UserDTO>? _followers;
  ListBuilder<UserDTO> get followers =>
      _$this._followers ??= ListBuilder<UserDTO>();
  set followers(ListBuilder<UserDTO>? followers) =>
      _$this._followers = followers;

  String? _email;
  String? get email => _$this._email;
  set email(String? email) => _$this._email = email;

  StoredFileDTOBuilder? _profilePicture;
  StoredFileDTOBuilder get profilePicture =>
      _$this._profilePicture ??= StoredFileDTOBuilder();
  set profilePicture(StoredFileDTOBuilder? profilePicture) =>
      _$this._profilePicture = profilePicture;

  UserDTOBuilder() {
    UserDTO._defaults(this);
  }

  UserDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _uuid = $v.uuid;
      _username = $v.username;
      _karma = $v.karma;
      _createdAt = $v.createdAt;
      _spots = $v.spots.toBuilder();
      _posts = $v.posts.toBuilder();
      _following = $v.following.toBuilder();
      _followers = $v.followers.toBuilder();
      _email = $v.email;
      _profilePicture = $v.profilePicture?.toBuilder();
      _$v = null;
    }
    return this;
  }

  @override
  void replace(UserDTO other) {
    _$v = other as _$UserDTO;
  }

  @override
  void update(void Function(UserDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  UserDTO build() => _build();

  _$UserDTO _build() {
    _$UserDTO _$result;
    try {
      _$result = _$v ??
          _$UserDTO._(
            uuid:
                BuiltValueNullFieldError.checkNotNull(uuid, r'UserDTO', 'uuid'),
            username: BuiltValueNullFieldError.checkNotNull(
                username, r'UserDTO', 'username'),
            karma: BuiltValueNullFieldError.checkNotNull(
                karma, r'UserDTO', 'karma'),
            createdAt: BuiltValueNullFieldError.checkNotNull(
                createdAt, r'UserDTO', 'createdAt'),
            spots: spots.build(),
            posts: posts.build(),
            following: following.build(),
            followers: followers.build(),
            email: email,
            profilePicture: _profilePicture?.build(),
          );
    } catch (_) {
      late String _$failedField;
      try {
        _$failedField = 'spots';
        spots.build();
        _$failedField = 'posts';
        posts.build();
        _$failedField = 'following';
        following.build();
        _$failedField = 'followers';
        followers.build();

        _$failedField = 'profilePicture';
        _profilePicture?.build();
      } catch (e) {
        throw BuiltValueNestedFieldError(
            r'UserDTO', _$failedField, e.toString());
      }
      rethrow;
    }
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
