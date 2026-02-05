// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'spot_comment_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$SpotCommentDTO extends SpotCommentDTO {
  @override
  final int id;
  @override
  final UserDTO user;
  @override
  final String description;
  @override
  final int rating;
  @override
  final DateTime? createdAt;

  factory _$SpotCommentDTO([void Function(SpotCommentDTOBuilder)? updates]) =>
      (SpotCommentDTOBuilder()..update(updates))._build();

  _$SpotCommentDTO._({
    required this.id,
    required this.user,
    required this.description,
    required this.rating,
    this.createdAt,
  }) : super._();
  @override
  SpotCommentDTO rebuild(void Function(SpotCommentDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  SpotCommentDTOBuilder toBuilder() => SpotCommentDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is SpotCommentDTO &&
        id == other.id &&
        user == other.user &&
        description == other.description &&
        rating == other.rating &&
        createdAt == other.createdAt;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, id.hashCode);
    _$hash = $jc(_$hash, user.hashCode);
    _$hash = $jc(_$hash, description.hashCode);
    _$hash = $jc(_$hash, rating.hashCode);
    _$hash = $jc(_$hash, createdAt.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'SpotCommentDTO')
          ..add('id', id)
          ..add('user', user)
          ..add('description', description)
          ..add('rating', rating)
          ..add('createdAt', createdAt))
        .toString();
  }
}

class SpotCommentDTOBuilder
    implements Builder<SpotCommentDTO, SpotCommentDTOBuilder> {
  _$SpotCommentDTO? _$v;

  int? _id;
  int? get id => _$this._id;
  set id(int? id) => _$this._id = id;

  UserDTOBuilder? _user;
  UserDTOBuilder get user => _$this._user ??= UserDTOBuilder();
  set user(UserDTOBuilder? user) => _$this._user = user;

  String? _description;
  String? get description => _$this._description;
  set description(String? description) => _$this._description = description;

  int? _rating;
  int? get rating => _$this._rating;
  set rating(int? rating) => _$this._rating = rating;

  DateTime? _createdAt;
  DateTime? get createdAt => _$this._createdAt;
  set createdAt(DateTime? createdAt) => _$this._createdAt = createdAt;

  SpotCommentDTOBuilder() {
    SpotCommentDTO._defaults(this);
  }

  SpotCommentDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _id = $v.id;
      _user = $v.user.toBuilder();
      _description = $v.description;
      _rating = $v.rating;
      _createdAt = $v.createdAt;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(SpotCommentDTO other) {
    _$v = other as _$SpotCommentDTO;
  }

  @override
  void update(void Function(SpotCommentDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  SpotCommentDTO build() => _build();

  _$SpotCommentDTO _build() {
    _$SpotCommentDTO _$result;
    try {
      _$result =
          _$v ??
          _$SpotCommentDTO._(
            id: BuiltValueNullFieldError.checkNotNull(
              id,
              r'SpotCommentDTO',
              'id',
            ),
            user: user.build(),
            description: BuiltValueNullFieldError.checkNotNull(
              description,
              r'SpotCommentDTO',
              'description',
            ),
            rating: BuiltValueNullFieldError.checkNotNull(
              rating,
              r'SpotCommentDTO',
              'rating',
            ),
            createdAt: createdAt,
          );
    } catch (_) {
      late String _$failedField;
      try {
        _$failedField = 'user';
        user.build();
      } catch (e) {
        throw BuiltValueNestedFieldError(
          r'SpotCommentDTO',
          _$failedField,
          e.toString(),
        );
      }
      rethrow;
    }
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
