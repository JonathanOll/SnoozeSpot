// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'spot_attribute_dto.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$SpotAttributeDTO extends SpotAttributeDTO {
  @override
  final int id;
  @override
  final String name;
  @override
  final StoredFileDTO? icon;

  factory _$SpotAttributeDTO(
          [void Function(SpotAttributeDTOBuilder)? updates]) =>
      (SpotAttributeDTOBuilder()..update(updates))._build();

  _$SpotAttributeDTO._({required this.id, required this.name, this.icon})
      : super._();
  @override
  SpotAttributeDTO rebuild(void Function(SpotAttributeDTOBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  SpotAttributeDTOBuilder toBuilder() =>
      SpotAttributeDTOBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is SpotAttributeDTO &&
        id == other.id &&
        name == other.name &&
        icon == other.icon;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, id.hashCode);
    _$hash = $jc(_$hash, name.hashCode);
    _$hash = $jc(_$hash, icon.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'SpotAttributeDTO')
          ..add('id', id)
          ..add('name', name)
          ..add('icon', icon))
        .toString();
  }
}

class SpotAttributeDTOBuilder
    implements Builder<SpotAttributeDTO, SpotAttributeDTOBuilder> {
  _$SpotAttributeDTO? _$v;

  int? _id;
  int? get id => _$this._id;
  set id(int? id) => _$this._id = id;

  String? _name;
  String? get name => _$this._name;
  set name(String? name) => _$this._name = name;

  StoredFileDTOBuilder? _icon;
  StoredFileDTOBuilder get icon => _$this._icon ??= StoredFileDTOBuilder();
  set icon(StoredFileDTOBuilder? icon) => _$this._icon = icon;

  SpotAttributeDTOBuilder() {
    SpotAttributeDTO._defaults(this);
  }

  SpotAttributeDTOBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _id = $v.id;
      _name = $v.name;
      _icon = $v.icon?.toBuilder();
      _$v = null;
    }
    return this;
  }

  @override
  void replace(SpotAttributeDTO other) {
    _$v = other as _$SpotAttributeDTO;
  }

  @override
  void update(void Function(SpotAttributeDTOBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  SpotAttributeDTO build() => _build();

  _$SpotAttributeDTO _build() {
    _$SpotAttributeDTO _$result;
    try {
      _$result = _$v ??
          _$SpotAttributeDTO._(
            id: BuiltValueNullFieldError.checkNotNull(
                id, r'SpotAttributeDTO', 'id'),
            name: BuiltValueNullFieldError.checkNotNull(
                name, r'SpotAttributeDTO', 'name'),
            icon: _icon?.build(),
          );
    } catch (_) {
      late String _$failedField;
      try {
        _$failedField = 'icon';
        _icon?.build();
      } catch (e) {
        throw BuiltValueNestedFieldError(
            r'SpotAttributeDTO', _$failedField, e.toString());
      }
      rethrow;
    }
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
