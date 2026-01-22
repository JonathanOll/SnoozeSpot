import 'package:dio/dio.dart';

sealed class Result<T> {
  R map<R>({
    required R Function(T data) onSuccess,
    required R Function() onEmpty,
    required R Function(Exception e) onFailure,
  }){
    switch(this) {
      case Success(data : final data): return onSuccess(data);
      case EmptySuccess<T>(): return onEmpty();
      case Failure<T>(exception: final exception): return onFailure(exception);
    }
  }

  static Future<Result<T>> guardAsync<T>(Future<Response<T>> Function() call) async {
    try{
      final response = await call();

      final statusCode = response.statusCode ?? 0;
      if (statusCode < 200 || statusCode > 299) {
        throw Exception("Server returned error: $statusCode");
      }

      final result = response.data;
      if(result == null) {
        return EmptySuccess<T>();
      }
      return Success<T>(result);

    } on DioException catch (e) {
      return Failure<T>(Exception(e.message ?? e.type.toString()));
    }catch(caught) {
      final exception = caught is Exception ? caught : Exception(caught.toString());
      return Failure(exception);
    }
  }
}

class Success<T> extends Result<T> {
  final T data;
  Success(this.data);
}

class EmptySuccess<T> extends Result<T> {
  EmptySuccess();
}

class Failure<T> extends Result<T> {
  final Exception exception;
  Failure(this.exception);
}