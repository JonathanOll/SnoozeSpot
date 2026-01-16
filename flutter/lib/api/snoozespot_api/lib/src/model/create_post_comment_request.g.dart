// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'create_post_comment_request.dart';

// **************************************************************************
// BuiltValueGenerator
// **************************************************************************

class _$CreatePostCommentRequest extends CreatePostCommentRequest {
  @override
  final String content;

  factory _$CreatePostCommentRequest(
          [void Function(CreatePostCommentRequestBuilder)? updates]) =>
      (CreatePostCommentRequestBuilder()..update(updates))._build();

  _$CreatePostCommentRequest._({required this.content}) : super._();
  @override
  CreatePostCommentRequest rebuild(
          void Function(CreatePostCommentRequestBuilder) updates) =>
      (toBuilder()..update(updates)).build();

  @override
  CreatePostCommentRequestBuilder toBuilder() =>
      CreatePostCommentRequestBuilder()..replace(this);

  @override
  bool operator ==(Object other) {
    if (identical(other, this)) return true;
    return other is CreatePostCommentRequest && content == other.content;
  }

  @override
  int get hashCode {
    var _$hash = 0;
    _$hash = $jc(_$hash, content.hashCode);
    _$hash = $jf(_$hash);
    return _$hash;
  }

  @override
  String toString() {
    return (newBuiltValueToStringHelper(r'CreatePostCommentRequest')
          ..add('content', content))
        .toString();
  }
}

class CreatePostCommentRequestBuilder
    implements
        Builder<CreatePostCommentRequest, CreatePostCommentRequestBuilder> {
  _$CreatePostCommentRequest? _$v;

  String? _content;
  String? get content => _$this._content;
  set content(String? content) => _$this._content = content;

  CreatePostCommentRequestBuilder() {
    CreatePostCommentRequest._defaults(this);
  }

  CreatePostCommentRequestBuilder get _$this {
    final $v = _$v;
    if ($v != null) {
      _content = $v.content;
      _$v = null;
    }
    return this;
  }

  @override
  void replace(CreatePostCommentRequest other) {
    _$v = other as _$CreatePostCommentRequest;
  }

  @override
  void update(void Function(CreatePostCommentRequestBuilder)? updates) {
    if (updates != null) updates(this);
  }

  @override
  CreatePostCommentRequest build() => _build();

  _$CreatePostCommentRequest _build() {
    final _$result = _$v ??
        _$CreatePostCommentRequest._(
          content: BuiltValueNullFieldError.checkNotNull(
              content, r'CreatePostCommentRequest', 'content'),
        );
    replace(_$result);
    return _$result;
  }
}

// ignore_for_file: deprecated_member_use_from_same_package,type=lint
