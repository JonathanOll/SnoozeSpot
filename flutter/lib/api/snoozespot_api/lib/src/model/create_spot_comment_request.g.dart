// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'create_spot_comment_request.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$CreateSpotCommentRequest extends CreateSpotCommentRequest {
  @override
  final String content;
  @override
  final int rating;

  factory _$CreateSpotCommentRequest(
          [void Function(CreateSpotCommentRequestBuilder)? updates]) =>
      (CreateSpotCommentRequestBuilder()..update(updates))._build();

  _$CreateSpotCommentRequest._({required this.content, required this.rating})
      : super._();
  @override
  CreateSpotCommentRequest rebuild(
          void Function(CreateSpotCommentRequestBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  CreateSpotCommentRequestBuilder toBuilder() =>
      CreateSpotCommentRequestBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is CreateSpotCommentRequest &&
        content == other.content &&
        rating == other.rating;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, content.hashCode);
    _$hash = $jc(_$hash, rating.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'CreateSpotCommentRequest')
          ..add('content', content)
          ..add('rating', rating))
        .toString();
  }
}

class CreateSpotCommentRequestBuilder
    implements
        Builder<CreateSpotCommentRequest, CreateSpotCommentRequestBuilder> {
  _$CreateSpotCommentRequest? _$v;

  String? _content;
  String? get content => _$this._content;
  set content(String? content) => _$this._content = content;

  int? _rating;
  int? get rating => _$this._rating;
  set rating(int? rating) => _$this._rating = rating;

  CreateSpotCommentRequestBuilder() {
    CreateSpotCommentRequest._defaults(this);
  }

  CreateSpotCommentRequestBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _content = $v.content;
      _rating = $v.rating;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(CreateSpotCommentRequest other) {
    _$v = other as _$CreateSpotCommentRequest;
  }

  @override
  void update(void Function(CreateSpotCommentRequestBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  CreateSpotCommentRequest build() => _build();

  _$CreateSpotCommentRequest _build() {
    final _$result = _$v ??
        _$CreateSpotCommentRequest._(
          content: BuiltValueNullFieldError.checkNotNull(
              content, r'CreateSpotCommentRequest', 'content'),
          rating: BuiltValueNullFieldError.checkNotNull(
              rating, r'CreateSpotCommentRequest', 'rating'),
        );
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
