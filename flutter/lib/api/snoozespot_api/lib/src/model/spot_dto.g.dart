// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'spot_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$SpotDTO extends SpotDTO {
  @override
  final int id;
  @override
  final String name;
  @override
  final String description;
  @override
  final double latitude;
  @override
  final double longitude;
  @override
  final int likeCount;
  @override
  final BuiltList<StoredFileDTO> pictures;
  @override
  final BuiltList<SpotAttributeDTO> attributes;
  @override
  final BuiltList<SpotCommentDTO> comments;
  @override
  final UserDTO? creator;
  @override
  final double? rating;
  @override
  final DateTime? createdAt;

  factory _$SpotDTO([void Function(SpotDTOBuilder)? updates]) =>
      (SpotDTOBuilder()..update(updates))._build();

  _$SpotDTO._(
      {required this.id,
      required this.name,
      required this.description,
      required this.latitude,
      required this.longitude,
      required this.likeCount,
      required this.pictures,
      required this.attributes,
      required this.comments,
      this.creator,
      this.rating,
      this.createdAt})
      : super._();
  @override
  SpotDTO rebuild(void Function(SpotDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  SpotDTOBuilder toBuilder() => SpotDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is SpotDTO &&
        id == other.id &&
        name == other.name &&
        description == other.description &&
        latitude == other.latitude &&
        longitude == other.longitude &&
        likeCount == other.likeCount &&
        pictures == other.pictures &&
        attributes == other.attributes &&
        comments == other.comments &&
        creator == other.creator &&
        rating == other.rating &&
        createdAt == other.createdAt;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, id.hashCode);
    _$hash = $jc(_$hash, name.hashCode);
    _$hash = $jc(_$hash, description.hashCode);
    _$hash = $jc(_$hash, latitude.hashCode);
    _$hash = $jc(_$hash, longitude.hashCode);
    _$hash = $jc(_$hash, likeCount.hashCode);
    _$hash = $jc(_$hash, pictures.hashCode);
    _$hash = $jc(_$hash, attributes.hashCode);
    _$hash = $jc(_$hash, comments.hashCode);
    _$hash = $jc(_$hash, creator.hashCode);
    _$hash = $jc(_$hash, rating.hashCode);
    _$hash = $jc(_$hash, createdAt.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'SpotDTO')
          ..add('id', id)
          ..add('name', name)
          ..add('description', description)
          ..add('latitude', latitude)
          ..add('longitude', longitude)
          ..add('likeCount', likeCount)
          ..add('pictures', pictures)
          ..add('attributes', attributes)
          ..add('comments', comments)
          ..add('creator', creator)
          ..add('rating', rating)
          ..add('createdAt', createdAt))
        .toString();
  }
}

class SpotDTOBuilder implements Builder<SpotDTO, SpotDTOBuilder> {
  _$SpotDTO? _$v;

  int? _id;
  int? get id => _$this._id;
  set id(int? id) => _$this._id = id;

  String? _name;
  String? get name => _$this._name;
  set name(String? name) => _$this._name = name;

  String? _description;
  String? get description => _$this._description;
  set description(String? description) => _$this._description = description;

  double? _latitude;
  double? get latitude => _$this._latitude;
  set latitude(double? latitude) => _$this._latitude = latitude;

  double? _longitude;
  double? get longitude => _$this._longitude;
  set longitude(double? longitude) => _$this._longitude = longitude;

  int? _likeCount;
  int? get likeCount => _$this._likeCount;
  set likeCount(int? likeCount) => _$this._likeCount = likeCount;

  ListBuilder<StoredFileDTO>? _pictures;
  ListBuilder<StoredFileDTO> get pictures =>
      _$this._pictures ??= ListBuilder<StoredFileDTO>();
  set pictures(ListBuilder<StoredFileDTO>? pictures) =>
      _$this._pictures = pictures;

  ListBuilder<SpotAttributeDTO>? _attributes;
  ListBuilder<SpotAttributeDTO> get attributes =>
      _$this._attributes ??= ListBuilder<SpotAttributeDTO>();
  set attributes(ListBuilder<SpotAttributeDTO>? attributes) =>
      _$this._attributes = attributes;

  ListBuilder<SpotCommentDTO>? _comments;
  ListBuilder<SpotCommentDTO> get comments =>
      _$this._comments ??= ListBuilder<SpotCommentDTO>();
  set comments(ListBuilder<SpotCommentDTO>? comments) =>
      _$this._comments = comments;

  UserDTOBuilder? _creator;
  UserDTOBuilder get creator => _$this._creator ??= UserDTOBuilder();
  set creator(UserDTOBuilder? creator) => _$this._creator = creator;

  double? _rating;
  double? get rating => _$this._rating;
  set rating(double? rating) => _$this._rating = rating;

  DateTime? _createdAt;
  DateTime? get createdAt => _$this._createdAt;
  set createdAt(DateTime? createdAt) => _$this._createdAt = createdAt;

  SpotDTOBuilder() {
    SpotDTO._defaults(this);
  }

  SpotDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _id = $v.id;
      _name = $v.name;
      _description = $v.description;
      _latitude = $v.latitude;
      _longitude = $v.longitude;
      _likeCount = $v.likeCount;
      _pictures = $v.pictures.toBuilder();
      _attributes = $v.attributes.toBuilder();
      _comments = $v.comments.toBuilder();
      _creator = $v.creator?.toBuilder();
      _rating = $v.rating;
      _createdAt = $v.createdAt;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(SpotDTO other) {
    _$v = other as _$SpotDTO;
  }

  @override
  void update(void Function(SpotDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  SpotDTO build() => _build();

  _$SpotDTO _build() {
    _$SpotDTO _$result;
    try {
      _$result = _$v ??
          _$SpotDTO._(
            id: BuiltValueNullFieldError.checkNotNull(id, r'SpotDTO', 'id'),
            name:
                BuiltValueNullFieldError.checkNotNull(name, r'SpotDTO', 'name'),
            description: BuiltValueNullFieldError.checkNotNull(
                description, r'SpotDTO', 'description'),
            latitude: BuiltValueNullFieldError.checkNotNull(
                latitude, r'SpotDTO', 'latitude'),
            longitude: BuiltValueNullFieldError.checkNotNull(
                longitude, r'SpotDTO', 'longitude'),
            likeCount: BuiltValueNullFieldError.checkNotNull(
                likeCount, r'SpotDTO', 'likeCount'),
            pictures: pictures.build(),
            attributes: attributes.build(),
            comments: comments.build(),
            creator: _creator?.build(),
            rating: rating,
            createdAt: createdAt,
          );
    } catch (_) {
      late String _$failedField;
      try {
        _$failedField = 'pictures';
        pictures.build();
        _$failedField = 'attributes';
        attributes.build();
        _$failedField = 'comments';
        comments.build();
        _$failedField = 'creator';
        _creator?.build();
      } catch (e) {
        throw BuiltValueNestedFieldError(
            r'SpotDTO', _$failedField, e.toString());
      }
      rethrow;
    }
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
